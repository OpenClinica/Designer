package org.akaza.openclinica.designer.web.fields.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValidator extends FieldValidator {

    @Override
    public boolean isValid(Object fieldValue) {
        // return fieldValue instanceof Date ? true : false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date theDate = null;
        try {
            theDate = sdf.parse((String) fieldValue);
        } catch (ParseException e) {
            return false;
        }

        if (!sdf.format(theDate).equals(fieldValue)) {
            return false;
        }

        return true;
    }

    @Override
    public String getMessagePrefix() {
        return "Incorrect date value";
    }

    @Override
    public String getValidatorCSSClassName() {
        return "DATE";
    }

}