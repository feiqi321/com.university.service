package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.FineCourse;
import com.ovft.configure.sys.vo.PageVo;

public interface FineCourseService {

    public WebResult fineCourseList(PageVo pageVo);
    
    public WebResult createFineCourse(FineCourse fineCourse);

    public WebResult findFineCourse(Integer fineId);

    public WebResult deleteFineCourse(Integer fineId);

    // 从app进入精品课程, 浏览量+1
    public WebResult findCourse(Integer fineId);
    // 点赞数 + 1
    public Integer addThumbup(Integer fineId);
}
