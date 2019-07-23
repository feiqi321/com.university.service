package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.Department;
import com.ovft.configure.sys.vo.DepartmentVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface DepartmentMapper {
    //院系列表
    public List<Department> departmentList(DepartmentVo departmentVo);
    //删除一个院系
    public void deleteDepartment(DepartmentVo departmentVo);
}
