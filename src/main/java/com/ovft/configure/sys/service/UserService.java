package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.User;
import com.ovft.configure.sys.vo.PhoneVo;

public interface UserService {
    public WebResult addUser(User user);

    public WebResult findUser(User user);

    public WebResult updatePassword(PhoneVo phoneVo);

    public WebResult updatePasswordByOldPass(PhoneVo phoneVo);

    public WebResult savaInfo(User user);

    public WebResult updatePhone(PhoneVo phoneVo);

    public WebResult userQuit(String token);

    public WebResult selectInfo(User user);

    //查找头像地址
    public String queryAllAddress(Integer userId);

    //上传头像
    public void updateAddress(User user);
}
