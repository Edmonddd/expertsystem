package com.panda.expertsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.panda.expertsystem.entity.User;
import com.panda.expertsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public void usershow(){
        System.out.println("this is ...");
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getId,1);
        User emp = userService.getOne(queryWrapper);
        System.out.println(emp.toString());

        //new user
        //判断当前手机号对应的用户是否为新用户，如果是新用户就自动完成注册
        User user = new User();
        user.setName("edmond");
        user.setPhone("15626174022");
        userService.save(user);

    }


}

