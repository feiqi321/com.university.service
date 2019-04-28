package com.ovft.configure.sys.web;

import com.jfinal.aop.Before;
import com.ovft.configure.config.CORSInterceptor;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Admin;
import com.ovft.configure.sys.service.AdminService;
import com.ovft.configure.sys.utils.RedisUtil;
import com.ovft.configure.sys.vo.PageVo;
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
        Integer adminId = (Integer) request.getAttribute("adminId");
        admin.setAdminId(adminId);
        return adminService.createAdmin(admin, 1);
    }

    /**
     * 教师列表
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/teacherList")
    public WebResult teacherList(@RequestBody PageVo pageVo) {
        return adminService.teacherList(pageVo);
    }

    /**
     * 添加教师
     *
     * @param admin
     * @return
     */
    @PostMapping(value = "/createTeacher")
    public WebResult createTeacher(HttpServletRequest request, @RequestBody Admin admin) {
        Integer adminId = (Integer) request.getAttribute("adminId");
        admin.setAdminId(adminId);
        return adminService.createAdmin(admin, 2);
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
