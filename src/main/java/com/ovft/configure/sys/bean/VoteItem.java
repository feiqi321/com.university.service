package com.ovft.configure.sys.bean;
/**
 *   投票选项实体类
 **/
public class VoteItem {
    private Integer  id;
    private  Integer sid;
    private String  item;
    private int num;
    private String  image;
    private String  question;
    private Integer userId;
    private Integer visits;

    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    public Integer getVisits() {
        return visits;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getId() {
        return id;
    }





    public String getItem() {
        return item;
    }

    public Integer getNum() {
        return num;
    }

    public String getImage() {
        return image;
    }

    public void setId(Integer id) {
        this.id = id;
    }




    public void setItem(String item) {
        this.item = item;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
