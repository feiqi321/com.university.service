package com.ovft.configure.sys.service.impl;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.User;
import com.ovft.configure.sys.dao.UserMapper;
import com.ovft.configure.sys.service.SmsService;
import com.ovft.configure.sys.utils.RedisUtil;
import com.ovft.configure.sys.utils.SecurityUtils;
import com.ovft.configure.sys.utils.Sms253Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Random;

/**
 * @ClassName SmsServiceImpl
 * @Author zqx
 * @Date 2019/4/15 16:01
 * @Version 1.0
 **/
@Service
public class SmsServiceImpl implements SmsService {

    private static final Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);

    @Autowired
    private RedisUtil redisUtil;
    @Resource
    private UserMapper userMapper;
    @Override
    public WebResult sendSms(Map<String, String> phoneMap) {
        String phone = phoneMap.get("phone");

//        //验证手机号是否被注册
//        User finduserbyphone = userMapper.findUserByPhone2(phone);
//        if (finduserbyphone != null) {
//            return new WebResult("600", "该用户已注册");
//        }
        //手机号格式验证
        if (!SecurityUtils.securityPhone(phone)) {
            return new WebResult("400", "请输入正确手机号", "");
        }

        //生成6位数短信验证码
        String securityCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String key = "sendSms-" + phone;

        //设置两分钟内不能重发
        long expire = redisUtil.getExpire(key);
        if (expire > 3 * 60) {
            return new WebResult("400", "您操作太频繁", "");
        }

        boolean isSend = Sms253Util.sendSms(phone, securityCode);
        if (!isSend) {
            return new WebResult("400", "短信发送失败", "");
        }
//        短信验证码存入redis中
        redisUtil.set(key, securityCode, 5 * 60);

        return new WebResult("200", "短信发送成功", "");
    }


}
