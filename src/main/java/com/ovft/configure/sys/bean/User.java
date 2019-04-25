package com.ovft.configure.sys.bean;

/**
 * @ClassName User
 * @Author zqx
 * @Date 2019/4/9 10:13
 * @Version 1.0
 **/
public class User {

    private Integer userId;
    private String userName;
    /**
     * 0-女 1-男
     */
    private Integer sex;
    private String password;
    private Integer schoolId;
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

    /**
     * 紧急联系人1
     */
    private String emergencyContact1;

    /**
     * 紧急联系人关系1
     */
    private String emergencyRelation1;

    /**
     * 紧急联系人电话1
     */


    private String emergencyPhone1;
    private String emergencyContact2;
    private String emergencyRelation2;
    private String emergencyPhone2;
    //确认密码
    private String nextPass;
    /**
     * 学员审批
     */
    private Integer checkin;
    //身份证
    private String identityCard;
    //短信验证码
    private String securityCode;

    private Integer userItemId;

    public Integer getUserItemId() {
        return userItemId;
    }

    public void setUserItemId(Integer userItemId) {
        this.userItemId = userItemId;
    }

    public Integer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Integer volunteer) {
        this.volunteer = volunteer;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public String getNextPass() {
        return nextPass;
    }

    public void setNextPass(String nextPass) {
        this.nextPass = nextPass;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {

        this.political = political;
    }

    public String getEducational() {
        return educational;
    }

    public void setEducational(String educational) {

        this.educational = educational;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRetired() {
        return retired;
    }

    public void setRetired(String retired) {

        this.retired = retired;
    }


    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmergencyContact1() {
        return emergencyContact1;
    }

    public void setEmergencyContact1(String emergencyContact1) {
        this.emergencyContact1 = emergencyContact1;
    }

    public String getEmergencyRelation1() {
        return emergencyRelation1;
    }

    public void setEmergencyRelation1(String emergencyRelation1) {
        this.emergencyRelation1 = emergencyRelation1;
    }

    public String getEmergencyPhone1() {
        return emergencyPhone1;
    }

    public void setEmergencyPhone1(String emergencyPhone1) {
        this.emergencyPhone1 = emergencyPhone1;
    }

    public String getEmergencyContact2() {
        return emergencyContact2;
    }

    public void setEmergencyContact2(String emergencyContact2) {
        this.emergencyContact2 = emergencyContact2;
    }

    public String getEmergencyRelation2() {
        return emergencyRelation2;
    }

    public void setEmergencyRelation2(String emergencyRelation2) {
        this.emergencyRelation2 = emergencyRelation2;
    }

    public String getEmergencyPhone2() {
        return emergencyPhone2;
    }

    public void setEmergencyPhone2(String emergencyPhone2) {
        this.emergencyPhone2 = emergencyPhone2;
    }

    public Integer getCheckin() {
        return checkin;
    }

    public void setCheckin(Integer checkin) {
        this.checkin = checkin;
    }
}
