package com.spring.login.config;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  全局异常处理
 */
@CacheConfig
public class ExceptionConfig implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        // shiro权限拦截
        if ( e instanceof AuthorizationException) {
            modelAndView.setViewName("error");
            modelAndView.addObject("msg","无操作权限");
        }
        return modelAndView;
    }
}
