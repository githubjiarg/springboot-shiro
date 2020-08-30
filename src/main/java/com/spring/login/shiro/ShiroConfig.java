package com.spring.login.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  shiro 配置
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

    /**
     *  账户认证
     * @return
     */
    @Bean
    public Realm myRealm(){
        MyRealm myRealm = new MyRealm();
        // 认证信息开启缓存
        myRealm.setCachingEnabled(true);
        // 认证缓存
        myRealm.setAuthenticationCachingEnabled(true);
        myRealm.setAuthenticationCacheName("authenticationcache");
        // 权限缓存
        myRealm.setAuthorizationCachingEnabled(true);
        myRealm.setAuthorizationCacheName("authorizationcache");
        // 使用缓存
        myRealm.setCacheManager(new EhCacheManager());
        return myRealm;
    }

    /**
     * shiro认证管理
     * @return
     */
    @Bean
    public DefaultWebSecurityManager  securityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myRealm());
        return defaultWebSecurityManager;
    }

    /**
     *  filter工厂
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 指定认证管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        // 指定地址
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unRole");
        // 指定权限过滤
        Map<String,String> filterMap = new LinkedHashMap<>();
        filterMap.put("/logout","logout");
        filterMap.put("/sys/login","anon");
        filterMap.put("/static/**","anon");
        filterMap.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

}
