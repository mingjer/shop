package net.realme.mall.basics.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.realme.framework.cache.redis.RedisCache;
import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.mall.basics.beantool.CurrencyConverter;
import net.realme.mall.basics.consts.CacheConsts;
import net.realme.mall.basics.dao.CurrencyMapper;
import net.realme.mall.basics.dto.CurrencyDto;
import net.realme.mall.basics.dto.SiteDto;
import net.realme.mall.basics.facade.CurrencyService;
import net.realme.mall.basics.facade.SiteService;
import net.realme.mall.basics.model.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.basics.impl
 *
 * @author 91000044
 * @date 2018/7/24 17:33
 */
@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyMapper currencyMapper;

    @Autowired
    private SiteService siteService;

    @Autowired
    private CurrencyConverter currencyConverter;

    @Autowired
    private RedisCache cache;

    @Override
    public ResultT<Integer> addCurrency(CurrencyDto currencyDto) {
        Currency currency = currencyConverter.fromCurrencyDto(currencyDto);
        if (currencyMapper.insert(currency) > 0) {
            return ResultT.success(currency.getId());
        }
        return ResultT.fail();
    }

    @Override
    public ResultT<Integer>  updateCurrency(CurrencyDto currencyDto) {
        Currency currency = currencyConverter.fromCurrencyDto(currencyDto);
        if (currencyMapper.updateByPrimaryKey(currency) > 0) {
            return ResultT.success(1);
        }
        return ResultT.fail();
    }

    @Override
    public ResultT<Integer>  updateCurrencyStatus(CurrencyDto currencyDto) {
        Currency currency = currencyConverter.fromCurrencyDto(currencyDto);
        if (currencyMapper.updateByPrimaryKeySelective(currency) > 0) {
            return ResultT.success(1);
        }
        return ResultT.fail();
    }

    @Override
    public ResultT<CurrencyDto> getByAbbr(String abbr) {
        Example example = new Example(Currency.class);
        example.createCriteria().andEqualTo("abbr", abbr);
        Currency currency = currencyMapper.selectOneByExample(example);
        if (currency != null) {
            return ResultT.success(currencyConverter.toCurrencyDto(currency));
        }
        return ResultT.fail();
    }

    /**
     * 根据siteCode 获取币种列表
     *
     * @param siteCode
     * @return
     */
    @Override
    public ResultT<CurrencyDto> getBySiteCode(String siteCode) {
        String redisKey = String.format(CacheConsts.SITE_CODE_CURRENCY, siteCode);
        CurrencyDto currencyDto = (CurrencyDto)cache.get(redisKey);
        if (currencyDto == null) {
            ResultT<SiteDto> siteResult = siteService.getBySiteCode(siteCode);
            if (siteResult.isSuccess()) {
                SiteDto site = siteResult.getData();
                Example example = new Example(Currency.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("abbr", site.getCurrencyAbbr());
                criteria.andEqualTo("status", 1);
                Currency currency = currencyMapper.selectOneByExample(example);
                if (currency != null) {
                    currencyDto = currencyConverter.toCurrencyDto(currency);
                    cache.set(redisKey, currencyDto);
                    return ResultT.success(currencyDto);
                }
            }
        } else {
            return ResultT.success(currencyDto);
        }

        return ResultT.fail();
    }

    @Override
    public ResultT<CurrencyDto> getById(int id) {
        Currency currency =  currencyMapper.selectByPrimaryKey(id);
        if (currency != null) {
            return ResultT.success(currencyConverter.toCurrencyDto(currency));
        }
        return ResultT.fail();
    }

    @Override
    public ResultT<ResultList<CurrencyDto>> list(int page, int limit) {
        PageHelper.startPage(page, limit, true);
        List<Currency> records = currencyMapper.selectAll();
        PageInfo<Currency> pageInfo = new PageInfo<>(records);
        ResultList<CurrencyDto> result = new ResultList<>();
        if (pageInfo.getTotal() > 0) {
            List<CurrencyDto> dtoRecords = currencyConverter.toCurrencyDtoList(pageInfo.getList());
            result.setRecords(dtoRecords);
        }
        result.setPageNum(page);
        result.setPageSize(limit);
        result.setRecordTotal(pageInfo.getTotal());
        return ResultT.success(result);
    }
}
