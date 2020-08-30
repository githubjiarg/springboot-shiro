package com.spring.login.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@Configuration
public class MultipartConfig{

    /**
     *  设置文件上传大小
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory multipartConfigFactory = new MultipartConfigFactory();
        // 单个数据上传大小
        multipartConfigFactory.setMaxFileSize(DataSize.parse("100MB"));
        // 总数据上传大小
        multipartConfigFactory.setMaxRequestSize(DataSize.parse("1000MB"));
        return multipartConfigFactory.createMultipartConfig();
    }

}
