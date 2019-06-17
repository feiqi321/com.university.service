package com.ovft.configure.sys.bean;

import java.io.Serializable;

/**
 *      权限
 **/
public class Permission implements Serializable {

    private Integer permissionId;
    private String permissionName;
    private String ChineseName;
    private String url;
    private String remarks;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getChineseName() {
        return ChineseName;
    }

    public void setChineseName(String chineseName) {
        ChineseName = chineseName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
