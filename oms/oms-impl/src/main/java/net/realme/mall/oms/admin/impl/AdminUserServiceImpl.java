package net.realme.mall.oms.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.realme.framework.util.constant.CommonStatus;
import net.realme.framework.util.dto.ResultList;
import net.realme.mall.oms.admin.beantool.AdminUserConverter;
import net.realme.mall.oms.admin.dao.AdminUserMapper;
import net.realme.mall.oms.admin.facade.AdminUserService;
import net.realme.mall.oms.admin.model.AdminUser;
import net.realme.mall.oms.domain.admin.AdminUserDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.admin.impl
 *
 * @author 91000044
 * @date 2018/8/4 18:32
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserConverter adminUserConverter;

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public int add(AdminUserDto adminUserDto) {
        AdminUser adminUser = adminUserConverter.fromAdminUserDto(adminUserDto);
        adminUser.setStatus(CommonStatus.ENABLED);
        long now = System.currentTimeMillis();
        if (adminUser.getCreatedAt() == null) {
            adminUser.setCreatedAt(now);
        }
        if (adminUser.getUpdatedAt() == null) {
            adminUser.setUpdatedAt(now);
        }
        if(adminUserMapper.insert(adminUser) > 0) {
            return adminUser.getId();
        }
        return 0;
    }

    @Override
    public int update(AdminUserDto adminUserDto) {
        AdminUser adminUser = adminUserConverter.fromAdminUserDto(adminUserDto);
        adminUser.setPassword(null);
        return adminUserMapper.updateByPrimaryKeySelective(adminUser);
    }

    @Override
    public int modifyPassword(AdminUserDto adminUserDto) {
        AdminUser adminUser = adminUserConverter.fromAdminUserDto(adminUserDto);
        if (adminUser != null) {
            return adminUserMapper.updateByPrimaryKeySelective(adminUser);
        }
        return 0;
    }

    @Override
    public int modifyPassword(int userId, String newEncodedPassword) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(userId);
        if (adminUser != null) {
            adminUser.setPassword(newEncodedPassword);
            return adminUserMapper.updateByPrimaryKeySelective(adminUser);
        }
        return 0;
    }

    @Override
    public AdminUserDto getById(int userId) {
        AdminUser adminUser = adminUserMapper.selectByPkWithRoles(userId);
        if (adminUser != null) {
             return adminUserConverter.toAdminUserDto(adminUser);
        }
        return null;
    }

    @Override
    public AdminUserDto getByName(String name) {
        AdminUser adminUser = adminUserMapper.selectByNameWithRoles(name);
        if (adminUser != null) {
            return adminUserConverter.toAdminUserDto(adminUser);
        }
        return null;
    }

    @Override
    public void loginFail(String name) {
        if (StringUtils.isBlank(name)) {
            return;
        }
        Example example = new Example(AdminUser.class);
        example.createCriteria().andEqualTo("name", name);
        AdminUser adminUser = adminUserMapper.selectOneByExample(example);
        if (adminUser == null) {
            return;
        }
        adminUser.setLastLoginAt(System.currentTimeMillis());
        adminUser.setLoginFailTimes((adminUser.getLoginFailTimes() == null ? 0 : adminUser.getLoginFailTimes()) + 1);
        adminUserMapper.updateByPrimaryKey(adminUser);
    }

    @Override
    public void loginSuccess(String name) {
        if (StringUtils.isBlank(name)) {
            return;
        }
        Example example = new Example(AdminUser.class);
        example.createCriteria().andEqualTo("name", name);
        AdminUser adminUser = adminUserMapper.selectOneByExample(example);
        if (adminUser == null) {
            return;
        }
        adminUser.setLastLoginAt(System.currentTimeMillis());
        adminUser.setLoginFailTimes(0);
        adminUserMapper.updateByPrimaryKey(adminUser);
    }

    @Override
    public ResultList<AdminUserDto> getUserList(int page, int limit) {
        ResultList<AdminUserDto> result = new ResultList<>();
        PageHelper.startPage(page, limit, true);
        List<AdminUser> users = adminUserMapper.selectAllWithRoles();
        PageInfo<AdminUser> pageInfo = new PageInfo<>(users);
        if (pageInfo.getTotal() > 0) {
            List<AdminUserDto> dtoRecords = adminUserConverter.toAdminUserDtoList(pageInfo.getList());
            result.setRecords(dtoRecords);
        }
        result.setPageNum(page);
        result.setPageSize(limit);
        result.setRecordTotal(pageInfo.getTotal());
        return result;
    }

}
