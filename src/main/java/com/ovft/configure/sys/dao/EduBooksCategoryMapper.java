package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.EduBooksCategory;
import com.ovft.configure.sys.bean.EduBooksCategoryExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface EduBooksCategoryMapper {
    long countByExample(EduBooksCategoryExample example);

    int deleteByExample(EduBooksCategoryExample example);

    int deleteByPrimaryKey(Short id);

    int insert(EduBooksCategory record);

    int insertSelective(EduBooksCategory record);

    List<EduBooksCategory> selectByExample(EduBooksCategoryExample example);

    EduBooksCategory selectByPrimaryKey(Short id);

    int updateByExampleSelective(@Param("record") EduBooksCategory record, @Param("example") EduBooksCategoryExample example);

    int updateByExample(@Param("record") EduBooksCategory record, @Param("example") EduBooksCategoryExample example);

    int updateByPrimaryKeySelective(EduBooksCategory record);

    int updateByPrimaryKey(EduBooksCategory record);

    //查询所有的教材分类
    List<EduBooksCategory> queryAllCategory();
}