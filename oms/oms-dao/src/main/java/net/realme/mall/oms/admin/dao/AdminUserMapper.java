package net.realme.mall.oms.admin.dao;

import net.realme.mall.oms.admin.model.AdminUser;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AdminUserMapper extends Mapper<AdminUser> {

    AdminUser loadUserByUsername(String loginName);

    AdminUser selectByPkWithRoles(Integer id);

    AdminUser selectByNameWithRoles(String name);

    List<AdminUser> selectAllWithRoles();
}