package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.School;
import com.ovft.configure.sys.service.SchoolService;
import com.ovft.configure.sys.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName SchoolController
 * @Author zqx
 * @Date 2019/4/10 15:45
 * @Version 1.0
 **/

@RestController
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    /**
     * 添加学校
     * @param school
     * @return
     */
    @PostMapping(value = "/server/school/create")
    public WebResult createAdmin(HttpServletRequest request, @RequestBody School school)  {
        Integer adminId = (Integer) request.getAttribute("adminId");
        school.setAdminId(adminId);
        return  schoolService.createSchool(school);
    }

    /**
     * 进入修改学校页面
     * @param schoolId
     * @return
     */
    @GetMapping(value = "/server/school/findSchool")
    public WebResult findSchool(@RequestParam(value = "schoolId")Integer schoolId) {
        return schoolService.findSchool(schoolId);
    }

    /**
          * 修改学校
          * @param school
          * @return
          */
    @PostMapping(value = "/server/school/updateSchool")
    public WebResult updateSchool(@RequestBody School school) {
        return schoolService.updateSchool(school);
    }
    /**
          * 切换学校
          * @param
          * @return
          */
    @GetMapping(value = "/school/switchSchool")
    public WebResult switchSchool() {
        return schoolService.switchSchool();
    }
    /**
          * 切换学校ID
          * @param
          * @return
          */
    @GetMapping(value = "/school/switchSchoolID")
    public WebResult switchSchoolID(@RequestParam(value = "SchoolId")Integer SchoolId,@RequestParam(value = "userId")Integer userId) {
        return schoolService.switchSchoolID(SchoolId,userId);
    }

    /**
     * 学校列表
     * @param request
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/server/school/schoolList")
    public WebResult schoolList(HttpServletRequest request, @RequestBody PageVo pageVo)  {
        Integer adminId = (Integer) request.getAttribute("adminId");
        return schoolService.schoolList(adminId, pageVo);
    }

    /**
     * 删除学校
     * @param schoolId
     * @return
     */
    @GetMapping(value = "/server/school/deleteSchool")
    public WebResult deleteSchool(@RequestParam(value = "schoolId")Integer schoolId) {
        return schoolService.deleteSchool(schoolId);
    }


}
