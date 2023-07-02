package com.panda.expertsystem.common;

import lombok.Data;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回结果，服务端响应的数据最终都会封装成此对象
 * @param <T>
 */
@Data
public class CommonResult<T> {

    private Integer code; //编码：1成功，0和其它数字为失败

    private String msg; //错误信息

    private T data; //数据

    private Map map = new HashMap(); //动态数据

    public static <T> CommonResult<T> success(T object) {
        CommonResult<T> respone = new CommonResult<T>();
        respone.data = object;
        respone.code = 1;
        return respone;
    }

    public static <T> CommonResult<T> error(String msg) {
        CommonResult r = new CommonResult();
        r.msg = msg;
        r.code = 0;
        return r;
    }

    public CommonResult<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}
