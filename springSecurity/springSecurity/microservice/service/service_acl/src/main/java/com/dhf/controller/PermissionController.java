package com.dhf.controller;


import com.dhf.entity.Permission;
import com.dhf.service.PermissionService;
import com.dhf.utils.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限 菜单管理
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@RestController
@RequestMapping("/admin/acl/permission")
//@CrossOrigin
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    //获取全部菜单
//    @ApiOperation(value = "查询所有菜单")
    @GetMapping
    public Result indexAllPermission() {
//        List<Permission> list =  permissionService.queryAllMenu();
//        return Result.ok().data("children",list);
        return null;
    }

//    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    public Result doAssign(String roleId,String[] permissionId) {
//        permissionService.saveRolePermissionRealtionShip(roleId,permissionId);
        return Result.ok();
    }

//    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("toAssign/{roleId}")
    public Result toAssign(@PathVariable String roleId) {
//        List<Permission> list = permissionService.selectAllMenu(roleId);
//        return Result.ok().data("children", list);
        return null;
    }

}

