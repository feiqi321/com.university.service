package com.ovft.configure.config;

import com.ovft.configure.interceptor.PassportInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
 
/**
 * @auther 
 * @Date 2019/2/15 11:29
 * @Description
 */
//@Configuration
public class MywebConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public PassportInterceptor passportInterceptor() {
        return new PassportInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
      /*registry.addInterceptor(passportInterceptor())
                .addPathPatterns("/**")
                //TODO    放行路径待修改
               .excludePathPatterns("/server/admin/login","/user/login","/user/regist");
        super.addInterceptors(registry);*/
    }
}
