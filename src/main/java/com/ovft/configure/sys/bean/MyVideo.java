package com.ovft.configure.sys.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *   我的视频
 **/
public class MyVideo implements Serializable {

    private Integer id;
    private Integer userId;

    /*
     *   我的视频id
     */
    private Integer videoId;
    /**
     * 添加日期
     */
    private Date date;

    /**
     * 已学习视频的ID
     */
    private String items;

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
