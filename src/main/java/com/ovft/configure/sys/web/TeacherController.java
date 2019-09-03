package com.ovft.configure.sys.web;

import com.ovft.configure.constant.ConstantClassField;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Admin;
import com.ovft.configure.sys.bean.User;
import com.ovft.configure.sys.service.TeacherService;
import com.ovft.configure.sys.service.UserService;
import com.ovft.configure.sys.utils.RedisUtil;
import com.ovft.configure.sys.vo.EduCourseVo;
import com.ovft.configure.sys.vo.PageVo;
import com.ovft.configure.sys.vo.UserVo;
import com.ovft.configure.sys.vo.WithdrawVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName TeacherController
 * 后台：学员相关、请假相关、课程列表
 * @Author zqx
 * @Date 2019/4/10 15:45
 * @Version 1.0
 **/

@RestController
@RequestMapping("/server/teacher")
public class TeacherController {

    @Autowired
    public TeacherService teacherService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserService userService;

    /**
     * 学员列表
     * @return
     */
    @PostMapping(value = "/userList")
    public WebResult userList(HttpServletRequest request, @RequestBody PageVo pageVo) {
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
            return teacherService.userList(pageVo);
        }else {
            return new WebResult("50012", "请先登录", "");
        }
    }
    /**
     * 查找游客的所有记录
     * 游客：系统内只注册未报名学校的用户
     * @return
     */
    @PostMapping(value = "/findVisitors")
    public WebResult findVisitors(HttpServletRequest request, @RequestBody PageVo pageVo) {
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
            return teacherService.findVisitors(pageVo);
        }else {
            return new WebResult("50012", "请先登录", "");
        }
    }
    /**
     * 学员注销申请列表
     * @return
     */
    @PostMapping(value = "/userWithdrawList")
    public WebResult userWithdrawList(HttpServletRequest request, @RequestBody PageVo pageVo) {
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
            return teacherService.userWithdrawVoList(pageVo);
        }else {
            return new WebResult("50012", "请先登录", "");
        }
    }

    /**
     * 进入 学员修改页面
     * @param user
     * @return
     */
    @PostMapping(value = "/findUser")
    public WebResult findUser(HttpServletRequest request, @RequestBody User user) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if(o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
            Admin hget =(Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            if(hget.getRole() != 0) {
                user.setSchoolId(hget.getSchoolId());
            }
            return userService.selectInfo(user);
        }else {
            return new WebResult("50012", "请先登录", "");
        }
    }

    /**
     * 添加/修改 学员
     * @param user
     * @return
     */
    @PostMapping(value = "/createUser")
    public WebResult createUser(HttpServletRequest request, @RequestBody User user) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if(o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
            Admin hget =(Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            if(hget.getRole() != 0) {
                user.setSchoolId(hget.getSchoolId());
            }
//            User findUser = userService.findUserByPhone(user.getPhone());
//               if (findUser!=null){
//                   User queryByItemsId = userService.queryByItemsId(findUser.getUserId());
//                        if (queryByItemsId.getSchoolId()!=null){
//                            return new WebResult("400", "该学员已报名其他学校", "");
//                        }else{
//                            return teacherService.savaUserInfo(user);
//                        }
//
//               }
                   return teacherService.savaUserInfo(user);
        }else {
            return new WebResult("50012", "请先登录", "");
        }
    }
    /**
     * 删除 学员
     * @param userItemId
     * @return
     */
    @GetMapping(value = "/deleteUser")
    public WebResult deleteUser(@RequestParam(value = "userItemId")Integer userItemId,Integer userId,HttpServletRequest request) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if(o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
            Admin hget =(Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            if(hget.getRole() == 0) {
              userService.deleteUserItem(userItemId);
            }
            if(hget.getRole() != 0){
              userService.deleteUserItem(userItemId);
                return new WebResult("200", "删除成功", "");
            }
        }else {
            return new WebResult("50012", "请先登录", "");
        }
        return null;
    }
    /**
     * 学员审核状态更改
     *
     * 状态说明   "0"：通过，"1"待审核，"2"拒绝
     * @return
     */
    @PostMapping(value = "/auditUser")
    public WebResult auditUser(@RequestBody User user){
        Integer checkin=user.getCheckin();
        Integer userId=user.getUserId();
            if (checkin==2){
                userService.UpdateUserSchoolId(userId);
                return teacherService.updateCheckIn(userId,checkin);
            }
        return teacherService.updateCheckIn(userId,checkin);
    }
    /**
     * 后台（学员）一键审核通过
     *
     * 状态说明   "0"：通过，"1"待审核，"2"拒绝
     * @return
     */
    @PostMapping(value = "/bigAuditUser")
    public WebResult bigAuditUser(@RequestBody UserVo userVo){

        return teacherService.bigAuditUser(userVo.getUserIds());
    }
    /**
     * 学员"注销"审核状态更改
     * 状态说明   "0"：通过，"1"待审核，"2"拒绝
     * @return
     */
    @PostMapping(value = "/auditWithdraw")
    public WebResult auditWithdraw(@RequestBody WithdrawVo withdrawVo){
             Integer  checkin=withdrawVo.getCheckin();
             Integer  wid=withdrawVo.getWid();


        if (checkin==0){
                userService.deleteWithdraw(wid);
                userService.UpdateUserSchoolId(withdrawVo.getUid());
                return teacherService.updateWithdrawCheckIn(wid,checkin);
            }
        userService.deleteWithdraw(wid);
        return new WebResult("200", "拒绝成功", "");
    }
    /**
     * 请假申请列表
     * @return
     */
    @PostMapping(value = "/vacateList")
    public WebResult vacateList(HttpServletRequest request, @RequestBody PageVo pageVo) {
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
            return  teacherService.vacateChackList(pageVo);
        }else {
            return new WebResult("50012", "请先登录", "");
        }
    }

    /**
     * 删除请假申请
     */
    @GetMapping(value = "/deleteVacate")
    public WebResult deleteVacate(@RequestParam(value = "vacateId")Integer vacateId) {
        return teacherService.deleteVacate(vacateId);
    }

    /**
     * 请假审批
     * @param isCheck
     * @param vacateId
     * @return
     */
    @PostMapping(value = "/vacateApprover")
    public WebResult vacateApprover(@RequestParam(value = "isCheck")Integer isCheck, @RequestParam(value = "vacateId")Integer vacateId) {
        return  teacherService.vacateApprover(vacateId, isCheck);
    }

    /**
     * 课程列表
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/courseList")
    public WebResult courseList(HttpServletRequest request, @RequestBody PageVo pageVo) {
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
            return  teacherService.courseList(pageVo);
        }else {
            return new WebResult("50012", "请先登录", "");
        }
    }

    /**
     * 课程 启用/停用 功能
     * @param course
     * @return
     */
    @PostMapping(value = "/updateIsenable")
    public WebResult updateIsenable(@RequestBody EduCourseVo course) {
        return teacherService.updateIsenable(course);
    }

    /**
     * 添加/修改 课程
     * @param courseVo
     * @return
     */
    @PostMapping(value = "/createCourse")
    public WebResult createCourse(HttpServletRequest request, @RequestBody EduCourseVo courseVo) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if(o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
            Admin hget =(Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            if(hget.getRole() != 0) {
                courseVo.setSchoolId(hget.getSchoolId().toString());
            }
            return  teacherService.createCourse(courseVo);
        }else {
            return new WebResult("50012", "请先登录", "");
        }
    }

    /**
     * 进入修改课程页面
     * @param courseId
     * @return
     */
    @GetMapping(value = "/findCourse")
    public WebResult findCourse(@RequestParam(value = "courseId")Integer courseId) {
        return teacherService.findCourse(courseId);
    }

    /**
     * 删除课程
     * @param courseVo
     * @return
     */
    @PostMapping(value = "/deleteCourse")
    public WebResult deleteCourse(@RequestBody EduCourseVo courseVo) {
        return teacherService.deleteCourse(courseVo.getCourseIds());
    }

    /**
     * 根据checkin和schoolId条件进行学员审核查找
     *
     * @param user
     * @return
     */
    @GetMapping(value = "/findUserByCheckinAndSchoolId")
    public WebResult findUserByCheckinAndSchoolId(@RequestBody User user){

        return userService.findUserByCheckinAndSchoolId(user);
    }
    /**
     * 根据checkin和schoolId条件进行学员注销审核查找
     *
     * @param withdrawVo
     * @return
     */
    @GetMapping(value = "/findWithdrawByCheckinAndSchoolId")
    public WebResult findWithdrawByCheckinAndSchoolId(@RequestBody WithdrawVo withdrawVo){
        return userService.findWithdrawByCheckinAndSchoolId(withdrawVo);
    }
}
