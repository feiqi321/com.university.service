package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import org.springframework.web.multipart.MultipartFile;

public interface FtpFileService {

    public WebResult ftpUpload(MultipartFile file);
}
