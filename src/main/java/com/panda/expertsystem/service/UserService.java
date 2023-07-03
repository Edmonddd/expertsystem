package com.panda.expertsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.panda.expertsystem.entity.bo.userBo.RegisterBo;
import com.panda.expertsystem.entity.dto.CommonResult;
import com.panda.expertsystem.entity.po.User;

public interface UserService extends IService<User> {

    /**
     * 注册个人会员的业务
     *
     * @param registerBo 新用户
     * @return 返回注册结果
     */
    CommonResult<Void> register(RegisterBo registerBo);
}
