package com.ovft.configure.sys.service.impl;

import com.ovft.configure.constant.ConstantClassField;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Admin;
import com.ovft.configure.sys.dao.AdminMapper;
import com.ovft.configure.sys.service.AdminService;
import com.ovft.configure.sys.web.AdminController;
import com.ovft.configure.utils.MD5Utils;
import com.ovft.configure.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.UUID;

/**
 * @ClassName AdminServiceImpl
 * @Author zqx
 * @Date 2019/4/10 16:01
 * @Version 1.0
 **/
@Service
public class AdminServiceImpl implements AdminService {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    @Resource
    private AdminMapper adminMapper;

    @Autowired
    private RedisUtil redisUtil;

    /**
     *登录
     * @return
     */
    @Override
    public WebResult login(Admin admin) {
        if(StringUtils.isBlank(admin.getPhone())) {
            return new WebResult("400", "手机号不能为空");
        }
        if(StringUtils.isBlank(admin.getPassword())) {
            return new WebResult("400", "密码不能为空");
        }
        //查询该手机号是否已经存在
        Admin adminPhone = adminMapper.selectByPhone(admin.getPhone());
        if(adminPhone == null) {
            return new WebResult("400", "手机号不存在");
        }
        String pasword = MD5Utils.md5Password(admin.getPhone() + admin.getPassword());
        if(!pasword.equals(adminPhone.getPassword())) {
            return new WebResult("400", "密码错误");
        }
        HashMap<String, Object> map = new HashMap();
        map.put("admin", adminPhone);

        //添加token
        String token = UUID.randomUUID().toString();
        //pc端设置半个小时缓存过期
        boolean isSet =  redisUtil.set(token, adminPhone.getAdminId(), ConstantClassField.PC_CACHE_EXPIRATION_TIME);
        if(!isSet) {
            return new WebResult("400", "登录失败");
        }
        map.put("token", token);
        WebResult result = new WebResult("200", "", map);
        return result;
    }

    /**
     * 修改密码
     * @param adminId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @Transactional
    @Override
    public WebResult updatePassword(Integer adminId, String oldPassword, String newPassword) {
        Admin admin = adminMapper.selectById(adminId);
        String password = MD5Utils.md5Password(admin.getPhone() + oldPassword);
        if(!admin.getPassword().equals(password)) {
            return new WebResult("400", "原密码错误");
        }
        password = MD5Utils.md5Password(admin.getPhone() + newPassword);
        adminMapper.updateByPassword(adminId, password);
        return new WebResult("200", "修改成功");
    }

    /**
     * 添加管理员
     * @param admin
     * @return
     */
    @Transactional
    @Override
    public WebResult createAdmin(Admin admin) {
        WebResult result = new WebResult();
        if(StringUtils.isBlank(admin.getName())) {
            result.setCode("400");
            result.setMsg("用户名不能为空");
            return result;
        }
        if(StringUtils.isBlank(admin.getPhone())) {
            result.setCode("400");
            result.setMsg("手机号不能为空");
            return result;
        }
        //查询该手机号是否已经存在
        Admin adminPhone = adminMapper.selectByPhone(admin.getPhone());
        if(adminPhone != null) {
            return new WebResult("400", "手机号已存在");
        }
        //管理员初始密码为000000
        String password = admin.getPhone() + "000000";
        admin.setPassword(MD5Utils.md5Password(password));
        //添加角色    1-管理员
        admin.setRole(1);
        adminMapper.creatAdmin(admin);
        System.out.println("admin = " + admin.getAdminId());
        result.setCode("200");
        result.setData(admin);
        return result;
    }


}
