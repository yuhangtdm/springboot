package com.dity.redis.dao;

import com.dity.redis.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author:yuhang
 * @Date:2018/4/25
 */
public interface UserDao extends JpaRepository<User,Long> {
}
