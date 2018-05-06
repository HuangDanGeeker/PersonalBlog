package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by HuangDanGeeker on 2018/4/25.
 */
@Controller
public class BasicController {

    @RequestMapping("/test/index")
    public String test(){
        return "index";
    }

    @RequestMapping("/test/login")
    public String testLogin(){
        return "/admin/login";
    }
}
