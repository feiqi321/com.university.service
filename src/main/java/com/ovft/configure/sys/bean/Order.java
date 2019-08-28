package com.ovft.configure.sys.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author
 */
public class Order implements Serializable {
    /**
     * 表id自增
     */
    private Integer id;

    /**
     * 订单编号
     */
    private String orderSn;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 订单的支付状态5.已付款
     */
    private Integer orderStatus;

    /**
     * 订单创建时间
     */
    private Date createTime;

    /**
     * 订单失效时间
     */
    private Date expireTime;

    /**
     * 付款时间
     */
    private Date paymentTime;

    /**
     * 总金额
     */
    private BigDecimal totalAmount;

    /**
     * 付款方式
     */
    private String paymentWay;

    /**
     * 订单交易编号
     */
    private String outTradeNo;

    /**
     * 订单描述
     */
    private String tradeBody;

    /**
     * 图片路径
     */
    private String imgUrl;

    /**
     * 收货人
     */
    private String consignee;

    /**
     * 学校id
     */
    private String schoolId;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 地址
     */
    private String address;

    /**
     * 发送方式
     */
    private String sendType;

    /**
     * 邮费
     */
    private BigDecimal tosendPrice;

    /**
     * 备注
     */
    private String remark;

    /**
     * 资源的类型1待付款,2已经取消，3.待收货,4已完成
     */
    private String resourceStatus;

    /**
     * 回调返回路径
     */
    private String returnurl;

    /**
     * 资源的状态:1.表示教材购买 2.其他
     */
    private String resourceType;



    private static final long serialVersionUID = 1L;

    public String getReturnurl() {
        return returnurl;
    }

    public void setReturnurl(String returnurl) {
        this.returnurl = returnurl;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(String paymentWay) {
        this.paymentWay = paymentWay;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTradeBody() {
        return tradeBody;
    }

    public void setTradeBody(String tradeBody) {
        this.tradeBody = tradeBody;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public BigDecimal getTosendPrice() {
        return tosendPrice;
    }

    public void setTosendPrice(BigDecimal tosendPrice) {
        this.tosendPrice = tosendPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getResourceStatus() {
        return resourceStatus;
    }

    public void setResourceStatus(String resourceStatus) {
        this.resourceStatus = resourceStatus;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
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
        Order other = (Order) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getOrderSn() == null ? other.getOrderSn() == null : this.getOrderSn().equals(other.getOrderSn()))
                && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
                && (this.getOrderStatus() == null ? other.getOrderStatus() == null : this.getOrderStatus().equals(other.getOrderStatus()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getExpireTime() == null ? other.getExpireTime() == null : this.getExpireTime().equals(other.getExpireTime()))
                && (this.getPaymentTime() == null ? other.getPaymentTime() == null : this.getPaymentTime().equals(other.getPaymentTime()))
                && (this.getTotalAmount() == null ? other.getTotalAmount() == null : this.getTotalAmount().equals(other.getTotalAmount()))
                && (this.getPaymentWay() == null ? other.getPaymentWay() == null : this.getPaymentWay().equals(other.getPaymentWay()))
                && (this.getOutTradeNo() == null ? other.getOutTradeNo() == null : this.getOutTradeNo().equals(other.getOutTradeNo()))
                && (this.getTradeBody() == null ? other.getTradeBody() == null : this.getTradeBody().equals(other.getTradeBody()))
                && (this.getImgUrl() == null ? other.getImgUrl() == null : this.getImgUrl().equals(other.getImgUrl()))
                && (this.getConsignee() == null ? other.getConsignee() == null : this.getConsignee().equals(other.getConsignee()))
                && (this.getSchoolId() == null ? other.getSchoolId() == null : this.getSchoolId().equals(other.getSchoolId()))
                && (this.getTelephone() == null ? other.getTelephone() == null : this.getTelephone().equals(other.getTelephone()))
                && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
                && (this.getSendType() == null ? other.getSendType() == null : this.getSendType().equals(other.getSendType()))
                && (this.getTosendPrice() == null ? other.getTosendPrice() == null : this.getTosendPrice().equals(other.getTosendPrice()))
                && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
                && (this.getResourceStatus() == null ? other.getResourceStatus() == null : this.getResourceStatus().equals(other.getResourceStatus()))
                && (this.getResourceType() == null ? other.getResourceType() == null : this.getResourceType().equals(other.getResourceType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderSn() == null) ? 0 : getOrderSn().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getOrderStatus() == null) ? 0 : getOrderStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getExpireTime() == null) ? 0 : getExpireTime().hashCode());
        result = prime * result + ((getPaymentTime() == null) ? 0 : getPaymentTime().hashCode());
        result = prime * result + ((getTotalAmount() == null) ? 0 : getTotalAmount().hashCode());
        result = prime * result + ((getPaymentWay() == null) ? 0 : getPaymentWay().hashCode());
        result = prime * result + ((getOutTradeNo() == null) ? 0 : getOutTradeNo().hashCode());
        result = prime * result + ((getTradeBody() == null) ? 0 : getTradeBody().hashCode());
        result = prime * result + ((getImgUrl() == null) ? 0 : getImgUrl().hashCode());
        result = prime * result + ((getConsignee() == null) ? 0 : getConsignee().hashCode());
        result = prime * result + ((getSchoolId() == null) ? 0 : getSchoolId().hashCode());
        result = prime * result + ((getTelephone() == null) ? 0 : getTelephone().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getSendType() == null) ? 0 : getSendType().hashCode());
        result = prime * result + ((getTosendPrice() == null) ? 0 : getTosendPrice().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getResourceStatus() == null) ? 0 : getResourceStatus().hashCode());
        result = prime * result + ((getResourceType() == null) ? 0 : getResourceType().hashCode());
        return result;
    }

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(getClass().getSimpleName());
//        sb.append(" [");
//        sb.append("Hash = ").append(hashCode());
//        sb.append(", id=").append(id);
//        sb.append(", orderSn=").append(orderSn);
//        sb.append(", userId=").append(userId);
//        sb.append(", orderStatus=").append(orderStatus);
//        sb.append(", createTime=").append(createTime);
//        sb.append(", expireTime=").append(expireTime);
//        sb.append(", paymentTime=").append(paymentTime);
//        sb.append(", totalAmount=").append(totalAmount);
//        sb.append(", paymentWay=").append(paymentWay);
//        sb.append(", outTradeNo=").append(outTradeNo);
//        sb.append(", tradeBody=").append(tradeBody);
//        sb.append(", imgUrl=").append(imgUrl);
//        sb.append(", consignee=").append(consignee);
//        sb.append(", schoolId=").append(schoolId);
//        sb.append(", telephone=").append(telephone);
//        sb.append(", address=").append(address);
//        sb.append(", sendType=").append(sendType);
//        sb.append(", tosendPrice=").append(tosendPrice);
//        sb.append(", remark=").append(remark);
//        sb.append(", resourceStatus=").append(resourceStatus);
//        sb.append(", resourceType=").append(resourceType);
//        sb.append(", serialVersionUID=").append(serialVersionUID);
//        sb.append("]");
//        return sb.toString();
//    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderSn='" + orderSn + '\'' +
                ", userId=" + userId +
                ", orderStatus=" + orderStatus +
                ", createTime=" + createTime +
                ", expireTime=" + expireTime +
                ", paymentTime=" + paymentTime +
                ", totalAmount=" + totalAmount +
                ", paymentWay='" + paymentWay + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", tradeBody='" + tradeBody + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", consignee='" + consignee + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", sendType='" + sendType + '\'' +
                ", tosendPrice=" + tosendPrice +
                ", remark='" + remark + '\'' +
                ", resourceStatus='" + resourceStatus + '\'' +
                ", returnurl='" + returnurl + '\'' +
                ", resourceType='" + resourceType + '\'' +
                '}';
    }
}