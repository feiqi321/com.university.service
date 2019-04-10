package com.ovft.configure.sys.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName User
 * @Author zqx
 * @Date 2019/4/9 10:13
 * @Version 1.0
 **/
public class Course {

    private Integer courseId;
    private String courseName;
    private BigDecimal coursePrice;

    /**
     * 课程老师  admin_id
     */
    private Integer courseTeacher;

    /**
     * 上课地点
     */
    private String placeClass;

    /**
     * 开课日期
     */
    private Date startDate;

    /**
     * 结课日期
     */
    private Date endDate;

    /**
     * 上课时间
     */
    private String startClock;

    /**
     * 下课时间
     */
    private String endClock;

    /**
     * 班级人数
     */
    private Integer peopleNumber;
    private Integer schoolId;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public BigDecimal getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(BigDecimal coursePrice) {
        this.coursePrice = coursePrice;
    }

    public Integer getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(Integer courseTeacher) {
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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStartClock() {
        return startClock;
    }

    public void setStartClock(String startClock) {
        this.startClock = startClock;
    }

    public String getEndClock() {
        return endClock;
    }

    public void setEndClock(String endClock) {
        this.endClock = endClock;
    }

    public Integer getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(Integer peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }
}
