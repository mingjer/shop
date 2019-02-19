package net.realme.mall.basics.facade;

import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.mall.basics.dto.CurrencyDto;


/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.basics.facade
 *
 * @author 91000044
 * @date 2018/7/24 17:28
 */
public interface CurrencyService {


    /**
     * 添加货种
     *
     * @param currencyDto
     * @return
     */
    ResultT<Integer> addCurrency(CurrencyDto currencyDto);

    /**
     * 更新币种
     *
     * @param currencyDto
     * @return
     */
    ResultT<Integer>  updateCurrency(CurrencyDto currencyDto);

    /**
     * 更新币种状态
     *
     * @param currencyDto
     * @return
     */
    ResultT<Integer>  updateCurrencyStatus(CurrencyDto currencyDto);

    /**
     * 按缩写获取币种
     *
     * @return
     */
    ResultT<CurrencyDto>  getByAbbr(String abbr);

    /**
     * 按ID获取币种
     *
     * @return
     */
    ResultT<CurrencyDto>  getById(int id);

    /**
     * 币种列表
     *
     * @return
     */
    ResultT<ResultList<CurrencyDto>> list(int page, int limit);

    /**
     * 根据siteCode 获取币种列表
     *
     * @param siteCode
     * @return
     */
    ResultT<CurrencyDto> getBySiteCode(String siteCode);

}
