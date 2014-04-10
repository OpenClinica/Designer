package org.akaza.openclinica.designer.web.controller;

import org.cdisc.ns.odm.v130.ODMcomplexTypeDefinitionStudyEventDef;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

import java.util.ArrayList;
import java.util.List;

public class UIEvent {

    String oid;
    String name;
    ODMcomplexTypeDefinitionStudyEventDef studyEventDef;
    List<UICrf> crfs;

    public UIEvent(String oid, String name) {
        this.oid = oid;
        this.name = name;
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

    public ODMcomplexTypeDefinitionStudyEventDef getStudyEventDef() {
        return studyEventDef;
    }

    public void setStudyEventDef(ODMcomplexTypeDefinitionStudyEventDef studyEventDef) {
        this.studyEventDef = studyEventDef;
    }

    public List<UICrf> getCrfs() {
        if (this.crfs == null) {
            this.crfs = new ArrayList<UICrf>();
        }
        return crfs;
    }

    public void setCrfs(List<UICrf> crfs) {
        this.crfs = crfs;
    }

    public void addCrfIfNotExist(UICrf crf) {
        if (this.crfs == null) {
            this.crfs = new ArrayList<UICrf>();
        }
        if (!this.crfs.contains(crf)) {
            this.crfs.add(crf);
        }
    }

    public UICrf getCrfByOID(String oid) {
        List<UICrf> crfs = new ArrayList<UICrf>();
        crfs.addAll(Collections2.filter(getCrfs(), crfOIDEqualTo(oid)));
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

}
