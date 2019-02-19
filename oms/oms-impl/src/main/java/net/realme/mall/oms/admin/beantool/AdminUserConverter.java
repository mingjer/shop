package net.realme.mall.oms.admin.beantool;

import net.realme.mall.oms.admin.model.AdminUser;
import net.realme.mall.oms.domain.admin.AdminUserDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
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
@DecoratedWith(AdminUserConverterDecorator.class)
public interface AdminUserConverter {

    AdminUserDto toAdminUserDto(AdminUser adminUser);

    List<AdminUserDto> toAdminUserDtoList(List<AdminUser> adminUser);

    AdminUser fromAdminUserDto(AdminUserDto adminUserDto);
}

abstract class AdminUserConverterDecorator implements AdminUserConverter {

    @Autowired
    @Qualifier("delegate")
    private AdminUserConverter delegate;


    @Override
    public AdminUserDto toAdminUserDto(AdminUser adminUser) {
        AdminUserDto adminUserDto = delegate.toAdminUserDto(adminUser);
        if (adminUserDto.getRoles() != null && !adminUserDto.getRoles().isEmpty()) {
            String[] roleCodes = new String[adminUserDto.getRoles().size()];
            for (int i=0;i<adminUserDto.getRoles().size();i++) {
                roleCodes[i] = adminUserDto.getRoles().get(i).getCode();
            }
            adminUserDto.setRoleCodes(roleCodes);
        }
        return adminUserDto;
    }

    @Override
    public List<AdminUserDto> toAdminUserDtoList(List<AdminUser> users) {
        if ( users == null ) {
            return null;
        }

        List<AdminUserDto> list1 = new ArrayList<>( users.size() );
        for ( AdminUser adminUser : users ) {
            list1.add( this.toAdminUserDto( adminUser ) );
        }

        return list1;
    }

    @Override
    public AdminUser fromAdminUserDto(AdminUserDto adminUserDto) {
        return delegate.fromAdminUserDto(adminUserDto);
    }
}