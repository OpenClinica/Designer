package org.akaza.openclinica.designer.web.controller;

import org.openclinica.ns.rules.v31.HideActionType;
import org.openclinica.ns.rules.v31.PropertyType;
import org.springframework.util.AutoPopulatingList;

public class LazyHideActionType extends HideActionType {

    private AutoPopulatingList<LazyPropertyType> lazyProperties;

    public LazyHideActionType() {
        // TODO Auto-generated constructor stub
        // destinationProperty = new ArrayList<PropertyType>();
        lazyProperties = new AutoPopulatingList(LazyPropertyType.class);
    }

    public LazyHideActionType(HideActionType hideActionType) {
        this();
        this.setIfExpressionEvaluates(hideActionType.getIfExpressionEvaluates());
        this.setMessage(hideActionType.getMessage());
        this.setRun(hideActionType.getRun());
        this.getDestinationProperty().addAll(hideActionType.getDestinationProperty());
        for (PropertyType propertyType : this.getDestinationProperty()) {
            LazyPropertyType lazyProperty = new LazyPropertyType(propertyType, "placeHolder");
            getLazyProperties().add(lazyProperty);
        }
    }

    public AutoPopulatingList<LazyPropertyType> getLazyProperties() {
        return lazyProperties;
    }

    public void setLazyProperties(AutoPopulatingList<LazyPropertyType> lazyProperties) {
        this.lazyProperties = lazyProperties;
    }

}
