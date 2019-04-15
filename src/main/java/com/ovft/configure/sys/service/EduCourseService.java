package com.ovft.configure.sys.service;

import com.ovft.configure.sys.bean.EduCourse;
import com.ovft.configure.sys.vo.EduCourseVo;

import java.util.Date;
import java.util.List;

/**
 * @author vvtxw
 * @create 2019-04-13 11:37
 */
public interface EduCourseService {
    /**
     * 按学校的id来查找专业类别
     * @param schoolId
     * @return
     */
    public List<EduCourse> listCourseCategoryByShoolId(int schoolId);

    /**
     * 根据课程id查询课程信息报名
     * @param courseId
     * @return
     */
    public EduCourseVo queryCourseByCourseId(int courseId);

    /**
     * 根据当前的课程Id获取上课的具体时间
     * @param couseId
     * @return
     */
    Date queryStartTimeByCouserId(int couseId);

}
