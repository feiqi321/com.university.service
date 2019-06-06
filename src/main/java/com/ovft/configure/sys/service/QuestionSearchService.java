package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Question;
import com.ovft.configure.sys.bean.QuestionItem;
import com.ovft.configure.sys.bean.SearchQuestion;
import com.ovft.configure.sys.vo.PageVo;

//问卷调查
public interface QuestionSearchService {
    //添加问卷调查主题（SearchQuestion）
    public WebResult createSearchQuestion(SearchQuestion searchQuestion);
//     //添加问卷题目（SearchQuestion）
//     public WebResult createQuestion(Question question);
//     //添加问卷题目选项（SearchQuestion）
//     public WebResult createQuestionItem(QuestionItem questionItem);
     //删除一篇问卷题目及选项
     public WebResult deleteSearchQuestion(Integer sid);
     //问卷调查列表
     public WebResult findSearchQuestionAll(PageVo pageVo);



 }
