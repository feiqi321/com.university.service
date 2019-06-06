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
      private List<Question> questions;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public List<Question> getQuestions() { return questions; }

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
