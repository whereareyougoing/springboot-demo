package com.hdsong.datasources.service;

import com.hdsong.datasources.entity.db1.User;

import java.util.List;

/**
 * @作者 宋寒冬
 * @日期 2020-04-20
 * @时间 09:21
 * @描述
 */
public interface UserService {

    List<User> selectUserList();

    void saveUser(User user);
}
