package org.akaza.openclinica.designer.web.controller;

import org.cdisc.ns.odm.v130.ODM;
import org.cdisc.ns.odm.v130.ODMcomplexTypeDefinitionItemDef;
import org.openclinica.ns.odm_ext_v130.v31.OCodmComplexTypeDefinitionItemDetails;
import org.openclinica.ns.odm_ext_v130.v31.OCodmComplexTypeDefinitionMultiSelectList;
import org.openclinica.ns.odm_ext_v130.v31.OCodmComplexTypeDefinitionMultiSelectListRef;
import org.openclinica.ns.rules.v31.RuleAssignmentType;
import org.openclinica.ns.rules.v31.RuleDefType;
import org.openclinica.ns.rules.v31.RuleRefType;
import org.openclinica.ns.rules.v31.Rules;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

import java.util.ArrayList;
import java.util.List;

public class UIODMContainer {

    private ODM odm;
    private List<UICrf> crfs;
    private List<UIEvent> events;

    private List<OCodmComplexTypeDefinitionMultiSelectList> multiSelectList;
    private List<Rules> rulesList;

    public ODM getOdm() {
        return odm;
    }

    public void setOdm(ODM odm) {
        this.odm = odm;
    }

    public List<UICrf> getCrfs() {
        return crfs;
    }

    public void setCrfs(List<UICrf> crfs) {
        this.crfs = crfs;
    }

    public String getStudyOid() {
        return getOdm().getStudy().get(0).getOID();
    }

    public List<OCodmComplexTypeDefinitionMultiSelectList> getMultiSelectList() {
        if (this.multiSelectList == null) {
            multiSelectList = new ArrayList<OCodmComplexTypeDefinitionMultiSelectList>();
        }
        return multiSelectList;
    }

    public void setMultiSelectList(List<OCodmComplexTypeDefinitionMultiSelectList> multiSelectList) {
        this.multiSelectList = multiSelectList;
    }

    public List<Rules> getRulesList() {
        if (this.rulesList == null) {
            rulesList = new ArrayList<Rules>();
        }
        return rulesList;
    }

    public void setRulesList(List<Rules> rulesList) {
        this.rulesList = rulesList;
    }

    public List<UIEvent> getEvents() {
        return events;
    }

    public void setEvents(List<UIEvent> events) {
        this.events = events;
    }

    public UICrf addCrfIfNotExist(UICrf crf) {
        if (crfs == null) {
            crfs = new ArrayList<UICrf>();
        }
        if (!crfs.contains(crf)) {
            crfs.add(crf);
        }
        return crfs.get(crfs.indexOf(crf));
    }

    public UIEvent addEventIfNotExist(UIEvent event) {
        if (events == null) {
            events = new ArrayList<UIEvent>();
        }
        if (!events.contains(event)) {
            events.add(event);
        }
        return events.get(events.indexOf(event));
    }

    public String getStudyNameAndOid() {
        return odm.getStudy().get(0).getGlobalVariables().getStudyName().getValue() + " (" + odm.getStudy().get(0).getOID() + ") ";
    }

    private Rules getRules() {
        List<Rules> rules = getRulesList();
        if (rules != null && rules.size() > 0) {
            return rules.get(0);
        }
        return null;
    }

    public RulesCommand getRuleCommandByRuleOidAndTarget(String ruleOid, String target) {
        Rules rules = getRules();

        for (RuleAssignmentType ruleAssignment : rules.getRuleAssignment()) {
            if (ruleAssignment.getTarget().getValue().equals(target)) {
                for (RuleRefType ruleRef : ruleAssignment.getRuleRef()) {
                    if (ruleRef.getOID().equals(ruleOid)) {
                        RuleDefType ruleDef = getRuleDefByOid(ruleRef.getOID(), rules.getRuleDef());
                        if (ruleDef != null) {
                            RulesCommand rc = new RulesCommand();
                            rc.buildFromRules(ruleAssignment.getTarget(), ruleDef, ruleRef);
                            return rc;
                        } else {
                            return null;
                        }

                    }
                }
            }
        }
        return null;
    }

    public List<OCodmComplexTypeDefinitionMultiSelectListRef> getMultiSelectRefFromItemDef(ODMcomplexTypeDefinitionItemDef itemDef) {
        ArrayList<OCodmComplexTypeDefinitionMultiSelectListRef> multiSelectRefs = new ArrayList<OCodmComplexTypeDefinitionMultiSelectListRef>();
        for (Object obj : itemDef.getItemDefElementExtension()) {
            if (obj instanceof OCodmComplexTypeDefinitionMultiSelectListRef) {
                multiSelectRefs.add((OCodmComplexTypeDefinitionMultiSelectListRef) obj);
            }
        }
        return multiSelectRefs;
    }

    public OCodmComplexTypeDefinitionItemDetails getItemDetailsFromItemDef(ODMcomplexTypeDefinitionItemDef itemDef) {
        for (Object obj : itemDef.getItemDefElementExtension()) {
            if (obj instanceof OCodmComplexTypeDefinitionItemDetails) {
                return (OCodmComplexTypeDefinitionItemDetails) obj;
            }
        }
        return null;
    }

    public Boolean isItemDetailHidden(ODMcomplexTypeDefinitionItemDef itemDef) {
        OCodmComplexTypeDefinitionItemDetails itemDetails = getItemDetailsFromItemDef(itemDef);
        return itemDetails.getItemPresentInForm().get(0).getShowItem().toUpperCase().equals("NO") ? true : false;
    }

    public RuleDefType getRuleDefByOid(String oid, List<RuleDefType> unmarshalledRuleDefs) {
        List<RuleDefType> ruleDefs = new ArrayList<RuleDefType>();
        ruleDefs.addAll(Collections2.filter(unmarshalledRuleDefs, ruleOIDEqualTo(oid)));
        return ruleDefs.size() > 0 ? ruleDefs.get(0) : null;
    }

    private Predicate<RuleDefType> ruleOIDEqualTo(final String value) {
        return new Predicate<RuleDefType>() {
            @Override
            public boolean apply(RuleDefType ruleDefType) {
                return ruleDefType.getOID().equals(value);
            }
        };
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

    public UIEvent getEventsByOID(String oid) {
        List<UIEvent> events = new ArrayList<UIEvent>();
        events.addAll(Collections2.filter(getEvents(), eventOIDEqualTo(oid)));
        return events.size() > 0 ? events.get(0) : null;

    }

    private Predicate<UIEvent> eventOIDEqualTo(final String value) {
        return new Predicate<UIEvent>() {
            @Override
            public boolean apply(UIEvent event) {
                return event.getOid().equals(value);
            }
        };
    }

}
