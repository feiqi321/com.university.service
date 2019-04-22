package com.ovft.configure.sys.web;

import com.ovft.configure.constant.OrderStatus;
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

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @GetMapping(value = "dosign")
    public WebResult doSign(HttpServletRequest request, @RequestParam(value = "x", required = true) Double x, @RequestParam(value = "y", required = true) Double y) {
//        Integer userId = (Integer) request.getAttribute("userId");
        String userId1 = request.getHeader("userId");
        Integer userId = Integer.parseInt(userId1);

        if (userId == null) {
            return new WebResult(StatusCode.ERROR, "userId不能为空", "");
        }
//        userId = 1;
        //1.查询已付款的订单课程是否存在
        //2.如果订单商品存在，就进行打卡
        List<OrderVo> orderVos = orderService.queryAllRecord(userId);
        if (orderVos.size() > 0) {
            //3.打卡时间判断，则可以进行打卡
            WebResult orNoCheck = isOrNoCheck(orderVos, userId, x, y);
            return orNoCheck;
        } else {
            return new WebResult(StatusCode.OK, "你还未购买课程，无法课时打卡");
        }
    }

    /**
     * 打卡记录
     *
     * @return
     */
    @PostMapping(value = "record")
    public WebResult queryAllPunchRecord(HttpServletRequest request) {
//        Integer userId = (Integer) request.getAttribute("userId");
        String userId1 = request.getHeader("userId");
        Integer userId = Integer.parseInt(userId1);
        if (userId == null) {
            return new WebResult(StatusCode.ERROR, "userId不能为空", "");
        }
        List<EduCheckVo> eduCheckVos = eduCheckService.queryAllPunchRecord(userId);
        List Check = new ArrayList();
        for (EduCheckVo eduCheckVoInfo : eduCheckVos) {
            if (eduCheckVoInfo.getIsCheck() == 0) {
                Map<String, Object> noCheck = new HashMap<>();
                noCheck.put("courseName", eduCheckVoInfo.getCourseName());
                noCheck.put("placeClass", eduCheckVoInfo.getPlaceClass());
                noCheck.put("startDate", eduCheckVoInfo.getStartDate());
                noCheck.put("startTime", eduCheckVoInfo.getStartTime());
                noCheck.put("endTime", eduCheckVoInfo.getEndTime());
                noCheck.put("isCheck", "未签到");
                Check.add(noCheck);
            } else {
                Map<String, Object> isCheck = new HashMap<>();
                isCheck.put("courseName", eduCheckVoInfo.getCourseName());
                isCheck.put("placeClass", eduCheckVoInfo.getPlaceClass());
                isCheck.put("startDate", eduCheckVoInfo.getStartDate());
                isCheck.put("startTime", eduCheckVoInfo.getStartTime());
                isCheck.put("endTime", eduCheckVoInfo.getEndTime());
                isCheck.put("schoolName", eduCheckVoInfo.getSchoolName());
                isCheck.put("checkStartTime", eduCheckVoInfo.getCheckStartTime());
                isCheck.put("isCheck", "正常");
                Check.add(isCheck);
            }
        }
        return new WebResult(StatusCode.OK, "查询成功", Check);
    }


    public WebResult isOrNoCheck(List<OrderVo> orderVos, Integer userId, Double x, Double y) {
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
                return new WebResult(StatusCode.ERROR, "请到课时当天打卡");
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
                        EduCheck eduCheck = CheckInsert(orderVo, userId);
                        eduCheckService.doSign(eduCheck);
                        return new WebResult(StatusCode.OK, "打卡成功", null);
                    } else {
                        EduCheck eduCheck = noCheckInsert(orderVo, userId);
                        eduCheckService.doSign(eduCheck);
                        return new WebResult(StatusCode.ERROR, "您还不在指定的打卡区域，请到指定区域打卡", null);
                    }
                } else {
                    //在交易成功生成需要打卡的记录
                   /* EduCheck eduCheck = noCheckInsert(orderVo);
                    eduCheckService.doSign(eduCheck);*/
                    return new WebResult(StatusCode.ERROR, "请在当天半小时之内打卡", null);
                }
            } catch (ParseException e) {
                return new WebResult(StatusCode.ERROR, "后台输入的开始时间格式有误", null);
            }
        }
        return new WebResult(StatusCode.ERROR, "操作错误", null);
    }

    public EduCheck noCheckInsert(OrderVo orderVo, Integer userId) {
        EduCheck eduCheck = new EduCheck();
        orderVo.setUserId(userId);
        eduCheck.setUserId(userId);
        eduCheck.setCheckStartTime(new Date());
        eduCheck.setCheckEndTime(new Date());
        eduCheck.setUserId(orderVo.getUserId());
        eduCheck.setSchoolId(String.valueOf(orderVo.getSchoolId()));
        eduCheck.setIsCheck(OrderStatus.NOCHECK);
        eduCheckService.doSign(eduCheck);
        return eduCheck;
    }

    public EduCheck CheckInsert(OrderVo orderVo, Integer userId) {
        EduCheck eduCheck = new EduCheck();
        orderVo.setUserId(userId);
        eduCheck.setUserId(userId);
        eduCheck.setCheckStartTime(new Date());
        eduCheck.setCheckEndTime(new Date());
        eduCheck.setUserId(orderVo.getUserId());
        eduCheck.setSchoolId(String.valueOf(orderVo.getSchoolId()));
        eduCheck.setIsCheck(OrderStatus.ISCHECK);
        eduCheckService.doSign(eduCheck);
        return eduCheck;

    }

}
