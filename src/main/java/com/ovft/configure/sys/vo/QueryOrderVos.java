package com.ovft.configure.sys.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ovft.configure.sys.bean.Address;

import java.util.List;

/**
 * @author vvtxw
 * @create 2019-05-18 14:38
 */

public class QueryOrderVos {
    private List<Address> address;

    private PageBean eduBookGoods;

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public PageBean getEduBookGoods() {
        return eduBookGoods;
    }

    public void setEduBookGoods(PageBean eduBookGoods) {
        this.eduBookGoods = eduBookGoods;
    }
}
