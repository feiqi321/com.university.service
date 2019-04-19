package com.ovft.configure.interceptor;

import com.ovft.configure.config.MessageCenterException;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PassportInterceptor implements HandlerInterceptor {

    @Resource
    public RedisUtil redisUtil;

    private static final Logger logger = LoggerFactory.getLogger(PassportInterceptor.class);
 
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String token = httpServletRequest.getHeader("token");
        String schoolId = httpServletRequest.getHeader("schoolId");
        //验证token
        if(StringUtils.isBlank(token)) {
            logger.error("[获取token失败] + " + token);
            throw new MessageCenterException(new WebResult("400", "请先登录", ""), null);
        }
        //todo 如果token不存在
//        if(!redisUtil.hasKey(token)) {
//            throw new AuthException("请先登录");
//        }
//        Integer id = (Integer) redisUtil.get(token);
        Integer id = 1;
        String servletPath = httpServletRequest.getServletPath();
        if(servletPath.contains("/server")) {
            //todo 如果是pc端登录，更新token缓存失效时间
//            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
//            httpServletRequest.setAttribute("adminId", id);
            httpServletRequest.setAttribute("adminId", 8);
        } else {
            httpServletRequest.setAttribute("userId", id);
            httpServletRequest.setAttribute("schoolId", schoolId);
        }
        return true;
    }
 
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
 
 
    }
 
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
 
    }
}