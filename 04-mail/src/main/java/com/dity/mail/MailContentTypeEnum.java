package com.dity.mail;

/**
 * @author:yuhang
 * @Date:2018/4/21
 */
public enum MailContentTypeEnum {
    HTML("text/html;charset=UTF-8"),
    TEXT("text");
    private String value;

    MailContentTypeEnum(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
