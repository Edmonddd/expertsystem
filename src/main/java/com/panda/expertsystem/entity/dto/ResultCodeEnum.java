package com.panda.expertsystem.entity.dto;

import com.sun.net.httpserver.Authenticator;

/**
 * @Author: lhw
 * @Date: 2023-07-02-23:07
 * @Description:
 */
public enum ResultCodeEnum {
    SUCCESS(200, "操作成功"),
    ERROR(500, "系统错误"),
    NOT_PERMISSION(401, "您无访问权限该资源"),
    LOGIN_ANOTHER(444, "您的账号在其它地方登录!"),
    NOT_FOUND(404, "对应路径找不到"),
    IDENTITY_INVALID(403, "当前用户状态失效"),
    BAG_REQUEST(400, "错误请求"),
    NOT_TOKEN_REQUEST(401, "非法请求头，缺少token"),
    INVALID_WEBSOCKET_CONNECTION(406, "非法websocket连接，缺少token或token无效"),
    TOO_MANY_REQUEST(429, "请求频繁");

    private Integer code;
    private String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
