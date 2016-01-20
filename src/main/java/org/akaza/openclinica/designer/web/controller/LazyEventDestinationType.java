package org.akaza.openclinica.designer.web.controller;

import org.openclinica.ns.rules.v31.EventDestinationType;

public class LazyEventDestinationType extends EventDestinationType {

    String placeHolder;

    public LazyEventDestinationType() {
        // TODO Auto-generated constructor stub
    }

    public LazyEventDestinationType(EventDestinationType eventDestinationType, String placeHolder) {
        this.setProperty(eventDestinationType.getProperty());
        this.setValueExpression(eventDestinationType.getValueExpression());
        this.placeHolder = placeHolder;
    }

    public String getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(String placeHolder) {
        this.placeHolder = placeHolder;
    }

}
