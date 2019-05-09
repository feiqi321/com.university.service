package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.EduClass;
import com.ovft.configure.sys.bean.EduCourse;
import com.ovft.configure.sys.bean.User;
import com.ovft.configure.sys.vo.EduCourseVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 教师操作
 */
@Mapper
public interface TeacherMapper {

    public List<Map<String,Object>> seleceVacateList(@Param("schoolId") Integer schoolId);

    //添加课程
    public void insertCourse(EduCourse course);

    //修改课程信息
    public void updateCourseByCourseId(EduCourse course);

    //根据学校id查找课程列表
    public List<EduCourse> selectCourseListBySchoolId(@Param("schoolId") Integer schoolId, @Param("isenable") Integer isenable, @Param("search") String search);

    //根据课程id查找课程
    public EduCourseVo selectByCourseId(@Param("courseId") Integer courseId);

    //根据课程id查找课程详细信息
    public List<EduClass> selectClassByCourseId(@Param("courseId") Integer courseId);

    //根据课程id删除课程详细信息
    public void deleteClassByCourseId(@Param("courseId") Integer courseId);

    //根据课程id删除课程
    public void deleteCourseById(@Param("courseId") Integer courseId);

    public List<User> selectUserList(@Param("schoolId") Integer schoolId, @Param("search") String search);

    public List<User> selectWithdrawList(@Param("schoolId") Integer schoolId, @Param("search") String search);

    public void updateCheckIn(@Param("userId") Integer userId,@Param("checkin") Integer checkin);

    public void updateWithdrawCheckIn(@Param("wid") Integer wid,@Param("checkin") Integer checkin);

}
