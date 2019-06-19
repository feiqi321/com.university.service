package com.ovft.configure.sys.vo;

import java.util.Date;

public class MyCourseAll  {


    /**
     * 报名方式
     */
    private String statu;

    /**
     * 课程名称
     */
    private Integer id;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 结束时间
     */
    private Date endDate;
    /**
     * 老师名称
     */
    private String courseTeacher;
    /**
     * 所属学校id
     */
    private Integer schoolId;
    /**
     * 所属学校id
     */
    private String schoolName;
    /**
     * 所属学校id
     */
    private String Courseyear;

    public String getCourseyear() {
        return Courseyear;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public void setCourseyear(String courseyear) {
        Courseyear = courseyear;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getCourseTeacher() {
        return courseTeacher;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setCourseTeacher(String courseTeacher) {
        this.courseTeacher = courseTeacher;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public String getStatu() {
        return statu;
    }
}
