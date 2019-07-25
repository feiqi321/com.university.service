package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.StatusCode;
import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.EduCart;
import com.ovft.configure.sys.service.EduBookGoodsService;
import com.ovft.configure.sys.service.EduCartService;
import com.ovft.configure.sys.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author vvtxw
 * @create 2019-05-17 14:10
 */

@RestController
@RequestMapping("cart")
public class EduCartController {
    @Autowired
    private EduCartService eduCartService;

    @Autowired
    private EduBookGoodsService eduBookGoodsService;

    /**
     * 添加购物车
     *
     * @param goodsId
     * @param goodsNum
     * @param request
     * @return
     */
    @GetMapping(value = "add")
    public WebResult addCart(@RequestParam(required = true) Integer goodsId, @RequestParam(required = true) Integer goodsNum, HttpServletRequest request) {
        String userId1 = request.getHeader("userId");
        Integer userId = Integer.valueOf(userId1);

        String schoolId1 = request.getHeader("schoolId");
        Integer schoolId = Integer.valueOf(schoolId1);
        if (userId != null) {
            //判断该商品是否已经加入购物车。如果已经加入，则提示已经加入，无需再操作
            EduCart eduCart = new EduCart();
            eduCart.setGoodsId(goodsId);
            eduCart.setUserId(userId);

            long num = eduCartService.countNum(userId, goodsId);
            if (num >= 1) {
                return new WebResult(StatusCode.OK, "亲，该商品已经加入购物车，请在购物车加数量");
            }
            eduCartService.addCart(goodsId, goodsNum, userId, schoolId);
            return new WebResult(StatusCode.OK, "亲，我在购物车等你哦！");
        }
        return new WebResult(StatusCode.ERROR, "您还没有登录，请登录");
    }

    /**
     * 展示购物车
     *
     * @param request
     * @return
     */
    @GetMapping(value = "showcart")
    public WebResult queryAllCart(HttpServletRequest request) {
        String userId1 = request.getHeader("userId");
        Integer userId = Integer.valueOf(userId1);
        if (userId != null) {
            PageBean pageBean = eduCartService.showAllCartByUserId(userId);
            return new WebResult(StatusCode.OK, "查询成功", pageBean);
        }
        return new WebResult(StatusCode.ERROR, "您还没有登录，请登录");
    }

}
