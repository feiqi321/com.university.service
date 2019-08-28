package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.EduClass;
import com.ovft.configure.sys.bean.EduCourse;
import com.ovft.configure.sys.bean.User;
import com.ovft.configure.sys.vo.EduCourseVo;
import com.ovft.configure.sys.vo.PageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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
    //修改课程是否添加
    public void updateisAddQuestionByCourseId(@Param("courseId") Integer courseId, @Param("status") Integer status);

    //根据学校id查找课程列表
    public List<EduCourse> selectCourseListBySchoolId(PageVo pageVo);

    //查找课程列表   多表查询，带学校名称和教师名称
    public List<EduCourseVo> selectCourseList(PageVo pageVo);

    //根据课程id查找课程
    public EduCourseVo selectByCourseId(@Param("courseId") Integer courseId);

    //根据课程id查找课程详细信息
    public List<EduClass> selectClassByCourseId(@Param("courseId") Integer courseId);

    //根据课程id删除课程详细信息
    public void deleteClassByCourseId(@Param("courseId") Integer courseId);

    //根据课程id删除课程
    public void deleteCourseById(@Param("courseId") Integer courseId);

    //根据id 删除请假申请
    public void deleteVacate(@Param("vacateId") Integer vacateId);

    public List<User> selectUserList(@Param("pageVo") PageVo pageVo);
     //查找游客所有记录
    public List<User> findVisitors();

    public List<User> selectWithdrawList(@Param("schoolId") Integer schoolId, @Param("search") String search);

    public void updateCheckIn(@Param("userId") Integer userId, @Param("checkin") Integer checkin);

    public void updateWithdrawCheckIn(@Param("wid") Integer wid, @Param("checkin") Integer checkin);

    public List<EduCourse> shelvesCourse(Date date);

    //学员一键审核通过
    public void bigAuditUser(@Param("userIds") int[] userIds);

    //<!--删除课程的的同时删除班级-->
    public void deleteClassByKeCheng(@Param("courseId") Integer courseId);
}
