package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ovft.configure.constant.ConstantClassField;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Admin;
import com.ovft.configure.sys.bean.School;
import com.ovft.configure.sys.dao.AdminMapper;
import com.ovft.configure.sys.dao.SchoolMapper;
import com.ovft.configure.sys.service.AdminService;
import com.ovft.configure.sys.utils.MD5Utils;
import com.ovft.configure.sys.utils.RedisUtil;
import com.ovft.configure.sys.utils.SecurityUtils;
import com.ovft.configure.sys.vo.AdminVo;
import com.ovft.configure.sys.vo.PageVo;
import com.ovft.configure.sys.web.AdminController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @Resource
    private SchoolMapper schoolMapper;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 登录
     *
     * @return
     */
    @Override
    public WebResult login(Admin admin) {
        if (!SecurityUtils.securityPhone(admin.getPhone())) {
            return new WebResult("400", "请输入正确的手机号", "");
        }
        if (StringUtils.isBlank(admin.getPassword())) {
            return new WebResult("400", "密码不能为空", "");
        }
        //查询该手机号是否已经存在
        Admin adminPhone = adminMapper.selectByPhone(admin.getPhone());
        if (adminPhone == null) {
            return new WebResult("400", "手机号未注册", "");
        }
        String pasword = MD5Utils.md5Password(admin.getPassword());
        if (!pasword.equals(adminPhone.getPassword())) {
            return new WebResult("400", "密码错误", "");
        }
        HashMap<String, Object> map = new HashMap();
        map.put("admin", adminPhone);

        //添加token
        String token = UUID.randomUUID().toString();
        //pc端设置半个小时缓存过期
        boolean isSet = redisUtil.set(token, adminPhone.getAdminId(), ConstantClassField.PC_CACHE_EXPIRATION_TIME);
        if (!isSet) {
            return new WebResult("400", "登录失败", "");
        }
        map.put("token", token);

        //单点登录功能 single sign on   SSO
        Object hget = redisUtil.hget(ConstantClassField.SINGLE_SIGN_ON, adminPhone.getAdminId().toString());
        if(hget != null) {
            String oldToken = (String) hget;
            redisUtil.delete(oldToken);
        }
        redisUtil.hset(ConstantClassField.SINGLE_SIGN_ON, adminPhone.getAdminId().toString(), token);

        //存放用户信息
        boolean bo = redisUtil.hset(ConstantClassField.ADMIN_INFO, adminPhone.getAdminId().toString(), adminPhone);

        if(adminPhone.getSchoolId() != null) {
            School school = schoolMapper.selectById(adminPhone.getSchoolId());
            map.put("schoolName", school.getSchoolName());
        } else {
            map.put("schoolName", "");
        }

        WebResult result = new WebResult("200", "登录成功", map);
        return result;
    }

    /**
     * 修改密码
     *
     * @param adminId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @Transactional
    @Override
    public WebResult updatePassword(Integer adminId, String oldPassword, String newPassword) {
        Admin admin = adminMapper.selectById(adminId);
        if (StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword)) {
            return new WebResult("400", "密码不能为空", "");
        }
        if (newPassword.length() < 6 || newPassword.length() > 16) {
            return new WebResult("400", "密码长度必须要在6-16之间", "");
        }
        String password = MD5Utils.md5Password(oldPassword);
        if (!admin.getPassword().equals(password)) {
            return new WebResult("400", "原密码错误", "");
        }
        password = MD5Utils.md5Password(newPassword);
        adminMapper.updateByPassword(adminId, password);
        return new WebResult("200", "修改成功", "");
    }

    /**
     * 修改手机号
     *
     * @param adminId
     * @param newPhone
     * @return
     */
    @Override
    public WebResult updatePhone(Integer adminId, String newPhone, String securityCode) {
        if (!SecurityUtils.securityPhone(newPhone)) {
            return new WebResult("400", "请输入正确的手机号", "");
        }
        //查询该手机号是否已经存在
        Admin adminPhone = adminMapper.selectByPhone(newPhone);
        if (adminPhone != null) {
            return new WebResult("400", "手机号已存在", "");
        }

        Object value = redisUtil.get("sendSms-" + newPhone);
        if (value == null) {
            return new WebResult("400", "验证码失效", "");
        }
        if (!securityCode.equals(value.toString())) {
            return new WebResult("400", "验证码错误", "");
        }
        adminPhone = new Admin();
        adminPhone.setAdminId(adminId);
        adminPhone.setPhone(newPhone);
        adminMapper.updateByPrimary(adminPhone);

        return new WebResult("200", "修改成功", "");
    }

    @Override
    public WebResult findAdmin(Integer adminId, Integer schoolId) {
        Admin admin = adminMapper.selectById(adminId);
        AdminVo adminVo = new AdminVo(admin);
        if(admin.getRole().equals(2)) {
            List<Map<String, Object>> maps = adminMapper.selectTeacherBySchool(adminId, schoolId);
            if(maps.size() != 0) {
                Map<String, Object> map = maps.get(0);
                adminVo.setPost((String) map.get("post"));
                adminVo.setSchoolId((Integer) map.get("schoolId"));
            }
        }
        School school = schoolMapper.selectById(adminVo.getSchoolId());
        adminVo.setSchoolName(school == null ? "" : school.getSchoolName());
        return new WebResult("200", "查询成功", adminVo);
    }

    /**
     * 删除管理员、教师
     *
     * @param adminId
     * @return
     */
    @Transactional
    @Override
    public WebResult deleteAdmin(Integer adminId, Integer schoolId) {
        if(schoolId != null) {
            adminMapper.deleteTeacherSchool(adminId, schoolId);
            List<Map<String, Object>> maps = adminMapper.selectTeacherBySchool(adminId, null);
            if(maps.size() == 0) {
                adminMapper.deleteById(adminId);
            }
            return new WebResult("200", "删除成功", "");
        }
        adminMapper.deleteById(adminId);
        return new WebResult("200", "删除成功", "");
    }

    /**
     * 管理员/教师列表
     *
     * @param pageVo
     * @return
     */
    @Override
    public WebResult adminList(PageVo pageVo) {
        Integer schoolId = pageVo.getSchoolId();
        if (pageVo.getPageSize() == 0) {
            if(pageVo.getRole()!= null && pageVo.getRole().equals(2)) {
                List<Map<String, Object>> teacherList = adminMapper.selectTeacherBySchool(null, schoolId);
                return new WebResult("200", "查询成功", teacherList);
            }
            List<Map<String, Object>> teacherList = adminMapper.selectBySchool(pageVo.getRole(), schoolId);
            return new WebResult("200", "查询成功", teacherList);
        }
        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize(), "a.admin_id");
        List<Map<String, Object>> teacherList;
        if(pageVo.getRole()!= null && pageVo.getRole().equals(2)) {
            teacherList = adminMapper.selectTeacherBySchool(null, schoolId);
        } else {
            teacherList = adminMapper.selectBySchool(pageVo.getRole(), schoolId);
        }
        PageInfo pageInfo = new PageInfo<>(teacherList);
        return new WebResult("200", "查询成功", pageInfo);
    }

    /**
     * 添加/修改  管理员、教师
     *
     * @param admin
     * @return
     */
    @Transactional
    @Override
    public WebResult createAdmin(Admin admin) {
        if (StringUtils.isBlank(admin.getName())) {
            return new WebResult("400", "姓名不能为空", "");
        }
        if (!SecurityUtils.securityPhone(admin.getPhone())) {
            return new WebResult("400", "请输入正确的手机号", "");
        }
        //添加 管理员、教师时学校id不能为空
        if (admin.getSchoolId() == null) {
            return new WebResult("400", "请选择学校", "");
        }
        Admin adminPhone = adminMapper.selectByPhone(admin.getPhone());
        //添加教师
        if(admin.getRole().equals(2)) {
            if(admin.getAdminId() == null) {
                //查询该手机号是否已经存在
                if (adminPhone != null) {
                    admin.setAdminId(adminPhone.getAdminId());
                } else {
                    //初始密码为000000
                    String password = "000000";
                    admin.setPassword(MD5Utils.md5Password(password));
                    adminMapper.creatAdmin(admin);
                }
            } else {
                //查询该手机号是否已经存在
                if (adminPhone != null && adminPhone.getAdminId() != admin.getAdminId()) {
                    return new WebResult("400", "手机号已存在", "");
                }
                adminMapper.updateByPrimary(admin);
            }
            List<Map<String, Object>> maps = adminMapper.selectTeacherBySchool(admin.getAdminId(), admin.getSchoolId());
            if(maps.size() == 0) {
                adminMapper.createTeacherSchool(admin);
            } else {
                adminMapper.updateTeacherSchool(admin);
            }
            return new WebResult("200", "操作成功", "");
        }

        //添加管理员
        if(admin.getAdminId() == null) {
            //查询该手机号是否已经存在
            if (adminPhone != null) {
                return new WebResult("400", "手机号已存在", "");
            }

            //初始密码为000000
            String password = "000000";
            if(!StringUtils.isBlank(admin.getPassword()) && admin.getRole() != 2) {
                password = admin.getPassword();
            }
            admin.setPassword(MD5Utils.md5Password(password));

            adminMapper.creatAdmin(admin);
            return new WebResult("200", "添加成功", "");
        } else {
            //查询该手机号是否已经存在
            if (adminPhone != null && adminPhone.getAdminId() != admin.getAdminId()) {
                return new WebResult("400", "手机号已存在", "");
            }
            if(!StringUtils.isBlank(admin.getPassword())) {
                admin.setPassword(MD5Utils.md5Password(admin.getPassword()));
            }
            adminMapper.updateByPrimary(admin);
            return new WebResult("200", "修改成功", "");
        }
    }


}
