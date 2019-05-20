package com.ovft.configure.sys.service.impl;

import com.ovft.configure.sys.bean.PaymentInfo;
import com.ovft.configure.sys.dao.PaymentInfoMapper;
import com.ovft.configure.sys.service.paymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author vvtxw
 * @create 2019-05-05 18:28
 */

@Service
public class paymentServiceImpl implements paymentService {
    @Resource
    private PaymentInfoMapper paymentInfoMapper;

    @Override
    public void savePaymentInfo(PaymentInfo paymentInfo) {
        paymentInfoMapper.insertSelective(paymentInfo);
    }
}
