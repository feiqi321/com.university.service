package com.ovft.configure.sys.bean;

import java.util.Date;

/**
 * @author vvtxw
 * @create 2019-04-24 10:44
 */
public class file {
    private Date lastModified;
    private Date lastModifiedDate;
    private String name;
    private Integer size;
    private String type;
    private String webkitRelativePath;

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWebkitRelativePath() {
        return webkitRelativePath;
    }

    public void setWebkitRelativePath(String webkitRelativePath) {
        this.webkitRelativePath = webkitRelativePath;
    }
}
