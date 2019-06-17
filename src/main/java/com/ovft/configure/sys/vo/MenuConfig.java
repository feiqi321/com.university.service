package com.ovft.configure.sys.vo;

import java.io.Serializable;
import java.util.HashSet;

public class MenuConfig  implements Serializable {
    private String legend;
    private String path;
    private String icon;
    private HashSet<Children> children;

    public String getLegend() {
        return legend;
    }

    public void setLegend(String legend) {
        this.legend = legend;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public HashSet<Children> getChildren() {
        return children;
    }

    public void setChildren(HashSet<Children> children) {
        this.children = children;
    }

}
