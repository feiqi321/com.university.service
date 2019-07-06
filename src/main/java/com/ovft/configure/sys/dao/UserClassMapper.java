package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.UserClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName UserClassMapper 学员班级
 * @Author xzy
 * @Version 2.5
 **/
public interface UserClassMapper {
      //班级列表
      public List<UserClass> userClassList();
      //添加班级记录
      public void addUserClass(UserClass userClass);
      //修改班级记录
      public void updateUserClass(UserClass userClass);
      //删除班级记录
      public void deleteUserClass(@Param("classId") Integer classId);
}
