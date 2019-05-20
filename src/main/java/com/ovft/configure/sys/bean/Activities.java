package com.ovft.configure.sys.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 校园活动
 **/
public class Activities implements Serializable {

    private Integer activitiesId;
    private Integer adminId;
    private Integer userId;
    private Integer schoolId;
    private String schoolName;

    /**
     * 活动类型  1-联盟活动  2-校园活动  3-志愿活动
     */
    private String type;

    /**
     * 活动标题
     */
    private String title;

    /**
     * 活动内容
     */
    private String content;

    /**
     * 活动图片
     */
    private String image;

    /**
     * 场景
     */
    private String scenario;

    /**
     * 参与人数
     */
    private Integer number;

    private String place;

    /**
     * 活动开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    /**
     *活动结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    /**
     * 报名开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date registStartTime;
    /**
     * 报名结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date registEndTime;

    private Integer startAge;
    private Integer endAge;

    /**
     *  人员类别   管理员,教师,学员
     */
    private String category;

    /**
     * 已报名人人数
     */
    private Integer registNum;

    public Integer getRegistNum() {
        return registNum;
    }

    public void setRegistNum(Integer registNum) {
        this.registNum = registNum;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Date getRegistStartTime() {
        return registStartTime;
    }

    public void setRegistStartTime(Date registStartTime) {
        this.registStartTime = registStartTime;
    }

    public Date getRegistEndTime() {
        return registEndTime;
    }

    public void setRegistEndTime(Date registEndTime) {
        this.registEndTime = registEndTime;
    }

    public Integer getStartAge() {
        return startAge;
    }

    public void setStartAge(Integer startAge) {
        this.startAge = startAge;
    }

    public Integer getEndAge() {
        return endAge;
    }

    public void setEndAge(Integer endAge) {
        this.endAge = endAge;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getActivitiesId() {
        return activitiesId;
    }

    public void setActivitiesId(Integer activitiesId) {
        this.activitiesId = activitiesId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

}
