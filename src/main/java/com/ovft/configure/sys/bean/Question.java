package com.ovft.configure.sys.bean;

/**
 * 调查问卷问题选项结果表
 **/
public class Question {
    private Integer qid;             //题目id
    private Integer sid;             //问卷调查主表记录id
    private String question;         //题目名称
    private String item1;            //选项一
    private Integer item1_num;       //选项一被选择的数量
    private String item1_grade;      //选项一的分数（权重）
    private String item1_image;      //选项一的图片（例如投票里面的选项图片）
    private String item2;
    private Integer item2_num;
    private String item2_grade;
    private String item2_image;
    private String item3;
    private Integer item3_num;
    private String item3_grade;
    private String item3_image;
    private String item4;
    private Integer item4_num;
    private String item4_grade;
    private String item4_image;
    private String item5;
    private Integer item5_num;
    private String item5_grade;
    private String item5_image;
    private String item6;
    private Integer item6_num;
    private String item6_grade;
    private String item6_image;
    private Integer tid;

    public String getItem1_image() {
        return item1_image;
    }

    public String getItem2_image() {
        return item2_image;
    }

    public String getItem3_image() {
        return item3_image;
    }

    public String getItem4_image() {
        return item4_image;
    }

    public String getItem5_image() {
        return item5_image;
    }

    public String getItem6_image() {
        return item6_image;
    }

    public void setItem1_image(String item1_image) {
        this.item1_image = item1_image;
    }

    public void setItem2_image(String item2_image) {
        this.item2_image = item2_image;
    }

    public void setItem3_image(String item3_image) {
        this.item3_image = item3_image;
    }

    public void setItem4_image(String item4_image) {
        this.item4_image = item4_image;
    }

    public void setItem5_image(String item5_image) {
        this.item5_image = item5_image;
    }

    public void setItem6_image(String item6_image) {
        this.item6_image = item6_image;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getQid() {
        return qid;
    }

    public Integer getSid() {
        return sid;
    }

    public String getQuestion() {
        return question;
    }

    public String getItem1() {
        return item1;
    }

    public Integer getItem1_num() {
        return item1_num;
    }

    public String getItem1_grade() {
        return item1_grade;
    }

    public String getItem2() {
        return item2;
    }

    public Integer getItem2_num() {
        return item2_num;
    }

    public String getItem2_grade() {
        return item2_grade;
    }

    public String getItem3() {
        return item3;
    }

    public Integer getItem3_num() {
        return item3_num;
    }

    public String getItem3_grade() {
        return item3_grade;
    }

    public String getItem4() {
        return item4;
    }

    public Integer getItem4_num() {
        return item4_num;
    }

    public String getItem4_grade() {
        return item4_grade;
    }

    public String getItem5() {
        return item5;
    }

    public Integer getItem5_num() {
        return item5_num;
    }

    public String getItem5_grade() {
        return item5_grade;
    }

    public String getItem6() {
        return item6;
    }

    public Integer getItem6_num() {
        return item6_num;
    }

    public String getItem6_grade() {
        return item6_grade;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public void setItem1_num(Integer item1_num) {
        this.item1_num = item1_num;
    }

    public void setItem1_grade(String item1_grade) {
        this.item1_grade = item1_grade;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public void setItem2_num(Integer item2_num) {
        this.item2_num = item2_num;
    }

    public void setItem2_grade(String item2_grade) {
        this.item2_grade = item2_grade;
    }

    public void setItem3(String item3) {
        this.item3 = item3;
    }

    public void setItem3_num(Integer item3_num) {
        this.item3_num = item3_num;
    }

    public void setItem3_grade(String item3_grade) {
        this.item3_grade = item3_grade;
    }

    public void setItem4(String item4) {
        this.item4 = item4;
    }

    public void setItem4_num(Integer item4_num) {
        this.item4_num = item4_num;
    }

    public void setItem4_grade(String item4_grade) {
        this.item4_grade = item4_grade;
    }

    public void setItem5(String item5) {
        this.item5 = item5;
    }

    public void setItem5_num(Integer item5_num) {
        this.item5_num = item5_num;
    }

    public void setItem5_grade(String item5_grade) {
        this.item5_grade = item5_grade;
    }

    public void setItem6(String item6) {
        this.item6 = item6;
    }

    public void setItem6_num(Integer item6_num) {
        this.item6_num = item6_num;
    }

    public void setItem6_grade(String item6_grade) {
        this.item6_grade = item6_grade;
    }
}
