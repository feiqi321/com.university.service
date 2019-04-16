package com.ovft.configure.sys.service.impl;

import com.ovft.configure.constant.ConstantClassField;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.User;
import com.ovft.configure.sys.dao.UserMapper;
import com.ovft.configure.sys.service.UserService;
import com.ovft.configure.sys.utils.MD5Utils;
import com.ovft.configure.sys.utils.RedisUtil;
import com.ovft.configure.sys.web.AdminController;
import com.ovft.configure.sys.web.UserController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName UserService
 * @Author  xzy
 * @Date 2019/4/11 16:21
 * @Version 1.0
 **/
@Service
public class UserServiceImpl  implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;
    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @Transactional
    @Override
    public WebResult addUser(User user) {
             //验证手机号是否被注册
           User finduserbyphone=userMapper.findUserByPhone(user);
               if (finduserbyphone!=null){
                   return new WebResult("400","用户已注册！");
               }
           //手机号码格式验证
            WebResult phoneResult = isTure(user);
              if (!phoneResult.getCode().equals("200")){
                  return new WebResult("400",phoneResult.getMsg());
              }
        int l = user.getPassword().length();
              if (user.getPassword().equals("123456")){
                  return new WebResult("400", "密码不能过于简单");
              }
        if (l < 6 || l > 16 ) {
           return new WebResult("400", "密码长度必须要在6-16之间");
        }
        if (StringUtils.isBlank(user.getPassword())) {
            return new WebResult("400", "密码不能为空");
        }
        if (StringUtils.isBlank(user.getNextPass())) {
            return new WebResult("400", "确认密码不能为空");
        }
        if (!user.getPassword().equals(user.getNextPass())) {
            return new WebResult("400", "输入的两次密码不一致");

        }
        String password = user.getPassword();
        user.setPassword(MD5Utils.md5Password(password));
        //TODO   添加短信验证
        WebResult result = new WebResult();
        userMapper.addUser(user);
        result.setCode("200");
        result.setMsg("注册成功");
        return result;
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @Transactional
    @Override
    public WebResult findUser(User user) {
        //手机号码格式验证
        WebResult phoneResult = isTure(user);
        if (!phoneResult.getCode().equals("200")){
            return new WebResult("400",phoneResult.getMsg());
        }

        if (StringUtils.isBlank(user.getPassword())) {
            return new WebResult("400", "密码不能为空");
        }
        User finduserbyphone = userMapper.findUserByPhone(user);
        if (finduserbyphone == null) {
            return new WebResult("400", "您的手机号尚未注册！");
        }

        String pasword = MD5Utils.md5Password(user.getPassword());
        if (!pasword.equals(finduserbyphone.getPassword())) {
            return new WebResult("400", "密码错误");
        }
        HashMap<String, Object> map = new HashMap();
        map.put("user", finduserbyphone);

        //添加token
        String token = UUID.randomUUID().toString();
        //pc端设置半年缓存过期
        boolean isSet =  redisUtil.set(token, finduserbyphone.getUserId(),6*30*24*60*60);
        if(!isSet) {
            return new WebResult("400", "登录失败");
        }
        map.put("token", token);
        WebResult result = new WebResult("200", "", map);
        return result;
    }

    /**
     * 修改密码
     *
     * @param phone, newPassword, nextpass
     * @return
     */
    @Transactional
    @Override
    public WebResult updatePassword(String phone, String newPassword, String nextpass) {
            User user=new User();
            user.setPhone(phone);
        //手机号码格式验证
        WebResult phoneResult = isTure(user);
        if (!phoneResult.getCode().equals("200")){
            return new WebResult("400",phoneResult.getMsg());
        }

        int l = newPassword.length();
        if (newPassword.equals("123456")){
            return new WebResult("400", "密码不能过于简单");
        }
        if (l < 6 || l > 16 ) {
            return new WebResult("400", "密码长度必须要在6-16之间");
        }

        if (StringUtils.isBlank(nextpass)) {
            return new WebResult("400", "密码不能为空");
        }
        User findUser = userMapper.findUserByPhone2(phone);
        //判段手机号是否错误
        if (findUser==null) {
            return new WebResult("400", "手机号不存在");
        }
        if (!newPassword.equals(nextpass)) {
            return new WebResult("400", "输入的两次密码不一致");
        }

        String newPasswordMd5 = MD5Utils.md5Password(newPassword);
        userMapper.updateByPassword(phone, newPasswordMd5);
        //TODO   添加短信验证
        WebResult result = new WebResult();
        result.setCode("200");
        result.setMsg("修改密码成功");
        return result;
    }

    /**
     * 保存基本信息
     *
     * @param user
     * @return
     */
    @Transactional
    @Override
    public WebResult savaInfo(User user) {

        //手机号码格式验证
        WebResult phoneResult = isTure(user);
        if (!phoneResult.getCode().equals("200")){
            return new WebResult("400",phoneResult.getMsg());
        }
        //紧急联系人一手机号验证
        if (StringUtils.isBlank(user.getEmergencyPhone1())) {
            return new WebResult("400", "紧急联系人1电话不能为空");
        }
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (user.getEmergencyPhone1().length() != 11) {
            return new WebResult("400", "手机号应为11位");
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(user.getEmergencyPhone1());
            boolean isMatch = m.matches();

            if (!isMatch) {
                return new WebResult("400", "请输入正确手机号");
            }
        }
        //紧急联系人二手机号验证
        if (user.getEmergencyPhone2()!=null){
        if (user.getEmergencyPhone2().length() != 11) {
            return new WebResult("400", "手机号应为11位");
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(user.getEmergencyPhone2());
            boolean isMatch = m.matches();

            if (!isMatch) {
                return new WebResult("400", "请输入正确手机号");
            }
        }
        }
         //身份证格式校验
            boolean testCard = this.isIDNumber(user.getIdentity_card());
            if (testCard == false) {
                return new WebResult("400", "输入身份证格式有误");
            }
            //保存
            userMapper.savaInfo(user);
            return new WebResult("200", "保存成功");
        }
    /**
     * 更换手机
     *
     * @param oldPhone,newPhone
     * @return
     */
    @Transactional
    @Override
    public WebResult updatePhone(String oldPhone, String newPhone) {
           User user=new User();
           user.setPhone(newPhone);
        WebResult phoneResult = isTure(user);
        if (!phoneResult.getCode().equals("200")){
            return new WebResult("400",phoneResult.getMsg());
        }
       User findUser=userMapper.findUserByPhone2(newPhone);
           if (findUser!=null){
               return new WebResult("400","手机号已存在");
           }
              userMapper.updatePhone(oldPhone,newPhone);
               return new WebResult("200","更换成功");
    }


    /**
     * 身份证校验方法
     *
     * @param IDNumber
     * @return
     */
    public static boolean isIDNumber(String IDNumber) {
        if (IDNumber == null || "".equals(IDNumber)) {
            return false;
        }
        // 定义判别用户身份证号的正则表达式（15位或者18位，最后一位可以为字母）
        String regularExpression = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
                "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
        //假设18位身份证号码:41000119910101123X  410001 19910101 123X
        //^开头
        //[1-9] 第一位1-9中的一个      4
        //\\d{5} 五位数字           10001（前六位省市县地区）
        //(18|19|20)                19（现阶段可能取值范围18xx-20xx年）
        //\\d{2}                    91（年份）
        //((0[1-9])|(10|11|12))     01（月份）
        //(([0-2][1-9])|10|20|30|31)01（日期）
        //\\d{3} 三位数字            123（第十七位奇数代表男，偶数代表女）
        //[0-9Xx] 0123456789Xx其中的一个 X（第十八位为校验值）
        //$结尾

        //假设15位身份证号码:410001910101123  410001 910101 123
        //^开头
        //[1-9] 第一位1-9中的一个      4
        //\\d{5} 五位数字           10001（前六位省市县地区）
        //\\d{2}                    91（年份）
        //((0[1-9])|(10|11|12))     01（月份）
        //(([0-2][1-9])|10|20|30|31)01（日期）
        //\\d{3} 三位数字            123（第十五位奇数代表男，偶数代表女），15位身份证不含X
        //$结尾


        boolean matches = IDNumber.matches(regularExpression);

        //判断第18位校验值
        if (matches) {

            if (IDNumber.length() == 18) {
                try {
                    char[] charArray = IDNumber.toCharArray();
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
                    System.out.println("异常:" + IDNumber);
                    return false;
                }
            }

        }
        return matches;
    }
    //手机号格式验证
    public static WebResult isTure(User user) {
        if (StringUtils.isBlank(user.getPhone())) {
            return new WebResult("400", "手机号不能为空");
        }

        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (user.getPhone().length() != 11) {
            return new WebResult("400", "手机号应为11位");
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(user.getPhone());
            boolean isMatch = m.matches();

            if (!isMatch) {
                return new WebResult("400", "请输入正确手机号");
            }
        }

        return new WebResult("200", "输入手机号正确");
    }

}
