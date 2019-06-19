package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ovft.configure.constant.ConstantClassField;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Admin;
import com.ovft.configure.sys.bean.Role;
import com.ovft.configure.sys.bean.School;
import com.ovft.configure.sys.dao.AdminMapper;
import com.ovft.configure.sys.dao.RoleMapper;
import com.ovft.configure.sys.dao.SchoolMapper;
import com.ovft.configure.sys.service.AdminService;
import com.ovft.configure.sys.service.RoleService;
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
import java.util.*;

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
    @Autowired
    private RoleService roleService;
    @Resource
    private RoleMapper roleMapper;

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
        /*//管理员只属于多个学校时打开
        List<AdminVo> adminVos = adminMapper.selectByAdminAndSchool(adminPhone.getAdminId(), admin.getSchoolId(), null);
        if(adminPhone.getRole() != 0) {
            if(admin.getSchoolId() == null) {
                return new WebResult("400", "请选择学校", "");
            }
            adminPhone.setSchoolId(admin.getSchoolId());
        }*/
        if (adminPhone == null) {
            return new WebResult("400", "手机号未注册", "");
        }
        String pasword = MD5Utils.md5Password(admin.getPassword());
        if (!pasword.equals(adminPhone.getPassword())) {
            return new WebResult("400", "密码错误", "");
        }
        HashMap<String, Object> map = new HashMap();
        map.put("admin", adminPhone);
        /*//管理员只属于多个学校时打开
        map.put("admin", adminVos.get(0));*/

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
        /*//管理员只属于多个学校时打开
        boolean bo = redisUtil.hset(ConstantClassField.ADMIN_INFO, adminPhone.getAdminId().toString(), adminVos.get(0));*/
        //存放用户权限
        HashSet<String> permission = roleService.selectByAdminId(adminPhone.getAdminId(), adminPhone.getSchoolId());
        redisUtil.hset(ConstantClassField.ADMIN_PERMISSION, adminPhone.getAdminId().toString(), permission);

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
 /*   @Transactional
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
    }*/

    /**
     * 修改手机号
     *
     * @param adminId
     * @return
     */
    /*@Override
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
    }*/

    @Override
    public WebResult findAdmin(Integer adminId, Integer schoolId) {
        List<AdminVo> maps = adminMapper.selectByAdminAndSchool(adminId, schoolId, null);
        AdminVo admin = maps.get(0);
        //获取角色
        if(schoolId != null) {
            List<Role> roles = roleMapper.selectByAdminIdList(adminId, schoolId);
            Integer[] roleIds = new Integer[roles.size()];
            for (int i = 0; i < roles.size(); i++) {
                roleIds[i] = roles.get(i).getRoleId();
            }
            admin.setRoleIds(roleIds);
        }

        return new WebResult("200", "查询成功",admin);
    }

    /**
     * 删除管理员
     *
     * @param adminId
     * @return
     */
    @Transactional
    @Override
    public WebResult deleteAdmin(Integer adminId, Integer schoolId) {
        if(schoolId != null) {
            adminMapper.deleteAdminSchool(adminId, schoolId);
            List<AdminVo> maps = adminMapper.selectByAdminAndSchool(adminId, null, null);
            if(maps.size() == 0) {
                adminMapper.deleteById(adminId);
            }
            //删除 用户-角色
            roleMapper.deleteAdminRole(adminId, null, schoolId);
            return new WebResult("200", "删除成功", "");
        }
        adminMapper.deleteById(adminId);
        return new WebResult("200", "删除成功", "");
    }

    /**
     * 管理员列表
     *
     * @param pageVo
     * @return
     */
    @Override
    public WebResult adminList(PageVo pageVo) {
        Integer schoolId = pageVo.getSchoolId();
        if (pageVo.getPageSize() == 0) {
            List<AdminVo> adminList = adminMapper.selectByAdminAndSchool(null, schoolId, pageVo.getRole());
            return new WebResult("200", "查询成功", adminList);
        }
        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize(), "a.admin_id");
        List<AdminVo> adminList = adminMapper.selectByAdminAndSchool(null, schoolId, pageVo.getRole());
        PageInfo pageInfo = new PageInfo<>(adminList);
        return new WebResult("200", "查询成功", pageInfo);
    }

    //添加 管理员(带角色)
    @Transactional
    @Override
    public WebResult createAdminRole(AdminVo admin) {
        if (StringUtils.isBlank(admin.getName())) {
            return new WebResult("400", "姓名不能为空", "");
        }
        if (!SecurityUtils.securityPhone(admin.getPhone())) {
            return new WebResult("400", "请输入正确的手机号", "");
        }
        if(admin.getRole() == null) {
            return new WebResult("400", "请选择角色", "");
        }
        if(!admin.getRole().equals(0)) {
            //添加 学校管理员时学校id不能为空
            if (admin.getSchoolId() == null) {
                return new WebResult("400", "请选择学校", "");
            }
            //添加人员（role = 2,3）时角色列表不能为空
            if(admin.getRole().equals(2) || admin.getRole().equals(3)){
                if(admin.getRoleIds() == null || admin.getRoleIds().length == 0) {
                    return new WebResult("400", "请选择角色", "");
                }
            }
        }else {
            admin.setSchoolId(null);
        }
        Admin adminPhone = adminMapper.selectByPhone(admin.getPhone());
        //添加管理员
        if(admin.getAdminId() == null) {
            //查询该手机号是否已经存在
            if (adminPhone == null) {
                //初始密码为000000
                String password = "000000";
                if(!StringUtils.isBlank(admin.getPassword())) {
                    password = admin.getPassword();
                }
                admin.setPassword(MD5Utils.md5Password(password));
                adminMapper.creatAdmin(admin);
            } else {
                admin.setAdminId(adminPhone.getAdminId());
            }
        } else {
            //查询该手机号是否已经存在
            if (adminPhone != null && adminPhone.getAdminId() != admin.getAdminId()) {
                return new WebResult("400", "手机号已存在", "");
            }
            if(!StringUtils.isBlank(admin.getPassword())) {
                admin.setPassword(MD5Utils.md5Password(admin.getPassword()));
            }
            adminMapper.updateByPrimary(admin);
        }
        addAdminSchool(admin);
        if(admin.getRole().equals(2) || admin.getRole().equals(3)){
            roleService.addAdminRole(admin.getRoleIds(), admin.getAdminId(), admin.getSchoolId());
        }
        return new WebResult("200", "操作成功", "");
    }

    public void addAdminSchool(Admin admin) {
        List<AdminVo> maps = adminMapper.selectByAdminAndSchool(admin.getAdminId(), admin.getSchoolId(), null);
        if(maps.size() == 0) {
            adminMapper.createAdminSchool(admin);
        } else {
            adminMapper.updateAdminSchool(admin);
        }
    }

    // 根据手机号获取学校列表
    @Override
    public WebResult findSchoolByPhone(String phone) {
        boolean b = SecurityUtils.securityPhone(phone);
        if(!b) {
            return new WebResult("400", "请输入正确的手机号", "");
        }
        List<School> schoolList = adminMapper.selectByPhoneList(phone);
        return new WebResult("200", "操作成功", schoolList);
    }

}
