package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;

import java.util.Map;

public interface SmsService {
    public WebResult sendSms(Map<String, String> phone);
}
