package com.ovft.configure.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ovft.configure.sys.bean.EduOfflineOrder;

import java.util.Date;

/**
 * @author vvtxw
 * @create 2019-05-23 15:16
 */
public class EduOfflineOrderVo extends EduOfflineOrder {
    /**
     * 缴费地点
     */
    private String paymentAddress;

    /**
     * 缴费开始时间
     */
    private Date paymentStarttime;

    /**
     * 缴费结束时间
     */
    private Date paymentEndtime;


    /**
     * 学校头像
     */
    private String image;

    /**
     * 开课日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    /**
     * 结课日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPaymentAddress() {
        return paymentAddress;
    }

    public void setPaymentAddress(String paymentAddress) {
        this.paymentAddress = paymentAddress;
    }

    public Date getPaymentStarttime() {
        return paymentStarttime;
    }

    public void setPaymentStarttime(Date paymentStarttime) {
        this.paymentStarttime = paymentStarttime;
    }

    public Date getPaymentEndtime() {
        return paymentEndtime;
    }

    public void setPaymentEndtime(Date paymentEndtime) {
        this.paymentEndtime = paymentEndtime;
    }
}
