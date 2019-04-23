package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduArticle;
import com.ovft.configure.sys.service.EduArticleService;
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


}
