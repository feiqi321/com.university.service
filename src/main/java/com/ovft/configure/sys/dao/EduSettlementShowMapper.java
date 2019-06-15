package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.EduSettlementShow;
import com.ovft.configure.sys.bean.EduSettlementShowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduSettlementShowMapper {
    long countByExample(EduSettlementShowExample example);

    int deleteByExample(EduSettlementShowExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EduSettlementShow record);

    int insertSelective(EduSettlementShow record);

    List<EduSettlementShow> selectByExample(EduSettlementShowExample example);

    EduSettlementShow selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EduSettlementShow record, @Param("example") EduSettlementShowExample example);

    int updateByExample(@Param("record") EduSettlementShow record, @Param("example") EduSettlementShowExample example);

    int updateByPrimaryKeySelective(EduSettlementShow record);

    int updateByPrimaryKey(EduSettlementShow record);
}