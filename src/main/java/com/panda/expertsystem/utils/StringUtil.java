package com.panda.expertsystem.utils;

import java.util.Random;
import java.util.UUID;

/**
 * @Author: lhw
 * @Date: 2023-07-03-11:18
 * @Description: String工具类
 */
public class StringUtil {
    /**
     * 生成 length 位随机数
     *
     * @param length 随机数的长度
     * @return 返回随机数
     */
    public static String getRandomNumber(int length) {
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(random.nextInt(10));
        }
        return result.toString();
    }

    /**
     * 根据redis的key获取uid和roleId
     *
     * @param key 缓存的key
     * @return 第一个元素为uid。第二个元素为roleId
     */
    public static String[] getUidAndRoleIdByRedisKy(String key) {
        return key.substring(1).split("R");
    }

    /**
     * 获取同一时刻不重复的文件名
     *
     * @return 返回文件名
     */
    public static String getFileName() {
        return System.currentTimeMillis() + UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成一个会员号（10位）
     *
     * @param uid uid
     * @return 返回会员号
     */
    public static String getVipNumber(Long uid) {
        return getRandomNumber(10 - uid.toString().length()) + "V" + uid;
    }

    /**
     * 根据会员号得到对应的uid
     *
     * @param vipNumber 会员号
     * @return 返回uid
     */
    public static Long getUidByVipNumber(String vipNumber) {
        return Long.valueOf(vipNumber.substring(vipNumber.indexOf('V') + 1));
    }
}
