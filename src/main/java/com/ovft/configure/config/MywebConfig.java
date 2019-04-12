package com.ovft.configure.config;

import com.ovft.configure.interceptor.PassportInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
 
/**
 * @auther 
 * @Date 2019/2/15 11:29
 * @Description
 */
//TODO-------------------------------------
//@Configuration
public class MywebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PassportInterceptor());
//                .addPathPatterns("/**");
                //TODO    放行路径待修改
//                .excludePathPatterns("/user/login/**", "/server/admin/login");
        super.addInterceptors(registry);
    }
}
