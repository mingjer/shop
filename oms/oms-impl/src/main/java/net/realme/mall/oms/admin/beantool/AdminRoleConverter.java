package net.realme.mall.oms.admin.beantool;

import net.realme.mall.oms.admin.model.AdminRole;
import net.realme.mall.oms.domain.admin.AdminRoleDto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.admin.beantool
 *
 * @author 91000044
 * @date 2018/8/4 18:34
 */
@Mapper(componentModel = "spring")
public interface AdminRoleConverter {

    AdminRoleDto toAdminRoleDto(AdminRole adminUser);

    List<AdminRoleDto> toAdminRoleDtoList(List<AdminRole> adminUser);

    AdminRole fromAdminRoleDto(AdminRoleDto adminUserDto);
}
