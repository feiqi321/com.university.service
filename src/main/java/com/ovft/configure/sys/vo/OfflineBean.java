package com.ovft.configure.sys.vo;

import com.ovft.configure.sys.bean.EduOfflineOrder;

import java.util.List;

/**
 * @author vvtxw
 * @create 2019-05-24 18:55
 */
public class OfflineBean {
    private List<EduOfflineOrder> eduOfflineOrders;
    private String image;


    public OfflineBean(List<EduOfflineOrder> eduOfflineOrders, String image) {
        this.eduOfflineOrders = eduOfflineOrders;
        this.image = image;
    }

    public List<EduOfflineOrder> getEduOfflineOrders() {
        return eduOfflineOrders;
    }

    public void setEduOfflineOrders(List<EduOfflineOrder> eduOfflineOrders) {
        this.eduOfflineOrders = eduOfflineOrders;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
