package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.*;
import com.ovft.configure.sys.dao.*;
import com.ovft.configure.sys.service.QuestionSearchService;
import com.ovft.configure.sys.vo.*;
import net.sf.saxon.expr.instruct.ForEach;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 调查问卷实现类
 **/
@Service
public class QuestionSearchImpl implements QuestionSearchService {
    @Resource
    private SchoolMapper schoolMapper;
    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private QuestionSearchMapper questionSearchMapper;
    @Resource
    private EduOfflineOrderMapper eduOfflineOrderMapper;
    @Resource
    private EduCourseMapper eduCourseMapper;
    @Resource
    private EduPayrecordMapper eduPayrecordMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private  EduLivePayMapper eduLivePayMapper;




    //添加问卷调查（SearchQuestion）
    @Transactional
    @Override
    public WebResult createSearchQuestion(SearchQuestion searchQuestion) {
        if (StringUtils.isBlank(searchQuestion.getSearchName())) {
            return new WebResult("400", "问卷标题不能为空！", "");
        }
        if (StringUtils.isBlank(searchQuestion.getTopImage())) {
            return new WebResult("400", "您还未添加问卷图片！", "");
        }



        String schoolById = schoolMapper.findSchoolById(searchQuestion.getSchoolId());
        searchQuestion.setSchoolName(schoolById);
        searchQuestion.setStatus(1);
        if (searchQuestion.getSid() == null) {
            searchQuestion.setCreateTime(new Date());
        }
        searchQuestion.setUpdateTime(new Date());
        if (searchQuestion.getTid() == 1) {   //问卷调查
            if (searchQuestion.getQuestions().isEmpty()) {
                return new WebResult("400", "您还未添加问卷题目！", "");
            }
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
                if (searchQuestion.getQuestions().isEmpty()) {
                    return new WebResult("400", "您还未添加问卷题目！", "");
                }
                questionSearchMapper.updateSearchQuestion(searchQuestion);
                List<Question> questions = searchQuestion.getQuestions();
                for (int i=0;i<questions.size();i++){
                    List<Question>  questionList=new ArrayList<>();
                    questionList.add(questions.get(i));
                    if (questions.get(i).getQid()==null){
                        questionSearchMapper.insertBigQuestionItem(questionList);
                    }
                    questionSearchMapper.updateBigQuestionItem(questions);
                }
                questionSearchMapper.updateBigQuestionItem(questions);
                return new WebResult("200", "修改成功", "");
            }
        }
        if (searchQuestion.getTid() == 2||searchQuestion.getTid()==0) {  //类型为教师评价是处理
            if (searchQuestion.getQuestions().isEmpty()) {
                return new WebResult("400", "您还未添加问卷题目！", "");
            }
            //通过courseId查询edu_search_question表是否存在相关记录
            List<SearchQuestion> searchQuestionByCourseId = questionSearchMapper.findSearchQuestionByCourseId(searchQuestion.getCourseId());

            if (searchQuestion.getSid() == null&&searchQuestionByCourseId.isEmpty()) {
                 searchQuestion.setTid(0);
                questionSearchMapper.createSearchQuestion(searchQuestion);

                //批量添加问题及选项

                List<Question> questions = searchQuestion.getQuestions();
                //设置question的sid与问卷主题标的主键sid进行绑定
                for (int i = 0; i < searchQuestion.getQuestions().size(); i++) {
                    questions.get(i).setSid(searchQuestion.getSid());

                }
                teacherMapper.updateisAddQuestionByCourseId(searchQuestion.getCourseId(),1); //修改课程为已添加问卷题目状态
                questionSearchMapper.insertBigQuestionItem(questions);
                return new WebResult("200", "添加成功", "");

            } else {
                if (searchQuestion.getQuestions().isEmpty()) {
                    return new WebResult("400", "您还未添加问卷题目！", "");
                }
                questionSearchMapper.updateSearchQuestion(searchQuestion);
                List<Question> questions = searchQuestion.getQuestions();
                for (int i=0;i<questions.size();i++){
                    List<Question>  questionList=new ArrayList<>();
                    questionList.add(questions.get(i));
                    if (questions.get(i).getQid()==null){
                        questionSearchMapper.insertBigQuestionItem(questionList);
                    }
                    questionSearchMapper.updateBigQuestionItem(questions);
                }

                return new WebResult("200", "修改成功", "");
            }


        }
        if (searchQuestion.getTid() == 3) {  //类型为投票时处理

            if (searchQuestion.getVoteItems().isEmpty()) {
                return new WebResult("400", "您还未投票题目及选项！", "");
            }

            //批量添加问题及选项
            if (searchQuestion.getSid() == null) {
                questionSearchMapper.createSearchQuestion(searchQuestion);
                //设置question的sid与问卷主题标的主键sid进行绑定
                for (int i = 0; i < searchQuestion.getVoteItems().size(); i++) {
                    searchQuestion.getVoteItems().get(i).setSid(searchQuestion.getSid());

                }

                questionSearchMapper.createBigVoteItem(searchQuestion.getVoteItems()); //批量添加投票题目及选项
                return new WebResult("200", "添加成功", "");
            } else {
                if (searchQuestion.getQuestions().isEmpty()) {
                    return new WebResult("400", "您还未添加问卷题目！", "");
                }
                questionSearchMapper.updateSearchQuestion(searchQuestion);
                List<VoteItem> voteItems = searchQuestion.getVoteItems();
                for (int i=0;i<voteItems.size();i++){
                    List<VoteItem>  voteItemList=new ArrayList<>();
                    voteItemList.add(voteItems.get(i));
                    if (voteItems.get(i).getId()==null){

                        questionSearchMapper.createBigVoteItem(voteItemList);
                    }
                    questionSearchMapper.updateBigVoteItem(voteItemList);
                }
                return new WebResult("200", "修改成功", "");
            }
        }
        return null;
    }

    //删除问卷调查（SearchQuestion）
    @Transactional
    @Override
    public WebResult deleteSearchQuestion(Integer sid, Integer tid,Integer courseId) {
        questionSearchMapper.deleteSearchQuestion(sid);
        if (tid == 1) {
            questionSearchMapper.deleteQuestionItem(sid);
        }
        if (tid == 2) {
            List<SearchQuestion> searchQuestionByCourseId = questionSearchMapper.findSearchQuestionByCourseId(courseId);
            if (!searchQuestionByCourseId.isEmpty()){
                sid= searchQuestionByCourseId.get(0).getSid();
            }
            teacherMapper.updateisAddQuestionByCourseId(courseId,0);
            questionSearchMapper.deleteQuestionItem(sid);
        }
        if (tid == 3) {
            //当类型是投票的时候 删除对应表的相关记录
            questionSearchMapper.deleteVoteItembyId(sid);
        }
        return new WebResult("200", "删除成功", "");
    }
    //删除一条结果记录
    @Transactional
    @Override
    public WebResult deleteAnswerRecordOne(AnswerRecord answerRecord) {
        questionSearchMapper.deleteAnswerRecordOne(answerRecord);
        return new WebResult("200", "删除成功", "");
    }
    //批量删除用户结果记录
    @Transactional
    @Override
    public WebResult BigDeleteAnswerRecord(AnswerRecord answerRecord) {
        questionSearchMapper.BigDeleteAnswerRecord(answerRecord.getAids());
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
        return new WebResult("200", "查询成功", pageInfo);


    }

    @Override
    public WebResult findVoteRecord(PageVo pageVo) {
        if (pageVo.getPageSize() == 0) {
            List<VoteItem> voteItemAll = questionSearchMapper.findVoteItemAll();
            return new WebResult("200", "查询成功", voteItemAll);
        }
        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());
        List<VoteItem> voteItemAll = questionSearchMapper.findVoteItemAll();
        List<SearchQuestion> searchQuestionAll2 = questionSearchMapper.findSearchQuestionAll(pageVo.getStatus(),pageVo.getSid(), pageVo.getTopId(), pageVo.getDownId(), pageVo.getSchoolId(), pageVo.getTid(),pageVo.getCourseId(), pageVo.getSearch());
        searchQuestionAll2.get(0).setVoteItems(voteItemAll);
        PageInfo pageInfo = new PageInfo<>(voteItemAll);
        return new WebResult("200", "查询成功", pageInfo);
    }

    //提交投票记录
    @Transactional
    @Override
    public WebResult submitVoteRecord(AnswerRecord answerRecord, Integer id) {
        String userName = questionSearchMapper.findAnswerRecordbyUserId(answerRecord.getCourseId(),answerRecord.getUid(), answerRecord.getList().get(0).getSid(), answerRecord.getTopId(), answerRecord.getDownId());
        if (userName != null) {
            return new WebResult("300", "您已提交过此内容,无需重复操作！", "");
        }
        if (id == null) {
            return new WebResult("300", "未收到选项参数", "");
        }
        VoteItem itembyId = questionSearchMapper.findVoteItembyId(id);
        questionSearchMapper.updateVoteItem(itembyId.getNum() + 1, id);    //让相关选项的投票总数+1
        //用户所填的答案结果组成String类型,然后存入数据库
        String answer = "";
        List<AnswerRecordVo> answerValues = answerRecord.getAnswerValues();     //用户所填每题题号加结果记录的集合
        for (int n = 0; n < answerValues.size(); n++) {
            if (n == 0) {
                answer = answerValues.get(n).getTitleId() + "-" + answerValues.get(n).getAnswer();
            } else {
                answer = answer.concat(answerValues.get(n).getTitleId() + "-" + answerValues.get(n).getAnswer());
            }
        }

        answerRecord.setAnswer(answer);
        answerRecord.setType("投票评选");
        answerRecord.setSid(answerRecord.getList().get(0).getSid());//将题目所对应的问卷主题设置给answerRecord
        answerRecord.setTid(3);
        questionSearchMapper.createAnswerRecord(answerRecord);

        return new WebResult("200", "提交成功", "");
    }
    //弃用
    @Override
    public WebResult findCourseTopic(PageVo pageVo) {
        return null;
    }


    /**
     * 根据身份证号获取年龄
     * @param certId
     * @return
     */
    public static int getAgeByCertId(String certId) {
        String birthday = "";
        if (certId.length() == 18) {
            birthday = certId.substring(6, 10) + "/"
                    + certId.substring(10, 12) + "/"
                    + certId.substring(12, 14);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date now = new Date();
        Date birth = new Date();
        try {
            birth = sdf.parse(birthday);
        } catch (ParseException e) {
        }
        long intervalMilli = now.getTime() - birth.getTime();
        int age = (int) (intervalMilli/(24 * 60 * 60 * 1000))/365;
        System.out.println(age);
        return age;
    }

    //1.app端教师评价列表()、2.班级成员列表
    @Override
    public WebResult findMyCourseList(PageVo pageVo) {


        String schoolName = schoolMapper.findSchoolById(pageVo.getSchoolId());
        VateType vateType = questionSearchMapper.findCourseImage(2);
        if (pageVo.getPageSize() == 0) {   // pageVo.getUserId()!=null   ===》暂时处理app端能显示正常。直接显示所有成员
//            pageVo.setUserId(null);     //查询班级里面所有的成员
            pageVo.setPayStatus(5);
            List<MyCourseAll> myCourseAlltop = questionSearchMapper.findMyCourseAlltop(pageVo);  //线上缴费
            pageVo.setPayStatus(1);
            List<MyCourseAll> myCourseAlldown = questionSearchMapper.findMyCourseAlldown(pageVo);   //线下缴费
            List<MyCourseAll> list = new ArrayList();
            //查询现场报名相关成员记录
            LivePayVo livePayVo=new LivePayVo();
            livePayVo.setCourseId(pageVo.getCourseId());
            List<LivePayVo> livePayVos = eduLivePayMapper.selectLivePay(livePayVo);
            List<MyCourseAll> livePaysList=new LinkedList<>();

            for (int n = 0; n <livePayVos.size(); n++) {     //封装线下缴费列表记录
                livePaysList.get(n).setCourseId(livePayVos.get(n).getCourseId());
                livePaysList.get(n).setUserName(livePayVos.get(n).getUserName());
                livePaysList.get(n).setCourseName(livePayVos.get(n).getCourseName());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
                String format = formatter.format(livePayVos.get(n).getPayDate());
                if (Integer.parseInt(format.substring(5, 7)) > 6) {
                    livePaysList.get(n).setCourseyear(format.substring(0, 4) + "下半年度");
                } else {
                    livePaysList.get(n).setCourseyear(format.substring(0, 4) + "上半年度");
                }
                livePaysList.get(n).setCourseTeacher(livePayVos.get(n).getCourseTeacher());
                livePaysList.get(n).setSchoolName(schoolName);
                livePaysList.get(n).setAddress(livePayVos.get(n).getAddress());
                livePaysList.get(n).setPhone(livePayVos.get(n).getPhone());
                livePaysList.get(n).setIdentityCard(livePayVos.get(n).getIdentityCard());
                livePaysList.get(n).setJob(livePayVos.get(n).getJob());
                livePaysList.get(n).setStatu("现场收费");
                list.add(livePaysList.get(n));
            }
            for (int m = 0; m < myCourseAlltop.size(); m++) {



                myCourseAlltop.get(m).setCourseImage(vateType.getImage());
                myCourseAlltop.get(m).setStatu("线上报名");
                Integer schoolId = myCourseAlltop.get(m).getSchoolId();
                myCourseAlltop.get(m).setSchoolName(schoolName);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
                String format = formatter.format(myCourseAlltop.get(m).getCoursestartTime());
                if (Integer.parseInt(format.substring(5, 7)) > 6) {
                    myCourseAlltop.get(m).setCourseyear(format.substring(0, 4) + "下半年度");
                } else {
                    myCourseAlltop.get(m).setCourseyear(format.substring(0, 4) + "上半年度");
                }
                list.add(myCourseAlltop.get(m));
            }
            for (int n = 0; n < myCourseAlldown.size(); n++) {
                myCourseAlldown.get(n).setCourseImage(vateType.getImage());
                myCourseAlldown.get(n).setStatu("线下报名");
                Integer schoolId = myCourseAlldown.get(n).getSchoolId();
                myCourseAlldown.get(n).setSchoolName(schoolName);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
                String format = formatter.format(myCourseAlldown.get(n).getCoursestartTime());
                if (Integer.parseInt(format.substring(5, 7)) > 6) {
                    myCourseAlldown.get(n).setCourseyear(format.substring(0, 4) + "下半年度");
                } else {
                    myCourseAlldown.get(n).setCourseyear(format.substring(0, 4) + "上半年度");
                }
                list.add(myCourseAlldown.get(n));
            }

            for (int i = 0; i < list.size(); i++) {      //通过身份证计算出每个学员的年龄并返回
                String card=list.get(i).getIdentityCard();
                int age;
                if (card!=null) {
                    age = TeacherServiceImpl.getAgeByCertId(card);
                    list.get(i).setAge(age);
                }
            }

            return new WebResult("200", "查询成功", list);
        }


        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());

        pageVo.setPayStatus(5);
        List<MyCourseAll> myCourseAlltop2 = questionSearchMapper.findMyCourseAlltop(pageVo);
        pageVo.setPayStatus(1);
        List<MyCourseAll> myCourseAlldown2 = questionSearchMapper.findMyCourseAlldown(pageVo);
        VateType vateType2 = questionSearchMapper.findCourseImage(2);
        List<MyCourseAll> list2 = new ArrayList();

        //查询现场报名相关成员记录
        LivePayVo livePayVo=new LivePayVo();
        livePayVo.setCourseId(pageVo.getCourseId());
        List<LivePayVo> livePayVos = eduLivePayMapper.selectLivePay(livePayVo);
        List<MyCourseAll> livePaysList=new LinkedList<>();

        for (int n = 0; n <livePayVos.size(); n++) {     //封装线下缴费列表记录
            livePaysList.get(n).setCourseId(livePayVos.get(n).getCourseId());
            livePaysList.get(n).setUserName(livePayVos.get(n).getUserName());
            livePaysList.get(n).setCourseName(livePayVos.get(n).getCourseName());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
            String format = formatter.format(livePayVos.get(n).getPayDate());
            if (Integer.parseInt(format.substring(5, 7)) > 6) {
                livePaysList.get(n).setCourseyear(format.substring(0, 4) + "下半年度");
            } else {
                livePaysList.get(n).setCourseyear(format.substring(0, 4) + "上半年度");
            }
            livePaysList.get(n).setCourseTeacher(livePayVos.get(n).getCourseTeacher());
            livePaysList.get(n).setSchoolName(schoolName);
            livePaysList.get(n).setAddress(livePayVos.get(n).getAddress());
            livePaysList.get(n).setPhone(livePayVos.get(n).getPhone());
            livePaysList.get(n).setIdentityCard(livePayVos.get(n).getIdentityCard());
            livePaysList.get(n).setJob(livePayVos.get(n).getJob());
            livePaysList.get(n).setStatu("现场收费");
            list2.add(livePaysList.get(n));
        }

        for (int m = 0; m < myCourseAlltop2.size(); m++) {



            myCourseAlltop2.get(m).setCourseImage(vateType.getImage());
            myCourseAlltop2.get(m).setStatu("线上报名");
            Integer schoolId = myCourseAlltop2.get(m).getSchoolId();

            myCourseAlltop2.get(m).setSchoolName(schoolName);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
            String format = formatter.format(myCourseAlltop2.get(m).getCoursestartTime());
            if (Integer.parseInt(format.substring(5, 7)) > 6) {
                myCourseAlltop2.get(m).setCourseyear(format.substring(0, 4) + "下半年度");
            } else {
                myCourseAlltop2.get(m).setCourseyear(format.substring(0, 4) + "上半年度");
            }
            list2.add(myCourseAlltop2.get(m));
        }
        for (int n = 0; n < myCourseAlldown2.size(); n++) {
            myCourseAlldown2.get(n).setCourseImage(vateType.getImage());
            myCourseAlldown2.get(n).setStatu("线下报名");
            Integer schoolId = myCourseAlldown2.get(n).getSchoolId();

            myCourseAlldown2.get(n).setSchoolName(schoolName);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
            String format = formatter.format(myCourseAlldown2.get(n).getCoursestartTime());
            if (Integer.parseInt(format.substring(5, 7)) > 6) {
                myCourseAlldown2.get(n).setCourseyear(format.substring(0, 4) + "下半年度");
            } else {
                myCourseAlldown2.get(n).setCourseyear(format.substring(0, 4) + "上半年度");
            }
            list2.add(myCourseAlldown2.get(n));
        }



        for (int i = 0; i < list2.size(); i++) {      //通过身份证计算出每个学员的年龄并返回
            String card=list2.get(i).getIdentityCard();
            int age;
            if (card!=null) {
                age = TeacherServiceImpl.getAgeByCertId(card);
                list2.get(i).setAge(age);
            }
        }
        PageInfo pageInfo3 = new PageInfo<>(myCourseAlltop2);
        PageInfo pageInfo2 = new PageInfo<>(myCourseAlldown2);
        PageInfo pageInfo = new PageInfo<>(list2);
           pageInfo.setTotal(pageInfo3.getTotal()+pageInfo2.getTotal());
        return new WebResult("200", "查询成功", pageInfo);
    }

    //app端班级成员列表()
    @Override
    public WebResult findMyClassUsers(PageVo pageVo) {

        pageVo.setPayStatus(5);
        List<MyCourseAll> myCourseAlltop = questionSearchMapper.findMyCourseAlltop(pageVo);
        pageVo.setPayStatus(1);
        List<MyCourseAll> myCourseAlldown = questionSearchMapper.findMyCourseAlldown(pageVo);
        VateType vateType = questionSearchMapper.findCourseImage(2);
        List<MyCourseAll> list = new ArrayList();
        String schoolName = schoolMapper.findSchoolById(pageVo.getSchoolId());

        //查询现场报名相关成员记录
        LivePayVo livePayVo=new LivePayVo();
        livePayVo.setCourseId(pageVo.getCourseId());
        List<LivePayVo> livePayVos = eduLivePayMapper.selectLivePay(livePayVo);
        List<MyCourseAll> livePaysList=new LinkedList<>();

        for (int n = 0; n <livePayVos.size(); n++) {     //封装线下缴费列表记录
            livePaysList.get(n).setCourseId(livePayVos.get(n).getCourseId());
            livePaysList.get(n).setUserName(livePayVos.get(n).getUserName());
            livePaysList.get(n).setCourseName(livePayVos.get(n).getCourseName());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
            String format = formatter.format(livePayVos.get(n).getPayDate());
            if (Integer.parseInt(format.substring(5, 7)) > 6) {
                livePaysList.get(n).setCourseyear(format.substring(0, 4) + "下半年度");
            } else {
                livePaysList.get(n).setCourseyear(format.substring(0, 4) + "上半年度");
            }
            livePaysList.get(n).setCourseTeacher(livePayVos.get(n).getCourseTeacher());
            livePaysList.get(n).setSchoolName(schoolName);
            livePaysList.get(n).setAddress(livePayVos.get(n).getAddress());
            livePaysList.get(n).setPhone(livePayVos.get(n).getPhone());
            livePaysList.get(n).setIdentityCard(livePayVos.get(n).getIdentityCard());
            livePaysList.get(n).setJob(livePayVos.get(n).getJob());
            livePaysList.get(n).setStatu("现场收费");
            list.add(livePaysList.get(n));
        }


        for (int m = 0; m < myCourseAlltop.size(); m++) {



            myCourseAlltop.get(m).setCourseImage(vateType.getImage());
            myCourseAlltop.get(m).setStatu("线上报名");
            Integer schoolId = myCourseAlltop.get(m).getSchoolId();
            myCourseAlltop.get(m).setSchoolName(schoolName);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
            String format = formatter.format(myCourseAlltop.get(m).getEndDate());
            if (Integer.parseInt(format.substring(5, 7)) > 6) {
                myCourseAlltop.get(m).setCourseyear(format.substring(0, 4) + "下半年度");
            } else {
                myCourseAlltop.get(m).setCourseyear(format.substring(0, 4) + "上半年度");
            }
            list.add(myCourseAlltop.get(m));
        }
        for (int n = 0; n < myCourseAlldown.size(); n++) {
            myCourseAlldown.get(n).setCourseImage(vateType.getImage());
            myCourseAlldown.get(n).setStatu("线下报名");
            Integer schoolId = myCourseAlldown.get(n).getSchoolId();

            myCourseAlldown.get(n).setSchoolName(schoolName);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
            String format = formatter.format(myCourseAlldown.get(n).getEndDate());
            if (Integer.parseInt(format.substring(5, 7)) > 6) {
                myCourseAlldown.get(n).setCourseyear(format.substring(0, 4) + "下半年度");
            } else {
                myCourseAlldown.get(n).setCourseyear(format.substring(0, 4) + "上半年度");
            }
            list.add(myCourseAlldown.get(n));
        }

        if (pageVo.getPageSize() == 0) {

            for (int i = 0; i < list.size(); i++) {      //通过身份证计算出每个学员的年龄并返回
                String card=list.get(i).getIdentityCard();
                int age;
                if (card!=null) {
                    age = TeacherServiceImpl.getAgeByCertId(card);
                    list.get(i).setAge(age);
                }
            }

            return new WebResult("200", "查询成功", list);
        }


        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());

        for (int i = 0; i < list.size(); i++) {      //通过身份证计算出每个学员的年龄并返回
            String card=list.get(i).getIdentityCard();
            int age;
            if (card!=null) {
                age = TeacherServiceImpl.getAgeByCertId(card);
                list.get(i).setAge(age);
            }
        }

        PageInfo pageInfo = new PageInfo<>(list);
        return new WebResult("200", "查询成功", pageInfo);
    }

    //1.问卷调查列表 and 2.进入详情页    (*进入详情也在此方法共用，在传入了)
    @Override
    public WebResult findSearchQuestionAll(PageVo pageVo) {

        if (pageVo.getPageSize() == 0) {
            //进入详情页
            if (pageVo.getSid() != null || pageVo.getCourseId()!= null) {     //进入某篇问卷调查的详情页==>> 2.


                List<SearchQuestion> searchQuestionAll2 = questionSearchMapper.findSearchQuestionAll(pageVo.getStatus(),pageVo.getSid(), pageVo.getTopId(), pageVo.getDownId(), pageVo.getSchoolId(), pageVo.getTid(),pageVo.getCourseId(), pageVo.getSearch());
                 if (searchQuestionAll2==null){
                     return new WebResult("200","没有找到对应试题","");
                 }
                if (pageVo.getTid() == 1) {

                    List<Question> questions = questionSearchMapper.findQuestionAll(pageVo.getSid(), null, pageVo.getTopId(), pageVo.getDownId(), pageVo.getSearch());
                    if (questions.isEmpty()) {
                        return new WebResult("300", "此问卷暂时还没有添加相关试题内容", "");

                    }
                    searchQuestionAll2.get(0).setQuestions(questions); //将选项设置给对应问卷
                }
                if (pageVo.getTid() == 2 || pageVo.getTid() == 0) {
                    List<SearchQuestion> searchQuestionByCourseId = questionSearchMapper.findSearchQuestionByCourseId(pageVo.getCourseId());
                    Integer sid=null;
                    if (!searchQuestionByCourseId.isEmpty()){
                          sid= searchQuestionByCourseId.get(0).getSid();
                    }
                    List<Question> questions = questionSearchMapper.findQuestionAll(sid, null, pageVo.getTopId(), pageVo.getDownId(), pageVo.getSearch());
                    if (questions.isEmpty()) {
                        return new WebResult("300", "此课程暂时还没有添加评价相关内容", "");

                    }
                    searchQuestionAll2.get(0).setQuestions(questions);   //将选项设置给对应问卷
                }
                if (pageVo.getTid() == 3) {//返回对应投票详情

                    List<VoteItem> voteItembySid = questionSearchMapper.findVoteItembySid(pageVo.getSid());
                    if (voteItembySid.isEmpty()) {
                        return new WebResult("300", "此投票暂时还没有添加相关选项内容", "");

                    }
                    searchQuestionAll2.get(0).setVoteItems(voteItembySid);
                }
                questionSearchMapper.updateSearchQuestionVisits(searchQuestionAll2.get(0).getVisits() + 1, searchQuestionAll2.get(0).getSid()); //让该篇问卷调查的浏览量+1

                return new WebResult("200", "进入详情页", searchQuestionAll2.get(0));
            }
            //问卷调查列表==>>1.
            List<SearchQuestion> searchQuestionAll = questionSearchMapper.findSearchQuestionAll(pageVo.getStatus(),pageVo.getSid(), pageVo.getTopId(), pageVo.getDownId(), pageVo.getSchoolId(), pageVo.getTid(),pageVo.getCourseId(), pageVo.getSearch());
            if (searchQuestionAll==null){
                return new WebResult("200","没有找到对应试题","");
            }
//            int size=searchQuestionAll.size();

            return new WebResult("200", "查询成功", searchQuestionAll);
        }
        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());
        //进入详情页
        if (pageVo.getSid() != null || pageVo.getTopId() != null || pageVo.getDownId() != null) {  //进入某篇问卷调查的详情页==>> 2.

            List<SearchQuestion> searchQuestionAll = questionSearchMapper.findSearchQuestionAll(pageVo.getStatus(),pageVo.getSid(), pageVo.getTopId(), pageVo.getDownId(), pageVo.getSchoolId(), null,pageVo.getCourseId(), pageVo.getSearch());
            if (pageVo.getTid() == 1) {
                if (searchQuestionAll.get(0).getQuestions().isEmpty()) {
                    return new WebResult("300", "此问卷暂时还没有添加相关试题内容", "");

                }
                List<Question> questions = questionSearchMapper.findQuestionAll(pageVo.getSid(), null, pageVo.getTopId(), pageVo.getDownId(), pageVo.getSearch());
                searchQuestionAll.get(0).setQuestions(questions);
            }
            if (pageVo.getTid() == 2) {
                List<SearchQuestion> searchQuestionByCourseId = questionSearchMapper.findSearchQuestionByCourseId(pageVo.getCourseId());
                Integer sid=null;
                if (!searchQuestionByCourseId.isEmpty()){
                    sid= searchQuestionByCourseId.get(0).getSid();
                }
                List<Question> questions = questionSearchMapper.findQuestionAll(sid, null, pageVo.getTopId(), pageVo.getDownId(), pageVo.getSearch());
                if (questions.isEmpty()) {
                    return new WebResult("300", "此课程暂时还没有添加评价相关内容", "");

                }
                searchQuestionAll.get(0).setQuestions(questions);
            }
            if (pageVo.getTid() == 3) {//返回对应投票详情
                if (searchQuestionAll.get(0).getVoteItems().isEmpty()) {
                    return new WebResult("300", "此投票暂时还没有添加相关选项内容", "");

                }

                List<VoteItem> voteItembySid = questionSearchMapper.findVoteItembySid(pageVo.getSid());
                searchQuestionAll.get(0).setVoteItems(voteItembySid);
            }
            questionSearchMapper.updateSearchQuestionVisits(searchQuestionAll.get(0).getVisits() + 1, searchQuestionAll.get(0).getSid()); //让该篇问卷调查的浏览量+1

            return new WebResult("200", "进入详情页", searchQuestionAll.get(0));
        }
        //问卷调查列表==>> 1.
        List<SearchQuestion> searchQuestionAll2 = questionSearchMapper.findSearchQuestionAll(pageVo.getStatus(),pageVo.getSid(), pageVo.getTopId(), pageVo.getDownId(), pageVo.getSchoolId(), pageVo.getTid(),pageVo.getCourseId(), pageVo.getSearch());

        PageInfo pageInfo = new PageInfo<>(searchQuestionAll2);
        return new WebResult("200", "查询成功", pageInfo);
    }


    //1.问卷调查列表 and 2.进入详情页    (*进入详情也在此方法共用，在传入了)   =============>>后台进入详情页（可反显选项对应分数）
    @Override
    public WebResult findServerSearchQuestionAll(PageVo pageVo) {

        if (pageVo.getPageSize() == 0) {
            //进入详情页
            if (pageVo.getSid() != null || pageVo.getCourseId()!= null) {     //进入某篇问卷调查的详情页==>> 2.


                List<SearchQuestion> searchQuestionAll2 = questionSearchMapper.findSearchQuestionAll(pageVo.getStatus(),pageVo.getSid(), pageVo.getTopId(), pageVo.getDownId(), pageVo.getSchoolId(), pageVo.getTid(),pageVo.getCourseId(), pageVo.getSearch());
                 if (searchQuestionAll2==null){
                     return new WebResult("200","没有找到对应试题","");
                 }
                if (pageVo.getTid() == 1) {

                    List<Question> questions = questionSearchMapper.findQuestionAll(pageVo.getSid(), null, pageVo.getTopId(), pageVo.getDownId(), pageVo.getSearch());
                    if (questions.isEmpty()) {
                        return new WebResult("300", "此问卷暂时还没有添加相关试题内容", "");

                    }
                    searchQuestionAll2.get(0).setQuestions(questions); //将选项设置给对应问卷
                }
                if (pageVo.getTid() == 2 || pageVo.getTid() == 0) {
                    List<SearchQuestion> searchQuestionByCourseId = questionSearchMapper.findSearchQuestionByCourseId(pageVo.getCourseId());
                    Integer sid=null;
                    if (!searchQuestionByCourseId.isEmpty()){
                          sid= searchQuestionByCourseId.get(0).getSid();
                    }
                    List<Question> questions = questionSearchMapper.findQuestionAllAndGrade(sid,pageVo.getTid(),pageVo.getSearch());
                    if (questions.isEmpty()) {
                        return new WebResult("300", "此课程暂时还没有添加评价相关内容", "");

                    }
                    searchQuestionAll2.get(0).setQuestions(questions);   //将选项设置给对应问卷
                }
                if (pageVo.getTid() == 3) {//返回对应投票详情

                    List<VoteItem> voteItembySid = questionSearchMapper.findVoteItembySid(pageVo.getSid());
                    if (voteItembySid.isEmpty()) {
                        return new WebResult("300", "此投票暂时还没有添加相关选项内容", "");

                    }
                    searchQuestionAll2.get(0).setVoteItems(voteItembySid);
                }
                questionSearchMapper.updateSearchQuestionVisits(searchQuestionAll2.get(0).getVisits() + 1, searchQuestionAll2.get(0).getSid()); //让该篇问卷调查的浏览量+1

                return new WebResult("200", "进入详情页", searchQuestionAll2.get(0));
            }
            //问卷调查列表==>>1.
            List<SearchQuestion> searchQuestionAll = questionSearchMapper.findSearchQuestionAll(pageVo.getStatus(),pageVo.getSid(), pageVo.getTopId(), pageVo.getDownId(), pageVo.getSchoolId(), pageVo.getTid(),pageVo.getCourseId(), pageVo.getSearch());
            if (searchQuestionAll==null){
                return new WebResult("200","没有找到对应试题","");
            }
//            int size=searchQuestionAll.size();

            return new WebResult("200", "查询成功", searchQuestionAll);
        }
        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());
        //进入详情页
        if (pageVo.getSid() != null || pageVo.getTopId() != null || pageVo.getDownId() != null) {  //进入某篇问卷调查的详情页==>> 2.

            List<SearchQuestion> searchQuestionAll = questionSearchMapper.findSearchQuestionAll(pageVo.getStatus(),pageVo.getSid(), pageVo.getTopId(), pageVo.getDownId(), pageVo.getSchoolId(), null,pageVo.getCourseId(), pageVo.getSearch());
            if (pageVo.getTid() == 1) {
                if (searchQuestionAll.get(0).getQuestions().isEmpty()) {
                    return new WebResult("300", "此问卷暂时还没有添加相关试题内容", "");

                }
                List<Question> questions = questionSearchMapper.findQuestionAll(pageVo.getSid(), null, pageVo.getTopId(), pageVo.getDownId(), pageVo.getSearch());
                searchQuestionAll.get(0).setQuestions(questions);
            }
            if (pageVo.getTid() == 2) {
                List<SearchQuestion> searchQuestionByCourseId = questionSearchMapper.findSearchQuestionByCourseId(pageVo.getCourseId());
                Integer sid=null;
                if (!searchQuestionByCourseId.isEmpty()){
                    sid= searchQuestionByCourseId.get(0).getSid();
                }
                List<Question> questions = questionSearchMapper.findQuestionAll(sid, null, pageVo.getTopId(), pageVo.getDownId(), pageVo.getSearch());
                if (questions.isEmpty()) {
                    return new WebResult("300", "此课程暂时还没有添加评价相关内容", "");

                }
                searchQuestionAll.get(0).setQuestions(questions);
            }
            if (pageVo.getTid() == 3) {//返回对应投票详情
                if (searchQuestionAll.get(0).getVoteItems().isEmpty()) {
                    return new WebResult("300", "此投票暂时还没有添加相关选项内容", "");

                }

                List<VoteItem> voteItembySid = questionSearchMapper.findVoteItembySid(pageVo.getSid());
                searchQuestionAll.get(0).setVoteItems(voteItembySid);
            }
            questionSearchMapper.updateSearchQuestionVisits(searchQuestionAll.get(0).getVisits() + 1, searchQuestionAll.get(0).getSid()); //让该篇问卷调查的浏览量+1

            return new WebResult("200", "进入详情页", searchQuestionAll.get(0));
        }
        //问卷调查列表==>> 1.
        List<SearchQuestion> searchQuestionAll2 = questionSearchMapper.findSearchQuestionAll(pageVo.getStatus(),pageVo.getSid(), pageVo.getTopId(), pageVo.getDownId(), pageVo.getSchoolId(), pageVo.getTid(),pageVo.getCourseId(), pageVo.getSearch());

        PageInfo pageInfo = new PageInfo<>(searchQuestionAll2);
        return new WebResult("200", "查询成功", pageInfo);
    }

    //按“热门”条件进行查询
    @Override
    public WebResult findSearchQuestionAllbyVisits(PageVo pageVo) {
        if (pageVo.getPageSize() == 0) {
            //进入详情页
            if (pageVo.getSid() != null) {     //进入某篇问卷调查的详情页==>> 2.
                List<Question> questions = questionSearchMapper.findQuestionAll(pageVo.getSid(), pageVo.getTid(), pageVo.getTopId(), pageVo.getDownId(), pageVo.getSearch());
                List<SearchQuestion> searchQuestionAll2 = questionSearchMapper.findSearchQuestionAll(pageVo.getStatus(),pageVo.getSid(), pageVo.getTopId(), pageVo.getDownId(), pageVo.getSchoolId(), pageVo.getTid(),pageVo.getCourseId(), pageVo.getSearch());

                searchQuestionAll2.get(0).setQuestions(questions);
                questionSearchMapper.updateSearchQuestionVisits(searchQuestionAll2.get(0).getVisits() + 1, searchQuestionAll2.get(0).getSid()); //让该篇问卷调查的浏览量+1
                return new WebResult("200", "进入详情页", searchQuestionAll2.get(0));
            }
            //问卷调查列表==>>1.
            List<SearchQuestion> searchQuestionAll = questionSearchMapper.findSearchQuestionAllbyVisits(pageVo.getSid(), pageVo.getTopId(), pageVo.getDownId(), pageVo.getSchoolId(), pageVo.getTid(), pageVo.getSearch());
            if (pageVo.getUserId() != null) {//截止时间过了的app端不会显示，而后台可以看见
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
            List<Question> questions = questionSearchMapper.findQuestionAll(pageVo.getSid(), pageVo.getTid(), pageVo.getTopId(), pageVo.getDownId(), pageVo.getSearch());
            List<SearchQuestion> searchQuestionAll = questionSearchMapper.findSearchQuestionAll(pageVo.getStatus(),pageVo.getSid(), pageVo.getTopId(), pageVo.getDownId(), pageVo.getSchoolId(), pageVo.getTid(),pageVo.getCourseId(), pageVo.getSearch());
            searchQuestionAll.get(0).setQuestions(questions);
            questionSearchMapper.updateSearchQuestionVisits(searchQuestionAll.get(0).getVisits() + 1, searchQuestionAll.get(0).getSid()); //让该篇问卷调查的浏览量+1
            return new WebResult("200", "进入详情页", searchQuestionAll.get(0));
        }
        //问卷调查列表==>> 1.
        List<SearchQuestion> searchQuestionAll2 = questionSearchMapper.findSearchQuestionAllbyVisits(pageVo.getSid(), pageVo.getTopId(), pageVo.getDownId(), pageVo.getSchoolId(), pageVo.getTid(), pageVo.getSearch());
        if (pageVo.getUserId() != null) {//截止时间过了的app端不会显示，而后台可以看见
            for (int i = 0; i < searchQuestionAll2.size(); i++) {      //处理edu_search_question表里面status=0的，不让其展示
                if (searchQuestionAll2.get(i).getStatus() == 2) {
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
                List<Question> questions = questionSearchMapper.findQuestionAll(pageVo.getSid(), pageVo.getTid(), pageVo.getTopId(), pageVo.getDownId(), pageVo.getSearch());
                List<SearchQuestion> searchQuestionAll2 = questionSearchMapper.findSearchQuestionAll(pageVo.getStatus(),pageVo.getSid(), pageVo.getTopId(), pageVo.getDownId(), pageVo.getSchoolId(), pageVo.getTid(),pageVo.getCourseId(), pageVo.getSearch());
                searchQuestionAll2.get(0).setQuestions(questions);
                questionSearchMapper.updateSearchQuestionVisits(searchQuestionAll2.get(0).getVisits() + 1, searchQuestionAll2.get(0).getSid()); //让该篇问卷调查的浏览量+1
                return new WebResult("200", "进入详情页", searchQuestionAll2.get(0));
            }
            //问卷调查列表==>>1.
            List<SearchQuestion> searchQuestionAll = questionSearchMapper.findSearchQuestioncomposite(pageVo.getSid(), pageVo.getTopId(), pageVo.getDownId(), pageVo.getSchoolId(), pageVo.getTid(), pageVo.getSearch());
            if (pageVo.getUserId() != null) {//截止时间过了的app端不会显示，而后台可以看见
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
            List<Question> questions = questionSearchMapper.findQuestionAll(pageVo.getSid(), pageVo.getTid(), pageVo.getTopId(), pageVo.getDownId(), pageVo.getSearch());
            List<SearchQuestion> searchQuestionAll = questionSearchMapper.findSearchQuestionAll(pageVo.getStatus(),pageVo.getSid(), pageVo.getTopId(), pageVo.getDownId(), pageVo.getSchoolId(), pageVo.getTid(),pageVo.getCourseId(), pageVo.getSearch());
            searchQuestionAll.get(0).setQuestions(questions);
            questionSearchMapper.updateSearchQuestionVisits(searchQuestionAll.get(0).getVisits() + 1, searchQuestionAll.get(0).getSid()); //让该篇问卷调查的浏览量+1
            return new WebResult("200", "进入详情页", searchQuestionAll.get(0));
        }
        //问卷调查列表==>> 1.
        List<SearchQuestion> searchQuestionAll2 = questionSearchMapper.findSearchQuestioncomposite(pageVo.getSid(), pageVo.getTopId(), pageVo.getDownId(), pageVo.getSchoolId(), pageVo.getTid(), pageVo.getSearch());
        if (pageVo.getUserId() != null) {//截止时间过了的app端不会显示，而后台可以看见
            for (int i = 0; i < searchQuestionAll2.size(); i++) {      //处理edu_search_question表里面status=2的，不让其展示
                if (searchQuestionAll2.get(i).getStatus() == 2) {
                    searchQuestionAll2.remove(i);      //移除集合里面status=2的
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

        String userName = questionSearchMapper.findAnswerRecordbyUserId(answerRecord.getCourseId(),answerRecord.getUid(), answerRecord.getList().get(0).getSid(), answerRecord.getTopId(), answerRecord.getDownId());
        if (userName != null) {
            return new WebResult("300", "您已提交过此内容,无需重复操作！", "");
        }
        int[] answerNums = answerRecord.getAnswerNums();
        if (answerNums.length == 0) {
            return new WebResult("300", "您还未做试卷任何题目，不能提交空试卷！", "");
        }
        //找到对应主题的题目及选项(选项及分数权重的集合)的集合，主要针对教师评价
        List<Question> questions = questionSearchMapper.findQuestionAllAndGrade(answerRecord.getList().get(0).getSid(),null, null);
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
        for (int n = 0; n < answerValues.size(); n++) {
            if (n == 0) {
                answer = answerValues.get(n).getTitleId() + "-" + answerValues.get(n).getAnswer() + "; ";
            } else {
                answer = answer.concat(answerValues.get(n).getTitleId() + "-" + answerValues.get(n).getAnswer() + "; ");
            }
        }

        //统计对应问卷相关选项的被选择量
        if (answerRecord.getList().get(0).getTid() == 1 || answerRecord.getList().get(0).getTid() == 2) {
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
        if (answerRecord.getTid() == 2) {
            answerRecord.setType("教师评价");
        }
        if (answerRecord.getTid() == 1) {
            answerRecord.setType("问卷调查");
        }
        answerRecord.setTid(answerRecord.getTid());
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
     //处理截止时间过了的问卷
    @Transactional
    @Override
    public void deleteScheduleTask() {
        List<SearchQuestion> searchQuestions = questionSearchMapper.findSearchQuestionAll(null,null, null, null, null, null,null, null);
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        for (SearchQuestion searchQuestion : searchQuestions) {
            Date endTime = searchQuestion.getEndTime();

            if (endTime == null) {
                // eduArticleMapper.updateIsTop(eduArticle.getId(), "0", null);
                continue;
            }
            boolean before = now.before(endTime);
            boolean after = now.after(endTime);
            //判断两个Date是不是同一天
            boolean samedate = DateUtils.isSameDay(endTime, now);
            if (samedate) {
                before = true;
                after = false;
            }

            if (before && !after && samedate) {
                //
                questionSearchMapper.updateSearchQuestionStatues(2, searchQuestion.getSid());

            }

        }
    }
    @Transactional
    @Override
    public WebResult deleteQuestionOne(Question question) {
         questionSearchMapper.deleteQuestionOne(question.getQid());
        return new WebResult("200", "删除成功", "");
    }
    @Transactional
    @Override
    public WebResult deleteVoteItemOne(VoteItem voteItem) {
         questionSearchMapper.deleteVoteItemOne(voteItem.getId());
        return new WebResult("200", "删除成功", "");
    }

    // 换课
    @Transactional
    @Override
    public WebResult updateCourseById(EduPayrecord eduPayrecord) {
        User user = userMapper.queryByItemsId(eduPayrecord.getUserId());

        //查询专业接受报名的人数
        int acceptNum = eduCourseMapper.queryAcceptNum(eduPayrecord.getCourseId());
        if (acceptNum == 0) {
            return new WebResult("200", "换课失败：该课程尚未设置计划招生人数", "");
        } else {
            //查询用户所对应的专业显示已经购买人数
            Map<String, Object> param = new HashMap<>();
            param.put("course_id", eduPayrecord.getCourseId());
            param.put("payment_status", "PAID");

            int olineNum = orderMapper.countPayCourseNum(param);

            //查询用户所对应的专业线下的总人数
            Integer offNum = eduOfflineOrderMapper.queryOffRecordNum(eduPayrecord.getCourseId());

            //得到最终报名人数
            Integer payNum = olineNum + offNum;
            //可报名剩余人数
            int nowtotal = acceptNum - payNum;
            if (nowtotal<=0){
                return new WebResult("600", "换课失败：该课程人数已满", "");
            }
        }

        eduPayrecord.setIsDelete(2);
        List<EduPayrecord> eduPayrecords = eduPayrecordMapper.selectByUserIdAndIsdelete(eduPayrecord);
             List<EduPayrecord>  newList=new LinkedList<>();
        for (int i = 0; i < eduPayrecords.size(); i++) {    //判断排重
               if (eduPayrecords.get(i).getCourseId().equals(eduPayrecord.getCourseId())){
               newList.add(eduPayrecords.get(i));
               }
        }
          if (newList.isEmpty()) {
              EduCourse eduCourse = eduCourseMapper.selectByPrimaryKey(eduPayrecord.getCourseId());
              eduPayrecord.setCourseName(eduCourse.getCourseName());
              questionSearchMapper.updateCourseById(eduPayrecord);
              return new WebResult("200", "更新成功", "");
          }else{
              return new WebResult("400", "报名重复,您已经报名过该班级", "");
          }
    }
     //删除这条记录之前,先增加到另一张表
    @Transactional
    @Override
    public WebResult deleteById(EduPayrecord  eduPayrecord) {

        List<EduLivePay> eduLivePays = questionSearchMapper.selectPeopleAndMoney(eduPayrecord);//查出来后取第一个元素设置进去

        //创建一个新的对象,接收到另一张表的数据
        EduLivePay eduLivePay = new EduLivePay();
        eduLivePay.setPhone(eduLivePays.get(0).getPhone());//电话号码要查
        eduLivePay.setIdentityCard(eduLivePays.get(0).getIdentityCard());//身份证要查
        eduLivePay.setAddress(eduLivePays.get(0).getAddress());//地址要查
        eduLivePay.setJob(eduLivePays.get(0).getJob());//职务要查
        eduLivePay.setUserName(eduLivePays.get(0).getUserName());//用户姓名要查
        eduLivePay.setCourseName(eduPayrecord.getCourseName());
        eduLivePay.setCourseId(eduPayrecord.getCourseId());
        eduLivePay.setCourseTeacher(eduPayrecord.getCourseTeacher());
        eduLivePay.setSchoolName(eduPayrecord.getSchoolName());

        eduLivePay.setPayCode(3);//线上报名退课
        eduLivePay.setSchoolId(Integer.valueOf(eduPayrecord.getSchoolId()));
        eduLivePay.setEmployer(eduLivePays.get(0).getEmployer());//人员分类 要查
        eduLivePay.setMoney(eduLivePays.get(0).getMoney());//金额要查

       questionSearchMapper.insertClassOut(eduLivePay);

        questionSearchMapper.deleteById(eduPayrecord.getId());
        return new WebResult("200", "退课成功", "") ;
    }

    @Override
    public WebResult selectClassOut(LivePayVo livePayVo) {
        //不分页显示
        if(livePayVo.getPageSize()==0){
            List<LivePayVo> livePayVos = questionSearchMapper.selectClassOut(livePayVo);
            return new WebResult("200", "查询成功", livePayVos);
        }
        //分页显示
        PageHelper.startPage(livePayVo.getPageNum(),livePayVo.getPageSize());
        List<LivePayVo> livePayVosPage =questionSearchMapper.selectClassOut(livePayVo);
        PageInfo  pageInfo=new PageInfo(livePayVosPage);
        return new WebResult("200", "查询成功", pageInfo);
    }


}
