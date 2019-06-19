package com.ovft.configure.sys.service;

import com.ovft.configure.sys.bean.EduSettlementOrderDetails;
import com.ovft.configure.sys.vo.EduSettlementOrderDetailsVo;
import com.ovft.configure.sys.vo.PageBean;

/**
 * @author vvtxw
 * @create 2019-06-15 11:46
 */
public interface EduSettlementOrderDetailsService {

    //订单明细模糊查询类表
    PageBean showSettlementDetail(EduSettlementOrderDetailsVo eduSettlementOrderDetailsVo);

    //更换为结算中状态
    Integer updatesettlementNowStatus(EduSettlementOrderDetails eduSettlementOrderDetails);

    //根据ids修改结算中状态
    Integer updatesettlementNowStatuss(Integer id, Integer[] ids);

    //批量修改结算中的状态
    Integer bathupdatesStatus(EduSettlementOrderDetails eduSettlementOrderDetails);
}
