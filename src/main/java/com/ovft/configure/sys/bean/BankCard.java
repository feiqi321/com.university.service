package com.ovft.configure.sys.bean;

import java.io.Serializable;

/**
 * 我的银行卡
 **/
public class BankCard implements Serializable {

    private Integer cardId;
    private Integer userId;

    /**
     * 银行卡类型
     */
    private String cardType;

    private String phone;
    private String password;

    /**
     * 卡号
     */
    private String cardNumber;

    /**
     * 持卡人姓名
     */
    private String userName;

    /**
     * 身份证号
     */
    private String idCard;

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
