package net.realme.scm.wms.dao;

import net.realme.scm.wms.model.ProductCreateHistory;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ProductCreateHistoryMapper extends Mapper<ProductCreateHistory> {
    Integer batchInsert(List<ProductCreateHistory> productCreateHistories);
}