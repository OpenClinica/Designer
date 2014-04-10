package org.akaza.openclinica.designer.web.controller;

import org.openclinica.ns.rules.v31.PropertyType;
import org.openclinica.ns.rules.v31.ShowActionType;
import org.springframework.util.AutoPopulatingList;

public class LazyShowActionType extends ShowActionType {

    private AutoPopulatingList<LazyPropertyType> lazyProperties;

    public LazyShowActionType() {
        // TODO Auto-generated constructor stub
        // destinationProperty = new ArrayList<PropertyType>();
        lazyProperties = new AutoPopulatingList(LazyPropertyType.class);
    }

    public LazyShowActionType(ShowActionType showActionType) {
        this();
        this.setIfExpressionEvaluates(showActionType.getIfExpressionEvaluates());
        this.setMessage(showActionType.getMessage());
        this.setRun(showActionType.getRun());
        this.getDestinationProperty().addAll(showActionType.getDestinationProperty());
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
