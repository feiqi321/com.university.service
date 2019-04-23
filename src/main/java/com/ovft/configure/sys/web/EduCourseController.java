package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduCourse;
import com.ovft.configure.sys.service.EduCourseService;
import com.ovft.configure.sys.vo.EduCourseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * 按学校的id来查找专业类别
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
        List<EduCourse> eduCourses = eduCourseService.listCourseCategoryByShoolId(schoolId);
        return new WebResult(StatusCode.OK, "查找成功", eduCourses);
    }

    /**
     * 立即报名
     *
     * @param request
     * @return
     */
    @GetMapping(value = "order")
    public WebResult queryCourseInfoById(@RequestParam(value = "courseId", required = true) Integer courseId, HttpServletRequest request) {
//        String queryString = request.getQueryString();
//        Integer courseId = Integer.parseInt(queryString);
//        System.out.println(queryString);
//        Integer userId = (Integer) request.getAttribute("userId");
        String userId1 = request.getHeader("userId");
        Integer userId = Integer.parseInt(userId1);

        System.out.println(userId);
        if (userId == null) {
            return new WebResult(StatusCode.ERROR, "userId不能为空", "");
        }
        if (courseId == null) {
            return new WebResult(StatusCode.ERROR, "课程id不能为空", "");
        }
        //获取请求头的userId
        Map<String, EduCourseVo> map = eduCourseService.queryCourseByCourseId(userId, courseId);
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

