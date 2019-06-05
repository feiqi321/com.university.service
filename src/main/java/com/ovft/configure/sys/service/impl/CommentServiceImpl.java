package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduComment;
import com.ovft.configure.sys.dao.EduCommentMapper;
import com.ovft.configure.sys.service.CommentService;
import com.ovft.configure.sys.vo.CommentVo;
import com.ovft.configure.sys.vo.PageComVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName CommentServiceImpl
 * @Author zqx
 * @Version 1.0
 **/
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private EduCommentMapper commentMapper;

    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Transactional
    @Override
    public WebResult addComment(EduComment comment) {
        List<CommentVo> eduComments = commentMapper.selectList(comment.getUid(), comment.getNewtype(), comment.getNewsid());
        if(eduComments.size() != 0) {
            return new WebResult("400", "您已评论", "");
        }
       if(StringUtils.isBlank(comment.getContext())) {
           return new WebResult("400", "评论内容不能为空", "");
       }
       if(comment.getNewsid() == null || comment.getNewtype() == null) {
           return new WebResult("400", "评论失败", "");
       }
       if(comment.getStart() == null ) {
           return new WebResult("400", "请选择星级", "");
       }
       comment.setComtime(new Date());
        comment.setIsshow(1);
        comment.setParentid(0);
        commentMapper.insert(comment);
        return new WebResult("200", "评论成功", "");
    }

    @Override
    public WebResult commentList(PageComVo pageComVo) {
        if (pageComVo.getPageSize() == 0) {
            List<CommentVo> eduComments = commentMapper.selectList(null, pageComVo.getNewtype(), pageComVo.getNewsid());
            return new WebResult("200", "查询成功", eduComments);
        }
        PageHelper.startPage(pageComVo.getPageNum(), pageComVo.getPageSize());
        List<CommentVo> eduComments = commentMapper.selectList(null, pageComVo.getNewtype(), pageComVo.getNewsid());
        PageInfo pageInfo = new PageInfo<>(eduComments);
        return new WebResult("200", "查询成功", pageInfo);
    }

    @Transactional
    @Override
    public WebResult deleteComment(Integer comid) {
        commentMapper.deleteByPrimaryKey(comid);
        return new WebResult("200", "删除成功", "");
    }

}
