package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.EduCheck;
import com.ovft.configure.sys.bean.EduCheckExample;
import java.util.List;

import com.ovft.configure.sys.vo.EduCheckVo;
import org.apache.ibatis.annotations.Param;

public interface EduCheckMapper {
    long countByExample(EduCheckExample example);

    int deleteByExample(EduCheckExample example);

    int deleteByPrimaryKey(Integer checkId);

    int insert(EduCheck record);

    /**
     * 根据userId打卡
     * @param record
     * @return
     */
    int insertSelective(EduCheck record);

    List<EduCheck> selectByExample(EduCheckExample example);

    EduCheck selectByPrimaryKey(Integer checkId);

    int updateByExampleSelective(@Param("record") EduCheck record, @Param("example") EduCheckExample example);

    int updateByExample(@Param("record") EduCheck record, @Param("example") EduCheckExample example);

    int updateByPrimaryKeySelective(EduCheck record);

    int updateByPrimaryKey(EduCheck record);

    /**
     * 根据userId查询打卡记录
     * @param userId
     * @return
     */
    List<EduCheckVo> queryAllPunchRecord(Integer userId);


}