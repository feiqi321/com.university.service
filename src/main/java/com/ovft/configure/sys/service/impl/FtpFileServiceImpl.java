package com.ovft.configure.sys.service.impl;

import com.ovft.configure.config.MessageCenterException;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.service.FtpFileService;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * ftp 文件操作服务实现类
 * 
 */
@Service
public class FtpFileServiceImpl implements FtpFileService {

  private static final Logger logger = LoggerFactory.getLogger(FtpFileServiceImpl.class);

/*  @Override
  public WebResult ftpUpload(MultipartFile file) {
    if (file.isEmpty()) {
      return new WebResult("400", "图片为空", "");
    }
    String fileName = file.getOriginalFilename();  // 文件名
    String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
    String filePath = "D://temp-rainy//"; // 上传后的路径
    fileName = UUID.randomUUID() + suffixName; // 新文件名
    File dest = new File(filePath + fileName);
    if (!dest.getParentFile().exists()) {
      dest.getParentFile().mkdirs();
    }
    try {
      file.transferTo(dest);
    } catch (IOException e) {
      e.printStackTrace();
    }
    String filename = "/temp-rainy/" + fileName;

    return null;
  }*/


  /**
   * ftp 服务器
*/
  //ftp服务器ip地址
    @Value("${ftp.address}")
    private  String FTP_ADDRESS;
    //端口号
    @Value("${ftp.prot}")
    private int FTP_PORT;
    //用户名
    @Value("${ftp.username}")
    private String FTP_USERNAME;
    //密码
    @Value("${ftp.password}")
    private String FTP_PASSWORD;
    //图片路径
    @Value("${ftp.imgpath}")
    private String FTP_IMGPATH;

/*  *
   * 本地字符集编码*/

  private static final String LOCAL_CHARSET = "GBK";

/*
  *
   * ftp 服务器字符集编码
*/

  private static final String SERVER_CHARSET = "ISO-8859-1";

/*  *
   * ftp 文件上传*/

  @Override
  public WebResult ftpUpload(MultipartFile file) {
    FTPClient ftpClient = new FTPClient();
    String charset = LOCAL_CHARSET;
    String filePath=null;
    try {
      // 首先获得扩展名，然后生成一个UUID码作为名称，然后加上扩展名
      String fileName = file.getOriginalFilename();
      String ext = fileName.substring(fileName.lastIndexOf("."));
      fileName = UUID.randomUUID().toString() + ext;

      InputStream inputStream=file.getInputStream();

      ftpClient.connect(FTP_ADDRESS, FTP_PORT);
      ftpClient.login(FTP_USERNAME, FTP_PASSWORD);

      ftpClient.changeWorkingDirectory(FTP_IMGPATH);
      ftpClient.setBufferSize(1024);
      ftpClient.enterLocalPassiveMode();
      if (FTPReply.isPositiveCompletion(ftpClient.sendCommand("OPTS UTF8", "ON"))) {
        // 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）.
        charset = "UTF-8";
      }
      ftpClient.setControlEncoding(charset);
      fileName = new String(fileName.getBytes(charset), SERVER_CHARSET);
      // 设置文件类型（二进制）
      ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
      ftpClient.storeFile(fileName, inputStream);
    } catch (IOException e) {
      logger.error("[FTP客户端出错！] :" + e);
      throw new MessageCenterException(new WebResult("400", "图片上传出错！", ""), e);
    } finally {
      try {
        ftpClient.disconnect();
        return new WebResult("200", "图片上传成功", filePath);
      } catch (IOException e) {
        logger.error("[关闭FTP连接发生异常！] :" + e);
        throw new MessageCenterException(new WebResult("400", "图片上传出错！", ""), e);
      }
    }
  }
}