package com.ovft.configure.sys.service;

import com.ovft.configure.sys.vo.EduSettlementOrderDetailsVo;
import com.ovft.configure.sys.vo.PageBean;

import java.util.List;

/**
 * @author vvtxw
 * @create 2019-06-15 11:46
 */
public interface EduSettlementOrderDetailsService {

    //订单明细模糊查询类表
    PageBean showSettlementDetail(EduSettlementOrderDetailsVo eduSettlementOrderDetailsVo);
}
