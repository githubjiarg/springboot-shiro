package com.spring.login.controller;

import com.spring.login.model.UserInfoBean;
import com.spring.login.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("sys")
public class LoginController {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Resource
    private UserService userService;

    /**
     *  用户登录
     * @param userName
     * @param password
     * @return
     */
    @PostMapping(value = "login")
    public String login(String userName, String password, Model model){
        Subject subject = SecurityUtils.getSubject();
        // 密码加密处理
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName,password);
        try {
            redisTemplate.opsForValue().set("jack","123456");
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e){
            System.out.println("----------- 账户不存在 -----------");
            model.addAttribute("msg","账号错误");
            return "login";
        } catch (IncorrectCredentialsException e){
            System.out.println("----------- 密码错误 -----------");
            model.addAttribute("msg","密码错误");
            // 返回login请求
            return "login";
        }
        // 登录成功，返回index页面
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
    @RequestMapping(value = "logout")
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
