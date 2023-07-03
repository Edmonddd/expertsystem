package com.panda.expertsystem.controller;

import com.panda.expertsystem.auth.constant.SecurityConstant;
import com.panda.expertsystem.entity.bo.userBo.RegisterBo;
import com.panda.expertsystem.entity.dto.CommonResult;
import com.panda.expertsystem.global.ApplicationHelper;
import com.panda.expertsystem.service.UserService;
import com.panda.expertsystem.utils.RegexUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;



    @RequestMapping("/sendCode/{email}")
    public CommonResult<Void> sendCode(@PathVariable("email") String email) throws ExecutionException, InterruptedException {
        if (RegexUtil.isEmail(email)) {
            //验证码为5min有效
            boolean flag = ApplicationHelper.sendAuthCode(email, SecurityConstant.FIVE_MIN);
            if (flag) {
                return CommonResult.operateSuccessWithMessage("邮箱验证码已发送，请注意查收!");
            }
            return CommonResult.operateServerError();
        }
        return CommonResult.operateFailWithMessage("这不是一个合法邮箱");
    }


    /**
     * 个人会员的注册接口，只会分配注册注册会员的权限
     *
     * @param registerBo 新的注册会员所需的字段
     * @return 没有数据返回
     */
    @RequestMapping("/register")
    public CommonResult<Void> register(@RequestBody RegisterBo registerBo) {
        return userService.register(registerBo);
    }

}

