package com.ovft.configure.sys.service;

import com.ovft.configure.sys.bean.CourseManager;

import java.util.List;

/**
 * @author vvtxw
 * @create 2019-04-14 18:01
 */
public interface CourseManagerService {
    /**
     * 查询课程变动通知
     * @return
     */
    List<CourseManager> queryAllCourseNotice();
}
