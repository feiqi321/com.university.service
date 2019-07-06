package com.ovft.configure.sys.bean;
/**
 * @ClassName UserClass 班级
 * @Author xzy
 * @Version 2.5
 **/
public class UserClass {
     private Integer classId;
     private String ClassName;
     private String ClassNo;    //编号
     private String job;        //职业
     private String specialty;  //所属专业
     private Integer courseId;  //课程id
     private String remark;      //备注

    public Integer getClassId() {
        return classId;
    }

    public String getClassName() {
        return ClassName;
    }

    public String getClassNo() {
        return ClassNo;
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

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public void setClassNo(String classNo) {
        ClassNo = classNo;
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

    @Override
    public String toString() {
        return "UserClass{" +
                "classId=" + classId +
                ", ClassName='" + ClassName + '\'' +
                ", ClassNo='" + ClassNo + '\'' +
                ", job='" + job + '\'' +
                ", specialty='" + specialty + '\'' +
                ", courseId=" + courseId +
                ", remark='" + remark + '\'' +
                '}';
    }
}
