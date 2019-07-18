package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.service.EduSignService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zqx
 * @since 2019-07-04
 */
@RestController
@RequestMapping("/eduSign")
public class EduSignContrroller {
    @Resource
    private EduSignService signService;

    /**
     * 签到
     * @return
     */
    @GetMapping(value = "/sign")
    public WebResult approve(HttpServletRequest request){
        String userId = request.getHeader("userId");
        if(StringUtils.isBlank(userId) || userId.equals("null")) {
            return new WebResult("400", "请登录！", "");
        }
        return signService.sign(Integer.valueOf(userId));
    }

    /**
     * 查询是否签到
     * @return
     */
    @GetMapping(value = "/isSign")
    public WebResult isSign(HttpServletRequest request){
        String userId = request.getHeader("userId");
        if(StringUtils.isBlank(userId) || userId.equals("null")) {
            return new WebResult("50012", "请登录！", "");
        }
        return signService.isSign(Integer.valueOf(userId));
    }
}

