package com.ovft.configure.sys.web;

import com.jfinal.aop.Before;
import com.ovft.configure.config.CORSInterceptor;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 发送短信
 */
@Before(CORSInterceptor.class)
@RestController
@RequestMapping("/sms")
public class SmsControll {

    @Autowired
    public SmsService smsService;

    /**
     * 发送短信
     * @param phone
     * @return
     */
    @PostMapping(value = "/sendSms")
    public WebResult sendSms(@RequestBody Map<String, String> phone) {
        return smsService.sendSms(phone);
    }

}
