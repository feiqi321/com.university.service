package com.ovft.configure.constant;

/**
 * @author vvtxw
 * @create 2019-07-09 11:01
 */
public enum PaymentStatus {

    UNPAID("支付中"),
    PAID("已支付"),
    PAY_FAIL("支付失败"),
    ClOSED("已关闭");

    private String name;

    PaymentStatus(String name) {
        this.name = name;
    }


}


