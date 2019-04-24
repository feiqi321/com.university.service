package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.User;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    public WebResult addUser(User user);
    public WebResult findUser(User user);
    public WebResult updatePassword( String phone, String newPassword, String nextPass,String securityCode);
    public WebResult updatePasswordByOldPass(String oldPass,String newPass,String nextPass);
    public WebResult savaInfo(User user);
    public WebResult updatePhone(String oldPhone,String newPhone,String securityCode);
    public WebResult userQuit(String token);
    public WebResult selectInfo( User user);
}
