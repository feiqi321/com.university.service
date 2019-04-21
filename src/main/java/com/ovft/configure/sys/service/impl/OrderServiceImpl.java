package com.ovft.configure.sys.service.impl;

import com.ovft.configure.sys.dao.EduCourseMapper;
import com.ovft.configure.sys.dao.OrderMapper;
import com.ovft.configure.sys.service.OrderService;
import com.ovft.configure.sys.vo.OrderVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author vvtxw
 * @create 2019-04-12 13:33
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private EduCourseMapper eduCourseMapper;
    @Resource
    private OrderMapper orderMapper;

    @Override
    public int countPayCourseNum(Map<String, Object> map) {
        return orderMapper.countPayCourseNum(map);
    }

    @Override
    public int countPayCourseEmployerNum(Map<String, Object> map) {
        return 0;
    }



    /*
     */
/**
 * 根据用户id查询已付款的订单
 * @param userId
 * @return
 *//*

    @Override
    public List<OrderVo> queryAllOrder(int userId) {
        return orderMapper.queryAllOrder(userId);
    }
*/

    /*    *//**
     * 根据用户id查询开课的具体时间点
     * @param userId
     * @return
     *//*
    public List<OrderVo> queryStartDateTimeByCouserId(int userId) {
        return orderMapper.queryAllOrder(userId);
    }


    *//**
     * 查询courseId
     * @param userId
     * @param status
     * @return
     *//*
    @Override
    public int queryForCourseId(int userId, int status) {
        return orderMapper.queryCourseNumById(userId,status);
    }*/

}
