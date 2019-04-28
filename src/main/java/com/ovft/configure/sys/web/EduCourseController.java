package com.ovft.configure.sys.web;

import com.jfinal.aop.Before;
import com.ovft.configure.config.CORSInterceptor;
import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduCourse;
import com.ovft.configure.sys.service.EduCourseService;
import com.ovft.configure.sys.vo.EduCourseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author vvtxw
 * @create 2019-04-14 7:43
 */

@RestController
@RequestMapping("apply")
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 按学校的id来查找专业类别信息
     *
     * @return
     */
    @GetMapping(value = "showCategory")
    public WebResult queryAllCourse(HttpServletRequest request) {
        String schoolId1 = request.getHeader("schoolId");
        Integer schoolId = Integer.parseInt(schoolId1);

        if (schoolId == null) {
            return new WebResult(StatusCode.ERROR, "学校id不能为空", "");
        }
        List<EduCourse> eduCourseId = eduCourseService.listCourseCategoryByShoolId(schoolId);
        EduCourseVo eduCourseVo = (EduCourseVo) redisTemplate.opsForValue().get("schoolId" + schoolId);
        ArrayList<Object> courseVos = new ArrayList<>();
        if (eduCourseVo == null) {
            for (EduCourse eduCourse : eduCourseId) {
                eduCourseVo = eduCourseService.queryCourseByCategory(eduCourse.getCourseId());
                courseVos.add(eduCourseVo);
            }
            redisTemplate.opsForValue().set("course" + schoolId, eduCourseVo);
        }
        return new WebResult(StatusCode.OK, "查找成功", courseVos);
    }

    @GetMapping(value = "showInfo")
    public WebResult queryAllInfo(@RequestParam(value = "courseId") Integer courseId, HttpServletRequest request) {
        if (courseId == null) {
            return new WebResult(StatusCode.ERROR, "课程id不能为空", "");
        }
        EduCourseVo eduCourseVos = (EduCourseVo) redisTemplate.opsForValue().get("course" + courseId);
        if (eduCourseVos == null) {
            eduCourseVos = eduCourseService.queryCourseByCategory(courseId);
            redisTemplate.opsForValue().set("course" + courseId, eduCourseVos);
        }
        return new WebResult(StatusCode.OK, "查询成功", eduCourseVos);
    }

    /**
     * 立即报名
     *
     * @param request
     * @return
     */
    @GetMapping(value = "order")
    public WebResult queryCourseInfoById(@RequestParam(value = "courseId") Integer courseId, HttpServletRequest request) {
//        String queryString = request.getQueryString();
//        Integer courseId = Integer.parseInt(queryString);
//        System.out.println(queryString);
//        Integer userId = (Integer) request.getAttribute("userId");
        String userId1 = request.getHeader("userId");
        Integer userId = Integer.parseInt(userId1);
//        Integer userId = 1;

        if (userId == null) {
            return new WebResult(StatusCode.ERROR, "userId不能为空", "");
        }
        if (courseId == null) {
            return new WebResult(StatusCode.ERROR, "课程id不能为空", "");
        }
        //获取请求头的userId
        Map<String, EduCourseVo> map = eduCourseService.queryCourseByCourseId(userId, courseId, request);
        Iterator<String> it = map.keySet().iterator();
        if (it != null) {
            while (it.hasNext()) {
                String message = it.next();
                EduCourseVo eduCourseVo = map.get(message);
                return new WebResult(StatusCode.OK, message, eduCourseVo);
            }
        }
        return new WebResult(StatusCode.ERROR, "查询没有数据", null);
    }


}

