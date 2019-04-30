package com.ovft.configure.sys.service.impl;

import com.ovft.configure.constant.EmployerStatus;
import com.ovft.configure.constant.OrderStatus;
import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.sys.bean.*;
import com.ovft.configure.sys.dao.*;
import com.ovft.configure.sys.service.EduCourseService;
import com.ovft.configure.sys.utils.AgeUtil;
import com.ovft.configure.sys.vo.EduCourseVo;
import com.ovft.configure.sys.utils.OrderIdUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
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

    @Resource
    private EduRegistMapper eduRegistMapper;

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
    @Transactional
    @Override
    public Map<String, Object> queryCourseByCourseId(Integer userId, Integer courseId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> param = new HashMap<>();

        String schoolId1 = request.getHeader("schoolId");
        Integer schoolId = Integer.parseInt(schoolId1);


        //0.可以报名的人数
        //查询专业接受报名的人数
        int acceptNum = eduCourseMapper.queryAcceptNum(courseId);
        if (acceptNum == 0) {
            map.put("本专业未设置可报名的人数，请管理员设置报名人数限制", "");
        }
        //查询用户所对应的专业已购买人数
        param.put("course_id", courseId);
        param.put("payment_status", OrderStatus.PAY);
        int payNum = orderMapper.countPayCourseNum(param);
        if (payNum >= acceptNum) {
            map.put("人数已满，请下期再报名", "");
            return map;
        }
        //可报名剩余人数
        int nowtotal = acceptNum - payNum;


        //1.限制可报名的报名时间
        //获取当前的时间
        Date now = new Date();
        long nowTime = now.getTime();//当前时间戳

        try {
            EduRegist eduRegist = eduRegistMapper.selectByCourseId(courseId);


            if (eduRegist == null) {
                EduCourseVo courseInfo = applyCourse(userId, courseId);
                nowtotal--;
                map.put("该课程没有任何条件制约，可以报名,报名还剩" + nowtotal + "个名额，请赶紧抢购", courseInfo);
                return map;
            }
            Date startTime = eduRegist.getRegiststartTime();
            Date endTime = eduRegist.getRegistendTime();
            if (startTime.getTime() > nowTime || nowTime > endTime.getTime()) {
                map.put("请在报名截止时间内报名：" + startTime + "至" + endTime + "方可报名", "");
            }
            //2.限制可报名年龄
            User user = userMapper.queryByItemsIdAndSchoolId(userId, schoolId);
            if (user == null) {
                map.put("请完善好自己的基本信息，再来报名！", "");
            }
            Integer startAge = eduRegist.getStartAge();
            Integer endAge = eduRegist.getEndAge();
            String identity = user.getIdentityCard();
            if (identity == null || identity == "") {
                map.put("请完善好自己的基本信息，再来报名！", "");
                return map;
            }
            int age = AgeUtil.IdNOToAge(identity);
            if (startAge > age || age > endAge) {
                map.put("请在在允许的年龄阶段报名：" + startAge + "至" + endAge + "方可报名", "");
                return map;
            }

            //3.限制可报名的学员分类   查询用户所对应的学员分类人数
            if (user.getEmployer() == null || user.getEmployer() == "") {
                map.put("请填写完善个人的基本信息，再来报名", "");
                return map;
            }
            //如果传入的学员类别不为空，方可报名
            if (eduRegist.getRegistCategoryOne() != null && eduRegist.getRegistCategoryOne().equals(user.getEmployer())) {
                if (nowtotal > 0) {
                    EduCourseVo courseInfo = applyCourse(userId, courseId);
                    nowtotal--;
                    map.put("报名还剩" + nowtotal + "个名额，请赶紧抢购", courseInfo);
                }

            }
            if (eduRegist.getRegistCategoryTwo() != null && eduRegist.getRegistCategoryTwo().equals(user.getEmployer())) {
                if (nowtotal > 0) {
                    EduCourseVo courseInfo = applyCourse(userId, courseId);
                    nowtotal--;
                    map.put("报名还剩" + nowtotal + "个名额，请赶紧抢购", courseInfo);
                }
            }
            if (eduRegist.getRegistCategoryThree() != null && eduRegist.getRegistCategoryThree().equals(user.getEmployer())) {
                if (nowtotal > 0) {
                    EduCourseVo courseInfo = applyCourse(userId, courseId);
                    nowtotal--;
                    map.put("报名还剩" + nowtotal + "个名额，请赶紧抢购", courseInfo);
                }
            }
            if (eduRegist.getRegistCategoryFour() != null && eduRegist.getRegistCategoryFour().equals(user.getEmployer())) {
                if (nowtotal > 0) {
                    EduCourseVo courseInfo = applyCourse(userId, courseId);
                    nowtotal--;
                    map.put("报名还剩" + nowtotal + "个名额，请赶紧抢购", courseInfo);
                }
            }
            if (eduRegist.getRegistCategoryFive() != null && eduRegist.getRegistCategoryFive().equals(user.getEmployer())) {
                if (nowtotal > 0) {
                    EduCourseVo courseInfo = applyCourse(userId, courseId);
                    nowtotal--;
                    map.put("报名还剩" + nowtotal + "个名额，请赶紧抢购", courseInfo);
                }
            }
            if (eduRegist.getRegistCategorySix() != null && eduRegist.getRegistCategorySix().equals(user.getEmployer())) {
                if (nowtotal > 0) {
                    EduCourseVo courseInfo = applyCourse(userId, courseId);
                    nowtotal--;
                    map.put("报名还剩" + nowtotal + "个名额，请赶紧抢购", courseInfo);
                }
            }

        } catch (Exception e) {
            map.put("后台课程设置条件重复，请管理员修改", "");
        }


        return map;
    }

    @Override
    public EduCourseVo queryCourseByCategory(Integer courseId) {
        //1.根据courseId查询订单的信息
        EduCourseVo courseInfo = eduCourseMapper.queryCourseByCourseId(courseId);
        //2.根据courseId查询开课的具体时间
        List<EduClass> eduClasses = eduClassMapper.queryCourseTimeByCourseId(courseId);
        //3.生成报名详情信息
        courseInfo.setClassList(eduClasses);
        return courseInfo;
    }

    /**
     * 课程表
     *
     * @param week
     * @param schoolId
     * @return
     */
    @Override
    public List<EduCourseVo> queryAllTimetable(String week, String schoolId) {
        return eduCourseMapper.queryAllTimetable(week, schoolId);
    }

    public EduCourseVo applyCourse(Integer userId, Integer courseId) {
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
