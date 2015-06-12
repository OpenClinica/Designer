package org.openclinica.ns.rules.v31;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NotificationActionType", propOrder = {
    "to",
    "subject",
    "message",
})
public class NotificationActionType {

    @XmlAttribute(name = "IfExpressionEvaluates")
    protected String ifExpressionEvaluates;
    @XmlElement(name = "To", required = true)
    protected String to;
    @XmlElement(name = "Subject", required = true)
    protected String subject;
    @XmlElement(name = "Message", required = true)
    protected String message;

    /**
     * Gets the value of the ifExpressionEvaluates property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIfExpressionEvaluates() {
        return ifExpressionEvaluates;
    }

    /**
     * Sets the value of the ifExpressionEvaluates property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIfExpressionEvaluates(String value) {
        this.ifExpressionEvaluates = value;
    }

    /**
     * Gets the value of the to property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets the value of the to property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTo(String value) {
        this.to = value;
    }

    /**
     * Gets the value of the subject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the value of the subject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubject(String value) {
        this.subject = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }
}