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
     * @param schoolId
     * @return
     */
    public List<EduCourse> listCourseCategoryByShoolId(int schoolId);

    /**
     * 根据课程id查询课程信息报名
     *
     * @param courseId
     * @return
     */
    public Map<String, EduCourseVo> queryCourseByCourseId(Integer user_id, Integer courseId);

}
