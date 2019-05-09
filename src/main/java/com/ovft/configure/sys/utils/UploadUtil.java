//package com.ovft.configure.sys.utils;
//
//import com.ovft.configure.http.result.StatusCode;
//import com.ovft.configure.http.result.WebResult;
//import com.ovft.configure.sys.bean.Base64File;
//import com.ovft.configure.sys.service.impl.BASE64DecodedMultipartFile;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * 上传工具
// *
// * @author vvtxw
// * @create 2019-04-24 17:22
// */
//@RestController
//public class UploadUtil {
//
//    public static String uploadImge(Base64File base64File) {
//        System.out.println(base64File.getContent());
//        MultipartFile multipartFile = BASE64DecodedMultipartFile.base64Convert(base64File.getContent());
//        String originalFilename = multipartFile.getOriginalFilename();
//        System.out.println(originalFilename);
//        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
//        System.out.println(extName);
//        String filePath = null;
//        try {
//            filePath = FastUtil.uploadFile(multipartFile.getBytes(), extName);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        return filePath;
//    }
//
//
//}
