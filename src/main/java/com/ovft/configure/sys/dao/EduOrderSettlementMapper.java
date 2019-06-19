package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.EduOrderSettlement;
import com.ovft.configure.sys.bean.EduOrderSettlementExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface EduOrderSettlementMapper {
    long countByExample(EduOrderSettlementExample example);

    int deleteByExample(EduOrderSettlementExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EduOrderSettlement record);

    int insertSelective(EduOrderSettlement record);

    List<EduOrderSettlement> selectByExample(EduOrderSettlementExample example);

    EduOrderSettlement selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EduOrderSettlement record, @Param("example") EduOrderSettlementExample example);

    int updateByExample(@Param("record") EduOrderSettlement record, @Param("example") EduOrderSettlementExample example);

    int updateByPrimaryKeySelective(EduOrderSettlement record);

    int updateByPrimaryKey(EduOrderSettlement record);

    //查询出结算状态的总数据
    List<EduOrderSettlement> queryAllSettlementStatus(Map<String, Object> map);
}