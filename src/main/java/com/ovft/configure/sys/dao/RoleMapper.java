package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.Permission;
import com.ovft.configure.sys.bean.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName RoleMapper
 * @Author zqx
 * @Version 1.0
 **/
@Mapper
public interface RoleMapper {
    /**
     * 角色
     */
    //根据角色id获取角色
    public Role selectById(@Param("roleId") Integer roleId);

    //根据adminId获取角色
    public List<Role> selectByAdminIdList(@Param("adminId") Integer adminId, @Param("schoolId") Integer schoolId);

    //根据schoolId获取角色
    public List<Role> selectBySchoolIdList(@Param("schoolId") Integer schoolId);

    //添加角色
    public void createRole(Role role);

    //修改角色
    public void updateRole(Role role);

    //删除角色
    public void deleteRole(@Param("roleId") Integer roleId);

    /**
     * 权限
     */
    //根据roleId获取权限
    public List<Permission> selectPermissionList(@Param("roleId") Integer roleId);
    //获取所有权限
    public List<Permission> selectPermissionAll(@Param("remarks") String remarks);

    public List<String> groupRemark();

    /**
     * 用户-角色
     */
    public void insertAdminRole(@Param("adminId") Integer adminId, @Param("roleId") Integer roleId, @Param("schoolId") Integer schoolId);

    public void deleteAdminRole(@Param("adminId") Integer adminId, @Param("roleId") Integer roleId, @Param("schoolId") Integer schoolId);

    /**
     * 角色 - 权限
     */
    public void insertByRolePermission(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);

    public void deleteRolePermission(@Param("roleId") Integer roleId);
}
