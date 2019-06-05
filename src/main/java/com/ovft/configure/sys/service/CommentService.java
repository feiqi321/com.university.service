package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduComment;
import com.ovft.configure.sys.vo.PageComVo;

public interface CommentService {

    //评论
    public WebResult addComment(EduComment comment);

    //评论列表
    public WebResult commentList(PageComVo pageComVo);

    //删除评论
    public WebResult deleteComment(Integer comid);
}
