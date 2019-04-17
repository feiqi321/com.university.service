package com.ovft.configure.sys.service;

import com.ovft.configure.sys.bean.Order;
import com.ovft.configure.sys.vo.OrderVo;

import java.util.List;

/**
 * @author vvtxw
 * @create 2019-04-12 13:33
 */

public interface OrderService {

    /**
     * 根据用户id查询已付款的订单
     * @param userId
     * @return
     */
    List<OrderVo> queryAllOrder(int userId);

    /**
     * 根据用户id查询开课的具体时间点
     * @param userId
     * @return
     */
    List<OrderVo> queryStartDateTimeByCouserId(int userId);

    /**
     * 查询courseId
     * @param userId
     * @param status
     * @return
     */
    public int queryForCourseId(int userId, int status);

    /**
     *查询订单的已付款的个数
     * @param userId
     * @param status
     * @return
     */
    public int queryCourseNumById(int userId, int status);
}
