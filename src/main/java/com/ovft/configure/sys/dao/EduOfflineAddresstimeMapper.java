package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.EduOfflineAddresstime;
import com.ovft.configure.sys.bean.EduOfflineAddresstimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduOfflineAddresstimeMapper {
    long countByExample(EduOfflineAddresstimeExample example);

    int deleteByExample(EduOfflineAddresstimeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EduOfflineAddresstime record);

    int insertSelective(EduOfflineAddresstime record);

    List<EduOfflineAddresstime> selectByExample(EduOfflineAddresstimeExample example);

    EduOfflineAddresstime selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EduOfflineAddresstime record, @Param("example") EduOfflineAddresstimeExample example);

    int updateByExample(@Param("record") EduOfflineAddresstime record, @Param("example") EduOfflineAddresstimeExample example);

    int updateByPrimaryKeySelective(EduOfflineAddresstime record);

    int updateByPrimaryKey(EduOfflineAddresstime record);
}