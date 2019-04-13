package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.SchoolClass;

public interface SchoolClassService {

    public WebResult createClass(SchoolClass schoolClass);

    public WebResult updateNameClass(SchoolClass schoolClass);
}
