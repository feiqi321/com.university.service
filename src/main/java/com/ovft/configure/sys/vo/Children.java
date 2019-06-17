package com.ovft.configure.sys.vo;

import java.io.Serializable;
import java.util.Objects;

public class Children implements Serializable {
    private String legend;
    private String path;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Children children = (Children) o;
        return Objects.equals(legend, children.legend);
    }

    @Override
    public int hashCode() {
        return Objects.hash(legend);
    }
}
