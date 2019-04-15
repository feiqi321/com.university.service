package com.ovft.configure.sys.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class EduClass implements Serializable {
    private Integer classScheduleId;

    /**
     * 课程id
     */
    private Integer courseIds;

    /**
     * 开课日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    /**
     * 结课日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;


    /**
     * 课时id
     */
    private String week;

    private String startTime;

    private String endTime;

    private static final long serialVersionUID = 1L;

    public Integer getClassScheduleId() {
        return classScheduleId;
    }

    public void setClassScheduleId(Integer classScheduleId) {
        this.classScheduleId = classScheduleId;
    }

    public Integer getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(Integer courseIds) {
        this.courseIds = courseIds;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        EduClass other = (EduClass) that;
        return (this.getClassScheduleId() == null ? other.getClassScheduleId() == null : this.getClassScheduleId().equals(other.getClassScheduleId()))
            && (this.getCourseIds() == null ? other.getCourseIds() == null : this.getCourseIds().equals(other.getCourseIds()))
            && (this.getWeek() == null ? other.getWeek() == null : this.getWeek().equals(other.getWeek()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getClassScheduleId() == null) ? 0 : getClassScheduleId().hashCode());
        result = prime * result + ((getCourseIds() == null) ? 0 : getCourseIds().hashCode());
        result = prime * result + ((getWeek() == null) ? 0 : getWeek().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", classScheduleId=").append(classScheduleId);
        sb.append(", courseIds=").append(courseIds);
        sb.append(", week=").append(week);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}