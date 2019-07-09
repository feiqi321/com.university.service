package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.EduVolunteer;
import com.ovft.configure.sys.bean.MyVolunteer;
import com.ovft.configure.sys.vo.PageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zqx
 * @since 2019-07-04
 */
@Mapper
public interface EduVolunteerMapper  {

    public EduVolunteer selectById(@Param("volunteerId") Integer volunteerId);

    public List<EduVolunteer> selectList(PageVo pageVo);

    public void createVolunteer(EduVolunteer eduVolunteer);

    public void updateVisits(EduVolunteer eduVolunteer);

    public void deleteVolunteer(@Param("volunteerId") Integer volunteerId);

    //查找已经报名志愿活动
    public MyVolunteer selectMyVolunteer(@Param("userId") Integer userId, @Param("volunteerId") Integer volunteerId);

    //志愿活动报名
    public void createMyVolunteer(MyVolunteer myVolunteer);

    //我报名的志愿活动列表
    public List<EduVolunteer> myRegistVolunteer(@Param("userId") Integer userId);
}