package com.ovft.configure.sys.bean;

import com.ovft.configure.sys.vo.AnswerRecordVo;

import java.util.HashMap;
import java.util.List;

/**
 * 调查问卷用户答案记录列表
 **/
public class AnswerRecord {
      private Integer aid;
      private Integer uid;
      private String userName;
      private String schoolName;
      private String answer;
      private String title;
      private String adviceInfo;
      private int []  answerNums;
      private List<AnswerRecordVo> answerValues;
      private List<Question> list;
      private Integer  totalGrade;
      private Integer  sid;
      private Integer  topId;
      private Integer  downId;
      private Integer id;
      private String type;
      private int tid;
      private Integer courseId;  //教师评价对应的课程id
      private Integer [] aids;

    public Integer[] getAids() {
        return aids;
    }

    public void setAids(Integer[] aids) {
        this.aids = aids;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public List<AnswerRecordVo> getAnswerValues() {
        return answerValues;
    }

    public void setAnswerValues(List<AnswerRecordVo> answerValues) {
        this.answerValues = answerValues;
    }

    public List<Question> getList() {
        return list;
    }

    public void setList(List<Question> list) {
        this.list = list;
    }

    public Integer getTotalGrade() {
        return totalGrade;
    }

    public void setTotalGrade(Integer totalGrade) {
        this.totalGrade = totalGrade;
    }


    public int[] getAnswerNums() {
        return answerNums;
    }

    public void setAnswerNums(int[] answerNums) {
        this.answerNums = answerNums;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAdviceInfo(String adviceInfo) {
        this.adviceInfo = adviceInfo;
    }

    public Integer getAid() {
        return aid;
    }

    public Integer getUid() {
        return uid;
    }

    public String getUserName() {
        return userName;
    }

    public String getSchoolName() {
        return schoolName;
    }



    public String getTitle() {
        return title;
    }

    public String getAdviceInfo() {
        return adviceInfo;
    }

    @Override
    public String toString() {
        return "AnswerRecord{" +
                "aid=" + aid +
                ", uid=" + uid +
                ", userName='" + userName + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", answer='" + answer + '\'' +
                ", title='" + title + '\'' +
                ", adviceInfo='" + adviceInfo + '\'' +
                '}';
    }
}
