package com.ovft.configure.sys.web;

import com.ovft.configure.constant.ConstantClassField;
import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.*;
import com.ovft.configure.sys.dao.EduSettlementShowMapper;
import com.ovft.configure.sys.service.EduOrderSettlementService;
import com.ovft.configure.sys.utils.RedisUtil;
import com.ovft.configure.sys.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author vvtxw
 * @create 2019-06-15 10:24
 */
@RestController
@RequestMapping("server/settlement")
public class EduOrderSettlementController {

    @Autowired
    private EduOrderSettlementService eduOrderSettlementService;
    @Resource
    private EduSettlementShowMapper eduSettlementShowMapper;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 订单结算      //	结算状态未结算1正在结算2已结算3
     *
     * @return
     */
    @GetMapping(value = "shows")
    public WebResult showsSettlement(@RequestParam("pageNum") Integer page, @RequestParam("pageSize") Integer size, @RequestParam(value = "schoolId", defaultValue = "1", required = true) Integer schoolId, @RequestParam(value = "settlementStatus", defaultValue = "2", required = true) Integer settlementStatus) {
        EduOrderSettlementVo eduOrderSettlementVo = new EduOrderSettlementVo();
        //根据学校查询出结算的相关金额总数据
        EduSettlementShowExample eduSettlementShowExample = new EduSettlementShowExample();
        eduSettlementShowExample.createCriteria().andSchoolIdEqualTo(schoolId);
        List<EduSettlementShow> eduSettlementShows = eduSettlementShowMapper.selectByExample(eduSettlementShowExample);

        eduOrderSettlementVo.setEduSettlementShows(eduSettlementShows);
        //查询出状态的结算情况
        PageBean pageBean = eduOrderSettlementService.showsSettlement(page, size, schoolId, settlementStatus);
        eduOrderSettlementVo.setEduOrderSettlements(pageBean);
        return new WebResult(StatusCode.OK, "查询成功", eduOrderSettlementVo);
    }

    /**
     * 更改已结算状态
     *
     * @param eduOrderSettlement
     * @return
     */
    @PostMapping(value = "already")
    public WebResult updateAlreadySettlementStatus(HttpServletRequest request, @RequestBody EduOrderSettlement eduOrderSettlement) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if(o != null) {
            Integer id = (Integer) o;
            Admin hget =(Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            if(hget.getRole() != 0) {
                return new WebResult("400", "暂无该权限", "");
            }
        }else {
            return new WebResult("50012", "请重新登录", "");
        }
        Integer res = eduOrderSettlementService.updateAlreadySettlementStatus(eduOrderSettlement);
        if (res > 0) {
            return new WebResult(StatusCode.OK, "结算成功", "");
        }
        if (res == -2) {
            return new WebResult(StatusCode.OK, "状态已为结算状态，无需结算", "");
        }
        return new WebResult(StatusCode.ERROR, "结算失败", "");
    }

    /**
     * 批量更改已结算状态
     *
     * @param eduOrderSettlementVo
     * @return
     */
    @PostMapping(value = "batchalready")
    public WebResult batchUpdateAlreadySettlementStatus(HttpServletRequest request, @RequestBody EduOrderSettlementVo eduOrderSettlementVo) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if(o != null) {
            Integer id = (Integer) o;
            Admin hget =(Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            if(hget.getRole() != 0) {
                return new WebResult("400", "暂无该权限", "");
            }
        }else {
            return new WebResult("50012", "请重新登录", "");
        }
        Integer res = eduOrderSettlementService.batchUpdateAlreadySettlementStatus(eduOrderSettlementVo);
        if (res > 0) {
            return new WebResult(StatusCode.OK, "批量结算成功", "");
        }
        if (res == -2) {
            return new WebResult(StatusCode.OK, "状态已为结算状态，无需结算", "");
        }
        return new WebResult(StatusCode.ERROR, "批量结算失败", "");
    }


}
