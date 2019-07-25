package com.ovft.configure.sys.service;

import com.ovft.configure.sys.bean.PaymentInfo;

/**
 * @author vvtxw
 * @create 2019-05-05 18:26
 */

public interface paymentService {
    //保存支付的信息详情
    void savePaymentInfo(PaymentInfo paymentInfo);

    //获取支付信息
    PaymentInfo getPaymentInfo(PaymentInfo paymentInfoQuery);

    //修改支付信息表内容
    void updatePaymentInfoByOutTradeNo(String outTradeNo, PaymentInfo paymentInfo4Upt);

    void sendPaymentResult2MQ(String orderId);
}
