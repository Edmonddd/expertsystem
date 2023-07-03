package com.panda.expertsystem.config.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: lhw
 * @Date: 2023-07-03-11:11
 * @Description:
 */

@Data
@ConfigurationProperties(prefix = "email")
@NoArgsConstructor
@AllArgsConstructor
@Component
public class EmailProperties {
    /**
     * 邮件发送方
     */
    private String address;
    /**
     * 发送方身份验证密码
     */
    private String password;
    /**
     * 发件人账户名
     */
    private String account;
    /**
     * 邮箱发送端口
     */
    private String port;
}
