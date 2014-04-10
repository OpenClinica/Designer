package org.akaza.openclinica.designer.web.controller;

import org.cdisc.ns.odm.v130.ODMcomplexTypeDefinitionItemDef;

import java.util.ArrayList;
import java.util.List;

public class UICrfVersion {

    String oid;
    String name;

    List<ODMcomplexTypeDefinitionItemDef> ungroupedItemDefs;
    List<UIGroup> groups;

    public UICrfVersion(String oid, String name) {
        this.oid = oid;
        this.name = name;
    }

    public void addUngroupedItemDefIfNotExist(ODMcomplexTypeDefinitionItemDef itemDef) {
        if (this.ungroupedItemDefs == null) {
            this.ungroupedItemDefs = new ArrayList<ODMcomplexTypeDefinitionItemDef>();
        }
        if (!this.ungroupedItemDefs.contains(itemDef)) {
            this.ungroupedItemDefs.add(itemDef);
        }
    }

    public void addGroupIfNotExist(UIGroup group) {
        if (this.groups == null) {
            this.groups = new ArrayList<UIGroup>();
        }
        if (!this.groups.contains(group)) {
            this.groups.add(group);
        }
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ODMcomplexTypeDefinitionItemDef> getUngroupedItemDefs() {
        return ungroupedItemDefs;
    }

    public void setUngroupedItemDefs(List<ODMcomplexTypeDefinitionItemDef> ungroupedItemDefs) {
        this.ungroupedItemDefs = ungroupedItemDefs;
    }

    public List<UIGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<UIGroup> groups) {
        this.groups = groups;
    }

}
