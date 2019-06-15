package com.ovft.configure.sys.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 
 */
public class EduSettlementOrderDetails implements Serializable {
    /**
     * 结算订单明细表
     */
    private Integer id;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 交易时间开始
     */
    private Date tradingTime;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 支付类型
     */
    private String paymentType;

    /**
     * 结算状态  1 2 3
     */
    private Integer settlementStatus;

    /**
     * 手机号码
     */
    private String telephone;

    /**
     * 学校id
     */
    private Integer schoolId;

    /**
     * 学校姓名
     */
    private String schoolName;

    /**
     * 交易金额
     */
    private BigDecimal transactionAmount;

    /**
     * 结算金额
     */
    private BigDecimal settlementAmount;

    /**
     * 课程已支付记录id
     */
    private Integer payrecordId;

    /**
     * 教材订单支付记录id
     */
    private Integer orderId;

    /**
     * 视频已支付订单id
     */
    private Integer videoId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getTradingTime() {
        return tradingTime;
    }

    public void setTradingTime(Date tradingTime) {
        this.tradingTime = tradingTime;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getSettlementStatus() {
        return settlementStatus;
    }

    public void setSettlementStatus(Integer settlementStatus) {
        this.settlementStatus = settlementStatus;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public BigDecimal getSettlementAmount() {
        return settlementAmount;
    }

    public void setSettlementAmount(BigDecimal settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    public Integer getPayrecordId() {
        return payrecordId;
    }

    public void setPayrecordId(Integer payrecordId) {
        this.payrecordId = payrecordId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        EduSettlementOrderDetails other = (EduSettlementOrderDetails) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderNumber() == null ? other.getOrderNumber() == null : this.getOrderNumber().equals(other.getOrderNumber()))
            && (this.getTradingTime() == null ? other.getTradingTime() == null : this.getTradingTime().equals(other.getTradingTime()))
            && (this.getResourceName() == null ? other.getResourceName() == null : this.getResourceName().equals(other.getResourceName()))
            && (this.getPaymentType() == null ? other.getPaymentType() == null : this.getPaymentType().equals(other.getPaymentType()))
            && (this.getSettlementStatus() == null ? other.getSettlementStatus() == null : this.getSettlementStatus().equals(other.getSettlementStatus()))
            && (this.getTelephone() == null ? other.getTelephone() == null : this.getTelephone().equals(other.getTelephone()))
            && (this.getSchoolId() == null ? other.getSchoolId() == null : this.getSchoolId().equals(other.getSchoolId()))
            && (this.getSchoolName() == null ? other.getSchoolName() == null : this.getSchoolName().equals(other.getSchoolName()))
            && (this.getTransactionAmount() == null ? other.getTransactionAmount() == null : this.getTransactionAmount().equals(other.getTransactionAmount()))
            && (this.getSettlementAmount() == null ? other.getSettlementAmount() == null : this.getSettlementAmount().equals(other.getSettlementAmount()))
            && (this.getPayrecordId() == null ? other.getPayrecordId() == null : this.getPayrecordId().equals(other.getPayrecordId()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getVideoId() == null ? other.getVideoId() == null : this.getVideoId().equals(other.getVideoId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderNumber() == null) ? 0 : getOrderNumber().hashCode());
        result = prime * result + ((getTradingTime() == null) ? 0 : getTradingTime().hashCode());
        result = prime * result + ((getResourceName() == null) ? 0 : getResourceName().hashCode());
        result = prime * result + ((getPaymentType() == null) ? 0 : getPaymentType().hashCode());
        result = prime * result + ((getSettlementStatus() == null) ? 0 : getSettlementStatus().hashCode());
        result = prime * result + ((getTelephone() == null) ? 0 : getTelephone().hashCode());
        result = prime * result + ((getSchoolId() == null) ? 0 : getSchoolId().hashCode());
        result = prime * result + ((getSchoolName() == null) ? 0 : getSchoolName().hashCode());
        result = prime * result + ((getTransactionAmount() == null) ? 0 : getTransactionAmount().hashCode());
        result = prime * result + ((getSettlementAmount() == null) ? 0 : getSettlementAmount().hashCode());
        result = prime * result + ((getPayrecordId() == null) ? 0 : getPayrecordId().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getVideoId() == null) ? 0 : getVideoId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderNumber=").append(orderNumber);
        sb.append(", tradingTime=").append(tradingTime);
        sb.append(", resourceName=").append(resourceName);
        sb.append(", paymentType=").append(paymentType);
        sb.append(", settlementStatus=").append(settlementStatus);
        sb.append(", telephone=").append(telephone);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", schoolName=").append(schoolName);
        sb.append(", transactionAmount=").append(transactionAmount);
        sb.append(", settlementAmount=").append(settlementAmount);
        sb.append(", payrecordId=").append(payrecordId);
        sb.append(", orderId=").append(orderId);
        sb.append(", videoId=").append(videoId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}