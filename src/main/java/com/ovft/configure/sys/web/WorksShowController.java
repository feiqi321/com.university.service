package com.ovft.configure.sys.web;

import com.ovft.configure.constant.ConstantClassField;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Admin;
import com.ovft.configure.sys.bean.EduArticle;
import com.ovft.configure.sys.service.EduArticleService;
import com.ovft.configure.sys.service.WorksShowService;
import com.ovft.configure.sys.utils.AgeUtil;
import com.ovft.configure.sys.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName WorksShowController
 * 注意：1.App端作品展示(包括学员展示、教师风采)
 *      2.教师风采、学员展示均是用的edu_article表，通过type区分
 * @Author xzy
 * @Date 2019/5/21 15:45
 * @Version 1.0
 **/
@RestController
public class WorksShowController {
     @Autowired
     private EduArticleService eduArticleService;
     @Autowired
     private WorksShowService worksShowService;

    /**
     * 学员展示列表及模糊查询（分页）
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/WorksShow/findUserShowAll")
    public WebResult findUserShowAll(HttpServletRequest request, @RequestBody PageVo pageVo) {
            pageVo.setType("7");
        return worksShowService.findUserShowAll(pageVo);

}
    /**
     * 学员展示列表及模糊查询（分页）
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/server/findUserShowAll")
    public WebResult serverUserShowAll(HttpServletRequest request, @RequestBody PageVo pageVo) {
        pageVo.setType("7");
        return worksShowService.findUserShowAll(pageVo);

    }

    /**
     * 教师风采列表及模糊查询（分页）
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/WorksShow/findTeacherShowAll")
    public WebResult findTeacherShowAll(HttpServletRequest request, @RequestBody PageVo pageVo) {
            pageVo.setType("8");
            return worksShowService.findUserShowAll(pageVo);

}

    /**
     * 教师风采列表及模糊查询（分页）
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/server/findTeacherShowAll")
    public WebResult serverTeacherShowAll(HttpServletRequest request, @RequestBody PageVo pageVo) {
        pageVo.setType("8");
        return worksShowService.findUserShowAll(pageVo);

    }

    /**
     * 学员展示首页推荐
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/WorksShow/findIndexShowAll")
    public WebResult findIndexShowAll(HttpServletRequest request, @RequestBody PageVo pageVo) {
        pageVo.setType("7");
        return worksShowService.findIndexShowAll(pageVo);

    }
    /**
     * 教师风采首页推荐
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/WorksShow/TeacherIndexShowAll")
    public WebResult TeacherIndexShowAll(HttpServletRequest request, @RequestBody PageVo pageVo) {
        pageVo.setType("8");
        return worksShowService.findIndexShowAll(pageVo);

    }

    /**
     * 进入学员详情页
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/WorksShow/queryUserShowById")
    public WebResult queryUserShowById(@RequestParam(value = "id", required = true) Integer id) {
        return eduArticleService.queryNoticeById(id);
    }

    /**
     * 进入学员详情页
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/server/queryUserShowById")
    public WebResult serverUserShowById(@RequestParam(value = "id", required = true) Integer id) {
        return eduArticleService.queryNoticeById(id);
    }

    /**
     * 进入教师详情页
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/WorksShow/queryTeacherShowById")
    public WebResult queryTeacherShowById(@RequestParam(value = "id", required = true) Integer id) {
        return eduArticleService.queryNoticeById(id);
    }

    /**
     * 进入教师详情页
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/server/queryTeacherShowById")
    public WebResult serverTeacherShowById(@RequestParam(value = "id", required = true) Integer id) {
        return eduArticleService.queryNoticeById(id);
    }

    /**
     * 后台添加、修改学员展示
     *
     * @param eduArticle
     * @return
     */
    @PostMapping(value = "/server/WorksShow/updateUserShowById")
    public WebResult updateUserShowById(@RequestBody EduArticle eduArticle) {
        //后台修改学员展示作品

        return eduArticleService.adminAddNotice(eduArticle,1);
    }
    /**
     * 后台添加、修改教师风采
     *
     * @param eduArticle
     * @return
     */
    @PostMapping(value = "/server/WorksShow/updateTeacherShowById")
    public WebResult updateTeacherShowById(@RequestBody EduArticle eduArticle) {
        //后台修改教师风采作品
        return eduArticleService.adminAddNotice(eduArticle,1);
    }
    /**
     * 后台删除一条学员展示
     *
     * @param eduArticle
     * @return
     */
    @PostMapping(value = "/server/WorksShow/deleteUserShowById")
    public WebResult deleteUserShowById(@RequestBody EduArticle eduArticle) {
        //后台删除学员展示作品
        return eduArticleService.deleteNotice(eduArticle.getId());
    }
    /**
     * 后台删除一条教师风采记录
     *
     * @param eduArticle
     * @return
     */
    @PostMapping(value = "/server/WorksShow/deleteTeacherShowById")
    public WebResult deleteTeacherShowById(@RequestBody EduArticle eduArticle) {
        //后台删除学员展示作品
        return eduArticleService.deleteNotice(eduArticle.getId());
    }
}