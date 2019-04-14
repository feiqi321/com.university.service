package com.ovft.configure.sys.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author vvtxw
 * @create 2019-04-14 10:10
 */
public class OrderIdUtil {
    public static String getOrderIdByTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        String result = "";
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            result += random.nextInt(10);
        }
        return newDate + result;
    }

    public static void main(String[] args) {
        String orderIdByTime = getOrderIdByTime();
        System.out.println(orderIdByTime);
    }


}
