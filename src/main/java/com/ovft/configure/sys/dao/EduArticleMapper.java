package com.ovft.configure.sys.dao;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduArticle;
import com.ovft.configure.sys.bean.EduArticleExample;

import java.util.List;

import com.ovft.configure.sys.vo.PageVo;
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
    /**
     * 后台添加文章
     *
     * @param eduArticle
     * @return
     */
    public void adminAddNotice(EduArticle eduArticle);
    /**
     * 后台条件查询文章
     *
     * @param type
     * @return
     */
    public List<EduArticle> findNoticeAll(@Param("type")String type, @Param("search") String search);
    /**
     * 后台删除文章
     *
     * @param id
     * @return
     */
    public void deleteNotice(Integer id);
    /**
     * 后台批量删除文章
     *
     * @param ids
     * @return
     */
    public void bigDeleteNotice(Integer [] ids);
    /**
     * 后台修改文章
     *
     * @param eduArticle
     * @return
     */
    public void updateNotice(EduArticle eduArticle);


}