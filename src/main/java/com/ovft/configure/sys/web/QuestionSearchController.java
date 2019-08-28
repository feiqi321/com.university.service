package com.ovft.configure.sys.web;

import com.ovft.configure.constant.ConstantClassField;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.*;
import com.ovft.configure.sys.service.QuestionSearchService;
import com.ovft.configure.sys.utils.RedisUtil;
import com.ovft.configure.sys.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 投票专区（调查问卷、教师评价、投票评先）
 **/
@RestController
public class QuestionSearchController {
    @Autowired
    private QuestionSearchService questionSearchService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 添加修改问卷调查主题（SearchQuestion）
     *
     * @param
     * @return
     */
    @PostMapping(value = "/server/createSearchQuestion")
    public WebResult createSearchQuestion(@RequestBody SearchQuestion searchQuestion, HttpServletRequest request) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if (o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
            Admin hget = (Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            String name = hget.getName();
             searchQuestion.setAuthor(name);
            if (hget.getRole() != 0) {
                searchQuestion.setSchoolId(hget.getSchoolId());
            }
            return questionSearchService.createSearchQuestion(searchQuestion);
        } else {
            return new WebResult("50012", "请先登录", "");
        }

    }

    /**
     * 删除一条结果记录（SearchQuestion）
     *
     * @param
     * @return
     */
    @PostMapping(value = "/server/deleteAnswerRecordOne")
    public WebResult deleteAnswerRecordOne(@RequestBody AnswerRecord answerRecord,HttpServletRequest request) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if (o != null) {
            Integer id = (Integer) o;
            return questionSearchService.deleteAnswerRecordOne(answerRecord);
        } else {
            return new WebResult("50012", "请先登录", "");
        }
    }
    /**
     * 批量删除结果记录（SearchQuestion）
     *
     * @param
     * @return
     */
    @PostMapping(value = "/server/BigDeleteAnswerRecord")
    public WebResult BigDeleteAnswerRecord(@RequestBody AnswerRecord answerRecord,HttpServletRequest request) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if (o != null) {
            Integer id = (Integer) o;
            return questionSearchService.BigDeleteAnswerRecord(answerRecord);
        } else {
            return new WebResult("50012", "请先登录", "");
        }
    }

    /**
     * 删除问卷调查（SearchQuestion）
     *
     * @param
     * @return
     */
    @PostMapping(value = "/server/deleteSearchQuestion")
    public WebResult deleteSearchQuestion(@RequestBody SearchQuestion searchQuestion,HttpServletRequest request) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if (o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
            Admin hget = (Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            if (hget.getRole() != 0) {
                searchQuestion.setSchoolId(hget.getSchoolId());
            }
            return questionSearchService.deleteSearchQuestion(searchQuestion.getSid(),searchQuestion.getTid(),searchQuestion.getCourseId());
        } else {
            return new WebResult("50012", "请先登录", "");
        }
    }
    /**
     * 添加用户填写结果记录
     *
     * @param
     * @return
     */
    @PostMapping(value = "/user/createAnswerRecord")
    public WebResult createAnswerRecord(@RequestBody AnswerRecord answerRecord,HttpServletRequest request) {
             Integer userId=Integer.parseInt(request.getHeader("userId"));
             if (userId==null){
             return  new WebResult("50012","请先登录！","");

             }
             answerRecord.setUid(userId);
             return questionSearchService.createAnswerRecord(answerRecord);

    }
    /**
     * 提交投票问卷
     *
     * @param
     * @return
     */
    @PostMapping(value = "/user/submitVoteRecord")
    public WebResult submitVoteRecord(@RequestBody AnswerRecord answerRecord,HttpServletRequest request) {
             Integer userId=Integer.parseInt(request.getHeader("userId"));
             if (userId!=null){
                   answerRecord.setUid(userId);
             }
             return  questionSearchService.submitVoteRecord(answerRecord,answerRecord.getId());
    }
    /**
     * 编辑时删除指定的题目
     *
     * @param
     * @return
     */
    @PostMapping(value = "/user/deleteQuestionOne")
    public WebResult deleteQuestionOne(@RequestBody Question question,HttpServletRequest request) {

             return  questionSearchService.deleteQuestionOne(question);
    }
    /**
     * 编辑时删除指定的投票选项
     *
     * @param
     * @return
     */
    @PostMapping(value = "/user/deleteVoteItemOne")
    public WebResult deleteVoteItemOne(@RequestBody VoteItem voteItem,HttpServletRequest request) {

             return  questionSearchService.deleteVoteItemOne(voteItem);
    }
    /**
     * 查看所有用户问卷填写结果记录
     *
     * @param
     * @return
     */
    @PostMapping(value = "/server/findAnswerRecord")
    public WebResult findAnswerRecord(HttpServletRequest request, @RequestBody PageVo pageVo) {
                  return    questionSearchService.findAnswerRecord(pageVo);
    }
    /**
     * 查看所有投票结果记录
     *
     * @param
     * @return
     */
    @PostMapping(value = "/server/findVoteRecord")
    public WebResult findVoteRecord(HttpServletRequest request, @RequestBody PageVo pageVo) {
                  return    questionSearchService.findVoteRecord(pageVo);
    }

    /**
     * 前台 问卷调查列表及模糊查询（分页） 以及进入详情页接口
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/searchQuestion/findSearchQuestionAll")
    public WebResult findSearchQuestionAll(HttpServletRequest request, @RequestBody PageVo pageVo) {
        pageVo.setStatus(1);  //前台只显示status=1状态的（因为到了截止时间的status都被设为了2，在前台不予显示）
        return questionSearchService.findSearchQuestionAll(pageVo);

    }
    /**
     * 后台 进入详情页（分页）
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/server/explicitQuestionAll")
    public WebResult findServerexplicitQuestionAll(HttpServletRequest request, @RequestBody PageVo pageVo) {
        pageVo.setStatus(1);  //前台只显示status=1状态的（因为到了截止时间的status都被设为了2，在前台不予显示）
        return questionSearchService.findServerSearchQuestionAll(pageVo);

    }
    /**
     * 问卷调查列表及模糊查询（分页）
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/server/findServerSearchQuestionAll")
    public WebResult findServerSearchQuestionAll(HttpServletRequest request, @RequestBody PageVo pageVo) {

        return questionSearchService.findSearchQuestionAll(pageVo);

    }

//    /**
//     * 添加问卷题目选项（SearchQuestion）
//     *
//     * @param
//     * @return
//     */
//    @PostMapping(value = "/server/createQuestionItem")
//    public WebResult createQuestionItem(@RequestBody QuestionItem questionItem) {
//
//        return questionSearchService.createQuestionItem(questionItem);
//    }
//    /**
//     * 添加问卷题目选项（SearchQuestion）
//     *
//     * @param
//     * @return
//     */
//    @PostMapping(value = "/server/createQuestion")
//    public WebResult createQuestion(@RequestBody Question question) {
//
//        return questionSearchService.createQuestion(question);
//    }
    /**
     * 按“热门”问卷调查列表及模糊查询（分页）
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/searchQuestion/findSearchQuestionAllbyVisits")
    public WebResult findSearchQuestionAllbyVisits(HttpServletRequest request, @RequestBody PageVo pageVo) {

        return questionSearchService.findSearchQuestionAllbyVisits(pageVo);

    }
    /**
     * 按“综合”问卷调查列表及模糊查询（分页）
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/searchQuestion/findSearchQuestioncomposite")
    public WebResult findSearchQuestioncomposite(HttpServletRequest request, @RequestBody PageVo pageVo) {

        return questionSearchService.findSearchQuestioncomposite(pageVo);

    }
    /**
     * 教师评价列表（分页）
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/searchQuestion/findCourseTopic")
    public WebResult findCourseTopic(HttpServletRequest request, @RequestBody PageVo pageVo) {

        return questionSearchService.findSearchQuestioncomposite(pageVo);

    }
    /**
     * app端教师评价列表（分页）
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/searchQuestion/findMyCourseList")
    public WebResult findMyCourseList(HttpServletRequest request, @RequestBody PageVo pageVo) {
        if (request.getHeader("userId")!=null&&request.getHeader("userId")!="") {
            Integer userId = Integer.parseInt(request.getHeader("userId"));
            pageVo.setUserId(userId);
        }
        return questionSearchService.findMyCourseList(pageVo);

    }
    /**
     * 进入某班级详情列表（分页）
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/searchQuestion/findMyClassCourseList")
    public WebResult findMyClassCourseList(HttpServletRequest request, @RequestBody PageVo pageVo) {

        return questionSearchService.findMyCourseList(pageVo);

    }





}
