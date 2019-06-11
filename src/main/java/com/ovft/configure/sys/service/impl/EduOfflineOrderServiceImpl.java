package com.ovft.configure.sys.service.impl;

import com.ovft.configure.sys.bean.*;
import com.ovft.configure.sys.dao.EduOfflineNumMapper;
import com.ovft.configure.sys.dao.EduOfflineOrderMapper;
import com.ovft.configure.sys.dao.EduOfflineOrderitemMapper;
import com.ovft.configure.sys.dao.EduOfflinePayInfoMapper;
import com.ovft.configure.sys.service.EduOfflineOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author vvtxw
 * @create 2019-05-23 10:46
 */
@Service
public class EduOfflineOrderServiceImpl implements EduOfflineOrderService {

    @Resource
    private EduOfflineOrderMapper eduOfflineOrderMapper;

    @Resource
    private EduOfflineOrderitemMapper eduOfflineOrderitemMapper;

    @Resource
    private EduOfflineNumMapper eduOfflineNumMapper;

    @Resource
    private EduOfflinePayInfoMapper eduOfflinePayInfoMapper;


    @Override
    public int updateOffOrder(EduOfflineOrder eduOfflineOrder) {
        return eduOfflineOrderMapper.insertSelective(eduOfflineOrder);
    }

    @Override
    public List<EduOfflineOrder> queryAllOffRecord(Integer userId) {
        List<EduOfflineOrder> eduOfflineOrders = eduOfflineOrderMapper.queryAllOffRecord(userId);
        return eduOfflineOrders;
    }

    @Override
    public List<EduOfflineOrder> queryOffRecord(Integer userId, Integer courseId) {
        EduOfflineOrderExample eduOfflineOrderExample = new EduOfflineOrderExample();
        eduOfflineOrderExample.createCriteria().andUserIdEqualTo(userId).andCourseIdEqualTo(courseId);
        List<EduOfflineOrder> eduOfflineOrders = eduOfflineOrderMapper.selectByExample(eduOfflineOrderExample);
        return eduOfflineOrders;
    }

    @Override
    public List<EduOfflineOrder> queryAllOffOrder(String schoolId, Integer userId) {
        EduOfflineOrderExample eduOfflineOrderExample = new EduOfflineOrderExample();
        eduOfflineOrderExample.createCriteria().andUserIdEqualTo(userId).andSchoolIdEqualTo(Integer.valueOf(schoolId));
        List<EduOfflineOrder> eduOfflineOrders = eduOfflineOrderMapper.selectByExample(eduOfflineOrderExample);
        return eduOfflineOrders;
    }

    @Override
    public Integer updatePayStatus(String schoolId, Integer userId, Integer courseId) {
        return null;
    }

    @Override
    public Integer queryOffRecordNum(Integer payStatus) {
        Integer num = eduOfflineOrderMapper.queryOffRecordNum(payStatus);
        return num;
    }

    @Override
    public List<EduOfflineOrder> showOffOrderByUserPhone(String phone) {
        EduOfflineOrderExample eduOfflineOrderExample = new EduOfflineOrderExample();
        eduOfflineOrderExample.createCriteria().andPhoneEqualTo(phone);
        List<EduOfflineOrder> eduOfflineOrders = eduOfflineOrderMapper.selectByExample(eduOfflineOrderExample);
        return eduOfflineOrders;
    }

    @Override
    @Transactional
    public Integer deleteOffOrderByUserPhone(EduOfflineOrder eduOfflineOrder) {
        int res = eduOfflineOrderMapper.deleteByPrimaryKey(eduOfflineOrder.getId());
        if (res > 0) {
            EduOfflineOrderitemExample eduOfflineOrderitemExample = new EduOfflineOrderitemExample();
            eduOfflineOrderitemExample.createCriteria().andCourseIdEqualTo(eduOfflineOrder.getCourseId());
            eduOfflineOrderitemMapper.deleteByExample(eduOfflineOrderitemExample);
        }
        return res;
    }

    @Override
    @Transactional
    public Integer deleteOffOrderByUserIdAndCourseId(EduOfflineOrder eduOfflineOrder) {
        EduOfflineOrderExample eduOfflineOrderExample = new EduOfflineOrderExample();
        eduOfflineOrderExample.createCriteria().andUserIdEqualTo(eduOfflineOrder.getUserId()).andCourseIdEqualTo(eduOfflineOrder.getCourseId());
        int res = eduOfflineOrderMapper.deleteByExample(eduOfflineOrderExample);

        EduOfflineOrderitemExample eduOfflineOrderitemExample = new EduOfflineOrderitemExample();
        eduOfflineOrderitemExample.createCriteria().andUserIdEqualTo(eduOfflineOrder.getUserId()).andCourseIdEqualTo(eduOfflineOrder.getCourseId());
        int res1 = eduOfflineOrderitemMapper.deleteByExample(eduOfflineOrderitemExample);

        EduOfflineNumExample eduOfflineNumExample = new EduOfflineNumExample();
        eduOfflineNumExample.createCriteria().andUserIdEqualTo(eduOfflineOrder.getUserId()).andCourseIdEqualTo(eduOfflineOrder.getCourseId());
        int res2 = eduOfflineNumMapper.deleteByExample(eduOfflineNumExample);

        //删除课程订单的相关信息后
        //查询是否还有订单，如果订单数量为0，则要删除payInfo的信息
        EduOfflineOrderExample eduOfflineOrderExample2 = new EduOfflineOrderExample();
        eduOfflineOrderExample2.createCriteria().andUserIdEqualTo(eduOfflineOrder.getUserId());
        List<EduOfflineOrder> eduOfflineOrders = eduOfflineOrderMapper.selectByExample(eduOfflineOrderExample);
        if (eduOfflineOrders.size() == 0) {
            EduOfflinePayInfoExample eduOfflinePayInfoExample = new EduOfflinePayInfoExample();
            eduOfflinePayInfoExample.createCriteria().andUserIdEqualTo(eduOfflineOrder.getUserId());
            eduOfflinePayInfoMapper.deleteByExample(eduOfflinePayInfoExample);
        }


        if (res > 0 && res1 > 0 && res2 > 0) {
            return 1;
        }
        return -2;
    }
}
