package com.ovft.configure.http.result;


import java.io.Serializable;

/**
 * Created by looyer on 2018/6/8.
 */
public class WebResult implements Serializable {
    
    private String code;
    private String msg;
    private Object data;

    public WebResult(){

    }



    public WebResult(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public WebResult(String code,String msg,Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public WebResult(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public Object getData() {
        return data;
    }
    
    public void setData(Object data) {
        this.data = data;
    }
    
    @Override
    public String toString() {
        return "ResultBody{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
    
    
}
