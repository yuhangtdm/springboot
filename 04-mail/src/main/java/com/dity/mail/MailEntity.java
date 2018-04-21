package com.dity.mail;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:yuhang
 * @Date:2018/4/21
 */
@Data
public class MailEntity {
    //smtp服务器
    private String smtpService;

    //smtp端口
    private String smtpPort;

    //发送邮箱的地址
    private String sendMailAddress;

    //发送邮箱的smtp密码
    private String sendMailPwd;

    //邮件标题
    private String title;

    //邮件内容
    private String content;

    //内容格式
    private String contentType;

    //接收邮箱的地址
    private List<String> targets=new ArrayList<>();
}
