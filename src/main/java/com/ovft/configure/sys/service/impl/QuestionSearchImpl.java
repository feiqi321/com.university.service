package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.AnswerRecord;
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
import java.util.HashMap;
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
        if (searchQuestion.getSid() == null) {
            searchQuestion.setCreateTime(new Date());
        }
        searchQuestion.setUpdateTime(new Date());
        if (searchQuestion.getSid() == null) {
            questionSearchMapper.createSearchQuestion(searchQuestion);

            //批量添加问题及选项

            List<Question> questions = searchQuestion.getQuestions();
            //设置question的sid与问卷主题标的主键sid进行绑定
            for (int i = 0; i < searchQuestion.getQuestions().size(); i++) {
                questions.get(i).setSid(searchQuestion.getSid());

            }

            questionSearchMapper.insertBigQuestionItem(questions);
            return new WebResult("200", "添加成功", "");

        } else {
            questionSearchMapper.updateSearchQuestion(searchQuestion);
            List<Question> questions = searchQuestion.getQuestions();
            questionSearchMapper.updateBigQuestionItem(questions);
            return new WebResult("200", "修改成功", "");
        }
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
        if (pageVo.getPageSize() == 0) {
            //进入详情页
            if (pageVo.getSid() != null) {
                List<Question> questions = questionSearchMapper.findQuestionAll(pageVo.getSid(), pageVo.getTid(), pageVo.getSearch());
                List<SearchQuestion> searchQuestionAll2 = questionSearchMapper.findSearchQuestionAll(pageVo.getSid(), pageVo.getSchoolId(), pageVo.getTid(), pageVo.getSearch());
                searchQuestionAll2.get(0).setQuestions(questions);
                return new WebResult("200", "进入详情页", searchQuestionAll2.get(0));
            }
            List<SearchQuestion> searchQuestionAll = questionSearchMapper.findSearchQuestionAll(null, pageVo.getSchoolId(), pageVo.getTid(), pageVo.getSearch());
            return new WebResult("200", "查询成功", searchQuestionAll);
        }
        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());
        //进入详情页
        if (pageVo.getSid() != null) {
            List<Question> questions = questionSearchMapper.findQuestionAll(pageVo.getSid(), pageVo.getTid(), pageVo.getSearch());
            List<SearchQuestion> searchQuestionAll2 = questionSearchMapper.findSearchQuestionAll(pageVo.getSid(), pageVo.getSchoolId(), pageVo.getTid(), pageVo.getSearch());
            searchQuestionAll2.get(0).setQuestions(questions);
            return new WebResult("200", "进入详情页", searchQuestionAll2.get(0));
        }
        List<SearchQuestion> searchQuestionAll2 = questionSearchMapper.findSearchQuestionAll(null, pageVo.getSchoolId(), pageVo.getTid(), pageVo.getSearch());

        PageInfo pageInfo = new PageInfo<>(searchQuestionAll2);
        return new WebResult("200", "查询成功", pageInfo);
    }

    //添加用户调查结果记录
    @Transactional
    @Override
    public WebResult createAnswerRecord(AnswerRecord answerRecord) {
        List<Integer> answerNums = answerRecord.getAnswerNums();
        //找到对应主题的题目及选项(选项及分数权重的集合)的集合，主要针对教师评价
        List<Question> questions = questionSearchMapper.findQuestionAllAndGrade(answerRecord.getList().get(0).getSid(), answerRecord.getList().get(0).getTid(), null);
        //当类型是二,即教师评价的时候需要对答题总分进行统计
        if (answerRecord.getList().get(0).getTid() == 2) {
            Integer total = 0;   //用户答题总分记录
            for (int i = 0; i < answerRecord.getList().size(); i++) {
                switch (answerNums.get(i)) {
                    case 1:
                        total = questions.get(i).getItem1_grade() + total;
                        break; //可选
                    case 2:
                        //语句
                        total = questions.get(i).getItem2_grade() + total;
                        break; //可选
                    case 3:
                        //语句
                        total = questions.get(i).getItem3_grade() + total;
                        break; //可选
                    case 4:
                        //语句
                        total = questions.get(i).getItem4_grade() + total;
                        break; //可选
                    case 5:
                        //语句
                        total = questions.get(i).getItem5_grade() + total;
                        break; //可选
                    case 6:
                        //语句
                        total = questions.get(i).getItem6_grade() + total;
                        break; //可选
                    default:
                        total = total + 0;    //当前题目用户没有填任何选项的情况（没做）

                }

            }
            answerRecord.setTotalGrade(total);
        }
        //用户所填的答案结果组成String类型,然后存入数据库
        String answer = "";
        HashMap<Integer, String> answerValues = answerRecord.getAnswerValues();
        for (int n = 1; n <= answerValues.size(); n++) {
            if (n == 1) {
                answer = n + "," + answerValues.get(n) + ";";
            } else {
                answer = +n + "," + answerValues.get(n) + ";";
            }
        }

          //记录选项的的被选择总量（即：每个用户选择对应选项就会让其加一）
        for (int i = 0; i < answerRecord.getList().size(); i++) {
            switch (answerNums.get(i)) {
                case 1:
                    Integer questionItemTotal = questions.get(i).getItem1_num() + 1;
                    Question question1 = answerRecord.getList().get(i);
                    question1.setItem1_num(questionItemTotal);
                    questionSearchMapper.updateQuestionItemNum(question1);
                    break; //可选
                case 2:
                    //语句
                    Integer questionItemTotal2 = questions.get(i).getItem2_num() + 1;
                    Question question2 = answerRecord.getList().get(i);
                    question2.setItem1_num(questionItemTotal2);
                    questionSearchMapper.updateQuestionItemNum(question2);
                    break; //可选
                case 3:
                    //语句
                    Integer questionItemTotal3 = questions.get(i).getItem3_num() + 1;
                    Question question3 = answerRecord.getList().get(i);
                    question3.setItem1_num(questionItemTotal3);
                    questionSearchMapper.updateQuestionItemNum(question3);
                    break; //可选
                case 4:
                    //语句
                    Integer questionItemTotal4 = questions.get(i).getItem4_num() + 1;
                    Question question4 = answerRecord.getList().get(i);
                    question4.setItem1_num(questionItemTotal4);
                    questionSearchMapper.updateQuestionItemNum(question4);
                    break; //可选
                case 5:
                    //语句
                    Integer questionItemTotal5 = questions.get(i).getItem5_num() + 1;
                    Question question5= answerRecord.getList().get(i);
                    question5.setItem1_num(questionItemTotal5);
                    questionSearchMapper.updateQuestionItemNum(question5);
                    break; //可选
                case 6:
                    //语句
                    Integer questionItemTotal6 = questions.get(i).getItem6_num() + 1;
                    Question question6 = answerRecord.getList().get(i);
                    question6.setItem1_num(questionItemTotal6);
                    questionSearchMapper.updateQuestionItemNum(question6);
                    break; //可选
                default:

            }

        }
        answerRecord.setAnswer(answer);
        questionSearchMapper.createAnswerRecord(answerRecord);
        return new WebResult("200", "提交成功", "");
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
