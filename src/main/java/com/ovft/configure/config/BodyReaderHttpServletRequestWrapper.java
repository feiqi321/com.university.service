package com.ovft.configure.config;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Enumeration;


public class BodyReaderHttpServletRequestWrapper extends
        HttpServletRequestWrapper {

    private final byte[] body;

    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        System.out.println("-------------------------------------------------");
        Enumeration e = request.getHeaderNames()   ;
        while(e.hasMoreElements()){
            String name = (String) e.nextElement();
            String value = request.getHeader(name);
            System.out.println(name+" = "+value);

        }
        body = HttpHelper.getBodyString(request).getBytes(Charset.forName("UTF-8"));
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
       /* ------》从缓存的数据中读取数据《------*/
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);

        return new ServletInputStream() {
            public int read() throws IOException {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean isReady() {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {
                // TODO Auto-generated method stub

            }
        };
    }

    @Override
    public String getHeader(String name) {
        return super.getHeader(name);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        return super.getHeaderNames();
    }


}
