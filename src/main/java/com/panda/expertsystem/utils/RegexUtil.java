package com.panda.expertsystem.utils;

import java.util.regex.Pattern;

/**
 * @Author: lhw
 * @Date: 2023-07-03-10:52
 * @Description: 正则匹配判断的工具类
 */
public class RegexUtil {
    /**
     * 判断是否符合邮箱的格式
     *
     * @param email 待判断的电子邮箱
     * @return 判断返回结果
     */
    public static boolean isEmail(String email) {
        //邮箱正则表达式
        String check = "^\\s*\\w+(?:\\.?[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
        return Pattern.compile(check).matcher(email).matches();
    }

    /**
     * 判断是否符合手机正则表达式
     *
     * @param phoneNumber 待判断的手机号
     * @return 返回判断结果
     */
    public static boolean isPhoneNumber(String phoneNumber) {
        //手机正则表达式
        String check = "^1[3-9]\\d{9}$";
        return Pattern.compile(check).matcher(phoneNumber).matches();
    }
}
