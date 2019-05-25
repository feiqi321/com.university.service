package com.ovft.configure.sys.vo;

import com.ovft.configure.sys.bean.EduOfflineOrderitem;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author vvtxw
 * @create 2019-05-24 11:42
 */
public class QueryOffLineVos {
    private String schoolName;
    private String userName;
    private String phone;
    private List<EduOfflineOrderitem> orderitems;
    private BigDecimal accountAllMoney;
    private String payStatus;

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

    public List<EduOfflineOrderitem> getOrderitems() {
        return orderitems;
    }

    public void setOrderitems(List<EduOfflineOrderitem> orderitems) {
        this.orderitems = orderitems;
    }

    public BigDecimal getAccountAllMoney() {
        return accountAllMoney;
    }

    public void setAccountAllMoney(BigDecimal accountAllMoney) {
        this.accountAllMoney = accountAllMoney;
    }
}
