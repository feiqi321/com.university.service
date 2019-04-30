package com.ovft.configure.sys.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 根据身份证号输出年龄
 *
 * @author vvtxw
 * @create 2019-04-29 12:09
 */
public class AgeUtil {

    public static int IdNOToAge(String IdNO) {
        int leh = IdNO.length();
        String dates = "";
        if (leh == 18) {
            int se = Integer.valueOf(IdNO.substring(leh - 1)) % 2;
            dates = IdNO.substring(6, 10);
            SimpleDateFormat df = new SimpleDateFormat("yyyy");
            String year = df.format(new Date());
            int age = Integer.parseInt(year) - Integer.parseInt(dates);
            return age;
        } else {
            dates = IdNO.substring(6, 8);
            return Integer.parseInt(dates);
        }

    }

}
