package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.EduArticle;
import com.ovft.configure.sys.bean.EduArticleExample;
import com.ovft.configure.sys.vo.EduArticleVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface EduArticleMapper {
    long countByExample(EduArticleExample example);

    int deleteByExample(EduArticleExample example);

    int deleteByPrimaryKey(Integer id);

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
     * 后台条件查询文章
     *
     * @param type
     * @return
     */
    public List<EduArticleVo> findNoticeAll(@Param("id")Integer id, @Param("schoolId")Integer schoolId, @Param("type")String type, @Param("search") String search);
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
    /**
     * 后台修改文章点赞数
     *
     * @param id,thumbup
     * @return
     */
    public void updateNoticeThumbup(@Param("id") Integer id, @Param("thumbup")Integer thumbup);

    /**
     * 浏览量加 1
     * @param id
     * @param visits
     */
    public void updateVisites(@Param("id") Integer id, @Param("visits")Integer visits);

    /**
     * 所有置顶文章
     * @return
     */
    public List<EduArticle> selectIsTop();

    /**
     * 修改是否置顶
     * @param id
     * @param istop
     */
    public void updateIsTop(@Param("id") Integer id, @Param("istop")String istop, @Param("topdate")Date topdate);

    /**
     * 查询对应文章
     * @param id
     *
     */
     public  EduArticle  selectAriticleById(@Param("id") Integer id);
}