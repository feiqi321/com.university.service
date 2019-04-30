package com.ovft.configure.sys.vo;

import java.io.Serializable;
import java.util.List;

public class PageBean implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int page;
    private int size;
    private long total;
    private List<?> results;

    public PageBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    public PageBean(long total, List<?> results) {
        this.total = total;
        this.results = results;
    }

    public PageBean(int page, int size, long total, List<?> results) {
        super();
        this.page = page;
        this.size = size;
        this.total = total;
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getResults() {
        return results;
    }

    public void setResults(List<?> results) {
        this.results = results;
    }


}
