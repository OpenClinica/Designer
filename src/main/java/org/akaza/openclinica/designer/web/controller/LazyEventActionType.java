package org.akaza.openclinica.designer.web.controller;

import org.openclinica.ns.rules.v31.EventActionType;
import org.openclinica.ns.rules.v31.EventDestinationType;
import org.springframework.util.AutoPopulatingList;

public class LazyEventActionType extends EventActionType {

    private AutoPopulatingList<LazyEventDestinationType> lazyProperties;

    public LazyEventActionType() {
        // TODO Auto-generated constructor stub
        // destinationProperty = new ArrayList<PropertyType>();
        lazyProperties = new AutoPopulatingList(LazyEventDestinationType.class);
    }

    public LazyEventActionType(EventActionType eventActionType) {
        this();
        this.setIfExpressionEvaluates(eventActionType.getIfExpressionEvaluates());
        this.setRunOnStatus(eventActionType.getRunOnStatus());
        this.getEventDestination().addAll(eventActionType.getEventDestination());
        for (EventDestinationType eventDestinationType : this.getEventDestination()) {
            LazyEventDestinationType lazyEventDestination = new LazyEventDestinationType(eventDestinationType, "placeHolder");
            getLazyProperties().add(lazyEventDestination);
        }
    }

    public AutoPopulatingList<LazyEventDestinationType> getLazyProperties() {
        return lazyProperties;
    }

    public void setLazyProperties(AutoPopulatingList<LazyEventDestinationType> lazyProperties) {
        this.lazyProperties = lazyProperties;
    }

}
