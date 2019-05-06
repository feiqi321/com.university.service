package com.ovft.configure.sys.dao;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.User;
import com.ovft.configure.sys.vo.EduCourseVo;
import com.ovft.configure.sys.vo.WithdrawVo;
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

    //根据id查找全部用户信息
    public User selectByIdAll(@Param("userId") Integer userId);

    //查询对应学员是否已选择该学校
    public User queryByItemsIdAndSchoolId(@Param("userId") Integer userId, @Param("schoolId") Integer schoolId);
    //查询对应学员是否已选择该学校
    public User queryByItemsId(@Param("userId") Integer userId);

    //更换学校时保存信息
    public void updateInfoItems(User user);

    //增加更换学校时信息
    public void saveInfoItems(User user);

    //查询用户所对应的学员分类-vvtxw
    public String queryemployerByUserId(Integer userId);

    //查找头像地址-vvtxw
    public String queryAllAddress(Integer userId);

    //上传头像-vvtxw
    public void updateAddress(@Param("user") User user);

    //添加个性签名
    public void createMycontext(@Param("user") User user);

    //查找课程的详细信息
    public EduCourseVo queryCourseByCourseId(Integer courseId);

    //用户注销申请
    public void addWithdraw(@Param("withdrawVo") WithdrawVo withdrawVo);

    //获取用户注销申请结果状态
    public int selectWithdraw(Integer uid);
    //获取用户注销申请结果状态
    public WithdrawVo selectWithdrawOne(Integer uid);

    //后台删除用户
    public  void deleteUser(Integer userId);
}
