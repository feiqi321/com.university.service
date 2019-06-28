package com.ovft.configure.sys.web;

import com.ovft.configure.constant.ConstantClassField;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Activities;
import com.ovft.configure.sys.bean.Admin;
import com.ovft.configure.sys.bean.MyActivities;
import com.ovft.configure.sys.service.ActivitiesService;
import com.ovft.configure.sys.service.MyActivitiesService;
import com.ovft.configure.sys.utils.RedisUtil;
import com.ovft.configure.sys.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName ActivitiesController  活动
 * @Author zqx
 * @Version 1.0
 **/

@RestController
@RequestMapping("/server/activities")
public class ActivitiesController {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ActivitiesService activitiesService;
    @Autowired
    private MyActivitiesService myActivitiesService;

    /**
     * 活动列表
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/activitiesList")
    public WebResult activitiesList(HttpServletRequest request, @RequestBody PageVo pageVo) {
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
            return activitiesService.activitiesList(pageVo);
        }else {
            return new WebResult("50012", "请先登录", "");
        }
    }

    /**
     * 添加/修改活动
     *
     * @param activities
     * @return
     */
    @PostMapping(value = "/createActivities")
    public WebResult createActivities(HttpServletRequest request, @RequestBody Activities activities) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if(o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
            Admin hget =(Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            if(hget.getRole() != 0) {
                activities.setSchoolId(hget.getSchoolId());
            }
            return activitiesService.createActivities(activities);
        }else {
            return new WebResult("50012", "请先登录", "");
        }
    }

    /**
     * 进入 活动修改页面
     * @param activitiesId
     * @return
     */
    @GetMapping(value = "/findActivities")
    public WebResult findActivities(HttpServletRequest request, Integer activitiesId) {
        return activitiesService.findActivities(activitiesId);
    }

    /**
     * 删除活动
     *
     * @param activitiesId
     * @return
     */
    @GetMapping(value = "/deleteActivities")
    public WebResult deleteActivities(@RequestParam(value = "activitiesId") Integer activitiesId) {
        return activitiesService.deleteActivities(activitiesId);
    }

    /**
     * 我报名的活动 列表
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/myActivitiesList")
    public WebResult myActivitiesList(HttpServletRequest request) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if(o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
            Admin hget =(Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            return myActivitiesService.myActivitiesList(null, hget.getAdminId());
        }else {
            return new WebResult("50012", "请重新登录", "");
        }
    }

    /**
     * 添加 我报名的活动
     *
     * @param myActivities
     * @return
     */
    @PostMapping(value = "/registMyActivities")
    public WebResult registMyActivities(HttpServletRequest request, @RequestBody MyActivities myActivities) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if(o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
            Admin hget =(Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            myActivities.setAdminId(hget.getAdminId());
            return myActivitiesService.registMyActivities(myActivities);
        }else {
            return new WebResult("50012", "请重新登录", "");
        }
    }

    /**
     * 删除 我报名的活动
     * @param activitiesId
     * @return
     */
    @GetMapping(value = "/deleteMyActivities")
    public WebResult deleteMyActivities(@RequestParam(value = "activitiesId") Integer activitiesId) {
        return myActivitiesService.deleteMyActivitiesPc(activitiesId);
    }

    /**
     *活动报名列表
     * @param activitiesId
     * @return
     */
    @GetMapping(value = "/registList")
    public WebResult registList(@RequestParam(value = "activitiesId") Integer activitiesId) {
        return activitiesService.registList(activitiesId);
    }

}
