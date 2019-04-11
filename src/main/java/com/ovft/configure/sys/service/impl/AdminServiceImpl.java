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
