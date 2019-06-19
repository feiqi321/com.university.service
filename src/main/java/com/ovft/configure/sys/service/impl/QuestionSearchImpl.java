package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.*;
import com.ovft.configure.sys.dao.EduOfflineOrderMapper;
import com.ovft.configure.sys.dao.OrderMapper;
import com.ovft.configure.sys.dao.QuestionSearchMapper;
import com.ovft.configure.sys.dao.SchoolMapper;
import com.ovft.configure.sys.service.QuestionSearchService;
import com.ovft.configure.sys.vo.*;
import net.sf.saxon.expr.instruct.ForEach;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private OrderMapper orderMapper;
    @Resource
    private QuestionSearchMapper questionSearchMapper;
    @Resource
    private EduOfflineOrderMapper eduOfflineOrderMapper;

    //添加问卷调查（SearchQuestion）
    @Transactional
    @Override
    public WebResult createSearchQuestion(SearchQuestion searchQuestion) {
        String schoolById = schoolMapper.findSchoolById(searchQuestion.getSchoolId());
        searchQuestion.setSchoolName(schoolById);
        searchQuestion.setStatus(1);
        if (searchQuestion.getSid() == null) {
            searchQuestion.setCreateTime(new Date());
        }
        searchQuestion.setUpdateTime(new Date());
        if (searchQuestion.getTid()==1) {   //问卷调查
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
        if(searchQuestion.getTid()==2){  //类型为教师评价是处理

            //修改edu_search_question表记录
            if(searchQuestion.getTopId()!=null&&searchQuestion.getDownId()==null){    //线上报名时
                List<SearchQuestion> searchQuestionAll = questionSearchMapper.findSearchQuestionAll(null, searchQuestion.getTopId(), null, null, null, null);
                if (searchQuestionAll.size()==0){
                searchQuestion.setTid(0);    //当添加调查
                questionSearchMapper.createSearchQuestion(searchQuestion);
                    //设置对应的topId或者topId与问卷主题标的进行绑定
                for (int i = 0; i < searchQuestion.getQuestions().size(); i++) {
                    searchQuestion.getQuestions().get(i).setTopId(searchQuestion.getTopId());

                }
                }else {
                    //修改时
                    questionSearchMapper.updateSearchQuestion(searchQuestion);
                }
            }
            //修改edu_search_question表记录
            if(searchQuestion.getTopId()==null&&searchQuestion.getDownId()!=null) {
                    //线下报名时
                List<SearchQuestion> searchQuestionAll = questionSearchMapper.findSearchQuestionAll(null, null, searchQuestion.getDownId(), null, null, null);

                if (searchQuestionAll.size()==0){
                    searchQuestion.setTid(0);    //当添加调查
                    questionSearchMapper.createSearchQuestion(searchQuestion);
                    //设置对应的topId或者downId与问卷主题标的进行绑定
                    for (int i = 0; i < searchQuestion.getQuestions().size(); i++) {
                        searchQuestion.getQuestions().get(i).setDownId(searchQuestion.getDownId());
                    }
                }else {
                    //修改时

                    questionSearchMapper.updateSearchQuestion(searchQuestion);
                }
            }
               if (searchQuestion.getQuestions().get(0).getQid()==null) {
                   questionSearchMapper.insertBigQuestionItem(searchQuestion.getQuestions());
                   return new WebResult("200", "添加成功", "");
               }else{
                   questionSearchMapper.updateSearchQuestion(searchQuestion);
                   List<Question> questions = searchQuestion.getQuestions();
                   questionSearchMapper.updateBigQuestionItem(questions);
                   return new WebResult("200", "修改成功", "");
               }
        }
        if (searchQuestion.getTid()==3){  //类型为投票时处理


            //批量添加问题及选项
            if (searchQuestion.getSid() == null) {
                questionSearchMapper.createSearchQuestion(searchQuestion);
                //设置question的sid与问卷主题标的主键sid进行绑定
                for (int i = 0; i < searchQuestion.getVoteItems().size(); i++) {
                    searchQuestion.getVoteItems().get(i).setSid(searchQuestion.getSid());

                }

                questionSearchMapper.createBigVoteItem(searchQuestion.getVoteItems()); //批量添加投票题目及选项
                return new WebResult("200", "添加成功", "");
            }else {
                    questionSearchMapper.updateSearchQuestion(searchQuestion);
                             List<VoteItem> voteItems=searchQuestion.getVoteItems();
                    questionSearchMapper.updateBigVoteItem(voteItems);
                    return new WebResult("200", "修改成功", "");
                }
        }
        return null;
    }

    //删除问卷调查（SearchQuestion）
    @Transactional
    @Override
    public WebResult deleteSearchQuestion(Integer sid,Integer tid,Integer topId,Integer downId) {
        questionSearchMapper.deleteSearchQuestion(sid);
        if (tid==1) {
            questionSearchMapper.deleteQuestionItem(sid);
        }
        if (tid==2){
           if (topId!=null){
            questionSearchMapper.deleteQuestionItemByTopid(topId);
           }
           if (downId!=null){
            questionSearchMapper.deleteQuestionItemBydownid(downId);
           }

        }
           if (tid==3){
              //当类型是投票的时候 删除对应表的相关记录
            questionSearchMapper.deleteVoteItembyId(sid);
           }
        return new WebResult("200", "删除成功", "");
    }
    //结果记录列表（SearchQuestion）
    @Transactional
    @Override
    public WebResult findAnswerRecord(PageVo pageVo) {
        if (pageVo.getPageSize() == 0) {
            List<AnswerRecord> answerRecords = questionSearchMapper.findAnswerRecord(pageVo);
            return new WebResult("200", "查询成功", answerRecords);
        }
        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());
        List<AnswerRecord> answerRecords = questionSearchMapper.findAnswerRecord(pageVo);
        PageInfo pageInfo = new PageInfo<>(answerRecords);
        return new WebResult("200","查询成功", pageInfo);


    }

    @Override
    public WebResult findVoteRecord(PageVo pageVo) {
        if (pageVo.getPageSize() == 0) {
            List<VoteItem> voteItemAll = questionSearchMapper.findVoteItemAll();
            return new WebResult("200", "查询成功", voteItemAll);
        }
        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());
        List<VoteItem> voteItemAll = questionSearchMapper.findVoteItemAll();
        List<SearchQuestion> searchQuestionAll2 = questionSearchMapper.findSearchQuestionAll(pageVo.getSid(),pageVo.getTopId(),pageVo.getDownId(), pageVo.getSchoolId(), pageVo.getTid(), pageVo.getSearch());
        searchQuestionAll2.get(0).setVoteItems(voteItemAll);
        PageInfo pageInfo = new PageInfo<>(voteItemAll);
        return new WebResult("200","查询成功", pageInfo);
    }

    //提交投票记录
    @Transactional
    @Override
    public WebResult submitVoteRecord(Integer id,Integer userId) {
        VoteItem voteItembyuserId = questionSearchMapper.findVoteItembyuserId(userId);
           if (voteItembyuserId!=null){
               return  new WebResult("200", "您已提交过此内容,无需重复操作！", "");
           }
        VoteItem itembyId = questionSearchMapper.findVoteItembyId(id);
           questionSearchMapper.updateVoteItem(itembyId.getNum()+1,id);
        return  new WebResult("200", "提交成功", "");
    }

    //教师评价列表
    @Override
    public WebResult findCourseTopic(PageVo pageVo) {

        return null;
    }
    //app端教师评价列表
    @Override
    public WebResult findMyCourseList(PageVo pageVo) {


        List<MyCourseAll> myCourseAlltop = questionSearchMapper.findMyCourseAlltop(pageVo);
        List<MyCourseAll> myCourseAlldown = questionSearchMapper.findMyCourseAlldown(pageVo);
        List<MyCourseAll> list=new ArrayList();
        for (int m=0;m<myCourseAlltop.size();m++){
             myCourseAlltop.get(m).setStatu("线上报名");
            Integer schoolId = myCourseAlltop.get(m).getSchoolId();
            String schoolName = schoolMapper.findSchoolById(schoolId);
            myCourseAlltop.get(m).setSchoolName(schoolName);
            SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd ");
            String format = formatter.format(myCourseAlltop.get(m).getEndDate());
            if (Integer.parseInt(format.substring(5,7))>6){
                myCourseAlltop.get(m).setCourseyear(format.substring(0,4)+"下半年度");
            }else {
                myCourseAlltop.get(m).setCourseyear(format.substring(0,4)+"上半年度");
            }
            list.add(myCourseAlltop.get(m));
         }
        for (int n=0;n<myCourseAlldown.size();n++){
             myCourseAlldown.get(n).setStatu("线下报名");
            Integer schoolId = myCourseAlldown.get(n).getSchoolId();
            String schoolName = schoolMapper.findSchoolById(schoolId);
            myCourseAlldown.get(n).setSchoolName(schoolName);
            SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd ");
            String format = formatter.format(myCourseAlldown.get(n).getEndDate());
            if (Integer.parseInt(format.substring(5,7))>6){
                myCourseAlldown.get(n).setCourseyear(format.substring(0,4)+"下半年度");
            }else {
                myCourseAlldown.get(n).setCourseyear(format.substring(0,4)+"上半年度");
            }
            list.add(myCourseAlldown.get(n));
         }

        if (pageVo.getPageSize() == 0) {

            return new WebResult("200", "查询成功", list);
        }


        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());

        PageInfo pageInfo = new PageInfo<>(list);
        return new WebResult("200","查询成功", pageInfo);
    }

    //1.问卷调查列表 and 2.进入详情页
    @Override
    public WebResult findSearchQuestionAll(PageVo pageVo) {
        if (pageVo.getPageSize() == 0) {
            //进入详情页
            if (pageVo.getSid() != null||pageVo.getTopId()!=null||pageVo.getDownId()!=null) {     //进入某篇问卷调查的详情页==>> 2.


                List<SearchQuestion> searchQuestionAll2 = questionSearchMapper.findSearchQuestionAll(pageVo.getSid(),pageVo.getTopId(),pageVo.getDownId(), pageVo.getSchoolId(), pageVo.getTid(), pageVo.getSearch());
                if (pageVo.getTid()==1) {
                    List<Question> questions = questionSearchMapper.findQuestionAll(pageVo.getSid(), pageVo.getTid(),pageVo.getTopId(),pageVo.getDownId(), pageVo.getSearch());
                        searchQuestionAll2.get(0).setQuestions(questions);
                }
                if (pageVo.getTid()==2||pageVo.getTid()==0) {
                    List<Question> questions = questionSearchMapper.findQuestionAll(null, null,pageVo.getTopId(),pageVo.getDownId(), pageVo.getSearch());
                    searchQuestionAll2.get(0).setQuestions(questions);
                }
                 if (pageVo.getTid()==3){//返回对应投票详情
                     List<VoteItem> voteItembySid = questionSearchMapper.findVoteItembySid(pageVo.getSid());
                     searchQuestionAll2.get(0).setVoteItems(voteItembySid);
                 }
                questionSearchMapper.updateSearchQuestionVisits(searchQuestionAll2.get(0).getVisits()+1,searchQuestionAll2.get(0).getSid()); //让该篇问卷调查的浏览量+1

                return new WebResult("200", "进入详情页", searchQuestionAll2.get(0));
            }
            //问卷调查列表==>>1.
            List<SearchQuestion> searchQuestionAll = questionSearchMapper.findSearchQuestionAll(pageVo.getSid(),pageVo.getTopId(),pageVo.getDownId(), pageVo.getSchoolId(), pageVo.getTid(), pageVo.getSearch());
               if(pageVo.getUserId()!=null) {//截止时间过了的app端不会显示，而后台可以看见
                   for (int i = 0; i < searchQuestionAll.size(); i++) {      //处理edu_search_question表里面status=0的，不让其展示
                       if (searchQuestionAll.get(i).getStatus() == 2) {
                           searchQuestionAll.remove(i);      //移除集合里面status=0的
                       }
                   }
               }
            return new WebResult("200", "查询成功", searchQuestionAll);
        }
        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());
        //进入详情页
        if (pageVo.getSid() != null||pageVo.getTopId()!=null||pageVo.getDownId()!=null) {  //进入某篇问卷调查的详情页==>> 2.

            List<SearchQuestion> searchQuestionAll = questionSearchMapper.findSearchQuestionAll(pageVo.getSid(),pageVo.getTopId(),pageVo.getDownId(), pageVo.getSchoolId(), pageVo.getTid(), pageVo.getSearch());
            if (pageVo.getTid()==1) {
                List<Question> questions = questionSearchMapper.findQuestionAll(pageVo.getSid(), pageVo.getTid(),pageVo.getTopId(),pageVo.getDownId(), pageVo.getSearch());
                searchQuestionAll.get(0).setQuestions(questions);
            }
            if (pageVo.getTid()==2) {
                List<Question> questions = questionSearchMapper.findQuestionAll(null, null,pageVo.getTopId(),pageVo.getDownId(), pageVo.getSearch());
                searchQuestionAll.get(0).setQuestions(questions);
            }
            if (pageVo.getTid()==3){//返回对应投票详情
                List<VoteItem> voteItembySid = questionSearchMapper.findVoteItembySid(pageVo.getSid());
                searchQuestionAll.get(0).setVoteItems(voteItembySid);
            }
            questionSearchMapper.updateSearchQuestionVisits(searchQuestionAll.get(0).getVisits()+1,searchQuestionAll.get(0).getSid()); //让该篇问卷调查的浏览量+1

            return new WebResult("200", "进入详情页", searchQuestionAll.get(0));
        }
        //问卷调查列表==>> 1.
        List<SearchQuestion> searchQuestionAll2 = questionSearchMapper.findSearchQuestionAll(pageVo.getSid(),pageVo.getTopId(),pageVo.getDownId(), pageVo.getSchoolId(), pageVo.getTid(), pageVo.getSearch());
        if(pageVo.getUserId()!=null) {//截止时间过了的app端不会显示，而后台可以看见
            for (int i = 0; i < searchQuestionAll2.size(); i++) {      //处理edu_search_question表里面status=0的，不让其展示
                if (searchQuestionAll2.get(i).getStatus() == 2) {
                    searchQuestionAll2.remove(i);      //移除集合里面status=0的
                }
            }
        }
        PageInfo pageInfo = new PageInfo<>(searchQuestionAll2);
        return new WebResult("200", "查询成功", pageInfo);
    }
   //按“热门”条件进行查询
    @Override
    public WebResult findSearchQuestionAllbyVisits(PageVo pageVo) {
        if (pageVo.getPageSize() == 0) {
            //进入详情页
            if (pageVo.getSid() != null) {     //进入某篇问卷调查的详情页==>> 2.
                List<Question> questions = questionSearchMapper.findQuestionAll(pageVo.getSid(), pageVo.getTid(),pageVo.getTopId(),pageVo.getDownId(), pageVo.getSearch());
                List<SearchQuestion> searchQuestionAll2 = questionSearchMapper.findSearchQuestionAll(pageVo.getSid(),pageVo.getTopId(),pageVo.getDownId(), pageVo.getSchoolId(), pageVo.getTid(), pageVo.getSearch());

                searchQuestionAll2.get(0).setQuestions(questions);
                questionSearchMapper.updateSearchQuestionVisits(searchQuestionAll2.get(0).getVisits()+1,searchQuestionAll2.get(0).getSid()); //让该篇问卷调查的浏览量+1
                return new WebResult("200", "进入详情页", searchQuestionAll2.get(0));
            }
            //问卷调查列表==>>1.
            List<SearchQuestion> searchQuestionAll = questionSearchMapper.findSearchQuestionAllbyVisits(pageVo.getSid(),pageVo.getTopId(),pageVo.getDownId(), pageVo.getSchoolId(), pageVo.getTid(), pageVo.getSearch());
            if(pageVo.getUserId()!=null) {//截止时间过了的app端不会显示，而后台可以看见
                for (int i = 0; i < searchQuestionAll.size(); i++) {      //处理edu_search_question表里面status=0的，不让其展示
                    if (searchQuestionAll.get(i).getStatus() == 2) {
                        searchQuestionAll.remove(i);      //移除集合里面status=0的
                    }
                }
            }
            return new WebResult("200", "查询成功", searchQuestionAll);
        }

        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());
        //进入详情页
        if (pageVo.getSid() != null) {  //进入某篇问卷调查的详情页==>> 2.
            List<Question> questions = questionSearchMapper.findQuestionAll(pageVo.getSid(), pageVo.getTid(),pageVo.getTopId(),pageVo.getDownId(), pageVo.getSearch());
            List<SearchQuestion> searchQuestionAll = questionSearchMapper.findSearchQuestionAll(pageVo.getSid(),pageVo.getTopId(),pageVo.getDownId(), pageVo.getSchoolId(), pageVo.getTid(), pageVo.getSearch());
            searchQuestionAll.get(0).setQuestions(questions);
            questionSearchMapper.updateSearchQuestionVisits(searchQuestionAll.get(0).getVisits()+1,searchQuestionAll.get(0).getSid()); //让该篇问卷调查的浏览量+1
            return new WebResult("200", "进入详情页", searchQuestionAll.get(0));
        }
        //问卷调查列表==>> 1.
        List<SearchQuestion> searchQuestionAll2 = questionSearchMapper.findSearchQuestionAllbyVisits(pageVo.getSid(),pageVo.getTopId(),pageVo.getDownId(), pageVo.getSchoolId(), pageVo.getTid(), pageVo.getSearch());
        if(pageVo.getUserId()!=null) {//截止时间过了的app端不会显示，而后台可以看见
            for (int i = 0; i < searchQuestionAll2.size(); i++) {      //处理edu_search_question表里面status=0的，不让其展示
                if (searchQuestionAll2.get(i).getStatus() ==2) {
                    searchQuestionAll2.remove(i);      //移除集合里面status=0的
                }
            }
        }
        PageInfo pageInfo = new PageInfo<>(searchQuestionAll2);
        return new WebResult("200", "查询成功", pageInfo);
    }
   //按“综合”条件进行查询
    @Override
    public WebResult findSearchQuestioncomposite(PageVo pageVo) {
        if (pageVo.getPageSize() == 0) {
            //进入详情页
            if (pageVo.getSid() != null) {     //进入某篇问卷调查的详情页==>> 2.
                List<Question> questions = questionSearchMapper.findQuestionAll(pageVo.getSid(), pageVo.getTid(),pageVo.getTopId(),pageVo.getDownId(), pageVo.getSearch());
                List<SearchQuestion> searchQuestionAll2 = questionSearchMapper.findSearchQuestionAll(pageVo.getSid(),pageVo.getTopId(),pageVo.getDownId(), pageVo.getSchoolId(), pageVo.getTid(), pageVo.getSearch());
                searchQuestionAll2.get(0).setQuestions(questions);
                questionSearchMapper.updateSearchQuestionVisits(searchQuestionAll2.get(0).getVisits()+1,searchQuestionAll2.get(0).getSid()); //让该篇问卷调查的浏览量+1
                return new WebResult("200", "进入详情页", searchQuestionAll2.get(0));
            }
            //问卷调查列表==>>1.
            List<SearchQuestion> searchQuestionAll = questionSearchMapper.findSearchQuestioncomposite(pageVo.getSid(),pageVo.getTopId(),pageVo.getDownId(), pageVo.getSchoolId(), pageVo.getTid(), pageVo.getSearch());
            if(pageVo.getUserId()!=null) {//截止时间过了的app端不会显示，而后台可以看见
                for (int i = 0; i < searchQuestionAll.size(); i++) {      //处理edu_search_question表里面status=0的，不让其展示
                    if (searchQuestionAll.get(i).getStatus() == 2) {
                        searchQuestionAll.remove(i);      //移除集合里面status=0的
                    }
                }
            }
            return new WebResult("200", "查询成功", searchQuestionAll);
        }

        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());
        //进入详情页
        if (pageVo.getSid() != null) {  //进入某篇问卷调查的详情页==>> 2.
            List<Question> questions = questionSearchMapper.findQuestionAll(pageVo.getSid(), pageVo.getTid(),pageVo.getTopId(),pageVo.getDownId(), pageVo.getSearch());
            List<SearchQuestion> searchQuestionAll = questionSearchMapper.findSearchQuestionAll(pageVo.getSid(),pageVo.getTopId(),pageVo.getDownId(), pageVo.getSchoolId(), pageVo.getTid(), pageVo.getSearch());
            searchQuestionAll.get(0).setQuestions(questions);
            questionSearchMapper.updateSearchQuestionVisits(searchQuestionAll.get(0).getVisits()+1,searchQuestionAll.get(0).getSid()); //让该篇问卷调查的浏览量+1
            return new WebResult("200", "进入详情页", searchQuestionAll.get(0));
        }
        //问卷调查列表==>> 1.
        List<SearchQuestion> searchQuestionAll2 = questionSearchMapper.findSearchQuestioncomposite(pageVo.getSid(),pageVo.getTopId(),pageVo.getDownId(), pageVo.getSchoolId(), pageVo.getTid(), pageVo.getSearch());
        if(pageVo.getUserId()!=null) {//截止时间过了的app端不会显示，而后台可以看见
            for (int i = 0; i < searchQuestionAll2.size(); i++) {      //处理edu_search_question表里面status=0的，不让其展示
                if (searchQuestionAll2.get(i).getStatus() ==2) {
                    searchQuestionAll2.remove(i);      //移除集合里面status=0的
                }
            }
        }
        PageInfo pageInfo = new PageInfo<>(searchQuestionAll2);
        return new WebResult("200", "查询成功", pageInfo);
    }

    //添加调查问卷、教师评价结果记录
    @Transactional
    @Override
    public WebResult createAnswerRecord(AnswerRecord answerRecord) {

        String userName = questionSearchMapper.findAnswerRecordbyUserId(answerRecord.getUid());
        if (userName==null){
            return new WebResult("200", "您已提交过此内容,无需重复操作！", "");
        }
        int [] answerNums = answerRecord.getAnswerNums();
        //找到对应主题的题目及选项(选项及分数权重的集合)的集合，主要针对教师评价
        List<Question> questions = questionSearchMapper.findQuestionAllAndGrade(answerRecord.getList().get(0).getSid(), answerRecord.getList().get(0).getTid(), null);
        //当类型是二,即教师评价的时候需要对答题总分进行统计
        if (answerRecord.getList().get(0).getTid() == 2) {
            Integer total = 0;   //用户答题总分记录
            for (int i = 0; i < answerRecord.getList().size(); i++) {    //  for (int i = 0; i < answerRecord.getList().size(); i++)，==>>总共有多少个题目的循环遍历
                switch (answerNums[i]) {
                    case 1:   //当用户所选的答案是第一个选项的时候
                        total = questions.get(i).getItem1Grade() + total;  //第一个题的第一个选项的分数
                        break; //可选
                    case 2:
                        //语句
                        total = questions.get(i).getItem2Grade() + total;
                        break; //可选
                    case 3:
                        //语句
                        total = questions.get(i).getItem3Grade() + total;
                        break; //可选
                    case 4:
                        //语句
                        total = questions.get(i).getItem4Grade() + total;
                        break; //可选
                    case 5:
                        //语句
                        total = questions.get(i).getItem5Grade() + total;
                        break; //可选
                    case 6:
                        //语句
                        total = questions.get(i).getItem6Grade() + total;
                        break; //可选
                    default:
                        total = total + 0;    //当前题目用户没有填任何选项的情况（没做）

                }

            }
            answerRecord.setTotalGrade(total);
        }
        //用户所填的答案结果组成String类型,然后存入数据库
        String answer = "";
        List<AnswerRecordVo> answerValues = answerRecord.getAnswerValues();//用户所填每题题号加结果记录的集合
        for (int n = 0; n <answerValues.size(); n++) {
            if (n == 0) {
                answer = answerValues.get(n).getTitleId() + "," + answerValues.get(n).getAnswer() + ";";
            }else {
                answer = answer.concat(answerValues.get(n).getTitleId() + "," + answerValues.get(n).getAnswer() + ";");
            }
        }

           //统计对应问卷相关选项的被选择量
        if(answerRecord.getList().get(0).getTid()==1||answerRecord.getList().get(0).getTid()==2) {
            //记录选项的的被选择总量（即：每个用户选择对应选项就会让其被选择数加一）
            for (int i = 0; i < answerRecord.getList().size(); i++) {
                switch (answerNums[i]) {
                    case 1:
                        Integer questionItemTotal = questions.get(i).getItem1Num() + 1;
                        Question question1 = answerRecord.getList().get(i);
                        question1.setItem1Num(questionItemTotal);
                        questionSearchMapper.updateQuestionItemNum(question1);
                        break; //可选
                    case 2:
                        //语句
                        Integer questionItemTotal2 = questions.get(i).getItem2Num() + 1;
                        Question question2 = answerRecord.getList().get(i);
                        question2.setItem2Num(questionItemTotal2);
                        questionSearchMapper.updateQuestionItemNum(question2);
                        break; //可选
                    case 3:
                        //语句
                        Integer questionItemTotal3 = questions.get(i).getItem3Num() + 1;
                        Question question3 = answerRecord.getList().get(i);
                        question3.setItem3Num(questionItemTotal3);
                        questionSearchMapper.updateQuestionItemNum(question3);
                        break; //可选
                    case 4:
                        //语句
                        Integer questionItemTotal4 = questions.get(i).getItem4Num() + 1;
                        Question question4 = answerRecord.getList().get(i);
                        question4.setItem4Num(questionItemTotal4);
                        questionSearchMapper.updateQuestionItemNum(question4);
                        break; //可选
                    case 5:
                        //语句
                        Integer questionItemTotal5 = questions.get(i).getItem5Num() + 1;
                        Question question5 = answerRecord.getList().get(i);
                        question5.setItem5Num(questionItemTotal5);
                        questionSearchMapper.updateQuestionItemNum(question5);
                        break; //可选
                    case 6:
                        //语句
                        Integer questionItemTotal6 = questions.get(i).getItem6Num() + 1;
                        Question question6 = answerRecord.getList().get(i);
                        question6.setItem6Num(questionItemTotal6);
                        questionSearchMapper.updateQuestionItemNum(question6);
                        break; //可选
                    default:

                }

            }
        }

        answerRecord.setAnswer(answer);
        answerRecord.setSid(answerRecord.getList().get(0).getSid());//将题目所对应的问卷主题设置给answerRecord
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

    @Transactional
    @Override
    public void deleteScheduleTask() {
        List<SearchQuestion> searchQuestions = questionSearchMapper.findSearchQuestionAll(null,null,null, null, null, null);
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        for (SearchQuestion searchQuestion : searchQuestions) {
            Date endTime = searchQuestion.getEndTime();

            if(endTime == null) {
               // eduArticleMapper.updateIsTop(eduArticle.getId(), "0", null);
                continue;
            }
            boolean before = now.before(endTime);
            boolean after = now.after(endTime);
            //判断两个Date是不是同一天
            boolean samedate = DateUtils.isSameDay(endTime, now);
            if(samedate) {
                before = true;
                after = false;
            }

           if (before&&!after&&samedate){
             questionSearchMapper.updateSearchQuestionStatues(2,searchQuestion.getSid());

           }

        }
    }


}
