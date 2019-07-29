package com.ovft.configure.sys.web;

import com.ovft.configure.constant.ConstantClassField;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Admin;
import com.ovft.configure.sys.bean.Role;
import com.ovft.configure.sys.service.RoleService;
import com.ovft.configure.sys.utils.RedisUtil;
import com.ovft.configure.sys.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName 角色权限
 * @Author zqx
 * @Version 1.0
 **/

@RestController
@RequestMapping("/server/role")
public class RoleController {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RoleService roleService;

    /**
     * 角色列表
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/roleList")
    public WebResult roleList(HttpServletRequest request, @RequestBody PageVo pageVo) {
        return roleService.roleList(pageVo);
    }

    /**
     * 所有权限列表
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/allPermission")
    public WebResult allPermission(HttpServletRequest request) {
        return roleService.allPermission();
    }

    /**
     * 添加/修改 角色
     *
     * @param role
     * @return
     */
    @PostMapping(value = "/createRole")
    public WebResult createRole(HttpServletRequest request, @RequestBody Role role) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        Integer id = (Integer) o;
        Admin hget =(Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
        if(hget.getRole() != 0) {
            role.setSchoolId(hget.getSchoolId());
        }
        return roleService.createRole(role);
    }

    /**
     * 进入 角色修改页面
     * @param roleId
     * @return
     */
    @GetMapping(value = "findRole")
    public WebResult findRole(HttpServletRequest request, Integer roleId) {
        return roleService.findRole(roleId);
    }

    /**
     * 删除 角色
     *
     * @param roleId
     * @return
     */
    @GetMapping(value = "/deleteRole")
    public WebResult deleteRole(@RequestParam(value = "roleId") Integer roleId) {
        return roleService.deleteRole(roleId);
    }


    /**
     * 获取权限按钮
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/allMenu")
    public WebResult allMenu(HttpServletRequest request) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        Integer id = (Integer) o;
        Admin hget =(Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
        return roleService.allMenu(hget.getAdminId(), hget.getSchoolId(), hget.getRole());
    }

}
