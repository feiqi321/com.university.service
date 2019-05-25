package com.ovft.configure.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ovft.configure.sys.bean.EduCheck;

import java.util.Date;

/**
 * @author vvtxw
 * @create 2019-04-17 10:37
 */
public class EduCheckVo extends EduCheck {
    private String courseName;

    private String placeClass;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    private String startTime;

    private String endTime;

    private String schoolName;


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
