package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.Admin;
import com.ovft.configure.sys.bean.School;
import com.ovft.configure.sys.vo.AdminVo;
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

    /*public Admin selectById(@Param("adminId") Integer adminId, @Param("schoolId") Integer schoolId);*/

    public Admin selectByPhone(@Param("phone") String phone);

    public List<School> selectByPhoneList(@Param("phone") String phone);

    /**
     * 多表查询， 查询当前学校的admin
     * @param adminId
     * @param schoolId
     * @param role
     * @return
     */
    public List<AdminVo> selectByAdminAndSchool(@Param("adminId") Integer adminId, @Param("schoolId") Integer schoolId, @Param("role") Integer role);

    //查找管理员列表
    public List<AdminVo> selectByAdminList(@Param("adminId") Integer adminId, @Param("schoolId") Integer schoolId, @Param("role") Integer role);

    public void creatAdmin(Admin admin);

    /**
     * 添加admin子表
     * @param admin
     */
    public void createAdminSchool(AdminVo admin);

    public void updateByPassword(@Param("adminId") Integer adminId, @Param("password") String password);

    public void updateByPrimary(Admin admin);

    public void updateAdminSchool(AdminVo admin);

    public void deleteById(@Param("adminId") Integer adminId);

    /**
     *  删除admin子表
     */
    public void deleteAdminSchool(@Param("adminId") Integer adminId, @Param("schoolId") Integer schoolId);
}
