package com.ovft.configure.config;

public class AlipayConfig {
    // 商户appid
    public static String APPID = "2016092700607568";
    // 私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC2oM4dCz7iLNlzpZAxpmURPaQdhDH9qvUI+bpfBwcMjtn2jtBzYhALdiPhamv3hDVuG2biG9C3HPWEm3Jyam31mvOwkLtNbZv7TZ9i/l1IUSgU4dpB7q358wUAdm7zH2t+foIBQp4pdk/AV+f/DPl95HOa1Bb1JDmVZGYRng0yvjPFMc8aZsOO3q6BttCzEG8QMx/VV/lkiGbwgqLL1RadZv/eE4Y3ZE9MIdE6HYugx3jVJSd2Komf0TtqVTtRJ06oNnFy7kpWHazkbiy1roNld3C2zd/P60q2+RaHlXRKFvHgGXtyRrfWGlcbp5LCm4HxjehK07eBLSadH/crIc1xAgMBAAECggEBAK5zv0/ZLTGKXBHvhmUXKJYmhr23SLWi97yiKJibsu0ZDDZMx+xgMSlxqeaq7QWcYhmSzV2R6nbwj8sjXljTN8SURtJT/seAu657wJQknOD2vU3tTLsMj14i+ZUujN9rFRjZKcCgbUr3/Eik8DUfpLfQYWn1zRRQjT0akbGkg8ECdp5FvuF8trcncNcxlegQoUzouQ1MqxBCbFm5MnDVDn4psrDseoClVNgU3ROgE4eyRC8kENSwl/2ng/8Ul1a6TgPMvhrADUk9Os+7X7JkBWs3iaMGxJAt50gGaqWPDyXzgEUfNL9jXcRGHh93K2xmNZ+RvOzuLr+SVc1kjHskWQ0CgYEA7fPwfLvxzzE1liJWigfsDVSUGjfa/In5D+oP3HS3QJbLsF0n0xasNkO/jDegPTZtJ1rbhNm1MvHJE/nzC3/Ufw1UBXXFLhZ5ahcRZ0qVATZfzvLY61ATNMOTnnDiXZj2vgMHk7xnVpAFYrJzSFe3YefAzLhH/e6+VrP/o2NyyWcCgYEAxHqwKA1ms+D2Q3/Y3z2CzBaZVXyN8cOTh2iiIstV3JaSiilYKmYWAGbE8E6OqiJoPRU3aNOqbTsnfdJ7haqTOd49Wuj+7OXaBKZRPvGobdGGrz7VWdG4sMuF8Xlxs9q7OsrUc8X72lVt6CQKmNfYzUjp0xZqdLr1BB2CHk1K82cCgYAYgg1862ambm0Uf4jmQpafcNyoLqSRaiyK5RKpJCRTtLRZ/95AMY+fLy8lY/1PELIBYaxGbJ3rF1gchJvxzUK3q0nCHjUm4A21TZbvSpE6RL1tdTm+UssHnjUwFnRaK40OWQWIw9jo44CKf9K72rz6ioIjE7/meZaFG5jy3SVPdQKBgE8d8E1QiCiqz7+Cfbv3MvInr3ri+u19iayfD5rfMiXvY/bKizwXe4iR2X/CwmP0EvF116gYg6oUheR+m0rJ0xscSKyQ4gZW0CHLLzAfc2k1U/zBWnwenTZj3f3Mn2KDE52rCz3LXY0flXBDAxlk4WJYl/9uKVPz7LHJiBnXmfTFAoGBAJSdk6Uc2oZlnLrODhaAG1C8XekUOhGkBUFyb1zEPkxpPD23GJe3G+1p8iulhT0V1L0brD/PtNdxZVFC8b2L717FVuMUNp8kHZARRGQ3fswPMiiJPmwkUUQtjosrdy88QkUUwlSrpCP0kuFvshhvHZXjPKeQIJuJnGoHKXq24UKv";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://120.27.16.130/alipay/callback/notify";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 商户可以自定义同步跳转地址
    public static String return_url = "http://120.27.16.130/alipay/callback/return";
    public static String return_order_url = "http://127.0.0.1/#/registerOnline";
    // 请求网关地址
    public static String URL = "https://openapi.alipaydev.com/gateway.do";
    // 编码
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyozTFWH8lhnRQGyB0OWsL1AkDRKdNX+WxTqVB0BGipSVnjX0dpcH2GKDDUEvKYH7+PuRrxVwjoIJ2FJYtSrnIa34NogZHrbwakZ46m/Znj0XYIG4FF0eYrQFnBpkexBCsTxfYlAwQTAkzTXNOLRPwM8QDtaYiorf48TBUPLpSMbC99pzx5hvOg082y8jT05LS2S3MA7How5nkEn2TOcKA/kwu4a/Hq9mJ0KPGbR225VuvKCurvFdsdVi9Ha0M2k9gVvf9NCAOq+Dm74xxKYmc18erN1gm4uKw15a2RjlsrsXhyoOj+c+0hr/4qBYk+6nw+Wzg4qT3/scYoFzKw0rWQIDAQAB";
    // 日志记录目录
    public static String log_path = "/log";
    // RSA2
    public static String SIGNTYPE = "RSA2";
}
