<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>

<%@attribute name="path" description="The path to bind" required="true"%>
<%@attribute name="field" description="The field that is being wrapped" fragment="true" %>

<%@attribute name="cssClass" description="The 'class' attribute in HTML" %>
<%@attribute name="validationJSClass" description="The classes required for validation framework" %>

<%@attribute name="required" type="java.lang.Boolean" description="Tells that this field is a required (red asterisk)"%>
<%@attribute name="readonly" description="Specifies the readonly attribute" %>
<%@attribute name="readonlyDisplayFormat" description="The formatting to be used for readonly display text" %>
<%@attribute name="displayNamePath" description="This path is used to display the text, when the field is readOnly, if not specified 'path' is used as default " %>
<%@attribute name="title" description="Specifies the alternate or tooltip title" %>

<%@attribute name="embededJS" description="A piece of javascript, that if specified will be embeded along with this input"%>
<caaers:renderFilter elementID="${path}">
	<c:if test="${not readonly}">
	<c:set var="validationCss" scope="request">${validationJSClass}${required ? not empty validationJSClass ? '&&NOTEMPTY':'validate-NOTEMPTY' :''}</c:set>
	<jsp:invoke fragment="field" />
	<c:remove var="validationCss" scope="request" />
	</c:if>
	<!--
	<c:if test="${readonly}">
	<c:set var="dNproperty" value="${empty displayNamePath ? path : displayNamePath}"  scope="request"/>
	<c:choose>
		<c:when test="${readonlyDisplayFormat eq 'none'}"></c:when>
		<c:when test="${readonlyDisplayFormat eq 'date'}"><tags:formatDate value="${dNproperty}" /></c:when>
		<c:when test="${readonlyDisplayFormat eq 'split_date'}">yet to implement</c:when>
		<c:otherwise><ui:value propertyName="${dNproperty}" cssClass="${cssClass}" /></c:otherwise>
	</c:choose>
	<c:remove var="dNproperty" scope="request"/>
	</c:if>
	
	<ui:helpLink path="${path}"/>	
	<tags:errors path="${path}"/>
	<tags:errors path="${path}.*"/>
	<c:if test="${not empty embededJS}"><script>${embededJS}</script></c:if>
	-->
</caaers:renderFilter>
