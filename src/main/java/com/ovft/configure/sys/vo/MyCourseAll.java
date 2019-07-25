package com.ovft.configure.sys.vo;

import java.util.Date;

public class MyCourseAll  {


    /**
     * 报名方式
     */
    private String statu;

    /**
     * 课程名称
     */
    private Integer id;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 结束时间
     */
    private Date endDate;
    /**
     * 老师名称
     */
    private String courseTeacher;
    /**
     * 所属学校id
     */
    private Integer schoolId;
    /**
     * 所属学校id
     */
    private String schoolName;
    /**
     * 所属学校id
     */
    private String Courseyear;
    /**
     * 教师评价图片
     */
    private String courseImage;
    /**
     * 课程id
     */
    private int courseId;

    private String userName;
    /**
     * 0-女 1-男
     */
    private Integer sex;

    private String phone;
    /**
     * 固定电话
     */
    private String telephone;

    /**
     * 居住地
     */

    private String area;

    /**
     * 居住地
     */
    private String areaId;


    /**
     * 地址
     */
    private String address;


    /**
     * 政治面貌
     */

    private String political;

    /**
     * 文化程度
     */
    private String educational;

    /**
     * 工作单位
     */
    private String position;

    /**
     * 退休状态
     */
    private String retired;

    /**
     * 职务
     */
    private String job;
    /**
     * 志愿者
     */
    private Integer volunteer;


    /**
     * 学员分类
     */
    private String employer;

    private String identityCard;

    private Integer age;

    //用户个性签名
    private String mycontext;

    //班级学员备注
    private String classRecord;

    public String getClassRecord() {
        return classRecord;
    }

    public void setClassRecord(String classRecord) {
        this.classRecord = classRecord;
    }

    public String getMycontext() {
        return mycontext;
    }

    public void setMycontext(String mycontext) {
        this.mycontext = mycontext;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getSex() {
        return sex;
    }

    public String getPhone() {
        return phone;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getArea() {
        return area;
    }

    public String getAreaId() {
        return areaId;
    }

    public String getAddress() {
        return address;
    }

    public String getPolitical() {
        return political;
    }

    public String getEducational() {
        return educational;
    }

    public String getPosition() {
        return position;
    }

    public String getRetired() {
        return retired;
    }

    public String getJob() {
        return job;
    }

    public Integer getVolunteer() {
        return volunteer;
    }

    public String getEmployer() {
        return employer;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public void setEducational(String educational) {
        this.educational = educational;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setRetired(String retired) {
        this.retired = retired;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setVolunteer(Integer volunteer) {
        this.volunteer = volunteer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseImage() {
        return courseImage;
    }

    public void setCourseImage(String courseImage) {
        this.courseImage = courseImage;
    }

    public String getCourseyear() {
        return Courseyear;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public void setCourseyear(String courseyear) {
        Courseyear = courseyear;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getCourseTeacher() {
        return courseTeacher;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setCourseTeacher(String courseTeacher) {
        this.courseTeacher = courseTeacher;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public String getStatu() {
        return statu;
    }
}
