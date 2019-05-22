package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;

public interface ApproveService {

    //每天只能点赞一次
    public WebResult approve(Integer userId, Integer typeId, Integer type);

}
