package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduLivePay;
import com.ovft.configure.sys.bean.User;
import com.ovft.configure.sys.dao.EduLivePayMapper;
import com.ovft.configure.sys.service.EduLivePayService;
import com.ovft.configure.sys.vo.LivePayVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author vvtxw
 * @create 2019-09-04 12:22
 */
@Service
public class EduLivePayServiceImpl implements EduLivePayService {
    @Resource
    private EduLivePayMapper eduLivePayMapper;

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
        if (livePayVo.getMoney()==null){
            return new WebResult("400", "缴费金额不能为空", "");
        }
        livePayVo.setPayDate(new Date());//保存前设置时间
       if (livePayVo.getId()==null){
           List<EduLivePay> eduLivePays = eduLivePayMapper.selectByPhoneAndCourseName(livePayVo);
           if (!eduLivePays.isEmpty()){//如果查出数据说明报名重复不能添加进去
               return new WebResult("400", "报名重复,您已经报名过该班级", "");
           }
           eduLivePayMapper.addLivePay(livePayVo);
           return new WebResult(StatusCode.OK, "添加现场报名记录成功", "");
       }else {
          List<EduLivePay> eduLivePays = eduLivePayMapper.selectByPhoneAndCourseName(livePayVo);
           if (!eduLivePays.isEmpty()){//如果查出数据说明报名重复不能修改进去
               return new WebResult("400", "修改重复,他/她已经报名过该班级.如果想修改金额时,请先换个班级在修改金额", "");
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
