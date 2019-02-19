package net.realme.mall.basics.dao;

import net.realme.mall.basics.model.Translation;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface TranslationMapper extends Mapper<Translation> {
    int batchInsert(List<Translation> list);
}
