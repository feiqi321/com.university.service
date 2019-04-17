package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.EduCourse;
import com.ovft.configure.sys.vo.EduCourseVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 课程数据层
 *
 * @author vvtxw
 * @create 2019-04-13 11:34
 */
@Mapper
public interface EduCourseMapper {
    /**
     * 按学校的id来查找专业类别
     *
     * @param schoolId
     * @return
     */
    public List<EduCourse> listCourseCategoryByShoolId(int schoolId);

    /**
     * 根据课程id查询课程信息报名--课程地点老师等
     *
     * @param courseId
     * @return
     */
    public EduCourseVo queryCourseByCourseId(int courseId);


    /**
     * 根据当前的课程Id获取上课的具体时间
     *
     * @param couseId
     * @return
     */
    Date queryStartTimeByCouserId(int couseId);

    public void insert(EduCourse course);

    public List<EduCourse> selectByTeacherId(@Param("adminId") Integer adminId);
}
