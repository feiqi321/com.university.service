package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import org.springframework.web.multipart.MultipartFile;

public interface FileDownService {

    //课程上传
    public WebResult courseListImport(MultipartFile uploadFile, Integer schoolId);

    //定期删除文件
    public void deleteFile();
}
