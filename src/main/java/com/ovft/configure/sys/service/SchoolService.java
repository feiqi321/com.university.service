package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.School;
import com.ovft.configure.sys.vo.PageVo;
import org.springframework.web.bind.annotation.RequestParam;

public interface SchoolService {
    public WebResult createSchool(School school);

    public WebResult findSchool(Integer schoolId);

    public WebResult updateSchool(School school);
    /**
     * 根据学校id查询坐标
     * @param schoolId
     * @return
     */
    public School queryRecordBySchoolId(Integer schoolId);
    //找到所有学校
    public WebResult switchSchool();
     //更换学校ID
    public WebResult switchSchoolID(Integer SchoolId,Integer userId);

    public WebResult schoolList(Integer adminId, PageVo pageVo);

    public WebResult deleteSchool(Integer schoolId);
     //查找学校对应轮播图
    public School findSlideshowAll(Integer schoolId);

}
