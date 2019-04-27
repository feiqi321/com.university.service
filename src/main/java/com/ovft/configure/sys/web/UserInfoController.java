package com.ovft.configure.sys.web;

import com.jfinal.aop.Before;
import com.ovft.configure.config.CORSInterceptor;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.UserInfoBean;
import com.ovft.configure.sys.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by looyer on 2019/4/2.
 */
@Before(CORSInterceptor.class)
@RestController
@RequestMapping("/user")
public class UserInfoController {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);
    @Autowired
    private UserInfoService userInfoService;

    @PostMapping(value = "/listUserinfo")
    public WebResult listUserinfo(@RequestBody UserInfoBean userInfoBean)  {
        logger.info("listUserinfo");
        WebResult result = new WebResult();

        List<UserInfoBean> list =  userInfoService.listUserInfo(userInfoBean);
        result.setData(200);
        result.setData(list);

        return  result;
    }


}
