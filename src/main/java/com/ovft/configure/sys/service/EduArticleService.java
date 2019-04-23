package com.ovft.configure.sys.service;

import com.ovft.configure.sys.bean.EduArticle;

import java.util.List;

/**
 * @author vvtxw
 * @create 2019-04-21 19:58
 */
public interface EduArticleService {
    /**
     * 展示通知公告
     *
     * @param type
     * @return
     */
    public List<EduArticle> queryAllNotice(String type);




}
