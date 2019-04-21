package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduCourse;
import com.ovft.configure.sys.service.EduCourseService;
import com.ovft.configure.sys.vo.EduCourseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        Integer schoolId = (Integer) request.getAttribute("schoolId");
        if (schoolId == null) {
            return new WebResult(StatusCode.ERROR, "学校id不能为空", "");
        }
        List<EduCourse> eduCourses = eduCourseService.listCourseCategoryByShoolId(schoolId);
        return new WebResult(StatusCode.OK, "查找成功", eduCourses);
    }

    /**
     * 立即报名
     *
     * @param courseId
     * @return
     */
    @GetMapping(value = "order/{courseId}")
    public WebResult queryCourseInfoById(@PathVariable("courseId") Integer courseId, HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        userId = 2;
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

