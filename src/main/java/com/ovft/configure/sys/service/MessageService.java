package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;

public interface MessageService {

    public WebResult findMessage(Integer userId, Integer schoolId, Integer pageNum, Integer pageSize);
}
