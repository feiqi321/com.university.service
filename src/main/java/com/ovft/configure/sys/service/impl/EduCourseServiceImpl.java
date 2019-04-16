package com.ovft.configure.sys.service.impl;

import com.ovft.configure.sys.bean.EduClass;
import com.ovft.configure.sys.bean.EduCourse;
import com.ovft.configure.sys.bean.Order;
import com.ovft.configure.sys.dao.EduClassMapper;
import com.ovft.configure.sys.dao.EduCourseMapper;
import com.ovft.configure.sys.dao.OrderMapper;
import com.ovft.configure.sys.service.EduCourseService;
import com.ovft.configure.sys.vo.EduCourseVo;
import com.ovft.configure.sys.utils.OrderIdUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
    public EduCourseVo queryCourseByCourseId(int courseId) {
        //1.根据courseId查询订单的信息
        EduCourseVo courseInfo = eduCourseMapper.queryCourseByCourseId(courseId);
        //2.根据courseId查询开课的具体时间
        List<EduClass> eduClasses = eduClassMapper.queryCourseTimeByCourseId(courseId);
        //3.生成报名详情信息
        courseInfo.setClassList(eduClasses);
        //4.生成订单关联
        Order order = new Order();
        order.setOrderSn(OrderIdUtil.getOrderIdByTime());
        order.setUserId(1);
        order.setCourseId(1);
        order.setPayStatus(0);
        order.setCreateTime(new Date());
        orderMapper.insertSelective(order);
        return courseInfo;

    }

}
