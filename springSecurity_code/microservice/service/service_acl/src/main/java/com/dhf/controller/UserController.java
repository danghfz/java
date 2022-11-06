package com.dhf.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhf.entity.User;
import com.dhf.service.RoleService;
import com.dhf.service.UserService;
import com.dhf.utils.utils.MD5;
import com.dhf.utils.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@RestController
@RequestMapping("/admin/acl/user")
//@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    //    @ApiOperation(value = "获取管理用户分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(
//            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

//            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

//            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
            User userQueryVo) {
        Page<User> pageParam = new Page<>(page, limit);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(userQueryVo.getUsername())) {
            wrapper.like("username", userQueryVo.getUsername());
        }

        IPage<User> pageModel = userService.page(pageParam, wrapper);
        return Result.ok().data("items", pageModel.getRecords()).data("total", pageModel.getTotal());
    }

    //    @ApiOperation(value = "新增管理用户")
    @PostMapping("save")
    public Result save(@RequestBody User user) {
        user.setPassword(MD5.encrypt(user.getPassword()));
        userService.save(user);
        return Result.ok();
    }

    //    @ApiOperation(value = "根据用户获取角色数据")
    @GetMapping("/toAssign/{userId}")
    public Result toAssign(@PathVariable String userId) {
//        Map<String, Object> roleMap = roleService.findResultoleByUserId(userId);
//        return Result.ok().data(roleMap);
        return null;
    }

    //    @ApiOperation(value = "根据用户分配角色")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody String userId, @RequestParam("roleId") String[] roleId) {
//        roleService.saveUserResultoleResultealtionShip(userId, roleId);
        return Result.ok();
    }
}

