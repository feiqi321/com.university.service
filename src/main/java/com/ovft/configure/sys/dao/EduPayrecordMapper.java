package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.EduPayrecord;
import com.ovft.configure.sys.bean.EduPayrecordExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface EduPayrecordMapper {
    long countByExample(EduPayrecordExample example);

    int deleteByExample(EduPayrecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EduPayrecord record);

    int insertSelective(EduPayrecord record);

    List<EduPayrecord> selectByExample(EduPayrecordExample example);

    EduPayrecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EduPayrecord record, @Param("example") EduPayrecordExample example);

    int updateByExample(@Param("record") EduPayrecord record, @Param("example") EduPayrecordExample example);

    int updateByPrimaryKeySelective(EduPayrecord record);

    int updateByPrimaryKey(EduPayrecord record);
    //更新支付记录状态
    int updateByPrimaryKey2(EduPayrecord record);

    //查询线上的报名门数
    Integer queryCourseNum(Integer userId);

    public int updateIsDeleteStaues(EduPayrecord eduPayrecord);
     List<EduPayrecord> selectByUserIdAndIsdelete(EduPayrecord eduPayrecord);
}