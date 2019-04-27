package com.ovft.configure.sys.web;

import com.jfinal.aop.Before;
import com.ovft.configure.config.CORSInterceptor;
import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.CourseManager;
import com.ovft.configure.sys.service.CourseManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author vvtxw
 * @create 2019-04-14 18:05
 */
@Before(CORSInterceptor.class)
@RestController
@RequestMapping("notice")
public class CourseManagerController {
    @Autowired
    private CourseManagerService courseManagerService;

    /**
     * 课程管理查询
     * @return
     */
    @GetMapping
    public WebResult queryAllCourseNotice(){
        List<CourseManager> courseManagers = courseManagerService.queryAllCourseNotice();
        return  new WebResult(StatusCode.OK,"查询成功",courseManagers);
    }

}
