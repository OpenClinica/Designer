package org.akaza.openclinica.designer.web.controller;

import org.openclinica.ns.rules.v31.InsertActionType;
import org.openclinica.ns.rules.v31.PropertyType;
import org.springframework.util.AutoPopulatingList;

public class LazyInsertActionType extends InsertActionType {

    private AutoPopulatingList<LazyPropertyType> lazyProperties;

    public LazyInsertActionType() {
        // TODO Auto-generated constructor stub
        // destinationProperty = new ArrayList<PropertyType>();
        lazyProperties = new AutoPopulatingList(LazyPropertyType.class);
    }

    public LazyInsertActionType(InsertActionType insertActionType) {
        this();
        this.setIfExpressionEvaluates(insertActionType.getIfExpressionEvaluates());
        this.setRun(insertActionType.getRun());
        this.getDestinationProperty().addAll(insertActionType.getDestinationProperty());
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
