package com.ovft.configure.sys.bean;

/**
 * 调查问卷问题选项结果表
 **/
public class Question {
    private Integer qid;             //题目id
    private Integer sid;             //问卷调查主表记录id
    private String question;         //题目名称
    private String item1;            //选项一
    private int item1Num;       //选项一被选择的数量
    private int item1Grade;      //选项一的分数（权重）
    private String item1Image;      //选项一的图片（例如投票里面的选项图片）
    private String item2;
    private int item2Num;
    private int item2Grade;
    private String item2Image;
    private String item3;
    private int item3Num;
    private int item3Grade;
    private String item3Image;
    private String item4;
    private int item4Num;
    private int item4Grade;
    private String item4Image;
    private String item5;
    private int item5Num;
    private int item5Grade;
    private String item5Image;
    private String item6;
    private int item6Num;
    private int item6Grade;
    private String item6Image;
    private Integer tid;
    private  Integer topId;      //类型为2的时候  即教师评价时   线上报名id              注：用户的课程分为edu_payrecord、edu_offline_num两张表，因为在添加没门课的题目时须进行绑定而设计
    private  Integer downId;      //类型为2的时候  即教师评价时   线上报名id                   *在进入某门课程时会关联这两个id,为避免重复而设计

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

    public int getItem1Num() {
        return item1Num;
    }

    public int getItem1Grade() {
        return item1Grade;
    }

    public String getItem1Image() {
        return item1Image;
    }

    public String getItem2() {
        return item2;
    }

    public int getItem2Num() {
        return item2Num;
    }

    public int getItem2Grade() {
        return item2Grade;
    }

    public String getItem2Image() {
        return item2Image;
    }

    public String getItem3() {
        return item3;
    }

    public int getItem3Num() {
        return item3Num;
    }

    public int getItem3Grade() {
        return item3Grade;
    }


    public String getItem4() {
        return item4;
    }

    public int getItem4Num() {
        return item4Num;
    }

    public int getItem4Grade() {
        return item4Grade;
    }

    public String getItem4Image() {
        return item4Image;
    }

    public String getItem5() {
        return item5;
    }

    public int getItem5Num() {
        return item5Num;
    }

    public int getItem5Grade() {
        return item5Grade;
    }

    public String getItem5Image() {
        return item5Image;
    }

    public String getItem6() {
        return item6;
    }

    public int getItem6Num() {
        return item6Num;
    }

    public int getItem6Grade() {
        return item6Grade;
    }

    public String getItem6Image() {
        return item6Image;
    }

    public Integer getTid() {
        return tid;
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

    public void setItem1Num(int item1Num) {
        this.item1Num = item1Num;
    }

    public void setItem1Grade(int item1Grade) {
        this.item1Grade = item1Grade;
    }

    public void setItem1Image(String item1Image) {
        this.item1Image = item1Image;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public void setItem2Num(int item2Num) {
        this.item2Num = item2Num;
    }

    public void setItem2Grade(int item2Grade) {
        this.item2Grade = item2Grade;
    }

    public void setItem2Image(String item2Image) {
        this.item2Image = item2Image;
    }

    public void setItem3(String item3) {
        this.item3 = item3;
    }

    public void setItem3Num(int item3Num) {
        this.item3Num = item3Num;
    }

    public void setItem3Grade(int item3Grade) {
        this.item3Grade = item3Grade;
    }

    public String getItem3Image() {
        return item3Image;
    }

    public void setItem3Image(String item3Image) {
        this.item3Image = item3Image;
    }

    public void setItem4(String item4) {
        this.item4 = item4;
    }

    public void setItem4Num(int item4Num) {
        this.item4Num = item4Num;
    }

    public void setItem4Grade(int item4Grade) {
        this.item4Grade = item4Grade;
    }

    public void setItem4Image(String item4Image) {
        this.item4Image = item4Image;
    }

    public void setItem5(String item5) {
        this.item5 = item5;
    }

    public void setItem5Num(int item5Num) {
        this.item5Num = item5Num;
    }

    public void setItem5Grade(int item5Grade) {
        this.item5Grade = item5Grade;
    }

    public void setItem5Image(String item5Image) {
        this.item5Image = item5Image;
    }

    public void setItem6(String item6) {
        this.item6 = item6;
    }

    public void setItem6Num(int item6Num) {
        this.item6Num = item6Num;
    }

    public void setItem6Grade(int item6Grade) {
        this.item6Grade = item6Grade;
    }

    public void setItem6Image(String item6Image) {
        this.item6Image = item6Image;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }
}
