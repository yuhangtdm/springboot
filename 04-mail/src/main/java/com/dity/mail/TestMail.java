package com.dity.mail;

import java.util.ArrayList;

/**
 * @author:yuhang
 * @Date:2018/4/21
 */
public  class TestMail {

    public static void main(String[] args) throws Exception
    {
        new MailSender()
                .title("xxx标题")
                .content("xxx内容")
                .contentType(MailContentTypeEnum.TEXT)
                .targets(new ArrayList<String>(){{
                    add("xxx@qq.com");
                }})
                .send();
    }
}
