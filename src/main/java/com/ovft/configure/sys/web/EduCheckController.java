package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduCheck;
import com.ovft.configure.sys.bean.EduCheckExample;
import com.ovft.configure.sys.service.EduCheckService;
import com.ovft.configure.sys.service.EduCourseService;
import com.ovft.configure.sys.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.TimeZone;

/**
 * @author vvtxw
 * @create 2019-04-15 21:51
 */
@RestController
@RequestMapping("doCheck")
public class EduCheckController {

    @Autowired
    private EduCheckService eduCheckService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private EduCourseService eduCourseService;

    /**
     * 根据userId来修改打卡状态
     * @param eduCheck
     */
    @PutMapping
    public WebResult doSign(EduCheck eduCheck,Double x,Double y){
     /*   //1.查询已付款的订单课程是否存在
        //初始 默认查询订单状态为已付款的课程
        int orderStatus=1;
        int count = orderService.queryCourseNumById(eduCheck.getUserId(), orderStatus);
        //2.如果存在，就进行打卡
        if (count>0){
            //3.如果是当天开课前的30分钟，则可以进行打卡
            //获取当前凌晨的时间
            long current=System.currentTimeMillis();//当前时间毫秒数
            long zero=current/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数

            Date startDate = eduCourseService.queryStartDateByCouserId(couseId);
            long time = startDate.getTime();
            if (current!=time){
                return new WebResult(StatusCode.OK,"请到课时当天打卡");
            }
            Date startTime = eduCourseService.queryStartTimeByCouserId(couseId);
            long hours = startTime.getTime();
            //离开课时间半小时之前
            long fitTime = time+hours-1800000;
            Date nowTime = new Date();
            long now = nowTime.getTime();
            if (now<=fitTime&&now>=zero){
                //符合以上要求，进行更改打卡状态
                EduCheckExample eduCheckExample = new EduCheckExample();
                eduCheckExample.createCriteria().andIsCheckEqualTo(eduCheck.getUserId());
                eduCheckService.doSign(eduCheck,eduCheckExample);
            }else{
                return new  WebResult(StatusCode.OK,"请在当天半小时之内打卡");
            }


        }else{
            return  new WebResult(StatusCode.OK,"你还未购买课程，无法课时打卡");
        }*/
        return  new WebResult(StatusCode.ERROR,"操作错误");
    }


}
