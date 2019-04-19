package com.ovft.configure.config;

import com.ovft.configure.http.result.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class AdminExceptionHandler {
 
    private static final Logger logger = LoggerFactory.getLogger(AdminExceptionHandler.class);
 
    /**
      * @Author: zqx
      * @Description: 系统异常捕获处理
      */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public WebResult javaExceptionHandler(Exception ex) {//APIResponse是项目中对外统一的出口封装，可以根据自身项目的需求做相应更改
        logger.error("捕获到Exception异常",ex);
        //异常日志入库
        return new WebResult("400", "出错啦！！！ 请稍后再试", "");
    }
 
    /**
      * @Author: zqx
      * @Description: 自定义异常捕获处理
      */
    @ResponseBody
    @ExceptionHandler(value = MessageCenterException.class)//MessageCenterException是自定义的一个异常
    public WebResult messageCenterExceptionHandler(MessageCenterException ex) {
        logger.error("捕获到MessageCenterException异常",ex.getException());
        //异常日志入库
 
        return ex.getApiResponse();
    }
 
}
