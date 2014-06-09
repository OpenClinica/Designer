package org.akaza.openclinica.designer.web.controller;

import org.akaza.openclinica.designer.web.fields.InputField;
import org.akaza.openclinica.designer.web.fields.InputFieldFactory;
import org.akaza.openclinica.designer.web.fields.validators.FieldValidator;
import org.openclinica.ns.rules_test.v31.ParameterType;
import org.openclinica.ns.rules_test.v31.RulesTest;
import org.cdisc.ns.odm.v130.ODMcomplexTypeDefinitionStudyEventDef;

import java.util.ArrayList;
import java.util.List;

public class TestRulesGetResponseHandler implements TestRulesResponseHandler {

    @Override
    public void handle(RulesCommand form, RulesTest response, UIODMBuilder uiODMBuilder) {
        List<InputField> inputFields = new ArrayList<InputField>();
        for (ParameterType parameterType : response.getParameters()) {
            if (!parameterType.getKey().equals("result") && !parameterType.getKey().equals("ruleEvaluatesTo")
                && !parameterType.getKey().equals("ruleValidation")) {
                UIItemDetail itemDetail = uiODMBuilder.buildItemDetail(parameterType.getKey());
                if (null != itemDetail) {
                    form.getRuleProperties().put(parameterType.getKey(), parameterType.getValue());
                    inputFields.add(InputFieldFactory.createInputField("ruleProperties['" + parameterType.getKey() + "']", parameterType.getKey(), itemDetail));
                } else {
                    // parameterType.getKey() == SE_REGISTRATIONVISIT.STARTDATE
                    String[] oidSplitter = parameterType.getKey().split("\\.");
                    ODMcomplexTypeDefinitionStudyEventDef studyEventDef = uiODMBuilder.getStudyEventDefByStudyEventOID(oidSplitter[0]);
                    if (studyEventDef != null) {
                        if (oidSplitter[1].equals("STATUS")) {
                        } else if (oidSplitter[1].equals("STARTDATE")) {
                            inputFields.add(InputFieldFactory.createInputField(InputField.Type.TEXT, InputField.DataType.DATE, "ruleProperties['" + parameterType.getKey() + "']", parameterType.getKey(), FieldValidator.DATE_VALIDATOR));
                        } else {
                            // SOMETHING ELSE IS GOING ON HERE THROW AN ERROR ???
                        }
                    }
                }

                
            }
        }

        form.setRulePropertiesHtml(inputFields);
    }

}
