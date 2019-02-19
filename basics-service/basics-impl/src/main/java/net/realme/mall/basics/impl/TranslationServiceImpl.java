package net.realme.mall.basics.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.mall.basics.beantool.TranslationConverter;
import net.realme.mall.basics.dao.TranslationMapper;
import net.realme.mall.basics.dto.SiteEnum;
import net.realme.mall.basics.dto.TranslationDto;
import net.realme.mall.basics.dto.TranslationQuery;
import net.realme.mall.basics.facade.TranslationService;
import net.realme.mall.basics.model.Translation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;


@Service
public class TranslationServiceImpl implements TranslationService {

    @Autowired
    private TranslationConverter translationConverter;

    @Autowired
    private TranslationMapper translationMapper;

    @Override
    public ResultT<Integer> translationAdd(TranslationDto translationDto) {
        Translation translation = translationConverter.fromTranslationDto(translationDto);
        if (translationMapper.insert(translation)>0) {
            return ResultT.success(1);
        }
        return ResultT.fail();
    }
    @Override
    public ResultT<Integer> updateTranslation(TranslationDto translationDto) {
        Translation translation = translationConverter.fromTranslationDto(translationDto);
        if (translationMapper.updateByPrimaryKeySelective(translation)>0) {
            return ResultT.success(1);
        }
        return ResultT.fail();
    }

    @Override
    public ResultT<TranslationDto> getById(int id) {
        Translation translation = translationMapper.selectByPrimaryKey(id);
        if (translation != null) {
            return ResultT.success(translationConverter.toTranslationDto(translation));
        }
     return ResultT.fail();
    }

    @Override
    public ResultT<Integer> updateTranslationStatus(TranslationDto translationDto) {
        Translation translation = translationConverter.fromTranslationDto(translationDto);
        if (translationMapper.updateByPrimaryKeySelective(translation) > 0) {
            return ResultT.success(1);
        }
        return ResultT.fail();
    }

    @Override
    public ResultT<ResultList<TranslationDto>> listByDefault(TranslationQuery translationQuery) {
        ResultList<TranslationDto> result = new ResultList<>();
        if (translationQuery == null) {
            return ResultT.success(result);
        }
        String orderBy = "updated_at DESC";
        PageHelper.startPage(translationQuery.getPage(), translationQuery.getLimit(), true);
        Example example = new Example(Translation.class);
        example.setOrderByClause(orderBy);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("siteCode", SiteEnum.DEFAULT_SITE.getValue());
        List<Translation> records = translationMapper.selectByExample(example);
        PageInfo<Translation> pageInfo = new PageInfo<>(records);
        if (pageInfo.getTotal() > 0) {
            List<TranslationDto> dtoRecords = translationConverter.toTranslationDtoList(pageInfo.getList());
            result.setRecords(dtoRecords);
        }
        result.setPageNum(1);
        result.setPageSize(Math.toIntExact(pageInfo.getTotal()));
        result.setRecordTotal(pageInfo.getTotal());
        return ResultT.success(result);
    }

    @Override
    public ResultT<Integer> batchInsert(List<TranslationDto> translationDtos) {
        List<Translation> translations = translationConverter.fromTranslationDtoList(translationDtos);
        if (translationMapper.batchInsert(translations) > 0) {
            return ResultT.success(1);
        }
        return ResultT.fail();
    }

    @Override
    public ResultT<ResultList<TranslationDto>> listByKey(String t9nKey) {
        ResultList<TranslationDto> result = new ResultList<>();
        String orderBy = "updated_at DESC";
        Example example = new Example(Translation.class);
        example.setOrderByClause(orderBy);
        if (StringUtils.isEmpty(t9nKey)) {
            return ResultT.success(result);
        }
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("t9nKey", t9nKey);
        criteria.andNotEqualTo("siteCode", SiteEnum.DEFAULT_SITE.getValue());
        List<Translation> records = translationMapper.selectByExample(example);
        PageInfo<Translation> pageInfo = new PageInfo<>(records);
        if (pageInfo.getTotal() > 0) {
            List<TranslationDto> dtoRecords = translationConverter.toTranslationDtoList(pageInfo.getList());
            result.setRecords(dtoRecords);
        }
        result.setPageNum(1);
        result.setPageSize(Math.toIntExact(pageInfo.getTotal()));
        result.setRecordTotal(pageInfo.getTotal());
        return ResultT.success(result);
    }

    @Override
    public ResultT<List<TranslationDto>> listValues(String t9nKey, String siteCode) {
        List<TranslationDto> result = new ArrayList<>();
        if (t9nKey == null ) {
            return ResultT.success(result);
        }
        Example example = new Example(Translation.class);
        Example.Criteria criteria1 = example.createCriteria();
        criteria1.andEqualTo("t9nKey", t9nKey);
        Example.Criteria criteria2 = example.createCriteria();
        criteria2.andEqualTo("siteCode", SiteEnum.DEFAULT_SITE.getValue());
        if (!StringUtils.isBlank(siteCode)) {
            criteria2.orEqualTo("siteCode", siteCode);
        }
        Example.Criteria criteria3 = example.createCriteria();
        criteria3.andEqualTo("status", 1);
        example.and(criteria2);
        example.and(criteria3);
        List<Translation> records = translationMapper.selectByExample(example);
        PageInfo<Translation> pageInfo = new PageInfo<>(records);
        if (pageInfo.getTotal() > 0) {
            return ResultT.success(translationConverter.toTranslationDtoList(pageInfo.getList()));
        }
        return ResultT.success(result);
    }

    @Override
    public ResultT<Integer> deleteBySiteCode(TranslationQuery translationQuery) {
        String orderBy = "created_at DESC";
        Example example = new Example(Translation.class);
        example.setOrderByClause(orderBy);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("t9nKey", translationQuery.getT9nKey());
        criteria.andNotEqualTo("siteCode",SiteEnum.DEFAULT_SITE.getValue());
        return ResultT.success(translationMapper.deleteByExample(example));
    }

    @Override
    public ResultT<Integer> delete(TranslationQuery translationQuery) {
        String orderBy = "created_at DESC";
        Example example = new Example(Translation.class);
        example.setOrderByClause(orderBy);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("t9nKey", translationQuery.getT9nKey());
        return ResultT.success(translationMapper.deleteByExample(example));
    }
}




