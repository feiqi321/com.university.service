package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.Activities;
import com.ovft.configure.sys.vo.PageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    //查找报名人数
    public Integer registCount(@Param("activitiesId") Integer activitiesId);

    //活动报名列表
    public List<Map<String, Object>> registList(@Param("activitiesId") Integer activitiesId);
}
