package com.ovft.configure.sys.vo;

import java.io.Serializable;
import java.math.BigDecimal;
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
    private Object data;

    private BigDecimal accountAllMoney;


    public BigDecimal getAccountAllMoney() {
        return accountAllMoney;
    }

    public void setAccountAllMoney(BigDecimal accountAllMoney) {
        this.accountAllMoney = accountAllMoney;
    }

    public PageBean(long total, List<?> results, BigDecimal accountAllMoney) {
        this.total = total;
        this.results = results;
        this.accountAllMoney = accountAllMoney;
    }

    public PageBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    public PageBean(long total, List<?> results) {
        this.total = total;
        this.results = results;
    }

    public PageBean(long total, Object data) {
        this.total = total;
        this.data = data;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
