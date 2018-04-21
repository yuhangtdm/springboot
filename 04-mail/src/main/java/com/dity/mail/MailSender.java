package com.dity.mail;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.util.List;
import java.util.Properties;

/**
 * @author:yuhang
 * @Date:2018/4/21
 */
public class MailSender {

    //邮件实体
    private static MailEntity mail=new MailEntity();

    public MailSender title(String title){
        mail.setTitle(title);
        return this;
    }

    public MailSender content(String content){
        mail.setContent(content);
        return this;
    }

    public MailSender contentType(MailContentTypeEnum typeEnum){
        mail.setContentType(typeEnum.getValue());
        return this;
    }

    public MailSender targets(List<String> targets){
        mail.setTargets(targets);
        return this;
    }

    public void send() throws Exception {
        if(mail.getContentType()==null){
           contentType(MailContentTypeEnum.HTML);
        }
        if(StringUtils.isEmpty(mail.getTitle())){
            throw new RuntimeException("邮件标题不能为空");
        }

        if(StringUtils.isEmpty(mail.getContent())){
            throw new RuntimeException("邮件内容不能为空");
        }

        if(CollectionUtils.isEmpty(mail.getTargets())){
            throw new RuntimeException("目标不能为空");
        }

        final PropertyUtils propertyUtils=new PropertyUtils("mail");
        final Properties props=new Properties();

        //smtp认证
        props.setProperty("mail.smtp.auth","true");
        //smtp主机
        props.setProperty("mail.smtp.host",propertyUtils.getValue("mail.smtp.service"));
        //smyp端口
        props.setProperty("mail.smtp.port",propertyUtils.getValue("mail.smtp.port"));
        //发送者地址
        props.setProperty("mail.user",propertyUtils.getValue("mail.smtp.address"));
        //发送者密码
        props.setProperty("mail.password",propertyUtils.getValue("mail.smtp.pwd"));

        Authenticator authenticator=new Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                String userName=props.getProperty("mail.user");
                String password=props.getProperty("mail.password");
                return new PasswordAuthentication(userName,password);
            }
        };


        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        String nickName = MimeUtility.encodeText(propertyUtils.getValue("mail.smtp.nickname"));
        InternetAddress form = new InternetAddress(nickName + " <" + props.getProperty("mail.user") + ">");
        message.setFrom(form);

        // 设置邮件标题
        message.setSubject(mail.getTitle());
        //html发送邮件
        if(mail.getContentType().equals(MailContentTypeEnum.HTML.getValue())) {
            // 设置邮件的内容体
            message.setContent(mail.getContent(), mail.getContentType());
        }
        //文本发送邮件
        else if(mail.getContentType().equals(MailContentTypeEnum.TEXT.getValue())){
            message.setText(mail.getContent());
        }
        //发送邮箱地址
        List<String> targets = mail.getTargets();
        for(int i = 0;i < targets.size();i++){
            try {
                // 设置收件人的邮箱
                InternetAddress to = new InternetAddress(targets.get(i));
                message.setRecipient(Message.RecipientType.TO, to);
                // 最后当然就是发送邮件啦
                Transport.send(message);
            }catch (Exception e)
            {
                continue;
            }

        }
    }
}
