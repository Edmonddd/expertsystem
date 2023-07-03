package com.panda.expertsystem.entity.bo.userBo;

import lombok.Data;

/**
 * @Author: lhw
 * @Date: 2023-07-02-23:30
 * @Description:
 */
@Data
public class VerifyCodeBo {
    private String email;
    private String phone;
    private String code;
}
