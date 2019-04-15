package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.Order;
import com.ovft.configure.sys.service.OrderService;
import com.ovft.configure.sys.vo.OrderVo;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author vvtxw
 * @create 2019-04-12 13:38
 */
@RestController
@RequestMapping("pay")
public class OrderController {
    @Autowired
    private OrderService orderService;


    /**
     * 报名记录
     *
     * @return
     */
    @GetMapping(value = "payOrder")
    public WebResult queryAllPayOrder() {
        int userId = 1;
        //TODO
        return new WebResult(StatusCode.OK, "查询成功", orderService.queryAllOrder(userId));

    }


}
