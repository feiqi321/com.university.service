package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName AdminMapper
 * @Author zqx
 * @Date 2019/4/10 16:04
 * @Version 1.0
 **/
@Mapper
public interface AdminMapper {

    public Admin selectById(@Param("adminId") Integer adminId);
    public void creatAdmin(@Param("admin") Admin admin);

}
