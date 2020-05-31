package com.hdsong.datasources.service;

import com.hdsong.datasources.entity.db2.Role;

import java.util.List;

/**
 * @作者 宋寒冬
 * @日期 2020-04-20
 * @时间 09:21
 * @描述
 */
public interface RoleService {

    List<Role> selectRoleList();

    void saveRole(Role role);
}
