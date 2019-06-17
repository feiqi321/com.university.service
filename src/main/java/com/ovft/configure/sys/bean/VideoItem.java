package com.ovft.configure.sys.bean;

import java.io.Serializable;

/**
 *   视频教学 细单
 **/
public class VideoItem implements Serializable {

    private Integer itemId;
    /**
     *  视频总单id
     */
    private Integer videoId;
    /**
     *  标题
     */
    private String title;
    /**
     *  视频地址
     */
    private String video;
    /**
     *  时长
     */
    private Integer duration;
    /**
     *  课时
     */
    private Integer count;
    /**
     *  是否试听    0-非试听 1-试听
     */
    private Integer isTest;

    /**
     *  是否已学习  0-未学习  1-已学习
     */
    private Integer isLearn;

    /**
     * 总标题
     */
    private String totalTitle;

    public Integer getIsLearn() {
        return isLearn;
    }

    public String getTotalTitle() {
        return totalTitle;
    }

    public void setTotalTitle(String totalTitle) {
        this.totalTitle = totalTitle;
    }

    public void setIsLearn(Integer isLearn) {
        this.isLearn = isLearn;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getIsTest() {
        return isTest;
    }

    public void setIsTest(Integer isTest) {
        this.isTest = isTest;
    }
}
