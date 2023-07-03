package com.panda.expertsystem.utils;

import com.panda.expertsystem.config.properties.EmailProperties;
import com.panda.expertsystem.utils.constant.NumberConstant;
import lombok.extern.slf4j.Slf4j;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;


/**
 * @Author: lhw
 * @Date: 2023-07-03-10:54
 * @Description:  邮件的工具类
 */
@Slf4j
public class EmailUtil {
    private static final IDataStore DATA_STORE = SpringUtil.getBean("customDataStore");

    private static final EmailProperties EMAIL_PROPERTIES = SpringUtil.getBean("emailProperties");

    /**
     * 给某个电子邮箱发送验证码
     *
     * @param timeOut          验证码过期时间-毫秒时间戳
     * @param recipientAddress 收信方
     */
    public static boolean sendCodeMail(String recipientAddress, long timeOut) {
        boolean flag = false;
        //1、连接邮件服务器的参数配置
        Properties props = new Properties();
        //设置发件人的SMTP服务器地址
        props.setProperty("mail.smtp.host", "smtp.163.com");
        //设置传输协议
        props.setProperty("mail.transport.protocol", "smtp");
        //设置用户的认证方式
        props.setProperty("mail.smtp.auth", "true");
        //端口
        props.setProperty("mail.smtp.port", EMAIL_PROPERTIES.getPort());
        //jdk高于1.6
        props.setProperty("java.net.preferIPv4Stack", "true");
        //允许ssl
        props.setProperty("mail.smtp.ssl.enable", "true");

        // 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", "465");

        //2、创建定义整个应用程序所需的环境信息的 Session 对象
        Session session = Session.getInstance(props);
        //设置调试信息在控制台打印出来
        session.setDebug(true);
        //3、创建邮件的实例对象
        MimeMessage msg;
        try {
            msg = getCodeMimeMessage(session, recipientAddress, timeOut);
            //4、根据session对象获取邮件传输对象Transport
            Transport transport = session.getTransport();
            //设置发件人的账户名和密码
            transport.connect(EMAIL_PROPERTIES.getAccount(), EMAIL_PROPERTIES.getPassword());
            //发送邮件，并发送到所有收件人地址，message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(msg, msg.getAllRecipients());
            //5、关闭邮件连接
            transport.close();
            flag = true;
        } catch (Exception e) {
            log.error("给" + recipientAddress + "发送邮件失败，异常信息：" + e.getLocalizedMessage());
        }
        return flag;
    }

    /**
     * 验证码验证码是否正确
     *
     * @param email 邮箱
     * @param code  验证码
     * @return 返回判断结果
     */
    public static boolean verifyCode(String email, String code) {
        if (code == null || code.length() != NumberConstant.CODE_LENGTH || !RegexUtil.isEmail(email)) {
            return false;
        }
        return code.equals(DATA_STORE.get(email));
    }


    /**
     * 获得创建一封邮件的实例对象，用于发送验证码
     *
     * @param session session
     * @param timeOut 验证码过期时间-毫秒时间戳
     * @return MimeMessage对象
     */
    private static MimeMessage getCodeMimeMessage(Session session, String recipientAddress, long timeOut) throws Exception {
        String code = StringUtil.getRandomNumber(6);
        String min = TimeUtil.timeMillisToMin(timeOut);
        String message = "邮箱验证码为【" + code + "】，" + min + "分钟内有效。为了保障您的账号安全，请勿泄露\n-注:专家系统开发阶段测试用。";
        //创建一封邮件的实例对象
        MimeMessage msg = new MimeMessage(session);
        //设置发件人地址
        msg.setFrom(new InternetAddress(EMAIL_PROPERTIES.getAddress()));

        // 设置收件人地址（可以增加多个收件人、抄送、密送），即下面这一行代码书写多行
        // MimeMessage.RecipientType.TO:发送
        // MimeMessage.RecipientType.CC：抄送
        // MimeMessage.RecipientType.BCC：密送
        msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(recipientAddress));
        //设置邮件主题
        msg.setSubject("广东省工业软件协会-验证码", "UTF-8");
        //设置邮件正文
        msg.setContent(message, "text/html;charset=UTF-8");
        //设置邮件的发送时间,默认立即发送
        msg.setSentDate(new Date());
        //验证码存redis，五分钟有效，就用 邮箱名 作为redis的key
        DATA_STORE.put(recipientAddress, code, timeOut);
        return msg;
    }
}
