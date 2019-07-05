package com.ovft.configure.sys.web;

import com.google.gson.Gson;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Base64File;
import com.ovft.configure.sys.bean.User;
import com.ovft.configure.sys.service.EduArticleService;
import com.ovft.configure.sys.service.FileDownService;
import com.ovft.configure.sys.service.TeacherService;
import com.ovft.configure.sys.service.UserService;
import com.ovft.configure.sys.service.impl.BASE64DecodedMultipartFile;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName QiniuyunController  视频上传
 * @Author zqx
 * @Version 1.0
 **/

@RestController
@RequestMapping("/qiniuyun")
public class QiniuyunController {
    private static final Logger logger = LoggerFactory.getLogger(QiniuyunController.class);

    @Autowired
    FileDownService fileDownService;
    @Autowired
    UserService userService;

    //...生成上传凭证，然后准备上传
    @Value("${qiniuyun.accessKey}")
    private String accessKey;
    @Value("${qiniuyun.secretKey}")
    private String secretKey;
    @Value("${qiniuyun.bucket}")
    private String bucket;

    /**
     * 后端返回的上传验证信息
     * @return
     */
    @PostMapping(value = "/getToken")
    public WebResult getToken() {
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        return new WebResult("200", "成功", upToken);
    }

    /**
     *  app端  图片上传
     * @return
     */
    @RequestMapping(value = "/uploadAppImage", method = RequestMethod.POST)
    public WebResult upload(@RequestBody Base64File base64File, HttpServletRequest request) {
        MultipartFile file = BASE64DecodedMultipartFile.base64Convert(base64File.getContent());
        return uploadFile(request, file, "");
    }

    /**
     *  头像上传
     * @return
     */
    @RequestMapping(value = "/headImage", method = RequestMethod.POST)
    public WebResult headImage(@RequestBody Base64File base64File, HttpServletRequest request) {
        MultipartFile file = BASE64DecodedMultipartFile.base64Convert(base64File.getContent());
        WebResult webResult = uploadFile(request, file, "head/");
        if(webResult.getCode().equals("200")) {
            String userId1 = request.getHeader("userId");
            Integer userId = Integer.parseInt(userId1);

            User user = new User();
            user.setImage((String) webResult.getData());
            user.setUserId(userId);
            userService.updateAddress(user);
        }
        return webResult;
    }

    /**
     * 文件上传
     * @param file
     * @return
     * 学校轮播图上传   schoolImages/   学校轮播图，学校校徽
     * 活动图片         activity/
     *
     *
     */
    @PostMapping(value = "/videoImport")
    public WebResult courseListImport(HttpServletRequest request, @RequestParam("file") MultipartFile file, @RequestParam("filePrefix") String filePrefix) {
        return uploadFile(request, file, filePrefix);
    }

    private WebResult uploadFile(HttpServletRequest request, MultipartFile file, String filePrefix) {
        String token = request.getHeader("token");
        if(file == null) {
            return new WebResult("400", "请上传文件", "");
        }
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);

        // 获取上传的文件的名称
        String filename = file.getOriginalFilename();
        //获取文件后缀名
        String prefix=filename.substring(filename.lastIndexOf(".")+1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateTime = dateFormat.format(new Date());
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = filePrefix + dateTime + "." + prefix;;

        try {
            byte[] uploadBytes = file.getBytes();
            ByteArrayInputStream byteInputStream=new ByteArrayInputStream(uploadBytes);
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(byteInputStream,key,upToken,null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                return new WebResult("200", "上传成功", putRet.key);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                logger.error("视频上传失败： " + ex.getMessage());
                return new WebResult("400", "上传失败", "");
            }
        } catch (UnsupportedEncodingException ex) {
            //ignore
            logger.error("视频上传失败： " + ex.getMessage());
            return new WebResult("400", "上传失败", "");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("视频上传失败： " + e.getMessage());
            return new WebResult("400", "上传失败", "");
        }
    }


    @Autowired
    public EduArticleService eduArticleService;
    @Autowired
    public TeacherService teacherService;
    @GetMapping(value = "/deleteVideo")
    public void courseListImport() {
        //文章置顶的定时任务
        try {
            eduArticleService.topScheduleTask();
        }catch (Exception e) {
            e.getMessage();
        }

        try {
            teacherService.shelvesCourse();
        }catch (Exception e) {
            e.getMessage();
        }
        try {
            fileDownService.deleteFile();
        }catch (Exception e) {
            e.getMessage();
        }
    }

}
