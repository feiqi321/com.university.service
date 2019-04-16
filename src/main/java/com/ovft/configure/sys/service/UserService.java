package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.User;

public interface UserService {
          public WebResult addUser(User user);
          public WebResult findUser(User user);
          public WebResult updatePassword( String phone, String newPassword, String nextpass);
          public WebResult savaInfo(User user);
}
