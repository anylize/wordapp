package com.sangeng.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 
@Configuration
public class MyWebCongfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //WINDOWS本地用
        registry.addResourceHandler("/img/**").addResourceLocations("file:C:/web/img/");//本地储存地址
        //LINUX服务器用
        // registry.addResourceHandler("/img/**").addResourceLocations("file:/root/opt/img/stuAw");//服务器储存地址
    }
}