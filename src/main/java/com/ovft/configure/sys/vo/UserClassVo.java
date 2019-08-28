package com.ovft.configure.sys.vo;

public class UserClassVo {
    private int pageSize;

    private int pageNum;

    private Integer userId;

    private Integer classId;

    private String className;

    private int classNo;

    private Integer courseId;

    private String specialty;

    private  Integer schoolId;

    private  String schoolName;

    private String search;

    private String job;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public void setPageSize(int pageSize) {

        this.pageSize = pageSize;
    }


    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setClassNo(int classNo) {
        this.classNo = classNo;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public String getClassName() {
        return className;
    }

    public int getClassNo() {
        return classNo;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public String getSpecialty() {
        return specialty;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getSearch() {
        return search;
    }



    @Override
    public String toString() {
        return "UserClassVo{" +
                "pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                ", className='" + className + '\'' +
                ", classNo=" + classNo +
                ", courseId=" + courseId +
                ", specialty='" + specialty + '\'' +
                ", schoolId=" + schoolId +
                ", schoolName='" + schoolName + '\'' +
                ", search='" + search + '\'' +
                '}';
    }
}
