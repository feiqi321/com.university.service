
package com.ovft.configure.sys.web;

import com.jfinal.aop.Before;
import com.ovft.configure.config.CORSInterceptor;
import com.ovft.configure.constant.ConstantClassField;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Admin;
import com.ovft.configure.sys.bean.Contribute;
import com.ovft.configure.sys.bean.User;
import com.ovft.configure.sys.service.UserService;
import com.ovft.configure.sys.utils.RedisUtil;
import com.ovft.configure.sys.vo.PageVo;
import com.ovft.configure.sys.vo.PhoneVo;
import com.ovft.configure.sys.vo.WithdrawVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 *学员用户模块
 * Created by looyer on 2019/4/2.
 */
@RestController
@Before(CORSInterceptor.class)
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/regist")
    public WebResult regist(@RequestBody User user,HttpServletRequest request) {
        user.setUserName(user.getPhone());
        return userService.addUser(user);
    }

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/login")
    public WebResult login(@RequestBody User user) {

        return userService.findUser(user);
    }

    /**
     * 修改密码
     *
     * @param phoneVo
     * @return
     */
    @PostMapping(value = "/updatePassword")
    public WebResult updatePassword(@RequestBody PhoneVo phoneVo) {
        return userService.updatePassword(phoneVo);
    }

//    /**
//     * 基本信息保存、修改
//     *
//     * @param user
//     * @return
//     */
//    @PostMapping(value = "/savaInfoOld")
//    public WebResult savaInfoOld(@RequestBody User user, HttpServletRequest request) {
////        System.out.println(user);
////        System.out.println("========================>"+request.getHeader("schoolId"));
//        user.setSchoolId(Integer.parseInt(request.getHeader("schoolId")));
//        user.setUserId(Integer.parseInt(request.getHeader("userId")));
//        return userService.savaInfo(user);
//    }

    /**
     * 基本信息保存、修改
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/savaInfo")
    public WebResult savaInfo(@RequestBody User user,HttpServletRequest request) {
        user.setUserId(Integer.parseInt(request.getHeader("userId")));
        if (user.getSchoolId()==null){
            user.setSchoolId(0);
        }else {

        }
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if(o != null) {
//            Integer id = (Integer) o;
//            // 如果是pc端登录，更新token缓存失效时间
//            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
            return userService.savaInfo(user);
        }else {
            return new WebResult("50012", "请先登录", "");
        }



    }

    /**
     * 变更手机
     *
     * @param phoneVo
     * @return
     */
    @PostMapping(value = "/updatePhone")
    public WebResult updatePhone(@RequestBody PhoneVo phoneVo) {
        return userService.updatePhone(phoneVo);
    }

    /**
     * 退出登录
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/userQuit")
    public WebResult userQuit(HttpServletRequest request) {
        String token = request.getHeader("token");
        return userService.userQuit(token);
    }

    /**
     * 修改密码（通过原密码）
     *
     * @param
     * @return
     */
    @PostMapping(value = "/updatePasswordByOldpass")
    public WebResult updatePasswordByOldpass(@RequestBody PhoneVo phoneVo, HttpServletRequest request) {
        phoneVo.setUserId(Integer.parseInt(request.getHeader("userId")));
        return userService.updatePasswordByOldPass(phoneVo);
    }

    /**
     * 信息回显
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/selectInfo")
    public WebResult selectInfo(HttpServletRequest request) {
        User user = new User();
        String userId = request.getHeader("userId");
        user.setUserId(Integer.parseInt(userId));
        return userService.selectInfo(user);
    }

    /**
     * 个性签名
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/createMycontext")
    public WebResult createMycontext(@RequestBody User user, HttpServletRequest request) {
        String userId = request.getHeader("userId");
        userService.createMycontext(user, Integer.parseInt(userId));
        return new WebResult("200", "发表成功");
    }

    /**
     * 获取我的课程详情
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/myCourse")
    public WebResult myCourse(HttpServletRequest request) {
        String userId = request.getHeader("userId");
        return userService.myCourse(Integer.valueOf(userId));
    }

    /**
     * 用户注销申请
     *
     * @param withdrawVo,request
     * @return
     */
    @PostMapping(value = "/addWithdraw")
    public WebResult addWithdraw(@RequestBody WithdrawVo withdrawVo, HttpServletRequest request) {
        String userId = request.getHeader("userId");
        User queryByItemsUser = userService.queryByItemsIdAndSchoolId(Integer.parseInt(userId),withdrawVo.getSchoolId());
           if (queryByItemsUser==null){
          return new WebResult("400","未找到您所报的学校相关信息");
           }
        if (queryByItemsUser.getCheckin()!=0){
            return new WebResult("400","您所报名的学校还在审核中");
        }
        User selectById = userService.selectById(Integer.parseInt(userId));
        WithdrawVo findWithdrawVo = userService.selectWithdrawOne(Integer.parseInt(userId));
            if (findWithdrawVo!=null){
                   return new WebResult("400","已在审核中","");
            }
        if (withdrawVo.getContent()==null||withdrawVo.getContent()==""){
            return new WebResult("400","注销原因不能为空","");
        }
        withdrawVo.setCheckin(1);
        withdrawVo.setUid(Integer.parseInt(userId));
        User findUser = userService.queryByItemsId(Integer.parseInt(userId));
        withdrawVo.setUserItemId(findUser.getUserItemId());
        withdrawVo.setUserName(selectById.getUserName());
        userService.addWithdraw(withdrawVo);
        return new WebResult("200", "注销申请成功", "");
    }
    /**
     * 获取用户注销申请记录
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/selectWithdrawOne")
    public WebResult selectWithdrawOne(HttpServletRequest request) {
        String userId = request.getHeader("userId");
        WithdrawVo withdrawOne = userService.selectWithdrawOne(Integer.parseInt(userId));
        if (withdrawOne!=null){
        return new WebResult("200","获取成功",withdrawOne);
        }
        return new WebResult("400","未找到相关记录","");
    }
    /**
     * 获取用户注销申请处理状态
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/selectWithdraw")
    public WebResult selectWithdraw(HttpServletRequest request) {
        String userId = request.getHeader("userId");
        if(userId==""||userId==null){
            return new WebResult("400","","未获取到用户ID");
        }
        WithdrawVo withdrawOne = userService.selectWithdrawOne(Integer.parseInt(userId));
        if (withdrawOne==null){
           return new WebResult("400","您尚未申请注销","");
        }
        int i = userService.selectWithdraw(Integer.parseInt(userId));
        if (i == 0) {
            return new WebResult("200", "注销成功", "");
        }
        if (i == 1) {
            return new WebResult("200", "待审核", "");
        }
        if (i == 2) {
            return new WebResult("200", "拒绝审核", "");
        }
        return null;
    }

    /**
     * 学员所报学校信息假删（1）
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/UpdateUserSchoolId")
     public WebResult UpdateUserSchoolId(@ RequestBody User user){

          userService.UpdateUserSchoolId(user.getUserId());
          return new WebResult("200","删除成功","");
     }
    /**
     * 学员所报学校信息假删
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/deleteUserItem")
     public WebResult deleteUserItem(@ RequestBody User user){
          userService.deleteUserItem(user.getUserItemId());
          return new WebResult("200","删除成功","");
     }

    /**
     * 学员投稿申请
     *
     * @param contribute
     * @return
     */
    @PostMapping(value = "/addUserContribute")
     public WebResult addUserContribute(@ RequestBody Contribute contribute,HttpServletRequest request){

         Integer userId=Integer.parseInt(request.getHeader("userId"));
            contribute.setUserId(userId);
           return userService.addUserContribute(contribute);
     }



    /**
     * 学员投稿申请审核状态列表
     *
     * @param pageVo,request
     * @return
     */
    @PostMapping(value = "/queryUserContributeCheckin")
     public WebResult queryUserContributeCheckin(@ RequestBody PageVo pageVo,HttpServletRequest request){

           return userService.queryUserContributeCheckin(pageVo);
     }

    /**
     * 删除学员投稿申请
     *
     * @param contribute
     * @return
     */
    @PostMapping(value = "/deleteUserContribute")
    public WebResult deleteUserContribute(@ RequestBody Contribute contribute){

        return userService.deleteUserContribute(contribute);
    }
    /**
     * 通过cid查询对应学员投稿申请
     *
     * @param cid
     * @return
     */
    @GetMapping(value = "/findContributeByCid")
    public WebResult findContributeByCid(@Param("cid") Integer cid){
        return userService.findContributeByCid(cid);
    }

    /**
     * 获取学员人数，男生数量，女生数量,党员数量
     * @return
     */
    @GetMapping(value = "/studentsCount")
    public WebResult studentsCount(HttpServletRequest request,@Param("schoolId") Integer schoolId){
//        String schoolIdStr = request.getHeader("schoolId");
//        if(StringUtils.isBlank(schoolIdStr)){
//            return new WebResult("400", "请选择学校", "");
//        }
        return userService.studentsCount(schoolId);
    }
}
