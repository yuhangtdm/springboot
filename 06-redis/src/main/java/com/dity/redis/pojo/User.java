package com.dity.redis.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author:yuhang
 * @Date:2018/4/25
 */
@Entity
@Table(name="t_user")
@Data
public class User extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer age;

    private String address;

    private String password;
}
