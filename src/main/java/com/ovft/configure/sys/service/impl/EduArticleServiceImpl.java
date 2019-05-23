package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduArticle;
import com.ovft.configure.sys.dao.EduArticleMapper;
import com.ovft.configure.sys.service.EduArticleService;
import com.ovft.configure.sys.vo.EduArticleVo;
import com.ovft.configure.sys.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public WebResult queryAllNotice(PageVo pageVo) {
        //查询：1-通知公告, 3-校园介绍, 4-联盟资讯, 5-政策法规
        //可以看见所有学校联盟资讯
        Integer schoolId = null;
        String type = pageVo.getType();
        if("4".equals(type) || "5".equals(type)) {
            schoolId = null;
        } else {
            schoolId = pageVo.getSchoolId();
        }

        if(pageVo.getPageNum() == 0) {
            List<EduArticleVo> noticeList =  eduArticleMapper.findNoticeAll(null, schoolId, type, null);
            return new WebResult("200", "查询成功", noticeList);
        }
        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());
        List<EduArticleVo> noticeList =  eduArticleMapper.findNoticeAll(null, schoolId, type, null);
        PageInfo pageInfo = new PageInfo<>(noticeList);
        return new WebResult("200", "查询成功", noticeList);
    }

    private WebResult security(EduArticle eduArticle) {
        if(eduArticle.getSchoolId() == null) {
            return new WebResult("400", "请选择学校", "");
        }
        if(StringUtils.isBlank(eduArticle.getTitle())) {
            return new WebResult("400", "标题不能为空", "");
        }
        if(StringUtils.isBlank(eduArticle.getType())) {
            return new WebResult("400", "类型不能为空", "");
        }

        if(StringUtils.isBlank(eduArticle.getContent())) {
            return new WebResult("400", "文章正文不能为空", "");
        }
        //默认公开文章
        if(StringUtils.isBlank(eduArticle.getIspublic())) {
            eduArticle.setIspublic("1");
        }
        //默认不置顶文章
        if(StringUtils.isBlank(eduArticle.getIstop())) {
            eduArticle.setIspublic("0");
        }
        return null;
    }




    /**
     * 后台添加、修改文章
     * @param eduArticle
     * @return
     */
    @Transactional
    @Override
    public WebResult adminAddNotice(EduArticle eduArticle, Integer role) {
        WebResult security = security(eduArticle);
        if(security != null) {
            return security;
        }
        if(eduArticle.getIstop().equals("0")) {
            eduArticle.setTopdate(null);
        }
        //置顶时间在当前日期之前, 文章置顶
        if(eduArticle.getTopdate() != null && eduArticle.getTopdate().before(new Date())) {
            eduArticle.setIstop("1");
        } else {
            eduArticle.setIstop("0");
        }
        if(eduArticle.getTopdate() != null && eduArticle.getTopenddate() == null) {
            return new WebResult("400", "置顶时间不能为空", "");
        }
        String[] types = eduArticle.getType().split(",");
        for (String type : types) {
            //非超级管理员的联盟资讯置顶
            if("4".equals(type) && role != 0 && eduArticle.getTopdate() != null) {
                return new WebResult("400", "联盟资讯仅超级管理员可置顶", "");
            }
        }
        if(eduArticle.getId() == null) {
            //校园介绍只能有一个   需求修改,能添加多条校园介绍
            /*if("3".equals(eduArticle.getType())) {
                List<EduArticleVo> noticeAll = eduArticleMapper.findNoticeAll(null, eduArticle.getSchoolId(), eduArticle.getType(), null);
                if(noticeAll != null && noticeAll.size() != 0) {
                    return new WebResult("400", "当前学校已有校园介绍", "");
                }
            }*/
            for (String type : types) {
                eduArticle.setType(type);
                eduArticle.setCreatetime(new Date());
                eduArticle.setUpdatetime(new Date());
                eduArticle.setIspublic("0");
                eduArticle.setVisits(0);
                eduArticle.setThumbup(0);
                eduArticle.setComment(0);
                eduArticleMapper.insert(eduArticle);
            }
            return new WebResult("200", "添加成功", "");
        } else {
            for (String type : types) {
                eduArticle.setType(type);
                eduArticle.setUpdatetime(new Date());
                eduArticleMapper.updateByPrimaryKeySelective(eduArticle);
            }
            return new WebResult("200", "修改成功", "");

        }
    }


    //后台文章列表显示（条件查询）
    @Override
    public WebResult findNoticeAll(PageVo pageVo) {
        if(pageVo.getPageSize() == 0) {
            List<EduArticleVo> noticeList =  eduArticleMapper.findNoticeAll(null, pageVo.getSchoolId(), pageVo.getType(),pageVo.getSearch());
            return new WebResult("200", "查询成功", noticeList);
        }
        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());
        List<EduArticleVo> noticeList =  eduArticleMapper.findNoticeAll(null, pageVo.getSchoolId(), pageVo.getType(), pageVo.getSearch());
        PageInfo pageInfo = new PageInfo<>(noticeList);
        return new WebResult("200", "查询成功", pageInfo);
    }

    //后台删除文章
    @Transactional
    @Override
    public WebResult deleteNotice(Integer id) {
        eduArticleMapper.deleteByPrimaryKey(id);
          return new WebResult("200","删除成功", "");
    }

    //后台批量删除文章
    @Transactional
    @Override
    public WebResult bigDeleteNotice(Integer[] ids) {
        eduArticleMapper.bigDeleteNotice(ids);
        return new WebResult("200","批量删除成功");
    }

    /**
     * 进入文章修改页面
     *
     * @param id
     * @return
     */
    @Override
    public WebResult findNotice(Integer id) {
        List<EduArticleVo> noticeList =  eduArticleMapper.findNoticeAll(id, null, null, null);
        if(noticeList==null || noticeList.size() == 0) {
            return new WebResult("200", "查询成功", "");
        }
        EduArticleVo eduArticleVo = noticeList.get(0);
        if(eduArticleVo.getTopdate() != null) {
            eduArticleVo.setIstop("1");
        }
        return new WebResult("200", "查询成功", eduArticleVo);
    }

    @Transactional
    @Override
    public WebResult queryNoticeById(Integer id) {
        List<EduArticleVo> noticeList =  eduArticleMapper.findNoticeAll(id, null, null, null);
        if(noticeList==null || noticeList.size() == 0) {
            return new WebResult("200", "查询成功", "");
        }
        //浏览量加1
        EduArticle article = noticeList.get(0);
        article.setVisits(article.getVisits() + 1);
        eduArticleMapper.updateVisites(article.getId(), article.getVisits());
        return new WebResult("200", "查询成功", noticeList.get(0));
    }

    private static final Logger logger = LoggerFactory.getLogger(EduArticleServiceImpl.class);

    @Transactional
    @Override
    public void topScheduleTask() {
        List<EduArticle> eduArticles = eduArticleMapper.selectIsTop();
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        for (EduArticle eduArticle : eduArticles) {
            Date topdate = eduArticle.getTopdate();
            Date topenddate = eduArticle.getTopenddate();

            //置顶开始时间 < 今天 < 置顶结束时间       -> 文章置顶
            if(now.after(topdate) && now.before(topenddate) && eduArticle.getIstop().equals("0")) {
                logger.info("置顶定时任务1: topdate: "+ sdf.format(topdate) + ", eduArticel.id=" + eduArticle.getId());
                eduArticleMapper.updateIsTop(eduArticle.getId(), "1", topdate);
            }
            //置顶结束时间 < 今天日期      -> 置顶结束
            if(now.after(topenddate) && eduArticle.getIstop().equals("1")) {
                logger.info("置顶结束定时任务2: topdate: "+ sdf.format(topdate) + ", eduArticel.id=" + eduArticle.getId());
                eduArticleMapper.updateIsTop(eduArticle.getId(), "0", null);
            }
        }
    }


}
