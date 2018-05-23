package com.dity.tag.field;

import com.dity.tag.entity.SyDictItem;
import com.dity.tag.service.DictItemService;
import com.dity.tag.util.CacheUtil;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author:yuhang
 * @Date:2018/5/21
 */
public class SelectFieldTag extends SimpleTagSupport {

    //数据字典的key
    private String dicField;

    //数据字典的选择
    private String dicFilter;

    //是否允许为空
    private String allowBlank;

    //初始化值
    private String value;






    @Override
    public void doTag() throws JspException, IOException {
        StringBuffer sb=new StringBuffer();
        sb.append("<select");
        PageContext pageContext= (PageContext) getJspContext();
        PrintWriter out = pageContext.getResponse().getWriter();
        List<SyDictItem> dictItem=null;
        if(!StringUtils.isEmpty(dicField)){
            sb.append(" dicField='"+dicField+"'");
            dictItem= CacheUtil.getDictItem(dicField);
        }
        List<String> containsCodes=null;
        List<String> unContainsCodes=null;
        if(!StringUtils.isEmpty(dicFilter)){
            sb.append(" dicFilter='"+dicFilter+"'");
            if(dicFilter.startsWith("!")){
                String[] split = dicFilter.substring(1, dicFilter.length()).split(",");
                unContainsCodes= Arrays.asList(split);
            }else{
                String[] split = dicFilter.split(",");
                containsCodes=Arrays.asList(split);
            }
        }
        if(!StringUtils.isEmpty(value)){
            sb.append(" value='"+value+"'" );
        }
        if(!StringUtils.isEmpty(allowBlank)){
            sb.append(" allowBlank='"+allowBlank+"'");
            if(allowBlank.equals("true")){
                sb.append(">").append("<option value=''>--</option>");
            }
        }
        List<SyDictItem> dictItems=new ArrayList<>();
        if(dictItem!=null){
            if(StringUtils.isEmpty(allowBlank)){
               sb.append(">");
            }
            if(containsCodes!=null){
                for (String containsCode : containsCodes) {
                    for (SyDictItem syDictItem : dictItem) {
                        if(containsCode.equals(syDictItem.getCode())){
                            dictItems.add(syDictItem);
                            break;
                        }
                    }

                }
            }
            if(unContainsCodes!=null){
                    for (SyDictItem syDictItem : dictItem) {
                        boolean flag=false;
                        for (String containsCode : unContainsCodes) {
                            if (containsCode.equals(syDictItem.getCode())) {
                                flag=true;
                                break;
                            }
                        }
                        if(!flag){
                            dictItems.add(syDictItem);
                        }
                    }

            }
            if(containsCodes==null && unContainsCodes==null){
                dictItems.addAll(dictItem);
            }
        }
        if(!CollectionUtils.isEmpty(dictItems)){
            if(!StringUtils.isEmpty(value)) {
                for (SyDictItem item : dictItems) {
                    if(value.equals(item.getCode())){
                        sb.append("<option value='"+item.getCode()+"' 'selected'>"+item.getDesc()+"</option>");
                    }else{
                        sb.append("<option value='"+item.getCode()+"'>"+item.getDesc()+"</option>");
                    }
                }
            }else{
                for (SyDictItem item : dictItems) {
                    sb.append("<option value='"+item.getCode()+"'>"+item.getDesc()+"</option>");
                }
            }
        }
        sb.append("</select>");

        out.write(sb.toString());

    }

    public String getDicField() {
        return dicField;
    }

    public void setDicField(String dicField) {
        this.dicField = dicField;
    }

    public String getDicFilter() {
        return dicFilter;
    }

    public void setDicFilter(String dicFilter) {
        this.dicFilter = dicFilter;
    }

    public String getAllowBlank() {
        return allowBlank;
    }

    public void setAllowBlank(String allowBlank) {
        this.allowBlank = allowBlank;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
