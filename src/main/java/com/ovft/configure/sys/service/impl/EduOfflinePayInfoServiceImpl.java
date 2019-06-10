package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ovft.configure.constant.OrderStatus;
import com.ovft.configure.sys.bean.*;
import com.ovft.configure.sys.dao.EduOfflineNumMapper;
import com.ovft.configure.sys.dao.EduOfflineOrderMapper;
import com.ovft.configure.sys.dao.EduOfflineOrderitemMapper;
import com.ovft.configure.sys.dao.EduOfflinePayInfoMapper;
import com.ovft.configure.sys.service.EduOfflinePayInfoService;
import com.ovft.configure.sys.vo.PageBean;
import com.ovft.configure.sys.vo.QueryOffLineVos;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author vvtxw
 * @create 2019-05-26 21:27
 */
@Service
public class EduOfflinePayInfoServiceImpl implements EduOfflinePayInfoService {
    @Resource
    private EduOfflinePayInfoMapper eduOfflinePayInfoMapper;

    @Resource
    private EduOfflineOrderMapper eduOfflineOrderMapper;

    @Resource
    private EduOfflineOrderitemMapper eduOfflineOrderitemMapper;

    @Resource
    private EduOfflineNumMapper eduOfflineNumMapper;

    @Override
    public Integer insertPayInfo(EduOfflinePayInfo eduOfflinePayInfo) {
        return eduOfflinePayInfoMapper.insertSelective(eduOfflinePayInfo);
    }

    @Override
    @Transactional
    public PageBean queryAllOffInfo(Integer page, Integer size, Integer schoolId, String schoolName, String telephone, String payStatus) {
        Page<Object> pageAll = PageHelper.startPage(page, size);
        Map<String, Object> map = new HashMap<>();
        map.put("schoolName", schoolName);
        map.put("telephone", telephone);
        map.put("payStatus", payStatus);
        map.put("schoolId", schoolId);
        //显示线下的所有数据
        List<EduOfflinePayInfo> eduOfflinePayInfos = eduOfflinePayInfoMapper.selectOfflineList(map);

        List<QueryOffLineVos> queryOffLineVoss = new ArrayList<>();
        long total = pageAll.getTotal();

        for (EduOfflinePayInfo eduOfflinePayInfo : eduOfflinePayInfos) {
            EduOfflineNumExample eduOfflineNumExample = new EduOfflineNumExample();
            eduOfflineNumExample.createCriteria().andUserIdEqualTo(eduOfflinePayInfo.getUserId()).andSchoolIdEqualTo(eduOfflinePayInfo.getSchoolId());
            List<EduOfflineNum> eduOfflineNums = eduOfflineNumMapper.selectByExample(eduOfflineNumExample);
            BigDecimal accountAllMoney = new BigDecimal(0);//放在里头累计
            for (EduOfflineNum eduOfflineNum : eduOfflineNums) {
                accountAllMoney = accountAllMoney.add(eduOfflineNum.getCoursePrice());
            }
            QueryOffLineVos queryOffLineVos = new QueryOffLineVos();//放里头防止覆盖
            queryOffLineVos.setOfflineNums(eduOfflineNums);
            queryOffLineVos.setUserName(eduOfflinePayInfo.getUserName());
            queryOffLineVos.setPhone(eduOfflinePayInfo.getTelephone());
            queryOffLineVos.setSchoolName(eduOfflinePayInfo.getSchoolName());
            queryOffLineVos.setPayStatus(eduOfflinePayInfo.getPayStatus());
            queryOffLineVos.setAccountAllMoney(accountAllMoney);
            queryOffLineVos.setUpdateTime(eduOfflinePayInfo.getPayUpdatetime());
            queryOffLineVos.setId(eduOfflinePayInfo.getId());
            queryOffLineVos.setUserId(eduOfflinePayInfo.getUserId());
            queryOffLineVoss.add(queryOffLineVos);
        }



      /*  //过滤要显示的数据
        List<EduOfflinePayInfo> showsInfo = new ArrayList<>();
        for (EduOfflinePayInfo eduOfflinePayInfo : eduOfflinePayInfos) {
            //查询出最新的下单信息保留它
            EduOfflinePayInfo showInfo = eduOfflinePayInfoMapper.queryShowOfflinePayInfo(eduOfflinePayInfo.getTelephone());
            //id与之不同则删除，相同则保留
            if (eduOfflinePayInfo.getId() != showInfo.getId()) {
                eduOfflinePayInfoMapper.deleteByPrimaryKey(eduOfflinePayInfo.getId());
            } else {
                showsInfo.add(showInfo);
            }
        }*/

        return new PageBean(page, size, total, queryOffLineVoss);
    }


    @Override
    public List<QueryOffLineVos> queryAllOff(Integer schoolId, String schoolName, String telephone, String
            payStatus) {
        Map<String, Object> map = new HashMap<>();
        map.put("schoolName", schoolName);
        map.put("telephone", telephone);
        map.put("payStatus", payStatus);
        map.put("schoolId", schoolId);
        List<EduOfflinePayInfo> eduOfflinePayInfos = eduOfflinePayInfoMapper.selectOfflineList(map);

        List<QueryOffLineVos> queryOffLineVoss = new ArrayList<>();

        for (EduOfflinePayInfo eduOfflinePayInfo : eduOfflinePayInfos) {
            EduOfflineNumExample eduOfflineNumExample = new EduOfflineNumExample();
            eduOfflineNumExample.createCriteria().andUserIdEqualTo(eduOfflinePayInfo.getUserId()).andSchoolIdEqualTo(eduOfflinePayInfo.getSchoolId());
            List<EduOfflineNum> eduOfflineNums = eduOfflineNumMapper.selectByExample(eduOfflineNumExample);
            BigDecimal accountAllMoney = new BigDecimal(0);//放在里头累计
            for (EduOfflineNum eduOfflineNum : eduOfflineNums) {
                accountAllMoney = accountAllMoney.add(eduOfflineNum.getCoursePrice());
            }
            QueryOffLineVos queryOffLineVos = new QueryOffLineVos();//放里头防止覆盖
            queryOffLineVos.setOfflineNums(eduOfflineNums);
            queryOffLineVos.setUserName(eduOfflinePayInfo.getUserName());
            queryOffLineVos.setPhone(eduOfflinePayInfo.getTelephone());
            queryOffLineVos.setSchoolName(eduOfflinePayInfo.getSchoolName());
            queryOffLineVos.setPayStatus(eduOfflinePayInfo.getPayStatus());
            queryOffLineVos.setAccountAllMoney(accountAllMoney);
            queryOffLineVos.setUpdateTime(eduOfflinePayInfo.getPayUpdatetime());
            queryOffLineVos.setId(eduOfflinePayInfo.getId());
            queryOffLineVos.setUserId(eduOfflinePayInfo.getUserId());
            queryOffLineVoss.add(queryOffLineVos);
        }

        return queryOffLineVoss;
    }

    @Override
    @Transactional
    public Integer updatePayStatus(EduOfflinePayInfo eduOfflinePayInfo) {
        int res = eduOfflinePayInfoMapper.updateByPrimaryKeySelective(eduOfflinePayInfo);
        //根据userid查询出所有订单信息
        EduOfflineOrderExample eduOfflineOrderExample = new EduOfflineOrderExample();
        eduOfflineOrderExample.createCriteria().andUserIdEqualTo(eduOfflinePayInfo.getUserId());
        List<EduOfflineOrder> eduOfflineOrders = eduOfflineOrderMapper.selectByExample(eduOfflineOrderExample);

        //根据userid查询出所有订单详情信息
        EduOfflineOrderitemExample eduOfflineOrderitemExample1 = new EduOfflineOrderitemExample();
        eduOfflineOrderitemExample1.createCriteria().andUserIdEqualTo(eduOfflinePayInfo.getUserId());
        List<EduOfflineOrderitem> eduOfflineOrderitems = eduOfflineOrderitemMapper.selectByExample(eduOfflineOrderitemExample1);

        //根据userid查询出所有课程订单信息
        EduOfflineNumExample eduOfflineNumExample = new EduOfflineNumExample();
        eduOfflineNumExample.createCriteria().andUserIdEqualTo(eduOfflinePayInfo.getUserId());
        List<EduOfflineNum> eduOfflineNums = eduOfflineNumMapper.selectByExample(eduOfflineNumExample);

        //如果支付的状态修改成功，传入的已经缴费1
        if (res > 0 && eduOfflinePayInfo.getPayStatus().equals("1")) {
            for (EduOfflineOrder eduOfflineOrder : eduOfflineOrders) {
                eduOfflineOrder.setPayStatus("1");
                eduOfflineOrderMapper.updateByPrimaryKeySelective(eduOfflineOrder);
            }
            for (EduOfflineOrderitem eduOfflineOrderitem : eduOfflineOrderitems) {
                eduOfflineOrderitem.setPayStatus("1");
                eduOfflineOrderitemMapper.updateByPrimaryKeySelective(eduOfflineOrderitem);
            }
            for (EduOfflineNum eduOfflineNum : eduOfflineNums) {
                eduOfflineNum.setPayStatus("1");
                eduOfflineNumMapper.updateByPrimaryKeySelective(eduOfflineNum);
            }

        } else if (res > 0 && eduOfflinePayInfo.getPayStatus().equals("2")) {
            for (EduOfflineOrder eduOfflineOrder : eduOfflineOrders) {
                eduOfflineOrder.setPayStatus("2");
                eduOfflineOrderMapper.updateByPrimaryKeySelective(eduOfflineOrder);
            }
            for (EduOfflineOrderitem eduOfflineOrderitem : eduOfflineOrderitems) {
                eduOfflineOrderitem.setPayStatus("2");
                eduOfflineOrderitemMapper.updateByPrimaryKeySelective(eduOfflineOrderitem);
            }
            for (EduOfflineNum eduOfflineNum : eduOfflineNums) {
                eduOfflineNum.setPayStatus("2");
                eduOfflineNumMapper.updateByPrimaryKeySelective(eduOfflineNum);
            }
        } else {
            return -1;
        }
        return res;
    }

    @Override
    @Transactional
    public Integer bathUpdataPaystatus(EduOfflinePayInfo eduOfflinePayInfo) {
        //查出所有课程的id
        List<EduOfflinePayInfo> eduOfflinePayInfos = eduOfflinePayInfoMapper.selectByExample(null);


        //批量修改
        int res = 0;
        for (EduOfflinePayInfo OfflinePayInfo : eduOfflinePayInfos) {
            eduOfflinePayInfo.setId(OfflinePayInfo.getId());
            res = eduOfflinePayInfoMapper.updateByPrimaryKeySelective(eduOfflinePayInfo);

            //根据userid查询出所有订单信息
            EduOfflineOrderExample eduOfflineOrderExample = new EduOfflineOrderExample();
            eduOfflineOrderExample.createCriteria().andUserIdEqualTo(OfflinePayInfo.getUserId());
            List<EduOfflineOrder> eduOfflineOrders = eduOfflineOrderMapper.selectByExample(eduOfflineOrderExample);

            //根据userid查询出所有订单详情信息
            EduOfflineOrderitemExample eduOfflineOrderitemExample1 = new EduOfflineOrderitemExample();
            eduOfflineOrderitemExample1.createCriteria().andUserIdEqualTo(OfflinePayInfo.getUserId());
            List<EduOfflineOrderitem> eduOfflineOrderitems = eduOfflineOrderitemMapper.selectByExample(eduOfflineOrderitemExample1);
            //根据userid查询出所有课程订单信息
            EduOfflineNumExample eduOfflineNumExample = new EduOfflineNumExample();
            eduOfflineNumExample.createCriteria().andUserIdEqualTo(OfflinePayInfo.getUserId());
            List<EduOfflineNum> eduOfflineNums = eduOfflineNumMapper.selectByExample(eduOfflineNumExample);


            //如果支付的状态修改成功，传入的已经缴费1
            if (res > 0 && eduOfflinePayInfo.getPayStatus().equals(OrderStatus.OFFPAY)) {
                for (EduOfflineOrder eduOfflineOrder : eduOfflineOrders) {
                    eduOfflineOrder.setPayStatus(OrderStatus.OFFPAY);
                    //更改已经支付状态
                    eduOfflineOrderMapper.updateByPrimaryKeySelective(eduOfflineOrder);
                }
                for (EduOfflineOrderitem eduOfflineOrderitem : eduOfflineOrderitems) {
                    eduOfflineOrderitem.setPayStatus(OrderStatus.OFFPAY);
                    //更改已经支付状态
                    eduOfflineOrderitemMapper.updateByPrimaryKeySelective(eduOfflineOrderitem);
                }
                for (EduOfflineNum eduOfflineNum : eduOfflineNums) {
                    eduOfflineNum.setPayStatus(OrderStatus.OFFPAY);
                    eduOfflineNumMapper.updateByPrimaryKeySelective(eduOfflineNum);
                }

            } else if (res > 0 && eduOfflinePayInfo.getPayStatus().equals(OrderStatus.OFFUNPAY)) {
                for (EduOfflineOrder eduOfflineOrder : eduOfflineOrders) {
                    //更改已经未支付状态
                    eduOfflineOrder.setPayStatus(OrderStatus.OFFUNPAY);
                    eduOfflineOrderMapper.updateByPrimaryKeySelective(eduOfflineOrder);
                }
                for (EduOfflineOrderitem eduOfflineOrderitem : eduOfflineOrderitems) {
                    //更改已经未支付状态
                    eduOfflineOrderitem.setPayStatus(OrderStatus.OFFUNPAY);
                    eduOfflineOrderitemMapper.updateByPrimaryKeySelective(eduOfflineOrderitem);
                }
                for (EduOfflineNum eduOfflineNum : eduOfflineNums) {
                    eduOfflineNum.setPayStatus(OrderStatus.OFFUNPAY);
                    eduOfflineNumMapper.updateByPrimaryKeySelective(eduOfflineNum);
                }
            } else {
                return -1;
            }
        }
        return res;
    }

    @Override
    public List<EduOfflinePayInfo> queryAllPayInfo(String phone) {
        EduOfflinePayInfoExample eduOfflinePayInfoExample = new EduOfflinePayInfoExample();
        eduOfflinePayInfoExample.createCriteria().andTelephoneEqualTo(phone);
        List<EduOfflinePayInfo> eduOfflinePayInfos = eduOfflinePayInfoMapper.selectByExample(eduOfflinePayInfoExample);
        return eduOfflinePayInfos;
    }


}
