package com.ovft.configure.sys.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 我报名的活动
 **/
public class MyActivities implements Serializable {

    private Integer id;
    private Integer activitiesId;
    private Integer adminId;
    private Integer userId;
    private Integer schoolId;
    private String type;
    private Date registTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }
}
