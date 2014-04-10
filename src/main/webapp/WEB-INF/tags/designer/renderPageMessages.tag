<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="designerTags" tagdir="/WEB-INF/tags/designer"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%-- Business Error --%>
<c:if test="${not empty messages}">
	<c:set var="messageType" value="${messages[0].type}" />
	<c:if test="${messageType == 'error' }">
		<c:set var="class" value="ui-state-error ui-corner-all" />
		<c:set var="iconClass" value="ui-icon ui-icon-alert" />
		<c:set var="prefixKey" value="label_alert" />
		<c:set var="messageKey" value="label_form_has_error" />
	</c:if>
	<c:if test="${messageType == 'warning' }">
        <c:set var="class" value="ui-state-error ui-corner-all" />
        <c:set var="iconClass" value="ui-icon ui-icon-alert" />
        <c:set var="prefixKey" value="label_alert" />
        <c:set var="messageKey" value="label_form_duplicate_rule" />
    </c:if>
	<c:if test="${messageType == 'success' }">
		<c:set var="class" value="ui-state-good ui-corner-all" />
		<c:set var="iconClass" value="ui-icon ui-icon-good" />
		<c:set var="prefixKey" value="label_alert" />
		<c:set var="messageKey" value="label_form_has_no_error" />
	</c:if>
	<c:if test="${messageType == 'saved' }">
		<c:set var="class" value="ui-state-good ui-corner-all" />
		<c:set var="iconClass" value="ui-icon ui-icon-good" />
		<c:set var="prefixKey" value="label_alert" />
		<c:set var="messageKey" value="label_form_has_no_error" />
	</c:if>

	<div class="ui-widget" id="message">
		<div style="padding: 0pt 0.7em;" class="${class}">
			<p>
				<span style="float: left; margin-right: 0.3em;" class="${iconClass}"></span>
				<strong><fmt:message key="${prefixKey}"/> - </strong><fmt:message key="${messageKey}"/>
			</p>
			<c:forEach items="${messages}" var="message">
			     <c:choose>
			         <c:when test="${message.type == 'warning'}">
			             <div><fmt:message key="duplicate_oid_message"/></div>
			             <script>
		                    $(document).ready( function() {
		                        $("#duplicateRuleDialog").dialog("open");
		                    });
                        </script>
			         </c:when>
			         <c:when test="${message.type == 'saved'}">
			             <div>${message.text}</div>
                         <c:if test="${message.type == 'saved'}">
                            <a href="" onclick="return showDialog('${sessionScope['scopedTarget.userPreferences'].editRulesOnOcInstance}')"><fmt:message key="label_view" /></a>
                         </c:if>
			         
			         </c:when>
			         <c:otherwise>
			             <div>${message.text}</div>
			         </c:otherwise>
			     </c:choose>
			</c:forEach>
		</div>
	</div>
</c:if>
<%-- Spring Validation Error --%>
<s:bind path="*">
	<c:if test="${status.error}">
		<div class="ui-widget" id="message">
			<div style="padding: 0pt 0.7em;" class="ui-state-error ui-corner-all">
				<p>
					<span style="float: left; margin-right: 0.3em;" class="ui-icon ui-icon-alert"></span>
					<strong><fmt:message key="label_alert"/>: </strong><fmt:message key="label_form_has_error" />.
				</p>
			</div>
		</div>
	</c:if>
</s:bind>
