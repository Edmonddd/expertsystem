package com.panda.expertsystem.utils;

/**
 * @Author: lhw
 * @Date: 2023-07-03-10:49
 * @Description:
 */
public interface IDataStore {
    /**
     * 存储数据
     *
     * @param key    数据Key
     * @param value  数据内容
     * @param expire 过期时间（毫秒单位）
     * @return 存储是否成功
     */
    boolean put(String key, String value, Long expire);

    /**
     * 删除数据
     *
     * @param key 数据Key
     * @return 删除是否成功
     */
    boolean remove(String key);

    /**
     * 更新数据时间
     *
     * @param key    数据Key
     * @param expire 过期时间（毫秒单位）
     * @return 更新是否成功
     */
    boolean refresh(String key, long expire);

    /**
     * 读取数据
     *
     * @param key 数据Key
     * @return 读取结果
     */
    String get(String key);

    /**
     * 更新redis的权限缓存
     */
    void updateAuthRedis();
}
