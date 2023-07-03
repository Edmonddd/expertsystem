package com.panda.expertsystem.utils.impl;

import com.alibaba.fastjson.JSONArray;
import com.panda.expertsystem.utils.IDataStore;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @Author: lhw
 * @Date: 2023-07-03-11:51
 * @Description:
 */

@Component
public class CustomDataStore implements IDataStore {
    private final StringRedisTemplate redisTemplate;

//先注释 PermissionMapper
    //private final PermissionMapper permissionMapper;

//    public CustomDataStore(StringRedisTemplate redisTemplate, PermissionMapper permissionMapper) {
//        this.redisTemplate = redisTemplate;
//        this.permissionMapper = permissionMapper;
//    }

    public CustomDataStore(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @Override
    public boolean put(String key, String value, Long expire) {
        if (expire == null) {
            return false;
        }
        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.MILLISECONDS);
        return true;
    }

    @Override
    public boolean remove(String key) {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

    @Override
    public boolean refresh(String key, long expire) {
        return Boolean.TRUE.equals(redisTemplate.expire(key, expire, TimeUnit.MILLISECONDS));
    }

    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

//先注释-
//    @Override
//    public void updateAuthRedis() {
//        Long[] arr = RoleConstant.ROLE_ARRAY;
//        //更新 redis 缓存
//        for (Long roleId : arr) {
//            String key = SecurityConstant.ROLE_REDIS + roleId.toString();
//            remove(key);
//            List<String> permissionList = permissionMapper.listPermissionByRoleId(roleId);
//            //缓存时间设置为一个月
//            put(key, JSONArray.toJSONString(permissionList), 2629800000L);
//        }
//    }

    @Override
    public void updateAuthRedis() {
    }


}
