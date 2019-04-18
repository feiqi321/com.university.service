
package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.UserInfoBean;
import com.ovft.configure.sys.bean.User;
import com.ovft.configure.sys.service.UserInfoService;
import com.ovft.configure.sys.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by looyer on 2019/4/2.
 */
@RestController
@RequestMapping("/user")
public class UserController {

       @Autowired
       private UserService userService;
    /**;
     * 注册
     * @param user
     * @return
     */
    @PutMapping(value = "/regist")
       public WebResult regist(@RequestBody User user){

        user.setUserName(user.getPhone());
        return userService.addUser(user);
    }
    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping(value = "/login")
       public WebResult login(@RequestBody User user){

           return userService.findUser(user);
    }
    /**
     * 修改密码
     * @param  phone,  newPassword,nextpass,identifying_code
     * @return
     */
    @PutMapping(value = "/updatePassword")
    public WebResult updatePassword( @RequestParam(value = "phone")String phone, @RequestParam(value = "newPassword")String newPassword,
                                    @RequestParam(value = "nextpass")String nextpass,@RequestParam(value = "securityCode")String securityCode ){

        System.out.print("短信验证码"+securityCode);
        return userService.updatePassword(phone,newPassword,nextpass,securityCode);
    }
    /**
     * 基本信息保存
     * @param  user
     * @return
     */
    @PutMapping(value = "/savaInfo")
    public WebResult savaInfo(@RequestBody User user){
        return userService.savaInfo(user);
    }
    /**
     * 变更手机
     * @param  oldPhone,newPhone
     * @return
     */
    @PutMapping(value = "/updatePhone")
    public WebResult updatePhone(@RequestParam(value = "oldphone")String oldPhone,@RequestParam(value = "newPhone")String newPhone,
    @RequestParam(value = "securityCode")String securityCode
    ){
         return userService.updatePhone(oldPhone,newPhone,securityCode);
    }

}
