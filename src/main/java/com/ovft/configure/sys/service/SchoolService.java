package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.School;

public interface SchoolService {
    public WebResult createSchool(School school);

    public WebResult updateSchoolName(School school);
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
}
