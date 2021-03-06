package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Admin;
import com.ovft.configure.sys.vo.AdminVo;
import com.ovft.configure.sys.vo.PageVo;

public interface AdminService {


    public WebResult login(Admin admin);

    /*public WebResult updatePassword(Integer adminId, String oldPassword, String newPassword);

    public WebResult updatePhone(Integer adminId, String newPhone, String securityCode);*/

    public WebResult findAdmin(Integer adminId, Integer schoolId);

    public WebResult deleteAdmin(Integer adminId, Integer schoolId);

    public WebResult adminList(PageVo pageVo);

    //添加 管理员(带角色)
    public WebResult createAdminRole(AdminVo adminVo);

    public WebResult findSchoolByPhone(String phone);
}
