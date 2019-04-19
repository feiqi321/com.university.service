package com.ovft.configure.sys.dao;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.School;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SchoolMapper {

    //添加学校
    public void createSchool(School school);

    //修改学校名称
    public void updateSchoolName(School school);

    /**
     * 根据学校id查询坐标
     * @param schoolId
     * @return
     */
    public School queryRecordBySchoolId(Integer schoolId);

    //更换学校查询所有
    public List<School> selectSchoolAll();
     //更换学校ID
    public WebResult switchSchoolID(Integer SchoolId,Integer userId);

    public List<School> selectSchoolByAdminId(@Param("adminId") Integer adminId, @Param("search") String search);
}
