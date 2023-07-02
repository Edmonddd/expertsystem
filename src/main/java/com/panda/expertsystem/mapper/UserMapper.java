package com.panda.expertsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.panda.expertsystem.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
