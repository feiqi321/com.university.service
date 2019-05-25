package com.ovft.configure.sys.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 
 */
public class EduOfflineOrder implements Serializable {
    /**
     * 订单id
     */
    private Integer id;

    /**
     * 学校id
     */
    private Integer schoolId;

    /**
     * 课程id
     */
    private Integer courseId;

    /**
     * 学员姓名
     */
    private Integer userId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 课程价格
     */
    private BigDecimal cousePrice;

    /**
     * 课程老师
     */
    private String courseTeacher;

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 学员名称
     */
    private String userName;

    /**
     * 学员电话
     */
    private String phone;

    /**
     * 支付状态
     */
    private String payStatus;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public BigDecimal getCousePrice() {
        return cousePrice;
    }

    public void setCousePrice(BigDecimal cousePrice) {
        this.cousePrice = cousePrice;
    }

    public String getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(String courseTeacher) {
        this.courseTeacher = courseTeacher;
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

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
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
        EduOfflineOrder other = (EduOfflineOrder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSchoolId() == null ? other.getSchoolId() == null : this.getSchoolId().equals(other.getSchoolId()))
            && (this.getCourseId() == null ? other.getCourseId() == null : this.getCourseId().equals(other.getCourseId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCourseName() == null ? other.getCourseName() == null : this.getCourseName().equals(other.getCourseName()))
            && (this.getCousePrice() == null ? other.getCousePrice() == null : this.getCousePrice().equals(other.getCousePrice()))
            && (this.getCourseTeacher() == null ? other.getCourseTeacher() == null : this.getCourseTeacher().equals(other.getCourseTeacher()))
            && (this.getSchoolName() == null ? other.getSchoolName() == null : this.getSchoolName().equals(other.getSchoolName()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getPayStatus() == null ? other.getPayStatus() == null : this.getPayStatus().equals(other.getPayStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSchoolId() == null) ? 0 : getSchoolId().hashCode());
        result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCourseName() == null) ? 0 : getCourseName().hashCode());
        result = prime * result + ((getCousePrice() == null) ? 0 : getCousePrice().hashCode());
        result = prime * result + ((getCourseTeacher() == null) ? 0 : getCourseTeacher().hashCode());
        result = prime * result + ((getSchoolName() == null) ? 0 : getSchoolName().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getPayStatus() == null) ? 0 : getPayStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", courseId=").append(courseId);
        sb.append(", userId=").append(userId);
        sb.append(", courseName=").append(courseName);
        sb.append(", cousePrice=").append(cousePrice);
        sb.append(", courseTeacher=").append(courseTeacher);
        sb.append(", schoolName=").append(schoolName);
        sb.append(", userName=").append(userName);
        sb.append(", phone=").append(phone);
        sb.append(", payStatus=").append(payStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}