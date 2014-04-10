package org.akaza.openclinica.designer.web.controller;

import org.cdisc.ns.odm.v130.ODMcomplexTypeDefinitionCodeListItem;
import org.openclinica.ns.odm_ext_v130.v31.OCodmComplexTypeDefinitionMultiSelectListItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UIItemDetail {
    String crfName;
    String itemName;
    String oid;
    String description;
    String dataType;
    String units;
    String phi;
    List<UIItemDetailPerCrfVersion> itemDetailsPerCrfVersion;

    public String getCrfName() {
        return crfName;
    }

    public void setCrfName(String crfName) {
        this.crfName = crfName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getPhi() {
        return phi;
    }

    public void setPhi(String phi) {
        this.phi = phi;
    }

    public List<UIItemDetailPerCrfVersion> getItemDetailsPerCrfVersion() {
        if (itemDetailsPerCrfVersion == null) {
            itemDetailsPerCrfVersion = new ArrayList<UIItemDetailPerCrfVersion>();
        }
        return itemDetailsPerCrfVersion;
    }

    public void setItemDetailsPerCrfVersion(List<UIItemDetailPerCrfVersion> itemDetailsPerCrfVersion) {
        this.itemDetailsPerCrfVersion = itemDetailsPerCrfVersion;
    }

    public Map<String, String> getOptionsForFirstVersion() {
        LinkedHashMap<String, String> options = new LinkedHashMap<String, String>();
        for (ODMcomplexTypeDefinitionCodeListItem codeListItem : getItemDetailsPerCrfVersion().get(0).getCodeList().getCodeListItem()) {
            options.put(codeListItem.getDecode().getTranslatedText().get(0).getValue(), codeListItem.getCodedValue());
        }
        return options;
    }

    public Map<String, String> getMultiSelectListForFirstVersion() {
        LinkedHashMap<String, String> options = new LinkedHashMap<String, String>();
        for (OCodmComplexTypeDefinitionMultiSelectListItem multiSelectItem : getItemDetailsPerCrfVersion().get(0).getMultiSelectList().getMultiSelectListItem()) {
            options.put(multiSelectItem.getDecode().getTranslatedText().get(0).getValue(), multiSelectItem.getCodedOptionValue());
        }
        return options;
    }

}
