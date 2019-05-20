//package com.ovft.configure.sys.web;
//
//import com.alibaba.fastjson.JSON;
//import com.alipay.api.AlipayApiException;
//import com.alipay.api.AlipayClient;
//import com.alipay.api.DefaultAlipayClient;
//import com.alipay.api.domain.AlipayTradeWapPayModel;
//import com.alipay.api.request.AlipayTradePagePayRequest;
//import com.alipay.api.request.AlipayTradeWapPayRequest;
//import com.ovft.configure.config.AlipayConfig;
//import com.ovft.configure.constant.OrderStatus;
//import com.ovft.configure.sys.bean.EduClass;
//import com.ovft.configure.sys.bean.Order;
//import com.ovft.configure.sys.bean.OrderDetail;
//import com.ovft.configure.sys.bean.PaymentInfo;
//import com.ovft.configure.sys.dao.EduClassMapper;
//import com.ovft.configure.sys.dao.EduCourseMapper;
//import com.ovft.configure.sys.dao.OrderDetailMapper;
//import com.ovft.configure.sys.dao.OrderMapper;
//import com.ovft.configure.sys.service.OrderService;
//import com.ovft.configure.sys.service.paymentService;
//import com.ovft.configure.sys.utils.OrderIdUtil;
//import com.ovft.configure.sys.vo.EduCourseVo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author vvtxw
// * @create 2019-05-05 17:35
// */
//
//@RestController
//@RequestMapping("pay")
//public class PaymentInfoController {
//    @Autowired
//    private OrderService orderService;
//
//    @Autowired
//    private paymentService paymentService;
//    @Resource
//    private EduCourseMapper eduCourseMapper;
//    @Resource
//    private EduClassMapper eduClassMapper;
//    @Resource
//    private OrderMapper orderMapper;
//    @Resource
//    private OrderDetailMapper orderDetailMapper;
//
//
//    @GetMapping(value = "alipay/submit")
//    public ResponseEntity<String> paymentAlipay(@RequestParam("courseId") Integer courseId, HttpServletRequest request, HttpServletResponse httpServletResponse) throws IOException {
//
//        String userId1 = request.getHeader("userId");
//        Integer userId = Integer.parseInt(userId1);
//        //1.根据courseId查询订单的信息
//        EduCourseVo courseInfo = eduCourseMapper.queryCourseByCourseId(courseId);
//       /* //2.根据courseId查询开课的具体时间
//        List<EduClass> eduClasses = eduClassMapper.queryCourseTimeByCourseId(courseId);
//        //3.生成报名详情信息
//        courseInfo.setClassList(eduClasses);*/
//
//
//        //4.生成订单关联
//        Order order = new Order();
//
//        //生成订单的订单号
//        order.setOrderSn(OrderIdUtil.getOrderIdByTime());
//        order.setUserId(userId);
//        order.setCreateTime(new Date());
//        order.setOrderStatus(OrderStatus.UNREMOVE);//未取消
//        order.setTotalAmount(courseInfo.getCoursePrice().longValue());
//        order.setTradeBody(courseInfo.getCourseName());
//        orderMapper.insertSelective(order);
//
//        OrderDetail orderDetail = new OrderDetail();
//        orderDetail.setOrderId(order.getId().longValue());
//        orderDetail.setCourseId(courseId.longValue());
//        orderDetail.setCourseName(courseInfo.getCourseName());
//        orderDetail.setOrderPrice(courseInfo.getCoursePrice());
//        orderDetail.setNum(1);
//        orderDetailMapper.insertSelective(orderDetail);
//
//        if (String.valueOf(order.getId()) == null || String.valueOf(order.getId()).length() == 0) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//
//        //获取订单信息
//        Order orderInfo = orderService.getOrderInfo(String.valueOf(order.getId()));
//        if (orderInfo == null) {
//            //没有对应的订单
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//        //保存支付信息
//        PaymentInfo paymentInfo = new PaymentInfo();
//        paymentInfo.setOrderId(String.valueOf(order.getId()));
//        // 商户订单号，商户网站订单系统中唯一订单号，必填
//        paymentInfo.setOutTradeNo(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + (int) (Math.random() * 90000 + 88888));
//        // 订单名称，必填
//        paymentInfo.setSubject(orderInfo.getTradeBody());
//
//        paymentInfo.setPaymentStatus(String.valueOf(OrderStatus.PAY));
//        // 付款金额，必填
//        paymentInfo.setTotalAmount(orderInfo.getTotalAmount());
//
//        paymentService.savePaymentInfo(paymentInfo);
//
//        AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID,
//                AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET,
//                AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
//        //利用支付宝客户端生成表单页面
//        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
//        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
//
//        alipayRequest.setReturnUrl(AlipayConfig.return_url);
//        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
//
//        model.setOutTradeNo(paymentInfo.getOutTradeNo());
//        model.setSubject(paymentInfo.getSubject());
//        model.setTotalAmount(paymentInfo.getTotalAmount().toString());
//        model.setBody(paymentInfo.getSubject());
//        model.setTimeoutExpress("2m");
//        model.setProductCode("QUICK_WAP_WAY");
//        alipayRequest.setBizModel(model);
//
//
//        String form = "";
//        try {
//            form = client.pageExecute(alipayRequest).getBody();
//            httpServletResponse.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
//        } catch (AlipayApiException e) {
//            e.printStackTrace();
//        }
//        //把表单html打印到客户端浏览器
//        return ResponseEntity.ok().body(form);
//    }
//
//    @RequestMapping(value = "/alipay/callback/return", method = RequestMethod.GET)
//    public String callbackReturn(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
//        System.out.println(" callback return to " + AlipayConfig.return_order_url);
//        return "redirect:" + AlipayConfig.return_order_url;
//    }
//
//
//}
