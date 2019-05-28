package com.ovft.configure.sys.service;

import com.ovft.configure.sys.bean.EduOfflineOrder;
import com.ovft.configure.sys.bean.User;

import java.util.List;

/**
 * @author vvtxw
 * @create 2019-05-23 10:44
 */
public interface EduOfflineOrderService {

    //生成线下订单
    int updateOffOrder(EduOfflineOrder eduOfflineOrder);

    //查询线下支付记录
    List<EduOfflineOrder> queryAllOffRecord(Integer userId);

    //查询线下支付记录
    List<EduOfflineOrder> queryOffRecord(Integer userId, Integer courseId);

    //查询下线订单记录
    List<EduOfflineOrder> queryAllOffOrder(String schoolId, Integer userId);

    //修改支付状态
    Integer updatePayStatus(String schoolId, Integer userId, Integer courseId);

}