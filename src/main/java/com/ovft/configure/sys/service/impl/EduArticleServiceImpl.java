package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduArticle;
import com.ovft.configure.sys.bean.EduArticleExample;
import com.ovft.configure.sys.bean.EduCourse;
import com.ovft.configure.sys.dao.EduArticleMapper;
import com.ovft.configure.sys.service.EduArticleService;
import com.ovft.configure.sys.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author vvtxw
 * @create 2019-04-21 20:01
 */
@Service
public class EduArticleServiceImpl implements EduArticleService {

    @Resource
    private EduArticleMapper eduArticleMapper;

    //通知公告

    @Override
    public List<EduArticle> queryAllNotice(String type) {
        List<EduArticle> eduArticles = eduArticleMapper.queryAllNotice(type);
        return eduArticles;
    }
    //后台添加文章
    @Transactional
    @Override
    public WebResult adminAddNotice(EduArticle eduArticle) {
              eduArticleMapper.adminAddNotice(eduArticle);
            return new WebResult("200", "添加成功");
    }
    //后台文章列表显示（条件查询）
    @Override
    public WebResult findNoticeAll(String type, PageVo pageVo) {

        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize(), "start_date");
        List<EduArticle> noticeList =  eduArticleMapper.findNoticeAll(type,pageVo.getSearch());
        PageInfo pageInfo = new PageInfo<>(noticeList);
        return new WebResult("200", "分页查询成功");
    }
    //后台删除文章
    @Transactional
    @Override
    public WebResult deleteNotice(Integer id) {
        eduArticleMapper.deleteNotice(id);
          return new WebResult("200","删除成功");
    }
    //后台批量删除文章
    @Transactional
    @Override
    public WebResult bigDeleteNotice(Integer[] ids) {
        eduArticleMapper.bigDeleteNotice(ids);
        return new WebResult("200","批量删除成功");
    }

    //后台修改文章
    @Transactional
    @Override
    public WebResult updateNotice(EduArticle eduArticle) {
        eduArticleMapper.updateNotice(eduArticle);
        return new WebResult("200","修改成功");
    }

}
