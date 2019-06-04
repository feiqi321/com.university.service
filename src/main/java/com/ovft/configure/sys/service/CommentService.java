package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduComment;
import com.ovft.configure.sys.vo.CommentVo;

public interface CommentService {

    //评论
    public WebResult addComment(EduComment comment);

    //评论列表
    public WebResult commentList(CommentVo commentVo);

    //删除评论
    public WebResult deleteComment(Integer comid);
}
