package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduArticle;
import com.ovft.configure.sys.vo.PageVo;

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
    public WebResult queryAllNotice(Integer schoolId, String type);
    /**
     * 后台添加文章
     *
     * @param eduArticle
     * @return
     */
    public WebResult adminAddNotice(EduArticle eduArticle );
    /**
     * 后台查询所有文章
     *
     * @param pageVo
     * @return
     */
    public WebResult findNoticeAll(PageVo pageVo);
    /**
     *  后台删除文章
     *
     * @param id
     * @return
     */
    public WebResult deleteNotice(Integer id);
    /**
     *  后台批量删除文章
     *
     * @param ids
     * @return
     */
    public WebResult bigDeleteNotice(Integer [] ids);

    /**
     * 进入文章修改页面
     * @param id
     * @return
     */
    public WebResult findNotice(Integer id);
}
