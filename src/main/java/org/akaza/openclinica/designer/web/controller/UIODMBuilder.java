package org.akaza.openclinica.designer.web.controller;

import org.cdisc.ns.odm.v130.ODM;
import org.cdisc.ns.odm.v130.ODMcomplexTypeDefinitionCheckValue;
import org.cdisc.ns.odm.v130.ODMcomplexTypeDefinitionCodeList;
import org.cdisc.ns.odm.v130.ODMcomplexTypeDefinitionFormDef;
import org.cdisc.ns.odm.v130.ODMcomplexTypeDefinitionFormRef;
import org.cdisc.ns.odm.v130.ODMcomplexTypeDefinitionItemDef;
import org.cdisc.ns.odm.v130.ODMcomplexTypeDefinitionItemGroupDef;
import org.cdisc.ns.odm.v130.ODMcomplexTypeDefinitionItemGroupRef;
import org.cdisc.ns.odm.v130.ODMcomplexTypeDefinitionItemRef;
import org.cdisc.ns.odm.v130.ODMcomplexTypeDefinitionRangeCheck;
import org.cdisc.ns.odm.v130.ODMcomplexTypeDefinitionStudyEventDef;
import org.cdisc.ns.odm.v130.ODMcomplexTypeDefinitionTranslatedText;
import org.openclinica.ns.odm_ext_v130.v31.OCodmComplexTypeDefinitionItemPresentInForm;
import org.openclinica.ns.odm_ext_v130.v31.OCodmComplexTypeDefinitionMultiSelectList;
import org.openclinica.ns.odm_ext_v130.v31.OCodmComplexTypeDefinitionMultiSelectListRef;
import org.openclinica.ns.rules.v31.Rules;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

import java.util.ArrayList;
import java.util.List;

public class UIODMBuilder {

    UIODMContainer container = new UIODMContainer();

    public UIODMBuilder(ODM odm) {
        this.container = new UIODMContainer();
        getContainer().setOdm(odm);
        initMultiSelectList();
        initRules();

    }

    public UIODMBuilder(UIODMContainer container) {
        this.container = container;
    }

    /**
     * Iterate over FormDefs and transform data in FormDef to CRF/CRF Version/Item Group/Item relationship using the UICrf
     * object
     */
    void build() {

        for (ODMcomplexTypeDefinitionFormDef formDef : getFormDefs()) {
            UICrf transientCrf = new UICrf(formDef.getFormDefElementExtension().get(0).getParentFormOID(), crfNameFromFormDefName(formDef.getName()));
            UICrf persistentCrf = getContainer().addCrfIfNotExist(transientCrf);
            UICrf crfVersion = createCrfVersion(formDef);
            persistentCrf.getCrfVersions().add(crfVersion);

            for (ODMcomplexTypeDefinitionItemGroupRef itemGroupRef : formDef.getItemGroupRef()) {
                // If the group is ungrouped
                if (itemGroupRef.getItemGroupOID().contains("UNGROUPED")) {
                    for (ODMcomplexTypeDefinitionItemRef itemRef : getItemGroupDefFromItemGroupRef(itemGroupRef).getItemRef()) {
                        upsertUIUnGrouped(itemGroupRef, persistentCrf, false);
                        upsertUIUnGrouped(itemGroupRef, crfVersion, true);
                    }
                } else {
                    upsertUIGroup(itemGroupRef, persistentCrf, false);
                    upsertUIGroup(itemGroupRef, crfVersion, true);
                }
            }

        }

        for (ODMcomplexTypeDefinitionStudyEventDef studyEventDef : getStudyEventDefs()) {
            UIEvent studyEvent = new UIEvent(studyEventDef.getOID(), studyEventDef.getName());
            studyEvent.setStudyEventDef(studyEventDef);
            for (ODMcomplexTypeDefinitionFormRef formRef : studyEventDef.getFormRef()) {
                studyEvent.addCrfIfNotExist(getCrfByCrfVersionOID(formRef.getFormOID(), getContainer().getCrfs()));
            }
            getContainer().addEventIfNotExist(studyEvent);
        }
    }

    private String oidCleaner(String oid) {
        Integer lastIndexOfDelimiter = oid.lastIndexOf('.');
        if (lastIndexOfDelimiter != -1) {
            return oid.substring(lastIndexOfDelimiter + 1, oid.length());
        }
        return oid;
    }

    UIItemDetail buildItemDetail(String oid) {
        final UIItemDetail itemDetail = new UIItemDetail();
        ODMcomplexTypeDefinitionItemDef itemDef = getItemDefFromItemOid(oidCleaner(oid));
        itemDetail.setItemName(itemDef.getName());
        itemDetail.setOid(itemDef.getOID());
        itemDetail.setDescription(itemDef.getComment() == null ? "" : itemDef.getComment());
        itemDetail.setDataType(itemDef.getDataType().name());
        String validation = "";
        String validationErrorMessage = "";
        ODMcomplexTypeDefinitionCodeList codeList = new ODMcomplexTypeDefinitionCodeList();
        if (itemDef.getCodeListRef() != null) {
            codeList = getCodeListDefFromCodeListOID(itemDef.getCodeListRef().getCodeListOID());
        }
        OCodmComplexTypeDefinitionMultiSelectList multiSelectList = new OCodmComplexTypeDefinitionMultiSelectList();
        for (OCodmComplexTypeDefinitionMultiSelectListRef multiSelectRef : this.container.getMultiSelectRefFromItemDef(itemDef)) {
            multiSelectList = getMultiSelectListDefFromMultiSelectOID(multiSelectRef.getMultiSelectListID());
        }
        if (itemDef.getRangeCheck() != null) {
            for (ODMcomplexTypeDefinitionRangeCheck rangeCheck : itemDef.getRangeCheck()) {
                if (rangeCheck.getCheckValue() != null) {
                    for (ODMcomplexTypeDefinitionCheckValue checkValue : rangeCheck.getCheckValue()) {
                        validation += checkValue.getValue() + "<br/>";
                    }
                }
                if (rangeCheck.getErrorMessage() != null) {
                    for (ODMcomplexTypeDefinitionTranslatedText errorMessage : rangeCheck.getErrorMessage().getTranslatedText()) {
                        validationErrorMessage += errorMessage.getValue() + "<br/>";
                    }
                }
            }
        }

        itemDetail.setUnits("");
        // List<ODMcomplexTypeDefinitionItemGroupDef> itemGroupDefs = getItemGroupByItemOID(itemDef.getOID(),
        // getItemGroupDefs());
        for (OCodmComplexTypeDefinitionItemPresentInForm itemPresentInForm : this.container.getItemDetailsFromItemDef(itemDef).getItemPresentInForm()) {
            ODMcomplexTypeDefinitionFormDef formDef = getFormDefFromFormOid(itemPresentInForm.getFormOID());
            UIItemDetailPerCrfVersion uiItemDetailPerCrfVersion = new UIItemDetailPerCrfVersion();
            itemDetail.getItemDetailsPerCrfVersion().add(uiItemDetailPerCrfVersion);
            itemDetail.setCrfName(crfNameFromFormDefName(formDef.getName()));
            uiItemDetailPerCrfVersion.setLeftItemText(itemPresentInForm.getLeftItemText());
            uiItemDetailPerCrfVersion.setRightItemText(itemPresentInForm.getRightItemText() == null ? "" : itemPresentInForm.getRightItemText());
            uiItemDetailPerCrfVersion.setDefaultValue(itemPresentInForm.getDefaultValue() == null ? "" : itemPresentInForm.getDefaultValue());
            uiItemDetailPerCrfVersion.setResponseLayout(itemPresentInForm.getItemResponse().getResponseLayout() == null ? "" : itemPresentInForm
                    .getItemResponse().getResponseLayout());
            uiItemDetailPerCrfVersion.setResponseType(itemPresentInForm.getItemResponse().getResponseType());
            uiItemDetailPerCrfVersion.setResponseLabel(itemPresentInForm.getItemResponse().getResponseType());
            uiItemDetailPerCrfVersion.setSectionLabel(itemPresentInForm.getSectionLabel());
            uiItemDetailPerCrfVersion.setPhi(itemPresentInForm.getPHI());
            uiItemDetailPerCrfVersion.setCodeList(codeList);
            uiItemDetailPerCrfVersion.setMultiSelectList(multiSelectList);
            uiItemDetailPerCrfVersion.setValidation(validation);
            uiItemDetailPerCrfVersion.setValidationErrorMessage(validationErrorMessage);
            uiItemDetailPerCrfVersion.setRequired("");

            // Get the group by using the form and then searching for the item. Is there a better way ?
            for (ODMcomplexTypeDefinitionItemGroupRef itemGroupRef : formDef.getItemGroupRef()) {
                uiItemDetailPerCrfVersion.setCrfVersionName(formDef.getName());
                ODMcomplexTypeDefinitionItemGroupDef itemGroupDef = getItemGroupDefFromItemGroupRef(itemGroupRef);
                for (ODMcomplexTypeDefinitionItemRef itemRef : itemGroupDef.getItemRef()) {
                    if (itemRef.getItemOID().equals(itemDef.getOID())) {
                        uiItemDetailPerCrfVersion.setGroupName(itemGroupDef.getName());
                        uiItemDetailPerCrfVersion.setRequired(itemRef.getMandatory().value());
                        break;
                    }
                }
            }
        }
        return itemDetail;

    }

    private String crfNameFromFormDefName(String formDefName) {
        return formDefName.substring(0, formDefName.lastIndexOf("-")).trim();
    }

    private UICrf createCrfVersion(ODMcomplexTypeDefinitionFormDef formDef) {
        UICrf crfVersion = new UICrf(formDef.getOID(), formDef.getName());
        crfVersion.setForm(formDef);
        return crfVersion;
    }

    private void upsertUIUnGrouped(ODMcomplexTypeDefinitionItemGroupRef itemGroupRef, UICrf persistentCrf, boolean isCrfVersion) {
        ODMcomplexTypeDefinitionItemGroupDef itemGroupDef = getItemGroupDefFromItemGroupRef(itemGroupRef);
        if (persistentCrf.getUngroupedGroup() == null) {
            UIGroup uiGroup = new UIGroup();
            uiGroup.setOid(itemGroupRef.getItemGroupOID());
            uiGroup.setName(itemGroupDef.getName());
            uiGroup.setItemGroupDef(itemGroupDef);
            persistentCrf.setUngroupedGroup(uiGroup);
        }
        for (ODMcomplexTypeDefinitionItemRef itemRef : getItemGroupDefFromItemGroupRef(itemGroupRef).getItemRef()) {
            ODMcomplexTypeDefinitionItemDef itemDef = getItemDefFromItemRef(itemRef);
            // persistentCrf.addUngroupedItemDefIfNotExist(itemDef);
            if (!isCrfVersion) {
                if (!persistentCrf.getUngroupedGroup().getItems().contains(itemDef))
                    persistentCrf.getUngroupedGroup().getItems().add(itemDef);
            } else {
                for (OCodmComplexTypeDefinitionItemPresentInForm itemPresentInForm : this.container.getItemDetailsFromItemDef(itemDef).getItemPresentInForm()) {
                    if (itemPresentInForm.getFormOID().equals(persistentCrf.getOid())) {
                        if (!persistentCrf.getUngroupedGroup().getItems().contains(itemDef))
                            persistentCrf.getUngroupedGroup().getItems().add(itemDef);
                    }
                }
            }
        }
    }

    private void upsertUIGroup(ODMcomplexTypeDefinitionItemGroupRef itemGroupRef, UICrf persistentCrf, boolean isCrfVersion) {
        ODMcomplexTypeDefinitionItemGroupDef itemGroupDef = getItemGroupDefFromItemGroupRef(itemGroupRef);
        UIGroup uiGroup = new UIGroup();
        uiGroup.setOid(itemGroupRef.getItemGroupOID());
        uiGroup.setName(itemGroupDef.getName());
        uiGroup.setItemGroupDef(itemGroupDef);
        UIGroup persistentUIGroup = doesUIGroupListContainUIGroupWithOid(persistentCrf.getGroups(), uiGroup.getOid());
        if (persistentUIGroup != null) {
            uiGroup = persistentUIGroup;
        } else {
            persistentCrf.getGroups().add(uiGroup);
        }
        for (ODMcomplexTypeDefinitionItemRef itemRef : getItemGroupDefFromItemGroupRef(itemGroupRef).getItemRef()) {
            ODMcomplexTypeDefinitionItemDef itemDef = getItemDefFromItemRef(itemRef);
            if (!isCrfVersion) {
                if (!uiGroup.getItems().contains(itemDef))
                    uiGroup.getItems().add(itemDef);
            } else {
                for (OCodmComplexTypeDefinitionItemPresentInForm itemPresentInForm : this.container.getItemDetailsFromItemDef(itemDef).getItemPresentInForm()) {
                    if (itemPresentInForm.getFormOID().equals(persistentCrf.getOid())) {
                        if (!uiGroup.getItems().contains(itemDef))
                            uiGroup.getItems().add(itemDef);
                    }
                }
            }
        }
    }

    private UIGroup doesUIGroupListContainUIGroupWithOid(List<UIGroup> uiGroups, String oid) {
        for (UIGroup uiGroup : uiGroups) {
            if (uiGroup.getOid().equals(oid)) {
                return uiGroup;
            }
        }
        return null;
    }

    private ODMcomplexTypeDefinitionItemDef getItemDefFromItemRef(ODMcomplexTypeDefinitionItemRef itemRef) {
        return getItemDefFromItemOid(itemRef.getItemOID());

    }

    private OCodmComplexTypeDefinitionMultiSelectList getMultiSelectListDefFromMultiSelectOID(String oid) {
        List<OCodmComplexTypeDefinitionMultiSelectList> localMultiSelectLists = new ArrayList<OCodmComplexTypeDefinitionMultiSelectList>();
        localMultiSelectLists.addAll(Collections2.filter(getMultiSelectLists(), multiSelectListOidEqualTo(oid)));
        assert localMultiSelectLists.size() == 1 : "size should be 1 but was: " + localMultiSelectLists.size();
        return localMultiSelectLists.size() > 0 ? localMultiSelectLists.get(0) : null;

    }

    private Predicate<OCodmComplexTypeDefinitionMultiSelectList> multiSelectListOidEqualTo(final String value) {
        return new Predicate<OCodmComplexTypeDefinitionMultiSelectList>() {
            @Override
            public boolean apply(OCodmComplexTypeDefinitionMultiSelectList customer) {
                return customer.getID().equals(value);
            }
        };
    }

    private ODMcomplexTypeDefinitionCodeList getCodeListDefFromCodeListOID(String oid) {
        List<ODMcomplexTypeDefinitionCodeList> localCodeLists = new ArrayList<ODMcomplexTypeDefinitionCodeList>();
        localCodeLists.addAll(Collections2.filter(getCodeLists(), codeListOidEqualTo(oid)));
        assert localCodeLists.size() == 1 : "size should be 1 but was: " + localCodeLists.size();
        return localCodeLists.size() > 0 ? localCodeLists.get(0) : null;

    }

    private Predicate<ODMcomplexTypeDefinitionCodeList> codeListOidEqualTo(final String value) {
        return new Predicate<ODMcomplexTypeDefinitionCodeList>() {
            @Override
            public boolean apply(ODMcomplexTypeDefinitionCodeList customer) {
                return customer.getOID().equals(value);
            }
        };
    }

    private ODMcomplexTypeDefinitionItemDef getItemDefFromItemOid(String oid) {
        List<ODMcomplexTypeDefinitionItemDef> localItemDefs = new ArrayList<ODMcomplexTypeDefinitionItemDef>();
        localItemDefs.addAll(Collections2.filter(getItemDefs(), itemOidEqualTo(oid)));
        assert localItemDefs.size() == 1 : "size should be 1 but was: " + localItemDefs.size();
        return localItemDefs.size() > 0 ? localItemDefs.get(0) : null;

    }

    private Predicate<ODMcomplexTypeDefinitionItemDef> itemOidEqualTo(final String value) {
        return new Predicate<ODMcomplexTypeDefinitionItemDef>() {
            @Override
            public boolean apply(ODMcomplexTypeDefinitionItemDef customer) {
                return customer.getOID().equals(value);
            }
        };
    }

    private ODMcomplexTypeDefinitionItemGroupDef getItemGroupDefFromItemGroupRef(ODMcomplexTypeDefinitionItemGroupRef itemGroupRef) {
        List<ODMcomplexTypeDefinitionItemGroupDef> localItemGroupDefs = new ArrayList<ODMcomplexTypeDefinitionItemGroupDef>();
        localItemGroupDefs.addAll(Collections2.filter(getItemGroupDefs(), oidEqualTo(itemGroupRef.getItemGroupOID())));
        assert localItemGroupDefs.size() == 1 : "size should be 1 but was: " + localItemGroupDefs.size();
        return localItemGroupDefs.size() > 0 ? localItemGroupDefs.get(0) : null;

    }

    private Predicate<ODMcomplexTypeDefinitionItemGroupDef> oidEqualTo(final String value) {
        return new Predicate<ODMcomplexTypeDefinitionItemGroupDef>() {
            @Override
            public boolean apply(ODMcomplexTypeDefinitionItemGroupDef customer) {
                return customer.getOID().equals(value);
            }
        };
    }

    private ODMcomplexTypeDefinitionFormDef getFormDefFromFormOid(String formOid) {
        List<ODMcomplexTypeDefinitionFormDef> localFormDefs = new ArrayList<ODMcomplexTypeDefinitionFormDef>();
        localFormDefs.addAll(Collections2.filter(getFormDefs(), formDefOidEqualTo(formOid)));
        assert localFormDefs.size() == 1 : "size should be 1 but was: " + localFormDefs.size();
        return localFormDefs.size() > 0 ? localFormDefs.get(0) : null;

    }

    private Predicate<ODMcomplexTypeDefinitionFormDef> formDefOidEqualTo(final String value) {
        return new Predicate<ODMcomplexTypeDefinitionFormDef>() {
            @Override
            public boolean apply(ODMcomplexTypeDefinitionFormDef customer) {
                return customer.getOID().equals(value);
            }
        };
    }

    public ODMcomplexTypeDefinitionStudyEventDef getStudyEventDefByFormOID(String oid) {
        List<ODMcomplexTypeDefinitionStudyEventDef> eventDefs = new ArrayList<ODMcomplexTypeDefinitionStudyEventDef>();
        eventDefs.addAll(Collections2.filter(getStudyEventDefs(), formOidEqualTo(oid)));
        return eventDefs.size() > 0 ? eventDefs.get(0) : null;

    }

    private Predicate<ODMcomplexTypeDefinitionStudyEventDef> formOidEqualTo(final String value) {
        return new Predicate<ODMcomplexTypeDefinitionStudyEventDef>() {
            @Override
            public boolean apply(ODMcomplexTypeDefinitionStudyEventDef eventDef) {
                for (ODMcomplexTypeDefinitionFormRef formRef : eventDef.getFormRef()) {
                    if (formRef.getFormOID().equals(value)) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    public UICrf getCrfByCrfVersionOID(String oid, List<UICrf> crfs) {
        List<UICrf> localCrfs = new ArrayList<UICrf>();
        localCrfs.addAll(Collections2.filter(crfs, crfVersionOidEqualTo(oid)));
        return localCrfs.size() > 0 ? localCrfs.get(0) : null;

    }

    private Predicate<UICrf> crfVersionOidEqualTo(final String value) {
        return new Predicate<UICrf>() {
            @Override
            public boolean apply(UICrf crfs) {
                for (UICrf crfVersion : crfs.getCrfVersions()) {
                    if (crfVersion.getOid().equals(value)) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    private List<ODMcomplexTypeDefinitionItemGroupDef> getItemGroupByItemOID(String oid, List<ODMcomplexTypeDefinitionItemGroupDef> itemGroupDefs) {
        List<ODMcomplexTypeDefinitionItemGroupDef> localItemGroupDefs = new ArrayList<ODMcomplexTypeDefinitionItemGroupDef>();
        localItemGroupDefs.addAll(Collections2.filter(itemGroupDefs, itemOidEqualToInItemGroup(oid)));
        return localItemGroupDefs.size() > 0 ? localItemGroupDefs : null;

    }

    private Predicate<ODMcomplexTypeDefinitionItemGroupDef> itemOidEqualToInItemGroup(final String value) {
        return new Predicate<ODMcomplexTypeDefinitionItemGroupDef>() {
            @Override
            public boolean apply(ODMcomplexTypeDefinitionItemGroupDef itemGroupDef) {
                for (ODMcomplexTypeDefinitionItemRef itemRef : itemGroupDef.getItemRef()) {
                    if (itemRef.getItemOID().equals(value)) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    public List<ODMcomplexTypeDefinitionFormDef> getFormByItemGroupOID(String oid, List<ODMcomplexTypeDefinitionFormDef> formDefs) {
        List<ODMcomplexTypeDefinitionFormDef> localFormDefs = new ArrayList<ODMcomplexTypeDefinitionFormDef>();
        localFormDefs.addAll(Collections2.filter(formDefs, itemGroupOidEqualToInCrf(oid)));
        return localFormDefs.size() > 0 ? localFormDefs : null;

    }

    private Predicate<ODMcomplexTypeDefinitionFormDef> itemGroupOidEqualToInCrf(final String value) {
        return new Predicate<ODMcomplexTypeDefinitionFormDef>() {
            @Override
            public boolean apply(ODMcomplexTypeDefinitionFormDef formDef) {
                for (ODMcomplexTypeDefinitionItemGroupRef itemGroupRef : formDef.getItemGroupRef()) {
                    if (itemGroupRef.getItemGroupOID().equals(value)) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    private List<ODMcomplexTypeDefinitionStudyEventDef> getStudyEventDefs() {
        return getContainer().getOdm().getStudy().get(0).getMetaDataVersion().get(0).getStudyEventDef();
    }

    private List<ODMcomplexTypeDefinitionFormDef> getFormDefs() {
        return getContainer().getOdm().getStudy().get(0).getMetaDataVersion().get(0).getFormDef();
    }

    private List<ODMcomplexTypeDefinitionItemGroupDef> getItemGroupDefs() {
        return getContainer().getOdm().getStudy().get(0).getMetaDataVersion().get(0).getItemGroupDef();
    }

    private List<ODMcomplexTypeDefinitionItemDef> getItemDefs() {
        return getContainer().getOdm().getStudy().get(0).getMetaDataVersion().get(0).getItemDef();
    }

    private List<ODMcomplexTypeDefinitionCodeList> getCodeLists() {
        return getContainer().getOdm().getStudy().get(0).getMetaDataVersion().get(0).getCodeList();
    }

    private List<OCodmComplexTypeDefinitionMultiSelectList> getMultiSelectLists() {
        return getContainer().getMultiSelectList();
    }

    private void initMultiSelectList() {
        for (Object obj : getContainer().getOdm().getStudy().get(0).getMetaDataVersion().get(0).getMetaDataVersionElementExtension()) {
            if (obj instanceof OCodmComplexTypeDefinitionMultiSelectList) {
                getContainer().getMultiSelectList().add((OCodmComplexTypeDefinitionMultiSelectList) obj);
            }
        }
    }

    public void initRules() {
        for (Object obj : getContainer().getOdm().getStudy().get(0).getMetaDataVersion().get(0).getMetaDataVersionElementExtension()) {
            if (obj instanceof Rules) {
                getContainer().getRulesList().add((Rules) obj);
            }
        }
    }

    public UIODMContainer getContainer() {
        return container;
    }

    public void setContainer(UIODMContainer container) {
        this.container = container;
    }

}
