package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Admin;
import com.ovft.configure.sys.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
