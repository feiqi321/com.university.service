package com.ovft.configure.sys.vo;

import com.ovft.configure.sys.bean.Admin;

/**
 * 课程的扩展类
 *
 * @author vvtxw
 * @create 2019-04-14 7:53
 */
public class AdminVo extends Admin {
    private String schoolName;

    public AdminVo() {

    }

    public AdminVo(Admin admin) {
        this.setAdminId(admin.getAdminId());
        this.setName(admin.getName());
        this.setPassword(admin.getPassword());
        this.setPhone(admin.getPhone());
        this.setImage(admin.getImage());
        this.setRole(admin.getRole());
        this.setSchoolId(admin.getSchoolId());
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
