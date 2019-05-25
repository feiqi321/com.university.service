package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.EduOfflineOrderitem;
import com.ovft.configure.sys.bean.EduOfflineOrderitemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduOfflineOrderitemMapper {
    long countByExample(EduOfflineOrderitemExample example);

    int deleteByExample(EduOfflineOrderitemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EduOfflineOrderitem record);

    int insertSelective(EduOfflineOrderitem record);

    List<EduOfflineOrderitem> selectByExample(EduOfflineOrderitemExample example);

    EduOfflineOrderitem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EduOfflineOrderitem record, @Param("example") EduOfflineOrderitemExample example);

    int updateByExample(@Param("record") EduOfflineOrderitem record, @Param("example") EduOfflineOrderitemExample example);

    int updateByPrimaryKeySelective(EduOfflineOrderitem record);

    int updateByPrimaryKey(EduOfflineOrderitem record);
}