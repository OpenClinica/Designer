package org.akaza.openclinica.designer.web.controller;

import java.util.HashMap;

public class Data {
    String title;
    String icon;
    HashMap<String, String> attr;

    public Data() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public HashMap<String, String> getAttr() {
        if (this.attr == null) {
            this.attr = new HashMap<String, String>();
        }
        return attr;
    }

    public void setAttr(HashMap<String, String> attr) {
        this.attr = attr;
    }

    public void addAttr(String key, String value) {
        if (this.attr == null) {
            this.attr = new HashMap<String, String>();
        }
        this.attr.put(key, value);
    }
}
