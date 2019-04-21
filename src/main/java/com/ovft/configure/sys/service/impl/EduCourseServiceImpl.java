package com.ovft.configure.sys.service.impl;

import com.ovft.configure.constant.EmployerStatus;
import com.ovft.configure.constant.OrderStatus;
import com.ovft.configure.sys.bean.EduClass;
import com.ovft.configure.sys.bean.EduCourse;
import com.ovft.configure.sys.bean.Order;
import com.ovft.configure.sys.bean.OrderDetail;
import com.ovft.configure.sys.dao.*;
import com.ovft.configure.sys.service.EduCourseService;
import com.ovft.configure.sys.vo.EduCourseVo;
import com.ovft.configure.sys.utils.OrderIdUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 课程报名业务层
 *
 * @author vvtxw
 * @create 2019-04-13 11:38
 */
@Service
public class EduCourseServiceImpl implements EduCourseService {

    @Resource
    private EduCourseMapper eduCourseMapper;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private EduClassMapper eduClassMapper;

    @Resource
    private OrderDetailMapper orderDetailMapper;

    @Resource
    private UserMapper userMapper;

    /**
     * 按学校的id来查找专业类别
     *
     * @param schoolId
     * @return
     */
    @Override
    public List<EduCourse> listCourseCategoryByShoolId(int schoolId) {
        return eduCourseMapper.listCourseCategoryByShoolId(schoolId);
    }

    /**
     * 根据课程id查询课程详细信息报名
     *
     * @param courseId
     * @return
     */
    @Override
    public Map<String, EduCourseVo> queryCourseByCourseId(Integer userId, Integer courseId) {
        Map<String, EduCourseVo> map = new HashMap<>();
        Map<String, Object> param = new HashMap<>();
        Map<String, Object> param2 = new HashMap<>();
        //查询用户所对应的学员分类人数
        String employer = userMapper.queryemployerByUserId(userId);
        if (employer == null || employer == "") {
            employer = EmployerStatus.NO4;
        }
        param.put("course_id", courseId);
        param.put("payment_status", OrderStatus.PAY);
        param.put("employer", EmployerStatus.NO1);
        //查询专业学员类别为1已经报名的人数
        int payEmployerNumNo1 = orderMapper.countPayCourseEmployerNum(param);
        //查询用户所对应的专业已购买人数
        param2.put("course_id", courseId);
        param2.put("payment_status", OrderStatus.PAY);
        int payNum = orderMapper.countPayCourseNum(param2);

        //查询专业接受报名的人数
        int acceptNum = eduCourseMapper.queryAcceptNum(courseId);
        if (payNum >= acceptNum) {
            map.put("人数已满，请下期再报名", null);
            return map;
        }
        //可报名剩余人数
        int nowtotal = acceptNum - payNum;

        if (employer.equals(EmployerStatus.NO1) && nowtotal > 0) {
            EduCourseVo courseInfo = applyCourse(userId, courseId);
            nowtotal--;
            map.put("报名还剩" + nowtotal + "个名额，请赶紧抢购", courseInfo);
        }
        if (employer.equals(EmployerStatus.NO2) && payEmployerNumNo1 >= acceptNum / 2) {
            EduCourseVo courseInfo = applyCourse(userId, courseId);
            map.put("报名还剩" + nowtotal + "个名额，请赶紧抢购", courseInfo);
        }
        if (employer.equals(EmployerStatus.NO3) && payNum >= acceptNum / 2 + 1) {
            EduCourseVo courseInfo = applyCourse(userId, courseId);
            map.put("报名还剩" + nowtotal + "个名额，请赶紧抢购", courseInfo);
        }
        if (employer.equals(EmployerStatus.NO4) && payNum >= acceptNum / 2 + 2) {
            EduCourseVo courseInfo = applyCourse(userId, courseId);
            map.put("报名还剩" + nowtotal + "个名额，请赶紧抢购", courseInfo);
        }
        return map;
    }

    private EduCourseVo applyCourse(Integer userId, Integer courseId) {
        //1.根据courseId查询订单的信息
        EduCourseVo courseInfo = eduCourseMapper.queryCourseByCourseId(courseId);
        //2.根据courseId查询开课的具体时间
        List<EduClass> eduClasses = eduClassMapper.queryCourseTimeByCourseId(courseId);
        //3.生成报名详情信息
        courseInfo.setClassList(eduClasses);
        //4.生成订单关联
        Order order = new Order();
        //生成订单的订单号
        order.setOrderSn(OrderIdUtil.getOrderIdByTime());
        order.setUserId(userId);
        order.setCreateTime(new Date());
        order.setOrderStatus(OrderStatus.UNREMOVE);//未取消
        order.setTotalAmount(courseInfo.getCoursePrice().longValue());
        order.setTradeBody(courseInfo.getCourseName());
        orderMapper.insertSelective(order);

        //生成订单明细表
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(order.getId().longValue());
        orderDetail.setCourseId(courseId.longValue());
        orderDetail.setCourseName(courseInfo.getCourseName());
        orderDetail.setOrderPrice(courseInfo.getCoursePrice());
        orderDetail.setNum(1);
        orderDetailMapper.insertSelective(orderDetail);
        return courseInfo;
    }

}
