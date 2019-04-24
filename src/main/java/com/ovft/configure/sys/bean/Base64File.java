package com.ovft.configure.sys.bean;

/**
 * @author vvtxw
 * @create 2019-04-24 10:42
 */
public class Base64File {
    private file File;
    private String content;

    public file getFile() {
        return File;
    }

    public void setFile(file file) {
        File = file;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
