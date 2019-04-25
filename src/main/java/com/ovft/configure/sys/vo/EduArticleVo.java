package com.ovft.configure.sys.vo;

import com.ovft.configure.sys.bean.EduArticle;

/**
 * @author vvtxw
 * @create 2019-04-17 10:37
 */
public class EduArticleVo extends EduArticle {
    private String userName;

    private String schoolName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
