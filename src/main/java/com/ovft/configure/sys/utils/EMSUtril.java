package com.ovft.configure.sys.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.Key;

/**
 * @author vvtxw
 * @create 2019-05-21 16:29
 */
public class EMSUtril {


    public String getEmsMethod(String types, String postid) throws IOException {
        String content = null;
        try {
            URL url = new URL("http://poll.kuaidi100.com/poll/query.do" + types + "&postid=" + postid);
            URLConnection con = url.openConnection();
            con.setAllowUserInteraction(false);
            InputStream urlStream = url.openStream();
            String type = con.guessContentTypeFromStream(urlStream);
            String charSet = null;
            if (type == null)
                type = con.getContentType();

            if (type == null || type.trim().length() == 0 || type.trim().indexOf("text/html") < 0)
                return null;

            if (type.indexOf("charset=") > 0)
                charSet = type.substring(type.indexOf("charset=") + 8);

            byte b[] = new byte[10000];
            int numRead = urlStream.read(b);
            content = new String(b, 0, numRead);
            while (numRead != -1) {
                numRead = urlStream.read(b);
                if (numRead != -1) {
                    //String newContent = new String(b, 0, numRead);
                    String newContent = new String(b, 0, numRead, charSet);
                    content += newContent;
                }
            }
            urlStream.close();
            return content;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }


    public static void main(String[] args) throws IOException {
     /*   EMSUtril util = new EMSUtril();
        String type = "shunfeng";
        String postId = "294060323023";
        String info = util.getEmsMethod(type, postId)*/
        ;
        String customer = "9D743BCB9EE6518A4570F7C70232BEF2";
        String param = "{\"com\":\"shunfeng\",\"num\":\"294060323023\",\"mobiletelephone\":\"15897763960\"}";
        String key = "jmeQKaLK9775";

        String s = MD5.encode(param + key + customer);

        System.out.println(s.toUpperCase());
        System.out.println(s.length());

    }
}

