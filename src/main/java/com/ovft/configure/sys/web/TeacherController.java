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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName TeacherController
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
            return new WebResult("400", "请先登录", "");
        }
    }
    /**
     * 学员注销申请列表
     * @return
     */
    @PostMapping(value = "/userWithdrawList")
    public WebResult userWithdrawList(HttpServletRequest request, @RequestBody PageVo pageVo) {
            return teacherService.userWithdrawVoList(pageVo);
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
            return new WebResult("400", "请先登录", "");
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
            return teacherService.savaUserInfo(user);
        }else {
            return new WebResult("400", "请先登录", "");
        }
    }
    /**
     * 删除 学员
     * @param user
     * @return
     */
    @PostMapping(value = "/deleteUser")
    public WebResult deleteUser( @RequestBody User user) {
           userService.deleteUser(user.getUserId());
           return new WebResult("200","删除成功","");
    }
    /**
     * 学员审核状态更改
     * @return
     */
    @PostMapping(value = "/auditUser")
    public WebResult auditUser(HttpServletRequest request){
        Integer checkin=Integer.parseInt(request.getHeader("checkin"));
            if (checkin==2){
                userService.deleteUser(Integer.parseInt(request.getHeader("userId")));
                return teacherService.updateCheckIn(Integer.parseInt(request.getHeader("userId")),checkin);
            }
        return teacherService.updateCheckIn(Integer.parseInt(request.getHeader("userId")),checkin);
    }
    /**
     * 学员注销审核状态更改
     * @return
     */
    @PostMapping(value = "/auditWithdraw")
    public WebResult auditWithdraw(HttpServletRequest request){
        Integer checkin=Integer.parseInt(request.getHeader("checkin"));
            if (checkin==2){
                userService.deleteWithdraw(Integer.parseInt(request.getHeader("wid")));
                return teacherService.updateWithdrawCheckIn(Integer.parseInt(request.getHeader("wid")),checkin);
            }
        return teacherService.updateWithdrawCheckIn(Integer.parseInt(request.getHeader("wid")),checkin);
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
            return new WebResult("400", "请先登录", "");
        }
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
            return new WebResult("400", "请先登录", "");
        }
    }

    /**
     * 进入添加课程页面
     * @param schoolId
     * @return
     */
    @GetMapping(value = "/intoCourse")
    public WebResult intoCourse(@RequestParam(value = "schoolId")Integer schoolId) {
        return teacherService.intoCourse(schoolId);
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
            return new WebResult("400", "请先登录", "");
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
     * 修改课程
     * @param courseVo
     * @return
     */
//    @PostMapping(value = "/updateCourse")
//    public WebResult updateCourse(@RequestBody EduCourseVo courseVo) {
//        return teacherService.updateCourse(courseVo);
//    }

    /**
     * 删除课程
     * @param courseId
     * @return
     */
    @GetMapping(value = "/deleteCourse")
    public WebResult deleteCourse(@RequestParam(value = "courseId")Integer courseId) {
        return teacherService.deleteCourse(courseId);
    }

}
