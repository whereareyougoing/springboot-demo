package com.hdsong.datasources.controller;

import com.hdsong.datasources.entity.db1.User;
import com.hdsong.datasources.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @作者 宋寒冬
 * @日期 2020-04-20
 * @时间 09:19
 * @描述
 */


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/select/list")
    public List<User> selectUserList() {
        return this.userService.selectUserList();
    }

    @GetMapping("/save")
    public String saveUser(User user) {
        this.userService.saveUser(user);
        return "OK";
    }
}
