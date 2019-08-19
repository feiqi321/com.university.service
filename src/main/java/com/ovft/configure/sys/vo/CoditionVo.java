package com.ovft.configure.sys.vo;

public class CoditionVo {
    private int pageSize;
    private int pageNum;
    private Integer schoolId;
    private Integer courseId;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "CoditionVo{" +
                "pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                ", schoolId=" + schoolId +
                ", courseId=" + courseId +
                '}';
    }
}
