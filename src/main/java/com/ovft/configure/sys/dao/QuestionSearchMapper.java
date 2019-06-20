package com.ovft.configure.sys.dao;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.*;
import com.ovft.configure.sys.vo.MyCourseAll;
import com.ovft.configure.sys.vo.PageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.validation.constraints.Max;
import java.util.List;

/**
 * 调查问卷数据接口类
 **/
@Mapper
public interface QuestionSearchMapper {
    //添加问卷调查主题（SearchQuestion）
    public void createSearchQuestion(SearchQuestion searchQuestion);
    //修改问卷调查主题（SearchQuestion）
    public void updateSearchQuestion(SearchQuestion searchQuestion);
    //添加问卷题目（SearchQuestion）
    public void createQuestion(Question question);
    //添加问卷题目选项（SearchQuestion）
    public void createQuestionItem( QuestionItem questionItem);
    //批量添加投票选项（SearchQuestion）
    public void createBigVoteItem(List<VoteItem> list);
    //批量添加问卷题目及选项记录（SearchQuestion）
    public void insertBigQuestionItem( List<Question> list);
    //批量修改问卷题目及选项记录（SearchQuestion）
    public void updateBigQuestionItem(List<Question> list);
    //批量修改问卷题目及选项记录（SearchQuestion）
    public void updateBigVoteItem(List<VoteItem> list);
    //删除一篇问卷主题
    public void deleteSearchQuestion(Integer sid);
    //删除相关的问卷调查题目及选项
    public void deleteQuestionItem(Integer sid);
    //删除相关的问卷调查题目及选项(TopId)
    public void deleteQuestionItemByTopid(Integer TopId);
    //删除相关的问卷调查题目及选项(TopId)
    public void deleteQuestionItemBydownid(Integer downId);
    //删除相关的 投票题目及选项
    public void deleteVoteItembyId(Integer sid);
    //问卷调查列表
    public List<SearchQuestion> findSearchQuestionAll(@Param("sid")Integer sid,@Param("topId")Integer topId,@Param("downId")Integer downId, @Param("schoolId")Integer schoolId,@Param("tid")Integer tid,@Param("search") String search);
    //问卷调查题目列表
    public List<Question> findQuestionAll(@Param("sid")Integer sid,@Param("tid")Integer tid,@Param("topId")Integer topId,@Param("downId")Integer downId,@Param("search") String search);
    //问卷调查列表包含选项分数的
    public List<Question> findQuestionAllAndGrade(@Param("sid")Integer sid,@Param("tid")Integer tid,@Param("search") String search);
    //添加用户调查结果记录
    public  void  createAnswerRecord(AnswerRecord answerRecord);
    //查询用户调查总分结果记录
    public List<Integer> queryAnswerRecordGrade();
    //查询用户调查结果记录
    public List<AnswerRecord> findAnswerRecord(PageVo pageVo);
    //修改问卷题目及选项记录ItemNum（SearchQuestion）
    public void updateQuestionItemNum(Question question);
    //修改问卷调查的浏览量
    public void updateSearchQuestionVisits(@Param("visits")int visits,@Param("sid") int sid);
    //按热门条件问卷调查列表
    public List<SearchQuestion> findSearchQuestionAllbyVisits(@Param("sid")Integer sid,@Param("topId")Integer topId,@Param("downId")Integer downId, @Param("schoolId")Integer schoolId,@Param("tid")Integer tid,@Param("search") String search);
    //按综合条件问卷调查列表
    public List<SearchQuestion> findSearchQuestioncomposite(@Param("sid")Integer sid,@Param("topId")Integer topId,@Param("downId")Integer downId, @Param("schoolId")Integer schoolId,@Param("tid")Integer tid,@Param("search") String search);
    //查找对应用户
    public String findAnswerRecordbyUserId(@Param("uid") Integer userId,@Param("sid") Integer sid,@Param("topId") Integer topId,@Param("downId") Integer downId);
    //所有投票选项列表
    public List<VoteItem> findVoteItemAll();
    //我的所有课程
    public List<MyCourseAll> findMyCourseAlltop(PageVo pageVo);
    //我的所有课程
    public List<MyCourseAll> findMyCourseAlldown(PageVo pageVo);
    //查询投票对应选项
    public VoteItem findVoteItembyId(Integer id);
    //查询投票对应选项(通过sid)
    public List<VoteItem> findVoteItembySid(Integer sid);
    //查询投票对应选项(通过userId)
    public VoteItem findVoteItembyuserId(Integer userId);
    //修改投票对应选项
    public void updateVoteItem(@Param("num") int num,@Param("id") Integer id);
    //修改投票对应选项
    public void updateSearchQuestionStatues(Integer status,Integer sid);







}
