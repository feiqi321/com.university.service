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
    private String userName;
    private String schoolName;

    public OfflineBean(List<EduOfflineOrder> eduOfflineOrders, String image, String userName, String schoolName) {
        this.eduOfflineOrders = eduOfflineOrders;
        this.image = image;
        this.userName = userName;
        this.schoolName = schoolName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
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
