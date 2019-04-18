package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.School;
import com.ovft.configure.sys.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SchoolController
 * @Author zqx
 * @Date 2019/4/10 15:45
 * @Version 1.0
 **/

@RestController
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    /**
     * 添加学校
     * @param school
     * @return
     */
    @PostMapping(value = "/create")
    public WebResult createAdmin(@RequestBody School school)  {
        return  schoolService.createSchool(school);
    }

    /**
          * 修改学校名称
          * @param school
          * @return
          */
    @PostMapping(value = "/updateSchoolName")
    public WebResult updateSchoolName(@RequestBody School school) {
        return schoolService.updateSchoolName(school);
    }
    /**
          * 切换学校
          * @param
          * @return
          */
    @GetMapping(value = "/switchSchool")
    public WebResult switchSchool() {
        return schoolService.switchSchool();
    }
    /**
          * 切换学校ID
          * @param
          * @return
          */
    @GetMapping(value = "/switchSchoolID")
    public WebResult switchSchoolID(@RequestParam(value = "SchoolId")Integer SchoolId,@RequestParam(value = "userId")Integer userId) {
        return schoolService.switchSchoolID(SchoolId,userId);
    }


}
