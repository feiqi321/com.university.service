package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.MyActivities;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName MyActivitiesMapper
 * @Author zqx
 * @Version 1.0
 **/
@Mapper
public interface MyActivitiesMapper {

    public MyActivities selectById(@Param("id") Integer id);

    public List<MyActivities> selectByUserOrActivities(@Param("userId") Integer userId, @Param("adminId") Integer adminId, @Param("activitiesId") Integer activitiesId);

    public void createMyActivities(MyActivities activities);

    public void deleteMyActivities(@Param("id") Integer id);
}
