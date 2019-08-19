package com.ovft.configure.sys.utils;

import java.text.ParseException;
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

    /**
     * 根据身份证号获取年龄
     *
     * @param certId
     * @return
     */
    public static int getAgeByCertId(String certId) {
        String birthday = "";
        if (certId.length() == 18) {
            birthday = certId.substring(6, 10) + "/"
                    + certId.substring(10, 12) + "/"
                    + certId.substring(12, 14);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date now = new Date();
        Date birth = new Date();
        try {
            birth = sdf.parse(birthday);
        } catch (ParseException e) {
        }
        long intervalMilli = now.getTime() - birth.getTime();
        int age = (int) (intervalMilli / (24 * 60 * 60 * 1000)) / 365;
        System.out.println(age);
        return age;
    }

}
