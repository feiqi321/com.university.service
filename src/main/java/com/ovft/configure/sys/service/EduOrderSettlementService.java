package com.ovft.configure.sys.service;

import com.ovft.configure.sys.bean.EduOrderSettlementVo;
import com.ovft.configure.sys.vo.PageBean;

import java.util.List;

/**
 * @author vvtxw
 * @create 2019-06-15 10:25
 */
public interface EduOrderSettlementService {
    //查询出结算状态的总数据
    PageBean showsSettlement(Integer page, Integer size, Integer schoolId, Integer settlementStatus);

}
