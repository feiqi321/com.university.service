package com.ovft.configure.sys.bean;

/**
 * @author vvtxw
 * @create 2019-04-24 10:42
 */
public class Base64File {
    private file File;
    private String content;
    private String filePrefix;

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

    public String getFilePrefix() {
        return filePrefix;
    }

    public void setFilePrefix(String filePrefix) {
        this.filePrefix = filePrefix;
    }
}
