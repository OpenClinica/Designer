package org.akaza.openclinica.designer.web.controller;

import org.akaza.openclinica.designer.web.fields.InputField;
import org.akaza.openclinica.designer.web.fields.InputFieldFactory;
import org.openclinica.ns.rules_test.v31.ParameterType;
import org.openclinica.ns.rules_test.v31.RulesTest;

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
                form.getRuleProperties().put(parameterType.getKey(), parameterType.getValue());
                inputFields.add(InputFieldFactory.createInputField("ruleProperties['" + parameterType.getKey() + "']", parameterType.getKey(), itemDetail));
            }
        }

        form.setRulePropertiesHtml(inputFields);
    }

}
