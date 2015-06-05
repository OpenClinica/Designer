<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="dFields" tagdir="/WEB-INF/tags/designer/form/fields" %>
<%@ taglib prefix="designerTags" tagdir="/WEB-INF/tags/designer" %>


<div id="kkforms">
<style>
#navAccount{position:relative}
#navAccount ul{background:#fff;border:1px solid #333;border-bottom:2px solid #2d4486;display:none;margin-right:-1px;margin-top:-1px;min-width:300px;padding:10px 0 5px;position:absolute;right:0;_right:-1px;top:100%;*width:100%;_width:200px;z-index:1}
#navAccount.openToggler ul,
.no_js #navAccount:hover ul{display:block}
#navAccount li{display:block;float:none}
    
    
    #target.value.errors { display:block; margin-left: 100px; }
    #ruleDef.OID {color: orange;}
    
    
    /*
    p label,input, span{
        display:block;
    }
    */
</style>

<div class="box">
<div class="block" id="forms">

<form:form modelAttribute="rulesCommand" action="ruleBuilder/ruleBuilderFormA" method="post">

    <fmt:message key="tooltip_rule_oid" var="tooltipRuleOid"/>
    <fmt:message key="tooltip_target" var="tooltipTarget"/>
    <fmt:message key="tooltip_expression" var="tooltipExpression"/>
    <fmt:message key="tooltip_actions" var="tooltipActions"/>
    <fmt:message key="tooltip_expression_evaluates_to" var="toolExpressionEvaluatesTo"/>
    <fmt:message key="tooltip_message" var="tooltipMessage"/>
    <fmt:message key="tooltip_to" var="tooltipTo"/>
    <fmt:message key="tooltip_apply_on_show" var="tooltipApplyOnShow"/>
    <fmt:message key="tooltip_apply_on_hide" var="tooltipApplyOnHide"/>
    <fmt:message key="tooltip_apply_on_insert" var="tooltipApplyOnInsert"/>
    <fmt:message key="tooltip_apply_on_value" var="tooltipApplyOnValue"/>
    <fmt:message key="tooltip_apply_on_expression" var="tooltipApplyOnExpression"/>
    <fmt:message key="tooltip_event_apply_to" var="tooltipEventApplyTo"/>
    <fmt:message key="tooltip_event_expression" var="tooltipEventExpression"/>
    <fmt:message key="tooltip_event_property" var="tooltipEventProperty"/>
     
    <span class="topButtons">
    <button id="validateButton1" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">Validate</span></button>
    <button id="saveButton1" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">Save</span></button>
    </span>
    <designerTags:renderPageMessages/>

    
    <fieldset class="noBorder login">
    <input id="highlightedTextInWYSIWYG" name="highlightedTextInWYSIWYG" type="hidden"/>
    <p id="targetWYSIWYG">
        <dFields:textarea path="target.value" imputCSSErrorClass="errorBorder" labelNameKey="label_target" labelCSSErrorClass="errorTextColor" title="This will show up in the TipTip popup." rows="1" cols="100%"/>
        <%-- FOR DEBUGGING 
        <c:out value="${rulesCommand.target.value}"/>
        <c:out value="${rulesCommand.targetCurated.value}"/>
         --%>
    </p>
    
    <p>
        <label>
            <fmt:message key="label_run_on_a_shedule"/>:
        </label>
        <img id="runOnButton" src="includes/img/icon_run_off.png" alt="run on a schedule button" width="70px" height="30px" style="cursor: pointer; float: none;" />
        <table id="showRunTimeTable" style="width: 425px; border: white; margin-bottom: 3px; display: none;">
            <tr>
                <td style="border: white; background: white; vertical-align: middle; padding-right: 0px;">
                    <fmt:message key="label_run_time"/>:
                </td>
                <td style="border: white; background: white; vertical-align: middle; padding-left: 5px; padding-right: 0px;">
                    <form:select path="runOnSchedule.time" id="runOnScheduleID" multiple="false" cssErrorClass="errorBorder" style="border-radius: 5px; height: 23px; width: 86px; background: url(./includes/img/arrowUpDown.png) no-repeat 0 0; background-position: 62px -2px; -moz-apprearance: none; -webkit-appearance: none; text-indent: 0.01px; text-overflow: ''; padding-left: 5px;">
                        <form:option value="">--select--</form:option>
                        <form:option value="01.00"/>
                        <form:option value="02.00"/>
                        <form:option value="03.00"/>
                        <form:option value="04.00"/>
                        <form:option value="05.00"/>
                        <form:option value="06.00"/>
                        <form:option value="07.00"/>
                        <form:option value="08.00"/>
                        <form:option value="09.00"/>
                        <form:option value="10.00"/>
                        <form:option value="11.00"/>
                        <form:option value="12.00"/>
                        <form:option value="13.00"/>
                        <form:option value="14.00"/>
                        <form:option value="15.00"/>
                        <form:option value="16.00"/>
                        <form:option value="17.00"/>
                        <form:option value="18.00"/>
                        <form:option value="19.00"/>
                        <form:option value="20.00"/>
                        <form:option value="21.00"/>
                        <form:option value="22.00"/>
                        <form:option value="23.00"/>
                        <form:option value="24.00"/>
                    </form:select>
                </td>
                <td style="border: white; background: white; vertical-align: middle; padding-left: 5px;">
                    <fmt:message key="run_time_format"/>
                </td>
            </tr>
        </table>
    </p>

    <p>
        <c:choose>
            <c:when test="${sessionScope['scopedTarget.userPreferences'].editMode == true }">
                <dFields:inputReadOnly path="ruleDef.OID" value="${rulesCommand.ruleDef.OID}" imputCSSErrorClass="errorBorder" labelNameKey="label_rule_oid" labelCSSErrorClass="errorTextColor" title=""/>
            </c:when>
            <c:otherwise>
                <dFields:input path="ruleDef.OID" imputCSSErrorClass="errorBorder" labelNameKey="label_rule_oid" labelCSSErrorClass="errorTextColor" title="${tooltipRuleOid}"/>
            </c:otherwise>
        </c:choose>
    </p>
    <p id="headerExp" style="margin-bottom: -18px; display: none;">
        <img src="includes/img/header_operator.png" style="width: 300px; padding-left: 99px;">
    </p>
    <p id="ruleExpressionWYSIWYG">
        <dFields:textarea path="ruleDef.expression.value" imputCSSErrorClass="errorBorder" labelNameKey="label_rule_expression" labelCSSErrorClass="errorTextColor" title="This will show up in the TipTip popup." rows="5" cols="100%"/>
        <%-- FOR DEBUGGING
        <c:out value="${rulesCommand.ruleDef.expression.value}"/>
        <c:out value="${rulesCommand.ruleDefCurated.expression.value}"/>
        --%>   
    </p>   
    <p>
        <form:label path="addActions" cssErrorClass="errorTextColor" id="actionsLabel"><fmt:message key="label_actions"/>:</form:label>
        <form:select path="addActions" id="addActions" multiple="false" cssErrorClass="errorBorder" title="${tooltipActions}" style="border-radius: 5px; height: 23px; width: 140px; background: url(./includes/img/arrowUpDown.png) no-repeat 0 0; background-position: 116px -2px; -moz-apprearance: none; -webkit-appearance: none; text-indent: 0.01px; text-overflow: '';">
            <form:option  value="add"><fmt:message key="label_select_one"/> </form:option>
            <form:option value="addDnAction"><fmt:message key="label_dis_note_action"/> </form:option>
            <form:option value="addEmailAction"><fmt:message key="label_email_action"/> </form:option>
            <form:option value="addShowAction"><fmt:message key="label_show_action"/> </form:option>
            <form:option value="addHideAction"><fmt:message key="label_hide_action"/> </form:option>
            <form:option value="addInsertAction"><fmt:message key="label_insert_action"/> </form:option>
            <form:option value="addEventAction"><fmt:message key="label_event_action"/> </form:option>
            <form:option value="addNotificationAction"><fmt:message key="label_notification_action"/> </form:option>
        </form:select>
        <form:errors path="addActions" cssClass="errorTextColor"/>
    </p>
    </fieldset>
    
    <div id="ruleActions">
        <!-- discrepancyNote Actions -->
        <c:forEach items="${rulesCommand.ruleRef.lazyDiscrepancyNoteActions}" varStatus="gridRow">
            <span id="lazyDiscrepancyNoteActions${gridRow.index}">
            <fieldset class="login">
            <legend><span><fmt:message key="label_dis_note_action"/> <a id="removeDnAction" href="#" onclick="$('#lazyDiscrepancyNoteActions${gridRow.index}').remove();"><img title="Remove" alt="Remove" src="images/bt_Remove.gif" name="bt_Remove1"></a></span></legend>
            <p>
                <form:label id="ruleRef.lazyDiscrepancyNoteActions${gridRow.index}.ifExpressionEvaluatesLabel" for="ruleRef.lazyDiscrepancyNoteActions[${gridRow.index}].ifExpressionEvaluates" path="ruleRef.lazyDiscrepancyNoteActions[${gridRow.index}].ifExpressionEvaluates" cssErrorClass="error"></form:label> 
                <form:select path="ruleRef.lazyDiscrepancyNoteActions[${gridRow.index}].ifExpressionEvaluates" title="${toolExpressionEvaluatesTo }" style="border-radius: 5px; height: 23px; width: 60px; background: url(./includes/img/arrowUpDown.png) no-repeat 0 0; background-position: 36px -2px; -moz-apprearance: none; -webkit-appearance: none; text-indent: 0.01px; text-overflow: '';">
                  <form:option value="true"/>
                  <form:option value="false"/>
                </form:select>
                <form:errors path="ruleRef.lazyDiscrepancyNoteActions[${gridRow.index}].ifExpressionEvaluates" cssClass="error" />
            </p>
            <p>
                <form:label id="ruleRef.lazyDiscrepancyNoteActions${gridRow.index}.messageLabel" for="ruleRef.lazyDiscrepancyNoteActions[${gridRow.index}].message" path="ruleRef.lazyDiscrepancyNoteActions[${gridRow.index}].message" cssErrorClass="errorTextColor"><fmt:message key="label_message"/>: </form:label>
                <form:textarea path="ruleRef.lazyDiscrepancyNoteActions[${gridRow.index}].message" rows="3" cols="50" cssErrorClass="errorBorder" title="${tooltipMessage }" />
                <form:errors path="ruleRef.lazyDiscrepancyNoteActions[${gridRow.index}].message" cssClass="errorTextColor" />
            </p>
            <designerTags:renderActionRunStatusTable pathPrefix="ruleRef.lazyDiscrepancyNoteActions[${gridRow.index}]"/>
            </fieldset>
            </span>
        </c:forEach>
        
        <!-- email Actions -->
        <c:forEach items="${rulesCommand.ruleRef.lazyEmailActions}" varStatus="gridRow">
            <span id="lazyEmailActions${gridRow.index}">
            <fieldset class="login">
            <legend><span><fmt:message key="label_email_action"/> <a id="removeEmailAction" href="#" onclick="$('#lazyEmailActions${gridRow.index}').remove();"><img title="Remove" alt="Remove" src="images/bt_Remove.gif" name="bt_Remove1"></a></span></legend>
            
            <p>
                <form:label id="ruleRef.lazyEmailActions${gridRow.index}.ifExpressionEvaluatesLabel" for="ruleRef.lazyEmailActions[${gridRow.index}].ifExpressionEvaluates" path="ruleRef.lazyEmailActions[${gridRow.index}].ifExpressionEvaluates" cssErrorClass="error"><fmt:message key="label_evaluates_to"/>: </form:label> 
                <form:select path="ruleRef.lazyEmailActions[${gridRow.index}].ifExpressionEvaluates" title="${toolExpressionEvaluatesTo }" style="border-radius: 5px; height: 23px; width: 60px; background: url(./includes/img/arrowUpDown.png) no-repeat 0 0; background-position: 36px -2px; -moz-apprearance: none; -webkit-appearance: none; text-indent: 0.01px; text-overflow: '';">
                  <form:option value="true"/>
                  <form:option value="false"/>
                </form:select>
                <form:errors path="ruleRef.lazyEmailActions[${gridRow.index}].ifExpressionEvaluates" cssClass="error" />
            </p>
            <p>
                <form:label id="ruleRef.lazyEmailActions${gridRow.index}.toLabel" for="ruleRef.lazyEmailActions[${gridRow.index}].to" path="ruleRef.lazyEmailActions[${gridRow.index}].to" cssErrorClass="errorTextColor"><fmt:message key="label_to"/>: </form:label>
                <form:input path="ruleRef.lazyEmailActions[${gridRow.index}].to" cssErrorClass="errorBorder" title="${tooltipTo}" />
                <form:errors path="ruleRef.lazyEmailActions[${gridRow.index}].to" cssClass="errorTextColor"/>
            </p>
            <p>
                <form:label id="ruleRef.lazyEmailActions${gridRow.index}.messageLabel" for="ruleRef.lazyEmailActions[${gridRow.index}].message" path="ruleRef.lazyEmailActions[${gridRow.index}].message" cssErrorClass="errorTextColor"><fmt:message key="label_message"/>: </form:label>
                <form:textarea path="ruleRef.lazyEmailActions[${gridRow.index}].message" rows="3" cols="50" cssErrorClass="errorBorder" title="${tooltipMessage }"  />
                <form:errors path="ruleRef.lazyEmailActions[${gridRow.index}].message" cssClass="errorTextColor" />
            </p>
            <designerTags:renderActionRunStatusTable pathPrefix="ruleRef.lazyEmailActions[${gridRow.index}]"/>
            </fieldset>
            </span>
        </c:forEach>
        
        <!-- show Actions -->
        <c:forEach items="${rulesCommand.ruleRef.lazyShowActions}" var="showAction" varStatus="gridRow">
            <span id="lazyShowActions${gridRow.index}">
            <fieldset class="login">
            <legend><span><fmt:message key="label_show_action"/> <a id="removeShowAction" href="#" onclick="$('#lazyShowActions${gridRow.index}').remove();"><img title="Remove" alt="Remove" src="images/bt_Remove.gif" name="bt_Remove1"></a></span></legend>
            
            <p>
                <form:label id="ruleRef.lazyShowActions${gridRow.index}.ifExpressionEvaluatesLabel" for="ruleRef.lazyShowActions[${gridRow.index}].ifExpressionEvaluates" path="ruleRef.lazyShowActions[${gridRow.index}].ifExpressionEvaluates" cssErrorClass="error"><fmt:message key="label_evaluates_to"/>: </form:label>
                <form:select path="ruleRef.lazyShowActions[${gridRow.index}].ifExpressionEvaluates" title="${toolExpressionEvaluatesTo }" style="border-radius: 5px; height: 23px; width: 60px; background: url(./includes/img/arrowUpDown.png) no-repeat 0 0; background-position: 36px -2px; -moz-apprearance: none; -webkit-appearance: none; text-indent: 0.01px; text-overflow: '';">
                  <form:option value="true"/>
                  <form:option value="false"/>
                </form:select>
                <form:errors path="ruleRef.lazyShowActions[${gridRow.index}].ifExpressionEvaluates" cssClass="error" />
            </p>
            <p>
                <form:label id="ruleRef.lazyShowActions${gridRow.index}.messageLabel" for="ruleRef.lazyShowActions[${gridRow.index}].message" path="ruleRef.lazyShowActions[${gridRow.index}].message" cssErrorClass="errorTextColor"><fmt:message key="label_message"/>: </form:label>
                <form:textarea path="ruleRef.lazyShowActions[${gridRow.index}].message" rows="3" cols="50" cssErrorClass="errorBorder" title="${tooltipMessage }"  />
                <form:errors path="ruleRef.lazyShowActions[${gridRow.index}].message" cssClass="errorTextColor" />
            </p>
            <p>
                <form:label path="ruleRef.lazyShowActions[${gridRow.index}].lazyProperties" cssErrorClass="errorTextColor">Apply on:</form:label>
                <a href="#" onClick="addProperty('ruleRef.lazyShowActions${gridRow.index}','ruleRef.lazyShowActions[${gridRow.index}]')"><img border="0" src="images/create_new.gif"></a>
                <form:errors path="ruleRef.lazyShowActions[${gridRow.index}].lazyProperties" cssClass="errorTextColor"/>
            </p>
            <span id="ruleRef.lazyShowActions${gridRow.index}.propertiesContainer">
                <c:forEach items="${showAction.lazyProperties}" varStatus="gridRow2">
                <span id="ruleRef.lazyShowActions${gridRow.index}.lazyProperties${gridRow2.index}">
                <p>
                    <label>&nbsp;</label>
                    <form:hidden path="ruleRef.lazyShowActions[${gridRow.index}].lazyProperties[${gridRow2.index}].placeHolder"/>
                    <form:input path="ruleRef.lazyShowActions[${gridRow.index}].lazyProperties[${gridRow2.index}].OID" cssErrorClass="errorBorder" title="${tooltipApplyOnShow}"/>
                    <a id="removeLazyProperties" href="#" onclick="$('#ruleRef\\.lazyShowActions${gridRow.index}\\.lazyProperties${gridRow2.index}').remove();"><img title="Remove" alt="Remove" src="images/bt_Remove.gif" name="bt_Remove1"></a>
                    <form:errors path="ruleRef.lazyShowActions[${gridRow.index}].lazyProperties[${gridRow2.index}].OID" cssClass="errorTextColor alignclass" />
                </p>
                </span>
                </c:forEach>
            </span>
            <designerTags:renderActionRunStatusTable pathPrefix="ruleRef.lazyShowActions[${gridRow.index}]"/>
            </fieldset>
            </span>
        </c:forEach>
        
        <!-- hide Actions -->
        <c:forEach items="${rulesCommand.ruleRef.lazyHideActions}" var="hideAction" varStatus="gridRow">
            <span id="lazyHideActions${gridRow.index}">
            <fieldset class="login">
            <legend><span><fmt:message key="label_hide_action"/> <a id="removeHideAction" href="#" onclick="$('#lazyHideActions${gridRow.index}').remove();"><img title="Remove" alt="Remove" src="images/bt_Remove.gif" name="bt_Remove1"></a></span></legend>
            
            <p>
                <form:label id="ruleRef.lazyHideActions${gridRow.index}.ifExpressionEvaluatesLabel" for="ruleRef.lazyHideActions[${gridRow.index}].ifExpressionEvaluates" path="ruleRef.lazyHideActions[${gridRow.index}].ifExpressionEvaluates" cssErrorClass="error"><fmt:message key="label_evaluates_to"/>: </form:label>
                <form:select path="ruleRef.lazyHideActions[${gridRow.index}].ifExpressionEvaluates" title="${toolExpressionEvaluatesTo }" style="border-radius: 5px; height: 23px; width: 60px; background: url(./includes/img/arrowUpDown.png) no-repeat 0 0; background-position: 36px -2px; -moz-apprearance: none; -webkit-appearance: none; text-indent: 0.01px; text-overflow: '';">
                  <form:option value="true"/>
                  <form:option value="false"/>
                </form:select>
                <form:errors path="ruleRef.lazyHideActions[${gridRow.index}].ifExpressionEvaluates" cssClass="error" />
            </p>
            <p>
                <form:label id="ruleRef.lazyHideActions${gridRow.index}.messageLabel" for="ruleRef.lazyHideActions[${gridRow.index}].message" path="ruleRef.lazyHideActions[${gridRow.index}].message" cssErrorClass="errorTextColor"><fmt:message key="label_message"/>: </form:label>
                <form:textarea path="ruleRef.lazyHideActions[${gridRow.index}].message" rows="3" cols="50" cssErrorClass="errorBorder" title="${tooltipMessage }"  />
                <form:errors path="ruleRef.lazyHideActions[${gridRow.index}].message" cssClass="errorTextColor" />
            </p>
            <p>
                <form:label path="ruleRef.lazyHideActions[${gridRow.index}].lazyProperties" cssErrorClass="errorTextColor" >Apply on:</form:label>
                <a href="#" onClick="addProperty('ruleRef.lazyHideActions${gridRow.index}','ruleRef.lazyHideActions[${gridRow.index}]')"><img border="0" src="images/create_new.gif"></a>
                <form:errors path="ruleRef.lazyHideActions[${gridRow.index}].lazyProperties" cssClass="errorTextColor"/>
            </p>
            <span id="ruleRef.lazyHideActions${gridRow.index}.propertiesContainer">
                <c:forEach items="${hideAction.lazyProperties}" varStatus="gridRow2">
                <span id="ruleRef.lazyHideActions${gridRow.index}.lazyProperties${gridRow2.index}">
                <p>
                    <label>&nbsp;</label>
                    <form:hidden path="ruleRef.lazyHideActions[${gridRow.index}].lazyProperties[${gridRow2.index}].placeHolder"/>
                    <form:input path="ruleRef.lazyHideActions[${gridRow.index}].lazyProperties[${gridRow2.index}].OID" cssErrorClass="errorBorder" title="${tooltipApplyOnHide}"/>
                    <a id="removeLazyProperties" href="#" onclick="$('#ruleRef\\.lazyHideActions${gridRow.index}\\.lazyProperties${gridRow2.index}').remove();"><img title="Remove" alt="Remove" src="images/bt_Remove.gif" name="bt_Remove1"></a>
                    <form:errors path="ruleRef.lazyHideActions[${gridRow.index}].lazyProperties[${gridRow2.index}].OID" cssClass="errorTextColor" />
                </p>
                </span>
                </c:forEach>
            </span>
            <designerTags:renderActionRunStatusTable pathPrefix="ruleRef.lazyHideActions[${gridRow.index}]"/>
            </fieldset>
            </span>
        </c:forEach>
        
        <!-- insert Actions -->
        <c:forEach items="${rulesCommand.ruleRef.lazyInsertActions}" var="insertAction" varStatus="gridRow">
            <span id="lazyInsertActions${gridRow.index}">
            <fieldset class="login">
            <legend><span><fmt:message key="label_insert_action"/> <a id="removeHideAction" href="#" onclick="$('#lazyInsertActions${gridRow.index}').remove();"><img title="Remove" alt="Remove" src="images/bt_Remove.gif" name="bt_Remove1"></a></span></legend>
            
            <p>
                <form:label id="ruleRef.lazyInsertActions${gridRow.index}.ifExpressionEvaluatesLabel" for="ruleRef.lazyInsertActions[${gridRow.index}].ifExpressionEvaluates" path="ruleRef.lazyInsertActions[${gridRow.index}].ifExpressionEvaluates" cssErrorClass="error"><fmt:message key="label_evaluates_to"/>: </form:label>
                <form:select path="ruleRef.lazyInsertActions[${gridRow.index}].ifExpressionEvaluates" title="${toolExpressionEvaluatesTo }" style="border-radius: 5px; height: 23px; width: 60px; background: url(./includes/img/arrowUpDown.png) no-repeat 0 0; background-position: 36px -2px; -moz-apprearance: none; -webkit-appearance: none; text-indent: 0.01px; text-overflow: '';">
                  <form:option value="true"/>
                  <form:option value="false"/>
                </form:select>
                <form:errors path="ruleRef.lazyInsertActions[${gridRow.index}].ifExpressionEvaluates" cssClass="error" />
            </p>
            <p>
                <form:label path="ruleRef.lazyInsertActions[${gridRow.index}].lazyProperties" cssErrorClass="errorTextColor">Apply on:</form:label>
                <a href="#" onClick="addProperty('ruleRef.lazyInsertActions${gridRow.index}','ruleRef.lazyInsertActions[${gridRow.index}]')"><img border="0" src="images/create_new.gif"></a>
                <form:errors path="ruleRef.lazyInsertActions[${gridRow.index}].lazyProperties" cssClass="errorTextColor"/>
            </p>
            <span id="ruleRef.lazyInsertActions${gridRow.index}.propertiesContainer">
                <c:forEach items="${insertAction.lazyProperties}" varStatus="gridRow2">
                <span id="ruleRef.lazyInsertActions${gridRow.index}.lazyProperties${gridRow2.index}">
                <p>
                    <label>&nbsp;</label>
                    <form:hidden path="ruleRef.lazyInsertActions[${gridRow.index}].lazyProperties[${gridRow2.index}].placeHolder"/>
                    <form:input path="ruleRef.lazyInsertActions[${gridRow.index}].lazyProperties[${gridRow2.index}].OID" cssErrorClass="errorBorder" title="${tooltipApplyOnInsert}"/>
                    Value: <form:input path="ruleRef.lazyInsertActions[${gridRow.index}].lazyProperties[${gridRow2.index}].value" title="${tooltipApplyOnValue}"/>
                    Or Expression: <form:input path="ruleRef.lazyInsertActions[${gridRow.index}].lazyProperties[${gridRow2.index}].valueExpression.value" title="${tooltipApplyOnExpression}"/>
                    <a id="removeLazyProperties" href="#" onclick="$('#ruleRef\\.lazyInsertActions${gridRow.index}\\.lazyProperties${gridRow2.index}').remove();"><img title="Remove" alt="Remove" src="images/bt_Remove.gif" name="bt_Remove1"></a>
                    <form:errors path="ruleRef.lazyInsertActions[${gridRow.index}].lazyProperties[${gridRow2.index}].OID" cssClass="errorTextColor" />
                    <form:errors path="ruleRef.lazyInsertActions[${gridRow.index}].lazyProperties[${gridRow2.index}].valueExpression.value" cssClass="errorTextColor" />
                    <form:errors path="ruleRef.lazyInsertActions[${gridRow.index}].lazyProperties[${gridRow2.index}].value" cssClass="errorTextColor" />
                </p>
                </span>
                </c:forEach>
            </span>
            <designerTags:renderActionRunStatusTable pathPrefix="ruleRef.lazyInsertActions[${gridRow.index}]"/>
            </fieldset>
            </span>
        </c:forEach>

        <!-- event Actions -->
        <c:forEach items="${rulesCommand.ruleRef.lazyEventActions}" var="eventAction" varStatus="gridRow">
            <span id="lazyEventActions${gridRow.index}">
            <fieldset class="login">
            <legend><span><fmt:message key="label_event_action"/> <a id="removeEventAction" href="#" onclick="$('#lazyEventActions${gridRow.index}').remove();"><img title="Remove" alt="Remove" src="images/bt_Remove.gif" name="bt_Remove1"></a></span></legend>
            
            <p>
                <form:label id="ruleRef.lazyEventActions${gridRow.index}.ifExpressionEvaluatesLabel" for="ruleRef.lazyEventActions[${gridRow.index}].ifExpressionEvaluates" path="ruleRef.lazyEventActions[${gridRow.index}].ifExpressionEvaluates" cssErrorClass="error"><fmt:message key="label_evaluates_to"/>: </form:label> 
                <form:select path="ruleRef.lazyEventActions[${gridRow.index}].ifExpressionEvaluates" title="${toolExpressionEvaluatesTo }" style="border-radius: 5px; height: 23px; width: 60px; background: url(./includes/img/arrowUpDown.png) no-repeat 0 0; background-position: 36px -2px; -moz-apprearance: none; -webkit-appearance: none; text-indent: 0.01px; text-overflow: '';">
                  <form:option value="true"/>
                  <form:option value="false"/>
                </form:select>
                <form:errors path="ruleRef.lazyEventActions[${gridRow.index}].ifExpressionEvaluates" cssClass="error" />
            </p>

            <p>
                <form:label id="ruleRef.lazyEventActions${gridRow.index}.applyToLabel" for="ruleRef.lazyEventActions[${gridRow.index}].OID" path="ruleRef.lazyEventActions[${gridRow.index}].OID" cssErrorClass="errorTextColor"><fmt:message key="label_event_apply_to"/>: </form:label>
                <form:input path="ruleRef.lazyEventActions[${gridRow.index}].OID" cssErrorClass="errorBorder" title="${tooltipEventApplyTo}" style="width: 50%" />
                <form:errors path="ruleRef.lazyEventActions[${gridRow.index}].OID" cssClass="errorTextColor"/>
            </p>

            <span id="ruleRef.lazyEventActions${gridRow.index}.propertiesContainer">
            <c:forEach items="${eventAction.lazyProperties}" varStatus="gridRow2">
                <span id="ruleRef.lazyShowActions${gridRow.index}.lazyProperties${gridRow2.index}">

                <p>
                    <form:label id="ruleRef.lazyEventActions${gridRow.index}.lazyProperties${gridRow2.index}.Property" for="ruleRef.lazyEventActions[${gridRow.index}].lazyProperties[${gridRow2.index}].Property" path="ruleRef.lazyEventActions[${gridRow.index}].lazyProperties[${gridRow2.index}].Property" cssErrorClass="error"><fmt:message key="label_property"/>: </form:label> 
                    <form:select path="ruleRef.lazyEventActions[${gridRow.index}].lazyProperties[${gridRow2.index}].Property" title="${tooltipEventProperty }" style="border-radius: 5px; height: 23px; width: 120px; background: url(./includes/img/arrowUpDown.png) no-repeat 0 0; background-position: 96px -2px; -moz-apprearance: none; -webkit-appearance: none; text-indent: 0.01px; text-overflow: '';">
                      <form:option value="">Select</form:option>
                      <form:option value="STARTDATE">STARTDATE</form:option>
                    </form:select>
                    <form:errors path="ruleRef.lazyEventActions[${gridRow.index}].lazyProperties[${gridRow2.index}].Property" cssClass="error" />
                </p>

                <p>
                    <form:label id="ruleRef.lazyEventActions${gridRow.index}.lazyProperties${gridRow2.index}.valueExpression.valueLabel" for="ruleRef.lazyEventActions[${gridRow.index}].lazyProperties[${gridRow2.index}].valueExpression.value" path="ruleRef.lazyEventActions[${gridRow.index}].lazyProperties[${gridRow2.index}].valueExpression.value" cssErrorClass="errorTextColor"><fmt:message key="label_event_value_expression"/>: </form:label>
                    <form:input path="ruleRef.lazyEventActions[${gridRow.index}].lazyProperties[${gridRow2.index}].valueExpression.value" cssErrorClass="errorBorder" title="${tooltipEventExpression}" style="width: 50%" />
                    <form:errors path="ruleRef.lazyEventActions[${gridRow.index}].lazyProperties[${gridRow2.index}].valueExpression.value" cssClass="errorTextColor" />
                </p>

                <form:hidden path="ruleRef.lazyEventActions[${gridRow.index}].lazyProperties[${gridRow2.index}].placeHolder"/>

                </span>
            </c:forEach>
            </span>

            <designerTags:renderActionRunOnStatusTable pathPrefix="ruleRef.lazyEventActions[${gridRow.index}]"/>
            </fieldset>
            </span>
        </c:forEach>
    </div>
    
</form:form>
<div class="buttonRight">
    <button id="validateButton2" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">Validate</span></button>
    <button id="saveButton2" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">Save</span></button>
</div>
</div>

<script type="text/javascript">
        
    function save(){
        $('form#rulesCommand').attr({action: "ruleBuilder/saveRuleFromDesignerTab?ignoreDuplicates=true"});
        $('#rulesCommand').submit();
    }
    
    function reset(){
        $.get("ruleBuilder/ruleBuilderFormA?reset=true", function(data) {
            $('#kkforms').html(data);
        });
    }

  
    $(document).ready( function() {
        
        // Initialize WYSIWYG editors 
        new nicEditor({
            iconsPath: 'includes/nicEdit/nicEditorIcons.gif',
            maxHeight: 120,
            buttonList: ['example', 'notEqual', 'greater', 'greaterOrEqual', 'less', 'lessOrEqual', 'and', 'or', 'itemDetail','plus','minus','multiply','divide']
        }).panelInstance('ruleDef.expression.value');
        
        new nicEditor({
            iconsPath: 'includes/nicEdit/nicEditorIcons.gif',
            maxHeight: 40,
            buttonList: ['itemDetail']
        }).panelInstance('target.value');
        
        // When itemDetail button pressed in WYSIWYG, The highlighted text is stored into the element 
        // with the id below, then focused, hence triggering the function below.
        $("#highlightedTextInWYSIWYG").focus(function() {
            populateItemDetails($("#highlightedTextInWYSIWYG").val());
        });
        
        
        // Initialize tooltips
        initializeWYSIWYGToolTips("${tooltipTarget}","${tooltipExpression}");
        initializeToolTips();

        
        $('#addActions').change(function() {
                //alert('Handler for .change() called.');
                //alert($("#addActions option:selected").val());
                switch($('#addActions option:selected').val())
                {
                case "addDnAction":
                    addDnAction("${toolExpressionEvaluatesTo}","${tooltipMessage}");
                    break;
                case "addEmailAction":
                    addEmailAction("${toolExpressionEvaluatesTo}","${tooltipMessage}","${tooltipTo}");
                    break;
                case "addShowAction":
                    addShowAction("${toolExpressionEvaluatesTo}","${tooltipMessage}","${tooltipApplyOnShow}");
                    break;  
                case "addHideAction":
                    addHideAction("${toolExpressionEvaluatesTo}","${tooltipMessage}","${tooltipApplyOnHide}");
                    break;
                case "addInsertAction":
                    addInsertAction("${toolExpressionEvaluatesTo}","${tooltipApplyOnInsert}","${tooltipApplyOnValue}","${tooltipApplyOnExpression}");
                    break;
                case "addEventAction":
                    addEventAction("${toolExpressionEvaluatesTo}","${tooltipEventExpression}","${tooltipEventApplyTo}","${tooltipEventProperty}");
                    break;
                case "addNotificationAction":
                    addNotificationAction("${toolExpressionEvaluatesTo}","${tooltipMessage}","${tooltipTo}");
                    break;      
                default:
                    // DO NOTHING specifically when Add is selected
                }
                $("#addActions option[value='add']").attr('selected', 'selected');
        });
        
        $("#rulesCommand").submit(function() {
            nicEditors.findEditor('ruleDef.expression.value').saveContent()
            nicEditors.findEditor('target.value').saveContent()
            $.post($(this).attr("action"), $(this).serialize(), function(data) {
                $('#kkforms').html(data);
                
                //$('html, body').animate({ scrollTop: $("#message").offset().top }, 500);
                // $('html, body').animate({ scrollTop: $("#message").offset().bottom }, 500);
            });
            return false;  
        });
        
        $("#validateButton1,#validateButton2").click(function(){
            $('form#rulesCommand').attr({action: "ruleBuilder/ruleBuilderFormA"});
            $('#rulesCommand').submit();
            return false;  
        });
        
        $("#saveButton1,#saveButton2").click(function(){
            $('form#rulesCommand').attr({action: "ruleBuilder/saveRuleFromDesignerTab?ignoreDuplicates=false"});
            $('#rulesCommand').submit();
            return false;  
        });

        $("#runOnButton").click(function() {
            if ($("#showRunTimeTable").css("display") == "none") {
                $("#runOnButton").attr("src", "includes/img/icon_run_on.png");
                $("#runOnButton").css("float", "left");
                $("#showRunTimeTable").css("display", "inline-block");
                $("#headerExp").css("display", "inherit");
            } else {
                $("#runOnButton").attr("src", "includes/img/icon_run_off.png");
                $("#runOnButton").css("float", "none");
                $("#showRunTimeTable").css("display", "none");
                $("#headerExp").css("display", "none");
                $("#scheduleHourID").val(""); 
            }
        });
        
        if ($("#runOnScheduleID").val() != "") {
            $("#runOnButton").trigger("click");
        }
    });

    </script>

</div>