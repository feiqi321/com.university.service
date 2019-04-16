package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发送短信
 */
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
    public WebResult sendSms(@RequestParam(value = "phone")String phone) {
        return smsService.sendSms(phone);
    }

}
