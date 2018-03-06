package com.xiangshang.xsclub.configuration;

import com.xiangshang.xsclub.web.auth.AuthHandler;
import com.xiangshang.xsclub.web.auth.AuthHandlerInterceptor;
import com.xiangshang.xsclub.web.auth.JwtAuthHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    @Autowired
    private AuthHandlerInterceptor authHandlerInterceptor;

    @Bean
    public AuthHandler jwtAuthHandler() {
        return new JwtAuthHandler("houge");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authHandlerInterceptor);
    }
}
