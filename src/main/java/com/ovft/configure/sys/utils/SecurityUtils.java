package com.ovft.configure.sys.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecurityUtils {
    /**
     * //手机号格式验证
     *
     * @param phone
     * @return
     */
    public static boolean securityPhone(String phone) {
        if (StringUtils.isBlank(phone)) {
            return false;
        }
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        boolean isMatch = m.matches();
        return isMatch;
    }

}