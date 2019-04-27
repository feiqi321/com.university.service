package com.ovft.configure.sys.web;

import com.jfinal.aop.Before;
import com.ovft.configure.config.CORSInterceptor;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.service.TeacherService;
import com.ovft.configure.sys.vo.EduCourseVo;
import com.ovft.configure.sys.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName TeacherController
 * @Author zqx
 * @Date 2019/4/10 15:45
 * @Version 1.0
 **/
@Before(CORSInterceptor.class)
@RestController
@RequestMapping("/server/teacher")
public class TeacherController {

    @Autowired
    public TeacherService teacherService;

    /**
     * 请假申请教师审批列表
     * @return
     */
    @GetMapping(value = "/vacateChackList")
    public WebResult vacateChackList(HttpServletRequest request) {
        Integer adminId = (Integer) request.getAttribute("adminId");
        return  teacherService.vacateChackList(adminId);
    }

    /**
     * 请假审批
     * @param isCheck
     * @param vacateId
     * @return
     */
    @PostMapping(value = "/vacateApprover")
    public WebResult vacateApprover(@RequestParam(value = "isCheck")Integer isCheck, @RequestParam(value = "vacateId")Integer vacateId) {
        return  teacherService.vacateApprover(vacateId, isCheck);
    }

    /**
     * 课程列表
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/courseList")
    public WebResult courseList(@RequestBody PageVo pageVo) {
        return  teacherService.courseList(pageVo);
    }

    /**
     * 进入添加课程页面
     * @param schoolId
     * @return
     */
    @GetMapping(value = "/intoCourse")
    public WebResult intoCourse(@RequestParam(value = "schoolId")Integer schoolId) {
        return teacherService.intoCourse(schoolId);
    }

    /**
     * 添加课程
     * @param courseVo
     * @return
     */
    @PostMapping(value = "/createCourse")
    public WebResult createCourse(@RequestBody EduCourseVo courseVo) {
        return  teacherService.createCourse(courseVo);
    }

    /**
     * 进入修改课程页面
     * @param courseId
     * @return
     */
    @GetMapping(value = "/findCourse")
    public WebResult findCourse(@RequestParam(value = "courseId")Integer courseId) {
        return teacherService.findCourse(courseId);
    }

    /**
     * 修改课程
     * @param courseVo
     * @return
     */
    @PostMapping(value = "/updateCourse")
    public WebResult updateCourse(@RequestBody EduCourseVo courseVo) {
        return teacherService.updateCourse(courseVo);
    }

    /**
     * 删除课程
     * @param courseId
     * @return
     */
    @GetMapping(value = "/deleteCourse")
    public WebResult deleteCourse(@RequestParam(value = "courseId")Integer courseId) {
        return teacherService.deleteCourse(courseId);
    }

}
