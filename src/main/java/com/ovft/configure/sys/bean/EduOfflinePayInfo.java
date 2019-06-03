package com.ovft.configure.sys.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author
 */
public class EduOfflinePayInfo implements Serializable {
    /**
     * 线下支付id
     */
    private Integer id;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 手机号码
     */
    private String telephone;

    /**
     * 订单列表
     */
    private String listOrder;

    /**
     * 总金额
     */
    private BigDecimal accountMoney;

    /**
     * 支付状态
     */
    private String payStatus;

    /**
     * 下单支付时间
     */
    private Date payUpdatetime;

    /**
     * 学校id
     */
    private Integer schoolId;

    /**
     * 用户id
     */
    private Integer userId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getListOrder() {
        return listOrder;
    }

    public void setListOrder(String listOrder) {
        this.listOrder = listOrder;
    }

    public BigDecimal getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(BigDecimal accountMoney) {
        this.accountMoney = accountMoney;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public Date getPayUpdatetime() {
        return payUpdatetime;
    }

    public void setPayUpdatetime(Date payUpdatetime) {
        this.payUpdatetime = payUpdatetime;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
        EduOfflinePayInfo other = (EduOfflinePayInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
                && (this.getSchoolName() == null ? other.getSchoolName() == null : this.getSchoolName().equals(other.getSchoolName()))
                && (this.getTelephone() == null ? other.getTelephone() == null : this.getTelephone().equals(other.getTelephone()))
                && (this.getListOrder() == null ? other.getListOrder() == null : this.getListOrder().equals(other.getListOrder()))
                && (this.getAccountMoney() == null ? other.getAccountMoney() == null : this.getAccountMoney().equals(other.getAccountMoney()))
                && (this.getPayStatus() == null ? other.getPayStatus() == null : this.getPayStatus().equals(other.getPayStatus()))
                && (this.getPayUpdatetime() == null ? other.getPayUpdatetime() == null : this.getPayUpdatetime().equals(other.getPayUpdatetime()))
                && (this.getSchoolId() == null ? other.getSchoolId() == null : this.getSchoolId().equals(other.getSchoolId()))
                && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getSchoolName() == null) ? 0 : getSchoolName().hashCode());
        result = prime * result + ((getTelephone() == null) ? 0 : getTelephone().hashCode());
        result = prime * result + ((getListOrder() == null) ? 0 : getListOrder().hashCode());
        result = prime * result + ((getAccountMoney() == null) ? 0 : getAccountMoney().hashCode());
        result = prime * result + ((getPayStatus() == null) ? 0 : getPayStatus().hashCode());
        result = prime * result + ((getPayUpdatetime() == null) ? 0 : getPayUpdatetime().hashCode());
        result = prime * result + ((getSchoolId() == null) ? 0 : getSchoolId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userName=").append(userName);
        sb.append(", schoolName=").append(schoolName);
        sb.append(", telephone=").append(telephone);
        sb.append(", listOrder=").append(listOrder);
        sb.append(", accountMoney=").append(accountMoney);
        sb.append(", payStatus=").append(payStatus);
        sb.append(", payUpdatetime=").append(payUpdatetime);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", userId=").append(userId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}