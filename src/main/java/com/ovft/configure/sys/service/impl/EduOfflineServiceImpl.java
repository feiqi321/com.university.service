package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduOfflineOrder;
import com.ovft.configure.sys.bean.EduOfflineOrderitem;
import com.ovft.configure.sys.dao.EduOfflineOrderMapper;
import com.ovft.configure.sys.dao.EduOfflineOrderitemMapper;
import com.ovft.configure.sys.service.EduOfflineOrderService;
import com.ovft.configure.sys.service.EduOfflineOrderitemService;
import com.ovft.configure.sys.service.EduOfflineService;
import com.ovft.configure.sys.vo.PageBean;
import com.ovft.configure.sys.vo.QueryOffLineVos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vvtxw
 * @create 2019-05-24 15:38
 */
@Service
public class EduOfflineServiceImpl implements EduOfflineService {
    @Resource
    private EduOfflineOrderService eduOfflineOrderService;

    @Resource
    private EduOfflineOrderitemService eduOfflineOrderitemService;


    @Override
    public PageBean queryAllOffInfo(Integer page, Integer size, String schoolId, Integer userId) {
        Page<Object> pageAll = PageHelper.startPage(page, size);
        List<EduOfflineOrder> eduOfflineOrders = eduOfflineOrderService.queryAllOffOrder(schoolId, userId);
        List<EduOfflineOrderitem> eduOfflineOrderitems = eduOfflineOrderitemService.queryAllOffOrderItem(schoolId, userId);
        List<QueryOffLineVos> queryOffLineVoslist = new ArrayList<>();

        BigDecimal accountAllMoney = new BigDecimal(0);

        QueryOffLineVos queryOffLineVos = new QueryOffLineVos();
        List<QueryOffLineVos> queryOffLineVos1 = new ArrayList<>();
        for (EduOfflineOrder eduOfflineOrder : eduOfflineOrders) {
            queryOffLineVos.setSchoolName(eduOfflineOrder.getSchoolName());
            queryOffLineVos.setUserName(eduOfflineOrder.getUserName());
            queryOffLineVos.setPhone(eduOfflineOrder.getPhone());
            queryOffLineVos.setOrderitems(eduOfflineOrderitems);
            accountAllMoney = accountAllMoney.add(eduOfflineOrder.getCousePrice());
            queryOffLineVos.setAccountAllMoney(accountAllMoney);
            queryOffLineVoslist.add(queryOffLineVos);
            queryOffLineVos1.add(queryOffLineVos);
        }
        long total = pageAll.getTotal();
        return new PageBean(page, size, total, queryOffLineVos1);
    }
}
