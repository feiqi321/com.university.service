package com.ovft.configure.sys.service.impl;


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
        String userId1 = request.getHeader("userId");
        Integer userId = Integer.parseInt(userId1);
        String schoolId = request.getHeader("schoolId");

        //生存教材订单
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


        //重复提交订单查询数据表是否有此教材的订单，
        List<OrderDetailVo> orderItemsVo = submitOrderVos.getOrderItemsVo();
        //生存一对多的关系
        for (OrderDetailVo orderItem : orderItemsVo) {
//            OrderDetailExample orderDetailExample = new OrderDetailExample();
//            orderDetailExample.createCriteria().andCourseIdEqualTo(Long.valueOf(orderItem.getGoodsId()));
//            List<OrderDetail> orderDetails = orderDetailMapper.selectByExample(orderDetailExample);
//            if (orderDetails.size() == 0) {
            OrderDetail orderDetail = new OrderDetail();
                /*if (order.getId() == null) {
                    orderMapper.insertSelective(order);
                }*/
            //生成订单详情
            orderDetail.setOrderId(Long.valueOf(order.getId()));
            orderDetail.setNum(orderItem.getNum());
            orderDetail.setOrderPrice(orderItem.getShopPrice());
            orderDetail.setCourseName(orderItem.getBooksName());
            orderDetail.setImgUrl(orderItem.getOriginalImg());
            orderDetail.setCourseId(Long.valueOf(orderItem.getGoodsId()));
            orderDetail.setSchoolId(orderItem.getSchoolId());
            orderDetail.setSchoolName(orderItem.getSchoolName());
            orderDetail.setBookAuthor(orderItem.getBooksAuthor());

            orderDetailMapper.insertSelective(orderDetail);
            order.setImgUrl(orderItem.getOriginalImg());
            orderMapper.updateByPrimaryKeySelective(order);

            //删除购物车数据
            EduCartExample eduCartExample = new EduCartExample();
            eduCartExample.createCriteria().andUserIdEqualTo(userId).andGoodsIdEqualTo(orderItem.getGoodsId());
            eduCartMapper.deleteByExample(eduCartExample);
            /*    return;
            } else {
                return;
            }*/
        }


    }

    @Override
    public List<OrderVo> showOrder(Integer userId, Integer orderStatus) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("orderStatus", orderStatus);
        map.put("resourceType", 1);

        List<OrderVo> orderVoList = orderMapper.showOrder(map);

        for (OrderVo orderVo : orderVoList) {
            //根据订单id查询教材名称
            List<OrderDetail> orderDetails = orderDetailMapper.selectByOrderId(orderVo.getId());
            for (OrderDetail orderDetail : orderDetails) {
                orderVo.setCourseName(orderDetail.getCourseName());
            }
            //根据订单id查询课程的门数
            OrderDetailExample orderDetailExample = new OrderDetailExample();
            orderDetailExample.createCriteria().andOrderIdEqualTo(Long.valueOf(orderVo.getId()));
            long num = orderDetailMapper.countByExample(orderDetailExample);
            int num2 = (int) num;
            orderVo.setNum(num2);
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

    @Override
    public SubmitOrderVos showOrders(OrderVo orderVo) {
        SubmitOrderVos submitOrderVos = new SubmitOrderVos();
        submitOrderVos.setAddress(orderVo.getAddress());
        submitOrderVos.setPhone(orderVo.getTelephone());
        submitOrderVos.setSendType(orderVo.getSendType());
        submitOrderVos.setToSendPrice(orderVo.getTosendPrice());
        submitOrderVos.setRemark(orderVo.getRemark());
        submitOrderVos.setTotalMoney(orderVo.getTotalAmount());
        submitOrderVos.setOrderSn(orderVo.getOrderSn());
        submitOrderVos.setCreateTime(orderVo.getCreateTime());
        submitOrderVos.setPaymentWay(orderVo.getPaymentWay());
        submitOrderVos.setPaymentTime(orderVo.getPaymentTime());
        submitOrderVos.setUserName(orderVo.getConsignee());
        //查询课程列表
        List<OrderDetailVo> orderDetails = orderDetailMapper.selectById(orderVo.getId());
        submitOrderVos.setOrderItemsVo(orderDetails);
        return submitOrderVos;
    }

    @Override
    public Integer updateOrderStatus(Order order) {
        int i = orderMapper.updateByPrimaryKeySelective(order);
        return i;
    }

    @Override
    public int sureOder(OrderVo order) {
        return orderMapper.updateByPrimaryKeySelective(order);
    }


}
