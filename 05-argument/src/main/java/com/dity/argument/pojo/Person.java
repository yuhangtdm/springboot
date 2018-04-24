package com.dity.argument.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author:yuhang
 * @Date:2018/4/23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement//加了该注解 @ResponseBody即可序列化成xml
public class Person implements Serializable {

    private String firstName;
    private String lastName;
}
