package com.ovft.configure.sys.vo;

import com.ovft.configure.sys.bean.Department;

public class DepartmentVo extends Department {
    private int pageSize;

    private int pageNum;

    private String search;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public void setPageSize(int pageSize) {
        if (pageSize <= 0) {
            pageSize = 10;
        }
        this.pageSize = pageSize;
    }


    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    @Override
    public String toString() {
        return "DepartmentVo{" +
                "pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                ", search='" + search + '\'' +
                '}';
    }
}
