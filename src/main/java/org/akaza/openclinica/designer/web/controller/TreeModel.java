package org.akaza.openclinica.designer.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TreeModel implements TreeModelInterface {

    Data data;
    String state;
    String oid;
    String name;
    String idPrefix;
    List<TreeModel> children;
    HashMap<String, String> attr;

    public TreeModel(String data, String state, String type, String idPrefix) {
        this.data = new Data();
        this.data.setTitle(data);
        this.idPrefix = idPrefix;
        this.state = state;
        this.idPrefix = idPrefix;
        children = new ArrayList<TreeModel>();
        attr = new HashMap<String, String>();
        addAttr("ocType", type);
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        addAttr("id", this.idPrefix + oid);
        this.oid = oid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TreeModel> getChildren() {
        return children;
    }

    public void setChildren(List<TreeModel> children) {
        this.children = children;
    }

    public HashMap<String, String> getAttr() {
        return attr;
    }

    public void setAttr(HashMap<String, String> attr) {
        this.attr = attr;
    }

    public void addAttr(String key, String value) {
        this.attr.put(key, value);
    }

}
