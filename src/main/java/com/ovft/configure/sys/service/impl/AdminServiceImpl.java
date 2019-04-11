package com.ovft.configure.sys.service.impl;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Admin;
import com.ovft.configure.sys.dao.AdminMapper;
import com.ovft.configure.sys.service.AdminService;
import com.ovft.configure.sys.web.AdminController;
import com.ovft.configure.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;

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

    /**
     *登录
     * @return
     */
    @Override
    public WebResult login(Admin admin) {
        if(StringUtils.isBlank(admin.getPhone())) {
            return new WebResult("error", "手机号不能为空");
        }
        if(StringUtils.isBlank(admin.getPassword())) {
            return new WebResult("error", "密码不能为空");
        }
        //查询该手机号是否已经存在
        Admin adminPhone = adminMapper.selectByPhone(admin.getPhone());
        if(adminPhone == null) {
            return new WebResult("error", "手机号不存在");
        }
        String pasword = MD5Utils.md5Password(admin.getPhone() + admin.getPassword());
        if(!pasword.equals(adminPhone.getPassword())) {
            return new WebResult("error", "密码错误");
        }
        HashMap<String, Object> map = new HashMap();
        map.put("admin", adminPhone);

        //TODO   添加redis
        map.put("token", "123456");

        WebResult result = new WebResult(map);
        result.setCode("success");
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
            return new WebResult("error", "原密码错误");
        }
        password = MD5Utils.md5Password(admin.getPhone() + newPassword);
        adminMapper.updateByPassword(adminId, password);
        return new WebResult("success", "修改成功");
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
            result.setCode("error");
            result.setMsg("用户名不能为空");
            return result;
        }
        if(StringUtils.isBlank(admin.getPhone())) {
            result.setCode("error");
            result.setMsg("手机号不能为空");
            return result;
        }
        //查询该手机号是否已经存在
        Admin adminPhone = adminMapper.selectByPhone(admin.getPhone());
        if(adminPhone != null) {
            return new WebResult("error", "手机号已存在");
        }
        //管理员初始密码为000000
        String password = admin.getPhone() + "000000";
        admin.setPassword(MD5Utils.md5Password(password));
        //添加角色    1-管理员
        admin.setRole(1);
        adminMapper.creatAdmin(admin);
        result.setCode("success");
        return result;
    }


}
