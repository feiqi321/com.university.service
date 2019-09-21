package com.ovft.configure.sys.vo;

import com.ovft.configure.sys.bean.EduClass;
import com.ovft.configure.sys.bean.EduCourse;

import java.io.Serializable;
import java.util.List;

/**
 * 课程的扩展类
 *
 * @author vvtxw
 * @create 2019-04-14 7:53
 */
public class EduCourseVo extends EduCourse implements Serializable {
    private List<EduClass> classList;
    private String schoolName;
    private String teacherName;

    private String week;
    private String startTime;
    private String endTime;
    private String[] courseIds;

    private String name;

    private int isAddQuestion;  //是否添加题目 “1”：已添加   “0”：未添加

    private Integer did;    //院系id

    private String  departmentName;   //院系名称

    private  String registStartTime;     //报名开始时间

    private  String regitEndTime;       //报名结束时间

    private String  offlineRegist;       //线下报名 1开启  2 隐藏

    public String getOfflineRegist() {
        return offlineRegist;
    }

    public void setOfflineRegist(String offlineRegist) {
        this.offlineRegist = offlineRegist;
    }

    public String getRegistStartTime() {
        return registStartTime;
    }

    public void setRegistStartTime(String registStartTime) {
        this.registStartTime = registStartTime;
    }

    public String getRegitEndTime() {
        return regitEndTime;
    }

    public void setRegitEndTime(String regitEndTime) {
        this.regitEndTime = regitEndTime;
    }

    public Integer getDid() {
        return did;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    //可报名剩余人数
    private Integer nowtotal;

    public int getIsAddQuestion() {
        return isAddQuestion;
    }

    public void setIsAddQuestion(int isAddQuestion) {
        this.isAddQuestion = isAddQuestion;
    }

    public Integer getNowtotal() {
        return nowtotal;
    }

    public void setNowtotal(Integer nowtotal) {
        this.nowtotal = nowtotal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(String[] courseIds) {
        this.courseIds = courseIds;
    }

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
