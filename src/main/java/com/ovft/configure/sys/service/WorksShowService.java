package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.vo.PageVo;

public interface WorksShowService {
     //学员展示列表及模糊查询
     public WebResult findUserShowAll(PageVo pageVo);
     //学员展示首页推荐
    public WebResult findIndexShowAll(PageVo pageVo);
     //学员展示点赞数+1
    public Integer addThumbup(Integer id);
     //教师风采点赞数+1
    public Integer addTeacherThumbup(Integer id);


}
