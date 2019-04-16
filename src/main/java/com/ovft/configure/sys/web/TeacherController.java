package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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
     * 教师请假申请审批列表
     * @param adminId
     * @return
     */
    @GetMapping(value = "/vacateChackList")
    public WebResult vacateChackList(@RequestParam(value = "adminId")Integer adminId) {
        return  teacherService.vacateChackList(adminId);
    }

    /**
     * 请假审批
     * @param map
     * @return
     */
    @PostMapping(value = "/vacateApprover")
    public WebResult vacateApprover(@RequestBody HashMap<String, Object> map) {
        return  teacherService.vacateApprover(map);
    }

    /**
     * 添加课程
     * @param map
     * @return
     */
    @PostMapping(value = "/createCourse")
    public WebResult createCourse(@RequestBody HashMap<String, Object> map) {
        return  teacherService.createCourse(map);
    }

}
