package com.ovft.configure.config;


import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

import javax.servlet.http.HttpServletResponse;

/**
 * @author vvtxw
 * @create 2019-04-26 17:57
 */
public class CORSInterceptor implements Interceptor {

    @Override
    public void intercept(Invocation inv) {
        inv.invoke();
        HttpServletResponse response = inv.getController().getResponse();
        response.addHeader("Access-Control-Allow-Origin", "*");
    }
}
