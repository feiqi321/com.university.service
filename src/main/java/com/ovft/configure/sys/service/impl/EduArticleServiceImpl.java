package com.ovft.configure.sys.service.impl;

import com.ovft.configure.sys.bean.EduArticle;
import com.ovft.configure.sys.bean.EduArticleExample;
import com.ovft.configure.sys.dao.EduArticleMapper;
import com.ovft.configure.sys.service.EduArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        EduArticleExample eduArticleExample = new EduArticleExample();
        eduArticleExample.createCriteria().andTypeEqualTo(type);
        List<EduArticle> eduArticles = eduArticleMapper.selectByExample(eduArticleExample);
        return eduArticles;
    }

    //投稿专区
    @Override
    public List<EduArticle> queryAllSubmit(String type) {
        EduArticleExample eduArticleExample = new EduArticleExample();
        eduArticleExample.createCriteria().andTypeEqualTo(type);
        List<EduArticle> eduArticles = eduArticleMapper.selectByExample(eduArticleExample);
        return eduArticles;
    }

    @Override
    public List<EduArticle> queryAllIntroduce(String type) {
        EduArticleExample eduArticleExample = new EduArticleExample();
        eduArticleExample.createCriteria().andTypeEqualTo(type);
        List<EduArticle> eduArticles = eduArticleMapper.selectByExample(eduArticleExample);
        return eduArticles;
    }

    @Override
    public List<EduArticle> queryAllLaws(String type) {
        EduArticleExample eduArticleExample = new EduArticleExample();
        eduArticleExample.createCriteria().andTypeEqualTo(type);
        List<EduArticle> eduArticles = eduArticleMapper.selectByExample(eduArticleExample);
        return eduArticles;
    }

    @Override
    public List<EduArticle> queryAllNews(String type) {
        EduArticleExample eduArticleExample = new EduArticleExample();
        eduArticleExample.createCriteria().andTypeEqualTo(type);
        List<EduArticle> eduArticles = eduArticleMapper.selectByExample(eduArticleExample);
        return eduArticles;
    }
}
