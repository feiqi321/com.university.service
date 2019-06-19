package com.ovft.configure.sys.web;

import com.ovft.configure.constant.ConstantClassField;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Admin;
import com.ovft.configure.sys.bean.EduArticle;
import com.ovft.configure.sys.bean.User;
import com.ovft.configure.sys.service.EduArticleService;
import com.ovft.configure.sys.service.UserService;
import com.ovft.configure.sys.utils.RedisUtil;
import com.ovft.configure.sys.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author vvtxw
 * @create 2019-04-21 20:07
 */

@RestController
public class EduArticleController {

    @Autowired
    private EduArticleService eduArticleService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserService userService;

    /**
     * 1-通知公告, 3-校园介绍,  4-联盟资讯,   5-政策法规
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/article/notice")
    public WebResult queryAllNotice(HttpServletRequest request, @RequestBody PageVo pageVo) {
        String schoolId = request.getHeader("schoolId");
        if(!StringUtils.isBlank(schoolId) && !schoolId.equals("null")) {
            pageVo.setSchoolId(Integer.valueOf(schoolId));
        }
        return eduArticleService.queryAllNotice(pageVo);
    }

    /**
     * 进入文章
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/article/queryNotice")
    public WebResult queryNoticeById(@RequestParam(value = "id", required = true) Integer id) {
        return eduArticleService.queryNoticeById(id);
    }

    /**
     * 后台添加 修改  1-通知公告, 3-校园介绍,  4-联盟资讯,   5-政策法规
     *
     * @param eduArticle
     * @return
     */
    @PostMapping(value = "/server/article/addNotice")
    public WebResult adminAddNotice(HttpServletRequest request, @RequestBody EduArticle eduArticle) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        Integer role = 0;
        if(o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
            Admin hget =(Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            if(hget.getRole() != 0) {
                eduArticle.setSchoolId(hget.getSchoolId());
                role = hget.getRole();
            }
            return eduArticleService.adminAddNotice(eduArticle, role);
        }else {
            return new WebResult("50012", "请先登录", "");
        }
    }

    /**
     * 进入文章修改页面
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/server/article/findNotice")
    public WebResult findNotice(@RequestParam(value = "id", required = true) Integer id) {
        return eduArticleService.findNotice(id);
    }

    /**
     * 后台查询文章（分页）
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/server/article/findNoticeAll")
    public WebResult findNoticeAll(HttpServletRequest request, @RequestBody PageVo pageVo) {
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
            return eduArticleService.findNoticeAll(pageVo);
        }else {
            return new WebResult("50012", "请先登录", "");
        }
    }

    /**
     * 后台删除文章
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/server/article/deleteNotice")
    public WebResult deleteNotice(@RequestParam(value = "id") Integer id) {
        return eduArticleService.deleteNotice(id);
    }

    /**
     *  app权限   文章列表
     * @return
     */
    @PostMapping(value = "/api/article/notice")
    public WebResult apiQueryAllNotice(HttpServletRequest request, @RequestBody PageVo pageVo) {
        String userId = request.getHeader("userId");
        if(StringUtils.isBlank(userId)) {
            return new WebResult("50012", "请先登录", "");
        }
        User user = userService.queryByItemsId(Integer.valueOf(userId));
        if(user.getSchoolJob() == null || !user.getSchoolJob().equals(ConstantClassField.SCHOOL_JOB_XCY)) {
            return new WebResult("400", "暂无该权限，请联系管理员添加", "");
        }
        pageVo.setSchoolId(user.getSchoolId());
        return eduArticleService.queryAllNotice(pageVo);
    }

    /**
     * app删除文章   app权限
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/api/article/deleteNotice")
    public WebResult apiDeleteNotice(@RequestParam(value = "id") Integer id) {
        return eduArticleService.deleteNotice(id);
    }


}
