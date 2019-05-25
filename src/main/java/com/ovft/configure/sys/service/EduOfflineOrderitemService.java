package com.ovft.configure.sys.service;

import com.ovft.configure.sys.bean.EduOfflineOrderitem;

import java.util.List;

/**
 * @author vvtxw
 * @create 2019-05-24 12:06
 */
public interface EduOfflineOrderitemService {

    //添加点单信息
    int addItemOrder(EduOfflineOrderitem eduOfflineOrderitem);

    //查询下线订单课程记录
    List<EduOfflineOrderitem> queryAllOffOrderItem(String schoolId, Integer userId);

    Integer updatePayStatus(String schoolId, Integer userId, Integer courseId);
}
