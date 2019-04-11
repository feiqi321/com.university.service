package com.ovft.configure.sys.bean;

/**
 * 教室
 * @ClassName SchoolClass
 * @Author zqx
 * @Date 2019/4/11 17:11
 * @Version 1.0
 **/
public class SchoolClass {

    public Integer classId;
    public Integer schoolId;
    public String className;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
