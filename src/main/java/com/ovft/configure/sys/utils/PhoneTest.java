package com.ovft.configure.sys.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneTest {
        //固定电话校验
        public  boolean isPhone(String str) {
            Pattern p1 = null, p2 = null;
            Matcher m = null;
            boolean isPhone = false;
            p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$"); // 验证带区号的
            p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");     // 验证没有区号的
            if (str.length() > 9) {
                m = p1.matcher(str);
                isPhone = m.matches();
            } else {
                m = p2.matcher(str);
                isPhone = m.matches();
            }
            return isPhone;
        }

}
