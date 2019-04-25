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
public class EduArticleController {

    @Autowired
    private EduArticleService eduArticleService;

    /**
     * 通知公告
     *
     * @param type
     * @return
     */
    @GetMapping(value = "/article/notice")
    public WebResult queryAllNotice(@RequestParam(value = "type", required = true) String type) {
        List<EduArticle> eduArticles = eduArticleService.queryAllNotice(type);
        return new WebResult(StatusCode.OK, "查询成功", eduArticles);
    }

    /**
     * 后台添加 修改  1-通知公告, 3-校园介绍,  4-联盟资讯,   5-政策法规
     *
     * @param eduArticle
     * @return
     */
    @PostMapping(value = "/server/article/addNotice")
    public WebResult adminAddNotice(@RequestBody EduArticle eduArticle) {
        return eduArticleService.adminAddNotice(eduArticle);
    }

    /**
     * 进入文章修改页面
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/server/article/findNotice")
    public WebResult findNoticeAll(@RequestParam(value = "id", required = true) Integer id) {
        return eduArticleService.findNotice(id);
    }

    /**
     * 后台查询文章（分页）
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/server/article/findNoticeAll")
    public WebResult findNoticeAll(@RequestBody PageVo pageVo) {
        return eduArticleService.findNoticeAll(pageVo);
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

//    /**
//     * 后台批量删除文章
//     *
//     * @param ids
//     * @return TODO
//     */
//    @PostMapping(value = "/server/article/bigDeleteNotice")
//    public WebResult bigDeleteNotice(@RequestParam Integer [] ids) {
//        return eduArticleService.bigDeleteNotice(ids);
//    }

}
