package com.ovft.configure.sys.bean;

import java.io.Serializable;

/**
 * @author 
 */
public class EduPayrecord implements Serializable {
    /**
     * 线上已支付id
     */
    private Integer id;

    /**
     * 学校id
     */
    private String schoolId;

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 老师名称
     */
    private String courseTeacher;

    /**
     * 上课地点
     */
    private String courseAddress;

    /**
     * 上课时间
     */
    private String courseStarttime;

    /**
     * 具体时间开始
     */
    private String startTime;

    /**
     * 具体时间开始
     */
    private String endTime;

    /**
     * 支付状态
     */
    private String payStatus;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 课程id
     */
    private Integer courseId;

    /**
     * 是否被删除
     */
    private Integer isDelete;

    /**
     * 订单id
     */
    private Integer orderId;

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(String courseTeacher) {
        this.courseTeacher = courseTeacher;
    }

    public String getCourseAddress() {
        return courseAddress;
    }

    public void setCourseAddress(String courseAddress) {
        this.courseAddress = courseAddress;
    }

    public String getCourseStarttime() {
        return courseStarttime;
    }

    public void setCourseStarttime(String courseStarttime) {
        this.courseStarttime = courseStarttime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
        EduPayrecord other = (EduPayrecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSchoolId() == null ? other.getSchoolId() == null : this.getSchoolId().equals(other.getSchoolId()))
            && (this.getSchoolName() == null ? other.getSchoolName() == null : this.getSchoolName().equals(other.getSchoolName()))
            && (this.getCourseName() == null ? other.getCourseName() == null : this.getCourseName().equals(other.getCourseName()))
            && (this.getCourseTeacher() == null ? other.getCourseTeacher() == null : this.getCourseTeacher().equals(other.getCourseTeacher()))
            && (this.getCourseAddress() == null ? other.getCourseAddress() == null : this.getCourseAddress().equals(other.getCourseAddress()))
            && (this.getCourseStarttime() == null ? other.getCourseStarttime() == null : this.getCourseStarttime().equals(other.getCourseStarttime()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getPayStatus() == null ? other.getPayStatus() == null : this.getPayStatus().equals(other.getPayStatus()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCourseId() == null ? other.getCourseId() == null : this.getCourseId().equals(other.getCourseId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSchoolId() == null) ? 0 : getSchoolId().hashCode());
        result = prime * result + ((getSchoolName() == null) ? 0 : getSchoolName().hashCode());
        result = prime * result + ((getCourseName() == null) ? 0 : getCourseName().hashCode());
        result = prime * result + ((getCourseTeacher() == null) ? 0 : getCourseTeacher().hashCode());
        result = prime * result + ((getCourseAddress() == null) ? 0 : getCourseAddress().hashCode());
        result = prime * result + ((getCourseStarttime() == null) ? 0 : getCourseStarttime().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getPayStatus() == null) ? 0 : getPayStatus().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
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
        sb.append(", schoolId=").append(schoolId);
        sb.append(", schoolName=").append(schoolName);
        sb.append(", courseName=").append(courseName);
        sb.append(", courseTeacher=").append(courseTeacher);
        sb.append(", courseAddress=").append(courseAddress);
        sb.append(", courseStarttime=").append(courseStarttime);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", payStatus=").append(payStatus);
        sb.append(", userId=").append(userId);
        sb.append(", courseId=").append(courseId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", orderId=").append(orderId);
        sb.append("]");
        return sb.toString();
    }
}