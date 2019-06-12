package com.ovft.configure.sys.web;

import com.ovft.configure.constant.ConstantClassField;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.*;
import com.ovft.configure.sys.service.QuestionSearchService;
import com.ovft.configure.sys.utils.RedisUtil;
import com.ovft.configure.sys.vo.PageVo;
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
            return questionSearchService.deleteSearchQuestion(searchQuestion.getSid());
        } else {
            return new WebResult("50012", "请先登录", "");
        }
    }
    /**
     * 添加用户调查结果记录
     *
     * @param
     * @return
     */
    @PostMapping(value = "/user/createAnswerRecord")
    public WebResult createAnswerRecord(@RequestBody AnswerRecord answerRecord,HttpServletRequest request) {
             Integer userId=Integer.parseInt(request.getHeader("userId"));
             answerRecord.setUid(userId);
             return questionSearchService.createAnswerRecord(answerRecord);

    }

    /**
     * 问卷调查列表及模糊查询（分页）
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/searchQuestion/findSearchQuestionAll")
    public WebResult findSearchQuestionAll(HttpServletRequest request, @RequestBody PageVo pageVo) {

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
}
