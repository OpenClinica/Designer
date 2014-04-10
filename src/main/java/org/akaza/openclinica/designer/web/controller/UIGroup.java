package org.akaza.openclinica.designer.web.controller;

import org.cdisc.ns.odm.v130.ODMcomplexTypeDefinitionItemDef;
import org.cdisc.ns.odm.v130.ODMcomplexTypeDefinitionItemGroupDef;

import java.util.ArrayList;
import java.util.List;

public class UIGroup {

    String oid;
    String name;

    ODMcomplexTypeDefinitionItemGroupDef itemGroupDef;
    List<ODMcomplexTypeDefinitionItemDef> items;

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

    public ODMcomplexTypeDefinitionItemGroupDef getItemGroupDef() {
        return itemGroupDef;
    }

    public void setItemGroupDef(ODMcomplexTypeDefinitionItemGroupDef itemGroupDef) {
        this.itemGroupDef = itemGroupDef;
    }

    public List<ODMcomplexTypeDefinitionItemDef> getItems() {
        if (this.items == null) {
            this.items = new ArrayList<ODMcomplexTypeDefinitionItemDef>();
        }
        return items;
    }

    public void setItems(List<ODMcomplexTypeDefinitionItemDef> items) {
        this.items = items;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((itemGroupDef == null) ? 0 : itemGroupDef.hashCode());
        result = prime * result + ((items == null) ? 0 : items.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((oid == null) ? 0 : oid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UIGroup other = (UIGroup) obj;
        if (itemGroupDef == null) {
            if (other.itemGroupDef != null)
                return false;
        } else if (!itemGroupDef.equals(other.itemGroupDef))
            return false;
        if (items == null) {
            if (other.items != null)
                return false;
        } else if (!items.equals(other.items))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (oid == null) {
            if (other.oid != null)
                return false;
        } else if (!oid.equals(other.oid))
            return false;
        return true;
    }

}
