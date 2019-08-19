package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.UserClass;
import com.ovft.configure.sys.vo.UserClassVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName UserClassMapper 学员班级
 * @Author xzy
 * @Version 2.5
 **/
@Mapper
public interface UserClassMapper {
      //班级列表
      public List<UserClass> userClassList(UserClassVo userClassVo);
      //班级所有ClassNo
      public List<UserClass> findClassNoAll(UserClass userClass);
      //添加班级记录
      public void addUserClass(UserClass userClass);
      //修改班级记录
      public void updateUserClass(UserClass userClass);
      //删除班级记录
      public void deleteUserClass(@Param("classId") Integer classId);
      //通过课程id删除班级记录
      public void deleteUserClassByCourseId(@Param("courseId") Integer courseId);
}
