package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Role;
import com.ovft.configure.sys.vo.PageVo;

import java.util.HashSet;

public interface RoleService {

    public HashSet<String> selectByAdminId(Integer adminId, Integer schoolId);

    public WebResult allMenu(Integer adminId, Integer schoolId, Integer role);

    public WebResult roleList(PageVo pageVo);

    public WebResult createRole(Role role);

    public WebResult findRole(Integer roleId);

    public WebResult deleteRole(Integer roleId);

    public WebResult allPermission();

    public WebResult addAdminRole(Integer[] roleIds, Integer adminId, Integer schoolId);
}
