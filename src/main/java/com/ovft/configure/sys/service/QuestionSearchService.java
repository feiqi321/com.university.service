package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.*;
import com.ovft.configure.sys.vo.LivePayVo;
import com.ovft.configure.sys.vo.PageVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

//问卷调查
public interface QuestionSearchService {
    //添加问卷调查主题（SearchQuestion）
    public WebResult createSearchQuestion(SearchQuestion searchQuestion);
//     //添加问卷题目（SearchQuestion）
//     public WebResult createQuestion(Question question);
//     //添加问卷题目选项（SearchQuestion）
//     public WebResult createQuestionItem(QuestionItem questionItem);
     //删除一篇问卷题目及选项
     public WebResult deleteSearchQuestion(Integer sid,Integer tid,Integer courseId);
     //删除一篇问卷题目及选项
     public WebResult deleteAnswerRecordOne(AnswerRecord answerRecord);
     //批量删除问卷题目及选项
     public WebResult BigDeleteAnswerRecord(AnswerRecord answerRecord);
     //问卷调查列表
     public WebResult findSearchQuestionAll(PageVo pageVo);
     //问卷调查列表
     public WebResult findServerSearchQuestionAll(PageVo pageVo);
     //按热门条件问卷调查列表
     public WebResult findSearchQuestionAllbyVisits(PageVo pageVo);
     //按综合条件问卷调查列表
     public WebResult findSearchQuestioncomposite(PageVo pageVo);
     //添加用户调查结果记录
     public WebResult createAnswerRecord(AnswerRecord answerRecord);
    //查询用户调查结果记录
    public WebResult findAnswerRecord(PageVo pageVo);
    //查询用户调查结果记录
    public WebResult findVoteRecord(PageVo pageVo);
    //提交用户调查结果记录
    public WebResult submitVoteRecord(AnswerRecord answerRecord,Integer id);
    //教师评价列表
    public WebResult findCourseTopic(PageVo pageVo);
    //教师评价列表
    public WebResult findMyCourseList(PageVo pageVo);
    //定时删除相关问卷（即：相关问卷到截止时间就会被删除）
    public void deleteScheduleTask();
    //删除对应问卷的一个题目
    public WebResult deleteQuestionOne(Question question);
    //删除对应问卷的一个题目
    public WebResult deleteVoteItemOne(VoteItem voteItem);
     //
    public WebResult findMyClassUsers(PageVo pageVo);

    // <!--换课 通过id编辑课程id,课程名字-->
    WebResult updateCourseById(EduPayrecord  eduPayrecord);

    //删除某条要退课的记录
    WebResult deleteById(EduPayrecord  eduPayrecord);

    //查看退课记录
    WebResult selectClassOut(LivePayVo livePayVo);





}
