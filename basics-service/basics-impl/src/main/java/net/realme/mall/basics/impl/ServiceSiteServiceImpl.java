package net.realme.mall.basics.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.realme.framework.cache.redis.RedisCache;
import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.mall.basics.beantool.ServiceSiteConverter;
import net.realme.mall.basics.common.consts.ServiceSiteConst;
import net.realme.mall.basics.dao.ServiceSiteMapper;
import net.realme.mall.basics.dto.ServiceSiteDto;
import net.realme.mall.basics.facade.ServiceSiteService;
import net.realme.mall.basics.model.ServiceSite;
import tk.mybatis.mapper.entity.Example;

@Service
public class ServiceSiteServiceImpl implements ServiceSiteService {

	private static final String SERVICE_SITE_LITT_CACHE_KEY = "SERVICE_SITE_LIST_CACHE_KEY_";

	@Autowired
	private ServiceSiteMapper serviceSiteMapper;

	@Autowired
	private ServiceSiteConverter serviceSiteConverter;

	@Autowired
	private RedisCache<List<ServiceSiteDto>> redisCache;

	@Override
	public ResultT<ResultList<ServiceSiteDto>> list(ServiceSiteDto serviceSiteDto, int page, int limit) {
		PageHelper.startPage(page, limit, true);
		Example example = new Example(ServiceSite.class);
		Example.Criteria criteria = example.createCriteria();
		if (serviceSiteDto.getId() != null) {
			criteria.andEqualTo("id", serviceSiteDto.getId());
		}
		if (serviceSiteDto.getStatus() != null) {
			criteria.andEqualTo("status", serviceSiteDto.getStatus());
		}
		if (serviceSiteDto.getAssessStatus() != null) {
			criteria.andEqualTo("assessStatus", serviceSiteDto.getAssessStatus());
		}
		if (serviceSiteDto.getType() != null) {
			criteria.andEqualTo("type", serviceSiteDto.getType());
		}
		if (serviceSiteDto.getCountryId() != null) {
			criteria.andEqualTo("countryId", serviceSiteDto.getCountryId());
		}
		if (serviceSiteDto.getCountryName() != null) {
			criteria.andEqualTo("countryName", serviceSiteDto.getCountryName());
		}
		if (serviceSiteDto.getProvinceId() != null) {
			criteria.andEqualTo("provinceId", serviceSiteDto.getProvinceId());
		}
		if (serviceSiteDto.getProvinceName() != null) {
			criteria.andEqualTo("provinceName", serviceSiteDto.getProvinceName());
		}
		if (serviceSiteDto.getCityId() != null) {
			criteria.andEqualTo("cityId", serviceSiteDto.getCityId());
		}
		if (serviceSiteDto.getCityName() != null) {
			criteria.andEqualTo("cityName", serviceSiteDto.getCityName());
		}
		example.setOrderByClause("created_at DESC,id DESC");
		List<ServiceSite> serviceSiteList = serviceSiteMapper.selectByExample(example);
		PageInfo<ServiceSite> pageInfo = new PageInfo<>(serviceSiteList);

		ResultList<ServiceSiteDto> result = new ResultList<>();

		if (pageInfo.getTotal() > 0) {
			List<ServiceSiteDto> dtoRecords = serviceSiteConverter.toServiceSiteDtoList(serviceSiteList);
			result.setRecords(dtoRecords);
		}
		result.setPageNum(page);
		result.setPageSize(limit);
		result.setRecordTotal(pageInfo.getTotal());
		return ResultT.success(result);
	}

	@Override
	public ResultT<ResultList<ServiceSiteDto>> listAll() {
		List<ServiceSite> serviceSiteList = serviceSiteMapper.selectAll();
		ResultList<ServiceSiteDto> result = new ResultList<>();
		if (!serviceSiteList.isEmpty()) {
			List<ServiceSiteDto> dtoRecords = serviceSiteConverter.toServiceSiteDtoList(serviceSiteList);
			result.setRecords(dtoRecords);
		}
		result.setPageNum(1);
		result.setPageSize(serviceSiteList.size());
		result.setRecordTotal(serviceSiteList.size());
		return ResultT.success(result);
	}

	@Override
	public ResultT<ServiceSiteDto> getServiceSiteById(Integer serviceSiteId) {
		ServiceSite serviceSite = serviceSiteMapper.selectByPrimaryKey(serviceSiteId);
		if (serviceSite != null) {
			return ResultT.success(serviceSiteConverter.toServiceSiteDto(serviceSite));
		}
		return ResultT.fail();
	}

	@Override
	public ResultT<Integer> addServiceSite(ServiceSiteDto serviceSiteDto) {
		ServiceSite serviceSite = serviceSiteConverter.fromServiceSiteDto(serviceSiteDto);
		int ret = serviceSiteMapper.insert(serviceSite);
		if (ret > 0) {
			return ResultT.success(ret);
		}
		return ResultT.fail();
	}

	@Override
	public ResultT<Integer> updateServiceSite(ServiceSiteDto serviceSiteDto) {
		ServiceSite serviceSite = serviceSiteConverter.fromServiceSiteDto(serviceSiteDto);
		int ret = serviceSiteMapper.updateByPrimaryKey(serviceSite);
		if (ret > 0) {
			// 更改缓存
			redisCache.delete(SERVICE_SITE_LITT_CACHE_KEY + serviceSiteDto.getCountryId() + "_"
					+ serviceSiteDto.getProvinceName() + "_" + serviceSiteDto.getCityName());
			return ResultT.success(1);
		}
		return ResultT.fail();
	}

	@Override
	public ResultT<Integer> batchAddServiceSite(List<ServiceSiteDto> serviceSiteDtos) {
		List<ServiceSite> serviceSiteList = serviceSiteConverter.fromServiceSiteDtoList(serviceSiteDtos);
		if (serviceSiteMapper.batchInsert(serviceSiteList) > 0) {
			return ResultT.success(1);
		}
		return ResultT.fail();
	}

	@Override
	public ResultT<ResultList<ServiceSiteDto>> list(String countryId, String provinceName, String cityName) {
		ResultList<ServiceSiteDto> result = new ResultList<>();
		result.setPageNum(1);
		result.setPageSize(0);
		result.setRecordTotal(0);
		List<ServiceSiteDto> dtoRecords = this.getServiceSiteByProvinceAndCity(countryId, provinceName, cityName);
		if (dtoRecords != null) {
			result.setRecords(dtoRecords);
			result.setPageSize(dtoRecords.size());
			result.setRecordTotal(dtoRecords.size());
		}
		return ResultT.success(result);
	}

	private List<ServiceSiteDto> getServiceSiteByProvinceAndCity(String countryId, String provinceName,
			String cityName) {
		List<ServiceSiteDto> dtoRecords = redisCache
				.get(SERVICE_SITE_LITT_CACHE_KEY + countryId + "_" + provinceName + "_" + cityName);
		if (dtoRecords != null && !dtoRecords.isEmpty()) {
			return dtoRecords;
		}
		Example example = new Example(ServiceSite.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("countryId", countryId);
		criteria.andEqualTo("provinceName", provinceName);
		criteria.andEqualTo("cityName", cityName);
		criteria.andEqualTo("status", ServiceSiteConst.SiteStatus.NORMAL.getValue());
		List<ServiceSite> serviceSiteList = serviceSiteMapper.selectByExample(example);
		if (serviceSiteList.size() > 0) {
			dtoRecords = serviceSiteConverter.toServiceSiteDtoList(serviceSiteList);
			redisCache.set(SERVICE_SITE_LITT_CACHE_KEY + countryId + "_" + provinceName + "_" + cityName, dtoRecords);
		}
		return dtoRecords;
	}

}
