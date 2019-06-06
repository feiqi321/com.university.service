package com.ovft.configure.sys.dao;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Question;
import com.ovft.configure.sys.bean.QuestionItem;
import com.ovft.configure.sys.bean.SearchQuestion;
import com.ovft.configure.sys.vo.PageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 调查问卷数据接口类
 **/
@Mapper
public interface QuestionSearchMapper {
    //添加问卷调查主题（SearchQuestion）
    public void createSearchQuestion(SearchQuestion searchQuestion);
    //添加问卷题目（SearchQuestion）
    public void createQuestion(Question question);
    //添加问卷题目选项（SearchQuestion）
    public void createQuestionItem(QuestionItem questionItem);
    //批量添加问卷题目及选项记录（SearchQuestion）
    public void insertBigQuestionItem(List<Question> question);
    //删除一篇问卷主题
    public void deleteSearchQuestion(Integer sid);
    //删除一篇问卷调查题目及选项
    public void deleteQuestionItem(Integer sid);
    //问卷调查列表
    public List<SearchQuestion> findSearchQuestionAll(@Param("sid")Integer sid, @Param("schoolId")Integer schoolId,@Param("tid")Integer tid,@Param("search") String search);
    //问卷调查列表
    public List<Question> findQuestionAll(@Param("sid")Integer sid,@Param("tid")Integer tid,@Param("search") String search);

}
