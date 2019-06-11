package com.ovft.configure.sys.service.impl;

import com.alipay.api.domain.OrderItem;
import com.ovft.configure.constant.OrderStatus;
import com.ovft.configure.sys.bean.*;
import com.ovft.configure.sys.dao.*;
import com.ovft.configure.sys.service.OrderService;
import com.ovft.configure.sys.utils.OrderIdUtil;
import com.ovft.configure.sys.vo.*;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    public void insertCartToOrder(SubmitOrderVos submitOrderVos, HttpServletRequest request) {
        //待加  重复提交订单

        String userId1 = request.getHeader("userId");
        Integer userId = Integer.parseInt(userId1);
        String schoolId = request.getHeader("schoolId");
        BigDecimal accountAllMoney = new BigDecimal(0);
        Order order = new Order();

        order.setUserId(userId);
        order.setOrderSn(OrderIdUtil.getOrderIdByTime());
        order.setOrderStatus(OrderStatus.UNPay);
        order.setCreateTime(new Date());
        order.setTotalAmount(submitOrderVos.getTotalMoney());
        order.setConsignee(submitOrderVos.getUserName());
        order.setTelephone(submitOrderVos.getPhone());
        order.setAddress(submitOrderVos.getAddress());
        order.setSendType(submitOrderVos.getSendType());
        order.setTosendPrice(submitOrderVos.getToSendPrice());
        order.setSchoolId(schoolId);
        order.setRemark(submitOrderVos.getRemark());
        orderMapper.insertSelective(order);

        //生存一对多的关系
        List<OrderDetailVo> orderItemsVo = submitOrderVos.getOrderItemsVo();
        for (OrderDetailVo orderItem : orderItemsVo) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(Long.valueOf(order.getId()));
            orderDetail.setNum(orderItem.getNum());
            orderDetail.setOrderPrice(orderItem.getShopPrice());
            orderDetail.setCourseName(orderItem.getBooksName());
            orderDetail.setImgUrl(orderItem.getOriginalImg());
            orderDetail.setCourseId(Long.valueOf(orderItem.getGoodsId()));
            orderDetailMapper.insertSelective(orderDetail);
            order.setImgUrl(orderItem.getOriginalImg());
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
