package com.dity.mail;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author:yuhang
 * @Date:2018/4/21
 */
public class PropertyUtils {
    private final ResourceBundle resource;
    private final String fileName;

    /**
     * 常量必须被声明 需要初始化 可以在构造方法里初始化
     * @param fileName
     */
    public PropertyUtils(String fileName){
        this.fileName=fileName;
        Locale locale=new Locale("zh","CN");
        this.resource=ResourceBundle.getBundle(this.fileName,locale);
    }

    public  String getValue(String key){
        return this.resource.getString(key);
    }

}
