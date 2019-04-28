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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
    public WebResult queryAllNotice(Integer schoolId, String type) {
        //查询：1-通知公告, 3-校园介绍, 4-联盟资讯, 5-政策法规
        //可以看见所有学校联盟资讯
        if("4".equals(type)) {
            schoolId = null;
        }
        List<EduArticleVo> noticeList =  eduArticleMapper.findNoticeAll(null, schoolId, type, null);
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
    public WebResult adminAddNotice(EduArticle eduArticle) {
        WebResult security = security(eduArticle);
        if(security != null) {
            return security;
        }
        if(eduArticle.getId() == null) {
            //校园介绍只能有一个   需求修改,能添加多条校园介绍
            /*if("3".equals(eduArticle.getType())) {
                List<EduArticleVo> noticeAll = eduArticleMapper.findNoticeAll(null, eduArticle.getSchoolId(), eduArticle.getType(), null);
                if(noticeAll != null && noticeAll.size() != 0) {
                    return new WebResult("400", "当前学校只能添加一个校园介绍", "");
                }
            }*/
            eduArticle.setCreatetime(new Date());
            eduArticle.setUpdatetime(new Date());
            eduArticle.setVisits(0);
            eduArticle.setThumbup(0);
            eduArticle.setComment(0);
            eduArticleMapper.insert(eduArticle);
            return new WebResult("200", "添加成功", "");
        } else {
            eduArticle.setUpdatetime(new Date());
            eduArticleMapper.updateByPrimaryKeySelective(eduArticle);
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
        if(noticeList==null && noticeList.size() == 0) {
            return new WebResult("200", "查询成功", "");
        }
        return new WebResult("200", "查询成功", noticeList.get(0));
    }

    @Transactional
    @Override
    public WebResult queryNoticeById(Integer id) {
        List<EduArticleVo> noticeList =  eduArticleMapper.findNoticeAll(id, null, null, null);
        if(noticeList==null && noticeList.size() == 0) {
            return new WebResult("200", "查询成功", "");
        }
        EduArticle article = noticeList.get(0);
        article.setVisits(article.getVisits() + 1);
        eduArticleMapper.updateVisites(article.getId(), article.getVisits());
        return new WebResult("200", "查询成功", noticeList.get(0));
    }

}
