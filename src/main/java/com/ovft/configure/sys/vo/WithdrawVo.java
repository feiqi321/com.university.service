package com.ovft.configure.sys.vo;

public class WithdrawVo {
    private int wid;
    private int uid;
    private String school;
    private String content;
    private int checkin;

    @Override
    public String toString() {
        return "WithdrawVo{" +
                "wid=" + wid +
                ", uid=" + uid +
                ", school='" + school + '\'' +
                ", content='" + content + '\'' +
                ", checkin=" + checkin +
                '}';
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
