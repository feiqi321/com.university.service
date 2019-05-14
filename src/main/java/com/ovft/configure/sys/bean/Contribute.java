package com.ovft.configure.sys.bean;

import java.util.Date;

/*
     投稿审核实体类
 */
public class Contribute {
    private Integer cid;
    private Integer userId;
    private Integer userItemId;
    private String title;
    private Date createtime;
    private Integer type;
    private String content;
    private  Integer checkin;
    private String image;
    private String audio;
    private String vedio;


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
