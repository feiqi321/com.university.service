package com.ovft.configure.config;

import com.ovft.configure.interceptor.PassportInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @auther
 * @Date 2019/2/15 11:29
 * @Description
 */
@Configuration
public class MywebConfiguration extends WebMvcConfigurerAdapter {
    @Bean
    public PassportInterceptor passportInterceptor() {
        return new PassportInterceptor();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST")
                .allowedHeaders("*").allowCredentials(true);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(passportInterceptor())
                .addPathPatterns("/**")
                //TODO    放行路径待修改
                .excludePathPatterns("/server/admin/login", "/user/login", "/user/regist", "/sms/sendSms",
                        "/apply/showCategory", "/article/**");
        super.addInterceptors(registry);
    }
}
