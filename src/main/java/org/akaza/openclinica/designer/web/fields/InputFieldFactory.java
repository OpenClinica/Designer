package org.akaza.openclinica.designer.web.fields;

import org.akaza.openclinica.designer.web.controller.UIItemDetail;
import org.akaza.openclinica.designer.web.fields.InputField.DataType;
import org.akaza.openclinica.designer.web.fields.InputField.Type;
import org.akaza.openclinica.designer.web.fields.validators.FieldValidator;

import java.util.Map;

public class InputFieldFactory {

    public static InputField createInputField(InputField.Type type, InputField.DataType dataType, String propertyName, String displayName) {
        return new DefaultInputField(type, dataType, propertyName, displayName);
    }

    public static InputField createInputField(InputField.Type type, InputField.DataType dataType, String propertyName, String displayName,
            FieldValidator... validators) {
        return new DefaultInputField(type, dataType, propertyName, displayName, validators);
    }

    public static InputField createSelectField(InputField.Type type, InputField.DataType dataType, String propertyName, String displayName,
            Map<String, String> options, FieldValidator... validators) {
        return new SelectInputField(type, dataType, propertyName, displayName, options, validators);
    }

    public static InputField createInputField(String propertyName, String displayName, UIItemDetail itemDetail) {
        String itemResponseType = itemDetail.getItemDetailsPerCrfVersion().get(0).getResponseType();
        final String ocIntegerDataType = "INTEGER";
        final String ocStringDataType = "TEXT";
        final String ocDateDataType = "DATE";
        final String ocFloatDataType = "FLOAT";
        if (itemResponseType.equals("text")) {
            if (itemDetail.getDataType().equals(ocDateDataType)) {
                return createInputField(Type.TEXT, DataType.DATE, propertyName, displayName, FieldValidator.DATE_VALIDATOR);
            } else if (itemDetail.getDataType().equals(ocIntegerDataType)) {
                return createInputField(Type.TEXT, DataType.INTEGER, propertyName, displayName, FieldValidator.INTEGER_VALIDATOR);
            } else if (itemDetail.getDataType().equals(ocFloatDataType)) {
                return createInputField(Type.TEXT, DataType.INTEGER, propertyName, displayName, FieldValidator.NUMBER_VALIDATOR);
            } else {
                return createInputField(Type.TEXT, DataType.STRING, propertyName, displayName);
            }
        } else if (itemResponseType.equals("single-select") || itemResponseType.equals("radio")) {
            if (itemDetail.getDataType().equals(ocIntegerDataType)) {
                return createSelectField(Type.SINGLESELECT, DataType.INTEGER, propertyName, displayName, itemDetail.getOptionsForFirstVersion(),
                        FieldValidator.INTEGER_VALIDATOR);
            } else if (itemDetail.getDataType().equals(ocFloatDataType)) {
                return createSelectField(Type.SINGLESELECT, DataType.INTEGER, propertyName, displayName, itemDetail.getOptionsForFirstVersion(),
                        FieldValidator.NUMBER_VALIDATOR);
            } else {
                return createSelectField(Type.SINGLESELECT, DataType.STRING, propertyName, displayName, itemDetail.getOptionsForFirstVersion());
            }
        } else if (itemResponseType.equals("multi-select") || itemResponseType.equals("checkbox")) {
            if (itemDetail.getDataType().equals(ocIntegerDataType)) {
                return createSelectField(Type.MULTISELECT, DataType.INTEGER, propertyName, displayName, itemDetail.getMultiSelectListForFirstVersion(),
                        FieldValidator.INTEGER_VALIDATOR);
            } else if (itemDetail.getDataType().equals(ocFloatDataType)) {
                return createSelectField(Type.MULTISELECT, DataType.INTEGER, propertyName, displayName, itemDetail.getMultiSelectListForFirstVersion(),
                        FieldValidator.NUMBER_VALIDATOR);
            } else {
                return createSelectField(Type.MULTISELECT, DataType.STRING, propertyName, displayName, itemDetail.getMultiSelectListForFirstVersion());
            }
        } else {
            return createInputField(Type.TEXT, DataType.STRING, propertyName, displayName);
        }
    }

    public static class DefaultInputField extends AbstractInputField {

        private DefaultInputField(Type type, DataType dataType, String propertyName, String displayName) {
            super(type, dataType, propertyName, displayName);
        }

        private DefaultInputField(Type type, DataType dataType, String propertyName, String displayName, FieldValidator... validators) {
            super(type, dataType, propertyName, displayName, validators);
        }
    }

    public static class SelectInputField extends AbstractInputField {

        private SelectInputField(Type type, DataType dataType, String propertyName, String displayName, Map<String, String> options) {
            super(type, dataType, propertyName, displayName);
            super.setOptions(options);
        }

        private SelectInputField(Type type, DataType dataType, String propertyName, String displayName, Map<String, String> options,
                FieldValidator... validators) {
            super(type, dataType, propertyName, displayName, validators);
            super.setOptions(options);
        }
    }

}
