package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.EduComment;
import com.ovft.configure.sys.bean.EduCommentExample;

import java.util.List;

public interface EduCommentMapper {

    int deleteByPrimaryKey(Integer comid);

    int insert(EduComment record);

    List<EduComment> selectByExample(EduCommentExample example);

    EduComment selectByPrimaryKey(Integer comid);

    int updateByPrimaryKeySelective(EduComment record);

}