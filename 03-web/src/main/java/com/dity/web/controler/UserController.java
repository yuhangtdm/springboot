package com.dity.web.controler;

import com.dity.web.dao.UserDao;
import com.dity.web.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(String name, String password, HttpServletRequest request){
        User user = userDao.findOne(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.where(criteriaBuilder.equal(root.get("name"),name));
                return null;
            }
        });
        if(user==null){
            return "用户名错误";
        }
        if(!password.equals(user.getPassword())) {
            return "密码错误";
        }
        request.getSession().setAttribute("user",user);

        return "用户登录成功";
    }
}
