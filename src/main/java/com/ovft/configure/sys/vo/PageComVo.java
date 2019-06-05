package com.ovft.configure.sys.vo;

/**
 * @author zqx
 * @create 2019-04-17 10:37
 */
public class PageComVo {
    private int pageSize;

    private int pageNum;

    private Integer newsid;

    private Integer uid;

    private Integer newtype;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getNewsid() {
        return newsid;
    }

    public void setNewsid(Integer newsid) {
        this.newsid = newsid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getNewtype() {
        return newtype;
    }

    public void setNewtype(Integer newtype) {
        this.newtype = newtype;
    }
}
