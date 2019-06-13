package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.EduBooksInfo;
import com.ovft.configure.sys.bean.EduBooksInfoExample;

import java.util.List;
import java.util.Map;

import com.ovft.configure.sys.vo.EduBooksInfoVo;
import org.apache.ibatis.annotations.Param;

public interface EduBooksInfoMapper {
    long countByExample(EduBooksInfoExample example);

    int deleteByExample(EduBooksInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EduBooksInfo record);

    int insertSelective(EduBooksInfo record);

    List<EduBooksInfo> selectByExample(EduBooksInfoExample example);

    EduBooksInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EduBooksInfo record, @Param("example") EduBooksInfoExample example);

    int updateByExample(@Param("record") EduBooksInfo record, @Param("example") EduBooksInfoExample example);

    int updateByPrimaryKeySelective(EduBooksInfo record);

    int updateByPrimaryKey(EduBooksInfo record);

    //查询详情
    EduBooksInfoVo selectBookById(Integer id);

    //分页查询教材详情信息
    List<EduBooksInfo> queryForPage(Map<String, Object> map);


}