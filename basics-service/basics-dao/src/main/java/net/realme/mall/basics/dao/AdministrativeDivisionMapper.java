package net.realme.mall.basics.dao;

import java.util.List;

import net.realme.mall.basics.model.AdministrativeDivision;
import tk.mybatis.mapper.common.Mapper;

public interface AdministrativeDivisionMapper extends Mapper<AdministrativeDivision> {

	int batchInsert(List<AdministrativeDivision> divisionDtos);

}