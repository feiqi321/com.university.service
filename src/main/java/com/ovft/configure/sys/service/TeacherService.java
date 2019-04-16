package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface TeacherService {

    public WebResult vacateChackList(Integer adminId);

    public WebResult createCourse(HashMap<String, Object> course);

    public WebResult vacateApprover(HashMap<String,Object> map);

}
