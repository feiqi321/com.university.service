package com.ovft.configure.sys.bean;

import java.util.Date;
import java.util.List;

/**
 * 调查问卷
 **/
public class SearchQuestion {

    private Integer sid;
    private Integer schoolId;   //报名学校id
    private String searchName;
    private String content;
    private Date createTime;
    private Date updateTime;
    private Date endTime;
    private String schoolName;
    private String author;  //创建人
    private String topImage;
    private Integer tid;    //投票内部分类栏目id
    private int visits;
    private List<Question> questions;
    private List<VoteItem> voteItems;    //投票接口时传入
    private  Integer topId;      //类型为2的时候  即教师评价时   线上报名id              注：用户的课程分为edu_payrecord、edu_offline_num两张表，因为在添加没门课的题目时须进行绑定而设计
    private  Integer downId;      //类型为2的时候  即教师评价时   线上报名id                   *在进入某门课程时会关联这两个id,为避免重复而设计
    private  int status;
    private   Integer courseId;



    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public List<VoteItem> getVoteItems() {
        return voteItems;
    }

    public void setVoteItems(List<VoteItem> voteItems) {
        this.voteItems = voteItems;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getTopImage() {
        return topImage;
    }

    public void setTopImage(String topImage) {
        this.topImage = topImage;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getSid() {
        return sid;
    }

    public String getSearchName() {
        return searchName;
    }

    public String getContent() {
        return content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getAuthor() {
        return author;
    }


    @Override
    public String toString() {
        return "SearchQuestion{" +
                "sid=" + sid +
                ", searchName='" + searchName + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", endTime=" + endTime +
                ", schoolName='" + schoolName + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
