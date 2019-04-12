package com.ovft.configure.sys.bean;

/**
 * 课程时间表
 * @ClassName SchoolTime
 * @Author zqx
 * @Date 2019/4/11 17:14
 * @Version 1.0
 **/
public class SchoolTime {

    private Integer timeId;
    private Integer schoolId;

    /**
     * 课时开始时间
     */
    private String startTime;

    /**
     * 课时结束时间
     */
    private String endTime;

    /**
     * 第几课时
     */
    private Integer section;

    public Integer getTimeId() {
        return timeId;
    }

    public void setTimeId(Integer timeId) {
        this.timeId = timeId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
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

    public Integer getSection() {
        return section;
    }

    public void setSection(Integer section) {
        this.section = section;
    }
}
