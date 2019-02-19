package net.realme.mall.oms.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.realme.framework.util.constant.CommonStatus;
import net.realme.framework.util.dto.ResultList;
import net.realme.mall.oms.admin.beantool.AdminRoleConverter;
import net.realme.mall.oms.admin.dao.AdminRoleMapper;
import net.realme.mall.oms.admin.dao.AdminUserRoleMapper;
import net.realme.mall.oms.admin.facade.AdminRoleService;
import net.realme.mall.oms.admin.model.AdminRole;
import net.realme.mall.oms.admin.model.AdminUser;
import net.realme.mall.oms.admin.model.AdminUserRole;
import net.realme.mall.oms.domain.admin.AdminRoleDto;
import net.realme.mall.oms.domain.admin.AdminUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.admin.impl
 *
 * @author 91000044
 * @date 2018/8/7 20:58
 */
@Service
@Transactional(value = "omsTransactionManager", rollbackFor = Exception.class)
public class AdminRoleServiceImpl implements AdminRoleService {

    @Autowired
    private AdminRoleConverter adminRoleConverter;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Autowired
    private AdminUserRoleMapper adminUserRoleMapper;

    @Override
    public AdminRoleDto getById(int roleId) {
        AdminRole adminRole = adminRoleMapper.selectByPrimaryKey(roleId);
        if (adminRole != null) {
            return adminRoleConverter.toAdminRoleDto(adminRole);
        }
        return null;
    }

    @Override
    public AdminRoleDto getByName(String name) {
        Example example = new Example(AdminRole.class);
        example.createCriteria().andEqualTo("name", name);
        AdminRole adminUser = adminRoleMapper.selectOneByExample(example);
        if (adminUser != null) {
            return adminRoleConverter.toAdminRoleDto(adminUser);
        }
        return null;
    }

    @Override
    public int add(AdminRoleDto adminRoleDto) {
        AdminRole adminRole = adminRoleConverter.fromAdminRoleDto(adminRoleDto);
        if (adminRoleMapper.insert(adminRole) > 0) {
            return adminRole.getId();
        }
        return 0;
    }

    @Override
    public int update(AdminRoleDto adminRoleDto) {
        if (adminRoleDto == null || adminRoleDto.getId() == null || adminRoleDto.getId() <= 0) {
            return 0;
        }
        int ret = adminRoleMapper.updateByPrimaryKeySelective(adminRoleConverter.fromAdminRoleDto(adminRoleDto));
        if (ret > 0 && CommonStatus.DISABLED == adminRoleDto.getStatus()) {
            Example example = new Example(AdminUserRole.class);
            example.createCriteria().andEqualTo("roleId", adminRoleDto.getId());
            adminUserRoleMapper.deleteByExample(example);
            //todo delete role's menus
//            adminRoleMenuMapper.deleteByRoleId(roleId);
        }
        return ret;
    }

    @Override
    public ResultList<AdminRoleDto> getRoles(int page, int limit) {
        ResultList<AdminRoleDto> result = new ResultList<>();
        PageHelper.startPage(page, limit, true);
        List<AdminRole> users = adminRoleMapper.selectAll();
        PageInfo<AdminRole> pageInfo = new PageInfo<>(users);
        if (pageInfo.getTotal() > 0) {
            List<AdminRoleDto> dtoRecords = adminRoleConverter.toAdminRoleDtoList(pageInfo.getList());
            result.setRecords(dtoRecords);
        }
        result.setPageNum(page);
        result.setPageSize(limit);
        result.setRecordTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public int assignRolesToUser(int userId, int... roleIds) {
        if (roleIds == null || roleIds.length == 0 || userId <= 0) {
            return 0;
        }
        AdminUserRole adminUserRole = null;
        Example example = new Example(AdminUserRole.class);
        example.createCriteria().andEqualTo("userId", userId);
        adminUserRoleMapper.deleteByExample(example);
        List<AdminUserRole> records = new ArrayList<>(20);
        for (int roleId: roleIds) {
            if (roleId == 0){
                continue;
            }
            adminUserRole = new AdminUserRole();
            adminUserRole.setUserId(userId);
            adminUserRole.setRoleId(roleId);
            adminUserRole.setCreatedAt(System.currentTimeMillis());
            records.add(adminUserRole);
        }
        return adminUserRoleMapper.batchInsert(records);
    }

    @Override
    public int removeAllRolesFromUser(int userId) {
        if (userId <= 0) {
            return 0;
        }
        Example example = new Example(AdminUserRole.class);
        example.createCriteria().andEqualTo("userId", userId);
        return adminUserRoleMapper.deleteByExample(example);
    }

}
