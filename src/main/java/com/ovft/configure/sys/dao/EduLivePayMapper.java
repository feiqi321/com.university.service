package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.EduLivePay;

import com.ovft.configure.sys.bean.User;
import com.ovft.configure.sys.vo.LivePayVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author vvtxw
 * @create 2019-09-04 12:09
 */

@Mapper
public interface EduLivePayMapper {

    //增加现场报名班级报名记录
    public int addLivePay(EduLivePay eduLivePay) ;

    //查看现场报名记录,通过组合实体类里面的信息
    List<LivePayVo> selectLivePay(LivePayVo livePayVo);

    //<!--修改现场报名记录-->
    public void updateLivePayById(EduLivePay eduLivePay);

    //删除某条记录根据id
    public void deleteById(@Param("id")Integer id);

    //通过手机号查询一些需要的信息
    User selectByPhone(@Param("phone") String phone);

    //通过模糊查询课程名字 查询一些需要的信息
    List<EduLivePay> selectByCourseName(@Param("search") String search);

    //<!--通过手机号和课程查询一条记录-->
    List<EduLivePay> selectByPhoneAndCourseName(LivePayVo livePayVo);


}
