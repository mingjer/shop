
package net.realme.mall.oms.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.realme.framework.util.constant.CommonStatus;
import net.realme.framework.util.dto.Result;
import net.realme.framework.util.dto.ResultList;
import net.realme.framework.web.controller.BaseController;
import net.realme.mall.oms.admin.facade.AdminRoleService;
import net.realme.mall.oms.domain.admin.AdminRoleDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


/**
 * @author 80124191
 */

@Api(tags = "后台角色管理")
@Validated
@RequestMapping("/admin/role")
@RestController
public class AdminRoleController extends BaseController {

    @Autowired
    private AdminRoleService adminRoleService;

    @ApiOperation(value = "添加角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "角色名称", paramType = "form", required = true),
            @ApiImplicitParam(name = "code", value = "角色编码(英文), 如admin", paramType = "form", required = true),
            @ApiImplicitParam(name = "description", value = "角色描述", paramType = "form", required = false)
    })
    @PostMapping("/create")
    public Result addRole(@NotBlank String name, @NotBlank String code, String description) {
        if (adminRoleService.getByName(name) != null) {
            return errI18N("err.role.name.duplicated");
        }
        AdminRoleDto adminRoleDto = new AdminRoleDto();
        adminRoleDto.setName(name);
        adminRoleDto.setCode(code);
        adminRoleDto.setStatus(CommonStatus.ENABLED);
        if (StringUtils.isNotBlank(description)) {
            adminRoleDto.setDescription(description);
        }
        adminRoleDto.setCreatedAt(System.currentTimeMillis());
        int ret = adminRoleService.add(adminRoleDto);
        if (ret > 0) {
            return ok(ret);
        }else {
            return errI18N("err.operation.fail");
        }
    }

    @ApiOperation(value = "查询角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID", paramType = "query", required = true)
    })
    @GetMapping("/get")
    public Result getRole(@Min(1) int id) {
        AdminRoleDto adminRoleDto = adminRoleService.getById(id);
        if (adminRoleDto != null) {
            return ok(adminRoleDto);
        }else {
            return errI18N("err.role.not.exists");
        }
    }

    @ApiOperation(value = "修改角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID", paramType = "form", required = true),
            @ApiImplicitParam(name = "code", value = "角色编码", paramType = "form", required = true),
            @ApiImplicitParam(name = "name", value = "角色名称", paramType = "form", required = true),
            @ApiImplicitParam(name = "description", value = "角色描述", paramType = "form", required = false)
    })
    @PostMapping("/update")
    public Result updateRole(@Min(1) int id, @NotBlank String name, @NotBlank String code, String description) {
        AdminRoleDto adminRoleDto = adminRoleService.getById(id);
        if (adminRoleDto == null) {
            return errI18N("err.role.not.exists");
        }
        if (StringUtils.isNotBlank(name)) {
            adminRoleDto.setName(name);
        }
        if (StringUtils.isNotBlank(code)) {
            adminRoleDto.setCode(code);
        }
        if (StringUtils.isNotBlank(description)) {
            adminRoleDto.setDescription(description);
        }
        adminRoleDto.setUpdatedAt(System.currentTimeMillis());
        int ret = adminRoleService.update(adminRoleDto);
        if (ret > 0) {
            return ok();
        }else {
            return errI18N("err.operation.fail");
        }
    }

    @ApiOperation(value = "删除角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID", paramType = "form", required = true),
            @ApiImplicitParam(name = "status", value = "状态", paramType = "form", required = true)
    })
    @PostMapping("/status")
    public Result status(@Min(1) int id, byte status) {
        AdminRoleDto adminRoleDto = adminRoleService.getById(id);
        if (adminRoleDto == null) {
            return errI18N("err.role.not.exists");
        }
        adminRoleDto.setStatus(status);
        adminRoleDto.setUpdatedAt(System.currentTimeMillis());
        int ret = adminRoleService.update(adminRoleDto);
        if (ret > 0) {
            return ok();
        }else {
            return errI18N("err.operation.fail");
        }
    }


    @ApiOperation(value = "角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", paramType = "query",  defaultValue = "1"),
            @ApiImplicitParam(name = "limit", value = "页大小", paramType = "query", defaultValue = "20"),
    })
    @GetMapping("/list")
    public Result list(@Min(1) int page, @Max(1000) int limit) {
        ResultList<AdminRoleDto> data = adminRoleService.getRoles(page, limit);
        return ok(data);
    }

//    @ApiOperation(value = "授权菜单")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "roleId", value = "角色ID", paramType = "form", dataType = "array", required = true),
//            @ApiImplicitParam(name = "menuIds", value = "菜单ID", paramType = "form",  allowMultiple = true),
//    })
//    @PostMapping("/assignMenus")
//    public Result assignMenus(Integer roleId, Integer... menuIds) {
//        if (roleId == null || roleId <= 0) {
//            return errI18N("err.param.invalid");
//        }
//        logger.info("trying to update role[{}]'s menus[{}]", roleId, Arrays.toString(menuIds));
//        if (menuIds == null ||menuIds.length == 0) {
//            adminMenuService.removeAllMenusFromRole(roleId);
//            return ok();
//        }
//        AdminRoleDto roleDto = adminRoleService.getById(roleId);
//        if (roleDto == null) {
//            return errI18N("err.role.not.exists");
//        }
//        int ret = adminMenuService.assignMenusToRole(roleId, getCurrentAdminUserId(), menuIds);
//        if (ret > 0) {
//            return ok();
//        } else {
//            return errI18N("err.operation.fail");
//        }
//    }

}

