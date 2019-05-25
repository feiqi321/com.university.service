package com.ovft.configure.sys.web;

import com.alibaba.druid.support.spring.stat.annotation.Stat;
import com.ovft.configure.constant.Status;
import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.*;
import com.ovft.configure.sys.dao.AddressMapper;
import com.ovft.configure.sys.service.*;
import com.ovft.configure.sys.vo.OfflineBean;
import com.ovft.configure.sys.vo.OrderVo;
import com.ovft.configure.sys.vo.PageBean;
import com.ovft.configure.sys.vo.QueryOrderVos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
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

    /**
     * 支付记录
     *
     * @param request
     * @return
     */
    @GetMapping(value = "payrecord")
    public WebResult queryAllPayOrder(HttpServletRequest request) {
        String userId1 = request.getHeader("userId");
        Integer userId = Integer.parseInt(userId1);
//        Integer userId = 59;
        List<OrderVo> orderVos = orderService.queryAllRecord(userId);
        return new WebResult(StatusCode.OK, "已缴费，报名成功", orderVos);
    }

    @GetMapping(value = "offrecord")
    public WebResult queryAllOffRecord(HttpServletRequest request) {
        String userId1 = request.getHeader("userId");
        Integer userId = Integer.parseInt(userId1);
        String schoolId1 = request.getHeader("schoolId");
        Integer schoolId = Integer.parseInt(schoolId1);
        /*Integer userId = 59;
        Integer schoolId = 11;*/
        List<EduOfflineOrder> eduOfflineOrders = eduOfflineOrderService.queryAllOffRecord(userId);
        School school = schoolService.queryRecordBySchoolId(schoolId);


        return new WebResult(StatusCode.OK, "查询成功，报名成功", new OfflineBean(eduOfflineOrders, school.getImage()));
    }

    /**
     * 进入订单展示
     *
     * @param request
     * @return
     */
    @PostMapping(value = "orderinfo")
    public WebResult queryGoSubmitOrderinfo(@RequestBody List<EduCart> eduCart, HttpServletRequest request) {
        /*String userId1 = request.getHeader("userId");
        Integer userId = Integer.parseInt(userId1);*/
        Integer userId = 59;
        for (EduCart cart : eduCart) {
            eduCartService.updateCart(cart);
        }

        PageBean pageBean = eduCartService.showAllCartByUserId(userId);
        List<Address> addresses = addressMapper.selectByUserId(userId);
        QueryOrderVos queryOrderVos = new QueryOrderVos();
        queryOrderVos.setAddress(addresses);
        queryOrderVos.setEduBookGoods(pageBean);
        return new WebResult(StatusCode.OK, "查询成功", queryOrderVos);
    }


    /**
     * 提交订单
     *
     * @param userId
     * @param addressId
     * @param sendType
     * @param toSendPrice
     * @param remark
     * @return
     */
    @GetMapping(value = "submitorder")
    public WebResult submitOrder(Integer userId, Integer addressId, String sendType, BigDecimal toSendPrice, String remark) {
        orderService.insertCartToOrder(userId, addressId, sendType, toSendPrice, remark);
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
       /* String userId1 = request.getHeader("userId");
        Integer userId = Integer.parseInt(userId1);*/
        Integer userId = 59;
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
      /*  String userId1 = request.getHeader("userId");
        Integer userId = Integer.parseInt(userId1);*/
        Integer userId = 59;
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
