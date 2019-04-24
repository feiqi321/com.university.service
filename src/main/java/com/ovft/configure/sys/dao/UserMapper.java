package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName UserMapper
 * @Author xzy
 * @Date 2019/4/10 16:04
 * @Version 1.0
 **/
@Mapper
public interface UserMapper {
    //用户注册
    public void addUser(@Param("user") User user);

    //通过电话号码查找用户1
    public User findUserByPhone(@Param("user") User user);

    //通过电话号码查找用户2
    public User findUserByPhone2(@Param("phone") String phone);

    //通过密码查找用户
    public User findUserByPassword(@Param("user") User user);

    //修改密码
    public void updateByPhone(@Param("phone") String phone, @Param("password") String password);

    //保存基本信息
    public void savaInfo(@Param("user") User user);

    //更换手机
    public void updatePhone(@Param("phone") String phone, @Param("newPhone") String newPhone);

    //根据id查找用户
    public User selectById(@Param("userId") Integer userId);

    //根据原密码查找用户
    public User selectByOldPass(@Param("oldPass") String oldPass);

    //修改密码
    public void updateByOldPass(@Param("newPass") String newPass, @Param("oldPass") String oldPass);

    //查询用户所对应的学员分类-vvtxw
    public String queryemployerByUserId(Integer userId);

    //查询对应学员
    public User queryByUserIdAndSchoolId(Integer userId, Integer schoolId);

    //查询对应学员是否已选择该学校
    public User queryByItemsIdAndSchoolId(Integer userId, Integer schoolId);

    //更换学校时保存信息
    public void savaInfoItems(@Param("user") User user);

    //增加更换学校时信息
    public void addInfoItems(@Param("user") User user);

    //查找头像地址
    public String queryAllAddress(Integer userId);

    //上传头像
    public void upadateAddress(String address, Integer userId);


}
