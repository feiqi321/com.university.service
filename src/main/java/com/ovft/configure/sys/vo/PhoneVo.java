package com.ovft.configure.sys.vo;

/**
 * 课程的扩展类
 *
 * @author vvtxw
 * @create 2019-04-14 7:53
 */
public class PhoneVo {
    private Integer userId;
    private String oldPhone;
    private String newPhone;
    private String securityCode;
    private String oldPass;
    private String newPass;
    private String nextPass;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNextPass() {
        return nextPass;
    }

    public void setNextPass(String nextPass) {
        this.nextPass = nextPass;
    }

    public String getOldPhone() {
        return oldPhone;
    }

    public void setOldPhone(String oldPhone) {
        this.oldPhone = oldPhone;
    }

    public String getNewPhone() {
        return newPhone;
    }

    public void setNewPhone(String newPhone) {
        this.newPhone = newPhone;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }
}
