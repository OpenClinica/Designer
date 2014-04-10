package org.akaza.openclinica.designer.web.controller;

import org.openclinica.ns.rules.v31.PropertyType;

public class LazyPropertyType extends PropertyType {

    String placeHolder;

    public LazyPropertyType() {
        // TODO Auto-generated constructor stub
    }

    public LazyPropertyType(PropertyType propertyType, String placeHolder) {
        this.setOID(propertyType.getOID());
        this.setValue(propertyType.getValue());
        this.setValueExpression(propertyType.getValueExpression());
        this.placeHolder = placeHolder;
    }

    public String getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(String placeHolder) {
        this.placeHolder = placeHolder;
    }

}
