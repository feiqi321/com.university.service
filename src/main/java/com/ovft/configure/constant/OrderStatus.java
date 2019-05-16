package com.ovft.configure.constant;

/**
 * @author vvtxw
 * @create 2019-04-19 17:13
 */
public class OrderStatus {
    /**
     * 订单的状态 0未取消，1表已取消
     */
    public static final Integer UNREMOVE = 0;//未取消
    public static final Integer REMOVE = 1;//已取消
    public static final Integer PAY = 1;//已支付
    public static final String UNPAY = "未支付";//未支付
    public static final Integer ISCHECK = 1;//已打卡
    public static final Integer NOCHECK = 0;//未打卡
}
