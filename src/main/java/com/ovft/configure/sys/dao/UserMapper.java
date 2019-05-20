package com.ovft.configure.sys.dao;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Contribute;
import com.ovft.configure.sys.bean.User;
import com.ovft.configure.sys.bean.UserItem;
import com.ovft.configure.sys.vo.EduCourseVo;
import com.ovft.configure.sys.vo.PageVo;
import com.ovft.configure.sys.vo.WithdrawVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName UserMapper
 * @Author xzy
 * @Date 2019/4/10 16:04
 * @Version 1.0
 **/
@Mapper
public interface UserMapper {
    //用户注册
    public void addUser(User user);

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

    //根据id查找edu_user（用户表1，即只包含帐号密码等信息）全部用户信息
    public User selectByIdAll(@Param("userId") Integer userId);

    //查询对应学员是否已选择该学校
    public User queryByItemsIdAndSchoolId(@Param("userId") Integer userId, @Param("schoolId") Integer schoolId);

    //查询对应学员是否已选择该学校（通过userId）
    public User queryByItemsId(@Param("userId") Integer userId);
    //  <!--查询对应edu_user_item表里面该用户记录(i.school_id is not null且i.school_id不为0)-->
    public User queryByItemsId2(@Param("userId") Integer userId);
    //查询对应学员是否已选择该学校
    public List<User> queryByItemsIdList(@Param("userId") Integer userId);

    //修改学校时保存信息
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

    //获取一个用户注销记录
    public WithdrawVo selectWithdrawOne(Integer uid);

    //后台删除用户
    public void deleteUserItem(@Param("userItemId") Integer userItemId);

    //后台删除用户注销申请记录
    public void deleteWithdraw(Integer wid);

    //根据checkin和schoolId条件进行学员审核查找
    public List<User> findUserByCheckinAndSchoolId(@Param("user") User user);

    //根据checkin条件进行学员审核查找
    public List<User> findUserByCheckin(@Param("user") User user);

    //根据checkin和schoolId条件进行学员注销审核查找
    public List<WithdrawVo> findWithdrawByCheckinAndSchoolId(@Param("withdrawVo") WithdrawVo withdrawVo);

    //修改用户报名学校的ID
    public void UpdateUserSchoolId(Integer userId);

    //后台修改用户信息
    public void updateByUserId(@Param("phone") String phone, @Param("userId") Integer userId);

    //添加学员投稿
    public void addUserContribute(Contribute contribute);

    //查询学员投稿申请审核状态
    public List<Contribute> queryUserContribute(PageVo pageVo);

    //删除一条用户投稿记录
    public void deleteUserContribute(Contribute contribute);

    //修改投稿申请状态
    public void updateContributeCheckin(@Param("checkin") Integer checkin,@Param("rejectReason") String rejectReason,@Param("cid") Integer cid);

    //查询报名学校Id和是否通过验证 -vvtxw
    public User queryUserInfo(Integer userId);

    //查询所有学员投稿申请列表
    public List<Contribute> contributeList(PageVo pageVo);

    //学员投稿修改
    public void updateContribute(Contribute contribute);


}
