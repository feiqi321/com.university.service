
package com.ovft.configure.sys.web;

import com.jfinal.aop.Before;
import com.ovft.configure.config.CORSInterceptor;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.User;
import com.ovft.configure.sys.service.UserService;
import com.ovft.configure.sys.vo.PhoneVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by looyer on 2019/4/2.
 */
@RestController
@Before(CORSInterceptor.class)
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    /**;
     * 注册
     * @param user
     * @return
     */
    @PostMapping(value = "/regist")
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
     * @param  phoneVo
     * @return
     */
    @PostMapping(value = "/updatePassword")
    public WebResult updatePassword(@RequestBody PhoneVo phoneVo){
        return userService.updatePassword(phoneVo);
    }
    /**
     * 基本信息保存、修改
     * @param  user
     * @return
     */
    @PostMapping(value = "/savaInfo")
    public WebResult savaInfo(@RequestBody User user,HttpServletRequest request){
//        System.out.println(user);
//        System.out.println("========================>"+request.getHeader("schoolId"));
        user.setSchoolId(Integer.parseInt(request.getHeader("schoolId")));
        user.setUserId(Integer.parseInt(request.getHeader("userId")));
        return userService.savaInfo(user);
    }
    /**
     * 变更手机
     * @param  phoneVo
     * @return
     */
    @PostMapping(value = "/updatePhone")
    public WebResult updatePhone(@RequestBody PhoneVo phoneVo){
        return userService.updatePhone(phoneVo);
    }
    /**
     * 退出登录
     * @param  request
     * @return
     */
    @PostMapping(value = "/userQuit")
    public WebResult userQuit(HttpServletRequest request){
        String token=request.getHeader("token");
        return userService.userQuit(token);
    }
    /**
     * 修改密码（通过原密码）
     * @param
     * @return
     */
    @PostMapping(value = "/updatePasswordByOldpass")
    public WebResult updatePasswordByOldpass(@RequestBody PhoneVo phoneVo, HttpServletRequest request){
        phoneVo.setUserId(Integer.parseInt(request.getHeader("userId")));
        return userService.updatePasswordByOldPass(phoneVo);
    }
    /**
     * 信息回显
     * @param  request
     * @return
     */
    @GetMapping(value = "/selectInfo")
    public WebResult selectInfo(HttpServletRequest request){
        User user=new User();
        String userId= request.getHeader("userId");
        user.setUserId(Integer.parseInt(userId));
        user.setSchoolId(Integer.parseInt(request.getHeader("schoolId")));
        return userService.selectInfo(user);
    }
    /**
     * 个性签名
     * @param  request
     * @return
     */
    @PostMapping(value = "/createMycontext")
    public WebResult createMycontext(@RequestBody User user,HttpServletRequest request){
        String userId=request.getHeader("userId" );
         userService.createMycontext(user,Integer.parseInt(userId));
          return  new WebResult("200","发表成功");
    }

    /**
     * 获取我的课程详情
     * @param request
     * @return
     */
    @GetMapping(value = "/myCourse")
    public  WebResult myCourse(HttpServletRequest request) {
        String userId=request.getHeader("userId" );
        return userService.myCourse(Integer.valueOf(userId));
    }

}
