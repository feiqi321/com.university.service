package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Question;
import com.ovft.configure.sys.bean.QuestionItem;
import com.ovft.configure.sys.bean.SearchQuestion;
import com.ovft.configure.sys.dao.QuestionSearchMapper;
import com.ovft.configure.sys.dao.SchoolMapper;
import com.ovft.configure.sys.service.QuestionSearchService;
import com.ovft.configure.sys.vo.EduArticleVo;
import com.ovft.configure.sys.vo.PageVo;
import net.sf.saxon.expr.instruct.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 调查问卷实现类
 **/
@Service
public class QuestionSearchImpl implements QuestionSearchService {
    @Resource
    private SchoolMapper schoolMapper;
    @Resource
    private QuestionSearchMapper questionSearchMapper;

    //添加问卷调查（SearchQuestion）
    @Transactional
    @Override
    public WebResult createSearchQuestion(SearchQuestion searchQuestion) {
        String schoolById = schoolMapper.findSchoolById(searchQuestion.getSchoolId());
        searchQuestion.setSchoolName(schoolById);
        searchQuestion.setCreateTime(new Date());
        searchQuestion.setUpdateTime(new Date());
        questionSearchMapper.createSearchQuestion(searchQuestion);
        //批量添加问题及选项

        List<Question> questions = searchQuestion.getQuestions();
        //设置question的sid与问卷主题标的主键id进行绑定
        for (int i = 0; i < searchQuestion.getQuestions().size(); i++) {
            questions.get(i).setSid(searchQuestion.getSid());

        }
        questionSearchMapper.insertBigQuestionItem(questions);
        return new WebResult("200", "添加成功", "");
    }

    //删除问卷调查（SearchQuestion）
    @Transactional
    @Override
    public WebResult deleteSearchQuestion(Integer sid) {
        questionSearchMapper.deleteSearchQuestion(sid);
        questionSearchMapper.deleteQuestionItem(sid);
        return new WebResult("200", "删除成功", "");
    }
    //问卷调查列表
    @Override
    public WebResult findSearchQuestionAll(PageVo pageVo) {
        if(pageVo.getPageSize() == 0) {
            //进入详情页
            if (pageVo.getSid()!=null) {
                List<Question> questions = questionSearchMapper.findQuestionAll(pageVo.getSid(), pageVo.getTid(), pageVo.getSearch());
                List<SearchQuestion> searchQuestionAll2 = questionSearchMapper.findSearchQuestionAll(pageVo.getSid(), pageVo.getSchoolId(),pageVo.getTid(), pageVo.getSearch());
                searchQuestionAll2.get(0).setQuestions(questions);
                return new WebResult("200", "进入详情页",  searchQuestionAll2.get(0));
            }
            List<SearchQuestion> searchQuestionAll = questionSearchMapper.findSearchQuestionAll(null, pageVo.getSchoolId(),pageVo.getTid(), pageVo.getSearch());
            return new WebResult("200", "查询成功", searchQuestionAll);
        }
        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());
        //进入详情页
        if (pageVo.getSid()!=null) {
            List<Question> questions = questionSearchMapper.findQuestionAll(pageVo.getSid(), pageVo.getTid(), pageVo.getSearch());
            List<SearchQuestion> searchQuestionAll2 = questionSearchMapper.findSearchQuestionAll(pageVo.getSid(), pageVo.getSchoolId(),pageVo.getTid(), pageVo.getSearch());
                      searchQuestionAll2.get(0).setQuestions(questions);
            return new WebResult("200", "进入详情页",  searchQuestionAll2.get(0));
        }
        List<SearchQuestion> searchQuestionAll2 = questionSearchMapper.findSearchQuestionAll(null, pageVo.getSchoolId(),pageVo.getTid(), pageVo.getSearch());

        PageInfo pageInfo = new PageInfo<>(searchQuestionAll2);
        return new WebResult("200", "查询成功", pageInfo);
    }


//    @Override
//    public WebResult createQuestion(Question question) {
//        return null;
//    }
//
//    //添加问卷题目选项（SearchQuestion）
//    @Transactional
//    @Override
//    public WebResult createQuestionItem(QuestionItem questionItem) {
//
//         questionSearchMapper.createQuestionItem(questionItem);
//        return new WebResult("200", "添加成功", "");
//    }
}
