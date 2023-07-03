package com.panda.expertsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.panda.expertsystem.entity.bo.userBo.RegisterBo;
import com.panda.expertsystem.entity.bo.userBo.VerifyCodeBo;
import com.panda.expertsystem.entity.dto.CommonResult;
import com.panda.expertsystem.entity.po.User;
import com.panda.expertsystem.global.ApplicationHelper;
import com.panda.expertsystem.mapper.UserMapper;
import com.panda.expertsystem.service.UserService;
import com.panda.expertsystem.utils.Md5Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult<Void> register(RegisterBo registerBo) {
        VerifyCodeBo verifyCodeBo = new VerifyCodeBo();
        verifyCodeBo.setCode(registerBo.getCode());
        verifyCodeBo.setEmail(registerBo.getEmail());
        verifyCodeBo.setPhone(registerBo.getMobilePhone());

        //先过滤
        if (!ApplicationHelper.verifyAccount(verifyCodeBo)) {
            return CommonResult.operateFailWithMessage("验证码无效");
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<User>()
                .and(param -> param.eq("phone", registerBo.getMobilePhone())
                        .or()
                        .eq("email", registerBo.getEmail()));
        //加读锁，第一次判断是否数据库已存在改账号
        lock.readLock().lock();
        try {
            //手机号或电子邮箱已经被绑定
            if (userMapper.selectList(queryWrapper).size() > 0) {
                return CommonResult.operateFailWithMessage("该手机号或电子邮箱已经被其它账号注册");
            }
        } finally {
            lock.readLock().unlock();
        }
        User user = new User();
        user.setName(registerBo.getName());
        user.setEmail(registerBo.getEmail());
        user.setPhone(registerBo.getMobilePhone());
        /*------------------以下字段必须确保正确性，故特此再次使用set方法----------------------*/

//先过滤
//        //会员类型为个人会员
//        user.setVipType(VipTypeConstant.PERSONAL_VIP);
//        //设置为注册会员
//        user.setVipRank(VipRankConstant.REGISTER_VIP);
//        //注册会员不具有会员号
//        user.setVipNumber(null);

        //密码md5加密
        user.setPassword(Md5Util.getMD5String(registerBo.getPassword()));
        //注册会员不需要推荐人
        user.setRecommender(null);
//        //注册会员没有会员有效时间
//        user.setStartTime(null);
//        user.setEndTime(null);
//        //账号正常，个人注册会员注册无需审核
//        user.setAccountStatus((byte) 0);
//        //total_pay=0
//        user.setTotalPay(0);
//        user.setCommonField(null);
        //插入数据库。加写锁
        lock.writeLock().lock();
        try {
            //手机号或电子邮箱已经被绑定
            if (userMapper.selectList(queryWrapper).size() > 0) {
                return CommonResult.operateFailWithMessage("该手机号或电子邮箱已经被其它账号注册");
            }
            //插入数据库
            if (userMapper.insert(user) > 0) {
                //为该用户分配 “注册会员” 的角色
//先屏蔽代码
// authorizationService.setRole(userMapper.selectOne(queryWrapper).getId(), RoleConstant.REGISTER_VIP);
                return CommonResult.operateSuccessWithMessage("注册成功");
            }
        } finally {
            lock.writeLock().unlock();
        }
        return CommonResult.operateServerError();
    }
}
