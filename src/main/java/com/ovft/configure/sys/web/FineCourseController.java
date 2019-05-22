package com.ovft.configure.sys.web;

import com.ovft.configure.constant.ConstantClassField;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Admin;
import com.ovft.configure.sys.bean.FineCourse;
import com.ovft.configure.sys.service.FineCourseService;
import com.ovft.configure.sys.utils.RedisUtil;
import com.ovft.configure.sys.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName FineCourseController  精品课程
 * @Author zqx
 * @Version 1.0
 **/

@RestController
public class FineCourseController {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private FineCourseService fineCourseService;

    /**
     * 精品课程列表
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/server/fineCourse/fineCourseList")
    public WebResult fineCourseList(HttpServletRequest request, @RequestBody PageVo pageVo) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if(o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
            Admin hget =(Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            if(hget.getRole() != 0) {
                pageVo.setSchoolId(hget.getSchoolId());
            }
            return fineCourseService.fineCourseList(pageVo);
        }else {
            return new WebResult("50012", "请重新登录", "");
        }
    }

    /**
     * 添加/修改 精品课程
     *
     * @param fineCourse
     * @return
     */
    @PostMapping(value = "/server/fineCourse/createFineCourse")
    public WebResult createFineCourse(HttpServletRequest request, @RequestBody FineCourse fineCourse) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if(o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
            Admin hget =(Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            if(hget.getRole() != 0) {
                fineCourse.setSchoolId(hget.getSchoolId());
            }
            return fineCourseService.createFineCourse(fineCourse);
        }else {
            return new WebResult("50012", "请重新登录", "");
        }
    }

    /**
     * 进入 精品课程 修改页面
     * @param fineId
     * @return
     */
    @GetMapping(value = "/server/fineCourse/findFineCourse")
    public WebResult findFineCourse(HttpServletRequest request, Integer fineId) {
        return fineCourseService.findFineCourse(fineId);
    }

    /**
     * 删除 精品课程
     *
     * @param fineId
     * @return
     */
    @GetMapping(value = "/server/fineCourse/deleteFineCourse")
    public WebResult deleteFineCourse(@RequestParam(value = "fineId") Integer fineId) {
        return fineCourseService.deleteFineCourse(fineId);
    }

    /**
     * app精品课程列表
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/fineCourse/fineCourseList")
    public WebResult courseList(HttpServletRequest request, @RequestBody PageVo pageVo) {
        String token = request.getHeader("token");
        String schoolId = request.getHeader("schoolId");
        Object o = redisUtil.get(token);
        if(o != null) {
            pageVo.setSchoolId(Integer.valueOf(schoolId));
            return fineCourseService.fineCourseList(pageVo);
        }else {
            return new WebResult("50012", "请先登录", "");
        }
    }

    /**
     * app进入 精品课程 页面
     * @param fineId
     * @return
     */
    @GetMapping(value = "/fineCourse/findFineCourse")
    public WebResult findCourse(HttpServletRequest request, Integer fineId) {
        return fineCourseService.findCourse(fineId);
    }

}
