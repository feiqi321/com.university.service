package com.ovft.configure.sys.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 
 */
public class EduOfflineOrderitem implements Serializable {
    /**
     * 订单详情表id
     */
    private Integer id;

    /**
     * 课程id
     */
    private Integer userId;

    /**
     * 课程名称
     */
    private String couserName;

    /**
     * 课程价格
     */
    private BigDecimal couserPrice;

    /**
     * 学校id
     */
    private Integer schoolId;

    /**
     * 支付状态
     */
    private String payStatus;

    /**
     * 课程id
     */
    private Integer courseId;

    private static final long serialVersionUID = 1L;

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

    public String getCouserName() {
        return couserName;
    }

    public void setCouserName(String couserName) {
        this.couserName = couserName;
    }

    public BigDecimal getCouserPrice() {
        return couserPrice;
    }

    public void setCouserPrice(BigDecimal couserPrice) {
        this.couserPrice = couserPrice;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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
        EduOfflineOrderitem other = (EduOfflineOrderitem) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCouserName() == null ? other.getCouserName() == null : this.getCouserName().equals(other.getCouserName()))
            && (this.getCouserPrice() == null ? other.getCouserPrice() == null : this.getCouserPrice().equals(other.getCouserPrice()))
            && (this.getSchoolId() == null ? other.getSchoolId() == null : this.getSchoolId().equals(other.getSchoolId()))
            && (this.getPayStatus() == null ? other.getPayStatus() == null : this.getPayStatus().equals(other.getPayStatus()))
            && (this.getCourseId() == null ? other.getCourseId() == null : this.getCourseId().equals(other.getCourseId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCouserName() == null) ? 0 : getCouserName().hashCode());
        result = prime * result + ((getCouserPrice() == null) ? 0 : getCouserPrice().hashCode());
        result = prime * result + ((getSchoolId() == null) ? 0 : getSchoolId().hashCode());
        result = prime * result + ((getPayStatus() == null) ? 0 : getPayStatus().hashCode());
        result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", couserName=").append(couserName);
        sb.append(", couserPrice=").append(couserPrice);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", payStatus=").append(payStatus);
        sb.append(", courseId=").append(courseId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}