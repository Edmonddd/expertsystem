package com.panda.expertsystem.global;

import com.panda.expertsystem.entity.bo.userBo.VerifyCodeBo;
import com.panda.expertsystem.utils.EmailUtil;
import com.panda.expertsystem.utils.IDataStore;
import com.panda.expertsystem.utils.RegexUtil;
import com.panda.expertsystem.utils.SpringUtil;
import com.panda.expertsystem.utils.constant.NumberConstant;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @Author: lhw
 * @Date: 2023-07-03-10:48
 * @Description:
 */
public class ApplicationHelper {
    /**
     * 计划任务线程池 , 可执行计划任务 , 也可执行普通任务
     */
    public static final ScheduledThreadPoolExecutor SCHEDULED_THREAD_POOL = new ScheduledThreadPoolExecutor(16);
    /**
     * redis
     */
    private static final IDataStore DATA_STORE = SpringUtil.getBean("customDataStore");

    /**
     * 从线程池拿取一条线程发生验证码
     *
     * @param email   电子邮箱
     * @param tomeOut 验证码过期时间
     * @return 返回发送是否成功
     * @throws ExecutionException   抛出ExecutionException
     * @throws InterruptedException 抛出InterruptedException
     */
    public static boolean sendAuthCode(String email, long tomeOut) throws ExecutionException, InterruptedException {
        if (RegexUtil.isEmail(email)) {
            //开启一条新线程去执行发送邮箱的任务
            Future<Boolean> result = SCHEDULED_THREAD_POOL.submit(() -> EmailUtil.sendCodeMail(email, tomeOut));
            return result.get();
        }
        return false;
    }

    /**
     * 验证码邮箱或者手机的验证码
     *
     * @param verifyCodeBo email、phone、code封装类
     * @return 返回判断结果
     */
    public static boolean verifyAccount(VerifyCodeBo verifyCodeBo) {
        //邮箱
        if (verifyCodeBo.getEmail() != null) {
            return EmailUtil.verifyCode(verifyCodeBo.getEmail(), verifyCodeBo.getCode());
        }
        //手机
        if (RegexUtil.isPhoneNumber(verifyCodeBo.getPhone())) {
            return verifyCodeBo.getCode().equals(DATA_STORE.get(verifyCodeBo.getPhone()));
        }
        return false;
    }

    /**
     * 验证手机验证码是否正确
     *
     * @param phone 手机
     * @param code  验证码
     * @return 返回验证结果
     */
    public static boolean verifyPhoneCode(String phone, String code) {
        if (code == null || code.length() != NumberConstant.CODE_LENGTH || !RegexUtil.isPhoneNumber(phone)) {
            return false;
        }
        return false;
    }

    /**
     * 验证邮箱验证码是否正确
     *
     * @param email 邮箱
     * @param code  验证码
     * @return 返回判断结果
     */
    public static boolean verifyEmailCode(String email, String code) {
        if (code == null || code.length() != NumberConstant.CODE_LENGTH || !RegexUtil.isEmail(email)) {
            return false;
        }
        return code.equals(DATA_STORE.get(email));
        //return false;
    }

}
