package org.openclinica.ns.rules.v31;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RunOnScheduleType", propOrder = {})
public class RunOnScheduleType {

	@XmlAttribute(name = "Time")
    protected String time;

    /**
     * Gets the value of the time property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     * 
     * @param time
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTime(String time) {
        this.time = time;
    }
}