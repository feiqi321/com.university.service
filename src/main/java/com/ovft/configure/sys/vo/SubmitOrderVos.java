package com.ovft.configure.sys.vo;

import com.alipay.api.domain.OrderItem;
import com.ovft.configure.sys.bean.OrderDetail;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author vvtxw
 * @create 2019-05-28 12:15
 */
public class SubmitOrderVos {
    private String userName;
    private String address;
    private String phone;
    private List<OrderDetail> orderItems;
    private String sendType;
    private BigDecimal toSendPrice;
    private String remark;
    private BigDecimal totalMoney;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<OrderDetail> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderDetail> orderItems) {
        this.orderItems = orderItems;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public BigDecimal getToSendPrice() {
        return toSendPrice;
    }

    public void setToSendPrice(BigDecimal toSendPrice) {
        this.toSendPrice = toSendPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }
}
