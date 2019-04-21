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

    /**
     * 展示投稿专区
     *
     * @param type
     * @return
     */
    public List<EduArticle> queryAllSubmit(String type);

    /**
     * 校园介绍
     *
     * @param type
     * @return
     */
    public List<EduArticle> queryAllIntroduce(String type);

    /**
     * 法规
     *
     * @param type
     * @return
     */
    public List<EduArticle> queryAllLaws(String type);

    /**
     * 联盟资讯
     *
     * @param type
     * @return
     */
    public List<EduArticle> queryAllNews(String type);


}
