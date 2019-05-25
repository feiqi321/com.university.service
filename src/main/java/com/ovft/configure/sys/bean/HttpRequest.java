package com.ovft.configure.sys.bean;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author vvtxw
 * @create 2019-05-21 18:18
 */
public class HttpRequest {

    public static String addUrl(String head, String tail) {
        if (head.endsWith("/")) {
            if (tail.startsWith("/")) {
                return head.substring(0, head.length() - 1) + tail;
            } else {
                return head + tail;
            }
        } else {
            if (tail.startsWith("/")) {
                return head + tail;
            } else {
                return head + "/" + tail;
            }
        }
    }


    public synchronized static String postData(String url, Map<String, String> params, String codePage) throws Exception {


        final HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(25 * 1000);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(30 * 1000);
        final PostMethod method = new PostMethod(url);
        if (params != null) {
            method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, codePage);
            method.setRequestBody(assembleRequestParams(params));
        }
        String result = "";
        try {
            httpClient.executeMethod(method);
            result = new String(method.getResponseBody(), codePage);
        } catch (final Exception e) {
            throw e;
        } finally {
            method.releaseConnection();
        }
        return result;
    }


    private synchronized static NameValuePair[] assembleRequestParams(Map<String, String> data) {
        final List<NameValuePair> nameValueList = new ArrayList<NameValuePair>();
        Iterator<Map.Entry<String, String>> it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
            nameValueList.add(new NameValuePair((String) entry.getKey(), (String) entry.getValue()));
        }
        return nameValueList.toArray(new NameValuePair[nameValueList.size()]);


    }


}
