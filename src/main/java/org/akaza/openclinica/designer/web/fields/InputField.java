package org.akaza.openclinica.designer.web.fields;

import org.akaza.openclinica.designer.web.fields.validators.FieldValidator;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

import java.util.Map;

public interface InputField {

    public Type getType();

    public String getTypeName();

    public DataType getDataType();

    public String getDataTypeName();

    public String getDisplayName();

    public String getPropertyName();

    public String getPropertyId();

    public Map<String, Object> getAttributes();

    public Map<String, String> getOptions();

    public void validate(BeanWrapper commandBean, Errors errors);

    public FieldValidator[] getValidators();

    enum Type {
        TEXT, SINGLESELECT, MULTISELECT
    }

    enum DataType {
        STRING, INTEGER, DATE
    }
}
