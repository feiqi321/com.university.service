package com.ovft.configure.sys.vo;

import java.util.List;

public class PermissionMenu {
    private Integer id;
    private String label;
    private List<PermissionMenu> children;

    public PermissionMenu(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<PermissionMenu> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionMenu> children) {
        this.children = children;
    }
}
