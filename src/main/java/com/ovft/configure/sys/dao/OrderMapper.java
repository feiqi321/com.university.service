package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.Order;
import com.ovft.configure.sys.bean.OrderExample;

import java.util.List;
import java.util.Map;

import com.ovft.configure.sys.vo.OrderVo;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);


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

    //显示详细的相关订单
    List<OrderVo> showOrders(Map<String, Object> map);


    //显示总订单
    List<OrderVo> showOrder(Map<String, Object> map);

    //查询订单的数量
    int countOrderNum(Integer orderId);


}