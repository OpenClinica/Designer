package org.akaza.openclinica.designer.web.fields;

import org.akaza.openclinica.designer.web.fields.validators.FieldValidator;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractInputField implements InputField {
    private Type type;

    private DataType dataType;

    private String displayName;

    private String propertyName;

    private Map<String, Object> attributes;

    private Map<String, String> options;

    private FieldValidator[] validators;

    protected AbstractInputField() {
        this.attributes = new LinkedHashMap<String, Object>();
        this.options = new LinkedHashMap<String, String>();
    }

    protected AbstractInputField(Type type, DataType dataType, String propertyName, String displayName) {
        this();
        this.type = type;
        this.dataType = dataType;
        this.displayName = displayName;
        this.propertyName = propertyName;
    }

    protected AbstractInputField(Type type, DataType dataType, String propertyName, String displayName, FieldValidator... validators) {
        this();
        this.type = type;
        this.dataType = dataType;
        this.displayName = displayName;
        this.propertyName = propertyName;
        this.validators = validators;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String getTypeName() {
        return getType().name().toLowerCase();
    }

    @Override
    public DataType getDataType() {
        return dataType;
    }

    @Override
    public String getDataTypeName() {
        return getDataType().name().toLowerCase();
    }

    @Override
    public String getDisplayName() {
        return displayName == null ? propertyName : displayName;
    }

    @Override
    public String getPropertyId() {
        return propertyName.trim().replaceAll("\\W+", "");
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public Map<String, String> getOptions() {
        return options;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }

    @Override
    public FieldValidator[] getValidators() {
        return this.validators;
    }

    @Override
    public void validate(BeanWrapper commandBean, Errors errors) {
        if (validators == null)
            return;
        for (FieldValidator validator : validators) {
            if (!validator.isValid(commandBean.getPropertyValue(this.getPropertyName()))) {
                errors.rejectValue(this.getPropertyName(), "REQUIRED", validator.getMessagePrefix() + " " + this.getDisplayName());
                return;
            }
        }
    }

    // ////OBJECT METHODS

    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName()).append("[propertyName=").append(getPropertyName()).append("; category=").append(getTypeName())
                .append(']').toString();
    }
}
