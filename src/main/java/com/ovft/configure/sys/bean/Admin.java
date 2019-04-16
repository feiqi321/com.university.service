package com.ovft.configure.sys.bean;

/**
 * 管理员
 * @ClassName Admin
 * @Author zqx
 * @Date 2019/4/10 11:39
 * @Version 1.0
 **/
public class Admin {

    private Integer adminId;
    private String name;
    private String password;
    private String phone;

    /**
     * 头像
     */
    private String image;

    /**
     * 0-超级管理员 1-管理员 2-教师
     */
    private Integer role;

    private Integer schoolId;

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
