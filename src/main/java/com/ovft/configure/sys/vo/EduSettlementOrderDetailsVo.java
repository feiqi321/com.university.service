package com.ovft.configure.sys.vo;

import com.ovft.configure.sys.bean.EduSettlementOrderDetails;

import java.util.Date;

/**
 * @author vvtxw
 * @create 2019-06-15 11:51
 */
public class EduSettlementOrderDetailsVo extends EduSettlementOrderDetails {
    private Date endTime;
    private Date startTime;

    private Integer pageNum;
    private Integer pageSize;

    private Integer[] ids;

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
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

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


}
