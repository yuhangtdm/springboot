package com.dity.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:yuhang
 * @Date:2018/3/31
 */
@Controller
public class HelloController {

    @RequestMapping("hei")
    @ResponseBody
    public String hei(){
        return "hei";
    }

    @RequestMapping("hello")
    @ResponseBody
    public String hello(){
        return "hello";
    }
}
