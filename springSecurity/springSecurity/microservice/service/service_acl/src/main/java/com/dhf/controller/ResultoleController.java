package com.dhf.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhf.entity.Role;
import com.dhf.service.RoleService;
import com.dhf.utils.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@RestController
@RequestMapping("/admin/acl/role")
//@CrossOrigin
public class ResultoleController {

    @Autowired
    private RoleService roleService;

    //    @ApiOperation(value = "获取角色分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(
//            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

//            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            Role role) {
        Page<Role> pageParam = new Page<>(page, limit);
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(role.getRoleName())) {
            wrapper.like("role_name", role.getRoleName());
        }
        roleService.page(pageParam, wrapper);
        return Result.ok().data("items", pageParam.getRecords()).data("total", pageParam.getTotal());
    }

    //    @ApiOperation(value = "新增角色")
    @PostMapping("save")
    public Result save(@RequestBody Role role) {
        roleService.save(role);
        return Result.ok();
    }
}

