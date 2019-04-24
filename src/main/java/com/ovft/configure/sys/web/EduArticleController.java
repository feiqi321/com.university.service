package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduArticle;
import com.ovft.configure.sys.service.EduArticleService;
import com.ovft.configure.sys.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author vvtxw
 * @create 2019-04-21 20:07
 */
@RestController
@RequestMapping("article")
public class EduArticleController {

    @Autowired
    private EduArticleService eduArticleService;

    /**
     * 通知公告
     *
     * @param type
     * @return
     */
    @GetMapping(value = "notice")
    public WebResult queryAllNotice(@RequestParam(value = "type", required = true) String type) {
        List<EduArticle> eduArticles = eduArticleService.queryAllNotice(type);
        return new WebResult(StatusCode.OK, "查询成功", eduArticles);
    }

    /**
     * 后台添加文章
     *
     * @param eduArticle
     * @return
     */
    @PostMapping(value = "server/adminAddNotice")
    public WebResult adminAddNotice(@RequestBody EduArticle eduArticle) {
        return eduArticleService.adminAddNotice(eduArticle);
    }

    /**
     * 后台查询文章（分页）
     *
     * @param type
     * @return TODO
     */
    @PostMapping(value = "server/findNoticeAll")
    public WebResult findNoticeAll(@RequestParam(value = "type") String type, @RequestBody PageVo pageVo) {
        return eduArticleService.findNoticeAll(type,pageVo);
    }

    /**
     * 后台删除文章
     *
     * @param id
     * @return TODO
     */
    @PostMapping(value = "server/deleteNotice")
    public WebResult deleteNotice(@RequestParam(value = "id") Integer id) {
        return eduArticleService.deleteNotice(id);
    }

    /**
     * 后台批量删除文章
     *
     * @param ids
     * @return TODO
     */
    @PostMapping(value = "server/bigDeleteNotice")
    public WebResult bigDeleteNotice(@RequestParam Integer [] ids) {
        return eduArticleService.bigDeleteNotice(ids);
    }

    /**
     * 后台修改文章
     *
     * @param eduArticle
     * @return
     */
    @PostMapping(value = "server/updateNotice")
    public WebResult updateNotice(@RequestBody EduArticle eduArticle) {
        return eduArticleService.updateNotice(eduArticle);
    }

}
