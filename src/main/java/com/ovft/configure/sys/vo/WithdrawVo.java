package com.ovft.configure.sys.vo;

public class WithdrawVo {
    private int wid;
    private int uid;
    private String school;
    private String content;
    private int checkin;

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
