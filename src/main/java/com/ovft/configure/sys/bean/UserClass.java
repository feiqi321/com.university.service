package com.ovft.configure.sys.bean;

import com.ovft.configure.sys.vo.MyCourseAll;

import java.util.List;

/**
 * @ClassName UserClass 班级
 * @Author xzy
 * @Version 2.5
 **/
public class UserClass {
    private Integer classId;
    private String className;
    private String classNo;    //编号
    private String job;        //职业
    private String specialty;  //所属专业
    private Integer courseId;  //课程id
    private String remark;      //备注
    private Integer schoolId;
    private String schoolName;
    private List<MyCourseAll> users;   //班级学员集合


    public List<MyCourseAll> getUsers() {
        return users;
    }

    public void setUsers(List<MyCourseAll> users) {
        this.users = users;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public void setClassName(String className) {
        this.className = className;
    }



    public void setJob(String job) {
        this.job = job;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Integer getClassId() {
        return classId;
    }

    public String getClassName() {
        return className;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getJob() {
        return job;
    }

    public String getSpecialty() {
        return specialty;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public String getRemark() {
        return remark;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    @Override
    public String toString() {
        return "UserClass{" +
                "classId=" + classId +
                ", className='" + className + '\'' +
                ", classNo=" + classNo +
                ", job='" + job + '\'' +
                ", specialty='" + specialty + '\'' +
                ", courseId=" + courseId +
                ", remark='" + remark + '\'' +
                ", schoolId=" + schoolId +
                ", schoolName='" + schoolName + '\'' +
                '}';
    }
}
