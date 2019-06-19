package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduSettlementOrderDetails;
import com.ovft.configure.sys.service.EduSettlementOrderDetailsService;
import com.ovft.configure.sys.vo.EduSettlementOrderDetailsVo;
import com.ovft.configure.sys.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author vvtxw
 * @create 2019-06-15 11:43
 */
@RestController
@RequestMapping("server/settlementdetail")
public class EduSettlementOrderDetailsController {

    @Autowired
    private EduSettlementOrderDetailsService eduSettlementOrderDetailsService;

    /**
     * 订单明细模糊查询类表      //	结算状态未结算1正在结算2已结算3
     *
     * @param eduSettlementOrderDetailsVo
     * @return
     */
    @PostMapping(value = "shows")
    public WebResult settlementDetailShow(@RequestBody EduSettlementOrderDetailsVo eduSettlementOrderDetailsVo) {
        PageBean pageBean = eduSettlementOrderDetailsService.showSettlementDetail(eduSettlementOrderDetailsVo);
        return new WebResult(StatusCode.OK, "查询成功", pageBean);
    }

    /**
     * 未结算账单---> 更换为--->  结算中状态     //	结算状态未结算1正在结算2已结算3
     *
     * @param eduSettlementOrderDetails
     * @return
     */
    @PostMapping(value = "update")
    public WebResult updatesettlementNowStatus(@RequestBody EduSettlementOrderDetails eduSettlementOrderDetails) {
        Integer res = eduSettlementOrderDetailsService.updatesettlementNowStatus(eduSettlementOrderDetails);
        if (res > 0) {
            return new WebResult(StatusCode.OK, "状态更换结算中成功", "");
        }
        if (res == -2) {
            return new WebResult(StatusCode.OK, "状态非未结算状态，无需更换", "");
        }
        return new WebResult(StatusCode.ERROR, "状态更换结算中失败", "");
    }

    /**
     * 未结算账单---> 更换为--->  结算中状态      //	结算状态未结算1正在结算2已结算3
     *
     * @param eduSettlementOrderDetailsVo
     * @return
     */
    @PostMapping(value = "updates")
    public WebResult updatesettlementNowStatuss(@RequestBody EduSettlementOrderDetailsVo eduSettlementOrderDetailsVo) {
        Integer res = 0;
        Integer[] ids = eduSettlementOrderDetailsVo.getIds();
        for (Integer id : ids) {
            res = eduSettlementOrderDetailsService.updatesettlementNowStatuss(id, ids);
        }
        if (res > 0) {
            return new WebResult(StatusCode.OK, "状态批量更换结算中成功", "");
        }
        if (res == -2) {
            return new WebResult(StatusCode.OK, "状态非未结算状态，无需更换", "");
        }
        return new WebResult(StatusCode.ERROR, "状态批量更换结算中失败", "");
    }

    /**
     * 一键结算中
     *
     * @param eduSettlementOrderDetails //	结算状态未结算1正在结算2已结算3
     * @return
     */
    @PostMapping(value = "bathupdates")
    public WebResult bathupdatesStatus(@RequestBody EduSettlementOrderDetails eduSettlementOrderDetails) {
        Integer res = eduSettlementOrderDetailsService.bathupdatesStatus(eduSettlementOrderDetails);
        if (res > 0) {
            return new WebResult(StatusCode.OK, "状态一键更换结算中成功", "");
        }
        if (res == -2) {
            return new WebResult(StatusCode.OK, "状态非未结算状态，无需更换", "");
        }
        return new WebResult(StatusCode.ERROR, "状态一键更换结算中失败", "");
    }
}
