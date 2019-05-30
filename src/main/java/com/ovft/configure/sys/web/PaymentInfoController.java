package com.ovft.configure.sys.web;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.mapping.AlipayFieldMethod;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.ovft.configure.config.AlipayConfig;
import com.ovft.configure.constant.OrderStatus;
import com.ovft.configure.constant.Status;
import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.*;
import com.ovft.configure.sys.dao.EduClassMapper;
import com.ovft.configure.sys.dao.EduCourseMapper;
import com.ovft.configure.sys.dao.OrderDetailMapper;
import com.ovft.configure.sys.dao.OrderMapper;
import com.ovft.configure.sys.service.*;
import com.ovft.configure.sys.utils.OrderIdUtil;
import com.ovft.configure.sys.vo.EduCourseVo;
import com.ovft.configure.sys.vo.PageBean;
import com.ovft.configure.sys.vo.QueryOffLineVos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author vvtxw
 * @create 2019-05-05 17:35
 */

@RestController
@RequestMapping("pay")
public class PaymentInfoController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private paymentService paymentService;
    @Resource
    private EduCourseMapper eduCourseMapper;
    @Resource
    private EduClassMapper eduClassMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private EduOfflineService eduOfflineService;

    @Autowired
    private UserService userService;

    @Autowired
    private EduOfflineOrderService eduOfflineOrderService;

    @Autowired
    private EduRegistService eduRegistService;

    @Autowired
    private EduOfflineAddressService eduOfflineAddressService;
    @Autowired
    private EduOfflineOrderitemService eduOfflineOrderitemService;
    @Autowired
    private EduOfflinePayInfoService eduOfflinePayInfoService;


    @GetMapping(value = "showonoroff")
    public WebResult sendToStatus(Integer courseId, HttpServletRequest request) {
        String schoolId1 = request.getHeader("schoolId");
        int schoolId = Integer.parseInt(schoolId1);
//        Integer schoolId = 11;
        int status = eduRegistService.queryOffRegist(schoolId, courseId);
        return new WebResult(StatusCode.OK, "查询成功", status);
    }


    @GetMapping(value = "alipay/submit")
    public WebResult paymentAlipay(@RequestParam("courseId") Integer courseId, Integer type, HttpServletRequest request, HttpServletResponse httpServletResponse) throws IOException {
        String schoolId1 = request.getHeader("schoolId");
        int schoolId = Integer.parseInt(schoolId1);
//        Integer schoolId = 11;

        if (type == 2) {
            ResponseEntity<String> stringResponseEntity = alipayMethod(courseId, request, httpServletResponse);
            return new WebResult(StatusCode.OK, "支付成功", stringResponseEntity);
        }

        if (type == 3) {
            int i = offlineRegist(request, courseId);
            List<EduOfflineAddresstime> eduOfflineAddresstimes = eduOfflineAddressService.queryAddressByschoolId(schoolId);
            for (EduOfflineAddresstime eduOfflineAddresstime : eduOfflineAddresstimes) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                String startTime = date.format(eduOfflineAddresstime.getPaymentStarttime());
                String endtTime = date.format(eduOfflineAddresstime.getPaymentStarttime());
                if (i > 0) {
                    return new WebResult(StatusCode.OK, "请在报名时间：" + startTime + "至" + endtTime + "之前到" + eduOfflineAddresstime.getPaymentAddress() + "缴费，否者自动取消");
                }
            }
            if (i == -2) {
                return new WebResult(StatusCode.OK, "您已经报名该课程，不要重复报名");
            }
        }
        return new WebResult(StatusCode.ERROR, "报名失败", "");
    }

    private int offlineRegist(HttpServletRequest request, Integer courseId) {
        //生存线下报名记录
        String userId1 = request.getHeader("userId");
        Integer userId = Integer.parseInt(userId1);
//        Integer userId = 59;

        String schoolId1 = request.getHeader("schoolId");
        Integer schoolId = Integer.parseInt(schoolId1);
      /*  String schoolId1 = "11";
        Integer schoolId = 11;*/


//        Integer userId = 59;
        //查询学员的基本信息
        User user = userService.queryInfo(userId);
        //查询学员的课程信息
        EduCourseVo eduCourseVo = eduCourseMapper.queryCourseInfo(courseId);

        //查询是否有订单，如果已经下单了，返回信息
        List<EduOfflineOrder> oldOrder = eduOfflineOrderService.queryOffRecord(user.getUserId(), courseId);
        if (oldOrder.size() > 0) {
            return -2;
        }

        EduOfflineOrder eduOfflineOrder = new EduOfflineOrder();
        eduOfflineOrder.setUserId(userId);
        eduOfflineOrder.setUserName(user.getUserName());
        eduOfflineOrder.setPhone(user.getPhone());
        eduOfflineOrder.setCourseId(courseId);
        eduOfflineOrder.setCourseTeacher(eduCourseVo.getName());
        eduOfflineOrder.setPayStatus(String.valueOf(Status.UNPAY));
        eduOfflineOrder.setCourseName(eduCourseVo.getCourseName());
        eduOfflineOrder.setCousePrice(eduCourseVo.getCoursePrice());
        eduOfflineOrder.setSchoolId(Integer.parseInt(eduCourseVo.getSchoolId()));
        eduOfflineOrder.setSchoolName(eduCourseVo.getSchoolName());

        EduOfflineOrderitem eduOfflineOrderitem = new EduOfflineOrderitem();
        eduOfflineOrderitem.setCouserName(eduCourseVo.getCourseName());
        eduOfflineOrderitem.setCouserPrice(eduCourseVo.getCoursePrice());
        eduOfflineOrderitem.setPayStatus(String.valueOf(Status.UNPAY));
        eduOfflineOrderitem.setCourseId(courseId);
        eduOfflineOrderitem.setSchoolId(Integer.parseInt(eduCourseVo.getSchoolId()));
        eduOfflineOrderitem.setUserId(userId);


        int res = eduOfflineOrderService.updateOffOrder(eduOfflineOrder);
        int res1 = eduOfflineOrderitemService.addItemOrder(eduOfflineOrderitem);

        QueryOffLineVos queryOffLineVos = eduOfflineService.queryAllOffInfo(schoolId1, userId);

        Integer res2 = 0;
        EduOfflinePayInfo eduOfflinePayInfo = new EduOfflinePayInfo();
        eduOfflinePayInfo.setSchoolId(Integer.parseInt(eduCourseVo.getSchoolId()));
        eduOfflinePayInfo.setSchoolName(queryOffLineVos.getSchoolName());
        eduOfflinePayInfo.setUserName(queryOffLineVos.getUserName());
        eduOfflinePayInfo.setTelephone(queryOffLineVos.getPhone());
        List<EduOfflineOrderitem> orderitems = queryOffLineVos.getOrderitems();


        String s = "";
//        eduOfflinePayInfo.setListOrder(orderitem.getCouserName() + "：【" + (orderitem.getPayStatus().equals("1") ? "已支付" : "未支付") + "】")
        for (int i = 0; i < orderitems.size(); i++) {
            s = s + "<br> 【" + orderitems.get(i).getCouserName() + "】:\t￥" + orderitems.get(i).getCouserPrice() + "元\t\t<color style='color:red'>(" + (orderitems.get(i).getPayStatus().equals("1") ? "已支付" : "未支付") + ") </color>";
        }
        eduOfflinePayInfo.setListOrder(s);
        eduOfflinePayInfo.setAccountMoney(queryOffLineVos.getAccountAllMoney());
        eduOfflinePayInfo.setPayStatus(String.valueOf(Status.UNPAY));
        eduOfflinePayInfo.setPayUpdatetime(new Date());
        res2 = eduOfflinePayInfoService.insertPayInfo(eduOfflinePayInfo);

        if (res > 0 && res1 > 0 && res2 > 0) {
            return 1;
        }
        return -1;
    }


    private ResponseEntity<String> alipayMethod(Integer courseId, HttpServletRequest request, HttpServletResponse httpServletResponse) {

        String userId1 = request.getHeader("userId");
        Integer userId = Integer.parseInt(userId1);
        //1.根据courseId查询订单的信息
        EduCourseVo courseInfo = eduCourseMapper.queryCourseByCourseId(courseId);
       /* //2.根据courseId查询开课的具体时间
        List<EduClass> eduClasses = eduClassMapper.queryCourseTimeByCourseId(courseId);
        //3.生成报名详情信息
        courseInfo.setClassList(eduClasses);*/


        //4.生成订单关联
        Order order = new Order();

        //生成订单的订单号
        order.setOrderSn(OrderIdUtil.getOrderIdByTime());
        order.setUserId(userId);
        order.setCreateTime(new Date());
        order.setOrderStatus(OrderStatus.UNREMOVE);//未取消
        order.setTotalAmount(courseInfo.getCoursePrice().longValue());
        order.setTradeBody(courseInfo.getCourseName());
        orderMapper.insertSelective(order);

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(order.getId().longValue());
        orderDetail.setCourseId(courseId.longValue());
        orderDetail.setCourseName(courseInfo.getCourseName());
        orderDetail.setOrderPrice(courseInfo.getCoursePrice());
        orderDetail.setNum(1);
        orderDetailMapper.insertSelective(orderDetail);

        if (String.valueOf(order.getId()) == null || String.valueOf(order.getId()).length() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        //获取订单信息
        Order orderInfo = orderService.getOrderInfo(String.valueOf(order.getId()));
        if (orderInfo == null) {
            //没有对应的订单
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        //保存支付信息
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setOrderId(String.valueOf(order.getId()));
        // 商户订单号，商户网站订单系统中唯一订单号，必填
        paymentInfo.setOutTradeNo(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + (int) (Math.random() * 90000 + 88888));
        // 订单名称，必填
        paymentInfo.setSubject(orderInfo.getTradeBody());

        paymentInfo.setPaymentStatus(String.valueOf(OrderStatus.PAY));
        // 付款金额，必填
        paymentInfo.setTotalAmount(orderInfo.getTotalAmount());

        paymentService.savePaymentInfo(paymentInfo);

        AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID,
                AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET,
                AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
        //利用支付宝客户端生成表单页面
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();

        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        model.setOutTradeNo(paymentInfo.getOutTradeNo());
        model.setSubject(paymentInfo.getSubject());
        model.setTotalAmount(paymentInfo.getTotalAmount().toString());
        model.setBody(paymentInfo.getSubject());
        model.setTimeoutExpress("2m");
        model.setProductCode("QUICK_WAP_WAY");
        alipayRequest.setBizModel(model);


        String form = "";
        try {
            form = client.pageExecute(alipayRequest).getBody();
            httpServletResponse.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        //把表单html打印到客户端浏览器
        return ResponseEntity.ok().body(form);
    }


    @RequestMapping(value = "/alipay/callback/return", method = RequestMethod.GET)
    public String callbackReturn(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        System.out.println(" callback return to " + AlipayConfig.return_order_url);
        return "redirect:" + AlipayConfig.return_order_url;
    }


}
