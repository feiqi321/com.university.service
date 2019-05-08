package com.ovft.configure.sys.dao;

import com.ovft.configure.sys.bean.BankCard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName BankCardMapper
 * @Author zqx
 * @Version 1.0
 **/
@Mapper
public interface BankCardMapper {

    public BankCard selectById(@Param("cardId") Integer cardId);

    public List<BankCard> selectByUserId(@Param("userId") Integer userId);

    public void createBankCard(BankCard bankCard);

    public void deleteBankCard(@Param("cardId") Integer cardId);
}
