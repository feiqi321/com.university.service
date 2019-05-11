package com.ovft.configure.sys.service;

import com.ovft.configure.sys.bean.PaymentInfo;

/**
 * @author vvtxw
 * @create 2019-05-05 18:26
 */

public interface paymentService {
    //保存支付的信息详情
    void savePaymentInfo(PaymentInfo paymentInfo);
}
