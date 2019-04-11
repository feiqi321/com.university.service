package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Admin;

public interface AdminService {

    public WebResult createAdmin(Admin admin);

}
