package org.akaza.openclinica.designer.web.controller;

import org.akaza.openclinica.designer.web.fields.InputField;
import org.jsoup.Jsoup;
import org.openclinica.ns.rules.v31.RuleDefType;
import org.openclinica.ns.rules.v31.RuleRefType;
import org.openclinica.ns.rules.v31.TargetType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RulesCommand {

    // All tabs
    LazyRuleRefType2 ruleRef;
    TargetType target;
    RuleDefType ruleDef;
    List<String> addActions = new ArrayList();
    // XML tab
    String xml;
    // Test tab
    List<InputField> rulePropertiesHtml;
    HashMap<String, String> ruleProperties;
    HashMap<String, String> testRulesResults;
    String testWillActionsRun;

    public RulesCommand() {
        ruleRef = new LazyRuleRefType2();
        target = new TargetType();
        ruleDef = new RuleDefType();
    }

    public LazyRuleRefType2 getRuleRef() {
        return ruleRef;
    }

    public void setRuleRef(LazyRuleRefType2 ruleRef) {
        this.ruleRef = ruleRef;
    }

    public TargetType getTarget() {
        return target;
    }

    public void setTarget(TargetType target) {
        this.target = target;
    }

    public RuleDefType getRuleDef() {
        return ruleDef;
    }

    public void setRuleDef(RuleDefType ruleDef) {
        this.ruleDef = ruleDef;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public TargetType getTargetCurated() {
        return getTargetCurated(this.target);
    }

    public TargetType getTargetCurated(TargetType htmlBasedTarget) {
        // To avoid receiving org.codehaus.jackson.map.JsonMappingException when command object is JSONd
        if (htmlBasedTarget == null) {
            return null;
        }
        TargetType target = new TargetType();
        target.setValue(htmlBasedTarget.getValue() == null ? null : html2text(htmlBasedTarget.getValue()));
        target.setContext(htmlBasedTarget.getContext());
        return target;
    }

    /**
     * When testing rules, and based on the rule expression result display a message indicating wether actions will run or
     * not.
     * @param ruleExpressionResult
     * @return String
     */
    public void populateTestWillActionsRun() {
        String ruleExpressionResult = testRulesResults.get("ruleEvaluatesTo");
        testWillActionsRun = ruleRef.willAnyActionFire(ruleExpressionResult) == true ? "Y" : "N";
    }

    public RuleDefType getRuleDefCurated() {
        RuleDefType ruleDef = new RuleDefType();
        ruleDef.setExpression(getTargetCurated(this.getRuleDef().getExpression()));
        ruleDef.setDescription(this.getRuleDef().getDescription());
        ruleDef.setName(this.getRuleDef().getName());
        ruleDef.setOID(this.getRuleDef().getOID());
        return ruleDef;
    }

    public void buildFromRules(TargetType target, RuleDefType ruleDef, RuleRefType ruleRef) {
        this.setTarget(target);
        this.setRuleDef(ruleDef);
        LazyRuleRefType2 ruleRefType = new LazyRuleRefType2(ruleRef);
        this.setRuleRef(ruleRefType);
    }

    public List<InputField> getRulePropertiesHtml() {
        if (rulePropertiesHtml == null) {
            rulePropertiesHtml = new ArrayList<InputField>();
        }
        return rulePropertiesHtml;
    }

    public void setRulePropertiesHtml(List<InputField> rulePropertiesHtml) {
        this.rulePropertiesHtml = rulePropertiesHtml;
    }

    public HashMap<String, String> getRuleProperties() {
        if (ruleProperties == null) {
            ruleProperties = new HashMap<String, String>();
        }
        return ruleProperties;
    }

    public void setRuleProperties(HashMap<String, String> ruleProperties) {
        this.ruleProperties = ruleProperties;
    }

    public HashMap<String, String> getTestRulesResults() {
        if (testRulesResults == null) {
            testRulesResults = new HashMap<String, String>();
        }
        return testRulesResults;
    }

    public void setTestRulesResults(HashMap<String, String> testRulesResults) {
        this.testRulesResults = testRulesResults;
    }

    public List<String> getAddActions() {
        return addActions;
    }

    public void setAddActions(List<String> addActions) {
        this.addActions = addActions;
    }

    public String getTestWillActionsRun() {
        return testWillActionsRun;
    }

    public void setTestWillActionsRun(String testWillActionsRun) {
        this.testWillActionsRun = testWillActionsRun;
    }

    public static String html2text(String html) {
        html = html.replaceAll("&nbsp;", " ");
        return Jsoup.parse(html).text();
    }
}
