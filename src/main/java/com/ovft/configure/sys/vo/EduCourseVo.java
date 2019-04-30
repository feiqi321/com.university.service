package com.ovft.configure.sys.vo;

import com.ovft.configure.sys.bean.EduClass;
import com.ovft.configure.sys.bean.EduCourse;

import java.util.List;

/**
 * 课程的扩展类
 *
 * @author vvtxw
 * @create 2019-04-14 7:53
 */
public class EduCourseVo extends EduCourse {
    private List<EduClass> classList;
    private String schoolName;
    private String teacherName;

    private String week;
    private String startTime;
    private String endTime;


    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
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

    public List<EduClass> getClassList() {
        return classList;
    }

    public void setClassList(List<EduClass> classList) {
        this.classList = classList;
    }


    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
