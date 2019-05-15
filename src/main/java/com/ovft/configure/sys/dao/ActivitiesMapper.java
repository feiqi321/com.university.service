package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.Activities;
import com.ovft.configure.sys.vo.PageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName ActivitiesMapper
 * @Author zqx
 * @Version 1.0
 **/
@Mapper
public interface ActivitiesMapper {

    public Activities selectById(@Param("activitiesId") Integer activitiesId);

    public List<Activities> selectActivitiesList(PageVo pageVo);

    public void createActivities(Activities activities);

    public void updateActivities(Activities activities);

    public void deleteActivities(@Param("activitiesId") Integer activitiesId);
}
