package com.ovft.configure.sys.vo;

/**
 * @author zqx
 * @create 2019-04-17 10:37
 */
public class PageVo {
    private int pageSize;

    private int pageNum;

    private String search;

    private Integer schoolId;

    private Integer checkin;

    private Integer wid;

    private String type;

    private Integer role;

    public Integer getCheckin() {
        return checkin;
    }

    public void setCheckin(Integer checkin) {
        this.checkin = checkin;
    }

    public Integer getWid() {
        return wid;
    }

    public void setWid(Integer wid) {
        this.wid = wid;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if(pageSize <= 0) {
            pageSize = 10;
        }
        this.pageSize = pageSize ;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
