package net.realme.mall.user.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.mall.user.beantool.UserAddressConverter;
import net.realme.mall.user.dao.UserAddressMapper;
import net.realme.mall.user.domain.UserAddressDto;
import net.realme.mall.user.facade.UserAddressService;
import net.realme.mall.user.model.UserAddress;
import tk.mybatis.mapper.entity.Example;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.user.impl
 *
 * @author 91000044
 * @date 2018/8/28 20:29
 */
// @Service
public class UserAddressServiceImpl implements UserAddressService {

	@Value("${user.address.max}")
	private Integer maxUserAddress;

	@Autowired
	private UserAddressMapper userAddressMapper;

	@Autowired
	private UserAddressConverter userAddressConverter;

	@Override
	public ResultT<String> add(UserAddressDto userAddressDto) {
		Example countExample = new Example(UserAddress.class);
		Example.Criteria countCriteria = countExample.createCriteria();
		countCriteria.andEqualTo("ssoid", userAddressDto.getSsoid());
		int count = userAddressMapper.selectCountByExample(countExample);
		// 限制每个用户设置的最大收货地址数量
		if (count + 1 > maxUserAddress) {
			return ResultT.fail("user.address.max");
		}

		Example example = new Example(UserAddress.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("ssoid", userAddressDto.getSsoid());
		criteria.andEqualTo("isDefault", 1);
		UserAddress userDefaultAddress = userAddressMapper.selectOneByExample(example);

		UserAddress userAddress = userAddressConverter.fromUserAddressDto(userAddressDto);
		int ret = userAddressMapper.insert(userAddress);
		if (ret < 1) {
			return ResultT.fail();
		}
		if (userDefaultAddress != null) {
			if ("1".equals(String.valueOf(userAddressDto.getIsDefault()))) {
				// 添加时默认地址情况时，更改以前的收货地址为非默认
				userDefaultAddress.setIsDefault(Byte.parseByte("0"));
				Example updateExample = new Example(UserAddress.class);
				Example.Criteria updateCriteria = updateExample.createCriteria();
				updateCriteria.andEqualTo("ssoid", userDefaultAddress.getSsoid());
				updateCriteria.andEqualTo("id", userDefaultAddress.getId());
				userAddressMapper.updateByExample(userDefaultAddress, updateExample);
			}
		}
		return ResultT.success(userAddress.getId().toString());
	}

	@Override
	public ResultT<UserAddressDto> getDefault(long ssoid) {
		Example example = new Example(UserAddress.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("ssoid", ssoid);
		criteria.andEqualTo("isDefault", 1);
		UserAddress userAddress = userAddressMapper.selectOneByExample(example);
		if (userAddress != null) {
			return ResultT.success(userAddressConverter.toUserAddressDto(userAddress));
		}
		return ResultT.fail();
	}

	@Override
	public ResultT<UserAddressDto> getById(long ssoid, long userAddressId) {
		UserAddress userAddress = userAddressMapper.selectByPrimaryKey(userAddressId);
		if (userAddress != null) {
			return ResultT.success(userAddressConverter.toUserAddressDto(userAddress));
		}
		return ResultT.fail();
	}

	@Override
	public ResultT<ResultList<UserAddressDto>> listAll(long ssoid) {
		Example example = new Example(UserAddress.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("ssoid", ssoid);
		List<UserAddress> records = userAddressMapper.selectByExample(example);
		ResultList<UserAddressDto> result = new ResultList<>();
		if (!records.isEmpty()) {
			List<UserAddressDto> dtoRecords = userAddressConverter.toUserAddressDtoList(records);
			result.setRecords(dtoRecords);
		}
		return ResultT.success(result);
	}

	@Override
	public ResultT<Integer> update(UserAddressDto userAddressDto) {
		Example defaultExample = new Example(UserAddress.class);
		Example.Criteria defaultCriteria = defaultExample.createCriteria();
		defaultCriteria.andEqualTo("ssoid", userAddressDto.getSsoid());
		defaultCriteria.andEqualTo("isDefault", 1);
		UserAddress userDefaultAddress = userAddressMapper.selectOneByExample(defaultExample);

		Example example = new Example(UserAddress.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("ssoid", userAddressDto.getSsoid());
		criteria.andEqualTo("id", userAddressDto.getId());
		UserAddress userAddress = userAddressConverter.fromUserAddressDto(userAddressDto);
		int ret = userAddressMapper.updateByExample(userAddress, example);
		if (ret < 1) {
			return ResultT.fail();
		}
		if (userDefaultAddress != null) {
			if ("1".equals(String.valueOf(userAddressDto.getIsDefault()))) {
				// 设置默认地址时，已有默认地址，则更改为非默认
				if (!userDefaultAddress.getId().equals(userAddressDto.getId())) {
					userDefaultAddress.setIsDefault(Byte.parseByte("0"));
					Example updateExample = new Example(UserAddress.class);
					Example.Criteria updateCriteria = updateExample.createCriteria();
					updateCriteria.andEqualTo("ssoid", userDefaultAddress.getSsoid());
					updateCriteria.andEqualTo("id", userDefaultAddress.getId());
					userAddressMapper.updateByExample(userDefaultAddress, updateExample);
				}
			}
		}
		return ResultT.success(ret);
	}

	@Override
	public ResultT<Integer> deleteById(long ssoid, long userAddressId) {
		UserAddress userAddress = userAddressMapper.selectByPrimaryKey(userAddressId);
		if (userAddress == null) {
			return ResultT.fail();
		}
		if (!userAddress.getSsoid().equals(ssoid)) {
			return ResultT.fail("user.adress.update.not.permission");
		}
		int ret = userAddressMapper.deleteByPrimaryKey(userAddressId);
		if (ret > 0) {
			return ResultT.success(ret);
		}
		return ResultT.fail();
	}

	@Override
	public ResultT<Integer> setDefault(UserAddressDto userAddressDto) {
		UserAddress dbUserAddress = userAddressMapper.selectByPrimaryKey(userAddressDto.getId());
		if (dbUserAddress == null) {
			return ResultT.fail();
		}
		if (!dbUserAddress.getSsoid().equals(userAddressDto.getSsoid())) {
			return ResultT.fail("user.adress.update.not.permission");
		}
		dbUserAddress.setIsDefault(userAddressDto.getIsDefault());
		dbUserAddress.setUpdatedAt(new Date().getTime());

		Example defaultExample = new Example(UserAddress.class);
		Example.Criteria defaultCriteria = defaultExample.createCriteria();
		defaultCriteria.andEqualTo("ssoid", userAddressDto.getSsoid());
		defaultCriteria.andEqualTo("isDefault", 1);
		UserAddress userDefaultAddress = userAddressMapper.selectOneByExample(defaultExample);

		Example example = new Example(UserAddress.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("ssoid", userAddressDto.getSsoid());
		criteria.andEqualTo("id", userAddressDto.getId());
		int ret = userAddressMapper.updateByExample(dbUserAddress, example);
		if (ret < 1) {
			return ResultT.fail();
		}
		if (userDefaultAddress != null) {
			// 设置默认地址时，才检查是否相同地址；否则不处理，isDefault=0的情况排除
			if ("1".equals(String.valueOf(dbUserAddress.getIsDefault()))) {
				// 设置默认地址时，已有默认地址，则更改为非默认
				if (!userDefaultAddress.getId().equals(dbUserAddress.getId())) {
					userDefaultAddress.setIsDefault(Byte.parseByte("0"));
					Example updateExample = new Example(UserAddress.class);
					Example.Criteria updateCriteria = updateExample.createCriteria();
					updateCriteria.andEqualTo("ssoid", userDefaultAddress.getSsoid());
					updateCriteria.andEqualTo("id", userDefaultAddress.getId());
					userAddressMapper.updateByExample(userDefaultAddress, updateExample);
				}
			}
		}
		return ResultT.success(ret);
	}
}
