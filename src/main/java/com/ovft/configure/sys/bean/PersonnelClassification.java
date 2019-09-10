package com.ovft.configure.sys.bean;
/**
 * @ClassName
 * @Author xzy      人员分类
 * @Date
 * @Version
 **/
public class PersonnelClassification {
    private Integer pid;
    private String  pName;
    private Integer schoolId;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    @Override
    public String toString() {
        return "PersonnelClassification{" +
                "pid=" + pid +
                ", pName='" + pName + '\'' +
                ", schoolId=" + schoolId +
                '}';
    }
}
