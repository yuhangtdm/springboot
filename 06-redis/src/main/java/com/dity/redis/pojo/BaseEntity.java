package com.dity.redis.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author:yuhang
 * @Date:2018/4/25
 */
@Data
public class BaseEntity implements Serializable {
    //页码
    private int pn=1;

    //每页记录数
    private int size=20;

    //排序字段
    private String sortFiled="id";
}
