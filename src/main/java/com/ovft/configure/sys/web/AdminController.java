package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Admin;
import com.ovft.configure.sys.service.AdminService;
import com.ovft.configure.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * @param admin
     * @return
     */
    @PostMapping(value = "/login")
    public WebResult login(@RequestBody Admin admin) {
        return adminService.login(admin);
    }

    /**
     * 修改密码
     * @param adminId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @PostMapping(value = "/updatePassword")
    public WebResult updatePasword(@RequestParam(value = "adminId")Integer adminId, @RequestParam(value = "oldPassword")String oldPassword,
                                   @RequestParam(value = "newPassword")String newPassword) {
        return adminService.updatePassword(adminId, oldPassword, newPassword);
    }

    /**
     * 添加管理员
     * @param admin
     * @return
     */
    @PostMapping(value = "/create")
    public WebResult createAdmin(@RequestBody Admin admin)  {
        return  adminService.createAdmin(admin);
    }


}
