package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduLivePay;
import com.ovft.configure.sys.bean.EduOfflineOrder;
import com.ovft.configure.sys.bean.EduPayrecord;
import com.ovft.configure.sys.bean.User;
import com.ovft.configure.sys.dao.*;
import com.ovft.configure.sys.service.EduLivePayService;
import com.ovft.configure.sys.vo.LivePayVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author vvtxw
 * @create 2019-09-04 12:22
 */
@Service
public class EduLivePayServiceImpl implements EduLivePayService {
    @Resource
    private EduLivePayMapper eduLivePayMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private EduOfflineOrderMapper eduOfflineOrderMapper;
    @Resource
    private EduPayrecordMapper eduPayrecordMapper;
    @Resource
    private EduRegistMapper eduRegistMapper;
    @Resource
    private EduCourseMapper eduCourseMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private QuestionSearchMapper questionSearchMapper;


    //增加现场报名班级报名记录
    @Override
    public Integer addLivePay(EduLivePay eduLivePay) {
        return eduLivePayMapper.addLivePay(eduLivePay);
    }
     //查看报名记录并分页
    @Override
    public WebResult selectLivePay(LivePayVo livePayVo) {
        //不分页显示
        if(livePayVo.getPageSize()==0){
            List<LivePayVo> livePayVos = eduLivePayMapper.selectLivePay(livePayVo);
            return new WebResult("200", "查询成功", livePayVos);
        }
        //分页显示
        PageHelper.startPage(livePayVo.getPageNum(),livePayVo.getPageSize());
        List<LivePayVo> livePayVosPage = eduLivePayMapper.selectLivePay(livePayVo);
        PageInfo  pageInfo=new PageInfo(livePayVosPage);
        return new WebResult("200", "查询成功", pageInfo);
    }

    //修改和添加现在一起,添加修改报名记录
    @Override
    @Transactional
    public WebResult createLivePay(LivePayVo livePayVo) {
        if(StringUtils.isBlank(livePayVo.getPhone())){
           return new WebResult("400", "请输入手机号", "");
       }
        if (StringUtils.isBlank(livePayVo.getCourseName())) {
            return new WebResult("400", "课程名称不能为空", "");
        }
        if (livePayVo.getMoney()==null||livePayVo.getMoney().intValue()<0){
            return new WebResult("400", "缴费金额不能为空或者为负数", "");
        }
        livePayVo.setPayDate(new Date());//保存前设置时间
       if (livePayVo.getId()==null){
           //通过手机号和课程名查现场报名的
           List<EduLivePay> eduLivePays = eduLivePayMapper.selectByPhoneAndCourseName(livePayVo);
           //通过手机号和课程查网上报名的
           List<EduPayrecord> eduPayrecords = eduLivePayMapper.selectByPhoneAndCourseNameOnLine(livePayVo);
           if (!eduLivePays.isEmpty() || ! eduPayrecords.isEmpty()) {
               //如果查出数据说明报名重复不能添加进去
               return new WebResult("400", "报名重复,您已经报名过该班级", "");
           }

           //3.限制可报名的报名学科门数
           //查询线下的报名门数
           User userByPhone2 = userMapper.findUserByPhone2(livePayVo.getPhone());
           Integer offCourseNum = eduOfflineOrderMapper.queryCountCourseNum(userByPhone2.getUserId());
           //查询线上的报名门数
           Integer oLineCourseNum = eduPayrecordMapper.queryCourseNum(userByPhone2.getUserId());
           User user1 = userMapper.selectById(userByPhone2.getUserId());
           LivePayVo livePayVo5=new LivePayVo();
           livePayVo5.setPhone(user1.getPhone());
           List<LivePayVo> livePayVos = eduLivePayMapper.selectLivePay(livePayVo5);
           Integer allCourse = offCourseNum + oLineCourseNum+livePayVos.size();    //线下+线上+现场
           //查询条件限制的门数
           Map<String, Object> param = new HashMap<>();
           param.put("courseId", livePayVo.getCourseId());
           param.put("schoolId", livePayVo.getSchoolId());
           int courseNum = eduRegistMapper.queryCourseNum(param);
           if (allCourse >= courseNum) {
               return new WebResult("600","为了您的身体健康，本课程只允许总共报" + courseNum + "门，您报名课程总数已经超出", "");
           }

           //查询专业接受报名的人数
           int acceptNum = eduCourseMapper.queryAcceptNum(livePayVo.getCourseId());
           if (acceptNum == 0) {
               return new WebResult("200", "录入失败：该课程尚未设置计划招生人数", "");
           } else {     //编辑
               //查询用户所对应的专业显示已经购买人数
               Map<String, Object> param2 = new HashMap<>();
               param2.put("course_id", livePayVo.getCourseId());
               param2.put("payment_status", "PAID");

               int olineNum = orderMapper.countPayCourseNum(param2);

               //查询用户所对应的专业线下的总人数
               Integer offNum = eduOfflineOrderMapper.queryOffRecordNum(livePayVo.getCourseId());

               //得到最终报名人数
               Integer payNum = olineNum + offNum;
               LivePayVo livePayVo6=new LivePayVo();
               livePayVo.setCourseId(livePayVo.getCourseId());   //查找当前课程的退课数量
               List<LivePayVo> livePayVos7 = questionSearchMapper.selectClassOut(livePayVo);
               //查询现场报名相关成员记录
               LivePayVo livePayVo2=new LivePayVo();
               livePayVo2.setCourseId(livePayVo.getCourseId());
               List<LivePayVo> livePayVos3 = eduLivePayMapper.selectLivePay(livePayVo2);
               //可报名剩余人数
               int nowtotal = acceptNum-(payNum+livePayVos3.size());
               if (nowtotal<=0){
                   return new WebResult("600", "录入失败：该课程人数已满", "");
               }
           }

           eduLivePayMapper.addLivePay(livePayVo);
           return new WebResult(StatusCode.OK, "添加现场报名记录成功", "");
       }else {
          List<EduLivePay> eduLivePays = eduLivePayMapper.selectByPhoneAndCourseName(livePayVo);
           //通过手机号和课程查网上报名的
           List<EduPayrecord> eduPayrecords = eduLivePayMapper.selectByPhoneAndCourseNameOnLine(livePayVo);
           if (!eduLivePays.isEmpty() || ! eduPayrecords.isEmpty()){//如果查出数据说明报名重复不能修改进去
               return new WebResult("400", "修改重复,他/她已经报名过该班级.如果想修改金额时,请先换个班级在修改金额", "");
           }

           //查询专业接受报名的人数
           int acceptNum = eduCourseMapper.queryAcceptNum(livePayVo.getCourseId());
           if (acceptNum == 0) {
               return new WebResult("200", "编辑失败：该课程尚未设置计划招生人数", "");
           } else {
               //查询用户所对应的专业显示已经购买人数
               Map<String, Object> param2 = new HashMap<>();
               param2.put("course_id", livePayVo.getCourseId());
               param2.put("payment_status", "PAID");

               int olineNum = orderMapper.countPayCourseNum(param2);

               //查询用户所对应的专业线下的总人数
               Integer offNum = eduOfflineOrderMapper.queryOffRecordNum(livePayVo.getCourseId());

               //得到最终报名人数
               Integer payNum = olineNum + offNum;
               LivePayVo livePayVo6=new LivePayVo();
               livePayVo.setCourseId(livePayVo.getCourseId());   //查找当前课程的退课数量
               List<LivePayVo> livePayVos7 = questionSearchMapper.selectClassOut(livePayVo);
               //查询现场报名相关成员记录
               LivePayVo livePayVo2=new LivePayVo();
               livePayVo2.setCourseId(livePayVo.getCourseId());
               List<LivePayVo> livePayVos3 = eduLivePayMapper.selectLivePay(livePayVo2);
               //可报名剩余人数
               int nowtotal = acceptNum-(payNum+livePayVos3.size());
               if (nowtotal<=0){
                   return new WebResult("600", "编辑失败：该课程人数已满", "");
               }
           }

           eduLivePayMapper.updateLivePayById(livePayVo);
           return new WebResult(StatusCode.OK, "修改报名记录成功", "");
       }
       }

     //删除一条记录
    @Override
    @Transactional
    public WebResult deleteById(Integer id) {

        eduLivePayMapper.deleteById(id);
        return new WebResult("200", "删除成功", "") ;
    }
    //通过手机号查用户信息
    @Override
    public WebResult selectByPhone(LivePayVo livePayVo) {
        User user = eduLivePayMapper.selectByPhone(livePayVo);
        try {
            if (user.getIdentityCard()==null){
            }
        } catch (Exception e) {
          //  e.printStackTrace();
            return new WebResult("600", "该手机号还没有录入系统,您需要手动填写他的信息,您确定这么做吗?", "") ;
        }
        return new WebResult("200", "查询成功", user) ;
    }
     //通过模糊查询课程名字查其他信息
    @Override
    public WebResult selectByCourseName(LivePayVo livePayVo) {
        List<EduLivePay> eduLivePays = eduLivePayMapper.selectByCourseName(livePayVo);
        return new WebResult("200", "查询成功", eduLivePays);
    }

}
