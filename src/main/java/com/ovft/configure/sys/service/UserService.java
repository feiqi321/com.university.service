package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.User;

public interface UserService {
          public WebResult addUser(User user);
          public WebResult findUser(User user);
          public WebResult updatePassword( String phone, String newPassword, String nextPass,String securityCode);
          public WebResult updatePasswordByOldPass(String oldPass,String newPass,String nextPass);
          public WebResult savaInfo(User user);
          public WebResult updatePhone(String oldPhone,String newPhone,String securityCode);
          public WebResult userQuit(String token);
}
