package com.ovft.configure.sys.web;

import com.ovft.configure.constant.ConstantClassField;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Admin;
import com.ovft.configure.sys.service.AdminService;
import com.ovft.configure.sys.utils.RedisUtil;
import com.ovft.configure.sys.vo.PageVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName AdminController
 * @Author zqx
 * @Date 2019/4/10 15:45
 * @Version 1.0
 **/

@RestController
@RequestMapping("/server/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 登录
     *
     * @param admin
     * @return
     */
    @PostMapping(value = "/login")
    public WebResult login(@RequestBody Admin admin) {
        return adminService.login(admin);
    }

    /**
     * 修改密码
     *
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @PostMapping(value = "/updatePassword")
    public WebResult updatePasword(HttpServletRequest request, @RequestParam(value = "oldPassword") String oldPassword,
                                   @RequestParam(value = "newPassword") String newPassword) {
        Integer adminId = (Integer) request.getAttribute("adminId");
        return adminService.updatePassword(adminId, oldPassword, newPassword);
    }

    /**
     * 修改手机号
     *
     * @param newPhone
     * @return
     */
    @PostMapping(value = "/updatePhone")
    public WebResult updatePhone(HttpServletRequest request, @RequestParam(value = "newPhone") String newPhone,
                                 @RequestParam(value = "securityCode") String securityCode) {
        Integer adminId = (Integer) request.getAttribute("adminId");
        return adminService.updatePhone(adminId, newPhone, securityCode);
    }

    /**
     * 添加管理员
     *
     * @param admin
     * @return
     */
    @PostMapping(value = "/create")
    public WebResult createAdmin(HttpServletRequest request, @RequestBody Admin admin) {
        return adminService.createAdmin(admin, 1);
    }

    /**
     * 教师列表
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/teacherList")
    public WebResult teacherList(HttpServletRequest request, @RequestBody PageVo pageVo) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if(o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
            Admin hget =(Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            if(hget.getRole() != 0) {
                pageVo.setSchoolId(hget.getSchoolId());
            }
            return adminService.teacherList(pageVo);
        }else {
            return new WebResult("400", "请先登录", "");
        }
    }

    /**
     * 添加教师
     *
     * @param admin
     * @return
     */
    @PostMapping(value = "/createTeacher")
    public WebResult createTeacher(HttpServletRequest request, @RequestBody Admin admin) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if(o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
            Admin hget =(Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            if(hget.getRole() != 0) {
                admin.setSchoolId(hget.getSchoolId());
            }
            return adminService.createAdmin(admin, 2);
        }else {
            return new WebResult("400", "请先登录", "");
        }
    }

    /**
     * 进入修改教师页面
     *
     * @param adminId
     * @return
     */
    @GetMapping(value = "/findTeacher")
    public WebResult findTeacher(@RequestParam(value = "adminId") Integer adminId) {
        return adminService.findTeacher(adminId);
    }

    /**
     * 修改管理员、教师
     *
     * @param admin
     * @return
     */
    @PostMapping(value = "/updateAdmin")
    public WebResult updateAdmin(@RequestBody Admin admin) {
        return adminService.updateAdmin(admin);
    }

    /**
     * 删除管理员、教师
     *
     * @param adminId
     * @return
     */
    @GetMapping(value = "/deleteAdmin")
    public WebResult deleteAdmin(@RequestParam(value = "adminId") Integer adminId) {
        return adminService.deleteAdmin(adminId);
    }

}
