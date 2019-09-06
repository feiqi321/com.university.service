package com.ovft.configure.sys.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author vvtxw
 * @create 2019-08-28 16:45
 */
public class EduVersion implements Serializable {

    private Integer id;
    //发布时间
    private Date  publishTime;
    //版本名称
    private String versionName;
    //更新内容
    private String updateContent;
    //版本编号
    private String versionCode;
    //版本地址
    private String versionUrl;
    //0 没发  1 发了
    private Integer publishStatus;
    //1 ios  2 android 3 windowphone
    private Integer applyTo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionUrl() {
        return versionUrl;
    }

    public void setVersionUrl(String versionUrl) {
        this.versionUrl = versionUrl;
    }

    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    public Integer getApplyTo() {
        return applyTo;
    }

    public void setApplyTo(Integer applyTo) {
        this.applyTo = applyTo;
    }
}
