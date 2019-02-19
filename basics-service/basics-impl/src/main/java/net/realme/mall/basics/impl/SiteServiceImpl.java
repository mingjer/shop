package net.realme.mall.basics.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.realme.framework.cache.redis.RedisCache;
import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.mall.basics.beantool.SiteConverter;
import net.realme.mall.basics.consts.CacheConsts;
import net.realme.mall.basics.dao.SiteMapper;
import net.realme.mall.basics.dto.CurrencyDto;
import net.realme.mall.basics.dto.SiteDto;
import net.realme.mall.basics.facade.SiteService;
import net.realme.mall.basics.model.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


@Service
public class SiteServiceImpl implements SiteService{

    @Autowired
    private SiteMapper siteMapper;

    @Autowired
    private SiteConverter siteConverter;

    @Autowired
    private RedisCache cache;

    @Override
    public ResultT<Integer> addSite(SiteDto siteDto) {
        Site site = siteConverter.fromSiteDto(siteDto);
        if (siteMapper.insert(site)>0) {
            return ResultT.success(1);
        }
        return ResultT.fail();
    }

    @Override
    public ResultT<Integer> updateSite(SiteDto siteDao) {
        Site site = siteConverter.fromSiteDto(siteDao);
        if (siteMapper.updateByPrimaryKeySelective(site)>0) {
            return ResultT.success(1);
        }
        return ResultT.fail();
    }

    @Override
    public ResultT<SiteDto> getById(int id) {
        Site site = siteMapper.selectByPrimaryKey(id);
        if (site != null) {
            return ResultT.success(siteConverter.toSitDto(site));
        }
        return ResultT.fail();
    }

    /**
     * 根据siteCode获取
     *
     * @param siteCode
     * @return
     */
    @Override
    public ResultT<SiteDto> getBySiteCode(String siteCode) {
        String redisKey = String.format(CacheConsts.SITE_CODE_SITE, siteCode);
        SiteDto siteDto = (SiteDto)cache.get(redisKey);
        if (siteDto != null) {
            return ResultT.success(siteDto);
        } else {
            Example example = new Example(Site.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("siteCode", siteCode);
            criteria.andEqualTo("status", 1);
            List<Site> records = siteMapper.selectByExample(example);
            if (!CollectionUtils.isEmpty(records)) {
                Site site = records.get(0);
                siteDto = siteConverter.toSitDto(site);
                cache.set(redisKey, siteDto);
                return ResultT.success(siteDto);
            }
        }
        return ResultT.fail();
    }

    @Override
    public ResultT<ResultList<SiteDto>> list(int page, int limit) {
        PageHelper.startPage(page, limit, true);
        // 查询状态为未删除的信息
        Example example = new Example(Site.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", 1);
        List<Site> records = siteMapper.selectByExample(example);
        PageInfo<Site> pageInfo = new PageInfo<>(records);
        ResultList<SiteDto> result = new ResultList<>();
        if (pageInfo.getTotal() > 0) {
            List<SiteDto> dtoRecords = siteConverter.toSiteDtoList(pageInfo.getList());
            result.setRecords(dtoRecords);
        }
        result.setPageNum(page);
        result.setPageSize(limit);
        result.setRecordTotal(pageInfo.getTotal());
        return ResultT.success(result);
    }

    @Override
    public ResultT<ResultList<SiteDto>> listAll(int page, int limit) {
        PageHelper.startPage(page, limit, true);
        List<Site> records = siteMapper.selectAll();
        PageInfo<Site> pageInfo = new PageInfo<>(records);
        ResultList<SiteDto> result = new ResultList<>();
        if (pageInfo.getTotal() > 0) {
            List<SiteDto> dtoRecords = siteConverter.toSiteDtoList(pageInfo.getList());
            result.setRecords(dtoRecords);
        }
        result.setPageNum(page);
        result.setPageSize(limit);
        result.setRecordTotal(pageInfo.getTotal());
        return ResultT.success(result);
    }

    @Override
    public ResultT<Integer> updateSiteStatus(SiteDto siteDao) {
        Site site = siteConverter.fromSiteDto(siteDao);
        if (siteMapper.updateByPrimaryKeySelective(site) > 0) {
            return ResultT.success(1);
        }
        return ResultT.fail();
    }
}
