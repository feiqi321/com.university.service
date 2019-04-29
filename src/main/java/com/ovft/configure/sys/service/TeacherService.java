package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.vo.EduCourseVo;
import com.ovft.configure.sys.vo.PageVo;
import org.springframework.stereotype.Service;

@Service
public interface TeacherService {

    public WebResult vacateChackList(PageVo pageVo);

    public WebResult intoCourse(Integer schoolId);

    public WebResult createCourse(EduCourseVo courseVo);

    public WebResult vacateApprover(Integer vacateId, Integer isCheck);

    public WebResult courseList(PageVo pageVo);

    public WebResult findCourse(Integer courseId);

    public WebResult updateCourse(EduCourseVo courseVo);

    public WebResult deleteCourse(Integer courseId);

    public WebResult userList(PageVo pageVo);
}
