package com.ovft.configure.sys.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecurityUtils {

    private static final Logger logger = LoggerFactory.getLogger(SecurityUtils.class);
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

    /**
     * 身份证校验
     *
     * @param idCard
     * @return
     */
    public static boolean securityIdCard(String idCard) {
        if (StringUtils.isBlank(idCard)) {
            return false;
        }
        // 定义判别用户身份证号的正则表达式（15位或者18位，最后一位可以为字母）
        String regularExpression = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
                "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
        boolean matches = idCard.matches(regularExpression);
        if (!matches) {
            return matches;
        }
        //判断第18位校验值
        if (idCard.length() == 18) {
            try {
                char[] charArray = idCard.toCharArray();
                //前十七位加权因子
                int[] idCardWi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
                //这是除以11后，可能产生的11位余数对应的验证码
                String[] idCardY = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
                int sum = 0;
                for (int i = 0; i < idCardWi.length; i++) {
                    int current = Integer.parseInt(String.valueOf(charArray[i]));
                    int count = current * idCardWi[i];
                    sum += count;
                }
                char idCardLast = charArray[17];
                int idCardMod = sum % 11;
                if (idCardY[idCardMod].toUpperCase().equals(String.valueOf(idCardLast).toUpperCase())) {
                    return true;
                } else {
                    System.out.println("身份证最后一位:" + String.valueOf(idCardLast).toUpperCase() +
                            "错误,正确的应该是:" + idCardY[idCardMod].toUpperCase());
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("身份证校验异常:" + e.getMessage());
                return false;
            }
        }
        return true;
    }

     /*

     */
    /**
    * 校验银行卡卡号
     * 校验过程：
     *    1、从卡号最后一位数字开始，逆向将奇数位(1、3、5等等)相加。
     *    2、从卡号最后一位数字开始，逆向将偶数位数字，先乘以2（如果乘积为两位数，将个位十位数字相加，即将其减去9），再求和。
     *    3、将奇数位总和加上偶数位总和，结果应该可以被10整除。
    */
    public static boolean checkBankCard(String bankCard) {
        if(bankCard.length() < 15 || bankCard.length() > 19) {
            return false;
        }
        char bit = getBankCardCheckCode(bankCard.substring(0, bankCard.length() - 1));
        if(bit == 'N'){
            return false;
        }
        return bankCard.charAt(bankCard.length() - 1) == bit;
    }

    /**
    * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
    * @param nonCheckCodeBankCard
    * @return
    * */
    public static char getBankCardCheckCode(String nonCheckCodeBankCard){
        if(nonCheckCodeBankCard == null || nonCheckCodeBankCard.trim().length() == 0
                || !nonCheckCodeBankCard.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeBankCard.trim().toCharArray();
        int luhmSum = 0;
        for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if(j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');
    }

}