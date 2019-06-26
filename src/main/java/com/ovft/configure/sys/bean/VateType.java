package com.ovft.configure.sys.bean;

public class VateType {
    private int vid;
    private String typeName;
    private  String image;

    public void setVid(int vid) {
        this.vid = vid;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getVid() {
        return vid;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "VateType{" +
                "vid=" + vid +
                ", typeName='" + typeName + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}

