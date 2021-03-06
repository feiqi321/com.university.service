package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.MyActivities;
import com.ovft.configure.sys.service.ActivitiesService;
import com.ovft.configure.sys.service.MyActivitiesService;
import com.ovft.configure.sys.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName MyActivitiesController
 * @Author zqx
 * @Version 1.0
 **/

@RestController
@RequestMapping("/myActivities")
public class MyActivitiesController {

    @Autowired
    private MyActivitiesService myActivitiesService;
    @Autowired
    private ActivitiesService activitiesService;

    /**
     * 活动 列表
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/activitiesList")
    public WebResult activitiesList(HttpServletRequest request, @RequestBody PageVo pageVo) {
        if(pageVo.getType().equals("2")) {
            String schoolId = request.getHeader("schoolId");
            pageVo.setSchoolId(Integer.parseInt(schoolId));
        }
        return activitiesService.activitiesList(pageVo);
    }

    /**
     * 活动 详情
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/findMyActivities")
    public WebResult findMyActivities(HttpServletRequest request, Integer activitiesId) {
        String userId = request.getHeader("userId");
        if(StringUtils.isBlank(userId) || userId.equals("null")) {
            return myActivitiesService.findMyActivities(activitiesId, null);
        }
        return myActivitiesService.findMyActivities(activitiesId, Integer.valueOf(userId));
    }

    /**
     * 我报名的活动 列表
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/myActivitiesList")
    public WebResult myActivitiesList(HttpServletRequest request) {
        String userId = request.getHeader("userId");
        if(StringUtils.isBlank(userId) || userId.equals("null")) {
            return new WebResult("50012", "请登录！", "");
        }
        return myActivitiesService.myActivitiesList(Integer.parseInt(request.getHeader("userId")), null);
    }

    /**
     * 报名活动
     *
     * @param myActivities
     * @return
     */
    @PostMapping(value = "/registMyActivities")
    public WebResult registMyActivities(HttpServletRequest request, @RequestBody MyActivities myActivities) {
        String userId = request.getHeader("userId");
        if(StringUtils.isBlank(userId) || userId.equals("null")) {
            return new WebResult("50012", "请登录！", "");
        }
        myActivities.setUserId(Integer.parseInt(userId));
        return myActivitiesService.registMyActivities(myActivities);
    }

    /**
     * 删除 我报名的活动
     *
     * @param activitiesId
     * @return
     */
    @GetMapping(value = "/deleteMyActivities")
    public WebResult deleteMyActivities(HttpServletRequest request, @RequestParam(value = "activitiesId") Integer activitiesId) {
        String userId = request.getHeader("userId");
        if(StringUtils.isBlank(userId) || userId.equals("null")) {
            return new WebResult("50012", "请登录！", "");
        }
        return myActivitiesService.deleteMyActivities(activitiesId,Integer.parseInt(userId));
    }

}
