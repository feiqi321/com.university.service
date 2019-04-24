package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Base64File;
import com.ovft.configure.sys.bean.file;
import com.ovft.configure.sys.service.UserService;
import com.ovft.configure.sys.service.impl.BASE64DecodedMultipartFile;
import com.ovft.configure.sys.utils.FastUtil;
import com.ovft.configure.sys.utils.JSONUtil;
import com.ovft.configure.sys.utils.UploadUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author vvtxw
 * @create 2019-04-23 15:06
 */
@RestController
@RequestMapping("pic")
public class FileController {

    @Autowired
    private UserService userService;

    /*@Value("${fastdfs.storage.url}")*/
    private String storageHost = "http://192.168.17.128/";

    /**
     * 用户图像上传
     *
     * @param base64File
     * @return
     */
/*
    @RequestMapping(value = "uploadUserImage", method = RequestMethod.POST)
    public WebResult upload(@RequestBody Base64File base64File, HttpServletRequest request) {
        String userId1 = request.getHeader("userId");
        Integer userId = Integer.parseInt(userId1);
        System.out.println(userId);
        if (userId == null) {
            return new WebResult(StatusCode.ERROR, "请先去登录", "");
        }
        //上传图片
        String filePath = uploadImge(base64File);

        if (filePath == null) {
            return new WebResult(StatusCode.ERROR, "上传发生错误", "");
        }
        userService.updateAddress(filePath, userId);
        return new WebResult(StatusCode.OK, "上传成功", "");
    }

    @GetMapping(value = "showimage")
    public WebResult showUserImage(HttpServletRequest request) {
        String userId1 = request.getHeader("userId");
        Integer userId = Integer.parseInt(userId1);
//        Integer userId = 1;
        if (userId == null) {
            return new WebResult(StatusCode.ERROR, "userId不能为空", "");
        }
        String url = userService.queryAllAddress(userId);
        return new WebResult(StatusCode.OK, "查询成功", url);
    }
*/
    @RequestMapping(value = "uploadUserImage", method = RequestMethod.POST)
    public Map<String, Object> uploadImge(@RequestBody Base64File base64File) {
        System.out.println(base64File.getContent());
//        System.out.println(base64File.getFile().getName());

        MultipartFile multipartFile = BASE64DecodedMultipartFile.base64Convert(base64File.getContent());
        String originalFilename = multipartFile.getOriginalFilename();
        System.out.println(originalFilename);
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        System.out.println(extName);
        Map<String, Object> map = new HashMap<String, Object>();
        String filePath = null;
        try {
            filePath = FastUtil.uploadFile(multipartFile.getBytes(), extName);
            filePath = storageHost + filePath;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", 1);
            map.put("message", e);
            return map;
        }
        map.put("error", 0);
        map.put("url", filePath);
        return map;
    }


    /*private String uploadImge(Base64File base64File) {
        System.out.println(base64File.getContent());
        MultipartFile multipartFile = BASE64DecodedMultipartFile.base64Convert(base64File.getContent());
        String originalFilename = multipartFile.getOriginalFilename();
        System.out.println(originalFilename);
        System.out.println(storageHost);
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        System.out.println(extName);
        String filePath = null;
        try {
            filePath = FastUtil.uploadFile(multipartFile.getBytes(), extName);
            filePath = storageHost + filePath;
        } catch (Exception e) {
            e.printStackTrace();
            return "连接超时";
        }
        return filePath;
    }*/

}
