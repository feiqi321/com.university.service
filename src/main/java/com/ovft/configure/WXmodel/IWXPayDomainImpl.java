package com.ovft.configure.WXmodel;

import com.ovft.configure.config.WXPayConfig;

public class IWXPayDomainImpl implements IWXPayDomain {
//    private static String url;

    @Override
    public DomainInfo getDomain(WXPayConfig config) {
//        IWXPayDomain wxPayDomain = (IWXPayDomainImpl)config.getWXPayDomain();
//
//        IWXPayDomain iwxPayDomain=new IWXPayDomainImpl(((IWXPayDomainImpl) wxPayDomain).getUrl());
        return new DomainInfo(config.getDomain(), true);
    }

//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }

    @Override
    public void report(String domain, long elapsedTimeMillis, Exception ex) {

    }

//    public IWXPayDomainImpl(String url) {
//        this.url = url;
//    }
}
