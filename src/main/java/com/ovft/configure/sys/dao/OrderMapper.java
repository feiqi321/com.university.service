package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.Order;
import com.ovft.configure.sys.bean.OrderExample;

import java.util.List;


import com.ovft.configure.sys.vo.OrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    /**
     * 生成订单
     *
     * @param order
     */
    int insertSelective(Order order);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    /**
     *查询订单的已付款的个数
     * @param userId
     * @param status
     * @return
     */
    public int queryCourseNumById(int userId, int status);

    /**
     * 根据用户id查询已付款的订单
     * @param userId
     * @return
     */
    List<OrderVo> queryAllOrder(int userId);

}