package com.ovft.configure.sys.vo;

import com.ovft.configure.sys.bean.EduOfflineNum;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author vvtxw
 * @create 2019-05-24 11:42
 */
public class QueryOffLineVos {
    private Integer id;
    private Integer userId;
    private String schoolName;
    private String userName;
    private String phone;
    private List<EduOfflineNum> offlineNums;
    private BigDecimal accountAllMoney;
    private String payStatus;
    private Date updateTime;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<EduOfflineNum> getOfflineNums() {
        return offlineNums;
    }

    public void setOfflineNums(List<EduOfflineNum> offlineNums) {
        this.offlineNums = offlineNums;
    }

    public BigDecimal getAccountAllMoney() {
        return accountAllMoney;
    }

    public void setAccountAllMoney(BigDecimal accountAllMoney) {
        this.accountAllMoney = accountAllMoney;
    }
}
