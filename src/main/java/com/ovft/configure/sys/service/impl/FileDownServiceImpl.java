package com.ovft.configure.sys.service.impl;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.dao.AdminMapper;
import com.ovft.configure.sys.dao.TeacherMapper;
import com.ovft.configure.sys.service.FileDownService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;


/**
 * @ClassName FileDownServiceImpl
 * @Author zqx
 * @Version 1.0
 **/
@Service
public class FileDownServiceImpl implements FileDownService {

    private static final Logger logger = LoggerFactory.getLogger(FileDownServiceImpl.class);

    @Resource
    public TeacherMapper teacherMapper;
    @Resource
    public AdminMapper adminMapper;

    @Override
    public WebResult courseListImport(MultipartFile uploadFile, Integer schoolId) {

        return null;

    }
}
