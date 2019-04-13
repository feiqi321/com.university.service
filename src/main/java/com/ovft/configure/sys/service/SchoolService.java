package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.School;

public interface SchoolService {
    public WebResult createSchool(School school);

    public WebResult updateSchoolName(School school);
}
