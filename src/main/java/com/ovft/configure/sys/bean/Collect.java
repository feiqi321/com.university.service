package com.ovft.configure.sys.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *   收藏
 **/
public class Collect implements Serializable {

    private Integer collectId;
    private Integer uid;
    /**
     * 收藏类型 1-视频教学
     */
    private Integer type;
    private Integer typeId;
    /**
     * 收藏日期
     */
    private Date date;

    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
