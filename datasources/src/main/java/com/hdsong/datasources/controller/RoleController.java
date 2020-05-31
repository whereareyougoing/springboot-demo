package com.hdsong.datasources.controller;

import com.hdsong.datasources.entity.db2.Role;
import com.hdsong.datasources.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @作者 宋寒冬
 * @日期 2020-04-20
 * @时间 09:20
 * @描述
 */

@RestController
@RequestMapping("/role")
public class RoleController {


    @Autowired
    private RoleService roleService;

    @GetMapping("/select/list")
    public List<Role> selectRoleList() {
        return this.roleService.selectRoleList();
    }

    @GetMapping("/save")
    public String saveRole(Role role) {
        this.roleService.saveRole(role);
        return "OK";
    }
}
