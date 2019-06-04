package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.EduComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EduCommentMapper {

    int deleteByPrimaryKey(Integer comid);

    int insert(EduComment record);

    List<EduComment> selectList(@Param("uid") Integer uid, @Param("newtype") Integer newtype, @Param("newsid") Integer newsid);

    EduComment selectByPrimaryKey(Integer comid);

    int updateByPrimaryKeySelective(EduComment record);

}