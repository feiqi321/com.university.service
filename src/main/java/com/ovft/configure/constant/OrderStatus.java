package com.ovft.configure.constant;

/**
 * @author vvtxw
 * @create 2019-04-19 17:13
 */
public class OrderStatus {
    //1待付款,2已经取消，3.待收货,4已完成
    public static final Integer UNPay = 1;//待付款
    public static final Integer REMOVE = 2;//已经取消
    public static final Integer RECIVE = 3;//待收货
    public static final Integer FINISH = 4;//已完成
    public static final Integer PAY = 5;//已完成


    public static final String OFFPAY = "1";//已支付
    public static final String OFFUNPAY = "2";//未支付

    public static final Integer ISCHECK = 1;//已打卡
    public static final Integer NOCHECK = 0;//未打卡

}
