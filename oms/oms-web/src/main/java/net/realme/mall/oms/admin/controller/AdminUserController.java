
package net.realme.mall.oms.admin.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.realme.framework.util.dto.Result;
import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.text.RMTextUtil;
import net.realme.framework.util.text.RegexUtil;
import net.realme.framework.web.controller.BaseController;
import net.realme.mall.basics.third.facade.EmailService;
import net.realme.mall.oms.admin.facade.AdminRoleService;
import net.realme.mall.oms.admin.facade.AdminUserService;
import net.realme.mall.oms.config.security.AuthUserDetails;
import net.realme.mall.oms.domain.admin.AdminUserDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author 91000044
 */

@Api(tags = {"后台用户管理"})
@Validated
@RestController
@RequestMapping("/admin/user")
public class AdminUserController extends BaseController {

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AdminRoleService adminRoleService;

    @ApiOperation(value = "添加管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名", paramType = "form", required = true),
            @ApiImplicitParam(name = "email", value = "邮箱", paramType = "form", required = true),
            @ApiImplicitParam(name = "nickname", value = "昵称", paramType = "form", required = false),
            @ApiImplicitParam(name = "phone", value = "手机号", paramType = "form", required = false),
    })
    @PostMapping("/create")
    public Result add(@NotBlank String name,
                      @NotBlank @Email String email,
                      String phone,
                      String nickname) {
        if (StringUtils.isBlank(name)) {
            return errI18N("err.name.empty");
        }
        AdminUserDto adminUserDto = adminUserService.getByName(name);
        if (adminUserDto != null) {
            return errI18N("err.name.duplicated");
        }
        adminUserDto = new AdminUserDto();
        adminUserDto.setName(name);
        adminUserDto.setEmail(email);
        if (StringUtils.isNotBlank(phone)) {
            adminUserDto.setPhone(phone);
        }
        if (StringUtils.isNotBlank(nickname) && nickname.length() <= 100) {
            adminUserDto.setNickname(nickname);
        }
        String randomPass = RMTextUtil.getRandomString(8);
        adminUserDto.setPassword(bCryptPasswordEncoder.encode(randomPass));
        int ret = adminUserService.add(adminUserDto);
        if (ret > 0) {
            String emailContent = "Your ID: " + email + "\n Your password: " + randomPass;
            emailService.send("Your OMS Account have been created",  emailContent, email);
            return ok(ret);
        } else {
            return errI18N("err.name.duplicated");
        }
    }

    @ApiOperation(value = "更新管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", paramType = "form", required = true),
            @ApiImplicitParam(name = "email", value = "邮箱", paramType = "form", required = false),
            @ApiImplicitParam(name = "nickname", value = "昵称", paramType = "form", required = false),
            @ApiImplicitParam(name = "phone", value = "手机号", paramType = "form", required = false),
    })
    @PostMapping("/update")
    public Result update(@Min(1) int id,
                         @NotBlank @Email String email,
                         String nickname,
                         String phone) {
        if (id <= 0) {
            return errI18N("err.param.invalid");
        }
        AdminUserDto operatingUser = adminUserService.getById(id);
        if (operatingUser == null) {
            return errI18N("err.entity.not.found");
        }
        if (StringUtils.isNotBlank(phone)) {
            operatingUser.setPhone(phone);
        }
        if (StringUtils.isNotBlank(nickname) && nickname.length() <= 100) {
            operatingUser.setNickname(nickname);
        }
        if (RegexUtil.isEmail(email)) {
            operatingUser.setEmail(email);
        }
        operatingUser.setUpdatedAt(System.currentTimeMillis());
        int ret = adminUserService.update(operatingUser);
        if (ret > 0) {
            return ok();
        } else {
            return errI18N("err.update.admin.user");
        }
    }

    @ApiOperation(value = "超级管理员或有权限的管理重置指定用户的密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", paramType = "form", required = true),
    })
    @PostMapping("/resetPassword")
    public Result resetPassword(@Min(1) int id) {
        if (id <= 0) {
            return errI18N("err.param.invalid");
        }
        AdminUserDto adminUserDto = adminUserService.getById(id);
        if (adminUserDto == null) {
            return errI18N("err.entity.not.found");
        }
        String randomPass = RMTextUtil.getRandomString(8);
        adminUserDto.setPassword(bCryptPasswordEncoder.encode(randomPass));
        int ret = adminUserService.modifyPassword(adminUserDto);
        if (ret > 0) {
            String emailContent = "Your new password: " + randomPass;
            emailService.send("Your OMS password have been reset",  emailContent, adminUserDto.getEmail());
            return ok();
        } else {
            return errI18N("err.operation.fail");
        }
    }

    @ApiOperation(value = "自己修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "password", value = "原密码", paramType = "form", required = true),
            @ApiImplicitParam(name = "newPassword", value = "新密码", paramType = "form", required = true),
            @ApiImplicitParam(name = "newPasswordConfirm", value = "新密码确认", paramType = "form", required = true),
    })
    @PostMapping("/changePassword")
    public Result changePassword(@NotBlank String password, @NotBlank String newPassword,
                                 @NotBlank String newPasswordConfirm, Authentication authentication) {
        if (!newPassword.equals(newPasswordConfirm)) {
            return errI18N("err.pass.not.match");
        }
        AuthUserDetails currentUser = (AuthUserDetails) authentication.getPrincipal();
        if (!bCryptPasswordEncoder.matches(password, currentUser.getPassword())) {
            return errI18N("err.old.pass.not.match");
        }

        int ret = adminUserService.modifyPassword(currentUser.getId(), bCryptPasswordEncoder.encode(newPassword));
        if (ret > 0) {
            return ok();
        } else {
            return errI18N("err.operation.fail");
        }
    }

    @ApiOperation(value = "修改状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", paramType = "form", required = true),
            @ApiImplicitParam(name = "status", value = "状态", paramType = "form", required = true),
    })
    @PostMapping("/status")
    public Result status(@Min(1) int id, byte status) {
        if (id <= 0) {
            return errI18N("err.entity.not.found");
        }
        AdminUserDto operatingUser = adminUserService.getById(id);
        operatingUser.setStatus(status);
        operatingUser.setUpdatedAt(System.currentTimeMillis());
        int ret = adminUserService.update(operatingUser);
        if (ret > 0) {
            return ok();
        } else {
            return errI18N("err.status.admin.user");
        }
    }

    @ApiOperation(value = "查看详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", paramType = "query", required = true),
    })
    @GetMapping("/get")
    public Result get(@Min(1) int id) {
        if (id <= 0) {
            return errI18N("err.param.invalid");
        }
        AdminUserDto user = adminUserService.getById(id);
        if (user == null) {
            return errI18N("err.user.not.exists");
        }
        return ok(user);
    }

    @ApiOperation(value = "绑定角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "form", required = true),
            @ApiImplicitParam(name = "roleIds", value = "角色ID", paramType = "form"),
    })
    @PostMapping("/assignRoles")
    public Result assignRoles(@Min(1) int userId, String roleIds) {
        if (userId <= 0) {
            return errI18N("err.entity.not.found");
        }
        if (StringUtils.isBlank(roleIds)) {
            int ret = adminRoleService.removeAllRolesFromUser(userId);
            if (ret > 0) {
            }
            return ok();
        }
        AdminUserDto user = adminUserService.getById(userId);
        if (user == null) {
            return errI18N("err.user.not.exists");
        }
        int[] roleIdArr = Arrays.stream(roleIds.split(",")).mapToInt(Integer::parseInt).toArray();
        int ret = adminRoleService.assignRolesToUser(userId, roleIdArr);
        if (ret > 0) {
            return ok();
        } else {
            return errI18N("err.operation.fail");
        }
    }

    @ApiOperation(value = "用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "limit", value = "页大小", paramType = "query", defaultValue = "20"),
    })
    @GetMapping("/list")
    public Result list(@Min(1) int page,
                       @Max(1000) int limit) {
        ResultList<AdminUserDto> users = adminUserService.getUserList(page, limit);
        return ok(users);
    }
}


