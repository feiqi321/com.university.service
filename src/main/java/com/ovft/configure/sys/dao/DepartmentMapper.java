package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.Department;
import com.ovft.configure.sys.vo.DepartmentVo;
import com.ovft.configure.sys.vo.EduCourseVo;
import com.ovft.configure.sys.vo.PageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface DepartmentMapper {
    //院系列表
    public List<Department> departmentList(DepartmentVo departmentVo);
    //删除一个院系
    public void deleteDepartment(@Param("did") Integer did);
    //批量删除
    public void bigDeleteDepartment(@Param("dids") Integer [] dids);
    //添加
    public void createDepartment(DepartmentVo departmentVo);
    //修改
    public void updateDepartment(DepartmentVo departmentVo);
    //查找院系里面对应课程
    public List<EduCourseVo> findCourseBydid(PageVo PageVo);
}
