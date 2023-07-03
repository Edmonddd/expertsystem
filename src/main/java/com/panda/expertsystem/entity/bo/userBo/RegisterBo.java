package com.panda.expertsystem.entity.bo.userBo;

import lombok.Data;

/**
 * @Author: lhw
 * @Date: 2023-07-02-22:50
 * @Description:
 */
@Data
public class RegisterBo {
    private String password;
    private String name;
    private String email;
    private String mobilePhone;
    private String code;
}