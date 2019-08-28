package com.ovft.configure.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author zqx
 * @create 2019-04-17 10:37
 */
public class PageVo {

    private  Integer sid;  //问卷调查的id

    private  Integer tid;   //问卷调查内部栏目id

    private int pageSize;

    private int phone;    //用户手机号码

    private int pageNum;

    private String search;

    private String authorSearch;

    private Integer schoolId;

    private Integer checkin;

    private Integer wid;

    private String type;

    private Integer role;

    private Integer userId;

    private Integer isenable;

    private Integer isFree;

    private Integer topId;

    private Integer downId;

    private Integer courseId;

    private  String employer;    //人员(学员)分类

    private  String retired;  //退休状态

    private  String sex;

    private  String political;   //政治面貌

    private String educational;  // 文化程度

    private String  volunteer;    //是否是志愿者

    private Integer startAge;        //开始年龄

    private Integer afterAge;        //截止年龄

    private Integer payStatus;      //支付状态

    private Integer status;          //问卷列表的状态（对应edu_search_question表里面的status字段）

    private Integer   isAddQuestion;   //教师评价时是否已添加问卷   1:已添加 0：未添加

    private Integer did;

    public Integer getIsAddQuestion() {
        return isAddQuestion;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public void setIsAddQuestion(Integer isAddQuestion) {
        this.isAddQuestion = isAddQuestion;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getStartAge() {
        return startAge;
    }

    public Integer getAfterAge() {
        return afterAge;
    }

    public void setStartAge(Integer startAge) {
        this.startAge = startAge;
    }

    public void setAfterAge(Integer afterAge) {
        this.afterAge = afterAge;
    }

    public String getEmployer() {
        return employer;
    }

    public String getRetired() {
        return retired;
    }

    public String getSex() {
        return sex;
    }

    public String getPolitical() {
        return political;
    }

    public String getEducational() {
        return educational;
    }

    public String getVolunteer() {
        return volunteer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public void setRetired(String retired) {
        this.retired = retired;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public void setEducational(String educational) {
        this.educational = educational;
    }

    public void setVolunteer(String volunteer) {
        this.volunteer = volunteer;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Integer getTopId() {
        return topId;
    }

    public Integer getDownId() {
        return downId;
    }

    public void setTopId(Integer topId) {
        this.topId = topId;
    }

    public void setDownId(Integer downId) {
        this.downId = downId;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    public String getAuthorSearch() {
        return authorSearch;
    }

    public void setAuthorSearch(String authorSearch) {

        this.authorSearch = authorSearch;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getIsenable() {
        return isenable;
    }

    public void setIsenable(Integer isenable) {
        this.isenable = isenable;
    }

    public Integer getCheckin() {
        return checkin;
    }

    public void setCheckin(Integer checkin) {
        this.checkin = checkin;
    }

    public Integer getWid() {
        return wid;
    }

    public void setWid(Integer wid) {
        this.wid = wid;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {

        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
