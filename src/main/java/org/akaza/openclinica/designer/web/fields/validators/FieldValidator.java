package org.akaza.openclinica.designer.web.fields.validators;

public abstract class FieldValidator {

    public static final FieldValidator DATE_VALIDATOR;
    public static final FieldValidator NUMBER_VALIDATOR;
    public static final FieldValidator INTEGER_VALIDATOR;

    static {

        NUMBER_VALIDATOR = new NumberValidator();
        INTEGER_VALIDATOR = new IntegerValidator();
        DATE_VALIDATOR = new DateValidator();
    }

    /**
     * Will validate the input, based on the specific validation rules.
     * 
     * @return
     */
    public abstract boolean isValid(Object fieldValue);

    /**
     * This will return the error message prefix. eg: in case of NotNullValidator - it should be "Missing" where as in the
     * case of a PhoneNumberValidator it should be "Invalid"
     * 
     * @return
     */
    public abstract String getMessagePrefix();

    public abstract String getValidatorCSSClassName();

    public String stringValue(Object fieldValue) {
        if (fieldValue == null)
            return null;
        return fieldValue.toString();
    }

}
