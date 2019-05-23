package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduArticle;
import com.ovft.configure.sys.dao.EduArticleMapper;
import com.ovft.configure.sys.dao.WorksShowMapper;
import com.ovft.configure.sys.service.WorksShowService;
import com.ovft.configure.sys.vo.EduArticleVo;
import com.ovft.configure.sys.vo.PageVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WorksShowServiceImpl implements WorksShowService {
    @Resource
    private WorksShowMapper worksShowMapper;
    @Resource
    private EduArticleMapper eduArticleMapper;
    //学员展示列表及模糊查询
    @Override
    public WebResult findUserShowAll(PageVo pageVo) {
        if(pageVo.getPageSize() == 0) {
            List<EduArticleVo> noticeList =  worksShowMapper.findUserShowAll(null, pageVo.getSchoolId(), pageVo.getType(),pageVo.getSearch());
            return new WebResult("200", "查询成功", noticeList);
        }
        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());
        List<EduArticleVo> noticeList =  worksShowMapper.findUserShowAll(null, pageVo.getSchoolId(), pageVo.getType(), pageVo.getSearch());
        PageInfo pageInfo = new PageInfo<>(noticeList);
        return new WebResult("200", "查询成功", pageInfo);
    }
    @Transactional
    @Override
    public Integer addThumbup(Integer id) {
        EduArticle eduArticle = eduArticleMapper.selectAriticleById(id);
        eduArticle.setThumbup(eduArticle.getThumbup()+1);
        eduArticleMapper.updateNoticeThumbup(eduArticle.getId(),eduArticle.getThumbup());
        return eduArticle.getThumbup();
    }

    @Override
    public Integer addTeacherThumbup(Integer id) {
        EduArticle eduArticle = eduArticleMapper.selectAriticleById(id);
        eduArticle.setThumbup(eduArticle.getThumbup()+1);
        eduArticleMapper.updateNoticeThumbup(eduArticle.getId(),eduArticle.getThumbup());
        return eduArticle.getThumbup();
    }
}
