package com.ovft.configure.sys.service;

import com.ovft.configure.sys.bean.EduOfflineNum;

import java.util.List;

/**
 * @author vvtxw
 * @create 2019-06-04 15:16
 */
public interface EduOfflineNumService {
    //查询所有的线下订单课程
    List<EduOfflineNum> queryAllCourse(Integer userId, Integer courseId);

    //生成数据
    int addOfflineNum(EduOfflineNum eduOfflineNum);

    //删除订单课程
    Integer deleteOffLineNumCourse(EduOfflineNum eduOfflineNum);
}
