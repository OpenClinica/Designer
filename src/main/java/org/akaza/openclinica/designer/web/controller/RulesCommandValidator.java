package org.akaza.openclinica.designer.web.controller;

import org.openclinica.ns.rules.v31.DiscrepancyNoteActionType;
import org.openclinica.ns.rules.v31.EmailActionType;
import org.openclinica.ns.rules.v31.HideActionType;
import org.openclinica.ns.rules.v31.InsertActionType;
import org.openclinica.ns.rules.v31.PropertyType;
import org.openclinica.ns.rules.v31.RuleDefType;
import org.openclinica.ns.rules.v31.ShowActionType;
import org.openclinica.ns.rules.v31.TargetType;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RulesCommandValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        return RulesCommand.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors e) {
        // ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
        RulesCommand p = (RulesCommand) obj;
        p.getRuleRef().lazyToNonLazy();

        ValidationUtils.rejectIfEmptyOrWhitespace(e, "ruleDef.OID", "error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "ruleDef.expression.value", "error.empty");

        TargetType target = p.getTargetCurated(p.getTarget());
        if (target.getValue() == null || target.getValue().length() == 0) {
            e.rejectValue("target.value", "error.empty");
        }

        RuleDefType ruleDef = p.getRuleDefCurated();
        if (ruleDef.getExpression().getValue() == null || ruleDef.getExpression().getValue().length() == 0) {
            e.rejectValue("ruleDef.expression.value", "error.empty");
        }

        LazyRuleRefType2 action = p.getRuleRef();
        // At least one action has to be selected
        if (action.getLazyDiscrepancyNoteActions().isEmpty() && action.getLazyEmailActions().isEmpty() && action.getLazyHideActions().isEmpty()
            && action.getLazyInsertActions().isEmpty() && action.getLazyShowActions().isEmpty()) {
            e.rejectValue("addActions", "add.action");
        }

        // DiscrepancyNote action validation
        validateDiscrepancyNoteAction(action, e);
        // Email action validation
        validateEmailAction(action, e);
        // Show action validation
        validateShowAction(action, e);
        // Hide action validation
        validateHideAction(action, e);
        // Insert action validation
        validateInsertAction(action, e);

    }

    private void validateDiscrepancyNoteAction(LazyRuleRefType2 action, Errors e) {
        if (!action.getDiscrepancyNoteAction().isEmpty()) {
            for (DiscrepancyNoteActionType dnAction : action.getDiscrepancyNoteAction()) {
                if (dnAction.getMessage() == null || dnAction.getMessage().trim().length() == 0) {
                    e.rejectValue("ruleRef.lazyDiscrepancyNoteActions[" + action.getDiscrepancyNoteAction().indexOf(dnAction) + "].message",
                            "discrepancy.note.message.empty");
                }
            }
        }
    }

    private void validateEmailAction(LazyRuleRefType2 action, Errors e) {
        if (!action.getLazyEmailActions().isEmpty()) {
            for (EmailActionType emailAction : action.getEmailAction()) {
                if (emailAction.getTo() == null || emailAction.getTo().trim().length() == 0) {
                    e.rejectValue("ruleRef.lazyEmailActions[" + action.getEmailAction().indexOf(emailAction) + "].to", "email.to.empty");
                } else {
                    Pattern pattern = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                    StringTokenizer tokenizer = new StringTokenizer(emailAction.getTo(), ",");
                    if (tokenizer.countTokens() == 0) {
                        Matcher matcher = pattern.matcher(emailAction.getTo());
                        if (!matcher.matches()) {
                            e.rejectValue("ruleRef.lazyEmailActions[" + action.getEmailAction().indexOf(emailAction) + "].to", "email.to.not.valid");
                        }
                    } else {
                        while (tokenizer.hasMoreTokens()) {
                            Matcher matcher = pattern.matcher(tokenizer.nextToken().trim());
                            if (!matcher.matches()) {
                                e.rejectValue("ruleRef.lazyEmailActions[" + action.getEmailAction().indexOf(emailAction) + "].to", "email.to.not.valid");
                                break;
                            }
                        }
                    }
                }
                if (emailAction.getMessage() == null || emailAction.getMessage().trim().length() == 0) {
                    e.rejectValue("ruleRef.lazyEmailActions[" + action.getEmailAction().indexOf(emailAction) + "].message", "error.empty");
                }
            }
        }
    }

    private void validateShowAction(LazyRuleRefType2 action, Errors e) {
        if (!action.getLazyShowActions().isEmpty()) {
            for (ShowActionType showAction : action.getShowAction()) {
                if (showAction.getMessage() == null || showAction.getMessage().trim().length() == 0) {
                    e.rejectValue("ruleRef.lazyShowActions[" + action.getShowAction().indexOf(showAction) + "].message", "message.cant.empty");
                }

                if (showAction.getDestinationProperty() == null || showAction.getDestinationProperty().isEmpty()) {
                    e.rejectValue("ruleRef.lazyShowActions[" + action.getShowAction().indexOf(showAction) + "].lazyProperties", "atleast.one.apply.on");
                } else {
                    for (PropertyType propertyType : showAction.getDestinationProperty()) {
                        if (propertyType.getOID() == null || propertyType.getOID().trim().length() == 0
                            || propertyType.getOID().equalsIgnoreCase("Drag an Item or type an OID")) {
                            e.rejectValue("ruleRef.lazyShowActions[" + action.getShowAction().indexOf(showAction) + "].lazyProperties["
                                + showAction.getDestinationProperty().indexOf(propertyType) + "].OID", "error.empty");
                        }
                    }
                }
            }
        }
    }

    private void validateHideAction(LazyRuleRefType2 action, Errors e) {
        if (!action.getLazyHideActions().isEmpty()) {
            for (HideActionType hideAction : action.getHideAction()) {
                if (hideAction.getMessage() == null || hideAction.getMessage().trim().length() == 0) {
                    e.rejectValue("ruleRef.lazyHideActions[" + action.getHideAction().indexOf(hideAction) + "].message", "message.cant.empty");
                }

                if (hideAction.getDestinationProperty() == null || hideAction.getDestinationProperty().isEmpty()) {
                    e.rejectValue("ruleRef.lazyHideActions[" + action.getHideAction().indexOf(hideAction) + "].lazyProperties", "atleast.one.apply.on");
                } else {
                    for (PropertyType propertyType : hideAction.getDestinationProperty()) {
                        if (propertyType.getOID() == null || propertyType.getOID().trim().length() == 0
                            || propertyType.getOID().equalsIgnoreCase("Drag an Item or type an OID")) {
                            e.rejectValue("ruleRef.lazyHideActions[" + action.getHideAction().indexOf(hideAction) + "].lazyProperties["
                                + hideAction.getDestinationProperty().indexOf(propertyType) + "].OID", "error.empty");
                        }
                    }
                }
            }
        }
    }

    private void validateInsertAction(LazyRuleRefType2 action, Errors e) {
        if (!action.getLazyInsertActions().isEmpty()) {
            for (InsertActionType insertAction : action.getInsertAction()) {
                if (insertAction.getDestinationProperty() == null || insertAction.getDestinationProperty().isEmpty()) {
                    e.rejectValue("ruleRef.lazyInsertActions[" + action.getInsertAction().indexOf(insertAction) + "].lazyProperties", "atleast.one.apply.on");
                } else {
                    for (PropertyType propertyType : insertAction.getDestinationProperty()) {
                        if (propertyType.getOID() == null || propertyType.getOID().trim().length() == 0
                            || propertyType.getOID().equalsIgnoreCase("Drag an Item or type an OID")) {
                            e.rejectValue("ruleRef.lazyInsertActions[" + action.getInsertAction().indexOf(insertAction) + "].lazyProperties["
                                + insertAction.getDestinationProperty().indexOf(propertyType) + "].OID", "error.empty");
                        }
                    }
                }
            }
        }
    }

}
