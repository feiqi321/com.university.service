package com.ovft.configure.sys.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 *   点赞
 **/
public class Approve implements Serializable {

    private Integer approveId;
    private Integer userId;
    private Integer typeId;

    /**
     *  类型 1-精品课程， 2-学员风采， 3-教师风采
     */
    private Integer type;

    /**
     * 点赞日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    /**
     *  点赞数
     */
    private Integer count;

    public Integer getApproveId() {
        return approveId;
    }

    public void setApproveId(Integer approveId) {
        this.approveId = approveId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
