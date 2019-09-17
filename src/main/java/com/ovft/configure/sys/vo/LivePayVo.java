package com.ovft.configure.sys.vo;

import com.ovft.configure.sys.bean.EduLivePay;

/**
 * @author vvtxw
 * @create 2019-09-04 16:56
 */
public class LivePayVo  extends EduLivePay{

    private int pageSize; //每页显示数
    private int pageNum;  //分页页数
    private String search;//模糊查询




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

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }


}
