package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.OrderDetail;
import com.ovft.configure.sys.bean.OrderDetailExample;

import java.util.List;

import com.ovft.configure.sys.vo.OrderDetailVo;
import org.apache.ibatis.annotations.Param;

public interface OrderDetailMapper {
    long countByExample(OrderDetailExample example);

    int deleteByExample(OrderDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    List<OrderDetail> selectByExample(OrderDetailExample example);

    OrderDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderDetail record, @Param("example") OrderDetailExample example);

    int updateByExample(@Param("record") OrderDetail record, @Param("example") OrderDetailExample example);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);

    //查询所有的订单详情
    List<OrderDetail> selectByOrderId(Integer orderId);

    //查询所有的订单详情
    List<OrderDetailVo> selectById(Integer id);
}