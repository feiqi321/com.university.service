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

    public int queryForCourseId(int userId, int status);
}
