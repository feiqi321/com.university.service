package com.ovft.configure.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ovft.configure.sys.bean.Order;

import java.util.Date;

/**
 * @author vvtxw
 * @create 2019-04-14 16:59
 */
public class OrderVo extends Order {
    private Integer schoolId;
    private String schoolName;
    private String courseName;

    private Integer courseId;
    private Integer paymentStatus;
    private String employer;
    private String name;

    /**
     * 课时id
     */
    private String week;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    /**
     * 课程老师
     */
    private String courseTeacher;

    /**
     * 上课地点
     */
    private String placeClass;

    /**
     * 开课日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    private String startTime;


    /**
     * 结课日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private String endTime;

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getShoolName() {
        return schoolName;
    }

    public void setShoolName(String shoolName) {
        this.schoolName = shoolName;
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

    public String getPlaceClass() {
        return placeClass;
    }

    public void setPlaceClass(String placeClass) {
        this.placeClass = placeClass;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
