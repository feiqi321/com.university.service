package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.EduComment;
import com.ovft.configure.sys.bean.EduCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduCommentMapper {
    long countByExample(EduCommentExample example);

    int deleteByExample(EduCommentExample example);

    int deleteByPrimaryKey(Integer comid);

    int insert(EduComment record);

    int insertSelective(EduComment record);

    List<EduComment> selectByExample(EduCommentExample example);

    EduComment selectByPrimaryKey(Integer comid);

    int updateByExampleSelective(@Param("record") EduComment record, @Param("example") EduCommentExample example);

    int updateByExample(@Param("record") EduComment record, @Param("example") EduCommentExample example);

    int updateByPrimaryKeySelective(EduComment record);

    int updateByPrimaryKey(EduComment record);
}