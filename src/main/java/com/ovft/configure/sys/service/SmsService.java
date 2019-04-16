package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;

public interface SmsService {
    public WebResult sendSms(String phone);
}
