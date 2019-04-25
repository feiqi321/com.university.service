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
     *登录
     * @return
     */
    @Override
    public WebResult login(Admin admin) {
        if(!SecurityUtils.securityPhone(admin.getPhone())) {
            return new WebResult("400", "请输入正确的手机号", "");
        }
        if(StringUtils.isBlank(admin.getPassword())) {
            return new WebResult("400", "密码不能为空", "");
        }
        //查询该手机号是否已经存在
        Admin adminPhone = adminMapper.selectByPhone(admin.getPhone());
        if(adminPhone == null) {
            return new WebResult("400", "手机号不存在", "");
        }
        String pasword = MD5Utils.md5Password(admin.getPassword());
        if(!pasword.equals(adminPhone.getPassword())) {
            return new WebResult("400", "密码错误", "");
        }
        HashMap<String, Object> map = new HashMap();
        map.put("admin", adminPhone);

        //添加token
        String token = UUID.randomUUID().toString();
        //pc端设置半个小时缓存过期
        boolean isSet =  redisUtil.set(token, adminPhone.getAdminId(), ConstantClassField.PC_CACHE_EXPIRATION_TIME);
        if(!isSet) {
            return new WebResult("400", "登录失败", "");
        }
        map.put("token", token);
        WebResult result = new WebResult("200", "登录成功", map);
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
        if(StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword)) {
            return new WebResult("400", "密码不能为空", "");
        }
        if (newPassword.length() < 6 || newPassword.length() > 16 ) {
            return new WebResult("400", "密码长度必须要在6-16之间", "");
        }
        String password = MD5Utils.md5Password(oldPassword);
        if(!admin.getPassword().equals(password)) {
            return new WebResult("400", "原密码错误", "");
        }
        password = MD5Utils.md5Password(newPassword);
        adminMapper.updateByPassword(adminId, password);
        return new WebResult("200", "修改成功", "");
    }

    /**
     * 修改手机号
     * @param adminId
     * @param newPhone
     * @return
     */
    @Override
    public WebResult updatePhone(Integer adminId, String newPhone, String securityCode) {
        if(!SecurityUtils.securityPhone(newPhone)) {
            return new WebResult("400", "请输入正确的手机号", "");
        }
        //查询该手机号是否已经存在
        Admin adminPhone = adminMapper.selectByPhone(newPhone);
        if(adminPhone != null) {
            return new WebResult("400", "手机号已存在", "");
        }

        Object value = redisUtil.get("sendSms-" + newPhone);
        if(value == null) {
            return new WebResult("400", "验证码失效", "");
        }
        if(!securityCode.equals(value.toString())) {
            return new WebResult("400", "验证码错误", "");
        }
        adminPhone = new Admin();
        adminPhone.setAdminId(adminId);
        adminPhone.setPhone(newPhone);
        adminMapper.updateByPrimary(adminPhone);

        return new WebResult("200", "修改成功", "");
    }

    @Override
    public WebResult findTeacher(Integer adminId) {
        Admin admin = adminMapper.selectById(adminId);
        School school = schoolMapper.selectById(admin.getSchoolId());
        AdminVo adminVo = new AdminVo(admin);
        adminVo.setSchoolName(school==null?"":school.getSchoolName());
        return new WebResult("200", "查询成功", adminVo);
    }

    /**
     *修改管理员、教师
     * @param admin
     * @return
     */
    @Transactional
    @Override
    public WebResult updateAdmin(Admin admin) {
        if(StringUtils.isBlank(admin.getName())) {
            return new WebResult("400", "姓名不能为空", "");
        }
        if(!SecurityUtils.securityPhone(admin.getPhone())) {
            return new WebResult("400", "请输入正确的手机号", "");
        }
        //查询该手机号是否已经存在
        Admin adminPhone = adminMapper.selectByPhone(admin.getPhone());
        if(adminPhone != null && adminPhone.getAdminId() != admin.getAdminId()) {
            return new WebResult("400", "手机号已存在", "");
        }
        adminMapper.updateByPrimary(admin);
        return new WebResult("200", "修改成功", "");
    }

    /**
     * 删除管理员、教师
     * @param adminId
     * @return
     */
    @Transactional
    @Override
    public WebResult deleteAdmin(Integer adminId) {
        adminMapper.deleteById(adminId);
        return new WebResult("200", "删除成功", "");
    }

    /**
     * 教师列表
     * @param pageVo
     * @return
     */
    @Override
    public WebResult teacherList(PageVo pageVo) {
        Integer schoolId = pageVo.getId();
//        if(schoolId == null) {
//            return new WebResult("400", "请选择学校", "");
//        }

        if(pageVo.getPageSize() == 0) {
            List<Map<String, Object>> teacherList = adminMapper.selectTeacherBySchool(schoolId);
            return new WebResult("200", "查询成功", teacherList);
        }
        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize(), "a.admin_id");
        List<Map<String, Object>> teacherList = adminMapper.selectTeacherBySchool(schoolId);
        PageInfo pageInfo = new PageInfo<>(teacherList);
        return new WebResult("200", "查询成功", pageInfo);
    }

    /**
     * 添加管理员、教师
     * @param admin
     * @return
     */
    @Transactional
    @Override
    public WebResult createAdmin(Admin admin, int role) {
        WebResult result = new WebResult();
        if(StringUtils.isBlank(admin.getName())) {
            return new WebResult("400", "用户名不能为空", "");
        }
        if(!SecurityUtils.securityPhone(admin.getPhone())) {
            return new WebResult("400", "请输入正确的手机号", "");
        }
        //查询该手机号是否已经存在
        Admin adminPhone = adminMapper.selectByPhone(admin.getPhone());
        if(adminPhone != null) {
            return new WebResult("400", "手机号已存在", "");
        }
        //添加教师时学校id不能为空
        if(role == 2) {
            if(admin.getSchoolId() != null) {
                return new WebResult("400", "请选择学校", "");
            }
        }

        //管理员初始密码为000000
        String password = "000000";
        admin.setPassword(MD5Utils.md5Password(password));
        //添加角色    1-管理员,2-教师
        admin.setRole(role);
        adminMapper.creatAdmin(admin);
        result.setCode("200");
        result.setMsg("添加成功");
        result.setData(admin);
        return result;
    }


}
