package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.service.OrderService;
import com.ovft.configure.sys.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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


    @GetMapping(value = "payrecord")
    public WebResult queryAllPayOrder(HttpServletRequest request) {
        String userId1 = request.getHeader("userId");
        Integer userId = Integer.parseInt(userId1);
//        Integer userId = (Integer) request.getAttribute("userId");
        List<OrderVo> orderVos = orderService.queryAllRecord(userId);
        return new WebResult(StatusCode.OK, "已缴费，报名成功", orderVos);
    }


}
