package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.UserClass;
import com.ovft.configure.sys.service.QuestionSearchService;
import com.ovft.configure.sys.service.UserClassService;
import com.ovft.configure.sys.vo.MyCourseAll;
import com.ovft.configure.sys.vo.PageVo;
import com.ovft.configure.sys.vo.UserClassVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName UserClassController 学员班级
 * @Author xzy
 * @Version 2.5
 **/

@RestController
public class UserClassController {
    @Resource
    private UserClassService userClassService;
    @Resource
    private QuestionSearchService questionSearchService;
    /**
     * 后台班级列表（分页）
     *
     * @param userClassVo
     * @return
     */
    @PostMapping(value = "/server/findServerClassAll")
    public WebResult findServerClassAll(HttpServletRequest request, @RequestBody UserClassVo userClassVo) {
        return userClassService.userClassList(userClassVo);
    }
    /**
     * 学员班级列表（分页）
     *
     * @param userClassVo
     * @return
     */
    @PostMapping(value = "/user/findUserClassAll")
    public WebResult findUserClassAll(HttpServletRequest request, @RequestBody UserClassVo userClassVo) {
        userClassVo.setUserId(Integer.valueOf(request.getHeader("userId")));
        userClassVo.setSchoolId(Integer.parseInt(request.getHeader("schoolId")));
        return userClassService.userClassList(userClassVo);
    }
    /**
     * 删除班级（分页）
     *
     * @param userClass
     * @return
     */
    @PostMapping(value = "/server/user/deleteUserClass")
    public WebResult deleteUserClass(@RequestBody UserClass userClass){

        return userClassService.deleteUserClass(userClass.getClassId());
    }
    /**
     * 删除班级里面的某个学员（分页）
     *
     * @param myCourseAll
     * @return
     */
    @PostMapping(value = "/server/user/classdeleteUser")
    public WebResult classdeleteUser(@RequestBody MyCourseAll myCourseAll){
        return userClassService.classdeleteUser(myCourseAll);
    }
    /**
     * 修改班级（分页）
     *
     * @param userClass
     * @return
     */
    @PostMapping(value = "/server/user/updateUserClass")
    public WebResult updateUserClass(@RequestBody UserClass userClass ){
        return userClassService.updateUserClass(userClass);
    }
}
