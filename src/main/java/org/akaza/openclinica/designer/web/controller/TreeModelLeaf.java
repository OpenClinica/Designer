package org.akaza.openclinica.designer.web.controller;

import java.util.HashMap;

public class TreeModelLeaf implements TreeModelInterface {

    private Data data;
    private String oid;
    private String name;
    private Boolean hidden;
    private final String idPrefix;
    private HashMap<String, String> attr;

    public TreeModelLeaf(String data, String state, String type, String idPrefix, String oid) {
        this.data = new Data();
        this.data.setTitle(data);
        this.idPrefix = idPrefix;
        attr = new HashMap<String, String>();
        addAttr("ocType", type);
        setOid(oid);
        setElementId(getOid());
        addItemDetailLinkDataAttrs(getOid());
        addAttr("oid", getOid());
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public void setElementId(String oid) {
        addAttr("id", this.idPrefix + oid);
    }

    /**
     * A Jquery onclick event handler uses the OID below to show leaf metadata.
     * @param oid
     */
    public void addItemDetailLinkDataAttrs(String oid) {
        // There is a Jquery event handler that
        this.data.addAttr("href", "#");
        this.data.addAttr("oid", oid);
    }

    /**
     * When item is hidden show item in grey color
     */
    public void addStyleToItemBasedonParameter(Boolean isItemHidden) {
        // this.data.addAttr("style", "color:grey;");
        if (isItemHidden) {
            this.data.addAttr("class", "textcolor");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
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
