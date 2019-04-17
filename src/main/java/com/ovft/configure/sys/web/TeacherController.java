package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.service.TeacherService;
import com.ovft.configure.sys.vo.EduCourseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName TeacherController
 * @Author zqx
 * @Date 2019/4/10 15:45
 * @Version 1.0
 **/

@RestController
@RequestMapping("/server/teacher")
public class TeacherController {

    @Autowired
    public TeacherService teacherService;

    /**
     * 请假申请教师审批列表
     * @param adminId
     * @return
     */
    @GetMapping(value = "/vacateChackList")
    public WebResult vacateChackList(@RequestParam(value = "adminId")Integer adminId) {
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
     * @param adminId
     * @return
     */
    @GetMapping(value = "/courseList")
    public WebResult courseList(@RequestParam(value = "adminId")Integer adminId) {
        return  teacherService.courseList(adminId);
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

}
