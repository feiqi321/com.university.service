package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.School;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SchoolMapper {

    //添加学校
    public void createSchool(School school);

    //修改学校名称
    public void updateSchoolName(School school);
}
