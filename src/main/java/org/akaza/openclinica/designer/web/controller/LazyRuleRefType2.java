package org.akaza.openclinica.designer.web.controller;

import org.openclinica.ns.rules.v31.*;
import org.springframework.util.AutoPopulatingList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Transient;

/**
 * @author Krikor Krumlian
 *         <p>
 *         Added this class for the sole purpose of adding lazyLists to support dynamic add/remove of actions on the UI.
 *         </p>
 */
public class LazyRuleRefType2 extends RuleRefType {

    private AutoPopulatingList<DiscrepancyNoteActionType> lazyDiscrepancyNoteActions;
    private AutoPopulatingList<EmailActionType> lazyEmailActions;
    private AutoPopulatingList<LazyShowActionType> lazyShowActions;
    private AutoPopulatingList<LazyHideActionType> lazyHideActions;
    private AutoPopulatingList<LazyInsertActionType> lazyInsertActions;
    private AutoPopulatingList<LazyEventActionType> lazyEventActions;
    private AutoPopulatingList<NotificationActionType> lazyNotificationActions;

    public LazyRuleRefType2() {
        discrepancyNoteAction = new ArrayList<DiscrepancyNoteActionType>();
        emailAction = new ArrayList<EmailActionType>();
        showAction = new ArrayList<ShowActionType>();
        hideAction = new ArrayList<HideActionType>();
        insertAction = new ArrayList<InsertActionType>();
        eventAction = new ArrayList<EventActionType>();
        notificationAction = new ArrayList<NotificationActionType>();
        lazyDiscrepancyNoteActions = new AutoPopulatingList(discrepancyNoteAction, DiscrepancyNoteActionType.class);
        lazyEmailActions = new AutoPopulatingList(emailAction, EmailActionType.class);
        lazyShowActions = new AutoPopulatingList(LazyShowActionType.class);
        lazyHideActions = new AutoPopulatingList(LazyHideActionType.class);
        lazyInsertActions = new AutoPopulatingList(LazyInsertActionType.class);
        lazyEventActions = new AutoPopulatingList(LazyEventActionType.class);
        lazyNotificationActions = new AutoPopulatingList(notificationAction, NotificationActionType.class);
        // TODO Auto-generated constructor stub
    }

    public LazyRuleRefType2(RuleRefType ruleRef) {
        this();
        emailActionToLazyEmailAction(ruleRef.getEmailAction());
        discrepancyNoteActionToLazyDiscrepancyNoteAction(ruleRef.getDiscrepancyNoteAction());
        showActionToLazyShowAction(ruleRef.getShowAction());
        hideActionToLazyHideAction(ruleRef.getHideAction());
        insertActionToLazyInsertAction(ruleRef.getInsertAction());
        eventActionToLazyEventAction(ruleRef.getEventAction());
        notificationActionToLazyNotificationAction(ruleRef.getNotificationAction());
    }

    public void lazyToNonLazy() {
        reAlignDiscrepancyNoteActions();
        reAlignEmailActions();
        reAlignLazyShowActions();
        reAlignLazyHideActions();
        reAlignLazyInsertActions();
        reAlignLazyEventActions();
        reAlignNotificationActions();
    }

    public List<LazyRuleRefType2> splitRuleRef(RuleRefType ruleRef) {
        List<LazyRuleRefType2> listLazy = new ArrayList<LazyRuleRefType2>();
        int count = 0;
        for (EmailActionType ea : ruleRef.getEmailAction()) {
            LazyRuleRefType2 rr = new LazyRuleRefType2();
            rr.setOID(ruleRef.getOID()+"_"+count);
            rr.getEmailAction().add(ea);
            count++;
            listLazy.add(rr);
        }
        for (DiscrepancyNoteActionType ea : ruleRef.getDiscrepancyNoteAction()) {
            LazyRuleRefType2 rr = new LazyRuleRefType2();
            rr.setOID(ruleRef.getOID()+"_"+count);
            rr.getDiscrepancyNoteAction().add(ea);
            count++;
            listLazy.add(rr);
        }
        for (ShowActionType ea : ruleRef.getShowAction()) {
            LazyRuleRefType2 rr = new LazyRuleRefType2();
            rr.setOID(ruleRef.getOID()+"_"+count);
            rr.getShowAction().add(ea);
            count++;
            listLazy.add(rr);
        }
        for (HideActionType ea : ruleRef.getHideAction()) {
            LazyRuleRefType2 rr = new LazyRuleRefType2();
            rr.setOID(ruleRef.getOID()+"_"+count);
            rr.getHideAction().add(ea);
            count++;
            listLazy.add(rr);
        }
        for (InsertActionType ea : ruleRef.getInsertAction()) {
            LazyRuleRefType2 rr = new LazyRuleRefType2();
            rr.setOID(ruleRef.getOID()+"_"+count);
            rr.getInsertAction().add(ea);
            count++;
            listLazy.add(rr);
        }
        for (EventActionType ea : ruleRef.getEventAction()) {
            LazyRuleRefType2 rr = new LazyRuleRefType2();
            rr.setOID(ruleRef.getOID()+"_"+count);
            rr.getEventAction().add(ea);
            count++;
            listLazy.add(rr);
        }
        for (NotificationActionType ea : ruleRef.getNotificationAction()) {
            LazyRuleRefType2 rr = new LazyRuleRefType2();
            rr.setOID(ruleRef.getOID()+"_"+count);
            rr.getNotificationAction().add(ea);
            count++;
            listLazy.add(rr);
        }
        return listLazy;
    }

    public List<RuleDefType> splitRuleDef(RuleDefType ruleDef, int size) {
        List<RuleDefType> listRdt = new ArrayList<RuleDefType>();
        for (int i = 0; i < size; i++) {
            RuleDefType rdt = new RuleDefType();
            rdt.setOID(ruleDef.getOID()+"_"+i);
            rdt.setExpression(ruleDef.getExpression());
            rdt.setName(ruleDef.getOID()+"_"+i);
            rdt.setDescription(ruleDef.getOID()+"_"+i);
            listRdt.add(rdt);
        }
        return listRdt;
    }

    /**
     * Based on the rule's result check if any action will fire
     * @param result
     * @return Boolean
     */
    public Boolean willAnyActionFire(String ruleExpressionResult) {
        for (DiscrepancyNoteActionType discrepancyNoteActionType : discrepancyNoteAction) {
            if (discrepancyNoteActionType.getIfExpressionEvaluates().equals(ruleExpressionResult)) {
                return true;
            }
        }
        for (EmailActionType emailActionType : emailAction) {
            if (emailActionType.getIfExpressionEvaluates().equals(ruleExpressionResult)) {
                return true;
            }
        }
        for (ShowActionType showActionType : showAction) {
            if (showActionType.getIfExpressionEvaluates().equals(ruleExpressionResult)) {
                return true;
            }
        }
        for (HideActionType hideActionType : hideAction) {
            if (hideActionType.getIfExpressionEvaluates().equals(ruleExpressionResult)) {
                return true;
            }
        }
        for (InsertActionType insertActionType : insertAction) {
            if (insertActionType.getIfExpressionEvaluates().equals(ruleExpressionResult)) {
                return true;
            }
        }
        for (EventActionType eventActionType : eventAction) {
            if (eventActionType.getIfExpressionEvaluates().equals(ruleExpressionResult)) {
                return true;
            }
        }
        for (NotificationActionType notificationActionType : notificationAction) {
            if (notificationActionType.getIfExpressionEvaluates().equals(ruleExpressionResult)) {
                return true;
            }
        }
        return false;
    }

    private void emailActionToLazyEmailAction(List<EmailActionType> emailAction) {
        Iterator<EmailActionType> emailActionsIterator = emailAction.iterator();

        while (emailActionsIterator.hasNext()) {
            EmailActionType emailActionType = emailActionsIterator.next();
            getLazyEmailActions().add(emailActionType);
        }
    }

    private void discrepancyNoteActionToLazyDiscrepancyNoteAction(List<DiscrepancyNoteActionType> discrepancyNoteAction) {
        Iterator<DiscrepancyNoteActionType> discrepancyNoteActionsIterator = discrepancyNoteAction.iterator();

        while (discrepancyNoteActionsIterator.hasNext()) {
            DiscrepancyNoteActionType discrepancyNoteActionType = discrepancyNoteActionsIterator.next();
            getLazyDiscrepancyNoteActions().add(discrepancyNoteActionType);
        }
    }

    private void showActionToLazyShowAction(List<ShowActionType> showAction) {
        Iterator<ShowActionType> showActionsIterator = showAction.iterator();

        while (showActionsIterator.hasNext()) {
            ShowActionType showActionType = showActionsIterator.next();
            LazyShowActionType lazyShowActionType = new LazyShowActionType(showActionType);
            getLazyShowActions().add(lazyShowActionType);
        }
    }

    private void hideActionToLazyHideAction(List<HideActionType> hideAction) {
        Iterator<HideActionType> hideActionsIterator = hideAction.iterator();

        while (hideActionsIterator.hasNext()) {
            HideActionType hideActionType = hideActionsIterator.next();
            LazyHideActionType lazyHideActionType = new LazyHideActionType(hideActionType);
            getLazyHideActions().add(lazyHideActionType);
        }
    }

    private void insertActionToLazyInsertAction(List<InsertActionType> insertAction) {
        Iterator<InsertActionType> insertActionsIterator = insertAction.iterator();

        while (insertActionsIterator.hasNext()) {
            InsertActionType insertActionType = insertActionsIterator.next();
            LazyInsertActionType lazyInsertActionType = new LazyInsertActionType(insertActionType);
            getLazyInsertActions().add(lazyInsertActionType);
        }
    }

    private void eventActionToLazyEventAction(List<EventActionType> eventAction) {
        Iterator<EventActionType> eventActionsIterator = eventAction.iterator();

        while (eventActionsIterator.hasNext()) {
            EventActionType eventActionType = eventActionsIterator.next();
            LazyEventActionType lazyEventActionType = new LazyEventActionType(eventActionType);
            getLazyEventActions().add(lazyEventActionType);
        }
    }

    private void notificationActionToLazyNotificationAction(List<NotificationActionType> notificationAction) {
        Iterator<NotificationActionType> notificationActionsIterator = notificationAction.iterator();

        while (notificationActionsIterator.hasNext()) {
            NotificationActionType notificationActionType = notificationActionsIterator.next();
            getLazyNotificationActions().add(notificationActionType);
        }
    }

    private void reAlignEmailActions() {
        Iterator<EmailActionType> emailActionsIterator = getLazyEmailActions().iterator();

        while (emailActionsIterator.hasNext()) {
            EmailActionType emailActionType = emailActionsIterator.next();
            if (emailActionType.getIfExpressionEvaluates() == null) {
                emailActionsIterator.remove();
            }
        }
    }

    private void reAlignDiscrepancyNoteActions() {
        Iterator<DiscrepancyNoteActionType> discrepancyNoteActionsIterator = getLazyDiscrepancyNoteActions().iterator();

        while (discrepancyNoteActionsIterator.hasNext()) {
            DiscrepancyNoteActionType dnType = discrepancyNoteActionsIterator.next();
            if (dnType.getIfExpressionEvaluates() == null) {
                discrepancyNoteActionsIterator.remove();
            }
        }
    }

    private void reAlignLazyShowActions() {
        Iterator<LazyShowActionType> lazyShowActionsIterator = getLazyShowActions().iterator();
        getShowAction().clear();

        while (lazyShowActionsIterator.hasNext()) {
            LazyShowActionType showActionType = lazyShowActionsIterator.next();
            Iterator<LazyPropertyType> lazyPropertyTypeIterator = showActionType.getLazyProperties().iterator();
            while (lazyPropertyTypeIterator.hasNext()) {
                LazyPropertyType lazyPropertyType = lazyPropertyTypeIterator.next();
                if (lazyPropertyType.getPlaceHolder() == null) {
                    lazyPropertyTypeIterator.remove();
                }
            }
            if (showActionType.getIfExpressionEvaluates() == null) {
                lazyShowActionsIterator.remove();
            } else {
                showActionType.getDestinationProperty().clear();
                showActionType.getDestinationProperty().addAll(showActionType.getLazyProperties());
                getShowAction().add(showActionType);
            }
        }
    }

    private void reAlignLazyHideActions() {
        Iterator<LazyHideActionType> lazyHideActionsIterator = getLazyHideActions().iterator();
        getHideAction().clear();

        while (lazyHideActionsIterator.hasNext()) {
            LazyHideActionType hideActionType = lazyHideActionsIterator.next();
            Iterator<LazyPropertyType> lazyPropertyTypeIterator = hideActionType.getLazyProperties().iterator();
            while (lazyPropertyTypeIterator.hasNext()) {
                LazyPropertyType lazyPropertyType = lazyPropertyTypeIterator.next();
                if (lazyPropertyType.getPlaceHolder() == null) {
                    lazyPropertyTypeIterator.remove();
                }
            }
            if (hideActionType.getIfExpressionEvaluates() == null) {
                lazyHideActionsIterator.remove();
            } else {
                hideActionType.getDestinationProperty().clear();
                hideActionType.getDestinationProperty().addAll(hideActionType.getLazyProperties());
                getHideAction().add(hideActionType);
            }
        }
    }

    private void reAlignLazyInsertActions() {
        Iterator<LazyInsertActionType> lazyInsertActionsIterator = getLazyInsertActions().iterator();
        getInsertAction().clear();

        while (lazyInsertActionsIterator.hasNext()) {
            LazyInsertActionType insertActionType = lazyInsertActionsIterator.next();
            Iterator<LazyPropertyType> lazyPropertyTypeIterator = insertActionType.getLazyProperties().iterator();
            while (lazyPropertyTypeIterator.hasNext()) {
                LazyPropertyType lazyPropertyType = lazyPropertyTypeIterator.next();
                if (lazyPropertyType.getPlaceHolder() == null) {
                    lazyPropertyTypeIterator.remove();
                }
            }
            if (insertActionType.getIfExpressionEvaluates() == null) {
                lazyInsertActionsIterator.remove();
            } else {
                insertActionType.getDestinationProperty().clear();
                insertActionType.getDestinationProperty().addAll(insertActionType.getLazyProperties());
                getInsertAction().add(insertActionType);
            }
        }
    }

    private void reAlignLazyEventActions() {
        Iterator<LazyEventActionType> lazyEventActionsIterator = getLazyEventActions().iterator();
        getEventAction().clear();

        while (lazyEventActionsIterator.hasNext()) {
            LazyEventActionType eventActionType = lazyEventActionsIterator.next();
            Iterator<LazyEventDestinationType> lazyEventDestinationTypeIterator = eventActionType.getLazyProperties().iterator();
            while (lazyEventDestinationTypeIterator.hasNext()) {
                LazyEventDestinationType lazyEventDestinationType = lazyEventDestinationTypeIterator.next();
                if (lazyEventDestinationType.getPlaceHolder() == null) {
                    lazyEventDestinationTypeIterator.remove();
                }
            }
            if (eventActionType.getIfExpressionEvaluates() == null) {
                lazyEventActionsIterator.remove();
            } else {
                eventActionType.getEventDestination().clear();
                eventActionType.getEventDestination().addAll(eventActionType.getLazyProperties());
                getEventAction().add(eventActionType);
            }
        }
    }

    private void reAlignNotificationActions() {
        Iterator<NotificationActionType> notificationActionsIterator = getLazyNotificationActions().iterator();

        while (notificationActionsIterator.hasNext()) {
            NotificationActionType notificationActionType = notificationActionsIterator.next();
            if (notificationActionType.getIfExpressionEvaluates() == null) {
                notificationActionsIterator.remove();
            }
        }
    }

    @Transient
    public void formToModel() {
        showAction.addAll(lazyShowActions);
        hideAction.addAll(lazyHideActions);
        insertAction.addAll(lazyInsertActions);
        eventAction.addAll(lazyEventActions);
        notificationAction.addAll(lazyNotificationActions);
    }

    public AutoPopulatingList<DiscrepancyNoteActionType> getLazyDiscrepancyNoteActions() {
        return lazyDiscrepancyNoteActions;
    }

    public void setLazyDiscrepancyNoteActions(AutoPopulatingList<DiscrepancyNoteActionType> lazyDiscrepancyNoteActions) {
        this.lazyDiscrepancyNoteActions = lazyDiscrepancyNoteActions;
    }

    public AutoPopulatingList<EmailActionType> getLazyEmailActions() {
        return lazyEmailActions;
    }

    public void setLazyEmailActions(AutoPopulatingList<EmailActionType> lazyEmailActions) {
        this.lazyEmailActions = lazyEmailActions;
    }

    public AutoPopulatingList<LazyShowActionType> getLazyShowActions() {
        return lazyShowActions;
    }

    public void setLazyShowActions(AutoPopulatingList<LazyShowActionType> lazyShowActions) {
        this.lazyShowActions = lazyShowActions;
    }

    public AutoPopulatingList<LazyHideActionType> getLazyHideActions() {
        return lazyHideActions;
    }

    public void setLazyHideActions(AutoPopulatingList<LazyHideActionType> lazyHideActions) {
        this.lazyHideActions = lazyHideActions;
    }

    public AutoPopulatingList<LazyInsertActionType> getLazyInsertActions() {
        return lazyInsertActions;
    }

    public void setLazyInsertActions(AutoPopulatingList<LazyInsertActionType> lazyInsertActions) {
        this.lazyInsertActions = lazyInsertActions;
    }

    public AutoPopulatingList<LazyEventActionType> getLazyEventActions() {
        return lazyEventActions;
    }

    public void setLazyEventActions(AutoPopulatingList<LazyEventActionType> lazyEventActions) {
        this.lazyEventActions = lazyEventActions;
    }

    public AutoPopulatingList<NotificationActionType> getLazyNotificationActions() {
        return lazyNotificationActions;
    }

    public void setLazyNotificationActions(AutoPopulatingList<NotificationActionType> lazyNotificationActions) {
        this.lazyNotificationActions = lazyNotificationActions;
    }

    /*
     * private List<DiscrepancyNoteActionType> lazyDiscrepancyNoteActions = LazyList.decorate(new
     * ArrayList<DiscrepancyNoteActionType>(), FactoryUtils.instantiateFactory(DiscrepancyNoteActionType.class)); private
     * List<EmailActionType> lazyEmailActions = LazyList .decorate(new ArrayList<EmailActionType>(),
     * FactoryUtils.instantiateFactory(EmailActionType.class)); private List<LazyShowActionType> lazyShowActions =
     * LazyList.decorate(new ArrayList<LazyShowActionType>(), FactoryUtils.instantiateFactory(LazyShowActionType.class));
     * private List<LazyHideActionType> lazyHideActions = LazyList.decorate(new ArrayList<LazyHideActionType>(),
     * FactoryUtils.instantiateFactory(LazyHideActionType.class)); private List<LazyInsertActionType> lazyInsertActions =
     * LazyList.decorate(new ArrayList<LazyInsertActionType>(), FactoryUtils.instantiateFactory(LazyInsertActionType.class));
     * public List<DiscrepancyNoteActionType> getLazyDiscrepancyNoteActions() { return lazyDiscrepancyNoteActions; } public
     * void setLazyDiscrepancyNoteActions(List<DiscrepancyNoteActionType> lazyDiscrepancyNoteActions) {
     * this.lazyDiscrepancyNoteActions = lazyDiscrepancyNoteActions; } public List<EmailActionType> getLazyEmailActions() {
     * return lazyEmailActions; } public void setLazyEmailActions(List<EmailActionType> lazyEmailActions) {
     * this.lazyEmailActions = lazyEmailActions; } public List<LazyShowActionType> getLazyShowActions() { return
     * lazyShowActions; } public void setLazyShowActions(List<LazyShowActionType> lazyShowActions) { this.lazyShowActions =
     * lazyShowActions; } public List<LazyHideActionType> getLazyHideActions() { return lazyHideActions; } public void
     * setLazyHideActions(List<LazyHideActionType> lazyHideActions) { this.lazyHideActions = lazyHideActions; } public
     * List<LazyInsertActionType> getLazyInsertActions() { return lazyInsertActions; } public void
     * setLazyInsertActions(List<LazyInsertActionType> lazyInsertActions) { this.lazyInsertActions = lazyInsertActions; }
     */
}
