package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduSettlementOrderDetails;
import com.ovft.configure.sys.service.EduSettlementOrderDetailsService;
import com.ovft.configure.sys.vo.EduSettlementOrderDetailsVo;
import com.ovft.configure.sys.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author vvtxw
 * @create 2019-06-15 11:43
 */
@RestController
@RequestMapping("settlementdetail")
public class EduSettlementOrderDetailsController {

    @Autowired
    private EduSettlementOrderDetailsService eduSettlementOrderDetailsService;

    /**
     * 订单明细模糊查询类表
     *
     * @param eduSettlementOrderDetailsVo
     * @return
     */
    @PostMapping(value = "shows")
    public WebResult settlementDetailShow(@RequestBody EduSettlementOrderDetailsVo eduSettlementOrderDetailsVo) {
        PageBean pageBean = eduSettlementOrderDetailsService.showSettlementDetail(eduSettlementOrderDetailsVo);
        return new WebResult(StatusCode.OK, "查询成功", pageBean);
    }


}
