package org.akaza.openclinica.designer.web.controller;

import org.openclinica.ns.rules_test.v31.RulesTest;

public interface TestRulesResponseHandler {

    void handle(RulesCommand form, RulesTest response, UIODMBuilder uiODMBuilder);

}
