package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.School;
import com.ovft.configure.sys.bean.SchoolClass;
import com.ovft.configure.sys.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SchoolController
 * @Author zqx
 * @Date 2019/4/10 15:45
 * @Version 1.0
 **/

@RestController
@RequestMapping("/server/school")
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
          * @param schoolClass
          * @return
          */
    @PostMapping(value = "/updateSchoolName")
    public WebResult updateSchoolName(@RequestBody School school) {
        return schoolService.updateSchoolName(school);
    }

}
