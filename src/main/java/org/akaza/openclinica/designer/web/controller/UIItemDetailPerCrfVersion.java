package org.akaza.openclinica.designer.web.controller;

import org.cdisc.ns.odm.v130.ODMcomplexTypeDefinitionCodeList;
import org.openclinica.ns.odm_ext_v130.v31.OCodmComplexTypeDefinitionMultiSelectList;

public class UIItemDetailPerCrfVersion {

    String leftItemText;
    String rightItemText;
    String defaultValue;
    String responseLayout;
    String responseType;
    String responseLabel;
    String sectionLabel;
    String groupName;
    String validation;
    String validationErrorMessage;
    String required;
    String phi;
    ODMcomplexTypeDefinitionCodeList codeList;
    OCodmComplexTypeDefinitionMultiSelectList multiSelectList;

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getResponseLayout() {
        return responseLayout;
    }

    public void setResponseLayout(String responseLayout) {
        this.responseLayout = responseLayout;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getResponseLabel() {
        return responseLabel;
    }

    public void setResponseLabel(String responseLabel) {
        this.responseLabel = responseLabel;
    }

    public String getSectionLabel() {
        return sectionLabel;
    }

    public void setSectionLabel(String sectionLabel) {
        this.sectionLabel = sectionLabel;
    }

    public String getValidation() {
        return validation;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

    public String getValidationErrorMessage() {
        return validationErrorMessage;
    }

    public void setValidationErrorMessage(String validationErrorMessage) {
        this.validationErrorMessage = validationErrorMessage;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    String crfVersionName;

    public String getLeftItemText() {
        return leftItemText;
    }

    public void setLeftItemText(String leftItemText) {
        this.leftItemText = leftItemText;
    }

    public String getRightItemText() {
        return rightItemText;
    }

    public void setRightItemText(String rightItemText) {
        this.rightItemText = rightItemText;
    }

    public String getCrfVersionName() {
        return crfVersionName;
    }

    public void setCrfVersionName(String crfVersionName) {
        this.crfVersionName = crfVersionName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getPhi() {
        return phi;
    }

    public void setPhi(String phi) {
        this.phi = phi;
    }

    public ODMcomplexTypeDefinitionCodeList getCodeList() {
        return codeList;
    }

    public void setCodeList(ODMcomplexTypeDefinitionCodeList codeList) {
        this.codeList = codeList;
    }

    public OCodmComplexTypeDefinitionMultiSelectList getMultiSelectList() {
        return multiSelectList;
    }

    public void setMultiSelectList(OCodmComplexTypeDefinitionMultiSelectList multiSelectList) {
        this.multiSelectList = multiSelectList;
    }
}
