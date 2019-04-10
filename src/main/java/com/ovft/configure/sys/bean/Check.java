package com.ovft.configure.sys.bean;

import java.util.Date;

/**
 * @ClassName Check
 * @Author zqx
 * @Date 2019/4/10 11:43
 * @Version 1.0
 **/
public class Check {

    private Integer checkId;
    private Integer userId;

    /**
     * 打卡开始时间
     */
    private Date checkStartTime;

    /**
     * 打卡结束时间
     */
    private Date checkEndTime;

    /**
     * 是否打卡
     */
    private Integer isCheck;

    public Integer getCheckId() {
        return checkId;
    }

    public void setCheckId(Integer checkId) {
        this.checkId = checkId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCheckStartTime() {
        return checkStartTime;
    }

    public void setCheckStartTime(Date checkStartTime) {
        this.checkStartTime = checkStartTime;
    }

    public Date getCheckEndTime() {
        return checkEndTime;
    }

    public void setCheckEndTime(Date checkEndTime) {
        this.checkEndTime = checkEndTime;
    }

    public Integer getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }
}
