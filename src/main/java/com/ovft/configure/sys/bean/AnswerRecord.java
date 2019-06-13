package com.ovft.configure.sys.bean;

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
      private List<Integer>  answerNums;
      private HashMap<Integer,String> answerValues;
      private List<Question> list;
      private Integer  totalGrade;

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

    public List<Integer> getAnswerNums() {
        return answerNums;
    }

    public void setAnswerValues(HashMap<Integer, String> answerValues) {
        this.answerValues = answerValues;
    }

    public HashMap<Integer, String> getAnswerValues() {
        return answerValues;
    }

    public void setAnswerNums(List<Integer> answerNums) {
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

    public String getAnswer() {
        return answer;
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
