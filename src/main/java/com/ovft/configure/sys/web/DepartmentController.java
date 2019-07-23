package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Department;
import com.ovft.configure.sys.service.DepartmentService;
import com.ovft.configure.sys.vo.DepartmentVo;
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
    /**
     * 院系列表
     *
     * @param departmentVo
     * @return
     */
    @PostMapping(value="/departmentList")
    public WebResult departmentList(@RequestBody DepartmentVo departmentVo) {
        return departmentService.departmentList(departmentVo);
    }
    /**
     * 后台院系列表
     *
     * @param departmentVo
     * @return
     */
    @PostMapping(value="/server/departmentserverList")
    public WebResult departmentserverList(@RequestBody DepartmentVo departmentVo) {
        return departmentService.departmentList(departmentVo);
    }

    /**
     * 删除一个院系
     *
     * @param departmentVo
     * @return
     */
    @PostMapping(value="/server/deleteDepartment")
    public WebResult deleteDepartment(@RequestBody DepartmentVo departmentVo) {
        return departmentService.departmentList(departmentVo);
    }

}
