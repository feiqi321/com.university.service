package com.ovft.configure.sys.service.impl;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduClass;
import com.ovft.configure.sys.bean.User;
import com.ovft.configure.sys.dao.EduClassMapper;
import com.ovft.configure.sys.dao.UserMapper;
import com.ovft.configure.sys.dao.VacateMapper;
import com.ovft.configure.sys.service.UserService;
import com.ovft.configure.sys.utils.MD5Utils;
import com.ovft.configure.sys.utils.PhoneTest;
import com.ovft.configure.sys.utils.RedisUtil;
import com.ovft.configure.sys.vo.EduCourseVo;
import com.ovft.configure.sys.vo.PhoneVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName UserServiceImpl
 * @Author xzy
 * @Date 2019/4/11 16:21
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Resource
    private VacateMapper vacateMapper;
    @Resource
    private EduClassMapper eduClassMapper;

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
        User finduserbyphone = userMapper.findUserByPhone(user);
        if (finduserbyphone != null) {
            return new WebResult("400", "该用户已注册");
        }
        //手机号码格式验证
        WebResult phoneResult = isTure(user);
        if (!phoneResult.getCode().equals("200")) {
            return new WebResult("400", phoneResult.getMsg());
        }
        int l = user.getPassword().length();

        if (l < 6 || l > 16) {
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
        //短信验证码
        Object value = redisUtil.get("sendSms-" + user.getPhone());
        if (value == null) {
            return new WebResult("400", "验证码失效");
        }
        if (!user.getSecurityCode().equals(value.toString())) {
            return new WebResult("400", "验证码错误");
        }
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
        if (!phoneResult.getCode().equals("200")) {
            return new WebResult("400", phoneResult.getMsg());
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
            return new WebResult("400", "帐号或密码错误");
        }
        HashMap<String, Object> map = new HashMap();
        map.put("user", finduserbyphone);

        //添加token
        String token = UUID.randomUUID().toString();
        //pc端设置半年缓存过期
        boolean isSet = redisUtil.set(token, finduserbyphone.getUserId(), 6 * 30 * 24 * 60 * 60);
        if (!isSet) {
            return new WebResult("400", "登录失败");
        }
        boolean b = redisUtil.hasKey(token);
        map.put("token",token) ;
        WebResult result = new WebResult("200", "登录成功", map);
        return result;
    }

    /**
     * 修改密码
     *
     * @param phoneVo
     * @return
     */
    @Transactional
    @Override
    public WebResult updatePassword(PhoneVo phoneVo) {
        //String phone, String newPassword, String nextPass, String securityCode
        String newPassword = phoneVo.getNewPass();
        String nextPass = phoneVo.getNextPass();
        String phone = phoneVo.getPhone();
        User user = new User();
        user.setPhone(phone);
        //手机号码格式验证
        WebResult phoneResult = isTure(user);
        if (!phoneResult.getCode().equals("200")) {
            return new WebResult("400", phoneResult.getMsg());
        }
        //密码验证
        if (StringUtils.isBlank(newPassword) || StringUtils.isBlank(nextPass)) {
            return new WebResult("400", "密码不能为空");
        }
        int l = newPassword.length();
        if (l < 6 || l > 16) {
            return new WebResult("400", "密码长度必须要在6-16之间");
        }

        User findUser = userMapper.findUserByPhone2(phone);
        //判段手机号是否错误
        if (findUser == null) {
            return new WebResult("400", "手机号不存在");
        }
        if (!newPassword.equals(nextPass)) {
            return new WebResult("400", "输入的两次密码不一致");
        }

        String newPasswordMd5 = MD5Utils.md5Password(newPassword);
        //短信验证码
        Object value = redisUtil.get("sendSms-" + user.getPhone());
        if (value == null) {
            return new WebResult("400", "验证码失效");
        }
        if (!phoneVo.getSecurityCode().equals(value.toString())) {
            return new WebResult("400", "验证码错误");
        }

        userMapper.updateByPhone(phone, newPasswordMd5);
        WebResult result = new WebResult();
        result.setCode("200");
        result.setMsg("修改密码成功");
        return result;
    }

    /**
     * 修改密码 （根据原密码修改）
     *
     * @param phoneVo
     * @return
     */
    @Transactional
    @Override
    public WebResult updatePasswordByOldPass(PhoneVo phoneVo) {
        User findUserByOldPass = userMapper.selectById(phoneVo.getUserId());
        if (findUserByOldPass == null) {
            return new WebResult("400", "未找到对应用户");
        }
        String newPass = phoneVo.getNewPass();
        String nextPass = phoneVo.getNextPass();
        if(StringUtils.isBlank(phoneVo.getOldPass())) {
            return new WebResult("400", "原密码不能为空");
        }
        if(StringUtils.isBlank(newPass) || StringUtils.isBlank(nextPass)) {
            return new WebResult("400", "新密码不能为空");
        }
        String oldPass = MD5Utils.md5Password(phoneVo.getOldPass());
        if(!findUserByOldPass.getPassword().equals(oldPass)) {
            return new WebResult("400", "原密码错误");
        }
        //密码验证
        int l = newPass.length();
        if (l < 6 || l > 16) {
            return new WebResult("400", "密码长度必须要在6-16之间");
        }
        if (!newPass.equals(nextPass)) {
            return new WebResult("400", "输入的两次密码不一致");
        }
        userMapper.updateByPhone(findUserByOldPass.getPhone(), MD5Utils.md5Password(newPass));
            return new WebResult("200", "修改成功");
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
        if (StringUtils.isBlank(user.getIdentityCard())){
            return  new WebResult("400","身份证号码不能为空");
        }
        if (StringUtils.isBlank(user.getUserName())){
            return  new WebResult("400","用户名不能为空");
        }
        if (StringUtils.isBlank(user.getIdentityCard())){
            return  new WebResult("400","身份证号码不能为空");
        }
            if (user.getSex()==null){
            return  new WebResult("400","性别未填*");
            }
        //手机号码格式验证
        WebResult phoneResult = isTure(user);
        if (!phoneResult.getCode().equals("200")) {
            return new WebResult("400", phoneResult.getMsg(), "");
        }
        //固定电话的验证
        PhoneTest phoneTest = new PhoneTest();
        if(!StringUtils.isBlank(user.getTelephone())) {
            Boolean isPhone = phoneTest.isPhone(user.getTelephone());
            if (isPhone == false) {
                return new WebResult("400", "输入电话格式有误", "");
            }
        }
        //紧急联系人一手机号验证
        if (StringUtils.isBlank(user.getEmergencyPhone1())) {
            return new WebResult("400", "紧急联系人1电话不能为空");
        }
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (user.getEmergencyPhone1().length() != 11) {
            return new WebResult("400", "紧急联系人手机号应为11位", "");
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(user.getEmergencyPhone1());
            boolean isMatch = m.matches();

            if (!isMatch) {
                return new WebResult("400", "请输入正确手机号", "");
            }
        }
        //紧急联系人二手机号验证
        if (user.getEmergencyPhone2() != null) {
            if (user.getEmergencyPhone2().length() != 11) {
                return new WebResult("400", "紧急联系人手机号应为11位", "");
            } else {
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(user.getEmergencyPhone2());
                boolean isMatch = m.matches();
                if (!isMatch) {
                    return new WebResult("400", "请输入正确手机号", "");
                }
            }
        }
        if (StringUtils.isBlank(user.getEmergencyContact1())) {
            return new WebResult("400", "请输入紧急联系人1", "");
        }
        if (StringUtils.isBlank(user.getEmergencyRelation1())) {
            return new WebResult("400", "请输入紧急联系人关系1", "");
        }
        //身份证格式校验
        boolean testCard = this.isIDNumber(user.getIdentityCard());
        if (testCard == false) {
            return new WebResult("400", "输入身份证格式有误", "");
        }
        //保存
        User findUser = userMapper.queryByItemsIdAndSchoolId(user.getUserId(), user.getSchoolId());
        if (findUser == null || findUser.getSchoolId() == null) {
            user.setCheckin(0);
            userMapper.saveInfoItems(user);
            return new WebResult("200", "保存成功", "");
        } else {
            userMapper.updateInfoItems(user);
            return new WebResult("200", "修改成功", "");
        }
    }

    /**
     * 更换手机号
     *
     * @param phoneVo
     * @return
     */
    @Transactional
    @Override
    public WebResult updatePhone(PhoneVo phoneVo) {
        String newPhone = phoneVo.getNewPhone();
        String oldPhone = phoneVo.getOldPhone();
        String securityCode = phoneVo.getSecurityCode();
        User user = new User();
        user.setPhone(newPhone);
        WebResult phoneResult = isTure(user);
        if (!phoneResult.getCode().equals("200")) {
            return new WebResult("400", phoneResult.getMsg());
        }
        User findUser = userMapper.findUserByPhone2(newPhone);
        if (findUser != null) {
            return new WebResult("400", "该手机号已被使用");
        }
        //短信验证码
        Object value = redisUtil.get("sendSms-" + newPhone);
        if (value == null) {
            return new WebResult("400", "验证码失效");
        }
        if (!securityCode.equals(value.toString())) {
            return new WebResult("400", "验证码错误");
        }
        userMapper.updatePhone(oldPhone, newPhone);
        return new WebResult("200", "更换成功");
    }
    //退出登录
    @Override
    public WebResult userQuit(String token) {
        redisUtil.delete(token);
        return new WebResult("200", "退出成功", "");
    }
    //查询基本信息接口
    @Override
    public WebResult selectInfo(User user) {
        if (user.getUserId() == null) {
            return new WebResult("400", "请选择学校", "");
        }
        User findUserInfo = userMapper.queryByItemsIdAndSchoolId(user.getUserId(), user.getSchoolId());
        return new WebResult("200", "获取成功", findUserInfo);
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


    /**
     * 查询头像——vvtxw
     *
     * @param userId
     * @return
     */
    @Override
    public String queryAllAddress(Integer userId) {
        return userMapper.queryAllAddress(userId);
    }


    /**
     * 更新地址——vvtxw
     *
     * @param user
     */
    @Transactional
    @Override
    public void updateAddress(User user) {
        userMapper.updateAddress(user);
    }

    /**
     * 添加个性签名
     * @param user
     */
    @Transactional
    @Override
    public void createMycontext(User user, Integer userId) {
             user.setUserId(userId);
          userMapper.createMycontext(user);
    }

    @Override
    public WebResult myCourse(Integer userId) {
        List<Map<String, Object>> course = vacateMapper.selectUserCourse(userId, null);
        LinkedList<EduCourseVo> courseVos = new LinkedList<>();
        if(course != null) {
            for (Map<String, Object> map : course) {
                Object courseId = map.get("courseId");
                EduCourseVo courseVo = userMapper.queryCourseByCourseId((Integer) courseId);
                List<EduClass> eduClasses = eduClassMapper.queryCourseTimeByCourseId((Integer) courseId);
                courseVo.setClassList(eduClasses);
                courseVos.push(courseVo);
            }
        }
        return new WebResult("200", "查询成功", courseVos);
    }

}
