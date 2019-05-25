package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.EduOfflineOrder;
import com.ovft.configure.sys.bean.EduOfflineOrderExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface EduOfflineOrderMapper {
    long countByExample(EduOfflineOrderExample example);

    int deleteByExample(EduOfflineOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EduOfflineOrder record);

    int insertSelective(EduOfflineOrder record);

    List<EduOfflineOrder> selectByExample(EduOfflineOrderExample example);

    EduOfflineOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EduOfflineOrder record, @Param("example") EduOfflineOrderExample example);

    int updateByExample(@Param("record") EduOfflineOrder record, @Param("example") EduOfflineOrderExample example);

    int updateByPrimaryKeySelective(EduOfflineOrder record);

    int updateByPrimaryKey(EduOfflineOrder record);

    //查询线下支付记录
    List<EduOfflineOrder> queryAllOffRecord(Integer userId);
}