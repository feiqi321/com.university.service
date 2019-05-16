package com.ovft.configure.sys.service;

import com.ovft.configure.sys.bean.EduCourse;
import com.ovft.configure.sys.vo.EduCourseVo;
import com.ovft.configure.sys.vo.OrderVo;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author vvtxw
 * @create 2019-04-13 11:37
 */
public interface EduCourseService {
    /**
     * 按学校的id来查找专业类别
     *
     * @param eduCourse
     * @return
     */
    public List<EduCourse> listCourseCategoryByShoolId(EduCourse eduCourse);

    /**
     * 根据课程id查询课程信息报名
     *
     * @param courseId
     * @return
     */
    public Map<String, Object> queryCourseByCourseId(Integer user_id, Integer courseId, HttpServletRequest request);

    EduCourseVo queryCourseByCategory(Integer courseId);


    /**
     * 查询课程表相关信息
     *
     * @param week
     * @param schoolId
     * @return
     */
    List<EduCourseVo> queryAllTimetable(String week, String schoolId);

    /**
     * 设置所有的时间
     *
     * @param eduCourse
     * @return
     */
    int updateAllTime(EduCourse eduCourse);


}
