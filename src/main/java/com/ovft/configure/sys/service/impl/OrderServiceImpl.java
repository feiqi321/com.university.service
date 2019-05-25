package com.ovft.configure.sys.service.impl;

import com.alipay.api.domain.OrderItem;
import com.ovft.configure.constant.OrderStatus;
import com.ovft.configure.sys.bean.*;
import com.ovft.configure.sys.dao.*;
import com.ovft.configure.sys.service.OrderService;
import com.ovft.configure.sys.utils.OrderIdUtil;
import com.ovft.configure.sys.vo.EduCartVo;
import com.ovft.configure.sys.vo.OrderVo;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

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
    @Resource
    private OrderDetailMapper orderDetailMapper;
    @Resource
    private EduCartMapper eduCartMapper;
    @Resource
    private AddressMapper addressMapper;


    @Override
    public int countPayCourseNum(Map<String, Object> map) {
        return orderMapper.countPayCourseNum(map);
    }

    @Override
    public int countPayCourseEmployerNum(Map<String, Object> map) {
        return 0;
    }

    @Override
    public List<OrderVo> queryAllRecord(Integer userId) {
        return orderMapper.queryAllRecord(userId);
    }

    @Override
    public Order getOrderInfo(String orderId) {
        return orderMapper.selectByPrimaryKey(Integer.parseInt(orderId));
    }

    @Override
    public void insertCartToOrder(Integer userId, Integer addressId, String sendType, BigDecimal toSendPrice, String remark) {
        //查出购物车的相关信息
        List<Object> eduCarts = eduCartMapper.selectCartByUserId(userId);
        //查出学员的收货地址
        Address address = addressMapper.selectByAddressId(addressId);

        BigDecimal accountAllMoney = new BigDecimal(0);
        for (Object eduCart : eduCarts) {
            Order order = new Order();
            EduCartVo eduCartVo = (EduCartVo) eduCarts;
            accountAllMoney = accountAllMoney.add(eduCartVo.getShopPrice());
            order.setTradeBody(eduCartVo.getBooksName());
            order.setUserId(userId);
            order.setOrderSn(OrderIdUtil.getOrderIdByTime());
            order.setOrderStatus(OrderStatus.UNREMOVE);
            order.setCreateTime(new Date());
            order.setTotalAmount(accountAllMoney.longValue());
            order.setConsignee(address.getConsigneeName());
            order.setTelephone(address.getPhone());
            order.setAddress(address.getAddress());
            order.setSendType(sendType);
            order.setTosendPrice(toSendPrice);
            order.setRemark(remark);
            orderMapper.insertSelective(order);
        }
    }

    @Override
    public List<OrderVo> showOrders(Integer userId, Integer orderStatus) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("orderStatus", orderStatus);

        List<OrderVo> orderVoList = orderMapper.showOrders(map);
     /*   for (OrderVo orderVo : orderVoList) {
            Integer num = orderDetailMapper.countOneOrderNum(orderVo.getUserId(), orderVo.getCourseId());
        }*/
        return orderVoList;
    }

    @Override
    public List<OrderVo> showOrder(Integer userId, Integer orderStatus) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("orderStatus", orderStatus);

        List<OrderVo> orderVoList = orderMapper.showOrder(map);
        for (OrderVo orderVo : orderVoList) {
            int num = orderMapper.countOrderNum(orderVo.getId());
            orderVo.setNum(num);
        }
        return orderVoList;
    }

    @Override
    public int offAndSubmitOrders(Order order) {
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public int deleteOrder(Order order) {

        int rs = orderMapper.deleteByPrimaryKey(order.getId());

        List<OrderDetail> orderDetails = orderDetailMapper.selectByOrderId(order.getId());
        int rs2 = -1;
        for (OrderDetail orderDetail : orderDetails) {
            rs2 = orderDetailMapper.deleteByPrimaryKey(orderDetail.getId());
        }
        if (rs > 0 && rs2 > 0) {
            return 1;
        }
        return -1;
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
