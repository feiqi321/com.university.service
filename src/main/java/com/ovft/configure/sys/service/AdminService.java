package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Admin;
import com.ovft.configure.sys.vo.PageVo;

public interface AdminService {

    public WebResult createAdmin(Admin admin);

    public WebResult login(Admin admin);

    public WebResult updatePassword(Integer adminId, String oldPassword, String newPassword);

    public WebResult updatePhone(Integer adminId, String newPhone, String securityCode);

    public WebResult findAdmin(Integer adminId, Integer schoolId);

    public WebResult deleteAdmin(Integer adminId);

    public WebResult adminList(PageVo pageVo);

}
