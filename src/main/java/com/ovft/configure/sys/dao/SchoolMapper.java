package com.ovft.configure.sys.dao;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.School;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Vector;

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

    //更换手机查询所有
    public List<School> selectSchoolAll();


}
