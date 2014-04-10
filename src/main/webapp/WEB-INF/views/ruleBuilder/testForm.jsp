<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="designerTags" tagdir="/WEB-INF/tags/designer" %>


<div id="rulesCommandTestDiv">
<style>
#navAccount{position:relative}
#navAccount ul{background:#fff;border:1px solid #333;border-bottom:2px solid #2d4486;display:none;margin-right:-1px;margin-top:-1px;min-width:300px;padding:10px 0 5px;position:absolute;right:0;_right:-1px;top:100%;*width:100%;_width:200px;z-index:1}
#navAccount.openToggler ul,
.no_js #navAccount:hover ul{display:block}
#navAccount li{display:block;float:none}
</style>



<div class="box">
<!--<h2><a href="#" id="toggle-forms">Forms</a></h2>-->
<div class="block" id="forms">

<form:form modelAttribute="rulesCommand" id="rulesCommandTest" action="ruleBuilder/testForm" method="post">
    <span class="topButtons">
    <button id="testButtonTest1" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">Test</span></button>
    </span>

    <designerTags:renderPageMessages/>
    <fieldset class="noBorder login">
    <p>
        <form:label id="targetLabel" for="target.value" path="target.value" cssErrorClass="error"><fmt:message key="label_target"/>: </form:label>
        <form:hidden path="target.value" id="ocTextArea" class="jstree-drop" rows="3" cols="50"/>
        <c:out value="${rulesCommand.targetCurated.value}" />&nbsp;
        <form:errors path="target.value" cssClass="error" />
    </p>
    <p>
        <form:label id="oidLabel" for="ruleDef.OID" path="ruleDef.OID" cssErrorClass="error"><fmt:message key="label_rule_oid"/>: </form:label>
        <form:hidden path="ruleDef.OID"/>
        <c:out value="${rulesCommand.ruleDef.OID}" />&nbsp; 
        <form:errors path="ruleDef.OID" cssClass="error" />
    </p>
    <p>
        <form:label id="ruleExpressionLabel" for="ruleDef.expression.value" path="ruleDef.expression.value" cssErrorClass="error"><fmt:message key="label_rule_expression"/>: </form:label> 
        <form:hidden path="ruleDef.expression.value" id="ocTextArea" class="jstree-drop" rows="3" cols="50"/>
        <c:out value="${rulesCommand.ruleDefCurated.expression.value}" />&nbsp;
        <form:errors path="ruleDef.expression.value" cssClass="error" />
    </p>
    </fieldset>
    
    <div id="ruleActions">
        <!-- discrepancyNote Actions -->
        <c:forEach items="${rulesCommand.ruleRef.lazyDiscrepancyNoteActions}" varStatus="gridRow">
            <span id="lazyDiscrepancyNoteActions${gridRow.index}">
            <fieldset class="login">
            <legend><span><fmt:message key="label_dis_note_action"/> <designerTags:displayTestFlag ruleResult="${rulesCommand.testRulesResults['result']}"  actionResult="${rulesCommand.ruleRef.lazyDiscrepancyNoteActions[gridRow.index].ifExpressionEvaluates}"/>&nbsp;</span></legend>
            <p>
                <form:label id="ruleRef.lazyDiscrepancyNoteActions${gridRow.index}.ifExpressionEvaluatesLabel" for="ruleRef.lazyDiscrepancyNoteActions[${gridRow.index}].ifExpressionEvaluates" path="ruleRef.lazyDiscrepancyNoteActions[${gridRow.index}].ifExpressionEvaluates" cssErrorClass="error"><fmt:message key="label_evaluates_to"/>: </form:label> 
                <c:out value="${rulesCommand.ruleRef.lazyDiscrepancyNoteActions[gridRow.index].ifExpressionEvaluates}" />&nbsp;
                <form:hidden path="ruleRef.lazyDiscrepancyNoteActions[${gridRow.index}].ifExpressionEvaluates"/>
                <form:errors path="ruleRef.lazyDiscrepancyNoteActions[${gridRow.index}].ifExpressionEvaluates" cssClass="error" />
            </p>
            <p>
                <form:label id="ruleRef.lazyDiscrepancyNoteActions${gridRow.index}.messageLabel" for="ruleRef.lazyDiscrepancyNoteActions[${gridRow.index}].message" path="ruleRef.lazyDiscrepancyNoteActions[${gridRow.index}].message" cssErrorClass="error"><fmt:message key="label_message"/>: </form:label> 
                <c:out value="${rulesCommand.ruleRef.lazyDiscrepancyNoteActions[gridRow.index].message}" />&nbsp;
                <form:hidden path="ruleRef.lazyDiscrepancyNoteActions[${gridRow.index}].message" rows="3" cols="50" />
                <form:errors path="ruleRef.lazyDiscrepancyNoteActions[${gridRow.index}].message" cssClass="error" />
            </p>
            <div id="tables" class="block" style="margin: 0px;">
                <table>
                    <tbody>
                    <tr>
                        <td colspan="100%"><fmt:message key="label_specify_mode"/></td>
                    </tr>
                    <tr>
                        <th><fmt:message key="label_admin_data_entry"/></th>
                        <th><fmt:message key="label_initial_data_entry"/></th>
                        <th><fmt:message key="label_double_data_entry"/></th>
                        <th><fmt:message key="label_batch"/></th>
                    </tr>
                    <tr>
                        <form:hidden path="ruleRef.lazyDiscrepancyNoteActions[${gridRow.index}].run.administrativeDataEntry"/>
                        <form:hidden path="ruleRef.lazyDiscrepancyNoteActions[${gridRow.index}].run.initialDataEntry"/>
                        <form:hidden path="ruleRef.lazyDiscrepancyNoteActions[${gridRow.index}].run.doubleDataEntry"/>
                        <form:hidden path="ruleRef.lazyDiscrepancyNoteActions[${gridRow.index}].run.importDataEntry"/>
                        <form:hidden path="ruleRef.lazyDiscrepancyNoteActions[${gridRow.index}].run.batch"/>
                        <td><c:out value="${rulesCommand.ruleRef.lazyDiscrepancyNoteActions[gridRow.index].run.administrativeDataEntry}" />&nbsp;</td>
                        <td><c:out value="${rulesCommand.ruleRef.lazyDiscrepancyNoteActions[gridRow.index].run.initialDataEntry}" />&nbsp;</td>
                        <td><c:out value="${rulesCommand.ruleRef.lazyDiscrepancyNoteActions[gridRow.index].run.doubleDataEntry}" />&nbsp;</td>
                        <td><c:out value="${rulesCommand.ruleRef.lazyDiscrepancyNoteActions[gridRow.index].run.batch}" />&nbsp;</td>
                    </tr>
                    </tbody>
                </table>
            </div>
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
                <c:out value="${rulesCommand.ruleRef.lazyEmailActions[gridRow.index].ifExpressionEvaluates}" />&nbsp;
                <form:hidden path="ruleRef.lazyEmailActions[${gridRow.index}].ifExpressionEvaluates"/>
                <form:errors path="ruleRef.lazyEmailActions[${gridRow.index}].ifExpressionEvaluates" cssClass="error" />
            </p>
            <p>
                <form:label id="ruleRef.lazyEmailActions${gridRow.index}.toLabel" for="ruleRef.lazyEmailActions[${gridRow.index}].to" path="ruleRef.lazyEmailActions[${gridRow.index}].to" cssErrorClass="error">To: </form:label> 
                <c:out value="${rulesCommand.ruleRef.lazyEmailActions[gridRow.index].to}" />&nbsp;
                <form:hidden path="ruleRef.lazyEmailActions[${gridRow.index}].to" />
                <form:errors path="ruleRef.lazyEmailActions[${gridRow.index}].to" cssClass="error"/>
            </p>
            <p>
                <form:label id="ruleRef.lazyEmailActions${gridRow.index}.messageLabel" for="ruleRef.lazyEmailActions[${gridRow.index}].message" path="ruleRef.lazyEmailActions[${gridRow.index}].message" cssErrorClass="error"><fmt:message key="label_message"/>: </form:label> 
                <c:out value="${rulesCommand.ruleRef.lazyEmailActions[gridRow.index].message}" />&nbsp;
                <form:hidden path="ruleRef.lazyEmailActions[${gridRow.index}].message" rows="3" cols="50" />
                <form:errors path="ruleRef.lazyEmailActions[${gridRow.index}].message" cssClass="error" />
            </p>
            <div id="tables" class="block" style="margin: 0px;">
                <table>
                    <tbody>
                    <tr>
                        <td colspan="100%"><fmt:message key="label_specify_mode"/></td>
                    </tr>
                    <tr>
                        <th><fmt:message key="label_admin_data_entry"/></th>
                        <th><fmt:message key="label_initial_data_entry"/></th>
                        <th><fmt:message key="label_double_data_entry"/></th>
                        <th><fmt:message key="label_import_data_entry"/></th>
                        <th><fmt:message key="label_batch"/></th>
                    </tr>
                    <tr>
                        <form:hidden path="ruleRef.lazyEmailActions[${gridRow.index}].run.administrativeDataEntry"/>
                        <form:hidden path="ruleRef.lazyEmailActions[${gridRow.index}].run.initialDataEntry"/>
                        <form:hidden path="ruleRef.lazyEmailActions[${gridRow.index}].run.doubleDataEntry"/>
                        <form:hidden path="ruleRef.lazyEmailActions[${gridRow.index}].run.batch"/>
                        <td><c:out value="${rulesCommand.ruleRef.lazyEmailActions[gridRow.index].run.administrativeDataEntry}" />&nbsp;</td>
                        <td><c:out value="${rulesCommand.ruleRef.lazyEmailActions[gridRow.index].run.initialDataEntry}" />&nbsp;</td>
                        <td><c:out value="${rulesCommand.ruleRef.lazyEmailActions[gridRow.index].run.doubleDataEntry}" />&nbsp;</td>
                        <td><c:out value="${rulesCommand.ruleRef.lazyEmailActions[gridRow.index].run.batch}" />&nbsp;</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            </fieldset>
            </span>
        </c:forEach>
        
        <!-- show Actions -->
        <c:forEach items="${rulesCommand.ruleRef.lazyShowActions}" var="showAction" varStatus="gridRow">
            <span id="lazyShowActions${gridRow.index}">
            <fieldset class="login">
            <legend><span><fmt:message key="label_show_action"/> <designerTags:displayTestFlag ruleResult="${rulesCommand.testRulesResults['result']}"  actionResult="${rulesCommand.ruleRef.lazyShowActions[gridRow.index].ifExpressionEvaluates}"/>&nbsp; </span></legend>
            
            <p>
                <form:label id="ruleRef.lazyShowActions${gridRow.index}.ifExpressionEvaluatesLabel" for="ruleRef.lazyShowActions[${gridRow.index}].ifExpressionEvaluates" path="ruleRef.lazyShowActions[${gridRow.index}].ifExpressionEvaluates" cssErrorClass="error"><fmt:message key="label_evaluates_to"/>:</form:label>
                <c:out value="${rulesCommand.ruleRef.lazyShowActions[gridRow.index].ifExpressionEvaluates}" />&nbsp;
                <form:hidden path="ruleRef.lazyShowActions[${gridRow.index}].ifExpressionEvaluates"/> 
                <form:errors path="ruleRef.lazyShowActions[${gridRow.index}].ifExpressionEvaluates" cssClass="error" />
            </p>
            <p>
                <form:label id="ruleRef.lazyShowActions${gridRow.index}.messageLabel" for="ruleRef.lazyShowActions[${gridRow.index}].message" path="ruleRef.lazyShowActions[${gridRow.index}].message" cssErrorClass="error"><fmt:message key="label_message"/>: </form:label> 
                <c:out value="${rulesCommand.ruleRef.lazyShowActions[gridRow.index].message}" />&nbsp;
                <form:hidden path="ruleRef.lazyShowActions[${gridRow.index}].message" rows="3" cols="50" />
            </p>
            <p>
                <label><fmt:message key="label_apply_on"/>:</label>
            </p>
            <span id="ruleRef.lazyShowActions${gridRow.index}.propertiesContainer">
            <c:forEach items="${showAction.lazyProperties}" varStatus="gridRow2">
                <span id="ruleRef.lazyShowActions${gridRow.index}.lazyProperties${gridRow2.index}">
                <p>
                    <label>&nbsp;</label>
                    <form:hidden path="ruleRef.lazyShowActions[${gridRow.index}].lazyProperties[${gridRow2.index}].placeHolder"/>
                    <form:hidden path="ruleRef.lazyShowActions[${gridRow.index}].lazyProperties[${gridRow2.index}].OID"/>
                    <c:out value="${rulesCommand.ruleRef.lazyShowActions[gridRow.index].lazyProperties[gridRow2.index].OID}" />&nbsp;
                </p>
                </span>
            </c:forEach>
            </span>
            <div id="tables" class="block" style="margin: 0px;">
                <table>
                    <tbody>
                    <tr>
                        <td colspan="100%"><fmt:message key="label_specify_mode"/></td>
                    </tr>
                    <tr>
                        <th><fmt:message key="label_admin_data_entry"/></th>
                        <th><fmt:message key="label_initial_data_entry"/></th>
                        <th><fmt:message key="label_double_data_entry"/></th>
                        <th><fmt:message key="label_import_data_entry"/></th>
                        <th><fmt:message key="label_batch"/></th>
                    </tr>
                    <tr>
                        <form:hidden path="ruleRef.lazyShowActions[${gridRow.index}].run.administrativeDataEntry"/>
                        <form:hidden path="ruleRef.lazyShowActions[${gridRow.index}].run.initialDataEntry"/>
                        <form:hidden path="ruleRef.lazyShowActions[${gridRow.index}].run.doubleDataEntry"/>
                        <form:hidden path="ruleRef.lazyShowActions[${gridRow.index}].run.batch"/>
                        <td><c:out value="${rulesCommand.ruleRef.lazyShowActions[gridRow.index].run.administrativeDataEntry}" />&nbsp;</td>
                        <td><c:out value="${rulesCommand.ruleRef.lazyShowActions[gridRow.index].run.initialDataEntry}" />&nbsp;</td>
                        <td><c:out value="${rulesCommand.ruleRef.lazyShowActions[gridRow.index].run.doubleDataEntry}" />&nbsp;</td>
                        <td><c:out value="${rulesCommand.ruleRef.lazyShowActions[gridRow.index].run.batch}" />&nbsp;</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            </fieldset>
            </span>
        </c:forEach>
        
        <!-- hide Actions -->
        <c:forEach items="${rulesCommand.ruleRef.lazyHideActions}" var="hideAction" varStatus="gridRow">
            <span id="lazyHideActions${gridRow.index}">
            <fieldset class="login">
            <legend><span><fmt:message key="label_hide_action"/> <designerTags:displayTestFlag ruleResult="${rulesCommand.testRulesResults['result']}"  actionResult="${rulesCommand.ruleRef.lazyHideActions[gridRow.index].ifExpressionEvaluates}"/>&nbsp;</span></legend>
            
            <p>
                <form:label id="ruleRef.lazyHideActions${gridRow.index}.ifExpressionEvaluatesLabel" for="ruleRef.lazyHideActions[${gridRow.index}].ifExpressionEvaluates" path="ruleRef.lazyHideActions[${gridRow.index}].ifExpressionEvaluates" cssErrorClass="error"><fmt:message key="label_evaluates_to"/>: </form:label> 
                <c:out value="${rulesCommand.ruleRef.lazyHideActions[gridRow.index].ifExpressionEvaluates}" />&nbsp;
                <form:hidden path="ruleRef.lazyHideActions[${gridRow.index}].ifExpressionEvaluates"/>
                <form:errors path="ruleRef.lazyHideActions[${gridRow.index}].ifExpressionEvaluates" cssClass="error" />
            </p>
            <p>
                <form:label id="ruleRef.lazyHideActions${gridRow.index}.messageLabel" for="ruleRef.lazyHideActions[${gridRow.index}].message" path="ruleRef.lazyHideActions[${gridRow.index}].message" cssErrorClass="error"><fmt:message key="label_message"/>: </form:label> 
                <c:out value="${rulesCommand.ruleRef.lazyHideActions[gridRow.index].message}" />&nbsp;
                <form:hidden path="ruleRef.lazyHideActions[${gridRow.index}].message" rows="3" cols="50" />
                <form:errors path="ruleRef.lazyHideActions[${gridRow.index}].message" cssClass="error" />
            </p>
            <p>
                <label><fmt:message key="label_apply_on"/>:</label>
            </p>
            <span id="ruleRef.lazyHideActions${gridRow.index}.propertiesContainer">
            <c:forEach items="${hideAction.lazyProperties}" varStatus="gridRow2">
                <span id="ruleRef.lazyHideActions${gridRow.index}.lazyProperties${gridRow2.index}">
                <p>
                    <label>&nbsp;</label>
                    <form:hidden path="ruleRef.lazyHideActions[${gridRow.index}].lazyProperties[${gridRow2.index}].placeHolder"/>
                    <form:hidden path="ruleRef.lazyHideActions[${gridRow.index}].lazyProperties[${gridRow2.index}].OID"/>
                    <c:out value="${rulesCommand.ruleRef.lazyHideActions[gridRow.index].lazyProperties[gridRow2.index].OID}" />&nbsp;
                </p>
                </span>
            </c:forEach>
            </span>
            <div id="tables" class="block" style="margin: 0px;">
                <table>
                    <tbody>
                    <tr>
                        <td colspan="100%"><fmt:message key="label_specify_mode"/></td>
                    </tr>
                    <tr>
                        <th><fmt:message key="label_admin_data_entry"/></th>
                        <th><fmt:message key="label_initial_data_entry"/></th>
                        <th><fmt:message key="label_double_data_entry"/></th>
                        <th><fmt:message key="label_batch"/></th>
                    </tr>
                    <tr>
                        <form:hidden path="ruleRef.lazyHideActions[${gridRow.index}].run.administrativeDataEntry"/>
                        <form:hidden path="ruleRef.lazyHideActions[${gridRow.index}].run.initialDataEntry"/>
                        <form:hidden path="ruleRef.lazyHideActions[${gridRow.index}].run.doubleDataEntry"/>
                        <form:hidden path="ruleRef.lazyHideActions[${gridRow.index}].run.importDataEntry"/>
                        <form:hidden path="ruleRef.lazyHideActions[${gridRow.index}].run.batch"/>
                        <td><c:out value="${rulesCommand.ruleRef.lazyHideActions[gridRow.index].run.administrativeDataEntry}" />&nbsp;</td>
                        <td><c:out value="${rulesCommand.ruleRef.lazyHideActions[gridRow.index].run.initialDataEntry}" />&nbsp;</td>
                        <td><c:out value="${rulesCommand.ruleRef.lazyHideActions[gridRow.index].run.doubleDataEntry}" />&nbsp;</td>
                        <td><c:out value="${rulesCommand.ruleRef.lazyHideActions[gridRow.index].run.batch}" />&nbsp;</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            </fieldset>
            </span>
        </c:forEach>
        
        <!-- insert Actions -->
        <c:forEach items="${rulesCommand.ruleRef.lazyInsertActions}" var="insertAction" varStatus="gridRow">
            <span id="lazyInsertActions${gridRow.index}">
            <fieldset class="login">
            <legend><span><fmt:message key="label_insert_action"/><designerTags:displayTestFlag ruleResult="${rulesCommand.testRulesResults['result']}"  actionResult="${rulesCommand.ruleRef.lazyInsertActions[gridRow.index].ifExpressionEvaluates}"/>&nbsp;</span></legend>
            
            <p>
                <form:label id="ruleRef.lazyInsertActions${gridRow.index}.ifExpressionEvaluatesLabel" for="ruleRef.lazyInsertActions[${gridRow.index}].ifExpressionEvaluates" path="ruleRef.lazyInsertActions[${gridRow.index}].ifExpressionEvaluates" cssErrorClass="error"><fmt:message key="label_evaluates_to"/>: </form:label> 
                <c:out value="${rulesCommand.ruleRef.lazyInsertActions[gridRow.index].ifExpressionEvaluates}" />&nbsp;
                <form:hidden path="ruleRef.lazyInsertActions[${gridRow.index}].ifExpressionEvaluates"/>
                <form:errors path="ruleRef.lazyInsertActions[${gridRow.index}].ifExpressionEvaluates" cssClass="error" />
            </p>
            <p>
                <label><fmt:message key="label_apply_on"/>:</label>
            </p>
            <span id="ruleRef.lazyInsertActions${gridRow.index}.propertiesContainer">
            <c:forEach items="${insertAction.lazyProperties}" varStatus="gridRow2">
                <span id="ruleRef.lazyInsertActions${gridRow.index}.lazyProperties${gridRow2.index}">
                <p>
                    <label>&nbsp;</label>
                    <form:hidden path="ruleRef.lazyInsertActions[${gridRow.index}].lazyProperties[${gridRow2.index}].placeHolder"/>
                    <form:hidden path="ruleRef.lazyInsertActions[${gridRow.index}].lazyProperties[${gridRow2.index}].OID"/>
                    <c:out value="${rulesCommand.ruleRef.lazyInsertActions[gridRow.index].lazyProperties[gridRow2.index].OID}" />&nbsp;
                    <form:hidden path="ruleRef.lazyInsertActions[${gridRow.index}].lazyProperties[${gridRow2.index}].value"/>
                    <c:out value="${rulesCommand.ruleRef.lazyInsertActions[gridRow.index].lazyProperties[gridRow2.index].value}" />&nbsp;
                    <form:hidden path="ruleRef.lazyInsertActions[${gridRow.index}].lazyProperties[${gridRow2.index}].valueExpression.value"/>
                    <c:out value="${rulesCommand.ruleRef.lazyInsertActions[gridRow.index].lazyProperties[gridRow2.index].valueExpression.value}" />&nbsp;
                </p>
                </span>
            </c:forEach>
            </span>
            <div id="tables" class="block" style="margin: 0px;">
                <table>
                    <tbody>
                    <tr>
                        <td colspan="100%"><fmt:message key="label_specify_mode"/></td>
                    </tr>
                    <tr>
                        <th><fmt:message key="label_admin_data_entry"/></th>
                        <th><fmt:message key="label_initial_data_entry"/></th>
                        <th><fmt:message key="label_double_data_entry"/></th>
                        <th><fmt:message key="label_batch"/></th>
                    </tr>
                    <tr>
                        <form:hidden path="ruleRef.lazyInsertActions[${gridRow.index}].run.administrativeDataEntry"/>
                        <form:hidden path="ruleRef.lazyInsertActions[${gridRow.index}].run.initialDataEntry"/>
                        <form:hidden path="ruleRef.lazyInsertActions[${gridRow.index}].run.doubleDataEntry"/>
                        <form:hidden path="ruleRef.lazyInsertActions[${gridRow.index}].run.importDataEntry"/>
                        <form:hidden path="ruleRef.lazyInsertActions[${gridRow.index}].run.batch"/>
                        <td><c:out value="${rulesCommand.ruleRef.lazyInsertActions[gridRow.index].run.administrativeDataEntry}" />&nbsp;</td>
                        <td><c:out value="${rulesCommand.ruleRef.lazyInsertActions[gridRow.index].run.initialDataEntry}" />&nbsp;</td>
                        <td><c:out value="${rulesCommand.ruleRef.lazyInsertActions[gridRow.index].run.doubleDataEntry}" />&nbsp;</td>
                        <td><c:out value="${rulesCommand.ruleRef.lazyInsertActions[gridRow.index].run.batch}" />&nbsp;</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            </fieldset>
            </span>
        </c:forEach>
    </div>
    <h3><fmt:message key="label_step_2"/></h3>
    <fieldset class="variables">
    <!-- <legend>Specify test values for variables in your rule expression</legend>  -->
    <c:forEach items="${rulesCommand.rulePropertiesHtml}" varStatus="gridRow">
        <p>
            <designerTags:renderInputs labelNameKey="${rulesCommand.rulePropertiesHtml[gridRow.index].displayName}" field="${rulesCommand.rulePropertiesHtml[gridRow.index]}"/>&nbsp;
        </p>
    </c:forEach>
    </fieldset>
    <c:if test="${not empty rulesCommand.testRulesResults['result']}">
        <h3><fmt:message key="label_step_3"/></h3>
	    <fieldset class="testResults">
	    <!-- <legend>Verify results</legend> -->
	        <!-- 
	        <p>
	            <label>Rule Validation:</label>
	            <c:out value="${rulesCommand.testRulesResults['result']}" />&nbsp;
	        </p>
	         -->
	        <p>
	            <label><fmt:message key="label_expression_evaluates_to"/>:</label>
	            <c:out value="${rulesCommand.testRulesResults['ruleEvaluatesTo']}" />&nbsp;
	        </p>
	        <p>
                <label><fmt:message key="label_actions_fired"/>:</label>
                <c:out value="${rulesCommand.testWillActionsRun}" />&nbsp;
            </p>
	        <p>
	            <label><fmt:message key="label_rule_validation"/>:</label>
	            <fmt:message key="${rulesCommand.testRulesResults['ruleValidation']}"/>
	            <!--<c:out value="${rulesCommand.testRulesResults['ruleValidation']}" />&nbsp;-->
	        </p>
	    </fieldset>
    </c:if>
    
    
</form:form>
<div class="buttonRight">
    <button id="testButtonTest2" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">Test</span></button>
</div>
</div>
</div>
</p>



<script type="text/javascript">

    $(document).ready( function() {
        
        $("#rulesCommandTest").submit(function() {
            $.post($(this).attr("action"), $(this).serialize(), function(data) {
                $('#rulesCommandTestDiv').html(data);
            });
            return false;  
        }); 
        
        $("#testButtonTest1,#testButtonTest2").click(function(){
            $('form#rulesCommandTest').attr({action: "ruleBuilder/testForm"});
            $('#rulesCommandTest').submit();
            return false;  
        });

    });
</script>

</div>