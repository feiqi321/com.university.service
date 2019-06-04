package com.ovft.configure.sys.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *   视频教学
 **/
public class Video implements Serializable {

   private Integer videoId;
   private Integer schoolId;
    /**
     *     标题
     */
   private String title;
    /**
     *  教师
     */
    private String teacher;
    /**
     *  视频介绍
     */
    private String content;
    /**
     *   封面
     */
    private String cover;
    /**
     *  浏览量
     */
    private Integer visits;
    /**
     *  是否免费   0-免费， 1-收费
     */
    private Integer isFree;
    /**
     *  原价
     */
    private BigDecimal price;
    /**
     *  活动价
     */
    private BigDecimal actPrice;
    /**
     * 是否收藏（非数据库字段）0-未收藏  1-已收藏
     */
    private Integer isCollect;

    /**
     * 是否学习（非数据库字段） 0-未学习  1-已学习
     */
    private Integer isLearn;

    public Integer getIsLearn() {
        return isLearn;
    }

    public void setIsLearn(Integer isLearn) {
        this.isLearn = isLearn;
    }

    public Integer getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(Integer isCollect) {
        this.isCollect = isCollect;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getVisits() {
        return visits;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getActPrice() {
        return actPrice;
    }

    public void setActPrice(BigDecimal actPrice) {
        this.actPrice = actPrice;
    }
}
