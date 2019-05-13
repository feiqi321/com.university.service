package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.MyActivities;
import com.ovft.configure.sys.vo.PageVo;
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

    public List<MyActivities> selectActivitiesList(PageVo pageVo);

    public void createMyActivities(MyActivities activities);

    public void updateMyActivities(MyActivities activities);

    public void deleteMyActivities(@Param("id") Integer id);
}
