package com.ovft.configure.sys.service.impl;

import com.ovft.configure.constant.OrderStatus;
import com.ovft.configure.constant.Status;
import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.sys.bean.*;
import com.ovft.configure.sys.dao.*;
import com.ovft.configure.sys.service.EduCourseService;
import com.ovft.configure.sys.service.EduOfflineOrderService;
import com.ovft.configure.sys.utils.AgeUtil;
import com.ovft.configure.sys.vo.EduCourseVo;
import com.ovft.configure.sys.utils.OrderIdUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Resource
    private EduOfflineOrderMapper eduOfflineOrderMapper;

    /**
     * 按学校的id来查找专业类别
     *
     * @param eduCourse
     * @return
     */
    @Override
    public List<EduCourse> listCourseCategoryByShoolId(EduCourse eduCourse) {
        return eduCourseMapper.listCourseCategoryByShoolId(eduCourse);
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
//        Integer schoolId = 11;

        //0.可以报名的人数
        //查询专业接受报名的人数
        int acceptNum = eduCourseMapper.queryAcceptNum(courseId);
        if (acceptNum == 0) {
            map.put("本专业未设置可报名的人数，请管理员设置报名人数限制", "");
        }
        //查询用户所对应的专业显示已经购买人数
        param.put("course_id", courseId);
        param.put("payment_status", OrderStatus.PAY);
        int olineNum = orderMapper.countPayCourseNum(param);

        //查询用户所对应的专业线下的总人数
        Integer offNum = eduOfflineOrderMapper.queryOffRecordNum(Status.PAY);

        Integer payNum = olineNum + offNum;

        if (payNum >= acceptNum) {
            map.put("人数已满，请下期再报名", "");
            return map;
        }
        //可报名剩余人数
        int nowtotal = acceptNum - payNum;


        EduRegist eduRegist = eduRegistMapper.selectByCourseId(courseId);
        try {
            //该课程没有任何条件制约，可以报名
            if (eduRegist == null) {
                EduCourseVo courseInfo = applyCourse(userId, courseId);
                nowtotal--;
                map.put("该课程没有任何条件制约，可以报名,报名还剩" + nowtotal + "个名额，请赶紧抢购", courseInfo);
                return map;
            }

            //如果传入的id为0，为全部设置
//            EduRegist AllCourserCondition = eduRegistMapper.selectByCourseId(Status.ALLCOURSE);

          /*  if (AllCourserCondition != null) {
                if (AllCourserCondition.getCourseId() == 0) {
                    //查询根据学校id,查询所有的课程id
                    List<Integer> courseIds = eduCourseMapper.selectCourseIdBySchoolId(schoolId);
                    //根据传入的id，是否包含在设置的条件里面，如果包含，就可以报名
                    for (Integer id : courseIds) {
                        if (id == courseId) {
                            //设置全部
                            map = decideApply(map, eduRegist, userId, schoolId, nowtotal, courseId);
                            return map;
                        }
                    }
                }
            }*/
            //TODO decideApply
        } catch (Exception e) {
            map.put("后台设置错误，请重新设置", "");
        }

        //单个课程的设置条件
        map = decideApply(map, eduRegist, userId, schoolId, nowtotal, courseId);
        return map;
    }

    private Map<String, Object> decideApply(Map<String, Object> map, EduRegist eduRegist, Integer userId, Integer schoolId, Integer nowtotal, Integer courseId) {
        try {
            //1.限制可报名的报名时间
            //获取当前的时间
            Date now = new Date();
            long nowTime = now.getTime();//当前时间戳
            Date startTime = eduRegist.getRegiststartTime();
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            String startTime1 = date.format(startTime);//获取当天日期字符串
            Date endTime = eduRegist.getRegistendTime();
            String endTime1 = date.format(endTime);//获取当天日期字符串
            if (startTime.getTime() > nowTime || nowTime > endTime.getTime()) {
                map.put("请在报名截止时间内报名：" + startTime1 + "至" + endTime1 + "方可报名", "");
                return map;
            }
            //2.限制可报名年龄
            User user = userMapper.queryByItemsIdAndSchoolId(userId, schoolId);
            if (user == null) {
                map.put("请在学员中心的基本信息填写必要信息：身份证，再来报名！", "");
                return map;
            }
            Integer startAge = eduRegist.getStartAge();
            Integer endAge = eduRegist.getEndAge();
            String identity = user.getIdentityCard();

            if (identity == null || identity == "") {
                map.put("请在学员中心的基本信息填写必要信息：学员类别，再来报名！", "");
                return map;
            }
            //通过省份证获取年龄
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

            List<String> category = new ArrayList<>();
            category.add(eduRegist.getRegistCategoryOne());
            category.add(eduRegist.getRegistCategoryTwo());
            category.add(eduRegist.getRegistCategoryThree());
            category.add(eduRegist.getRegistCategoryFour());
            category.add(eduRegist.getRegistCategoryFive());
            category.add(eduRegist.getRegistCategorySix());

            for (String s : category) {
                if (s == null) {
                    s = "";
                }
                if ((s != null || s != "") && s.equals(user.getEmployer())) {
                    if (nowtotal > 0) {
                        EduCourseVo courseInfo = applyCourse(userId, courseId);
                        nowtotal--;
                        map.put("报名还剩" + nowtotal + "个名额，请赶紧抢购", courseInfo);
                        return map;
                    }
                }
            }

          /*
            //如果传入的学员类别不为空，方可报名
            if (eduRegist.getRegistCategoryOne() != null && eduRegist.getRegistCategoryOne().equals(user.getEmployer())) {
                if (nowtotal > 0) {
                    EduCourseVo courseInfo = applyCourse(userId, courseId);
                    nowtotal--;
                    map.put("报名还剩" + nowtotal + "个名额，请赶紧抢购", courseInfo);
                    return map;
                }

            }*//* else {
                map.put("该课程目前不允许您的人员类别报名，请等候发放权限", "");
                return map;
            }*//*
            if (eduRegist.getRegistCategoryTwo() != null && eduRegist.getRegistCategoryTwo().equals(user.getEmployer())) {
                if (nowtotal > 0) {
                    EduCourseVo courseInfo = applyCourse(userId, courseId);
                    nowtotal--;
                    map.put("报名还剩" + nowtotal + "个名额，请赶紧抢购", courseInfo);
                    return map;
                }
            }*//* else {
                map.put("该课程目前不允许您的人员类别报名，请等候发放权限", "");
            }*//*
            if (eduRegist.getRegistCategoryThree() != null && eduRegist.getRegistCategoryThree().equals(user.getEmployer())) {
                if (nowtotal > 0) {
                    EduCourseVo courseInfo = applyCourse(userId, courseId);
                    nowtotal--;
                    map.put("报名还剩" + nowtotal + "个名额，请赶紧抢购", courseInfo);
                    return map;
                }
            } *//*else {
                map.put("该课程目前不允许您的人员类别报名，请等候发放权限", "");
                return map;
            }*//*
            if (eduRegist.getRegistCategoryFour() != null && eduRegist.getRegistCategoryFour().equals(user.getEmployer())) {
                if (nowtotal > 0) {
                    EduCourseVo courseInfo = applyCourse(userId, courseId);
                    nowtotal--;
                    map.put("报名还剩" + nowtotal + "个名额，请赶紧抢购", courseInfo);
                    return map;
                }
            } *//*else {
                map.put("该课程目前不允许您的人员类别报名，请等候发放权限", "");
                return map;
            }*//*
            if (eduRegist.getRegistCategoryFive() != null && eduRegist.getRegistCategoryFive().equals(user.getEmployer())) {
                if (nowtotal > 0) {
                    EduCourseVo courseInfo = applyCourse(userId, courseId);
                    nowtotal--;
                    map.put("报名还剩" + nowtotal + "个名额，请赶紧抢购", courseInfo);
                    return map;
                }
            } *//*else {
                map.put("该课程目前不允许您的人员类别报名，请等候发放权限", "");
                return map;
            }*//*
            if (eduRegist.getRegistCategorySix() != null && eduRegist.getRegistCategorySix().equals(user.getEmployer())) {
                if (nowtotal > 0) {
                    EduCourseVo courseInfo = applyCourse(userId, courseId);
                    nowtotal--;
                    map.put("报名还剩" + nowtotal + "个名额，请赶紧抢购", courseInfo);
                    return map;
                }
            } else {
                map.put("该课程目前不允许您的人员类别报名，请等候发放权限", "");
                return map;
            }*/

        } catch (Exception e) {
            map.put("后台设置出现问题，请检查设置", "");
            return map;
        }
        map.put("该课程目前不允许您的人员类别报名，请等候发放权限", "");
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


    @Override
    public int updateAllTime(EduCourse eduCourse) {
        //先查询出所有学校的课程
        List<EduCourse> eduCourses = eduCourseMapper.listCourseCategoryByShoolId(eduCourse);
        EduCourse updateCourse = new EduCourse();
        updateCourse.setStartDate(eduCourse.getStartDate());
        updateCourse.setEndDate(eduCourse.getEndDate());
        int i = 1;
        for (EduCourse eduCours : eduCourses) {
            updateCourse.setCourseId(eduCours.getCourseId());
            //然后对所有学校课程的时间进行修改
            i = eduCourseMapper.updateByPrimaryKeySelective(updateCourse);
            if (i < 1) {
                return -1;
            }
        }
        return i;
    }

    public EduCourseVo applyCourse(Integer userId, Integer courseId) {
        //1.根据courseId查询订单的信息
        EduCourseVo courseInfo = eduCourseMapper.queryCourseByCourseId(courseId);
        //2.根据courseId查询开课的具体时间
        List<EduClass> eduClasses = eduClassMapper.queryCourseTimeByCourseId(courseId);
        //3.生成报名详情信息
        courseInfo.setClassList(eduClasses);


      /*  //4.生成订单关联
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
        orderDetailMapper.insertSelective(orderDetail);*/
        return courseInfo;
    }


}
