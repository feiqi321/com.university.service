package com.ovft.configure.sys.service;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.vo.BankCardVo;

public interface BankCardService {

    public WebResult createCard(BankCardVo bankCardVo);

    public WebResult cardList(int userId);

    public WebResult deleteCard(Integer cardId);

    public WebResult findCard(Integer cardId);
}
