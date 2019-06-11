package com.ovft.configure.sys.service.impl;

import com.ovft.configure.sys.bean.*;
import com.ovft.configure.sys.dao.EduOfflineNumMapper;
import com.ovft.configure.sys.dao.EduOfflineOrderMapper;
import com.ovft.configure.sys.dao.EduOfflineOrderitemMapper;
import com.ovft.configure.sys.service.EduOfflineNumService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author vvtxw
 * @create 2019-06-04 15:16
 */
@Service
public class EduOfflineNumServiceImpl implements EduOfflineNumService {

    @Resource
    private EduOfflineNumMapper eduOfflineNumMapper;

    @Resource
    private EduOfflineOrderMapper eduOfflineOrderMapper;

    @Resource
    private EduOfflineOrderitemMapper eduOfflineOrderitemMapper;


    @Override
    public List<EduOfflineNum> queryAllCourse(Integer userId, Integer courseId) {
        EduOfflineNumExample eduOfflineNumExample = new EduOfflineNumExample();
        eduOfflineNumExample.createCriteria().andUserIdEqualTo(userId).andCourseIdEqualTo(courseId);
        List<EduOfflineNum> eduOfflineNums = eduOfflineNumMapper.selectByExample(eduOfflineNumExample);
        return eduOfflineNums;
    }

    @Override
    public int addOfflineNum(EduOfflineNum eduOfflineNum) {
        return eduOfflineNumMapper.insertSelective(eduOfflineNum);
    }

    @Override
    @Transactional
    public Integer deleteOffLineNumCourse(EduOfflineNum eduOfflineNum) {

        //删除订单的记录
        EduOfflineOrderExample eduOfflineOrderExample = new EduOfflineOrderExample();
        eduOfflineOrderExample.createCriteria().andUserIdEqualTo(eduOfflineNum.getUserId()).andCourseIdEqualTo(eduOfflineNum.getCourseId());
        eduOfflineOrderMapper.deleteByExample(eduOfflineOrderExample);

        //删除课程记录
        //判断是否有这个线下支付订单 如果没有最后删除用户信息
        //查询出是否还有订单
        EduOfflineOrderExample eduOfflineOrderExample1 = new EduOfflineOrderExample();
        eduOfflineOrderExample1.createCriteria().andUserIdEqualTo(eduOfflineNum.getUserId()).andCourseIdEqualTo(eduOfflineNum.getCourseId());
        List<EduOfflineOrder> eduOfflineOrders = eduOfflineOrderMapper.selectByExample(eduOfflineOrderExample1);
        int res = 1;

        if (eduOfflineOrders.size() == 1) {
            res = eduOfflineNumMapper.deleteByPrimaryKey(eduOfflineNum.getId());
        }

        //删除订单详细的记录
        EduOfflineOrderitemExample eduOfflineOrderitemExample = new EduOfflineOrderitemExample();
        eduOfflineOrderitemExample.createCriteria().andUserIdEqualTo(eduOfflineNum.getUserId()).andCourseIdEqualTo(eduOfflineNum.getCourseId());
        eduOfflineOrderitemMapper.deleteByExample(eduOfflineOrderitemExample);
        return res;
    }
}
