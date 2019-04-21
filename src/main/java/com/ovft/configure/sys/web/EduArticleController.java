package com.ovft.configure.sys.web;

import com.ovft.configure.sys.bean.EduArticle;
import com.ovft.configure.sys.service.EduArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping(value = "notice")
    public List<EduArticle> queryAllNotice(String type) {
        return eduArticleService.queryAllNotice(type);
    }

    /**
     * 投稿专区
     *
     * @param type
     * @return
     */
    @PostMapping(value = "submit")
    public List<EduArticle> queryAllSubmit(String type) {
        return eduArticleService.queryAllSubmit(type);
    }

    /**
     * 投稿专区
     *
     * @param type
     * @return
     */
    @PostMapping(value = "introduce")
    public List<EduArticle> queryAllIntroduce(String type) {
        return eduArticleService.queryAllIntroduce(type);
    }

    /**
     * 投稿专区
     *
     * @param type
     * @return
     */
    @PostMapping(value = "laws")
    public List<EduArticle> queryAllLaws(String type) {
        return eduArticleService.queryAllLaws(type);
    }

    /**
     * 投稿专区
     *
     * @param type
     * @return
     */
    @PostMapping(value = "news")
    public List<EduArticle> queryAllNews(String type) {
        return eduArticleService.queryAllNews(type);
    }


}
