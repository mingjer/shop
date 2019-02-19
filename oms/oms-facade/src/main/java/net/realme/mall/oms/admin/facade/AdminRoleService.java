package net.realme.mall.oms.admin.facade;

import net.realme.framework.util.dto.ResultList;
import net.realme.mall.oms.domain.admin.AdminRoleDto;

import java.util.List;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.admin.facade
 *
 * @author 91000044
 * @date 2018/8/4 17:48
 */
public interface AdminRoleService {

    /**
     * 按角色Id查询
     * @param roleId
     * @return
     */
    AdminRoleDto getById(int roleId);

    /**
     * 按角色名称查询
     * @param name
     * @return
     */
    AdminRoleDto getByName(String name);
    /**
     * 添加角色
     * @param adminRoleDto
     * @return
     */
    int add(AdminRoleDto adminRoleDto);

    /**
     * 更新角色
     * @param adminRoleDto
     * @return
     */
    int update(AdminRoleDto adminRoleDto);

    /**
     * 角色列表
     * @param page
     * @param limit
     * @return
     */
    ResultList<AdminRoleDto> getRoles(int page, int limit);

    /**
     * 给用户绑定角色
     * @param userId
     * @param roleIds
     * @return
     */
    int assignRolesToUser(int userId, int... roleIds);

    /**
     * 解绑用户所有角色
     * @param userId
     * @return
     */
    int removeAllRolesFromUser(int userId);
}
