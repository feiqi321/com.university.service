package com.ovft.configure.sys.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author vvtxw
 * @create 2019-05-28 12:15
 */
public class SubmitOrderVos {
    private String userName;
    private String address;
    private String phone;
    private List<OrderDetailVo> orderItemsVo;
    private String sendType;
    private BigDecimal toSendPrice;
    private String remark;
    private BigDecimal totalMoney;


    /**
     * 订单编号
     */
    private String orderSn;

    /**
     * 订单创建时间
     */
    private Date createTime;
    /**
     * 付款方式
     */
    private String paymentWay;

    /**
     * 付款时间
     */
    private Date paymentTime;


    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(String paymentWay) {
        this.paymentWay = paymentWay;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

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

    public List<OrderDetailVo> getOrderItemsVo() {
        return orderItemsVo;
    }

    public void setOrderItemsVo(List<OrderDetailVo> orderItemsVo) {
        this.orderItemsVo = orderItemsVo;
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
