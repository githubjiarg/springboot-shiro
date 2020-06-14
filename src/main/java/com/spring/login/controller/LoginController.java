package com.spring.login.controller;

import com.spring.login.model.UserInfoBean;
import com.spring.login.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("sys")
public class LoginController {

    @Resource
    private UserService userService;

    /**
     *  用户登录
     * @param userName
     * @param password
     * @return
     */
    @PostMapping(value = "login")
    public String login(String userName,String password){
        Subject subject = SecurityUtils.getSubject();
        // 密码加密处理
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName,password);
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e){
            System.out.println("----------- 账户不存在 -----------");
            return "redirect:/login";
        } catch (IncorrectCredentialsException e){
            System.out.println("----------- 密码错误 -----------");
            return "redirect:/login";
        }
        // 登录成功
        System.out.println("----------- 登录成功 -----------");
        return "index";
    }

    /**
     *  账号注册
     * @param userInfoBean
     * @return
     */
    @PostMapping(value = "register")
    public String register(UserInfoBean userInfoBean){
        userService.register(userInfoBean);
        return "login";
    }

    /**
     *  退出登录
     * @return
     */
    @PostMapping(value = "logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
        } catch (Exception e){
            System.out.println("操作异常");
        }
        System.out.println("----------- 退出登录 -----------");
        return "redirect:/login";

    }

}
