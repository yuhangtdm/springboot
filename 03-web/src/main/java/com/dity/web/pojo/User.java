package com.dity.web.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author:yuhang
 * @Date:2018/4/18
 */
@Entity
@Table(name = "t_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name="address")
    private String address;

    @Column(name="password")
    private String password;
}
