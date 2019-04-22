package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName AdminMapper
 * @Author zqx
 * @Date 2019/4/10 16:04
 * @Version 1.0
 **/
@Mapper
public interface AdminMapper {

    public Admin selectById(@Param("adminId") Integer adminId);

    public Admin selectByPhone(@Param("phone") String phone);

    public List<Admin> selectTeacherBySchool(@Param("schoolId") Integer schoolId);

    public void creatAdmin(Admin admin);

    public void updateByPassword(@Param("adminId") Integer adminId, @Param("password") String password);

    public void updateByPrimary(Admin admin);

    public void deleteById(@Param("adminId") Integer adminId);
}
