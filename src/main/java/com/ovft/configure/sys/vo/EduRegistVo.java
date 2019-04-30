package com.ovft.configure.sys.vo;

import com.ovft.configure.sys.bean.EduRegist;

/**
 * @author vvtxw
 * @create 2019-04-30 11:22
 */
public class EduRegistVo extends EduRegist {

    private String courseName;
    private String schoolName;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
