package com.ovft.configure.sys.web;

import com.ovft.configure.constant.ConstantClassField;
import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Admin;
import com.ovft.configure.sys.bean.EduLivePay;

import com.ovft.configure.sys.service.EduLivePayService;


import com.ovft.configure.sys.utils.RedisUtil;
import com.ovft.configure.sys.vo.LivePayVo;
import com.ovft.configure.sys.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author vvtxw
 * @create 2019-09-04 12:26
 */

@RestController
@RequestMapping("/server/eduLivePay")
public class EduLivePayController {

    @Resource
    private EduLivePayService eduLivePayService;
    @Autowired
    private RedisUtil redisUtil;

    //增加
    @PostMapping(value = "/addLivePay")
    public WebResult addLivePay(@RequestBody EduLivePay eduLivePay) {
        Integer res = eduLivePayService.addLivePay(eduLivePay);
        if (res > 0) {
            return new WebResult(StatusCode.OK, "添加现场报名记录成功", "");
        }
        return new WebResult(StatusCode.OK, "添加失败", "");

    }

    //查询
    @PostMapping(value = "/selectLivePay")
    public WebResult selectlivepay(HttpServletRequest request, @RequestBody LivePayVo livePayVo) {
        //request表示对用户的缓存,livePayVo表示查询的条件
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if (o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
            Admin hget = (Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            if (hget.getRole() != 0) {
                livePayVo.setSchoolId(hget.getSchoolId());
            }
            return eduLivePayService.selectLivePay(livePayVo);
        } else {
            return new WebResult("50012", "请先登录", "");
        }
    }

    //增加和修改一起
    @PostMapping(value = "/createLivePay")
    public WebResult createLivePay(HttpServletRequest request, @RequestBody LivePayVo livePayVo) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if (o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
            Admin hget = (Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            if (hget.getRole() != 0) {
                livePayVo.setSchoolId(hget.getSchoolId());
            }
            return eduLivePayService.createLivePay(livePayVo);
        } else {
            return new WebResult("50012", "请先登录", "");
        }
    }

    //删除记录
    @GetMapping(value = "/deleteById")
    public WebResult deleteById(HttpServletRequest request, @RequestParam("id") Integer id) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if (o != null) {
            Integer id2 = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
            Admin hget = (Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id2.toString());
            eduLivePayService.deleteById(id);
            return new WebResult("200", "删除成功", "");
        } else {
            return new WebResult("50012", "请先登录", "");
        }

    }

    //保存之前,通过phone查询user的信息,要全部显示
    @GetMapping(value = "/selectByPhone")
    public WebResult selectByPhone(HttpServletRequest request, @RequestParam("phone") String phone) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if (o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
            Admin hget = (Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            return eduLivePayService.selectByPhone(phone);
        } else {
            return new WebResult("50012", "请先登录", "");
        }

    }

    //保存之前,通过课程名字模糊查询课程的信息
    @GetMapping(value = "/selectByCourseName")
    public WebResult selectByCourseName(HttpServletRequest request, @RequestParam("search") String search) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if (o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
            Admin hget = (Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            return eduLivePayService.selectByCourseName(search);
        } else {
            return new WebResult("50012", "请先登录", "");
        }
    }
}




