package com.ovft.configure.config;

import com.ovft.configure.WXmodel.IWXPayDomain;
import com.ovft.configure.WXmodel.IWXPayDomainImpl;
import com.ovft.configure.sys.bean.WxConfigPojo;

import java.io.InputStream;

public class WXPayConfigImpl extends WXPayConfig {

    private String appID;

    private String tradeType;

    private String body;

    private String mchId;

    private String key;

    private Integer schoolId;

    private Integer type;



    /**
     *
     *   无参构造方法
     */
       public WXPayConfigImpl(){
        super();
    }


    /**
     *
     * @param schoolId 学校id
     * @param type  支付类型 0：微信 1：支付宝支付
     */
      public WXPayConfigImpl(Integer schoolId,Integer type,WxConfigPojo wxConfigPojo){
        this.appID = wxConfigPojo.getAppid();
        this.tradeType=wxConfigPojo.getTradeType();
        this.body=wxConfigPojo.getBody();
        this.mchId=wxConfigPojo.getMchId();
        this.schoolId=schoolId;
        this.type=type;
        this.key=wxConfigPojo.getWxkey();
    }




    @Override
    public String getAppID() {
        return "wx26bd08f9eb684cfb";
    }   //wxe20934f3b78626e3

    @Override
    public String getMchID() {
        return "1555054481";
    }       //1554106691

    @Override
    public String getIp() {
        return "192.168.31.232";
    }

    @Override
    public String getDomain() {
        return "api.mch.weixin.qq.com";
    }

    @Override
    public String getKey() {
        return "f046a2ee1d4b494e820d5b842054dee5";
    }    //399751baa9c647d78b90e5903a030058

    @Override
    public InputStream getCertStream() {
        return null;
    }

    @Override
    public IWXPayDomain getWXPayDomain() {
        return new IWXPayDomainImpl();
    }
}
