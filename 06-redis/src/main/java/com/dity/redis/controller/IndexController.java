package com.dity.redis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:yuhang
 * @Date:2018/4/25
 */
@Controller
public class IndexController {

    @RequestMapping("/cors")
    @ResponseBody
    public String index(){
        return "这是跨域请求，请求成功";
    }
}
