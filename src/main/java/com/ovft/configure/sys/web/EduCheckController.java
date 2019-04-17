package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduCheck;
import com.ovft.configure.sys.bean.EduCheckExample;
import com.ovft.configure.sys.bean.School;
import com.ovft.configure.sys.service.EduCheckService;
import com.ovft.configure.sys.service.EduCourseService;
import com.ovft.configure.sys.service.OrderService;
import com.ovft.configure.sys.service.SchoolService;
import com.ovft.configure.sys.vo.EduCheckVo;
import com.ovft.configure.sys.vo.EduCourseVo;
import com.ovft.configure.sys.vo.OrderVo;
import javafx.scene.input.InputMethodTextRun;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    @Autowired
    private SchoolService schoolService;

    /**
     * 打卡
     *
     * @param
     */
    @PutMapping(value = "{userId}")
    public WebResult doSign(@PathVariable Integer userId, Double x, Double y) {
        //1.查询已付款的订单课程是否存在
        //初始 默认查询订单状态为已付款的课程
        int orderStatus = 1;
        //2.如果订单商品存在，就进行打卡
        List<OrderVo> orderVos = orderService.queryAllOrder(userId);
        if (orderVos.size() > 0) {
            //3.打卡时间判断，则可以进行打卡
            WebResult orNoCheck = isOrNoCheck(orderVos, x, y);
            return orNoCheck;
        } else {
            return new WebResult(StatusCode.OK, "你还未购买课程，无法课时打卡");
        }
    }

    /**
     * 打卡记录
     *
     * @param userId
     * @return
     */
    @GetMapping(value = "record/{userId}")
    public WebResult queryAllPunchRecord(@PathVariable Integer userId) {
        List<EduCheckVo> eduCheckVos = eduCheckService.queryAllPunchRecord(userId);
        return new WebResult(StatusCode.OK, "查询成功", eduCheckVos);
    }


    public WebResult isOrNoCheck(List<OrderVo> orderVos, Double x, Double y) {
        //查询订单详细数据
        for (OrderVo orderVo : orderVos) {
            Date nowTime = new Date();
            long now = nowTime.getTime();//当前时间戳
            int schoolId = orderVo.getSchoolId();
            Date startDate = orderVo.getStartDate();//获取开班当前日期
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            String formatstartDate = date.format(startDate);//获取开班当前日期字符串
            String formatnowTime = date.format(nowTime);//获取当前日期字符串
            if (!formatstartDate.equals(formatnowTime)) {
                return new WebResult(StatusCode.OK, "请到课时当天打卡");
            }
            String startTime = orderVo.getStartTime();//获取开班当前时间的字符串
            String newClassDateTime = formatstartDate + " " + startTime;
            SimpleDateFormat hours = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            try {
                Date newTime = hours.parse(newClassDateTime);
                //获取开课时间的毫秒值
                long classStartTime = newTime.getTime();

                String current = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm");
                Date parse = hours.parse(current);
                //获取当前时间的毫秒值
                long currentTime = parse.getTime();
                //获取开课30分钟的时间
                long fitTime = classStartTime - 1800000;
                if ((fitTime <= currentTime) && (currentTime <= classStartTime)) {
                    //4.打卡之前判断是否在学校区域内
                    School school = schoolService.queryRecordBySchoolId(schoolId);
                    String longitude = school.getLongitude();//经度
                    String[] splits = longitude.split("\\.");
                    String longitudeX = splits[1];
                    int recordx = Integer.parseInt(longitudeX);
                    String latitude = school.getLatitude();//纬度
                    String[] split1 = latitude.split("\\.");
                    String latitudeY = split1[1];
                    int recordy = Integer.parseInt(latitudeY);
                    String xStr = String.valueOf(x);
                    String yStr = String.valueOf(y);
                    String[] split2 = xStr.split("\\.");
                    String xrecord = split2[1];
                    String[] split3 = yStr.split("\\.");
                    String yrecord = split3[1];
                    int x1 = Integer.parseInt(xrecord);
                    int y1 = Integer.parseInt(yrecord);
                    if ((x1 > recordx - 2 && x1 < recordx + 2) || (y1 > recordy - 2 && y1 < recordy + 2)) {
                        EduCheck eduCheck = CheckInsert(orderVo);
                        eduCheckService.doSign(eduCheck);
                        return new WebResult(StatusCode.OK, "打卡成功");
                    } else {
                        EduCheck eduCheck = noCheckInsert(orderVo);
                        eduCheckService.doSign(eduCheck);
                        return new WebResult(StatusCode.OK, "您还不在指定的打卡区域，请到指定区域打卡");
                    }
                } else {
                    //在交易成功生成需要打卡的记录
                   /* EduCheck eduCheck = noCheckInsert(orderVo);
                    eduCheckService.doSign(eduCheck);*/
                    return new WebResult(StatusCode.OK, "请在当天半小时之内打卡");
                }
            } catch (ParseException e) {
                return new WebResult(StatusCode.OK, "后台输入的开始时间格式有误");
            }
        }
        return new WebResult(StatusCode.ERROR, "操作错误");
    }

    public EduCheck noCheckInsert(OrderVo orderVo) {
        EduCheck eduCheck = new EduCheck();
        eduCheck.setUserId(orderVo.getUserId());
        eduCheck.setCheckStartTime(new Date());
        eduCheck.setCheckEndTime(new Date());
        eduCheck.setUserId(orderVo.getUserId());
        eduCheck.setSchoolId(String.valueOf(orderVo.getSchoolId()));
        eduCheck.setIsCheck(0);
        eduCheckService.doSign(eduCheck);
        return eduCheck;
    }

    public EduCheck CheckInsert(OrderVo orderVo) {
        EduCheck eduCheck = new EduCheck();
        eduCheck.setUserId(orderVo.getUserId());
        eduCheck.setCheckStartTime(new Date());
        eduCheck.setCheckEndTime(new Date());
        eduCheck.setUserId(orderVo.getUserId());
        eduCheck.setSchoolId(String.valueOf(orderVo.getSchoolId()));
        eduCheck.setIsCheck(1);
        eduCheckService.doSign(eduCheck);
        return eduCheck;
    }

}
