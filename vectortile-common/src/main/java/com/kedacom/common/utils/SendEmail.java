package com.kedacom.common.utils;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/21 0021 15:20
 * @Description: 发送邮件
 */
public class SendEmail {
    /*
     1 发件人的 邮箱 和 密码
     2 某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（有的邮箱称为"授权码"）,
     3 对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
    */
    private static final String SEND_EMAIL = "kfront-service@kedacom.com";
    private static final String PASSWORD = "ua87q872";
    //发件人邮箱的 SMTP服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
    private static final String HOST = "smtp.kedacom.com";

    //收件人邮箱
    private String receiveEmail;

    // 发送主题，发送内容
    private String subject;
    private String content;

    public SendEmail(String receiveEmail,String subject,String content){
        this.receiveEmail = receiveEmail;
        this.subject = subject;
        this.content = content;
    }

    private MimeMessage createMessage(Session session, String subject, String content) throws Exception
    {
        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);

        // 设置发件人
        message.setFrom(new InternetAddress(SEND_EMAIL, "自动化矢量切片服务", "UTF-8"));

        // 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveEmail, "自动化矢量切片用户", "UTF-8"));

        // 邮件主题
        message.setSubject(subject, "UTF-8");

        // Content: 邮件正文(可以使用html标签)
        message.setContent(content, "text/html;charset=UTF-8");

        // 置发件时间
        message.setSentDate(new Date());

        // 保存设置
        message.saveChanges();
        return message;
    }

    public void sendOut()
    {

        try
        {
            // 创建参数配置, 用于连接邮件服务器的参数配置
            Properties props = new Properties();                    // 参数配置
            props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
            props.setProperty("mail.smtp.host", HOST);               // 发件人的邮箱的 SMTP 服务器地址
            props.setProperty("mail.smtp.auth", "true");            // 需要请求认证

            // PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
            // 如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
            // 打开下面 /* ... */ 之间的注释代码, 开启 SSL 安全连接。
            // SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
            // QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)

            /*
            final String smtpPort = "465";
            props.setProperty("mail.smtp.port", smtpPort);
            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.socketFactory.port", smtpPort);
            */

            //根据配置创建会话对象，用于和邮件服务器通信
            Session session = Session.getInstance(props);
            session.setDebug(true);
            MimeMessage message = this.createMessage(session, this.subject, this.content);

            // 根据 Session 获取邮件传输对象
            Transport transport = session.getTransport();

            // 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
            //
            //    PS_01: 成败的判断关键在此一句, 如果连接服务器失败, 都会在控制台输出相应失败原因的 log,
            //           仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接, 根据给出的错误
            //           类型到对应邮件服务器的帮助网站上查看具体失败原因。
            //
            //    PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
            //           (1) 邮箱没有开启 SMTP 服务;
            //           (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
            //           (3) 邮箱服务器要求必须要使用 SSL 安全连接;
            //           (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
            //           (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
            //
            //    PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。
            transport.connect(SEND_EMAIL,PASSWORD);

            // 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(message, message.getAllRecipients());

            // 关闭连接
            transport.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
