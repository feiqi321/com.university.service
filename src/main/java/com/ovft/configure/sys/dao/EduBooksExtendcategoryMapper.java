package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.EduBooksCategory;
import com.ovft.configure.sys.bean.EduBooksExtendcategory;
import com.ovft.configure.sys.bean.EduBooksExtendcategoryExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface EduBooksExtendcategoryMapper {
    long countByExample(EduBooksExtendcategoryExample example);

    int deleteByExample(EduBooksExtendcategoryExample example);

    int deleteByPrimaryKey(Short id);

    int insert(EduBooksExtendcategory record);

    int insertSelective(EduBooksExtendcategory record);

    List<EduBooksExtendcategory> selectByExample(EduBooksExtendcategoryExample example);

    EduBooksExtendcategory selectByPrimaryKey(Short id);

    int updateByExampleSelective(@Param("record") EduBooksExtendcategory record, @Param("example") EduBooksExtendcategoryExample example);

    int updateByExample(@Param("record") EduBooksExtendcategory record, @Param("example") EduBooksExtendcategoryExample example);

    int updateByPrimaryKeySelective(EduBooksExtendcategory record);

    int updateByPrimaryKey(EduBooksExtendcategory record);

    //查询所有的教材分类
    List<EduBooksExtendcategory> queryAllExtendcategory();

}