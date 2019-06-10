package com.ovft.configure.sys.web;

import com.alibaba.druid.support.spring.stat.annotation.Stat;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ovft.configure.constant.Status;
import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.*;
import com.ovft.configure.sys.dao.AddressMapper;
import com.ovft.configure.sys.service.*;
import com.ovft.configure.sys.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vvtxw
 * @create 2019-04-12 13:38
 */

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

    /**
     * 线上支付记录
     *
     * @param request
     * @return
     */
    @GetMapping(value = "payrecord")
    public WebResult queryAllPayOrder(HttpServletRequest request) {
        String userId1 = request.getHeader("userId");
        if (userId1.equals("null")) {
            return new WebResult(StatusCode.ERROR, "请先登录再查询", "");
        }
        Integer userId = Integer.parseInt(userId1);
//        Integer userId = 59;
        List<OrderVo> orderVos = orderService.queryAllRecord(userId);
        for (OrderVo orderVo : orderVos) {
            //生存支付记录表信息
            EduPayrecord eduPayrecord = new EduPayrecord();
            eduPayrecord.setSchoolId(String.valueOf(orderVo.getSchoolId()));
            eduPayrecord.setSchoolName(orderVo.getSchoolName());
            eduPayrecord.setCourseName(orderVo.getCourseName());
            eduPayrecord.setCourseTeacher(orderVo.getCourseTeacher());
            eduPayrecord.setCourseAddress(orderVo.getAddress());
            eduPayrecord.setStartTime(orderVo.getStartTime());
            eduPayrecord.setEndTime(orderVo.getEndTime());
            SimpleDateFormat sf = new SimpleDateFormat();
            String startDate = sf.format(orderVo.getStartDate());
            eduPayrecord.setCourseStarttime(startDate);
            eduPayrecord.setPayStatus(String.valueOf(orderVo.getPaymentStatus()));
            eduPayrecord.setUserId(userId);
            eduPayrecord.setCourseId(orderVo.getCourseId());
            eduPayrecordService.insertPayRecord(eduPayrecord);
        }


        return new WebResult(StatusCode.OK, "已缴费，报名成功", orderVos);
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
        Page<Object> pageAll = PageHelper.startPage(1, 2);
        //查询购物车是否有此教材
        List<EduCart> eduCarts = eduCartService.queryCartByUserIdAndGoodSId(userId, goodsId);
        if (eduCarts.size() == 0) {
            //先把该商品添加到购物车
            eduCartService.addCart(goodsId, 1, userId);
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
     * 订单的状态 1待付款,2已经取消，3.待收货,4已完成
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
     * 查找总订单详情订单
     * 订单的状态 1待付款,2已经取消，3.待收货,4已完成
     *
     * @param request
     * @return
     */
    @GetMapping(value = "showorders")
    public WebResult showOrders(Integer orderStatus, HttpServletRequest request) {
        String userId1 = request.getHeader("userId");
        Integer userId = Integer.parseInt(userId1);

        List<OrderVo> orderVoList = orderService.showOrders(userId, orderStatus);
        return new WebResult(StatusCode.OK, "提交成功", orderVoList);
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
}
