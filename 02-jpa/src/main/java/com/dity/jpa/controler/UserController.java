package com.dity.jpa.controler;

import com.dity.jpa.dao.UserDao;
import com.dity.jpa.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author:yuhang
 * @Date:2018/4/18
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("list")
    public List<User> list(){
        return userDao.findAll();
    }

    @RequestMapping("save")
    public User save(User user){
        return userDao.save(user);
    }

    @RequestMapping("delete")
    public List<User> delete(Long id){
        userDao.delete(id);
        return userDao.findAll();
    }
}
