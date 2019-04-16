package com.ovft.configure.sys.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author vvtxw
 */
public class EduCheck implements Serializable {
    private Integer checkId;

    private Integer userId;

    private Date checkStartTime;

    private Date checkEndTime;

    /**
     * 是否打卡,0 未打卡 1已打卡
     */
    private Integer isCheck;

    /**
     * 学校id
     */
    private String schoolId;

    private static final long serialVersionUID = 1L;

    public Integer getCheckId() {
        return checkId;
    }

    public void setCheckId(Integer checkId) {
        this.checkId = checkId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCheckStartTime() {
        return checkStartTime;
    }

    public void setCheckStartTime(Date checkStartTime) {
        this.checkStartTime = checkStartTime;
    }

    public Date getCheckEndTime() {
        return checkEndTime;
    }

    public void setCheckEndTime(Date checkEndTime) {
        this.checkEndTime = checkEndTime;
    }

    public Integer getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
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
        EduCheck other = (EduCheck) that;
        return (this.getCheckId() == null ? other.getCheckId() == null : this.getCheckId().equals(other.getCheckId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCheckStartTime() == null ? other.getCheckStartTime() == null : this.getCheckStartTime().equals(other.getCheckStartTime()))
            && (this.getCheckEndTime() == null ? other.getCheckEndTime() == null : this.getCheckEndTime().equals(other.getCheckEndTime()))
            && (this.getIsCheck() == null ? other.getIsCheck() == null : this.getIsCheck().equals(other.getIsCheck()))
            && (this.getSchoolId() == null ? other.getSchoolId() == null : this.getSchoolId().equals(other.getSchoolId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCheckId() == null) ? 0 : getCheckId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCheckStartTime() == null) ? 0 : getCheckStartTime().hashCode());
        result = prime * result + ((getCheckEndTime() == null) ? 0 : getCheckEndTime().hashCode());
        result = prime * result + ((getIsCheck() == null) ? 0 : getIsCheck().hashCode());
        result = prime * result + ((getSchoolId() == null) ? 0 : getSchoolId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", checkId=").append(checkId);
        sb.append(", userId=").append(userId);
        sb.append(", checkStartTime=").append(checkStartTime);
        sb.append(", checkEndTime=").append(checkEndTime);
        sb.append(", isCheck=").append(isCheck);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}