//package com.ovft.configure.sys.utils;
//
//import org.apache.commons.net.ftp.FTPClient;
//import org.apache.commons.net.ftp.FTPReply;
//import org.springframework.beans.factory.annotation.Value;
//
//import java.io.IOException;
//import java.io.InputStream;
//
///**
// * Created by nishuai on 2018/1/12.
// */
//public class FtpFileUtil {
//
//    //ftp服务器ip地址
//    @Value("${ftp.address}")
//    private  String FTP_ADDRESS;
//    //端口号
//    @Value("${ftp.prot}")
//    private int FTP_PORT;
//    //用户名
//    @Value("${ftp.username}")
//    private String FTP_USERNAME;
//    //密码
//    @Value("${ftp.password}")
//    private String FTP_PASSWORD;
//    //图片路径
//    @Value("${ftp.basepath}")
//    private String FTP_BASEPATH;
//
//    public  static boolean uploadFile(String originFileName,InputStream input){
//        boolean success = false;
//        FTPClient ftp = new FTPClient();
//        ftp.setControlEncoding("GBK");
//        try {
//            int reply;
//            ftp.connect(FTP_ADDRESS, FTP_PORT);// 连接FTP服务器
//            ftp.login(FTP_USERNAME, FTP_PASSWORD);// 登录
//            reply = ftp.getReplyCode();
//            if (!FTPReply.isPositiveCompletion(reply)) {
//                ftp.disconnect();
//                return success;
//            }
//            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
//            ftp.makeDirectory(FTP_BASEPATH );
//            ftp.changeWorkingDirectory(FTP_BASEPATH );
//            ftp.storeFile(originFileName,input);
//            input.close();
//            ftp.logout();
//            success = true;
//        } catch (IOException e) {
//            e.printStackTrace();
//
//        } finally {
//            if (ftp.isConnected()) {
//                try {
//                    ftp.disconnect();
//                } catch (IOException ioe) {
//                }
//            }
//        }
//        return success;
//    }
//
//}