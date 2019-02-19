package net.realme.mall.oms.core.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.realme.framework.util.dto.ResultList;
import net.realme.mall.oms.core.beantool.FileUploadConverter;
import net.realme.mall.oms.core.dao.FileUploadMapper;
import net.realme.mall.oms.core.facade.FileUploadService;
import net.realme.mall.oms.core.model.FileUpload;
import net.realme.mall.oms.domain.core.FileUploadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional(rollbackFor = Exception.class)
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private FileUploadMapper fileUploadMapper;

    @Autowired
    private FileUploadConverter fileUploadConverter;

    @Override
    public int addFile(FileUploadDto fileUploadDto) {
        FileUpload fileUpload =  fileUploadConverter.fromFileUploadDto(fileUploadDto);
        if (fileUploadMapper.insert(fileUpload) > 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public ResultList<FileUploadDto> list(int page, int limit) {
        PageHelper.startPage(page, limit, true);
        List<FileUpload> records = fileUploadMapper.selectAll();
        PageInfo<FileUpload> pageInfo = new PageInfo<>(records);
        ResultList<FileUploadDto> result = new ResultList<>();
        if (pageInfo.getTotal() > 0) {
            List<FileUploadDto> dtoRecords = fileUploadConverter.toFileUploadDtoList(pageInfo.getList());
            result.setRecords(dtoRecords);
        }
        result.setPageNum(page);
        result.setPageSize(limit);
        result.setRecordTotal(pageInfo.getTotal());
        return result;
    }
}