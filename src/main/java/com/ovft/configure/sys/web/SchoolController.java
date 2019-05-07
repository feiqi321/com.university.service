package com.ovft.configure.sys.web;

import com.ovft.configure.constant.ConstantClassField;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Admin;
import com.ovft.configure.sys.bean.School;
import com.ovft.configure.sys.service.SchoolService;
import com.ovft.configure.sys.utils.RedisUtil;
import com.ovft.configure.sys.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SchoolController
 * @Author zqx
 * @Date 2019/4/10 15:45
 * @Version 1.0
 **/

@RestController
public class SchoolController {
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 添加学校
     *
     * @param school
     * @return
     */
    @PostMapping(value = "/server/school/create")
    public WebResult createAdmin(HttpServletRequest request, @RequestBody School school) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if(o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
            Admin hget =(Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            if(hget.getRole() != 0) {
                return new WebResult("400", "暂无该权限", "");
            }
            return schoolService.createSchool(school);
        }else {
            return new WebResult("400", "请先登录", "");
        }
    }

    /**
     * 进入修改学校页面
     *
     * @param schoolId
     * @return
     */
    @GetMapping(value = "/server/school/findSchool")
    public WebResult findSchool(@RequestParam(value = "schoolId") Integer schoolId) {
        return schoolService.findSchool(schoolId);
    }

    /**
     * 修改学校
     *
     * @param school
     * @return
     */
    @PostMapping(value = "/server/school/updateSchool")
    public WebResult updateSchool(@RequestBody School school) {
        return schoolService.updateSchool(school);
    }

    /**
     * 切换学校
     *
     * @param
     * @return
     */
    @GetMapping(value = "/school/switchSchool")
    public WebResult switchSchool() {
        return schoolService.switchSchool();
    }

    /**
     * 切换学校ID
     *
     * @param
     * @return
     */
    @GetMapping(value = "/school/switchSchoolID")
    public WebResult switchSchoolID(@RequestParam(value = "SchoolId") Integer SchoolId, @RequestParam(value = "userId") Integer userId) {
        return schoolService.switchSchoolID(SchoolId, userId);
    }

    /**
     * 学校列表
     *
     * @param request
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/server/school/schoolList")
    public WebResult schoolList(HttpServletRequest request, @RequestBody PageVo pageVo) {
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
            return schoolService.schoolList(pageVo);
        }else {
            return new WebResult("400", "请先登录", "");
        }
    }

    /**
     * 删除学校
     *
     * @param schoolId
     * @return
     */
    @GetMapping(value = "/server/school/deleteSchool")
    public WebResult deleteSchool(@RequestParam(value = "schoolId") Integer schoolId) {
        return schoolService.deleteSchool(schoolId);
    }
    /**
     * 查阅指定学校轮播图
     * @param request
     * @return
     */
    @GetMapping(value = "/school/findSlideshowAll")
    public WebResult findSlideshowAll(HttpServletRequest request) {
        School school = schoolService.findSlideshowAll(Integer.parseInt(request.getHeader("schoolId")));
        String slideshow = school.getSlideshow();
        if (slideshow==null){
            return  new WebResult("400","获取轮播图资源为空", "");
        }
        String[] strs = slideshow.split(",");
        return  new WebResult("200","获取成功", strs);
    }

    /**
     * 查询所有学校（学校名字和学校Id）
     *
     * @param
     * @return
     */
    @GetMapping(value = "/school/findSchoolAll")
    public WebResult findSchoolAll() {
        List<Map<String, String>> schoolAll = schoolService.findSchoolAll();
        return new WebResult("200","查询成功",schoolAll);
    }
    /**
     * 查询对应学校（通过学校id）
     *
     * @param
     * @return
     */
    @GetMapping(value = "/school/findSchoolById")
    public WebResult findSchoolById(HttpServletRequest request) {
        String school = schoolService.findSchoolById(Integer.parseInt(request.getHeader("schoolId")));
           return  new WebResult("200","查询学校成功",school);

    }
}
