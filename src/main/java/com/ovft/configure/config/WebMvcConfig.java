/*
package com.ovft.configure.config;

import com.ovft.configure.interceptor.CorsInterceptor;
import com.ovft.configure.interceptor.PassportInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

*/
/**
 * Created by looyer on 2019/3/20.
 *//*


@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Resource
    private CorsInterceptor corsInterceptor;

   @Bean
    public PassportInterceptor passportInterceptor() {
        return new PassportInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 跨域拦截器需放在最上面
        registry.addInterceptor(corsInterceptor).addPathPatterns("/**");
        registry.addInterceptor(passportInterceptor())
                .addPathPatterns("/**")
                //TODO    放行路径待修改
                .excludePathPatterns("/server/admin/login")
                .excludePathPatterns("/server/role/allMenu")
                .excludePathPatterns("/server/school/schoolList")
                .excludePathPatterns("/server/admin/findSchoolByPhone");
        super.addInterceptors(registry);
    }

}

*/
