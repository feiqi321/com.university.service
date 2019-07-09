package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduVolunteer;
import com.ovft.configure.sys.service.EduVolunteerService;
import com.ovft.configure.sys.vo.PageVo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zqx
 * @since 2019-07-04
 */
@RestController
@RequestMapping("/eduVolunteer")
public class EduVolunteerContrroller {

    @Autowired
    private EduVolunteerService volunteerService;

    //获取志愿活动列表
    @PostMapping("/volunteerList")
    public WebResult volunteerList(HttpServletRequest request, @RequestBody PageVo pageVo) {
        return volunteerService.volunteerList(pageVo);
    }

    //志愿活动详情
    @GetMapping("/findVolunteer")
    public WebResult findVolunteer(HttpServletRequest request, @RequestParam("volunteerId") Integer volunteerId) {
        String userId = request.getHeader("userId");
        return volunteerService.findVolunteer(userId, volunteerId);
    }

    //志愿活动报名
    @GetMapping("/volunteerRegist")
    public WebResult volunteerRegist(HttpServletRequest request, @RequestParam("volunteerId") Integer volunteerId) {
        String userId = request.getHeader("userId");
        if(StringUtils.isBlank(userId)){
            return new WebResult("400", "请先登录", "");
        }
        return volunteerService.volunteerRegist(Integer.valueOf(userId), volunteerId);
    }

    //我发布的志愿活动列表
    @PostMapping("/myVolunteerList")
    public WebResult myVolunteerList(HttpServletRequest request, @RequestBody PageVo pageVo) {
        String userId = request.getHeader("userId");
        if(StringUtils.isBlank(userId)){
            return new WebResult("400", "请先登录", "");
        }
        pageVo.setUserId(Integer.valueOf(userId));
        return volunteerService.volunteerList(pageVo);
    }

    //我报名的志愿活动列表
    @PostMapping("/myRegistVolunteer")
    public WebResult myRegistVolunteer(HttpServletRequest request, @RequestBody PageVo pageVo) {
        String userId = request.getHeader("userId");
        if(StringUtils.isBlank(userId)){
            return new WebResult("400", "请先登录", "");
        }
        pageVo.setUserId(Integer.valueOf(userId));
        return volunteerService.myRegistVolunteer(pageVo);
    }

    //发布志愿活动
    @PostMapping("/releaseVolunteer")
    public WebResult releaseVolunteer(HttpServletRequest request,@RequestBody EduVolunteer eduVolunteer) {
        String userId = request.getHeader("userId");
        if(StringUtils.isBlank(userId)){
            return new WebResult("400", "请先登录", "");
        }
        eduVolunteer.setUserId(Integer.valueOf(userId));
        return volunteerService.releaseVolunteer(eduVolunteer);
    }


}
