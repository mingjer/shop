package net.realme.mall.basics.facade;

import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.mall.basics.dto.TranslationDto;
import net.realme.mall.basics.dto.TranslationQuery;

import java.util.List;

public interface TranslationService {

    ResultT<Integer> translationAdd(TranslationDto translationDto);

    ResultT<Integer> updateTranslation(TranslationDto translationDto);

    ResultT<TranslationDto> getById(int id);

    ResultT<Integer>  updateTranslationStatus(TranslationDto translationDto);

    /**
     * 返回所有default的值
     * @param translationQuery
     * @return
     */
    ResultT<ResultList<TranslationDto>> listByDefault(TranslationQuery translationQuery);

    ResultT<Integer> batchInsert(List<TranslationDto> translationDto);

    /**
     * 返回该key所有站点下的值
     * @param t9nKey
     * @return
     */
    ResultT<ResultList<TranslationDto>> listByKey(String t9nKey);

    /**
     * 返回该key在siteCode下对应的值（包括默认值）
     *
     * @param t9nKey
     * @param siteCode
     * @return
     */
    ResultT<List<TranslationDto>> listValues(String t9nKey, String siteCode);

    ResultT<Integer>  deleteBySiteCode(TranslationQuery translationQuery);

    ResultT<Integer>  delete(TranslationQuery translationQuery);
}