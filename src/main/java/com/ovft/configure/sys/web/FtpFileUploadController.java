package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.service.FtpFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * 文件上传
 */
@CrossOrigin
@RestController
@RequestMapping("/upload")
public class FtpFileUploadController {
    @Autowired
    private FtpFileService ftpFileService;

    //ftp处理文件上传
    @RequestMapping(value="/ftpuploadimg", method = RequestMethod.POST)
    public WebResult uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        return  ftpFileService.ftpUpload(file);
    }


}