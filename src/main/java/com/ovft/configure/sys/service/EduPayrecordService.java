package com.ovft.configure.sys.service;

import com.ovft.configure.sys.bean.EduPayrecord;

/**
 * @author vvtxw
 * @create 2019-05-29 17:41
 */
public interface EduPayrecordService {

    //生存支付记录表
    Integer insertPayRecord(EduPayrecord eduPayrecord);
}
