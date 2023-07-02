package com.panda.expertsystem.entity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: lhw
 * @Date: 2023-07-02-22:57
 * @Description:
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResult<T> {
    /**
     * 自定义状态码
     */
    private int code;
    /**
     * 相关提示信息
     */
    private String message;
    /**
     * 业务操作是否成功
     */
    private boolean success;
    /**
     * 返回给前端的数据
     */
    private T data;

    public CommonResult() {
    }

    public CommonResult(int code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public CommonResult(int code, String message, boolean success, T data) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.data = data;
    }

    @SuppressWarnings("unchecked")
    public static <P> CommonResult<P> operateSuccess() {
        return UnmodifiableCommonResult.SUCCESS;
    }

    public static <P> CommonResult<P> operateSuccessWithMessage(String message) {
        return new CommonResult<P>(ResultCodeEnum.SUCCESS.getCode(), message, true, null);
    }

    public static <P> CommonResult<P> operateSuccessWithMessage(String message, P p) {
        return new CommonResult<P>(ResultCodeEnum.SUCCESS.getCode(), message, true, p);
    }

    public static <P> CommonResult<P> operateFailWithMessage(String message, P p) {
        return new CommonResult<P>(ResultCodeEnum.BAG_REQUEST.getCode(), message, false, p);
    }

    public static <P> CommonResult<P> operateSuccess(P p) {
        return new CommonResult<P>(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), true, p);
    }

    public static <P> CommonResult<P> operateFail(P p) {
        return new CommonResult<P>(ResultCodeEnum.BAG_REQUEST.getCode(), ResultCodeEnum.BAG_REQUEST.getMessage(), false, p);
    }

    public static <P> CommonResult<P> operateFailWithMessage(String message) {
        return new CommonResult<P>(ResultCodeEnum.BAG_REQUEST.getCode(), message, false);
    }

    public static <P> CommonResult<P> operateServerError() {
        return new CommonResult<P>(ResultCodeEnum.ERROR.getCode(), ResultCodeEnum.ERROR.getMessage(), false, null);
    }

    public static <P> CommonResult<P> operateNoPermission() {
        return new CommonResult<P>(ResultCodeEnum.NOT_PERMISSION.getCode(), ResultCodeEnum.NOT_PERMISSION.getMessage(), false, null);
    }

    public static <P> CommonResult<P> operateTokenFail() {
        return new CommonResult<P>(ResultCodeEnum.LOGIN_ANOTHER.getCode(), "token无效", false, null);
    }

    @SuppressWarnings("unchecked")
    public static <P> CommonResult<P> autoResult(boolean isSuccess) {
        if (isSuccess) {
            return UnmodifiableCommonResult.SUCCESS;
        } else {
            return UnmodifiableCommonResult.FAILED;
        }
    }

    public static <P> CommonResult<P> autoResult(boolean isSuccess, P data) {
        if (isSuccess) {
            return CommonResult.operateSuccess(data);
        } else {
            return CommonResult.operateFail(data);
        }
    }

    @SuppressWarnings("unchecked")
    public CommonResult<T> operateFail() {
        return UnmodifiableCommonResult.FAILED;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @SuppressWarnings("rawtypes")
    private static class UnmodifiableCommonResult<P> extends CommonResult<P> {
        protected static final UnmodifiableCommonResult SUCCESS = new UnmodifiableCommonResult<>(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), true);
        protected static final UnmodifiableCommonResult FAILED = new UnmodifiableCommonResult<>(ResultCodeEnum.BAG_REQUEST.getCode(), ResultCodeEnum.BAG_REQUEST.getMessage(), false);

        public UnmodifiableCommonResult(int code, String message, boolean isSuccess) {
            super.setCode(code);
            super.setMessage(message);
            super.setSuccess(isSuccess);
        }

        @Override
        public CommonResult<P> setData(P data) {
            throw new UnsupportedOperationException("常量返回结果不允许被修改，如果需要修改结果请创建新的返回结果对象！");
        }

        @Override
        public CommonResult<P> setSuccess(boolean isSuccess) {
            throw new UnsupportedOperationException("常量返回结果不允许被修改，如果需要修改结果请创建新的返回结果对象！");
        }

        @Override
        public CommonResult<P> setMessage(String message) {
            throw new UnsupportedOperationException("常量返回结果不允许被修改，如果需要修改结果请创建新的返回结果对象！");
        }

        @Override
        public CommonResult<P> setCode(int code) {
            throw new UnsupportedOperationException("常量返回结果不允许被修改，如果需要修改结果请创建新的返回结果对象！");
        }
    }
}


