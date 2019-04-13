package com.ovft.configure.sys.service.impl;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.School;
import com.ovft.configure.sys.dao.SchoolMapper;
import com.ovft.configure.sys.service.SchoolService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName SchoolServiceImpl
 * @Author zqx
 * @Date 2019/4/11 10:15
 * @Version 1.0
 **/
@Service
public class SchoolServiceImpl implements SchoolService {

    @Resource
    public SchoolMapper schoolmapper;

    /**
     * 添加学校
     * @param school
     * @return
     */
    @Override
    public WebResult createSchool(School school) {
        if(StringUtils.isBlank(school.getSchoolName())) {
            return new WebResult("400", "学校名称不能为空");
        }
        if(StringUtils.isBlank(school.getLongitude()) || StringUtils.isBlank(school.getLatitude())) {
            return new WebResult("400", "学校位置不能为空");
        }
        schoolmapper.createSchool(school);
        System.out.println("school.getSchoolId() = " + school.getSchoolId());
        return new WebResult("200", "", school);
    }

    @Override
    public WebResult updateSchoolName(School school) {
        if(StringUtils.isBlank(school.getSchoolName())) {
            return new WebResult("400", "学校名称不能为空");
        }
        schoolmapper.updateSchoolName(school);
        return new WebResult("200", "");
    }
}
