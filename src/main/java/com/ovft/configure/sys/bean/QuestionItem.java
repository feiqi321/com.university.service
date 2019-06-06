package com.ovft.configure.sys.bean;

/**
 * 调查问卷问题选项结果表
 **/
public class QuestionItem {
    private Integer id;
    private Integer sid;
    private Integer qid;
    private String itemChar;
    private String content;
    private Integer num;

    public Integer getId() {
        return id;
    }

    public Integer getQid() {
        return qid;
    }

    public String getContent() {
        return content;
    }

    public Integer getSid() {
        return sid;
    }

    public Integer getNum() {
        return num;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public String getItemChar() {
        return itemChar;
    }

    public void setItemChar(String itemChar) {
        this.itemChar = itemChar;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
