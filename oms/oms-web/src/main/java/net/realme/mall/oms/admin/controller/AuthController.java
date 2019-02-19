
package net.realme.mall.oms.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.realme.framework.util.dto.Result;
import net.realme.framework.util.dto.ResultUtil;
import net.realme.framework.web.controller.BaseController;
import net.realme.mall.oms.admin.facade.AdminUserService;
import net.realme.mall.oms.config.security.AuthUserDetails;
import net.realme.mall.oms.domain.admin.AdminUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;


/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.admin.controller
 *
 * @author 91000044
 * @date 2018/8/7 11:14
 */

@Api(tags = {"认证授权"})
@Validated
@RestController
public class AuthController extends BaseController {

    @Autowired
    private AdminUserService adminUserService;

    @ApiOperation(value = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", paramType = "form", required = true),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "form", required = true),
    })
    @PostMapping("/login")
    public Result login(@NotBlank String username,
                        @NotBlank String password, HttpServletRequest request) {
        Authentication currentUser = (Authentication) request.getUserPrincipal();
        if (currentUser != null && currentUser.isAuthenticated()) {
            return ok();
        }
        try {
            request.login(username, password);
            return ok(request.getSession().getId());
        } catch (ServletException authenticationFailed) {
            return err(authenticationFailed.getMessage());
        }
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public Result logout(HttpServletRequest request) {
        try {
            request.logout();
            return ok();
        } catch (ServletException authenticationFailed) {
            return err(authenticationFailed.getMessage());
        }
    }

    @ApiOperation(value = "用户详细信息")
    @GetMapping("/userInfo")
    public Result userInfo(Authentication authentication) {
        AuthUserDetails currentUser = (AuthUserDetails) authentication.getPrincipal();
        if (currentUser == null) {
            return ResultUtil.unauthorized("");
        }
        int id = currentUser.getId();
        AdminUserDto adminUserDto = adminUserService.getById(id);
        HashMap<String, Object> userInfo = new HashMap<>(10);
        if (adminUserDto != null) {
            userInfo.put("user", adminUserDto);
        }
        return ok(userInfo);
    }
}

