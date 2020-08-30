package com.spring.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  权限控制页面
 */
@Controller
public class IndexController {

    /**
     *  登录页面
     * @return
     */
    @RequestMapping(value = "login")
    public String login(){

        return "login";
    }

    /**
     *  首页
     * @return
     */
    @RequestMapping(value = "index")
    public String index(){

        return "redirect:/index";
    }

    /**
     *  注册页面
     * @return
     */
    @RequestMapping(value = "register")
    public String register(){

        return "register";
    }


}
