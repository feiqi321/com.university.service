package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.School;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SchoolMapper {

    //添加学校
    public void createSchool(@Param("school")School school);
}
