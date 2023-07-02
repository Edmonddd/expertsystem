package com.panda.expertsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.panda.expertsystem.entity.User;
import com.panda.expertsystem.mapper.UserMapper;
import com.panda.expertsystem.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
