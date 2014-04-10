package org.akaza.openclinica.designer.web.controller;

import org.openclinica.ns.rules_test.v31.ParameterType;
import org.openclinica.ns.rules_test.v31.RulesTest;
import org.openclinica.ns.rules_test.v31.RulesTestMessagesType;

public class TestRulesSubmitResponseHandler implements TestRulesResponseHandler {

    @Override
    public void handle(RulesCommand form, RulesTest response, UIODMBuilder uiODMBuilder) {
        for (ParameterType parameterType : response.getParameters()) {
            if (parameterType.getKey().equals("result") || parameterType.getKey().equals("ruleEvaluatesTo") || parameterType.getKey().equals("ruleValidation")) {
                form.getTestRulesResults().put(parameterType.getKey(), parameterType.getValue());
            } else {
                form.getRuleProperties().put(parameterType.getKey(), parameterType.getValue());
            }
        }
        for (RulesTestMessagesType messageType : response.getRulesTestMessages()) {
            form.getTestRulesResults().put(messageType.getKey(), messageType.getValue());
        }

    }

}
