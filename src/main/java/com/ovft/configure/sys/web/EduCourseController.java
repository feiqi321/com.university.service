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

import java.util.List;

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
     * @param schoolId
     * @return
     */
    @GetMapping(value = "showCategory/{schoolId}")
    public WebResult queryAllCourse(@PathVariable("schoolId") int schoolId) {
        List<EduCourse> eduCourses = eduCourseService.listCourseCategoryByShoolId(schoolId);
        return new WebResult(StatusCode.OK, "查找成功", eduCourses);
    }

    /**
     * 根据课程id查询课程信息报名
     *
     * @param courseId
     * @return
     */
    @GetMapping(value = "order/{courseId}")
    public WebResult queryCourseInfoById(@PathVariable("courseId") int courseId) {
        //1.报名之前判断是否登陆
        // TODO
        EduCourseVo eduCourseVo = eduCourseService.queryCourseByCourseId(courseId);
        return new WebResult(StatusCode.OK, "查找成功", eduCourseVo);
    }


}

