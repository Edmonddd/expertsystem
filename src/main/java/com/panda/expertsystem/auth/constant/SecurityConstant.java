package com.panda.expertsystem.auth.constant;

import com.panda.expertsystem.entity.dto.CommonResult;
import com.panda.expertsystem.entity.dto.ResultCodeEnum;

/**
 * @Author: lhw
 * @Date: 2023-07-03-11:32
 * @Description:
 */
public class SecurityConstant {
    public static final CommonResult<String> ACCESS_DENY =
            new CommonResult<String>().setSuccess(false).setMessage(ResultCodeEnum.NOT_PERMISSION.getMessage()).setCode(ResultCodeEnum.NOT_PERMISSION.getCode());

    public static final CommonResult<String> LOGIN_ANOTHER =
            new CommonResult<String>().setSuccess(false).setMessage(ResultCodeEnum.LOGIN_ANOTHER.getMessage()).setCode(ResultCodeEnum.LOGIN_ANOTHER.getCode());

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String STATE_PREFIX = "Bearer ";
    public static final String ROLE_REDIS = "Role-";

    public static final String TOKEN_PREFIX = "Token-";

    public static final String RESPONSE_HEADER_LOG_ID = "logId";

    /**
     * 5分钟的毫秒时间戳
     */
    public static final Long FIVE_MIN = 300000L;
    /**
     * 10分钟的毫秒时间戳
     */
    public static final Long TEN_MIN = FIVE_MIN * 2;
    /**
     * 15分钟的毫秒时间戳
     */
    public static final Long FIFTEEN_MIN = FIVE_MIN * 3;
}
