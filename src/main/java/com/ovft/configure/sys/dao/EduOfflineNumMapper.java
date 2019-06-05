package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.EduOfflineNum;
import com.ovft.configure.sys.bean.EduOfflineNumExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduOfflineNumMapper {
    long countByExample(EduOfflineNumExample example);

    int deleteByExample(EduOfflineNumExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EduOfflineNum record);

    int insertSelective(EduOfflineNum record);

    List<EduOfflineNum> selectByExample(EduOfflineNumExample example);

    EduOfflineNum selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EduOfflineNum record, @Param("example") EduOfflineNumExample example);

    int updateByExample(@Param("record") EduOfflineNum record, @Param("example") EduOfflineNumExample example);

    int updateByPrimaryKeySelective(EduOfflineNum record);

    int updateByPrimaryKey(EduOfflineNum record);
}