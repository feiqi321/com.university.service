package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ovft.configure.sys.bean.*;
import com.ovft.configure.sys.dao.EduOrderSettlementMapper;
import com.ovft.configure.sys.dao.EduSettlementOrderDetailsMapper;
import com.ovft.configure.sys.dao.EduSettlementShowMapper;
import com.ovft.configure.sys.service.EduOrderSettlementService;
import com.ovft.configure.sys.vo.PageBean;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author vvtxw
 * @create 2019-06-15 10:25
 */
@Service
public class EduOrderSettlementServiceImpl implements EduOrderSettlementService {

    @Resource
    private EduOrderSettlementMapper eduOrderSettlementMapper;

    @Resource
    private EduSettlementShowMapper eduSettlementShowMapper;

    @Resource
    private EduSettlementOrderDetailsMapper eduSettlementOrderDetailsMapper;

    @Override
    public PageBean showsSettlement(Integer page, Integer size, Integer schoolId, Integer settlementStatus) {
        Page<Object> pageAll = PageHelper.startPage(page, size);
        Map<String, Object> map = new HashMap<>();
        map.put("schoolId", schoolId);
        map.put("settlementStatus", settlementStatus);
        List<EduOrderSettlement> eduOrderSettlements = eduOrderSettlementMapper.queryAllSettlementStatus(map);
        long total = pageAll.getTotal();
        return new PageBean(page, size, total, eduOrderSettlements);
    }

    @Transactional
    @Override
    public Integer updateAlreadySettlementStatus(EduOrderSettlement eduOrderSettlement) {
        return getResult(eduOrderSettlement);
    }

    //获取结算的状态结果
    @NotNull
    private Integer getResult(EduOrderSettlement eduOrderSettlement) {
        if (eduOrderSettlement.getSettlementStatus() == 3) {
            return -2;
        }
        //更新已结算的状态
        eduOrderSettlement.setSettlementStatus(3);
        int res = eduOrderSettlementMapper.updateByPrimaryKeySelective(eduOrderSettlement);
        //更新已付款的金额，清零结算中的金额
        //查询出结算订单的结算金额
        EduOrderSettlementExample eduOrderSettlementExample = new EduOrderSettlementExample();
        eduOrderSettlementExample.createCriteria().andIdEqualTo(eduOrderSettlement.getId());
        List<EduOrderSettlement> eduOrderSettlements = eduOrderSettlementMapper.selectByExample(eduOrderSettlementExample);
        BigDecimal orderMoney = new BigDecimal(0);
        for (EduOrderSettlement orderSettlement : eduOrderSettlements) {
            orderMoney = orderSettlement.getSettleMoney();
        }
        //查询出结算订单显示的金额
        EduSettlementShowExample eduSettlementShowExample = new EduSettlementShowExample();
        eduSettlementShowExample.createCriteria().andIdEqualTo(eduOrderSettlement.getSettlementShowId());
        List<EduSettlementShow> eduSettlementShows = eduSettlementShowMapper.selectByExample(eduSettlementShowExample);
        BigDecimal alreadyMoney = new BigDecimal(0);
        BigDecimal clear = new BigDecimal(0);
        for (EduSettlementShow eduSettlementShow : eduSettlementShows) {
            alreadyMoney = eduSettlementShow.getAlreadySettled();
            clear = eduSettlementShow.getClearing();
        }
        BigDecimal endMoney = orderMoney.add(alreadyMoney);

        //1.清零结算中的金额
        EduSettlementShow eduSettlementShow = new EduSettlementShow();
        eduSettlementShow.setId(eduOrderSettlement.getSettlementShowId());
        BigDecimal zoro = new BigDecimal(0);
        if (clear.equals(0)) {
            eduSettlementShow.setClearing(zoro);
        }
        BigDecimal quit = clear.subtract(orderMoney);

        //查询出结算中的金额
        eduSettlementShow.setClearing(quit);
        //2.更新已付款的金额
        eduSettlementShow.setAlreadySettled(endMoney);
        //3.更新结算订单的相关金额
        int res1 = eduSettlementShowMapper.updateByPrimaryKeySelective(eduSettlementShow);

        //4.更新结算订单详情的支付状态
        //查询出详细订单的个数
        EduSettlementOrderDetailsExample eduSettlementOrderDetailsExample = new EduSettlementOrderDetailsExample();
        eduSettlementOrderDetailsExample.createCriteria().andOrderSettlementIdEqualTo(eduOrderSettlement.getId());
        List<EduSettlementOrderDetails> eduSettlementOrderDetails1 = eduSettlementOrderDetailsMapper.selectByExample(eduSettlementOrderDetailsExample);
        int res2 = 0;
        for (EduSettlementOrderDetails eduSettlementOrderDetails : eduSettlementOrderDetails1) {
            eduSettlementOrderDetails.setSettlementStatus(3);
            res2 = eduSettlementOrderDetailsMapper.updateByPrimaryKeySelective(eduSettlementOrderDetails);
        }
        if (res > 0 && res1 > 0 && res2 > 0) {
            return res;
        }
        return -1;
    }


    @Override
    public Integer batchUpdateAlreadySettlementStatus(EduOrderSettlementVo eduOrderSettlementVo) {
        Integer[] ids = eduOrderSettlementVo.getIds();
        Integer res = 0;
        for (Integer id : ids) {
            EduOrderSettlementExample eduOrderSettlementExample = new EduOrderSettlementExample();
            eduOrderSettlementExample.createCriteria().andIdEqualTo(id);
            List<EduOrderSettlement> eduOrderSettlements = eduOrderSettlementMapper.selectByExample(eduOrderSettlementExample);
            for (EduOrderSettlement eduOrderSettlement : eduOrderSettlements) {
                if (eduOrderSettlement.getSettlementStatus() == 3) {
                    return -2;
                }
                //获取结算的状态结果
                res = getResult(eduOrderSettlement);
            }
        }
        return res;
    }


}
