package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Base64File;
import com.ovft.configure.sys.bean.User;
import com.ovft.configure.sys.service.UserService;
import com.ovft.configure.sys.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author vvtxw
 * @create 2019-04-23 15:06
 */
@RestController
@RequestMapping("pic")
public class FileController {

    @Autowired
    private UserService userService;

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
