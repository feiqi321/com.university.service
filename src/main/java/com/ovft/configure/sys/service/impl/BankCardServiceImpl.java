package com.ovft.configure.sys.service.impl;

import com.ovft.configure.http.result.WebResult;
import com.ovft.configure.sys.bean.BankCard;
import com.ovft.configure.sys.dao.BankCardMapper;
import com.ovft.configure.sys.service.BankCardService;
import com.ovft.configure.sys.utils.MD5Utils;
import com.ovft.configure.sys.utils.RedisUtil;
import com.ovft.configure.sys.utils.SecurityUtils;
import com.ovft.configure.sys.vo.BankCardVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName BankCardServiceImpl
 * @Author zqx
 * @Version 1.0
 **/
@Service
public class BankCardServiceImpl implements BankCardService {

    private static final Logger logger = LoggerFactory.getLogger(BankCardServiceImpl.class);

    @Resource
    private BankCardMapper bankCardMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    @Transactional
    public WebResult createCard(BankCardVo bankCardVo) {
        boolean b = SecurityUtils.securityPhone(bankCardVo.getPhone());
        if(!b) {
            return new WebResult("400", "请输入正确的手机号", "");
        }
        if(StringUtils.isBlank(bankCardVo.getSecurityCode())) {
            return new WebResult("400", "验证码不能为空", "");
        }
        Object value = redisUtil.get("sendSms-" + bankCardVo.getPhone());
        if (value == null) {
            return new WebResult("400", "验证码失效", "");
        }
        if (!bankCardVo.getSecurityCode().equals(value.toString())) {
            return new WebResult("400", "验证码错误", "");
        }
        String password = bankCardVo.getPassword();
        if(StringUtils.isBlank(password) || StringUtils.isBlank(bankCardVo.getNextPass())) {
            return new WebResult("400", "两次密码不能为空", "");
        }
        if(!password.equals(bankCardVo.getNextPass())) {
            return new WebResult("400", "两次密码不一致", "");
        }
        if(StringUtils.isBlank(bankCardVo.getCardType())) {
            return new WebResult("400", "银行卡类型不能为空", "");
        }
        if(!SecurityUtils.checkBankCard(bankCardVo.getCardNumber())) {
            return new WebResult("400", "请输入正确的银行卡号", "");
        }
        if(StringUtils.isBlank(bankCardVo.getUserName())) {
            return new WebResult("400", "持卡人姓名不能为空", "");
        }
        if(!SecurityUtils.securityIdCard(bankCardVo.getIdCard())) {
            return new WebResult("400", "请输入正确的身份证号", "");
        }

        bankCardVo.setPassword(MD5Utils.md5Password(password));

        bankCardMapper.createBankCard(bankCardVo);
        return new WebResult("200", "添加成功", "");

    }

    @Override
    public WebResult cardList(int userId) {
        List<BankCard> bankCards = bankCardMapper.selectByUserId(userId);
        return new WebResult("200", "查询成功", bankCards);
    }

    @Override
    @Transactional
    public WebResult deleteCard(Integer cardId) {
        bankCardMapper.deleteBankCard(cardId);
        return new WebResult("200", "删除成功", "");
    }

    @Override
    public WebResult findCard(Integer cardId) {
        BankCard bankCard = bankCardMapper.selectById(cardId);
        return new WebResult("200", "查询成功", bankCard);
    }
}
