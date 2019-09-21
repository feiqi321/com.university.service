package com.ovft.configure.sys.web;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.ovft.configure.WXmodel.WXPay;
import com.ovft.configure.config.AlipayConfig;
import com.ovft.configure.config.WXPayConfig;
import com.ovft.configure.config.WXPayConfigImpl;
import com.ovft.configure.constant.*;
import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.*;
import com.ovft.configure.sys.dao.*;
import com.ovft.configure.sys.service.*;
import com.ovft.configure.sys.utils.OrderIdUtil;
import com.ovft.configure.sys.utils.RedisUtil;
import com.ovft.configure.sys.utils.WXPayUtil;
import com.ovft.configure.sys.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Resource
    private SchoolMapper schoolMapper;
    @Resource
    private EduSettlementOrderDetailsMapper eduSettlementOrderDetailsMapper;
    @Resource
    private EduCartMapper eduCartMapper;


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

    @Autowired
    private EduOfflineNumService eduOfflineNumService;

    @Resource
    private VideoMapper videoMapper;

    @Resource
    private TeacherMapper teacherMapper;

    @Resource
    private EduPayrecordService eduPayrecordService;

    @Resource
    private EduPayrecordMapper eduPayrecordMapper;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private PayConfigMapper payConfigMapper;


    @GetMapping(value = "showonoroff")
    public WebResult sendToStatus(Integer courseId, HttpServletRequest request) {
        String schoolId1 = request.getHeader("schoolId");
        int schoolId = Integer.parseInt(schoolId1);
        int status = eduRegistService.queryOffRegist(schoolId, courseId);
        return new WebResult(StatusCode.OK, "查询成功", status);
    }


    /**
     * 网上报名
     * 提交支付订单
     *
     * @param courseId
     * @param type
     * @param request
     * @param httpServletResponse
     * @return
     * @throws IOException
     */
    @GetMapping(value = "alipay/submit")
    @Transactional
    public Object paymentAlipay(@RequestParam("courseId") Integer courseId, Integer type, String code, HttpServletRequest request, HttpServletResponse httpServletResponse) throws IOException {
        String schoolId1 = request.getHeader("schoolId");
        int schoolId = Integer.parseInt(schoolId1);


        //微信支付 1
        if (type == 1) {

            redisUtil.set(schoolId1, schoolId1);   //后面会根据schoolId去查找对应学校的支付配置信息
            //校验用户是否已报名
            String userId = request.getHeader("userId");
            //查询学员的基本信息
            User user2 = userService.queryInfo(Integer.parseInt(userId));

            //查询是否有订单，如果已经下单了，返回信息
            List<EduOfflineOrder> oldOrder = eduOfflineOrderService.queryOffRecord(user2.getUserId(), courseId);
            List<OrderVo> orderVos = orderMapper.queryAllRecordByCourseId(user2.getUserId(), courseId);
            if (oldOrder.size() > 0 || orderVos.size() > 0) {
                return new WebResult("600", "您已经报名该课程，不要重复报名");
            }
            ResponseEntity<String> stringResponseEntity = alipayMethod(courseId, request, httpServletResponse, 1);
            Map<String, String> map2 = new HashMap<>();
            try {
                WXPayConstants.SignType signType = WXPayConstants.SignType.MD5;
                System.out.println("微信打印返回实体类内容" + stringResponseEntity.toString());
                Map<String, String> map = WXPayUtil.xmlToMap(stringResponseEntity.toString().split(",")[1]);

                map2.put("appId",map.get("appid"));
                map2.put("timeStamp", String.valueOf(Calendar.getInstance().getTimeInMillis()));
                map2.put("nonceStr", map.get("nonce_str"));
                map2.put("package", "prepay_id="+map.get("prepay_id"));
                map2.put("signType", signType.toString());
                EduCourseVo courseByCourseId = eduCourseMapper.queryCourseInfo(courseId);
                WxConfigPojo wxConfigPojo = payConfigMapper.selectWxPayConfig(1, null);//通过学校id进行查询对应学校配置系信息
                String paySign = WXPayUtil.generateSignature(map2, wxConfigPojo.getWxkey(), signType);
                map2.put("paySign", paySign);
                map2.remove("appId");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new WebResult("200", "操作成功", map2);


        }


        //支付宝支付 2
        if (type == 2) {

            //校验用户是否已报名
            String userId = request.getHeader("userId");
            //查询学员的基本信息
            User user2 = userService.queryInfo(Integer.parseInt(userId));

            //查询是否有订单，如果已经下单了，返回信息
            List<EduOfflineOrder> oldOrder = eduOfflineOrderService.queryOffRecord(user2.getUserId(), courseId);
            List<OrderVo> orderVos = orderMapper.queryAllRecordByCourseId(user2.getUserId(), courseId);
            if (oldOrder.size() > 0 || orderVos.size() > 0) {
                return new WebResult("600", "您已经报名该课程，不要重复报名");
            }


            ResponseEntity<String> stringResponseEntity = alipayMethod(courseId, request, httpServletResponse, 2);

            return stringResponseEntity;
        }

        //线下支付 3
        if (type == 3) {
            if (type == 3) {
                return new WebResult("600", "此功能暂时关闭", "");
            }
            int i = offlineRegist(request, courseId);
            List<EduOfflineAddresstime> eduOfflineAddresstimes = eduOfflineAddressService.queryAddressByschoolId(schoolId);
            for (EduOfflineAddresstime eduOfflineAddresstime : eduOfflineAddresstimes) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                String startTime = date.format(eduOfflineAddresstime.getPaymentStarttime());
                String endtTime = date.format(eduOfflineAddresstime.getPaymentEndtime());
                if (i > 0) {
                    return new WebResult(StatusCode.OK, "请在缴费时间：" + startTime + "至" + endtTime + "之前到" + eduOfflineAddresstime.getPaymentAddress() + "缴费，否者自动取消");
                }
            }
            if (i == -2) {
                return new WebResult("600", "您已经报名该课程，不要重复报名");
            }
        }
        return new WebResult(StatusCode.ERROR, "报名失败", "");
    }
    //2.支付宝线上支付

    private ResponseEntity<String> alipayMethod(Integer courseId, HttpServletRequest request, HttpServletResponse httpServletResponse, Integer type) {
        String userId1 = request.getHeader("userId");
        Integer userId = Integer.parseInt(userId1);
        String schoolId1 = request.getHeader("schoolId");
        int schoolId = Integer.parseInt(schoolId1);

        //0.购买之前先判断是否已经重复购买课程 根据courseId和orderstatus判断是否购买相同的课程


        //1.根据courseId查询订单的信息
        EduCourseVo courseInfo = eduCourseMapper.queryCourseByCourseId(courseId);
        redisUtil.set("payCourseName", courseInfo.getCourseName());
        redisUtil.set("clientIp", WXPay.getIp2(request));
        //2.生成订单关联
        Order order = new Order();

        //生成订单的订单号
        order.setOrderSn(OrderIdUtil.getOrderIdByTime());//订单号
        order.setUserId(userId);//付款学员
        order.setCreateTime(new Date());//订单创建时间
        order.setOrderStatus(OrderStatus.UNPay);//待付款
        order.setTotalAmount(courseInfo.getCoursePrice());//预付款金钱
        order.setTradeBody(courseInfo.getCourseName());//订单标题
        order.setSchoolId(String.valueOf(schoolId));
        if (type == 1) {
            order.setPaymentWay("微信支付");
        }
        if (type == 2) {
            order.setPaymentWay("支付宝");
        }
        orderMapper.insertSelective(order);
        Order orderInfo = orderService.getOrderInfo(String.valueOf(order.getId()));
        System.out.println(orderInfo.toString());
        //生成线上支付记录
        EduPayrecord eduPayrecord = new EduPayrecord();
        eduPayrecord.setSchoolId(String.valueOf(schoolId));
        eduPayrecord.setSchoolName(courseInfo.getSchoolName());
        eduPayrecord.setCourseName(courseInfo.getCourseName());
        eduPayrecord.setCourseTeacher(courseInfo.getCourseTeacher());
        eduPayrecord.setCourseAddress(order.getAddress());
        eduPayrecord.setStartTime(courseInfo.getStartTime());
        eduPayrecord.setEndTime(courseInfo.getEndTime());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd");
        String startDate = sf.format(courseInfo.getStartDate());
        eduPayrecord.setCourseStarttime(startDate);
        eduPayrecord.setPayStatus(String.valueOf(OrderStatus.UNPay));
        eduPayrecord.setUserId(userId);
        eduPayrecord.setCourseId(courseId);
        eduPayrecord.setIsDelete(2);
        eduPayrecord.setOrderId(order.getId());
        eduPayrecordService.insertPayRecord(eduPayrecord);
        //3生成订单详情信息
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(order.getId().longValue());//订单id
        orderDetail.setCourseId(courseId.longValue());//课程id
        orderDetail.setCourseName(courseInfo.getCourseName());//课程名称
        orderDetail.setOrderPrice(courseInfo.getCoursePrice());//课程价格
        orderDetail.setNum(1);//购买数量
        School school = schoolMapper.selectById(Integer.valueOf(order.getSchoolId()));
        orderDetail.setSchoolId(schoolId);
        orderDetail.setSchoolName(school.getSchoolName());
        orderDetailMapper.insertSelective(orderDetail);

        order.setReturnurl(request.getHeader("referer"));
        System.out.println("=============>>" + order.getReturnurl());
        if (type == 1) {
            return getWxResponseBody(httpServletResponse, order, courseInfo);
        }
        if (type == 2) {
            return getStringResponseEntity(httpServletResponse, order);
        }
        return null;
    }

    @RequestMapping(value = "/alipay/callback/notify", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public String callbackNotify(@RequestParam Map<String, String> paramMap) {
        System.out.println(" ----------callbackstart 支付宝开始回调" + paramMap.toString());
        boolean isCheckPass = false;
        try {

            isCheckPass = AlipaySignature.rsaCheckV1(paramMap, redisUtil.get("ALIPAY_PUBLIC_KEY").toString(), AlipayConfig.CHARSET, AlipayConfig.SIGNTYPE);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if (!isCheckPass) {
            System.out.println(" ----------验签不通过！！");
            return "验签不通过！！";
        }
        System.out.println(" ----------验签通过！！");

        String trade_status = paramMap.get("trade_status");
        if ("TRADE_SUCCESS".equals(trade_status)) {
            String outTradeNo = paramMap.get("out_trade_no");
            PaymentInfo paymentInfoQuery = new PaymentInfo();
            paymentInfoQuery.setOutTradeNo(outTradeNo);
            PaymentInfo paymentInfo = paymentService.getPaymentInfo(paymentInfoQuery);
            if (paymentInfo == null) {
                return "error: not exists out_trade_no:" + outTradeNo;
            }
            System.out.println("检查是否已处理= " + outTradeNo);

            //
            if (paymentInfo.getPaymentStatus().equals(PaymentStatus.PAID)) {
                System.out.println(" 已处理= " + outTradeNo);
                return "success";
            } else {
                System.out.println("未处理，更新状态=" + outTradeNo);
                PaymentInfo paymentInfo4Upt = new PaymentInfo();
                paymentInfo4Upt.setPaymentStatus(PaymentStatus.PAID);
                paymentInfo4Upt.setCallbackTime(new Date());
                paymentInfo4Upt.setCallbackContent(paramMap.toString());
                paymentInfo4Upt.setCreateTime(new Date());
                paymentService.updatePaymentInfoByOutTradeNo(outTradeNo, paymentInfo4Upt);

                Order order = orderMapper.selectByPrimaryKey(Integer.valueOf(paymentInfo.getOrderId()));
                order.setOrderStatus(OrderStatus.PAY);
                order.setResourceStatus("PAID");

                order.setPaymentTime(paymentInfo4Upt.getCreateTime());
                orderMapper.updateByPrimaryKeySelective(order);

                User user = userService.selectById(order.getUserId());
                OrderDetailExample orderDetailExample = new OrderDetailExample();
                orderDetailExample.createCriteria().andOrderIdEqualTo(Long.valueOf(order.getId()));
                List<OrderDetail> orderDetails = orderDetailMapper.selectByExample(orderDetailExample);
//                Long courseId = orderDetails.get(0).getCourseId();
//                EduCourseVo eduCourseVo = teacherMapper.selectByCourseId(courseId.intValue());
//                    eduCourseVo.setNowtotal();
//
//                teacherMapper.updateCourseByCourseId();

                //更新线上支付记录状态
                EduPayrecord eduPayrecord = new EduPayrecord();
                eduPayrecord.setPayStatus(String.valueOf(OrderStatus.PAY));
                eduPayrecord.setOrderId(Integer.valueOf(paymentInfo.getOrderId()));

                eduPayrecordMapper.updateByPrimaryKey2(eduPayrecord);

                BigDecimal orderPrice = new BigDecimal("0");
                BigDecimal SettlementMoney = new BigDecimal("0");
                BigDecimal feilu = new BigDecimal("0.006");//费率
                String schoolName = null;
                for (OrderDetail orderDetail : orderDetails) {
                    orderPrice = orderDetail.getOrderPrice();
                    BigDecimal multiply = orderPrice.multiply(feilu);
                    SettlementMoney = orderPrice.subtract(multiply);
                    schoolName = orderDetail.getSchoolName();
                }

                //对账系统生成未结算订单表详细表
                EduSettlementOrderDetails eduSettlementOrderDetails = new EduSettlementOrderDetails();
                eduSettlementOrderDetails.setOrderNumber(paymentInfo.getOutTradeNo());
                eduSettlementOrderDetails.setResourceName(paymentInfo.getSubject());
                eduSettlementOrderDetails.setTradingTime(new Date());
                eduSettlementOrderDetails.setPaymentType(String.valueOf(2));//支付宝
                eduSettlementOrderDetails.setSettlementStatus(1);//未结算
                eduSettlementOrderDetails.setTelephone(user.getPhone());
                eduSettlementOrderDetails.setSchoolId(Integer.valueOf(order.getSchoolId()));
                eduSettlementOrderDetails.setSchoolName(schoolName);
                eduSettlementOrderDetails.setTransactionAmount(orderPrice);
                eduSettlementOrderDetails.setSettlementAmount(SettlementMoney);
                eduSettlementOrderDetails.setPayrecordId(paymentInfo.getId());
                eduSettlementOrderDetails.setOrderId(order.getId());
                eduSettlementOrderDetailsMapper.insertSelective(eduSettlementOrderDetails);
                System.out.println("结束...");
                return "success";
            }
        }
        return "";

    }

    @RequestMapping(value = "/alipay/callback/Wxnotify", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public Boolean wxPayNotifyUrl(Map<String, String> map) throws Exception {
        //  log.info(">>>>微信支付回调...<<<<");

        String outTradeNo = map.get("out_trade_no");
        String return_code = map.get("return_code");

        if (return_code.equals("SUCCESS")) {
//            log.info(">>>>微信调起成功...<<<<");
//            PaymentDetailVo entity = paymentDao.queryPaymentDetail(out_trade_no);
            PaymentInfo paymentInfoQuery = new PaymentInfo();
            paymentInfoQuery.setOutTradeNo(outTradeNo);
            PaymentInfo paymentInfo = paymentService.getPaymentInfo(paymentInfoQuery);
            /*微信重复调起-->校验如果已经支付则直接返回*/
            if (paymentInfoQuery.getPaymentStatus().equals("PAID")) {
                return true;
            }
            WXPayConfig wxPayConfig = new WXPayConfigImpl();
            WXPay wxPay = new WXPay(wxPayConfig);
            /*判断sign签名是否一致*/
            if (wxPay.isResponseSignatureValid(map)) {
                System.out.println(">>>>微信签名通过...<<<<");

                /*校验api返回的金额与流水单的金额是否一致*/
                if (map.get("total_fee") == null) {
                    System.out.println("API返回无金额!");
//                    /*调用退款接口 */
//                    wxRefund(out_trade_no,entity.getUserId(), entity.getPaymentStatus());
                    throw new Exception("API返回无金额！");
                }
                /*转换金额为元*/
                BigDecimal totalPrice = new BigDecimal(paymentInfo.getTotalAmount().toString()); //单位是元
                String paymentPrive = totalPrice.multiply(new BigDecimal(100)).toBigInteger().toString();
                String total_fee = map.get("total_fee").toString();
                if (!total_fee.equals(paymentPrive)) {
                    System.out.println("API返回的交易金额与流水单的金额不一致，存在假通知的危险！");
                    //wxRefund(out_trade_no,entity.getUserId(), entity.getPaymentStatus());
                    throw new Exception("API返回的交易金额与流水单的金额不一致，存在假通知的危险！");
                }

                HashMap param = new HashMap();
                param.put("no", paymentInfo.getOutTradeNo());
                System.out.println("未处理，更新状态=" + outTradeNo);
                PaymentInfo paymentInfo4Upt = new PaymentInfo();
                paymentInfo4Upt.setPaymentStatus(PaymentStatus.PAID);
                paymentInfo4Upt.setCallbackTime(new Date());
                paymentInfo4Upt.setCallbackContent(param.toString());
                paymentInfo4Upt.setCreateTime(new Date());
                paymentService.updatePaymentInfoByOutTradeNo(outTradeNo, paymentInfo4Upt);

                Order order = orderMapper.selectByPrimaryKey(Integer.valueOf(paymentInfo.getOrderId()));
                order.setOrderStatus(OrderStatus.PAY);
                order.setResourceStatus("PAID");

                order.setPaymentTime(paymentInfo4Upt.getCreateTime());
                orderMapper.updateByPrimaryKeySelective(order);

                User user = userService.selectById(order.getUserId());
                OrderDetailExample orderDetailExample = new OrderDetailExample();
                orderDetailExample.createCriteria().andOrderIdEqualTo(Long.valueOf(order.getId()));
                List<OrderDetail> orderDetails = orderDetailMapper.selectByExample(orderDetailExample);
//                Long courseId = orderDetails.get(0).getCourseId();
//                EduCourseVo eduCourseVo = teacherMapper.selectByCourseId(courseId.intValue());
//                    eduCourseVo.setNowtotal();
//
//                teacherMapper.updateCourseByCourseId();

                //更新线上支付记录状态
                EduPayrecord eduPayrecord = new EduPayrecord();
                eduPayrecord.setPayStatus(String.valueOf(OrderStatus.PAY));
                eduPayrecord.setOrderId(Integer.valueOf(paymentInfo.getOrderId()));

                eduPayrecordMapper.updateByPrimaryKey2(eduPayrecord);

                BigDecimal orderPrice = new BigDecimal("0");
                BigDecimal SettlementMoney = new BigDecimal("0");
                BigDecimal feilu = new BigDecimal("0.006");//费率
                String schoolName = null;
                for (OrderDetail orderDetail : orderDetails) {
                    orderPrice = orderDetail.getOrderPrice();
                    BigDecimal multiply = orderPrice.multiply(feilu);
                    SettlementMoney = orderPrice.subtract(multiply);
                    schoolName = orderDetail.getSchoolName();
                }

                //对账系统生成未结算订单表详细表
                EduSettlementOrderDetails eduSettlementOrderDetails = new EduSettlementOrderDetails();
                eduSettlementOrderDetails.setOrderNumber(paymentInfo.getOutTradeNo());
                eduSettlementOrderDetails.setResourceName(paymentInfo.getSubject());
                eduSettlementOrderDetails.setTradingTime(new Date());
                eduSettlementOrderDetails.setPaymentType(String.valueOf(2));//支付宝
                eduSettlementOrderDetails.setSettlementStatus(1);//未结算
                eduSettlementOrderDetails.setTelephone(user.getPhone());
                eduSettlementOrderDetails.setSchoolId(Integer.valueOf(order.getSchoolId()));
                eduSettlementOrderDetails.setSchoolName(schoolName);
                eduSettlementOrderDetails.setTransactionAmount(orderPrice);
                eduSettlementOrderDetails.setSettlementAmount(SettlementMoney);
                eduSettlementOrderDetails.setPayrecordId(paymentInfo.getId());
                eduSettlementOrderDetails.setOrderId(order.getId());
                eduSettlementOrderDetailsMapper.insertSelective(eduSettlementOrderDetails);
                System.out.println("结束...");


                /*异步回调成功，修改订单状态*/
                //...


                System.out.println(">>>>微信异步回调完毕...<<<<");
            } else {
                System.out.println("API返回的数据签名验证不通过，有可能被第三方篡改!!!");
                // wxRefund(out_trade_no,entity.getUserId(), Integer.valueOf(map.get("type").toString()));
                throw new Exception("API返回的数据签名验证不通过，有可能被第三方篡改!!!");
            }
        } else {
            return false;
        }
        return true;
    }

    //3.线下支付

    private int offlineRegist(HttpServletRequest request, Integer courseId) {
        //生存线下报名记录
        String userId1 = request.getHeader("userId");
        Integer userId = Integer.parseInt(userId1);

        String schoolId1 = request.getHeader("schoolId");
        Integer schoolId = Integer.parseInt(schoolId1);

        //查询学员的基本信息
        User user = userService.queryInfo(userId);
        //查询学员的课程信息
        EduCourseVo eduCourseVo = eduCourseMapper.queryCourseInfo(courseId);

        //查询是否有订单，如果已经下单了，返回信息
        List<EduOfflineOrder> oldOrder = eduOfflineOrderService.queryOffRecord(user.getUserId(), courseId);
        List<OrderVo> orderVos = orderMapper.queryAllRecordByCourseId(user.getUserId(), courseId);
        if (oldOrder.size() > 0 || orderVos.size() > 0) {
            return -2;
        }
        //生成eduOfflineOrder
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
        //生成eduOfflineOrderitem
        EduOfflineOrderitem eduOfflineOrderitem = new EduOfflineOrderitem();
        eduOfflineOrderitem.setCouserName(eduCourseVo.getCourseName());
        eduOfflineOrderitem.setCouserPrice(eduCourseVo.getCoursePrice());
        eduOfflineOrderitem.setPayStatus(String.valueOf(Status.UNPAY));
        eduOfflineOrderitem.setCourseId(courseId);
        eduOfflineOrderitem.setSchoolId(Integer.parseInt(eduCourseVo.getSchoolId()));
        eduOfflineOrderitem.setUserId(userId);

        //生成eduOfflineNum
        EduOfflineNum eduOfflineNum = new EduOfflineNum();
        eduOfflineNum.setCourseId(courseId);
        eduOfflineNum.setUserId(userId);
        eduOfflineNum.setCourseName(eduCourseVo.getCourseName());
        eduOfflineNum.setCoursePrice(eduCourseVo.getCoursePrice());
        eduOfflineNum.setPayStatus(String.valueOf(Status.UNPAY));
        eduOfflineNum.setSchoolId(Integer.parseInt(eduCourseVo.getSchoolId()));


        int res = eduOfflineOrderService.updateOffOrder(eduOfflineOrder);
        int res1 = eduOfflineOrderitemService.addItemOrder(eduOfflineOrderitem);
        int res3 = eduOfflineNumService.addOfflineNum(eduOfflineNum);

        List<EduOfflineNum> eduOfflineNums = eduOfflineNumService.queryAllCourse(userId, courseId);
        QueryOffLineVos queryOffLineVos = new QueryOffLineVos();
        queryOffLineVos.setOfflineNums(eduOfflineNums);
        queryOffLineVos.setUserName(user.getUserName());
        queryOffLineVos.setPhone(user.getPhone());
        queryOffLineVos.setSchoolName(eduCourseVo.getSchoolName());

        Integer res2 = 0;
        //支付信息生存
        EduOfflinePayInfo edufflinePayInfo = new EduOfflinePayInfo();
        edufflinePayInfo.setSchoolId(Integer.parseInt(eduCourseVo.getSchoolId()));
        edufflinePayInfo.setSchoolName(queryOffLineVos.getSchoolName());
        edufflinePayInfo.setUserId(userId);
        edufflinePayInfo.setUserName(queryOffLineVos.getUserName());
        edufflinePayInfo.setTelephone(queryOffLineVos.getPhone());
        List<EduOfflineNum> orderitems = queryOffLineVos.getOfflineNums();

        BigDecimal accountAllMoney = new BigDecimal(0);
        for (int i = 0; i < orderitems.size(); i++) {
            accountAllMoney = accountAllMoney.add(orderitems.get(i).getCoursePrice());
        }
        edufflinePayInfo.setPayStatus(String.valueOf(Status.UNPAY));
        edufflinePayInfo.setPayUpdatetime(new Date());

        //查询支付订单表里的用户只有一个
        List<EduOfflinePayInfo> eduOfflinePayInfos = eduOfflinePayInfoService.queryAllPayInfo(queryOffLineVos.getPhone());
        if (eduOfflinePayInfos.size() == 0) {
            //没有用户信息就添加
            res2 = eduOfflinePayInfoService.insertPayInfo(edufflinePayInfo);
        }

        if (res > 0 && res1 > 0) {
            return 1;
        }
        return -1;
    }

    /**
     * 教材购买提交订单
     *
     * @param submitOrderVos
     * @param request
     * @param httpServletResponse
     * @return
     * @throws IOException
     */
    @PostMapping(value = "books/alipay/submit")
    @Transactional
    public Object paymentAlipayBooks(@RequestBody SubmitOrderVos submitOrderVos, HttpServletRequest request, HttpServletResponse httpServletResponse) throws IOException {
//        String schoolId1 = request.getHeader("schoolId");
//
//        int schoolId = Integer.parseInt(schoolId1);

        if (StringUtils.isBlank(submitOrderVos.getAddress())) {
            return new WebResult(StatusCode.ERROR, "您尚未选择地址，请先填写地址", "");

        }

        //微信支付 1
        /*if (submitOrderVos.getType() == 1) {
            ResponseEntity<String> stringResponseEntity = wxpayMethod(courseId, request, httpServletResponse);
            return new WebResult(StatusCode.OK, "支付成功", stringResponseEntity);
        }*/


        //支付宝支付 2
        if (submitOrderVos.getType() == 2) {
            ResponseEntity<String> stringResponseEntity = alipayMethodBoooks(submitOrderVos, request, httpServletResponse);
//            if(stringResponseEntity.getStatusCode().is2xxSuccessful()){
//                    System.out.println("我支付宝支付成功了！！！");
//            }
            return stringResponseEntity;
        }
        return new WebResult(StatusCode.ERROR, "报名失败", "");
    }

    //2.支付宝线上支付

    private ResponseEntity<String> alipayMethodBoooks(SubmitOrderVos submitOrderVos, HttpServletRequest request, HttpServletResponse httpServletResponse) {
        String userId1 = request.getHeader("userId");
        Integer userId = Integer.parseInt(userId1);
        String schoolId = request.getHeader("schoolId");

        Order order = new Order();
        order.setUserId(userId);
        order.setOrderSn(OrderIdUtil.getOrderIdByTime());
        order.setOrderStatus(OrderStatus.UNPay);//代付款
        order.setCreateTime(new Date());
        order.setTotalAmount(submitOrderVos.getTotalMoney());
        order.setConsignee(submitOrderVos.getUserName());
        order.setTelephone(submitOrderVos.getPhone());
        order.setAddress(submitOrderVos.getAddress());
        order.setSendType(submitOrderVos.getSendType());
        order.setTosendPrice(submitOrderVos.getToSendPrice());
        order.setSchoolId(schoolId);
        order.setRemark(submitOrderVos.getRemark());
        List<OrderDetailVo> orderItemsVo = submitOrderVos.getOrderItemsVo();
        OrderDetailVo orderDetailVo2 = orderItemsVo.get(0);
        order.setTradeBody(orderDetailVo2.getBooksName());
        order.setPaymentWay("支付宝");

        order.setResourceType("1");//教材
        order.setResourceStatus("UNPAID");//未支付
        orderMapper.insertSelective(order);
        for (OrderDetailVo orderItem : orderItemsVo) {
            OrderDetail orderDetail = new OrderDetail();
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

            EduCartExample eduCartExample = new EduCartExample();
            eduCartExample.createCriteria().andUserIdEqualTo(userId).andGoodsIdEqualTo(orderItem.getGoodsId());
            eduCartMapper.deleteByExample(eduCartExample);
        }

        return getStringResponseEntity(httpServletResponse, order);
    }




    /**
     * 视频购买
     *
     * @param videoId
     * @param request
     * @param httpServletResponse
     * @return
     * @throws IOException
     */
    @GetMapping(value = "video/alipay/submit")
    @Transactional
    public Object paymentAlipayvideo(@RequestParam("videoId") Integer videoId, Integer type, HttpServletRequest request, HttpServletResponse httpServletResponse) throws IOException {
        String schoolId1 = request.getHeader("schoolId");
        int schoolId = Integer.parseInt(schoolId1);

        //微信支付 1
        /*if (submitOrderVos.getType() == 1) {
            ResponseEntity<String> stringResponseEntity = wxpayMethod(courseId, request, httpServletResponse);
            return new WebResult(StatusCode.OK, "支付成功", stringResponseEntity);
        }*/

        //支付宝支付 2
        if (type == 2) {
            ResponseEntity<String> stringResponseEntity = alipayMethodVideo(videoId, request, httpServletResponse);
            return stringResponseEntity;
        }
        return new WebResult(StatusCode.ERROR, "报名失败", "");
    }


    //2.支付宝线上支付

    private ResponseEntity<String> alipayMethodVideo(Integer videoId, HttpServletRequest request, HttpServletResponse httpServletResponse) {
        String userId1 = request.getHeader("userId");
        Integer userId = Integer.parseInt(userId1);
        String schoolId = request.getHeader("schoolId");

        Video video = videoMapper.selectById(videoId);

        Order order = new Order();
        order.setUserId(userId);
        order.setOrderSn(OrderIdUtil.getOrderIdByTime());
        order.setOrderStatus(OrderStatus.UNPay);
        order.setCreateTime(new Date());
        BigDecimal actPrice = video.getActPrice();
        BigDecimal price = video.getPrice();
        if (price != null) {
            order.setTotalAmount(price);
        }
        if (actPrice != null) {
            order.setTotalAmount(actPrice);
        }
        order.setImgUrl(video.getCover());
        order.setTradeBody(video.getTitle());
        order.setSchoolId(schoolId);
        order.setPaymentWay("支付宝");
        orderMapper.insertSelective(order);

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(order.getId().longValue());//订单id
        orderDetail.setCourseId(Long.valueOf(videoId));//课程id
        orderDetail.setCourseName(video.getTitle());//课程名称
        if (price != null) {
            orderDetail.setOrderPrice(price);
        }
        if (actPrice != null) {
            orderDetail.setOrderPrice(actPrice);
        }
        orderDetail.setNum(1);//购买数量
        School school = schoolMapper.selectById(Integer.valueOf(order.getSchoolId()));
        orderDetail.setSchoolId(video.getSchoolId());
        orderDetail.setSchoolName(school.getSchoolName());
        orderDetailMapper.insertSelective(orderDetail);
        return getStringResponseEntity(httpServletResponse, order);
    }

    private ResponseEntity<String> getStringResponseEntity(HttpServletResponse httpServletResponse, Order order) {
        if (String.valueOf(order.getId()) == null || String.valueOf(order.getId()).length() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Order orderInfo = orderService.getOrderInfo(String.valueOf(order.getId()));
        if (orderInfo == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        //保存支付信息
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setOrderId(String.valueOf(order.getId()));
        paymentInfo.setOutTradeNo(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + (int) (Math.random() * 90000 + 88888));
        paymentInfo.setSubject(orderInfo.getTradeBody());
        paymentInfo.setPaymentStatus(PaymentStatus.UNPAID);
        paymentInfo.setTotalAmount(orderInfo.getTotalAmount());
        paymentService.savePaymentInfo(paymentInfo);
        AliConfigPojo aliConfigPojoList = payConfigMapper.selectAliPayConfig(1, null);//通过学校id进行查询对应学校配置系信息
        AlipayConfig alipayConfig = new AlipayConfig(Integer.parseInt(orderInfo.getSchoolId()), 1, aliConfigPojoList);
        redisUtil.set("ALIPAY_PUBLIC_KEY", alipayConfig.ALIPAY_PUBLIC_KEY);  //设置5分钟的过期时间
        AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, alipayConfig.APPID,
                alipayConfig.RSA_PRIVATE_KEY, alipayConfig.FORMAT, alipayConfig.CHARSET,
                alipayConfig.ALIPAY_PUBLIC_KEY, alipayConfig.SIGNTYPE);
        //利用支付宝客户端生成表单页面
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();

        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(alipayConfig.notify_url);

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

        return ResponseEntity.ok().body(form);

    }

    //
    private ResponseEntity<String> getWxResponseBody(HttpServletResponse httpServletResponse, Order order, EduCourseVo eduCourseVo) {
        EduCourseVo courseByCourseId = eduCourseMapper.queryCourseInfo(eduCourseVo.getCourseId());
        WxConfigPojo wxConfigPojoList = payConfigMapper.selectWxPayConfig(1, null);//通过学校id进行查询对应学校配置系信息
        WXPayConfig wxPayConfig = new WXPayConfigImpl(Integer.parseInt(courseByCourseId.getSchoolId()), 1, wxConfigPojoList);
        redisUtil.set("wxkey", wxPayConfig.getKey());
        String notify_url = "http://47.105.55.14:8830/pay/alipay/callback/notify";
        String s = null;
        try {
            WXPay wxPay = new WXPay(wxPayConfig, notify_url, true, true);
            HashMap map = new HashMap();
            map.put("openid", redisUtil.get("openid").toString());
            map.put("appid", wxConfigPojoList.getAppid());
            map.put("body", courseByCourseId.getCourseName());
            map.put("mch_id", wxConfigPojoList.getMchId());
            map.put("nonce_str", WXPayUtil.generateNonceStr());
            map.put("notify_url", wxConfigPojoList.getNotifyUrl());
            map.put("out_trade_no", order.getOrderSn());
            map.put("spbill_create_ip", redisUtil.get("clientIp").toString());
            map.put("timeStamp", String.valueOf(Calendar.getInstance().getTimeInMillis()));
            map.put("total_fee", 10 + "");
            map.put("trade_type", "JSAPI");


            Map<String, String> reqData = wxPay.fillRequestData(map);
            System.out.println("入参xml" + reqData);
//                wxPay.isResponseSignatureValid(reqData);
//                wxPay.isPayResultNotifySignatureValid(reqData);

            s = wxPay.requestWithoutCert("/pay/unifiedorder", reqData, wxPayConfig.getHttpConnectTimeoutMs(), wxPayConfig.getHttpReadTimeoutMs());
            System.out.println("微信支付返回信息xml" + s);

            Map<String, String> stringStringMap = wxPay.processResponseXml(s);
            System.out.println("微信支付返回信息map" + WXPayUtil.xmlToMap(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(s);
    }
}
