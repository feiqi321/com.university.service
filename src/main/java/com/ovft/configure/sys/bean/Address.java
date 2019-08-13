package com.ovft.configure.sys.bean;

import java.io.Serializable;

/**
 * 收货地址
 **/
public class Address implements Serializable {

    private Integer addressId;
    private Integer userId;

    /**
     * 收货人
     */
    private String consigneeName;

    private String phone;

    /**
     * 地区
     */
    private String area;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 邮政编码
     */
    private String postalCode;

    /**
     * 是否默认   0-否  1-是
     */
    private Integer isdefault;

    /*
    * 如果是0 ,没被选择 ,如果是1被选择
    * */
    private Integer changeStatus;


    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Integer getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Integer isdefault) {
        this.isdefault = isdefault;
    }

    public Integer getChangestatus() {
        return changeStatus;
    }

    public void setChangestatus(Integer changestatus) {
        this.changeStatus = changestatus;
    }
}
