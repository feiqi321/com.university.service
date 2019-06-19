package com.ovft.configure.interceptor;

import com.ovft.configure.config.BodyReaderHttpServletRequestWrapper;
import com.ovft.configure.config.MessageCenterException;
import com.ovft.configure.constant.ConstantClassField;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Admin;
import com.ovft.configure.sys.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class PassportInterceptor implements HandlerInterceptor {

    @Resource
    public RedisUtil redisUtil;

    private static final Logger logger = LoggerFactory.getLogger(PassportInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
//        printLog(httpServletRequest);
        String servletPath = httpServletRequest.getServletPath();
 /*       if(servletPath.contains("/server")) {
            String token = httpServletRequest.getHeader("token");
            Object obj = redisUtil.get(token);
            if (obj != null) {
                Integer id = (Integer) obj;
                // 如果是pc端登录，更新token缓存失效时间
                redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
                Admin hget = (Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
                *//*if (hget.getRole() != 0 || hget.getRole() != 1) {
                    HashSet<String> permission = (HashSet) redisUtil.hget(ConstantClassField.ADMIN_PERMISSION, id.toString());
                    String uri = httpServletRequest.getRequestURI();
                    boolean contains = permission.contains(uri);
                    if(!contains) {
                        throw new MessageCenterException(new WebResult("400","您没有该权限，请联系管理员添加",""), null);
                    }
                }*//*
            } else {
                throw new MessageCenterException(new WebResult("50012","请先登录",""), null);
            }
        }*/
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {


    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    //打印请求参数
    private void printLog(HttpServletRequest httpServletRequest) {
        String param = "";
        String method = "GET";
        ServletRequest requestWrapper = null;
        String uri = "";
        method = httpServletRequest.getMethod();
        uri = httpServletRequest.getRequestURI();
        //todo  获取调用的方法
        try {
            requestWrapper = new BodyReaderHttpServletRequestWrapper(httpServletRequest);  //替换
            if ("POST".equalsIgnoreCase(method)) {
                param = getBodyString(requestWrapper.getReader());
                logger.info("uri="+uri+",-----------filter读取body中的参数:{}", param);
            }else {
                logger.info("filter读取body中的参数:uri=" + uri + "?" +httpServletRequest.getQueryString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("filter读取body中的参数失败：", e);
        }


    }

    private static String getBodyString(BufferedReader br) {
        String inputLine;
        StringBuilder str = new StringBuilder();
        try {
            while ((inputLine = br.readLine()) != null) {
                str.append(inputLine);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return str.toString();

    }
}