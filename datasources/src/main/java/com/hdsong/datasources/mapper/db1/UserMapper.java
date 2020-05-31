package com.hdsong.datasources.mapper.db1;

import com.hdsong.datasources.entity.db1.User;

import java.util.List;

/**
 * @作者 宋寒冬
 * @日期 2020-04-20
 * @时间 09:28
 * @描述
 */
public interface UserMapper {


    List<User> selectUserList();

    void saveUser(User user);

}
