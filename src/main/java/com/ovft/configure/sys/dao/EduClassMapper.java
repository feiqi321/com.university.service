package com.ovft.configure.sys.dao;

import java.util.List;

import com.ovft.configure.sys.bean.EduClass;
import com.ovft.configure.sys.bean.EduClassExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EduClassMapper {
    long countByExample(EduClassExample example);

    int deleteByExample(EduClassExample example);

    int deleteByPrimaryKey(Integer classScheduleId);

    int insert(EduClass record);

    int insertSelective(EduClass record);

    List<EduClass> selectByExample(EduClassExample example);

    EduClass selectByPrimaryKey(Integer classScheduleId);

    int updateByExampleSelective(@Param("record") EduClass record, @Param("example") EduClassExample example);

    int updateByExample(@Param("record") EduClass record, @Param("example") EduClassExample example);

    int updateByPrimaryKeySelective(EduClass record);

    int updateByPrimaryKey(EduClass record);

    /**
     * 根据课程id查询课程信息报名--报名时间
     *
     * @param courseId
     * @return
     */
    public List<EduClass> queryCourseTimeByCourseId(int courseId);
}