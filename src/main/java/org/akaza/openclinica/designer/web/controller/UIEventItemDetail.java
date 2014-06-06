package org.akaza.openclinica.designer.web.controller;

import java.util.Map;

public class UIEventItemDetail extends UIEntityDetail {
    String eventName;
    String eventOid;
    String eventRepeating;
    String responseLabel;
    Map<String, String> responseOptions;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventOid() {
        return eventOid;
    }

    public void setEventOid(String eventOid) {
        this.eventOid = eventOid;
    }

    public String getEventRepeating() {
        return eventRepeating;
    }

    public void setEventRepeating(String eventRepeating) {
        this.eventRepeating = eventRepeating;
    }

    public String getResponseLabel() {
        return responseLabel;
    }

    public void setResponseLabel(String responseLabel) {
        this.responseLabel = responseLabel;
    }

    public Map<String, String> getResponseOptions() {
        return responseOptions;
    }

    public void setResponseOptions(Map<String, String> responseOptions) {
        this.responseOptions = responseOptions;
    }

}
