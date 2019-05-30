package com.ovft.configure.sys.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 */
public class EduOfflineAddresstime implements Serializable {
    /**
     * 缴费id
     */
    private Integer id;

    /**
     * 缴费地点
     */
    private String paymentAddress;

    /**
     * 缴费开始时间
     */
    private Date paymentStarttime;

    /**
     * 缴费结束时间
     */
    private Date paymentEndtime;

    /**
     * 学校id
     */
    private Integer schoolId;

    /**
     * 学校姓名
     */
    private String schoolName;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaymentAddress() {
        return paymentAddress;
    }

    public void setPaymentAddress(String paymentAddress) {
        this.paymentAddress = paymentAddress;
    }

    public Date getPaymentStarttime() {
        return paymentStarttime;
    }

    public void setPaymentStarttime(Date paymentStarttime) {
        this.paymentStarttime = paymentStarttime;
    }

    public Date getPaymentEndtime() {
        return paymentEndtime;
    }

    public void setPaymentEndtime(Date paymentEndtime) {
        this.paymentEndtime = paymentEndtime;
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
        EduOfflineAddresstime other = (EduOfflineAddresstime) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getPaymentAddress() == null ? other.getPaymentAddress() == null : this.getPaymentAddress().equals(other.getPaymentAddress()))
                && (this.getPaymentStarttime() == null ? other.getPaymentStarttime() == null : this.getPaymentStarttime().equals(other.getPaymentStarttime()))
                && (this.getPaymentEndtime() == null ? other.getPaymentEndtime() == null : this.getPaymentEndtime().equals(other.getPaymentEndtime()))
                && (this.getSchoolId() == null ? other.getSchoolId() == null : this.getSchoolId().equals(other.getSchoolId()))
                && (this.getSchoolName() == null ? other.getSchoolName() == null : this.getSchoolName().equals(other.getSchoolName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPaymentAddress() == null) ? 0 : getPaymentAddress().hashCode());
        result = prime * result + ((getPaymentStarttime() == null) ? 0 : getPaymentStarttime().hashCode());
        result = prime * result + ((getPaymentEndtime() == null) ? 0 : getPaymentEndtime().hashCode());
        result = prime * result + ((getSchoolId() == null) ? 0 : getSchoolId().hashCode());
        result = prime * result + ((getSchoolName() == null) ? 0 : getSchoolName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", paymentAddress=").append(paymentAddress);
        sb.append(", paymentStarttime=").append(paymentStarttime);
        sb.append(", paymentEndtime=").append(paymentEndtime);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", schoolName=").append(schoolName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}