package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.EduVersion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author vvtxw
 * @create 2019-08-28 17:16
 */
@Mapper
public interface EduVersionMapper {
   //增加版本
    public int insertVersion( EduVersion eduVersion);
   //查看版本
    EduVersion selectVersion(Integer id);



}
