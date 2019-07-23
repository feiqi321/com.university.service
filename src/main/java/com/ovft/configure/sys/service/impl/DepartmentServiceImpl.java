package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Department;
import com.ovft.configure.sys.dao.DepartmentMapper;
import com.ovft.configure.sys.service.DepartmentService;
import com.ovft.configure.sys.vo.DepartmentVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;

    /**
     * 院系列表
     *
     * @param departmentVo
     * @return
     */
    @Override
    public WebResult departmentList(DepartmentVo departmentVo) {
        if (departmentVo.getPageSize() == 0) {
            List<Department> departments = departmentMapper.departmentList(departmentVo);
            return new WebResult("200", "查询成功", departments);
        }
        PageHelper.startPage(departmentVo.getPageNum(), departmentVo.getPageSize());
        List<Department> departments = departmentMapper.departmentList(departmentVo);
        PageInfo pageInfo = new PageInfo<>(departments);
        return new WebResult("200", "查询成功", pageInfo);
    }
    @Transactional
    @Override
    public WebResult deleteDepartment(DepartmentVo departmentVo) {
         departmentMapper.deleteDepartment(departmentVo);
        return new WebResult("200", "删除成功", "");
    }
}
