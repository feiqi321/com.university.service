package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduLivePay;
import com.ovft.configure.sys.vo.LivePayVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author vvtxw
 * @create 2019-09-04 12:19
 */
public interface EduLivePayService {

    //增加现场报名班级报名记录
    public Integer addLivePay(EduLivePay eduLivePay) ;

    //查看现场报名记录
    WebResult selectLivePay(LivePayVo livePayVo);

    //修改和添加现在一起,添加修改报名记录
    WebResult createLivePay(LivePayVo livePayVo);

    //删除
    WebResult deleteById(Integer id);

    //通过手机号和学校id查询一些需要的信息
    WebResult selectByPhone(LivePayVo livePayVo);

    //通过模糊查询课程名字 查询一些需要的信息
    WebResult selectByCourseName(LivePayVo livePayVo);

 /*   //<!--通过手机号和课程查询一条记录-->
    WebResult selectByPhoneAndCourseName(LivePayVo livePayVo);*/

}
