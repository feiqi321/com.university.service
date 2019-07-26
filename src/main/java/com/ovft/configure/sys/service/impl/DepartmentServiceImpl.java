package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ovft.configure.constant.OrderStatus;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Department;
import com.ovft.configure.sys.dao.DepartmentMapper;
import com.ovft.configure.sys.dao.EduCourseMapper;
import com.ovft.configure.sys.dao.TeacherMapper;
import com.ovft.configure.sys.service.DepartmentService;
import com.ovft.configure.sys.vo.DepartmentVo;
import com.ovft.configure.sys.vo.EduCourseVo;
import com.ovft.configure.sys.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;
    @Resource
    private TeacherMapper   teacherMapper;
    @Resource
    public EduCourseMapper eduCourseMapper;
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
    //删除院系
    @Transactional
    @Override
    public WebResult deleteDepartment(DepartmentVo departmentVo) {
         departmentMapper.deleteDepartment(departmentVo.getDid());
        return new WebResult("200", "删除成功", "");
    }
    //批量删除院系
    @Transactional
    @Override
    public WebResult bigDeleteDepartment(Department department) {
         departmentMapper.bigDeleteDepartment(department.getDids());
        return new WebResult("200", "删除成功", "");
    }
    //添加/修改院系
    @Transactional
    @Override
    public WebResult createDepartment(DepartmentVo departmentVo) {
        if (StringUtils.isBlank(departmentVo.getDepartmentName())){
            return new WebResult("200","院系名称不能为空","");

        }
        if (departmentVo.getSchoolId()==null){
            return new WebResult("200","所属学校为空","");
        }
        if (departmentVo.getDid()==null){
            departmentMapper.createDepartment(departmentVo);
            return new WebResult("200","添加成功","");
        }else {
            departmentMapper.updateDepartment(departmentVo);
            return  new WebResult("200","修改成功","");
        }
    }


}
