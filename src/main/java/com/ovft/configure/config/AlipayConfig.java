package com.ovft.configure.config;

import com.ovft.configure.sys.bean.AliConfigPojo;
import com.ovft.configure.sys.bean.WxConfigPojo;

public class AlipayConfig {

    // 商户appid
    public  String APPID = "2019070665757804";
    // 私钥 pkcs8格式的 ===
    public  String RSA_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCv3/yapCVkFEtxVnCXT4JZ9XHOPxHXH+p7yk6M5RHal/9hmT/t2Y41MQNP1s9pcrwKkgfrAXX6diXSR3fShFaB0Y/xW7CfYHhtmdhgkhw1Ab3eoVFHiRaigpxqG+ssJ635UoKXySKJfZj3ttRJ8GTQPP/+vsB2boiB26p8yoLKYOMXmHFtwYz+YOh28tt95TDtupllC5sBnnrYJLRja2+S0fQn5b1PyxZRPveM2n9lnYr1ArKlFDo7VpbRMSFT3PbbESpv86KFpH8xy4UeIX0D9lzjgBnbEWwKHGvSxE91fpIDz+RM3yltyaxnY4D6e4WAVlQQ2IuC5JKhC6hXDO3bAgMBAAECggEATmNM2Tf0GmkZPwjB4g7kEGhfMwfVUca94jLHU/D9Riqc/skpPz/KLFcOHNWnWF51BVkVAl+CUAG81b5Y35FwZSgcOf0d+csrod4CHPjCbbW7BLFt4q5tUj1YrcqHxQRifE+UOUbCh/lc5nusns/wfwfRKZ+ndKHLYeWgVUKFjOFm7XLRh4tszQBJrStXSfVpW87mjwwba+VPyDufNbHjksMeUYjF2YDAHUCzvdFM+ORK+l+ONMijpZJEvC9qVEHFLQSo3WZVVne1QczCL9FPt/jDeBSYVe18N3ucZ41bNdpOGdBwO0iU0hNXxOaWYbMgecIeRXtHYJ11v5HNj8inUQKBgQD1diuEucLB8Rci9YwL7wY4svSMBLKdGX1FJAUtwcn8RQ8ss7SCtFScG8DiPDBprS5B5IsLT2mT27AMxDA1ns3mdRd5z/8jSEYLLTiMHO3WvhTOP9adGVxsGtnKogJSguf8TSRDN5+gSWRQkYoxZoddtOFeBVO4ETIdJdZAncW2hwKBgQC3bQAwN/373UTOGVzQzwkmCahAQuTzY8j5tVr4FITH1jZQ2iCDK/pYgIqSes+JKOcBI93SGb9mSd0aHIhE8ki8qLTx+DeARFGJi/9HoZ/ocsjCdoI86gqEzsgtptAFgiGaCr+p4nY0lIXODBh9KpiEKSzZgzSk7NNfr0V8dplPDQKBgGdGfuzrE4xyGZ/13CSxPtbgRJAc8eGzdcd/bkZgQNHJZKcUPSC5Ktg7vR3y4RO1b779n5r0oKE7T4kvinElDdCm92ejIeAAaqgNMLuS+GbftvVIWs3ie8ZoTbY6D+wTc/PtqRI29wyDlJdphNFgKBg/IItkhmLtqT4mS00IlMVFAoGBAJp8boUuE3c21EV1bQvc48qqpY+CB8zKRTGzQevhdDfPPi34mRuBRbeWg599WiAeV/lI2QGkd+TR3yunzkmregGTYdrOAQjCAZ6ET7xn7wNa8aV/JI/jWnqiokx4y4loIZ7Y4LZXqtD8KnnxDAsXfh8JguGrtQIXCKv1NePvC45dAoGBAN7K7rs0n2iu94U2jqoYCZJVRDhypgQc6CzpxXsPAOSWZ7D18ldbZXmAgIQ2lx3FEA4PrQlO0xhG5yRthQGhoWTzkCGOc6DkcmLv/ah2sY32Wa8pOBnMDl502461xzPmU9HDgWOG6CQXjIe9V/KMrzWGogZbdEeEDLkFvUA9APq8";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public  String notify_url = "http://47.105.55.14:8830/pay/alipay/callback/notify";//http://47.105.55.14:8830/alipay/callback/notify
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 商户可以自定义同步跳转地址
    public static String return_url = "https://www.zanbazanma16.com/public/appvuew.html";
    //http://47.105.55.14:8830/pay/alipay/callback/return
   // public static String return_url = "https://www.zanbazanma16.com/#/Record/";

    // public static String return_order_url = "https://www.zanbazanma16.com/public/sucess.jpg";
    // 请求网关地址
    public static String URL = "https://openapi.alipay.com/gateway.do";
    // 编码
    public  static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥 ===
    public   String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoyS3uWAGmT29tlL7tg2ViTft+TVK2/zpH0KvdfdiXsFbI6GpbnQZyI8uTze4AYPm3QosuERaRJ4uP/bvFJMd4Uv6KSxUbWjO/hdp1Jf+inwDT/eev47QxwTEsIAbWF/kDWjBWWsvkUQ61h9fvrwr2R+wgbKkPrssAys2ZvFNsKoS3DJN6EbEeDhLViCh615ytVq5X+EPsaLX0ri/wn6VVjiSeJ5chz6FDP2k/zp53oapXzDAAzaGmdOqAyBgBkQU54FA6Sw6msWtPdZVjWSDwKGqHB/9YjhN8KuhlkIDChmx9YXDZNwIy0rFXcJMoxy4In1RUekXrDXHQU1sBiviZwIDAQAB";
    // 日志记录目录
    public static String log_path = "/log";
    // RSA2
    public static String SIGNTYPE = "RSA2";


              public   AlipayConfig(Integer schoolId,Integer type,AliConfigPojo aliConfigPojo){
                  this.APPID=aliConfigPojo.getAppid();
                  this.notify_url=aliConfigPojo.getNotifyUrl();
                  this.ALIPAY_PUBLIC_KEY=aliConfigPojo.getAlipayPublicKey();
                  this.RSA_PRIVATE_KEY=aliConfigPojo.getRsaPrivateKey();

              }


}
