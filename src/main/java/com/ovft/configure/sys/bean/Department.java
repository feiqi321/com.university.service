package com.ovft.configure.sys.bean;

/**
 * 学校院系实体类
 *
  */
public class Department {
    private Integer did;
    private Integer [] dids;
    private String departmentName;
    private Integer schoolId;
    private String schoolName;

    public Integer[] getDids() {
        return dids;
    }

    public void setDids(Integer[] dids) {
        this.dids = dids;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    @Override
    public String toString() {
        return "Department{" +
                "did=" + did +
                ", departmentName='" + departmentName + '\'' +
                ", schoolId=" + schoolId +
                ", schoolName='" + schoolName + '\'' +
                '}';
    }
}
