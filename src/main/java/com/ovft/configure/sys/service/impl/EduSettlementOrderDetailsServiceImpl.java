package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ovft.configure.sys.bean.*;
import com.ovft.configure.sys.dao.EduOrderSettlementMapper;
import com.ovft.configure.sys.dao.EduSettlementOrderDetailsMapper;
import com.ovft.configure.sys.dao.EduSettlementShowMapper;
import com.ovft.configure.sys.service.EduSettlementOrderDetailsService;
import com.ovft.configure.sys.vo.EduSettlementOrderDetailsVo;
import com.ovft.configure.sys.vo.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author vvtxw
 * @create 2019-06-15 11:46
 */
@Service
public class EduSettlementOrderDetailsServiceImpl implements EduSettlementOrderDetailsService {

    @Resource
    private EduSettlementOrderDetailsMapper eduSettlementOrderDetailsMapper;
    @Resource
    private EduSettlementShowMapper eduSettlementShowMapper;
    @Resource
    private EduOrderSettlementMapper eduOrderSettlementMapper;

    private EduOrderSettlement eduOrderSettlement = new EduOrderSettlement();


    @Override
    public PageBean showSettlementDetail(EduSettlementOrderDetailsVo eduSettlementOrderDetailsVo) {
        Page<Object> pageAll = PageHelper.startPage(eduSettlementOrderDetailsVo.getPageNum(), eduSettlementOrderDetailsVo.getPageSize());
        List<EduSettlementOrderDetailsVo> list = eduSettlementOrderDetailsMapper.showSettlementDetail(eduSettlementOrderDetailsVo);
        long total = pageAll.getTotal();
        return new PageBean(total, list);
    }

    @Override
    @Transactional
    //	结算状态未结算1正在结算2已结算3
    public Integer updatesettlementNowStatus(EduSettlementOrderDetails eduSettlementOrderDetails) {
        int res = 0;
        //判断是否是未结算状态，如果是，就更改结算中状态
        EduSettlementOrderDetails eduSettlementOrderDetails1 = eduSettlementOrderDetailsMapper.selectByPrimaryKey(eduSettlementOrderDetails.getId());
        if (eduSettlementOrderDetails1.getSettlementStatus() == 1) {
            res = eduSettlementOrderDetailsMapper.updateByPrimaryKeySelective(eduSettlementOrderDetails);
            if (res > 0) {
                //更新3张表的数据及状态
                updateTableStatus(eduSettlementOrderDetails);
            }
        } else {
            res = -2;
        }
        return res;
    }

    private void updateTableStatus(EduSettlementOrderDetails eduSettlementOrderDetails) {
        //1.添加金额到结算中累加
        EduSettlementShow eduSettlementShow = addMoneyToClearing(eduSettlementOrderDetails);
        //2.生成OrderSettlement记录
        EduOrderSettlement orderSettlement = createOrderSettlement(eduSettlementOrderDetails, eduSettlementShow);
        //3.新增结算展示后更新结算订单详情的id
        eduSettlementOrderDetails.setOrderSettlementId(orderSettlement.getId());
        eduSettlementOrderDetailsMapper.updateByPrimaryKeySelective(eduSettlementOrderDetails);
    }

    //生成OrderSettlement记录
    private EduOrderSettlement createOrderSettlement(EduSettlementOrderDetails eduSettlementOrderDetails, EduSettlementShow eduSettlementShow) {
        //新增结算中订单EduOrderSettlement
        EduOrderSettlement eduOrderSettlement = new EduOrderSettlement();
        eduOrderSettlement.setSettleTime(new Date());
        eduOrderSettlement.setSettleNum(1);
        eduOrderSettlement.setSettlementStatus(2);
        eduOrderSettlement.setSettleMoney(eduSettlementOrderDetails.getSettlementAmount());
        eduOrderSettlement.setSchoolId(eduSettlementOrderDetails.getSchoolId());
        eduOrderSettlement.setSchoolName(eduOrderSettlement.getSchoolName());
        //结算中订单id关联订单详细id
        eduOrderSettlement.setSettlementShowId(eduSettlementShow.getId());
        eduOrderSettlementMapper.insertSelective(eduOrderSettlement);
        return eduOrderSettlement;
    }

    //添加金额到结算中累加
    private EduSettlementShow addMoneyToClearing(EduSettlementOrderDetails eduSettlementOrderDetails) {
        //结算表的结算中增加金额
        //查询出结算中的金额
        EduSettlementOrderDetails eduSettlementOrderDetail = eduSettlementOrderDetailsMapper.selectByPrimaryKey(eduSettlementOrderDetails.getId());
        //查询出对应学校的结算中的金额
        EduSettlementShowExample eduSettlementShowExample = new EduSettlementShowExample();
        eduSettlementShowExample.createCriteria().andSchoolIdEqualTo(eduSettlementOrderDetails.getSchoolId());
        List<EduSettlementShow> eduSettlementShows = eduSettlementShowMapper.selectByExample(eduSettlementShowExample);
        if (eduSettlementShows.size() == 0) {
            EduSettlementShow eduSettlementShow = new EduSettlementShow();
            eduSettlementShow.setNoSettlement(BigDecimal.valueOf(0.00));
            BigDecimal allCount = eduSettlementOrderDetail.getSettlementAmount();
            eduSettlementShow.setClearing(allCount);
            eduSettlementShow.setAlreadySettled(BigDecimal.valueOf(0.00));
            eduSettlementShow.setSchoolId(eduSettlementOrderDetails.getSchoolId());
            eduSettlementShow.setSchoolName(eduSettlementOrderDetails.getSchoolName());
            eduSettlementShowMapper.insertSelective(eduSettlementShow);
            return eduSettlementShow;

        }
        for (EduSettlementShow eduSettlementShow : eduSettlementShows) {
            BigDecimal allCount = eduSettlementOrderDetail.getSettlementAmount().add(eduSettlementShow.getClearing());
            eduSettlementShow.setClearing(allCount);
            eduSettlementShowMapper.updateByPrimaryKeySelective(eduSettlementShow);
            //新增结算展示后更新结算订单详情的id
            eduSettlementOrderDetails.setOrderSettlementId(eduSettlementShow.getId());
            eduSettlementOrderDetailsMapper.updateByPrimaryKeySelective(eduSettlementOrderDetails);
            return eduSettlementShow;
        }
        return null;
    }

    @Override
    @Transactional
    public Integer updatesettlementNowStatuss(Integer id, Integer[] ids) {
        int res = 0;
        Integer num = ids.length;
        //判断是否是未结算状态，如果是，就更改结算中状态
        EduSettlementOrderDetails eduSettlementOrderDetails = eduSettlementOrderDetailsMapper.selectByPrimaryKey(id);
        if (eduSettlementOrderDetails.getSettlementStatus() == 1) {
            eduSettlementOrderDetails.setSettlementStatus(2);
            res = eduSettlementOrderDetailsMapper.updateByPrimaryKeySelective(eduSettlementOrderDetails);
            if (res > 0) {
                bathupdateTablestatus(id == ids[0], num, eduSettlementOrderDetails);
            }
        } else {
            res = -2;
        }
        return res;
    }

    //批量修改状态
    private void bathupdateTablestatus(boolean b, Integer num, EduSettlementOrderDetails eduSettlementOrderDetails) {
        //1.添加金额到结算中累加
        EduSettlementShow eduSettlementShow = addMoneyToClearing(eduSettlementOrderDetails);
        //2.新增结算中订单EduOrderSettlement
        eduOrderSettlement.setSettleTime(new Date());
        eduOrderSettlement.setSettleNum(num);
        eduOrderSettlement.setSettlementStatus(2);
        BigDecimal allCount = new BigDecimal(0);
        if (eduOrderSettlement.getSettleMoney() != null) {
            allCount = eduSettlementOrderDetails.getSettlementAmount().add(eduOrderSettlement.getSettleMoney());
        } else {
            allCount = eduSettlementOrderDetails.getSettlementAmount();
        }
        eduOrderSettlement.setSettleMoney(allCount);
        eduOrderSettlement.setSchoolId(eduSettlementOrderDetails.getSchoolId());
        eduOrderSettlement.setSchoolName(eduOrderSettlement.getSchoolName());
        //结算中订单id关联订单详细id
        eduOrderSettlement.setSettlementShowId(eduSettlementShow.getId());

        if (b) {
            eduOrderSettlementMapper.insertSelective(eduOrderSettlement);
        } else {
            eduOrderSettlementMapper.updateByPrimaryKeySelective(eduOrderSettlement);
        }
        //3.新增结算展示后更新结算订单详情的id
        eduSettlementOrderDetails.setOrderSettlementId(eduOrderSettlement.getId());
        eduSettlementOrderDetailsMapper.updateByPrimaryKeySelective(eduSettlementOrderDetails);
    }

    @Override
    @Transactional
    public Integer bathupdatesStatus(EduSettlementOrderDetails eduSettlementOrderDetails) {
        //查询出所有未结算的状态数据，一键更改结算中的状态
        EduSettlementOrderDetailsExample eduSettlementOrderDetailsExample = new EduSettlementOrderDetailsExample();
        eduSettlementOrderDetailsExample.createCriteria().andSettlementStatusEqualTo(1);
        List<EduSettlementOrderDetails> eduSettlementOrderDetails1 = eduSettlementOrderDetailsMapper.selectByExample(eduSettlementOrderDetailsExample);
        int res = 0;
        for (EduSettlementOrderDetails settlementOrderDetails : eduSettlementOrderDetails1) {
            eduSettlementOrderDetails.setSettlementStatus(2);
            eduSettlementOrderDetails.setId(settlementOrderDetails.getId());
            eduSettlementOrderDetails.setSchoolId(settlementOrderDetails.getSchoolId());
            res = eduSettlementOrderDetailsMapper.updateByPrimaryKeySelective(eduSettlementOrderDetails);
            if (res > 0) {
                //1.添加金额到结算中累加
                EduSettlementShow eduSettlementShow = addMoneyToClearing(settlementOrderDetails);
                //2.新增结算中订单EduOrderSettlement
                eduOrderSettlement.setSettleTime(new Date());
                eduOrderSettlement.setSettleNum(eduSettlementOrderDetails1.size());
                eduOrderSettlement.setSettlementStatus(2);
                BigDecimal allCount = new BigDecimal(0);
                if (eduOrderSettlement.getSettleMoney() != null) {
                    allCount = settlementOrderDetails.getSettlementAmount().add(eduOrderSettlement.getSettleMoney());
                } else {
                    allCount = settlementOrderDetails.getSettlementAmount();
                }
                eduOrderSettlement.setSettleMoney(allCount);
                eduOrderSettlement.setSchoolId(eduSettlementOrderDetails.getSchoolId());
                eduOrderSettlement.setSchoolName(eduOrderSettlement.getSchoolName());
                //结算中订单id关联订单详细id
                eduOrderSettlement.setSettlementShowId(eduSettlementShow.getId());

                if (eduSettlementOrderDetails1.get(0) == settlementOrderDetails) {
                    eduOrderSettlementMapper.insertSelective(eduOrderSettlement);
                } else {
                    eduOrderSettlementMapper.updateByPrimaryKeySelective(eduOrderSettlement);
                }
                //3.新增结算展示后更新结算订单详情的id
                eduSettlementOrderDetails.setOrderSettlementId(eduOrderSettlement.getId());
                eduSettlementOrderDetailsMapper.updateByPrimaryKeySelective(eduSettlementOrderDetails);
            }
        }
        return res;
    }
}
