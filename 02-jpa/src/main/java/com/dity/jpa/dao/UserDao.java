package com.dity.jpa.dao;

import com.dity.jpa.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/**
 * @author:yuhang
 * @Date:2018/4/18
 */
public interface UserDao extends JpaRepository<User,Long>,
        JpaSpecificationExecutor<User>,Serializable {
}
