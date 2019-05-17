package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.MyActivities;
import com.ovft.configure.sys.service.ActivitiesService;
import com.ovft.configure.sys.service.MyActivitiesService;
import com.ovft.configure.sys.vo.PageVo;
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
     * 我报名的活动 列表
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/myActivitiesList")
    public WebResult myActivitiesList(HttpServletRequest request) {
        return myActivitiesService.myActivitiesList(Integer.parseInt(request.getHeader("userId")));
    }

    /**
     * 添加 我报名的活动
     *
     * @param myActivities
     * @return
     */
    @PostMapping(value = "/registMyActivities")
    public WebResult registMyActivities(HttpServletRequest request, @RequestBody MyActivities myActivities) {
        myActivities.setUserId(Integer.parseInt(request.getHeader("userId")));
        return myActivitiesService.registMyActivities(myActivities);
    }

    /**
     * 删除 我报名的活动
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/deleteMyActivities")
    public WebResult deleteMyActivities(@RequestParam(value = "id") Integer id) {
        return myActivitiesService.deleteMyActivities(id);
    }

}
