package com.ovft.configure.sys.bean;
/*
     支付宝配置信息
 */
public class AliConfigPojo {
    private String configId;    //主键id
    private String appid;       //微信支付分配的 appid
    private String tradeType;   //交易类型
    private String body;        //商品简单描述
    private String mchId;       //微信支付分配的商户号
    private String notifyUrl;       //异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
   // private String spbillCreateIp;     //调用微信支付API的机器IP
  //  private String nonceStr;          //随机字符串，长度要求在32位以内。
   // private String sign;               //通过签名算法计算得出的签名值
    private String totalFee;           //订单总金额，单位为分
    private String outTradeNo;          //商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一
    private String rsaPrivateKey;
    private String alipayPublicKey;
    private Integer schoolId;
    private Integer returnUrl;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getRsaPrivateKey() {
        return rsaPrivateKey;
    }

    public void setRsaPrivateKey(String rsaPrivateKey) {
        this.rsaPrivateKey = rsaPrivateKey;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey) {
        this.alipayPublicKey = alipayPublicKey;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }



    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }


}