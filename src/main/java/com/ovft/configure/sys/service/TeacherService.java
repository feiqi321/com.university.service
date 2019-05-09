package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduCourse;
import com.ovft.configure.sys.bean.User;
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

    public WebResult deleteCourse(Integer courseId);

    public WebResult userList(PageVo pageVo);

    public WebResult savaUserInfo(User user);
    //学员报校审核状态修改
    public WebResult updateCheckIn(Integer userId,Integer checkin);
    //学员注销审核状态修改
    public WebResult updateWithdrawCheckIn(Integer wid,Integer checkin);

    public WebResult userWithdrawVoList(PageVo pageVo);

    //课程 启用/停用 页面
    public WebResult updateIsenable(EduCourse course);
}
