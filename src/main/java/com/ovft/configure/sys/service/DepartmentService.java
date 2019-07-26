package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Department;
import com.ovft.configure.sys.vo.DepartmentVo;
import org.springframework.web.bind.annotation.RequestBody;

public interface DepartmentService {
    public WebResult departmentList(DepartmentVo departmentVo);
    public WebResult deleteDepartment(DepartmentVo departmentVo);
    public WebResult bigDeleteDepartment(Department department);
    public WebResult createDepartment(DepartmentVo departmentVo);


}
