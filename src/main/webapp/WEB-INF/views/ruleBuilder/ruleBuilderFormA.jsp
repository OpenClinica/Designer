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
    <fmt:message key="tooltip_notification_subject" var="tooltipNotifSubject"/>
    <fmt:message key="tooltip_notification_message" var="tooltipNotifMessage"/>
     
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
        <img id="runOnButton" src="includes/img/icon_run_off.png" alt="run on a schedule button" width="50px" height="24px" class="onoff_btn" />
        <table id="showRunTimeTable" class="time_table">
            <tr>
                <td class="time_table_td">
                    <fmt:message key="label_run_time"/>:
                </td>
                <td class="time_table_td" style="padding-left: 5px;">
                    <form:select path="runOnSchedule.time" id="runOnScheduleID" multiple="false" cssErrorClass="errorBorder" class="time_ddown">
                        <form:option value="">--select--</form:option>
                        <form:option value="01:00">&nbsp;&nbsp;&nbsp;01:00</form:option>
                        <form:option value="02:00">&nbsp;&nbsp;&nbsp;02:00</form:option>
                        <form:option value="03:00">&nbsp;&nbsp;&nbsp;03:00</form:option>
                        <form:option value="04:00">&nbsp;&nbsp;&nbsp;04:00</form:option>
                        <form:option value="05:00">&nbsp;&nbsp;&nbsp;05:00</form:option>
                        <form:option value="06:00">&nbsp;&nbsp;&nbsp;06:00</form:option>
                        <form:option value="07:00">&nbsp;&nbsp;&nbsp;07:00</form:option>
                        <form:option value="08:00">&nbsp;&nbsp;&nbsp;08:00</form:option>
                        <form:option value="09:00">&nbsp;&nbsp;&nbsp;09:00</form:option>
                        <form:option value="10:00">&nbsp;&nbsp;&nbsp;10:00</form:option>
                        <form:option value="11:00">&nbsp;&nbsp;&nbsp;11:00</form:option>
                        <form:option value="12:00">&nbsp;&nbsp;&nbsp;12:00</form:option>
                        <form:option value="13:00">&nbsp;&nbsp;&nbsp;13:00</form:option>
                        <form:option value="14:00">&nbsp;&nbsp;&nbsp;14:00</form:option>
                        <form:option value="15:00">&nbsp;&nbsp;&nbsp;15:00</form:option>
                        <form:option value="16:00">&nbsp;&nbsp;&nbsp;16:00</form:option>
                        <form:option value="17:00">&nbsp;&nbsp;&nbsp;17:00</form:option>
                        <form:option value="18:00">&nbsp;&nbsp;&nbsp;18:00</form:option>
                        <form:option value="19:00">&nbsp;&nbsp;&nbsp;19:00</form:option>
                        <form:option value="20:00">&nbsp;&nbsp;&nbsp;20:00</form:option>
                        <form:option value="21:00">&nbsp;&nbsp;&nbsp;21:00</form:option>
                        <form:option value="22:00">&nbsp;&nbsp;&nbsp;22:00</form:option>
                        <form:option value="23:00">&nbsp;&nbsp;&nbsp;23:00</form:option>
                        <form:option value="00:00">&nbsp;&nbsp;&nbsp;00:00</form:option>
                    </form:select>
                </td>
                <td class="time_table_td" style="padding-left: 5px;">
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

    <p id="ruleExpressionWYSIWYG">
        <dFields:textarea path="ruleDef.expression.value" imputCSSErrorClass="errorBorder" labelNameKey="label_rule_expression" labelCSSErrorClass="errorTextColor" title="This will show up in the TipTip popup." rows="5" cols="100%"/>
        <%-- FOR DEBUGGING
        <c:out value="${rulesCommand.ruleDef.expression.value}"/>
        <c:out value="${rulesCommand.ruleDefCurated.expression.value}"/>
        --%>   
    </p>   
    <p>
        <form:label path="addActions" cssErrorClass="errorTextColor" id="actionsLabel"><fmt:message key="label_actions"/>:</form:label>
        <form:select path="addActions" id="addActions" multiple="false" cssErrorClass="errorBorder" title="${tooltipActions}" style="border-radius: 5px; height: 23px; width: 140px; background: white;">
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
                <form:select path="ruleRef.lazyDiscrepancyNoteActions[${gridRow.index}].ifExpressionEvaluates" title="${toolExpressionEvaluatesTo }" class="evaluate_select">
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
                <form:select path="ruleRef.lazyEmailActions[${gridRow.index}].ifExpressionEvaluates" title="${toolExpressionEvaluatesTo }" class="evaluate_select">
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
                <form:select path="ruleRef.lazyShowActions[${gridRow.index}].ifExpressionEvaluates" title="${toolExpressionEvaluatesTo }" class="evaluate_select">
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
                <form:select path="ruleRef.lazyHideActions[${gridRow.index}].ifExpressionEvaluates" title="${toolExpressionEvaluatesTo }" class="evaluate_select">
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
                <form:select path="ruleRef.lazyInsertActions[${gridRow.index}].ifExpressionEvaluates" title="${toolExpressionEvaluatesTo }" class="evaluate_select">
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
                <form:select path="ruleRef.lazyEventActions[${gridRow.index}].ifExpressionEvaluates" title="${toolExpressionEvaluatesTo }" class="evaluate_select">
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
                    <form:select path="ruleRef.lazyEventActions[${gridRow.index}].lazyProperties[${gridRow2.index}].Property" title="${tooltipEventProperty }" style="border-radius: 5px; height: 23px; width: 120px; background: white;">
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
        <!-- notification Actions-->
        <c:forEach items="${rulesCommand.ruleRef.lazyNotificationActions}" varStatus="gridRow">
            <span id="lazyNotificationActions${gridRow.index}">
            <fieldset class="login">
            <legend>
                <span><fmt:message key="label_notification_action"/>
                <a id="removeNotificationAction" href="#" onclick="$('#lazyNotificationActions${gridRow.index}').remove();"><img title="Remove" alt="Remove" src="images/bt_Remove.gif" name="bt_Remove1"></a>
                </span>
            </legend>

            <p class="margin_left_notif">
                <form:label id="ruleRef.lazyNotificationActions${gridRow.index}.ifExpressionEvaluatesLabel" for="ruleRef.lazyNotificationActions[${gridRow.index}].ifExpressionEvaluates" path="ruleRef.lazyNotificationActions[${gridRow.index}].ifExpressionEvaluates" cssErrorClass="error"><fmt:message key="label_evaluates_to"/>: </form:label> 
                <form:select path="ruleRef.lazyNotificationActions[${gridRow.index}].ifExpressionEvaluates" title="${toolExpressionEvaluatesTo }" class="evaluate_select">
                  <form:option value="true"/>
                  <form:option value="false"/>
                </form:select>
                <form:errors path="ruleRef.lazyNotificationActions[${gridRow.index}].ifExpressionEvaluates" cssClass="error" />
            </p>

            <p class="margin_left_notif">
                <form:label id="ruleRef.lazyNotificationActions${gridRow.index}.toLabel" for="ruleRef.lazyNotificationActions[${gridRow.index}].to" path="ruleRef.lazyNotificationActions[${gridRow.index}].to" cssErrorClass="errorTextColor"><fmt:message key="label_to"/>: </form:label>
                <div class="background_button_div" style="height: 25px;">
                    <div style="opacity: 0.6;" id="to_divoc-${gridRow.index}" >
                        <span id="to_participant-${gridRow.index}" class="participantBtn ui-button span_btn" style="margin-left: 10px;">
                            Participant
                        </span>
                    </div>
                </div>
                <form:input path="ruleRef.lazyNotificationActions[${gridRow.index}].to" cssErrorClass="errorBorder notifToInput notif_input" title="${tooltipTo}" style="margin-left: 85px; width: 576px; padding-bottom: 0px; padding-top: 0px;" class="notifToInput notif_input" />
                <form:errors path="ruleRef.lazyNotificationActions[${gridRow.index}].to" cssClass="errorTextColor" />
            </p>

            <p class="margin_left_notif">
                <form:label id="ruleRef.lazyNotificationActions${gridRow.index}.subjectLabel" for="ruleRef.lazyNotificationActions[${gridRow.index}].subject" path="ruleRef.lazyNotificationActions[${gridRow.index}].subject" cssErrorClass="errorTextColor"><fmt:message key="label_subject"/>: </form:label>

                <div class="background_button_div" style="color: rgb(148, 148, 148); border-bottom-color: transparent; border-top-left-radius: 5px; width: 136px; float: left; margin-left: 0; padding-left: 3px;">
                    <span>OpenClinica Variables</span>
                </div>
                <div class="background_button_div" style="color: rgb(148, 148, 148); border-bottom-color: transparent; border-top-right-radius: 5px; width: 441px; ; margin-left: 225px; padding-left: 5px;">
                    <span>OpenClinica Participant Variables</span>
                </div>

                <div class="background_button_div" style="border-top-color: transparent; width: 136px; float: left; margin-left: 85px; padding-bottom: 2px; padding-left: 3px; margin-top: -1px;">
                    <div style="opacity: 0.6;" id="subject_divoc-${gridRow.index}">
                        <span id="subject_study-${gridRow.index}" class="studyBtn ui-button span_btn" style="margin-left: 7px; vertical-align: top;">
                            Study
                        </span>
                        <span id="subject_event-${gridRow.index}" class="eventBtn ui-button span_btn" style="margin-left: 3px; vertical-align: top;">
                            Event
                        </span>
                    </div>
                </div>
                <div class="background_button_div" style="border-top-color: transparent; width: 441px; float: left; margin-left: -1px; padding-bottom: 2px; padding-left: 5px; margin-top: -1px;">
                    <div style="opacity: 0.6;" id="subject_divocp-${gridRow.index}">
                        <span id="subject_participant-${gridRow.index}" class="participantBtn ui-button span_btn" style="margin-left: 5px; vertical-align: top;">
                            Participant
                        </span>
                        <span id="subject_fName-${gridRow.index}" class="fNameBtn ui-button span_btn" style="margin-left: 3px; vertical-align: top;">
                            First Name
                        </span>
                        <span id="subject_url-${gridRow.index}" class="urlBtn ui-button span_btn" style="margin-left: 3px; vertical-align: top;">
                            URL
                        </span>
                        <span id="subject_urlLogin-${gridRow.index}" class="urlLoginBtn ui-button span_btn" style="margin-left: 3px; vertical-align: top;">
                            URL+Login
                        </span>
                        <span id="subject_accessCode-${gridRow.index}" class="accessCodeBtn ui-button span_btn" style="margin-left: 3px; vertical-align: top;">
                            Access Code
                        </span>
                    </div>
                </div>
                <form:input path="ruleRef.lazyNotificationActions[${gridRow.index}].subject" cssErrorClass="errorBorder notifSubjectInput notif_input" title="${tooltipNotifSubject}" class="notifSubjectInput notif_input" style="margin-left: 85px; width: 576px; padding-bottom: 0px; padding-top: 0px;"/>
                <form:errors path="ruleRef.lazyNotificationActions[${gridRow.index}].subject" cssClass="errorTextColor" />
            </p>
            <p class="margin_left_notif">
                <form:label id="ruleRef.lazyNotificationActions${gridRow.index}.messageLabel" for="ruleRef.lazyNotificationActions[${gridRow.index}].message" path="ruleRef.lazyNotificationActions[${gridRow.index}].message" cssErrorClass="errorTextColor"><fmt:message key="label_message"/>: </form:label>


                <div class="background_button_div" style="color: rgb(148, 148, 148); border-bottom-color: transparent; border-top-left-radius: 5px; width: 136px; float: left; margin-left: 0; padding-left: 3px;">
                    <span>OpenClinica Variables</span>
                </div>
                <div class="background_button_div" style="color: rgb(148, 148, 148); border-bottom-color: transparent; border-top-right-radius: 5px; width: 441px; ; margin-left: 225px; padding-left: 5px;">
                    <span>OpenClinica Participant Variables</span>
                </div>

                <div class="background_button_div" style="border-top-color: transparent; width: 136px; float: left; margin-left: 85px; padding-bottom: 2px; padding-left: 3px; margin-top: -1px;">
                    <div style="opacity: 0.6;" id="message_divoc-${gridRow.index}">
                        <span id="message_study-${gridRow.index}" class="studyBtn ui-button span_btn" style="margin-left: 7px; vertical-align: top;">
                            Study
                        </span>
                        <span id="message_event-${gridRow.index}" class="eventBtn ui-button span_btn" style="margin-left: 3px; vertical-align: top;">
                            Event
                        </span>
                    </div>
                </div>
                <div class="background_button_div" style="border-top-color: transparent; width: 441px; float: left; margin-left: -1px; padding-bottom: 2px; padding-left: 5px; margin-top: -1px;">
                    <div style="opacity: 0.6;" id="message_divocp-${gridRow.index}">
                        <span id="message_participant-${gridRow.index}" class="participantBtn ui-button span_btn" style="margin-left: 5px; vertical-align: top;">
                            Participant
                        </span>
                        <span id="message_fName-${gridRow.index}" class="fNameBtn ui-button span_btn" style="margin-left: 3px; vertical-align: top;">
                            First Name
                        </span>
                        <span id="message_url-${gridRow.index}" class="urlBtn ui-button span_btn" style="margin-left: 3px; vertical-align: top;">
                            URL
                        </span>
                        <span id="message_urlLogin-${gridRow.index}" class="urlLoginBtn ui-button span_btn" style="margin-left: 3px; vertical-align: top;">
                            URL+Login
                        </span>
                        <span id="message_accessCode-${gridRow.index}" class="accessCodeBtn ui-button span_btn" style="margin-left: 3px; vertical-align: top;">
                            Access Code
                        </span>
                    </div>
                </div>
                <form:textarea path="ruleRef.lazyNotificationActions[${gridRow.index}].message" rows="3" cols="50" cssErrorClass="errorBorder notifMessageInput notif_input" title="${tooltipNotifMessage}" style="width: 576px; height: 150px; margin-bottom: -5px;" class="notifMessageInput notif_input"/>
                <form:errors path="ruleRef.lazyNotificationActions[${gridRow.index}].message" cssClass="errorTextColor" />
            </p>
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
                    addNotificationAction("${toolExpressionEvaluatesTo}","${tooltipTo}","${tooltipNotifSubject}","${tooltipNotifMessage}");
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
            } else {
                $("#runOnButton").attr("src", "includes/img/icon_run_off.png");
                $("#runOnButton").css("float", "none");
                $("#showRunTimeTable").css("display", "none");
                $("#runOnScheduleID").val(""); 
            }
        });
        
        if ($("#runOnScheduleID").val() != "") {
            $("#runOnButton").trigger("click");
        }

        function insertAtCaret(areaId,text) {
            var txtarea = document.getElementById(areaId);
            var scrollPos = txtarea.scrollTop;
            var strPos = 0;
            var br = ((txtarea.selectionStart || txtarea.selectionStart == '0') ? 
                "ff" : (document.selection ? "ie" : false ) );
            if (br == "ie") { 
                txtarea.focus();
                var range = document.selection.createRange();
                range.moveStart ('character', -txtarea.value.length);
                strPos = range.text.length;
            }
            else if (br == "ff") strPos = txtarea.selectionStart;

            var front = (txtarea.value).substring(0,strPos);  
            var back = (txtarea.value).substring(strPos,txtarea.value.length); 
            txtarea.value=front+text+back;
            strPos = strPos + text.length;
            if (br == "ie") { 
                txtarea.focus();
                var range = document.selection.createRange();
                range.moveStart ('character', -txtarea.value.length);
                range.moveStart ('character', strPos);
                range.moveEnd ('character', 0);
                range.select();
            }
            else if (br == "ff") {
                txtarea.selectionStart = strPos;
                txtarea.selectionEnd = strPos;
                txtarea.focus();
            }
            txtarea.scrollTop = scrollPos;
        }

        var isButtonOnClick = false;

        $("#ruleActions").delegate(".participantBtn,.studyBtn,.eventBtn,.fNameBtn,.urlBtn,.urlLoginBtn,.accessCodeBtn", "mousedown",function(){
            isButtonOnClick = true;
        });

        $("#ruleActions").delegate(".participantBtn,.studyBtn,.eventBtn,.fNameBtn,.urlBtn,.urlLoginBtn,.accessCodeBtn", "mouseup",function(){
            isButtonOnClick = false;
        });

        $("#ruleActions").delegate(".participantBtn,.studyBtn,.eventBtn,.fNameBtn,.urlBtn,.urlLoginBtn,.accessCodeBtn", "click",function(){
            var currentId = $(this).attr('id');
            var splitter = currentId.split("_");
            var splitter2 = splitter[1].split("-");
            var value = [];
            value["index"] = splitter2[1];
            value["part"] = splitter[0];
            value["type"] = splitter2[0];
            var targetId = "ruleRef.lazyNotificationActions"+ value["index"] +"."+ value["part"];
            var target = document.getElementById(targetId);
            var currentValue = target.value;
            var optionalValue = "";
            switch(value["type"]) {
                case "participant":
                    optionalValue = "\$\{participant\} ";
                    break;
                case "study":
                    optionalValue = "\$\{study.name\} ";
                    break;
                case "event":
                    optionalValue = "\$\{event.name\} ";
                    break;
                case "fName":
                    optionalValue = "\$\{participant.firstname\} ";
                    break;
                case "url":
                    optionalValue = "\$\{participant.url\} ";
                    break;
                case "urlLogin":
                    optionalValue = "\$\{participant.loginurl\} ";
                    break;
                case "accessCode":
                    optionalValue = "\$\{participant.accessCode\} ";
                    break;
            }
            
            insertAtCaret(targetId,optionalValue);
        });

        for(var i=0;i < "${rulesCommand.ruleRef.lazyNotificationActions.size()}"; i++) {
            var subject = document.getElementById("ruleRef.lazyNotificationActions"+i+".subject");
            var subjectValue = subject.value;
            var str = subjectValue.replace(/  +/g, ' ');
            document.getElementById("ruleRef.lazyNotificationActions"+i+".subject").value = str;
        }

        function extractValue(id) {
            var value = [];
            var splitter = id.split(".");
            value["part"] = splitter[2];
            value["index"] = splitter[1].split("lazyNotificationActions")[1];
            return value;
        }

        $("#ruleActions").delegate(".notifToInput,.notifSubjectInput,.notifMessageInput","focus",function() {
            var value = extractValue($(this).attr('id'));
            var part = "to";
            switch(value["part"]) {
                case "subject" :
                    part = "subject";
                    break;
                case "message" :
                    part = "message";
                    break;
            }
            document.getElementById(part+"_divoc-"+value["index"]).style.opacity = 1;
            document.getElementById(part+"_divocp-"+value["index"]).style.opacity = 1;
            document.getElementById(part+"_div-"+value["index"]).className += " active";
        });

        $("#ruleActions").delegate(".notifToInput,.notifSubjectInput,.notifMessageInput","blur",function() {
            var value = extractValue($(this).attr('id'));
            var part = "to";
            switch(value["part"]) {
                case "subject" :
                    part = "subject";
                    break;
                case "message" :
                    part = "message";
                    break;
            }
            if (!isButtonOnClick) {
                document.getElementById(part+"_divoc-"+value["index"]).style.opacity = 0.6;
                document.getElementById(part+"_divocp-"+value["index"]).style.opacity = 0.6;
                var tmpClass = document.getElementById(part+"_div-"+value["index"]).className = "";
            }
        })

    });

    </script>

</div>