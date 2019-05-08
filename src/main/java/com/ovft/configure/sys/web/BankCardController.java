package com.ovft.configure.sys.web;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.service.BankCardService;
import com.ovft.configure.sys.vo.BankCardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName BankCardController    银行卡
 * @Author zqx
 * @Version 1.0
 **/

@RestController
@RequestMapping("/bankcard")
public class BankCardController {

    @Autowired
    private BankCardService bankCardService;

    /**
     * 添加银行卡
     *
     * @param bankCardVo
     * @return
     */
    @PostMapping(value = "/createCard")
    public WebResult createCard(HttpServletRequest request, @RequestBody BankCardVo bankCardVo) {
        bankCardVo.setUserId(Integer.parseInt(request.getHeader("userId")));
        return bankCardService.createCard(bankCardVo);
    }

    /**
     * 银行卡详细信息
     *
     * @param cardId
     * @return
     */
    @GetMapping(value = "/findCard")
    public WebResult findCard(@RequestParam(value = "cardId") Integer cardId) {
        return bankCardService.findCard(cardId);
    }

    /**
     * 银行卡列表
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/cardList")
    public WebResult cardList(HttpServletRequest request) {
        return bankCardService.cardList(Integer.parseInt(request.getHeader("userId")));
    }

    /**
     * 删除银行卡
     *
     * @param cardId
     * @return
     */
    @GetMapping(value = "/deleteCard")
    public WebResult deleteCard(@RequestParam(value = "cardId") Integer cardId) {
        return bankCardService.deleteCard(cardId);
    }

}
