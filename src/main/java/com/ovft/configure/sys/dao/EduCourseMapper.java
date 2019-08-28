package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.EduCourse;
import com.ovft.configure.sys.bean.EduCourseExample;
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

    long countByExample(EduCourseExample example);

    int deleteByExample(EduCourseExample example);

    int deleteByPrimaryKey(Integer courseId);

    int insert(EduCourse record);

    int insertSelective(EduCourse record);

    List<EduCourse> selectByExample(EduCourseExample example);

    EduCourse selectByPrimaryKey(Integer courseId);

    int updateByExampleSelective(@Param("record") EduCourse record, @Param("example") EduCourseExample example);

    int updateByExample(@Param("record") EduCourse record, @Param("example") EduCourseExample example);

    int updateByPrimaryKeySelective(EduCourse record);

    int updateByPrimaryKey(EduCourse record);

    /**
     * 按学校的id来查找专业类别
     *
     * @param eduCourse
     * @return
     */
    public List<EduCourse> listCourseCategoryByShoolId(EduCourse eduCourse);

    /**
     * 根据课程id查询课程信息报名--课程地点老师等
     *
     * @param courseId
     * @return
     */
    public EduCourseVo queryCourseByCourseId(int courseId);

    /**
     * 查询学校报名的总人数
     *
     * @param courseId
     * @return
     */
    Integer queryAcceptNum(Integer courseId);

    /**
     * 查询课程表相关信息
     *
     * @param week
     * @param schoolId
     * @return
     */
    List<EduCourseVo> queryAllTimetable(@Param("week") String week, @Param("schoolId") String schoolId);

    /**
     * 根据学校id查询课程id
     *
     * @param schoolId
     * @return
     */
    List<Integer> selectCourseIdBySchoolId(Integer schoolId);

    //查询课程相关信息
    EduCourseVo queryCourseInfo(Integer courseId);

    // <!--根据院系id修改 by wd-->
    public List<EduCourse> updateByCourseId(EduCourse eduCourse);

    // <!--根据院系id删除 by wd-->
    public void deleteByDid(@Param("did") Integer did);

}
