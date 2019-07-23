package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.vo.DepartmentVo;

public interface DepartmentService {
    public WebResult departmentList(DepartmentVo departmentVo);
    public WebResult deleteDepartment(DepartmentVo departmentVo);
}
