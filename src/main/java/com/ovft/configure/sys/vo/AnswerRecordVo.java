package com.ovft.configure.sys.vo;

public class AnswerRecordVo {
    private int titleId;
    private String answer;

    public int getTitleId() {
        return titleId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "AnswerRecordVo{" +
                "titleId=" + titleId +
                ", answer='" + answer + '\'' +
                '}';
    }
}
