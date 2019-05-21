package com.ovft.configure.sys.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/*
     投稿审核实体类
 */
public class Contribute {
    private Integer cid;
    private Integer userId;
    private Integer userItemId;
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createtime;
    private Integer type;
    private String content;
    private  Integer checkin;
    private String image;
    private String audio;
    private String vedio;
    private Integer schoolId;
    private String userName;
    private String schoolName;
    private String rejectReason;     //拒绝原因

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {

        this.rejectReason = rejectReason;
    }

    public String getUserName() {
        return userName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    @Override
    public String toString() {
        return "Contribute{" +
                "cid=" + cid +
                ", userId=" + userId +
                ", userItemId=" + userItemId +
                ", title='" + title + '\'' +
                ", createtime=" + createtime +
                ", type=" + type +
                ", content='" + content + '\'' +
                ", checkin=" + checkin +
                ", image='" + image + '\'' +
                ", audio='" + audio + '\'' +
                ", vedio='" + vedio + '\'' +
                '}';
    }

    public String getVedio() {
        return vedio;
    }

    public void setVedio(String vedio) {
        this.vedio = vedio;
    }


    public Integer getCid() {
        return cid;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getUserItemId() {
        return userItemId;
    }

    public String getTitle() {
        return title;
    }



    public Integer getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public Integer getCheckin() {
        return checkin;
    }

    public String getImage() {
        return image;
    }

    public String getAudio() {
        return audio;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserItemId(Integer userItemId) {
        this.userItemId = userItemId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {

        this.createtime = createtime;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCheckin(Integer checkin) {
        this.checkin = checkin;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }
}
