package com.ovft.configure.sys.web;

import com.ovft.configure.constant.ConstantClassField;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Admin;
import com.ovft.configure.sys.bean.Contribute;
import com.ovft.configure.sys.bean.EduArticle;
import com.ovft.configure.sys.bean.User;
import com.ovft.configure.sys.service.AdminService;
import com.ovft.configure.sys.service.EduArticleService;
import com.ovft.configure.sys.service.UserService;
import com.ovft.configure.sys.utils.RedisUtil;
import com.ovft.configure.sys.vo.PageVo;
import com.ovft.configure.sys.vo.WithdrawVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName AdminController
 * @Author zqx
 * @Date 2019/4/10 15:45
 * @Version 1.0
 **/

@RestController
@RequestMapping("/server/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private EduArticleService eduArticleService;

    /**
     * 登录
     *
     * @param admin
     * @return
     */
    @PostMapping(value = "/login")
    public WebResult login(@RequestBody Admin admin) {
        return adminService.login(admin);
    }

    /**
     * 修改密码
     *
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @PostMapping(value = "/updatePassword")
    public WebResult updatePasword(HttpServletRequest request, @RequestParam(value = "oldPassword") String oldPassword,
                                   @RequestParam(value = "newPassword") String newPassword) {
        Integer adminId = (Integer) request.getAttribute("adminId");
        return adminService.updatePassword(adminId, oldPassword, newPassword);
    }

    /**
     * 修改手机号
     *
     * @param newPhone
     * @return
     */
    @PostMapping(value = "/updatePhone")
    public WebResult updatePhone(HttpServletRequest request, @RequestParam(value = "newPhone") String newPhone,
                                 @RequestParam(value = "securityCode") String securityCode) {
        Integer adminId = (Integer) request.getAttribute("adminId");
        return adminService.updatePhone(adminId, newPhone, securityCode);
    }

    /**
     * 管理员/教师列表
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/adminList")
    public WebResult adminList(HttpServletRequest request, @RequestBody PageVo pageVo) {
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
            return adminService.adminList(pageVo);
        }else {
            return new WebResult("50012", "请重新登录", "");
        }
    }

    /**
     * 添加 管理员/教师
     *
     * @param admin
     * @return
     */
    @PostMapping(value = "/createAdmin")
    public WebResult createAdmin(HttpServletRequest request, @RequestBody Admin admin) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if(o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
            Admin hget =(Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            if(hget.getRole() != 0) {
                admin.setSchoolId(hget.getSchoolId());
            }
            return adminService.createAdmin(admin);
        }else {
            return new WebResult("50012", "请重新登录", "");
        }
    }

    /**
     * 进入修改 管理员/教师页面
     *
     * @param adminId
     * @return
     */
    @GetMapping(value = "/findAdmin")
    public WebResult findAdmin(@RequestParam(value = "adminId") Integer adminId, Integer schoolId) {
        return adminService.findAdmin(adminId, schoolId);
    }


    /**
     * 删除管理员、教师
     *
     * @param adminId
     * @return
     */
    @GetMapping(value = "/deleteAdmin")
    public WebResult deleteAdmin(@RequestParam(value = "adminId") Integer adminId, Integer schoolId) {
        return adminService.deleteAdmin(adminId, schoolId);
    }
    /**
     * 学员投稿审核列表
     *
     * @param
     * @return
     */
    @PostMapping(value = "/contributeList")
    public WebResult contributeList(HttpServletRequest request, @RequestBody PageVo pageVo){
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

            return userService.contributeList(pageVo);
        }else {
            return new WebResult("50012", "请重新登录", "");
        }

    }

//    @PostMapping(value = "/addContribute")
//    public WebResult addUserContribute(@ RequestBody Contribute contribute,HttpServletRequest request){
//
//        return userService.addUserContribute(contribute);
//    }

    /**
     * 学员投稿审核
     *
     * @param
     * @return
     */
    @PostMapping(value = "/auditContribute")
    public WebResult auditContribute(HttpServletRequest request,@RequestBody Contribute contribute){
        Integer  checkin=contribute.getCheckin();
        Integer  cid=contribute.getCid();
        //0的话,向article表里面添加记录，改变Contribute的checkin状态
        if (checkin==0){
              userService.updateContributeChinkin(checkin,contribute.getRejectReason(),cid);
              EduArticle eduArticle=new EduArticle();
              eduArticle.setUserid(contribute.getUserId().toString());
              eduArticle.setTitle(contribute.getTitle());
              eduArticle.setContent(contribute.getContent());
              eduArticle.setImage(contribute.getImage());
              eduArticle.setCreatetime(contribute.getCreatetime());

              eduArticle.setIspublic("1");
              eduArticle.setIstop("0");
              eduArticle.setState(contribute.getCheckin().toString());
              eduArticle.setVisits(0);
              eduArticle.setThumbup(0);
              eduArticle.setComment(0);
              eduArticle.setCollect("0");
              eduArticle.setType(contribute.getType().toString());
              eduArticle.setSchoolId(contribute.getSchoolId());


              eduArticleService.adminAddNotice(eduArticle, 1);//向article表里面添加记录

              return new WebResult("200","审核通过","");


        }
//        String token = request.getHeader("token");
//        Object o = redisUtil.get(token);
//        if(o != null) {
//            Integer id = (Integer) o;
//            // 如果是pc端登录，更新token缓存失效时间
//            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
//            Admin hget =(Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
//            if(hget.getRole() == 0) {
//
//                eduArticleService.deleteNotice();
//                userService.updateContributeChinkin(checkin,cid);
//            }else{
//                userService.updateContributeChinkin(checkin,cid);
////        userService.deleteWithdraw(wid);
////        return new WebResult("200", "拒绝成功", "");
//                return new WebResult("200","拒绝成功","");
//
//            }
//
//        }else {
//            return new WebResult("50012", "请先登录", "");
//        }
        userService.updateContributeChinkin(checkin,contribute.getRejectReason(),cid);

//        userService.deleteWithdraw(wid);
//        return new WebResult("200", "拒绝成功", "");
        return new WebResult("200","拒绝成功","");
    }
    /**
     * 学员投稿修改
     *
     * @param contribute
     * @return
     */
    @PostMapping(value = "/updateUserContribute")
    public WebResult updateUserContribute(@ RequestBody Contribute contribute){
        return userService.updateContribute(contribute);
    }
}
