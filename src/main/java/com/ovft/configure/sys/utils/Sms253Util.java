package com.ovft.configure.sys.utils;

import com.alibaba.fastjson.JSON;
import com.ovft.configure.SMSmodel.request.SmsSendRequest;
import com.ovft.configure.SMSmodel.response.SmsSendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

//import java.io.PrintWriter;

/**
 *
 * @author tianyh
 * @Description:HTTP 请求
 */

public class Sms253Util {

    private static final Logger logger = LoggerFactory.getLogger(Sms253Util.class);

    private static final String charset = "utf-8";
    // 用户平台API账号(非登录账号,示例:N1234567)
    private static final String account = "N4066451";
    // 用户平台API密码(非登录密码)
    private static final String password = "goXkQq2taY4c63";
    //请求地址请登录253云通讯自助通平台查看或者询问您的商务负责人获取
    private static final String smsSingleRequestServerUrl = "http://smssh1.253.com/msg/send/json";

    public  static boolean sendSms(String phone, String securityCode){
        try {

            // 短信内容
            String msg = "【咱爸咱妈大学】您的验证码为" + securityCode + "请在5分钟内输入。感谢您的支持，祝您生活愉快！";

            //状态报告
            String report= "true";

            SmsSendRequest smsSingleRequest = new SmsSendRequest(account, password, msg, phone,report);

            String requestJson = JSON.toJSONString(smsSingleRequest);

            System.out.println("before request string is: " + requestJson);

            String response = Sms253Util.sendSmsByPost(smsSingleRequestServerUrl, requestJson);

            System.out.println("response after request result is :" + response);

            SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);

            System.out.println("response  toString is :" + smsSingleResponse);
            logger.info("[短信发送]：" + smsSingleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[短信发送失败]：" + e);
            return false;
        }
        return true;
    }

    /**
     *
     * @author tianyh
     * @Description
     * @param path
     * @param postContent
     * @return String
     * @throws
     */
    public static String sendSmsByPost(String path, String postContent) {
        URL url = null;
        try {
            url = new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");// 提交模式
            httpURLConnection.setConnectTimeout(10000);//连接超时 单位毫秒
            httpURLConnection.setReadTimeout(10000);//读取超时 单位毫秒
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");

//			PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
//			printWriter.write(postContent);
//			printWriter.flush();

            httpURLConnection.connect();
            OutputStream os=httpURLConnection.getOutputStream();
            os.write(postContent.getBytes("UTF-8"));
            os.flush();

            StringBuilder sb = new StringBuilder();
            int httpRspCode = httpURLConnection.getResponseCode();
            if (httpRspCode == HttpURLConnection.HTTP_OK) {
                // 开始获取数据
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                return sb.toString();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
