package com.ovft.configure.sys.vo;

public class WithdrawVo {
    private int wid;
    private int uid;
    private int schoolId;
    private String school;
    private String content;
    private int checkin;
    private String userName;
    private Integer userItemId;

    public void setUserItemId(Integer userItemId) {
        this.userItemId = userItemId;
    }

    public Integer getUserItemId() {
        return userItemId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public int getUid() {
        return uid;
    }

    public String getSchool() {
        return school;
    }

    public String getContent() {
        return content;
    }

    public int getCheckin() {
        return checkin;
    }

    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCheckin(int checkin) {
        this.checkin = checkin;
    }
}
