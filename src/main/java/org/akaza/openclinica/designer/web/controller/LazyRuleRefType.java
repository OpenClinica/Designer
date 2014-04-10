package org.akaza.openclinica.designer.web.controller;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;
import org.openclinica.ns.rules.v31.DiscrepancyNoteActionType;
import org.openclinica.ns.rules.v31.EmailActionType;
import org.openclinica.ns.rules.v31.RuleRefType;

import java.util.ArrayList;
import java.util.List;

public class LazyRuleRefType extends RuleRefType {
    
        
        private   List<DiscrepancyNoteActionType> lazyDiscrepancyNoteActions = LazyList.decorate(new ArrayList<DiscrepancyNoteActionType>(),
                FactoryUtils.instantiateFactory(DiscrepancyNoteActionType.class));
        private   List<EmailActionType> lazyEmailActions = LazyList
                .decorate(new ArrayList<EmailActionType>(), FactoryUtils.instantiateFactory(EmailActionType.class));
        private   List<LazyShowActionType> lazyShowActions = LazyList.decorate(new ArrayList<LazyShowActionType>(), FactoryUtils.instantiateFactory(LazyShowActionType.class));
        private   List<LazyHideActionType> lazyHideActions = LazyList.decorate(new ArrayList<LazyHideActionType>(), FactoryUtils.instantiateFactory(LazyHideActionType.class));    
        private   List<LazyInsertActionType> lazyInsertActions = LazyList.decorate(new ArrayList<LazyInsertActionType>(),
                FactoryUtils.instantiateFactory(LazyInsertActionType.class));
        
        public List<DiscrepancyNoteActionType> getLazyDiscrepancyNoteActions() {
            return lazyDiscrepancyNoteActions;
        }
        public void setLazyDiscrepancyNoteActions(List<DiscrepancyNoteActionType> lazyDiscrepancyNoteActions) {
            this.lazyDiscrepancyNoteActions = lazyDiscrepancyNoteActions;
        }
        public List<EmailActionType> getLazyEmailActions() {
            return lazyEmailActions;
        }
        public void setLazyEmailActions(List<EmailActionType> lazyEmailActions) {
            this.lazyEmailActions = lazyEmailActions;
        }
        public List<LazyShowActionType> getLazyShowActions() {
            return lazyShowActions;
        }
        public void setLazyShowActions(List<LazyShowActionType> lazyShowActions) {
            this.lazyShowActions = lazyShowActions;
        }
        public List<LazyHideActionType> getLazyHideActions() {
            return lazyHideActions;
        }
        public void setLazyHideActions(List<LazyHideActionType> lazyHideActions) {
            this.lazyHideActions = lazyHideActions;
        }
        public List<LazyInsertActionType> getLazyInsertActions() {
            return lazyInsertActions;
        }
        public void setLazyInsertActions(List<LazyInsertActionType> lazyInsertActions) {
            this.lazyInsertActions = lazyInsertActions;
        }
    }
