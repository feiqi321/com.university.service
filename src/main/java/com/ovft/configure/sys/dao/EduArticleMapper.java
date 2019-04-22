package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.EduArticle;
import com.ovft.configure.sys.bean.EduArticleExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface EduArticleMapper {
    long countByExample(EduArticleExample example);

    int deleteByExample(EduArticleExample example);

    int deleteByPrimaryKey(String id);

    int insert(EduArticle record);

    int insertSelective(EduArticle record);

    List<EduArticle> selectByExampleWithBLOBs(EduArticleExample example);

    List<EduArticle> selectByExample(EduArticleExample example);

    EduArticle selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EduArticle record, @Param("example") EduArticleExample example);

    int updateByExampleWithBLOBs(@Param("record") EduArticle record, @Param("example") EduArticleExample example);

    int updateByExample(@Param("record") EduArticle record, @Param("example") EduArticleExample example);

    int updateByPrimaryKeySelective(EduArticle record);

    int updateByPrimaryKeyWithBLOBs(EduArticle record);

    int updateByPrimaryKey(EduArticle record);

    /**
     * 展示通知公告
     *
     * @param type
     * @return
     */
    public List<EduArticle> queryAllNotice(String type);


}