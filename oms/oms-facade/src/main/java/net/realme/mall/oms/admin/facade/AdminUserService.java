package net.realme.mall.oms.admin.facade;

import net.realme.framework.util.dto.ResultList;
import net.realme.mall.oms.domain.admin.AdminUserDto;

import java.util.List;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.admin.facade
 *
 * @author 91000044
 * @date 2018/8/4 16:50
 */

public interface AdminUserService {

    /**
     * 添加用户
     * @param adminUserDto
     * @return
     */
    int add(AdminUserDto adminUserDto);

    /**
     * 更新用户
     * @param adminUserDto
     * @return
     */
    int update(AdminUserDto adminUserDto);

    /**
     * 修改密码
     *
     * @param adminUserDto
     * @return
     */
    int modifyPassword(AdminUserDto adminUserDto);
    int modifyPassword(int userId, String newEncodedPassword);

    /**
     * 用户列表
     * @param page
     * @param limit
     * @return
     */
    ResultList<AdminUserDto> getUserList(int page, int limit);

    /**
     * 按ID查询用户
     * @param userId
     * @return
     */
    AdminUserDto getById(int userId);


    /**
     * 按名称查询用户
     * @param name
     * @return
     */
    AdminUserDto getByName(String name);

    /**
     * 登录失败更新状态
     * @param name
     */
    void loginFail(String name);

    /**
     * 登录成功更新状态
     * @param name
     */
    void loginSuccess(String name);

}
