package com.ovft.configure.sys.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 */
public class EduRegist implements Serializable {
    /**
     * 报名条件限制id
     */
    private Integer id;

    /**
     * 报名的开始时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registstartTime;

    /**
     * 报名的截止时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registendTime;

    /**
     * 报名年龄限制开始年龄
     */
    private Integer startAge;

    /**
     * 报名年龄限制结束年龄
     */
    private Integer endAge;

    /**
     * 报名学员分类1
     */
    private String registCategoryOne;

    /**
     * 报名学员分类2
     */
    private String registCategoryTwo;

    /**
     * 报名学员分类3
     */
    private String registCategoryThree;

    /**
     * 报名学员分类4
     */
    private String registCategoryFour;

    /**
     * 报名学员分类5
     */
    private String registCategoryFive;

    /**
     * 报名学员分类6
     */
    private String registCategorySix;

    /**
     * 更新时间
     */
    private Date upateTime;

    /**
     * 学校id
     */
    private Integer schoolId;

    /**
     * 课程id
     */
    private Integer courseId;

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * topend
     */
    private String registPriority;

    /**
     * 线下报名：1开启 2关闭
     */
    private String offlineRegist;

    /**
     * 课程门数
     */
    private Integer courseNum;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getRegiststartTime() {
        return registstartTime;
    }

    public void setRegiststartTime(Date registstartTime) {
        this.registstartTime = registstartTime;
    }

    public Date getRegistendTime() {
        return registendTime;
    }

    public void setRegistendTime(Date registendTime) {
        this.registendTime = registendTime;
    }

    public Integer getStartAge() {
        return startAge;
    }

    public void setStartAge(Integer startAge) {
        this.startAge = startAge;
    }

    public Integer getEndAge() {
        return endAge;
    }

    public void setEndAge(Integer endAge) {
        this.endAge = endAge;
    }

    public String getRegistCategoryOne() {
        return registCategoryOne;
    }

    public void setRegistCategoryOne(String registCategoryOne) {
        this.registCategoryOne = registCategoryOne;
    }

    public String getRegistCategoryTwo() {
        return registCategoryTwo;
    }

    public void setRegistCategoryTwo(String registCategoryTwo) {
        this.registCategoryTwo = registCategoryTwo;
    }

    public String getRegistCategoryThree() {
        return registCategoryThree;
    }

    public void setRegistCategoryThree(String registCategoryThree) {
        this.registCategoryThree = registCategoryThree;
    }

    public String getRegistCategoryFour() {
        return registCategoryFour;
    }

    public void setRegistCategoryFour(String registCategoryFour) {
        this.registCategoryFour = registCategoryFour;
    }

    public String getRegistCategoryFive() {
        return registCategoryFive;
    }

    public void setRegistCategoryFive(String registCategoryFive) {
        this.registCategoryFive = registCategoryFive;
    }

    public String getRegistCategorySix() {
        return registCategorySix;
    }

    public void setRegistCategorySix(String registCategorySix) {
        this.registCategorySix = registCategorySix;
    }

    public Date getUpateTime() {
        return upateTime;
    }

    public void setUpateTime(Date upateTime) {
        this.upateTime = upateTime;
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

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getRegistPriority() {
        return registPriority;
    }

    public void setRegistPriority(String registPriority) {
        this.registPriority = registPriority;
    }

    public String getOfflineRegist() {
        return offlineRegist;
    }

    public void setOfflineRegist(String offlineRegist) {
        this.offlineRegist = offlineRegist;
    }

    public Integer getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(Integer courseNum) {
        this.courseNum = courseNum;
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
        EduRegist other = (EduRegist) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getRegiststartTime() == null ? other.getRegiststartTime() == null : this.getRegiststartTime().equals(other.getRegiststartTime()))
                && (this.getRegistendTime() == null ? other.getRegistendTime() == null : this.getRegistendTime().equals(other.getRegistendTime()))
                && (this.getStartAge() == null ? other.getStartAge() == null : this.getStartAge().equals(other.getStartAge()))
                && (this.getEndAge() == null ? other.getEndAge() == null : this.getEndAge().equals(other.getEndAge()))
                && (this.getRegistCategoryOne() == null ? other.getRegistCategoryOne() == null : this.getRegistCategoryOne().equals(other.getRegistCategoryOne()))
                && (this.getRegistCategoryTwo() == null ? other.getRegistCategoryTwo() == null : this.getRegistCategoryTwo().equals(other.getRegistCategoryTwo()))
                && (this.getRegistCategoryThree() == null ? other.getRegistCategoryThree() == null : this.getRegistCategoryThree().equals(other.getRegistCategoryThree()))
                && (this.getRegistCategoryFour() == null ? other.getRegistCategoryFour() == null : this.getRegistCategoryFour().equals(other.getRegistCategoryFour()))
                && (this.getRegistCategoryFive() == null ? other.getRegistCategoryFive() == null : this.getRegistCategoryFive().equals(other.getRegistCategoryFive()))
                && (this.getRegistCategorySix() == null ? other.getRegistCategorySix() == null : this.getRegistCategorySix().equals(other.getRegistCategorySix()))
                && (this.getUpateTime() == null ? other.getUpateTime() == null : this.getUpateTime().equals(other.getUpateTime()))
                && (this.getSchoolId() == null ? other.getSchoolId() == null : this.getSchoolId().equals(other.getSchoolId()))
                && (this.getCourseId() == null ? other.getCourseId() == null : this.getCourseId().equals(other.getCourseId()))
                && (this.getSchoolName() == null ? other.getSchoolName() == null : this.getSchoolName().equals(other.getSchoolName()))
                && (this.getCourseName() == null ? other.getCourseName() == null : this.getCourseName().equals(other.getCourseName()))
                && (this.getRegistPriority() == null ? other.getRegistPriority() == null : this.getRegistPriority().equals(other.getRegistPriority()))
                && (this.getOfflineRegist() == null ? other.getOfflineRegist() == null : this.getOfflineRegist().equals(other.getOfflineRegist()))
                && (this.getCourseNum() == null ? other.getCourseNum() == null : this.getCourseNum().equals(other.getCourseNum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRegiststartTime() == null) ? 0 : getRegiststartTime().hashCode());
        result = prime * result + ((getRegistendTime() == null) ? 0 : getRegistendTime().hashCode());
        result = prime * result + ((getStartAge() == null) ? 0 : getStartAge().hashCode());
        result = prime * result + ((getEndAge() == null) ? 0 : getEndAge().hashCode());
        result = prime * result + ((getRegistCategoryOne() == null) ? 0 : getRegistCategoryOne().hashCode());
        result = prime * result + ((getRegistCategoryTwo() == null) ? 0 : getRegistCategoryTwo().hashCode());
        result = prime * result + ((getRegistCategoryThree() == null) ? 0 : getRegistCategoryThree().hashCode());
        result = prime * result + ((getRegistCategoryFour() == null) ? 0 : getRegistCategoryFour().hashCode());
        result = prime * result + ((getRegistCategoryFive() == null) ? 0 : getRegistCategoryFive().hashCode());
        result = prime * result + ((getRegistCategorySix() == null) ? 0 : getRegistCategorySix().hashCode());
        result = prime * result + ((getUpateTime() == null) ? 0 : getUpateTime().hashCode());
        result = prime * result + ((getSchoolId() == null) ? 0 : getSchoolId().hashCode());
        result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());
        result = prime * result + ((getSchoolName() == null) ? 0 : getSchoolName().hashCode());
        result = prime * result + ((getCourseName() == null) ? 0 : getCourseName().hashCode());
        result = prime * result + ((getRegistPriority() == null) ? 0 : getRegistPriority().hashCode());
        result = prime * result + ((getOfflineRegist() == null) ? 0 : getOfflineRegist().hashCode());
        result = prime * result + ((getCourseNum() == null) ? 0 : getCourseNum().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", registstartTime=").append(registstartTime);
        sb.append(", registendTime=").append(registendTime);
        sb.append(", startAge=").append(startAge);
        sb.append(", endAge=").append(endAge);
        sb.append(", registCategoryOne=").append(registCategoryOne);
        sb.append(", registCategoryTwo=").append(registCategoryTwo);
        sb.append(", registCategoryThree=").append(registCategoryThree);
        sb.append(", registCategoryFour=").append(registCategoryFour);
        sb.append(", registCategoryFive=").append(registCategoryFive);
        sb.append(", registCategorySix=").append(registCategorySix);
        sb.append(", upateTime=").append(upateTime);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", courseId=").append(courseId);
        sb.append(", schoolName=").append(schoolName);
        sb.append(", courseName=").append(courseName);
        sb.append(", registPriority=").append(registPriority);
        sb.append(", offlineRegist=").append(offlineRegist);
        sb.append(", courseNum=").append(courseNum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}