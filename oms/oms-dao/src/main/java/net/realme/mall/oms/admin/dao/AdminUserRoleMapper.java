package net.realme.mall.oms.admin.dao;

import net.realme.mall.oms.admin.model.AdminUserRole;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AdminUserRoleMapper extends Mapper<AdminUserRole> {

    int batchInsert(List<AdminUserRole> records);
}