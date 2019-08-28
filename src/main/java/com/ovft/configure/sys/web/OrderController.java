package com.ovft.configure.sys.web;

import com.alibaba.druid.support.spring.stat.annotation.Stat;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ovft.configure.constant.OrderStatus;
import com.ovft.configure.constant.PaymentStatus;
import com.ovft.configure.constant.Status;
import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.*;
import com.ovft.configure.sys.dao.AddressMapper;
import com.ovft.configure.sys.dao.OrderMapper;
import com.ovft.configure.sys.dao.PaymentInfoMapper;
import com.ovft.configure.sys.service.*;
import com.ovft.configure.sys.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author vvtxw
 * @create 2019-04-12 13:38
 */

@EnableScheduling
@RestController
@RequestMapping("record")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private EduCartService eduCartService;

    @Resource
    private AddressMapper addressMapper;

    @Autowired
    private EduOfflineOrderService eduOfflineOrderService;

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private UserService userService;

    @Autowired
    private EduPayrecordService eduPayrecordService;
    @Autowired
    private EduCourseService eduCourseService;
    @Resource
    private PaymentInfoMapper paymentInfoMapper;

    @Resource
    private OrderMapper orderMapper;


    /**
     * 线上支付记录
     *
     * @param request
     * @return
     */
    @GetMapping(value = "payrecord")
    @Transactional
    public WebResult queryAllPayOrder(HttpServletRequest request) {
        String userId1 = request.getHeader("userId");
        if (userId1.equals("null")) {
            return new WebResult(StatusCode.ERROR, "请先登录再查询", "");
        }
        Integer userId = Integer.parseInt(userId1);

        //查出订单付款信息
//        List<OrderVo> orderVos = orderService.queryAllRecord(userId);
        List<OrderVo> orderVos = orderMapper.queryAllRecord(userId);
        List<EduPayrecord> eduPayrecords = eduPayrecordService.queryAllRecordByUserId(userId);


        //更新支付记录
        checkDeletePayRecord(orderVos, userId);


        for (OrderVo orderVo : orderVos) {
            //生存支付记录表信息
            if (eduPayrecords.size() == 0&&orderVo.getOrderStatus()==5) {
                creatPayRecord(userId, orderVo);
            }
        }
//        if (orderVos.size() != eduPayrecords.size() && eduPayrecords.size() > 0) {
//            for (EduPayrecord eduPayrecord : eduPayrecords) {
//                eduPayrecord.setIsDelete(1);
//                eduPayrecordService.updateIsDeleteStaues(eduPayrecord);
//            }
//            for (OrderVo orderVo : orderVos) {
//                //生存支付记录表信息
//                if (eduPayrecords.size() == 0&&orderVo.getOrderStatus()==5) {
//                    creatPayRecord(userId, orderVo);
//                }
//            }
//        }
     /*   if (orderVos.size() == eduPayrecords.size() && eduPayrecords.size() > 0) {

        }*/

        List<OrderVo>  orderVos2=new LinkedList();

        for (int i=0;i<orderVos.size();i++) {
            //生存支付记录表信息
            if (orderVos.get(i).getOrderStatus()==5) {
                orderVos2.add(orderVos.get(i));
            }
        }
//           EduPayrecord eduPayrecords2=new EduPayrecord();
//        eduPayrecords2.setUserId(userId);
//        List<EduPayrecord> eduPayrecords3 = eduPayrecordService.selectByUserIdAndIsdelete(eduPayrecords2);
        return new WebResult(StatusCode.OK, "已缴费，报名成功", orderVos2);
    }

    @Transactional
    Integer checkDeletePayRecord(List<OrderVo> orderVos, Integer userId) {
        //1.获取当天的时间
        Date nowTime = new Date();
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
        String formatnowTime = fm.format(nowTime);//获取当前日期字符串
        //2.获取用户对应购买课程id结束时间
        EduCourse eduCourse = null;
        for (OrderVo orderVo : orderVos) {
            eduCourse = eduCourseService.queryEndTimeByCourseId(orderVo.getCourseId());
            //3.如果当前时间==用户购买课程所有的结束时间 则删除用户对应的购买记录
            Date endDate = eduCourse.getEndDate();
            String endDateString = fm.format(endDate);
            if (formatnowTime.equals(endDateString)) {
                //4.假删除购买的对应的课程记录
                EduPayrecord eduPayrecord=new EduPayrecord();
                eduPayrecord.setIsDelete(1);
                eduPayrecord.setCourseId(orderVo.getCourseId());
                Integer res = eduPayrecordService.updateIsDeleteStaues(eduPayrecord);

                //5.更改支付的状态为已关闭
                PaymentInfo paymentInfo = new PaymentInfo();
                paymentInfo.setPaymentStatus(PaymentStatus.ClOSED);
                paymentInfo.setOrderId(String.valueOf(orderVo.getId()));
                PaymentInfoExample paymentInfoExample = new PaymentInfoExample();
                paymentInfoExample.createCriteria().andOrderIdEqualTo(paymentInfo.getOrderId());
                int res1 = paymentInfoMapper.updateByExampleSelective(paymentInfo, paymentInfoExample);

                //6.更改订单的支付状态为已完成
                Order order = new Order();
                order.setOrderStatus(OrderStatus.FINISH);
                order.setId(orderVo.getId());
                Integer res2 = orderService.updateOrderStatus(order);
                if (res > 0 && res1 > 0 && res2 > 0) {
                    return res;
                }
            }
        }
        return -1;
    }


    private void creatPayRecord(Integer userId, OrderVo orderVo) {
        EduPayrecord eduPayrecord = new EduPayrecord();
        eduPayrecord.setSchoolId(String.valueOf(orderVo.getSchoolId()));
        eduPayrecord.setSchoolName(orderVo.getSchoolName());
        eduPayrecord.setCourseName(orderVo.getCourseName());
        eduPayrecord.setCourseTeacher(orderVo.getCourseTeacher());
        eduPayrecord.setCourseAddress(orderVo.getAddress());
        eduPayrecord.setStartTime(orderVo.getStartTime());
        eduPayrecord.setEndTime(orderVo.getEndTime());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd");
        String startDate = sf.format(orderVo.getStartDate());
        eduPayrecord.setCourseStarttime(startDate);
        eduPayrecord.setPayStatus(String.valueOf(OrderStatus.PAY));
        eduPayrecord.setUserId(userId);
        eduPayrecord.setCourseId(orderVo.getCourseId());
        eduPayrecord.setIsDelete(2);
        eduPayrecordService.insertPayRecord(eduPayrecord);
    }

    /**
     * 线下报名记录
     *
     * @param request
     * @return
     */
    @GetMapping(value = "offrecord")
    public WebResult queryAllOffRecord(HttpServletRequest request) {
        String userId1 = request.getHeader("userId");
        if (userId1.equals("null")) {
            return new WebResult(StatusCode.ERROR, "请先登录再查询", "");
        }
        Integer userId = Integer.parseInt(userId1);
        if (userId.equals("null")) {
            new WebResult(StatusCode.ERROR, "请先登录，然后查询", "");
        }
        String schoolId1 = request.getHeader("schoolId");
        Integer schoolId = Integer.parseInt(schoolId1);
        List<EduOfflineOrder> eduOfflineOrders = eduOfflineOrderService.queryAllOffRecord(userId);
        School school = schoolService.queryRecordBySchoolId(schoolId);
        OfflineBean offlineBean = null;
        for (EduOfflineOrder eduOfflineOrder : eduOfflineOrders) {
            offlineBean = new OfflineBean(eduOfflineOrders, school.getImage(), eduOfflineOrder.getUserName(), eduOfflineOrder.getSchoolName());
        }
        return new WebResult(StatusCode.OK, "查询成功，报名成功", offlineBean);
    }

    /**
     * 取消线下订单
     *
     * @param eduOfflineOrder
     * @return
     */
    @PostMapping(value = "delete")
    public WebResult deleteOffRecord(@RequestBody EduOfflineOrder eduOfflineOrder) {
        Integer res = eduOfflineOrderService.deleteOffOrderByUserPhone(eduOfflineOrder);
        if (res > 0) {
            return new WebResult(StatusCode.OK, "取消成功", "");
        }
        return new WebResult(StatusCode.OK, "取消失败", "");
    }


    /**
     * 购物车进入订单展示
     *
     * @param request
     * @return
     */
    @PostMapping(value = "orderinfo")
    public WebResult queryGoSubmitOrderinfo(@RequestBody List<EduCart> eduCart, HttpServletRequest request) {
        String userId1 = request.getHeader("userId");
        Integer userId = Integer.parseInt(userId1);
        Page<Object> pageAll = PageHelper.startPage(1, 2);
        List<EduCart> allInfo = new ArrayList<>();
        for (EduCart cart : eduCart) {
            //修改订单数量
            eduCartService.updateCart(cart);
            //通过学员id和商品id查询订单详情信息
            EduCartVo OrderIfoFromOrder = eduCartService.queryOrderInfoFromCart(userId, cart.getGoodsId());
            allInfo.add(OrderIfoFromOrder);
        }
        long total = pageAll.getTotal();

        List<Address> addresses = addressMapper.selectByUserId(userId);
        QueryOrderVos queryOrderVos = new QueryOrderVos();
        queryOrderVos.setAddress(addresses);
        queryOrderVos.setEduBookGoods(new PageBean(1, 2, total, allInfo));
        return new WebResult(StatusCode.OK, "查询成功", queryOrderVos);
    }

    /**
     * 立即购买
     *
     * @param goodsId
     * @param request
     * @return
     */
    @GetMapping(value = "nowtopay")
    public WebResult nowToPay(Integer goodsId, HttpServletRequest request) {
        String userId1 = request.getHeader("userId");
        Integer userId = Integer.parseInt(userId1);

        String schoolId1 = request.getHeader("schoolId");
        //判断报名的学校不能为空
        if (schoolId1.equals("null")) {
            return new WebResult(StatusCode.ERROR, "您尚未报名学校，请填写基本信息报名学校", "");
        }
        Integer schoolId = Integer.valueOf(schoolId1);
        Page<Object> pageAll = PageHelper.startPage(1, 2);
        //查询购物车是否有此教材
        List<EduCart> eduCarts = eduCartService.queryCartByUserIdAndGoodSId(userId, goodsId);
        if (eduCarts.size() == 0) {
            //先把该商品添加到购物车
            eduCartService.addCart(goodsId, 1, userId, schoolId);
        }
        EduCartVo OrderIfoFromOrder = eduCartService.queryOrderInfoFromCart(userId, goodsId);
        long total = pageAll.getTotal();
        List<Address> addresses = addressMapper.selectByUserId(userId);
        QueryOrderVos queryOrderVos = new QueryOrderVos();
        queryOrderVos.setAddress(addresses);
        queryOrderVos.setEduBookGoods(new PageBean(total, OrderIfoFromOrder));
        return new WebResult(StatusCode.OK, "查询成功", queryOrderVos);
    }


    /**
     * 提交订单
     */
    @PostMapping(value = "submitorder")
    public WebResult submitOrder(@RequestBody SubmitOrderVos submitOrderVos, HttpServletRequest request) {
        orderService.insertCartToOrder(submitOrderVos, request);
        return new WebResult(StatusCode.OK, "提交成功");
    }

    /**
     * 查找总订单
     * 订单的状态 1待付款 ,2已经取消，3.待收货,4已完成
     *
     * @param request
     * @return
     */
    @GetMapping(value = "showorder")
    public WebResult showOrder(Integer orderStatus, HttpServletRequest request) {
        String userId1 = request.getHeader("userId");
        Integer userId = Integer.parseInt(userId1);

        List<OrderVo> orderVoList = orderService.showOrder(userId, orderStatus);
        return new WebResult(StatusCode.OK, "提交成功", orderVoList);
    }

    /**
     * 查找总订单  各个订单订单详情
     * 订单的状态 1待付款,2已经取消，3.待收货,4已完成
     *
     * @param request
     * @return
     */
    @PostMapping(value = "showorders")
    public WebResult showOrders(@RequestBody OrderVo orderVo, HttpServletRequest request) {
        String userId1 = request.getHeader("userId");
        Integer userId = Integer.parseInt(userId1);
        SubmitOrderVos submitOrderVos = orderService.showOrders(orderVo);
        return new WebResult(StatusCode.OK, "提交成功", submitOrderVos);
    }


    /**
     * 取消订单
     * 订单的状态 1待付款,2已经取消，3.待收货,4已完成
     *
     * @param order
     * @return
     */
    @PostMapping(value = "offandsubmitorder")
    public WebResult offAndSubmitOrders(@RequestBody OrderVo order) {
        int result = orderService.offAndSubmitOrders(order);
        if (result > 0) {

            return new WebResult(StatusCode.OK, "取消成功");
        }
        return new WebResult(StatusCode.OK, "取消失败");
    }

    /**
     * 删除订单
     * 订单的状态 1待付款,2已经取消，3.待收货,4已完成
     *
     * @param order
     * @return
     */
    @PostMapping(value = "deleteorder")
    public WebResult deleteOrder(@RequestBody OrderVo order) {
        int result = orderService.deleteOrder(order);
        if (result > 0) {
            return new WebResult(StatusCode.OK, "删除成功");
        }
        return new WebResult(StatusCode.ERROR, "删除失败");
    }

    /**
     * 完成订单
     * 订单的状态 1待付款,2已经取消，3.待收货,4已完成
     *
     * @param order
     * @return
     */
    @PostMapping(value = "sureorder")
    public WebResult sureOder(@RequestBody OrderVo order) {
        int result = orderService.sureOder(order);
        if (result > 0) {
            return new WebResult(StatusCode.OK, "已完成成功");
        }
        return new WebResult(StatusCode.ERROR, "已完成失败");
    }


}
