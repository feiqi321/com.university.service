package com.ovft.configure.sys.service;

import com.ovft.configure.sys.vo.OrderVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author vvtxw
 * @create 2019-04-12 13:33
 */

public interface OrderService {

    /**
     * 按支付状态统计总人数
     *
     * @param map
     * @return
     */
    public int countPayCourseNum(Map<String, Object> map);


    /**
     * 按学员的类别支付状态统计总人数
     *
     * @param map
     * @return
     */
    public int countPayCourseEmployerNum(Map<String, Object> map);

    /**
     * 查询所有的购买记录
     *
     * @param userId
     * @return
     */
    public List<OrderVo> queryAllRecord(Integer userId);






    /*  *//**
     * 根据用户id查询已付款的订单
     * @param userId
     * @return
     *//*
    List<OrderVo> queryAllOrder(int userId);*/

    /*  *//**
     * 根据用户id查询开课的具体时间点
     * @param userId
     * @return
     *//*
    List<OrderVo> queryStartDateTimeByCouserId(int userId);
*/
    /*  *//**
     * 查询courseId
     * @param userId
     * @param status
     * @return
     *//*
    public int queryForCourseId(int userId, int status);

    *//**
     *查询订单的已付款的个数
     * @param userId
     * @param status
     * @return
     *//*
    public int queryCourseNumById(int userId, int status);*/
}
