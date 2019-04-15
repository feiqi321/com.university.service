package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.CourseManager;
import com.ovft.configure.sys.bean.CourseManagerExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface CourseManagerMapper {
    long countByExample(CourseManagerExample example);

    int deleteByExample(CourseManagerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CourseManager record);

    int insertSelective(CourseManager record);

    List<CourseManager> selectByExample(CourseManagerExample example);

    CourseManager selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CourseManager record, @Param("example") CourseManagerExample example);

    int updateByExample(@Param("record") CourseManager record, @Param("example") CourseManagerExample example);

    int updateByPrimaryKeySelective(CourseManager record);

    int updateByPrimaryKey(CourseManager record);
}