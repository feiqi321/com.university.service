package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Base64File;
import com.ovft.configure.sys.bean.School;
import com.ovft.configure.sys.bean.User;
import com.ovft.configure.sys.service.SchoolService;
import com.ovft.configure.sys.service.UserService;
import com.ovft.configure.sys.utils.FastUtil;
import com.ovft.configure.sys.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    @Autowired
    private SchoolService schoolService;

    /**
     * 用户图像上传
     *
     * @param base64File
     * @return
     */
    @RequestMapping(value = "uploadUserImage", method = RequestMethod.POST)
    public WebResult upload(@RequestBody Base64File base64File, HttpServletRequest request) {
        String userId1 = request.getHeader("userId");
        Integer userId = Integer.parseInt(userId1);
        System.out.println(userId);
        if (userId == null) {
            return new WebResult(StatusCode.ERROR, "请先去登录", "");
        }
        String url = userService.queryAllAddress(userId);
        if (url != null) {
            String group = url.substring(0, 2);
            String storagePath = url.substring(3);
            FastUtil.delete_file(group, storagePath);
        }

        //上传图片
        String filePath = UploadUtil.uploadImge(base64File);
        System.out.println(filePath);

        if (filePath == null) {
            return new WebResult(StatusCode.ERROR, "上传发生错误", "");
        }
        User user = new User();
        user.setImage(filePath);
        user.setUserId(userId);
        userService.updateAddress(user);
        return new WebResult(StatusCode.OK, "上传成功", filePath);
    }


 /*   @RequestMapping(value = "uploadSchoolImages", method = RequestMethod.POST)
    public WebResult uploadSchoolImages(@RequestBody Base64File[] base64File, HttpServletRequest request) {
        //上传图片
        String[] filePath = UploadUtil.uploadImges(base64File);
        System.out.println(filePath);

        if (filePath == null) {
            return new WebResult(StatusCode.ERROR, "上传发生错误", "");
        }
        String path = "";
        for (String s : filePath) {
            if (s == filePath[filePath.length - 1]) {
                path = s;
            }
            path = s + ",";
        }
        School school = new School();
        school.setSlideshow(path);
        schoolService.updateSchool(school);
        return new WebResult(StatusCode.OK, "上传成功", filePath);
    }
*/

    /**
     * 学校轮播图上传
     *
     * @param uploadFile
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "uploadSchoolImages", method = RequestMethod.POST)
    public Map<String, Object> uploadSchoolImages(@RequestParam("file") MultipartFile uploadFile, Integer schoolId) throws IOException {
        String originalFilename = uploadFile.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        Map<String, Object> result = new HashMap<String, Object>();
        String filePath = null;

   /*     School school = schoolService.queryRecordBySchoolId(schoolId);

        if (school.getImage() != null) {
            String group = school.getImage().substring(0, 2);
            String storagePath = school.getImage().substring(3);
            FastUtil.delete_file(group, storagePath);
        }
        if (school.getSlideshow() != null) {
            String group = school.getSlideshow().substring(0, 2);
            String storagePath = school.getSlideshow().substring(3);
            FastUtil.delete_file(group, storagePath);
        }*/


        try {
            filePath = FastUtil.uploadFile(uploadFile.getBytes(), extName);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("error", 1);
            result.put("message", e);
            return result;
        }
        result.put("error", 0);
        result.put("url", filePath);
        return result;
    }


    /**
     * 展示个人头像
     *
     * @param request
     * @return
     */
    @GetMapping(value = "showimage")
    public WebResult showUserImage(HttpServletRequest request) {
        String userId1 = request.getHeader("userId");
        Integer userId = Integer.parseInt(userId1);
        if (userId == null) {
            return new WebResult(StatusCode.ERROR, "userId不能为空", "");
        }
        String url = userService.queryAllAddress(userId);
        return new WebResult(StatusCode.OK, "查询成功", url);
    }


}
