package com.ovft.configure.sys.vo;

import com.ovft.configure.sys.bean.Admin;

/**
 *
 * @author vvtxw
 * @create 2019-04-14 7:53
 */
public class AdminVo extends Admin {
    private String schoolName;
    private Integer[] roleIds;

    private Integer sex;
    // 简历（简单经历）
    private String resume;
    //出版专著（教材）
    private String books;
    //评价记录
    private String comments;

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getBooks() {
        return books;
    }

    public void setBooks(String books) {
        this.books = books;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Integer[] roleIds) {
        this.roleIds = roleIds;
    }

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
        this.setPost(admin.getPost());
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
