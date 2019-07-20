package com.ovft.configure.sys.bean;

import java.util.Date;

public class Suggestions {
    private Integer id;
    private Integer userId;
    private String userName;
    private Integer schoolId;
    private Date    createTime;
    private String  content;
    private Integer type;
    private String  typeName;
    private Integer [] ids;

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getContent() {
        return content;
    }

    public Integer getType() {
        return type;
    }

    public String getTypeName() {
        return typeName;
    }

    @Override
    public String toString() {
        return "Suggestions{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName=" + userName +
                ", schoolId=" + schoolId +
                ", createTime=" + createTime +
                ", content='" + content + '\'' +
                ", type=" + type +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
