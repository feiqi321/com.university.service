package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.EduNewstype;
import com.ovft.configure.sys.bean.EduNewstypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduNewstypeMapper {
    long countByExample(EduNewstypeExample example);

    int deleteByExample(EduNewstypeExample example);

    int deleteByPrimaryKey(Integer typeid);

    int insert(EduNewstype record);

    int insertSelective(EduNewstype record);

    List<EduNewstype> selectByExample(EduNewstypeExample example);

    EduNewstype selectByPrimaryKey(Integer typeid);

    int updateByExampleSelective(@Param("record") EduNewstype record, @Param("example") EduNewstypeExample example);

    int updateByExample(@Param("record") EduNewstype record, @Param("example") EduNewstypeExample example);

    int updateByPrimaryKeySelective(EduNewstype record);

    int updateByPrimaryKey(EduNewstype record);
}