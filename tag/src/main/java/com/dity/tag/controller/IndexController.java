package com.dity.tag.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author:yuhang
 * @Date:2018/5/21
 */
@Controller
public class IndexController {

    @RequestMapping("index")
    public String index(){
        return "index";
    }
}
