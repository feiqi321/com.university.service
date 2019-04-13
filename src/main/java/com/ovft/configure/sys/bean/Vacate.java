package com.ovft.configure.sys.bean;

import java.util.Date;

/**
 * 请假表
 */
public class Vacate {
    private Integer vacateId;
    private Integer userId;
    private Integer courseId;
    private Date startTime;
    private Date endTime;

    //联系人电话
    private String contactsPhone;

    //联系人
    private String contacts;

    //请假人姓名
    private String vacateName;

    //是否同意  0-不同意，1-同意，2-未审核
    private Integer isCheck;

    // 请假原因
    private String vacateCause;

    //请假类型
    private String vacateType;

    public Integer getVacateId() {
        return vacateId;
    }

    public void setVacateId(Integer vacateId) {
        this.vacateId = vacateId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getVacateName() {
        return vacateName;
    }

    public void setVacateName(String vacateName) {
        this.vacateName = vacateName;
    }

    public Integer getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }

    public String getVacateCause() {
        return vacateCause;
    }

    public void setVacateCause(String vacateCause) {
        this.vacateCause = vacateCause;
    }

    public String getVacateType() {
        return vacateType;
    }

    public void setVacateType(String vacateType) {
        this.vacateType = vacateType;
    }
}
