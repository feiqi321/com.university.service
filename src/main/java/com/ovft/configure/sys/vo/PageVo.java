package com.ovft.configure.sys.vo;

/**
 * @author zqx
 * @create 2019-04-17 10:37
 */
public class PageVo {
    private int pageSize;

    private int pageNum;

    private String search;

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
