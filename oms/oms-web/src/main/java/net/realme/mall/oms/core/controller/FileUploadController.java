package net.realme.mall.oms.core.controller;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import io.swagger.annotations.*;
import net.realme.framework.util.dto.Result;
import net.realme.framework.util.dto.ResultList;
import net.realme.framework.web.controller.BaseController;
import net.realme.mall.oms.core.facade.FileUploadService;
import net.realme.mall.oms.domain.core.FileUploadDto;
import net.realme.mall.oms.util.AmazonS3Service;
import net.realme.mall.oms.util.PathManager;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;


@Api(tags = {"文件管理"})
@RequestMapping("/file")
@RestController
public class FileUploadController extends BaseController {

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private AmazonS3Service amazonS3Service;

    @Autowired
    private PathManager pathManager;

    @ApiOperation(value = "文件上传")
    @PostMapping(value = "/upload", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "root", value = "存储根目录"),
            @ApiImplicitParam(paramType = "form", name = "description", value = "文件描述"),
    })
    public Result upload(@ApiParam(value = "上传的文件", required = true) MultipartFile file, String root, String description) {
        FileUploadDto fileUploadDto = new FileUploadDto();
        fileUploadDto.setOriginalName(file.getOriginalFilename());
        fileUploadDto.setDescription(description);
        String extension = FilenameUtils.getExtension(fileUploadDto.getOriginalName());
        fileUploadDto.setFormat(extension);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        if (StringUtils.isBlank(root)) {
            root = FileUploadService.DIR_GENERAL;
        }
        String path = root + "/" + now.format(formatter) + "/" + System.currentTimeMillis() + "." + extension;
        fileUploadDto.setPath(path);
        fileUploadDto.setSize(file.getSize());
        fileUploadDto.setUploadedAt(System.currentTimeMillis());
        try {
            PutObjectResult putObjectResult = amazonS3Service.publicUpload(path, file.getInputStream(), file.getContentType());
            HashMap<String, String> data = new HashMap<>(10);
            data.put("filename", file.getOriginalFilename());
            data.put("path", path);
            data.put("url", pathManager.getS3Url(path));
            int req = fileUploadService.addFile(fileUploadDto);
            if (req > 0) {
                return ok(data);
            }
        } catch (IOException e) {
            logger.error("file upload failed.", e);
            return err(e.getMessage());
        }
        return errI18N("err.file.upload");
    }

    @ApiOperation(value = "文件列表")
    @GetMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "int", value = "页面", name = "page", defaultValue = "1"),
            @ApiImplicitParam(name = "limit", value = "页大小", paramType = "query", defaultValue = "20"),
    })
    public Result list(int page, int limit) {
        ResultList<FileUploadDto> list = fileUploadService.list(page, limit);
        return ok(list);
    }
}
