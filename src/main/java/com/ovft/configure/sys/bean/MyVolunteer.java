package com.ovft.configure.sys.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *   我的志愿活动
 **/
public class MyVolunteer implements Serializable {

    private Integer id;
    private Integer userId;

    /*
     *   我的志愿活动id
     */
    private Integer volunteerId;
    /**
     * 报名日期
     */
    private Date registDate;

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

    public Integer getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(Integer volunteerId) {
        this.volunteerId = volunteerId;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }
}
