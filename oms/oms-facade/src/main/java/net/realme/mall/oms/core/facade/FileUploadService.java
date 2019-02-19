package net.realme.mall.oms.core.facade;

import net.realme.framework.util.dto.ResultList;
import net.realme.mall.oms.domain.core.FileUploadDto;


/**
 * @author 91000044
 */
public interface FileUploadService {

  /**
   * 常规，默认路径
   */
  String DIR_GENERAL = "general";
  String DIR_VIDEO = "video";
  /**
   * 产品站
   */
  String DIR_PRODUCT = "product";
  String DIR_ATTACHMENT = "attachment";
  /**
   * 商品
   */
  String DIR_GOODS = "goods";

  /**
   * 添加文件上传记录
   *
   * @param multipartFile
   * @return
   */
  int addFile(FileUploadDto multipartFile);

  /**
   * 文件上传记录列表
   *
   * @param page
   * @param limit
   * @return
   */
  ResultList<FileUploadDto> list(int page , int limit);
}
