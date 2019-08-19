package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Department;
import com.ovft.configure.sys.service.DepartmentService;
import com.ovft.configure.sys.service.TeacherService;
import com.ovft.configure.sys.vo.DepartmentVo;
import com.ovft.configure.sys.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学校院系controller
 * Author xzy
 */
@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private TeacherService teacherService;


    /**
     * 进入某院系详情列表
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/findCourseList")
    public WebResult findCourseList(@RequestBody PageVo pageVo) {
        return teacherService.courseList(pageVo);
    }

    /**
     * 进入某院系详情列表
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/server/findCourseList")
    public WebResult findServerCourseBydid(@RequestBody PageVo pageVo) {
        return teacherService.courseList(pageVo);
    }

    /**
     * 院系列表
     *
     * @param departmentVo
     * @return
     */
    @PostMapping(value = "/departmentList")
    public WebResult departmentList(@RequestBody DepartmentVo departmentVo) {
        return departmentService.departmentList(departmentVo);
    }

    /**
     * 后台院系列表
     *
     * @param departmentVo
     * @return
     */
    @PostMapping(value = "/server/departmentserverList")
    public WebResult departmentserverList(@RequestBody DepartmentVo departmentVo) {
        return departmentService.departmentList(departmentVo);
    }

    /**
     * 删除一个院系
     *
     * @param departmentVo
     * @return
     */
    @PostMapping(value = "/server/deleteDepartment")
    public WebResult deleteDepartment(@RequestBody DepartmentVo departmentVo) {
        return departmentService.deleteDepartment(departmentVo);
    }

    /**
     * 批量删除院系
     *
     * @param department
     * @return
     */
    @PostMapping(value = "/server/bigDeleteDepartment")
    public WebResult bigDeleteDepartment(@RequestBody Department department) {
        return departmentService.bigDeleteDepartment(department);
    }

    /**
     * 添加/修改院系
     *
     * @param departmentVo
     * @return
     */
    @PostMapping(value = "/server/createDepartment")
    public WebResult createDepartment(@RequestBody DepartmentVo departmentVo) {
        return departmentService.createDepartment(departmentVo);
    }

}
