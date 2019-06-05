package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduOfflineOrder;
import com.ovft.configure.sys.service.EduOfflineOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author vvtxw
 * @create 2019-06-04 11:55
 */
@RestController
@RequestMapping("offorder")
public class EduOfflineOrderController {

    @Autowired
    private EduOfflineOrderService eduOfflineOrderService;

    /**
     * 根据学员电话查询订单
     *
     * @param phone
     * @return
     */
    @GetMapping("show")
    public WebResult showOffOrderByUserPhone(String phone) {
        List<EduOfflineOrder> eduOfflineOrders = eduOfflineOrderService.showOffOrderByUserPhone(phone);
        return new WebResult(StatusCode.OK, "查询成功", eduOfflineOrders);
    }

    /**
     * 取消订单
     *
     * @param eduOfflineOrder
     * @return
     */
    @GetMapping("delete")
    public WebResult deleteOffOrderByUserPhone(@RequestBody EduOfflineOrder eduOfflineOrder) {
        Integer res = eduOfflineOrderService.deleteOffOrderByUserPhone(eduOfflineOrder);
        if (res > 0) {
            return new WebResult(StatusCode.OK, "取消成功", "");
        }
        return new WebResult(StatusCode.ERROR, "取消失败", "");
    }


}
