package com.ovft.configure.sys.bean;

import java.io.Serializable;

/**
 *      角色
 **/
public class Role implements Serializable {

    private Integer roleId;
    private String roleName;
    private Integer schoolId;
    private String schoolName;
    private Integer[] permisIds;

    public Integer[] getPermisIds() {
        return permisIds;
    }

    public void setPermisIds(Integer[] permisIds) {
        this.permisIds = permisIds;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }
}
