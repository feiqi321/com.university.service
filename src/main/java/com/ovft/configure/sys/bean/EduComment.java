package com.ovft.configure.sys.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class EduComment implements Serializable {
    private Integer comid;

    private String context;

    private Integer newsid;

    private Integer uid;

    private Date comtime;

    /**
     *  是否显示(0.隐藏 1.正常显示)
     */
    private Integer isshow;

    private Integer newtype;
    private Integer parentid;

    public Integer getNewtype() {
        return newtype;
    }

    public void setNewtype(Integer newtype) {
        this.newtype = newtype;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    private static final long serialVersionUID = 1L;

    public Integer getComid() {
        return comid;
    }

    public void setComid(Integer comid) {
        this.comid = comid;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Integer getNewsid() {
        return newsid;
    }

    public void setNewsid(Integer newsid) {
        this.newsid = newsid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getComtime() {
        return comtime;
    }

    public void setComtime(Date comtime) {
        this.comtime = comtime;
    }

    public Integer getIsshow() {
        return isshow;
    }

    public void setIsshow(Integer isshow) {
        this.isshow = isshow;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        EduComment other = (EduComment) that;
        return (this.getComid() == null ? other.getComid() == null : this.getComid().equals(other.getComid()))
            && (this.getContext() == null ? other.getContext() == null : this.getContext().equals(other.getContext()))
            && (this.getNewsid() == null ? other.getNewsid() == null : this.getNewsid().equals(other.getNewsid()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getComtime() == null ? other.getComtime() == null : this.getComtime().equals(other.getComtime()))
            && (this.getIsshow() == null ? other.getIsshow() == null : this.getIsshow().equals(other.getIsshow()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getComid() == null) ? 0 : getComid().hashCode());
        result = prime * result + ((getContext() == null) ? 0 : getContext().hashCode());
        result = prime * result + ((getNewsid() == null) ? 0 : getNewsid().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getComtime() == null) ? 0 : getComtime().hashCode());
        result = prime * result + ((getIsshow() == null) ? 0 : getIsshow().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", comid=").append(comid);
        sb.append(", context=").append(context);
        sb.append(", newsid=").append(newsid);
        sb.append(", uid=").append(uid);
        sb.append(", comtime=").append(comtime);
        sb.append(", isshow=").append(isshow);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}