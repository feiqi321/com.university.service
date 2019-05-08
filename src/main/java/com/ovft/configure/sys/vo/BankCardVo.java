package com.ovft.configure.sys.vo;

import com.ovft.configure.sys.bean.BankCard;

public class BankCardVo extends BankCard {
    private String securityCode;

    private String nextPass;

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getNextPass() {
        return nextPass;
    }

    public void setNextPass(String nextPass) {
        this.nextPass = nextPass;
    }
}
