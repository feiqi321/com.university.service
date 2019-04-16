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
        public void updateByPassword(@Param("phone") String phone, @Param("password") String password);
        //保存基本信息
        public void savaInfo(@Param("user") User user);
}
