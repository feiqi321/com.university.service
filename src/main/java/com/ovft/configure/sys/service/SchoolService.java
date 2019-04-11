package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.School;

public interface SchoolService {
    WebResult createSchool(School school);
}
