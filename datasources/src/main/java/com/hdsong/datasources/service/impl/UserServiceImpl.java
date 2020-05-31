package com.hdsong.datasources.service.impl;

import com.hdsong.datasources.entity.db1.User;
import com.hdsong.datasources.mapper.db1.UserMapper;
import com.hdsong.datasources.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @作者 宋寒冬
 * @日期 2020-04-20
 * @时间 09:22
 * @描述
 */


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectUserList() {
        return this.userMapper.selectUserList();
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        this.userMapper.saveUser(user);
//        throw new RuntimeException();
    }
}
