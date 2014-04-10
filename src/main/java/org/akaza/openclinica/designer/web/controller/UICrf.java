package org.akaza.openclinica.designer.web.controller;

import org.cdisc.ns.odm.v130.ODMcomplexTypeDefinitionFormDef;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kkrikor
 * 
 */
public class UICrf {

    String oid;
    String name;
    ODMcomplexTypeDefinitionFormDef form;

    UIGroup ungroupedGroup;
    List<UIGroup> groups;
    List<UICrf> crfVersions;

    public UICrf(String oid, String name) {
        this.oid = oid;
        this.name = name;
    }

    public UIGroup getGroupByOID(String oid) {
        List<UIGroup> groups = new ArrayList<UIGroup>();
        groups.addAll(Collections2.filter(getGroups(), groupOIDEqualTo(oid)));
        return groups.size() > 0 ? groups.get(0) : null;

    }

    private Predicate<UIGroup> groupOIDEqualTo(final String value) {
        return new Predicate<UIGroup>() {
            @Override
            public boolean apply(UIGroup group) {
                return group.getOid().equals(value);
            }
        };
    }

    public UICrf getCrfByOID(String oid) {
        List<UICrf> crfs = new ArrayList<UICrf>();
        crfs.addAll(Collections2.filter(getCrfVersions(), crfOIDEqualTo(oid)));
        return crfs.size() > 0 ? crfs.get(0) : null;

    }

    private Predicate<UICrf> crfOIDEqualTo(final String value) {
        return new Predicate<UICrf>() {
            @Override
            public boolean apply(UICrf crf) {
                return crf.getOid().equals(value);
            }
        };
    }

    public void addGroupIfNotExist(UIGroup group) {
        if (this.groups == null) {
            this.groups = new ArrayList<UIGroup>();
        }
        if (!this.groups.contains(group)) {
            this.groups.add(group);
        }
    }

    public List<UIGroup> getGroups() {
        if (this.groups == null) {
            this.groups = new ArrayList<UIGroup>();
        }
        return groups;
    }

    public void setGroups(List<UIGroup> groups) {
        this.groups = groups;
    }

    public List<UICrf> getCrfVersions() {
        if (this.crfVersions == null) {
            this.crfVersions = new ArrayList<UICrf>();
        }
        return crfVersions;
    }

    public void setCrfVersions(List<UICrf> crfVersions) {
        this.crfVersions = crfVersions;
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

    public ODMcomplexTypeDefinitionFormDef getForm() {
        return form;
    }

    public void setForm(ODMcomplexTypeDefinitionFormDef form) {
        this.form = form;
    }

    public UIGroup getUngroupedGroup() {
        return ungroupedGroup;
    }

    public void setUngroupedGroup(UIGroup ungroupedGroup) {
        this.ungroupedGroup = ungroupedGroup;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        UICrf other = (UICrf) obj;
        if (oid == null) {
            if (other.oid != null)
                return false;
        } else if (!oid.equals(other.oid))
            return false;
        return true;
    }
}
