package com.ovft.configure.sys.web;

import com.ovft.configure.constant.ConstantClassField;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Admin;
import com.ovft.configure.sys.service.FileDownService;
import com.ovft.configure.sys.utils.RedisUtil;
import com.ovft.configure.sys.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName FileDownController  文件下载
 * @Author zqx
 * @Version 1.0
 **/

@RestController
@RequestMapping("/server/fileDown")
public class FileDownController {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private FileDownService fileDownService;

    /**
     * 课程列表下载
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/courseListDowm")
    public WebResult courseListDowm(HttpServletRequest request, @RequestBody PageVo pageVo) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if(o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
            Admin hget =(Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            if(hget.getRole() != 0) {
                pageVo.setSchoolId(hget.getSchoolId());
            }
            return  fileDownService.courseListDowm(pageVo);
        }else {
            return new WebResult("50012", "请先登录", "");
        }
    }


}
