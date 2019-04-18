package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.EduCourse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 教师操作
 */
@Mapper
public interface TeacherMapper {

    public List<Map<String,Object>> seleceVacateByTeacherId(@Param("adminId") Integer adminId);

    public void insertCourse(EduCourse course);

    public List<EduCourse> selectByTeacherId(@Param("adminId") Integer adminId);
}