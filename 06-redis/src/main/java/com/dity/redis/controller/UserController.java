package com.dity.redis.controller;

import com.dity.redis.dao.UserDao;
import com.dity.redis.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author:yuhang
 * @Date:2018/4/25
 */
@RestController
@CacheConfig(cacheNames = "user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("list")
    @Cacheable
    public List<User> list(){
        return userDao.findAll();
    }

    @RequestMapping("page")
    public List<User> page(int page){
        User user=new User();
        user.setPn(page);
        user.setSize(2);
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        PageRequest pageRequest=new PageRequest(user.getPn()-1,user.getSize(),sort);
        List<User> content = userDao.findAll(pageRequest).getContent();
        return content;
    }

}
