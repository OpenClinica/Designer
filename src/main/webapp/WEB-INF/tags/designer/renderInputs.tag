<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="designerTags" tagdir="/WEB-INF/tags/designer" %>
<%@attribute name="field" type="org.akaza.openclinica.designer.web.fields.InputField" %>
<%@attribute name="size" %>
<%@attribute name="cssClass" %>
<%@attribute name="labelNameKey" type="java.lang.String" %>
<%@attribute name="labelCSSErrorClass" type="java.lang.String" %>
<%@attribute name="inputCSSErrorClass" type="java.lang.String" %>
<%@attribute name="disabled" type="java.lang.Boolean" %>

<c:set var="cssErrorClassLabelAndError" value="${empty labelCSSErrorClass ? 'errorTextColor' : labelCSSErrorClass}"/>
<c:set var="cssErrorClassInput" value="${empty inputCSSErrorClass ? 'errorBorder' : inputCSSErrorClass}"/>
<c:choose>
    <c:when test="${field.typeName == 'text'}">
        <form:label path="${field.propertyName}" 
                    cssErrorClass="${cssErrorClassLabelAndError}">
                    <a onclick="populateItemDetails('${labelNameKey}')" href="#"><c:out value="${labelNameKey}"/>:</a> </form:label>
        <form:input path="${field.propertyName}"
                    id="${field.propertyId}"
                    cssErrorClass="${cssErrorClassInput}" 
                    size="${empty size ? field.attributes.size : size}"
                    title="${field.displayName}"/>
        <c:if test="${field.dataTypeName == 'date'}"> 
            <script> var formId = "#" + "${field.propertyId}"; $(function() { $( formId ).datepicker({ dateFormat: 'yy-mm-dd' }); }); </script>
        </c:if>
        <form:errors path="${field.propertyName}" cssClass="${cssErrorClassLabelAndError}" />                    
    </c:when>
    <c:when test="${field.typeName == 'singleselect'}">
        <form:label path="${field.propertyName}" 
                    cssErrorClass="${cssErrorClassLabelAndError}">
                    <a onclick="populateItemDetails('${labelNameKey}')" href="#"><c:out value="${labelNameKey}"/>:</a> </form:label>
        <form:select path="${field.propertyName}" id="${field.propertyId}" multiple="false" cssErrorClass="${cssErrorClassInput}">
            <c:forEach var="entry" items="${field.options}">
                <form:option  value="${entry.value}">${entry.key} - ${entry.value}</form:option>
            </c:forEach>
        </form:select>                    
        <form:errors path="${field.propertyName}" cssClass="${cssErrorClassLabelAndError}" />                    
    </c:when>
    <c:when test="${field.typeName == 'multiselect'}">
        <form:label path="${field.propertyName}" 
                    cssErrorClass="${cssErrorClassLabelAndError}">
                    <a onclick="populateItemDetails('${labelNameKey}')" href="#"><c:out value="${labelNameKey}"/>:</a> </form:label>
        <form:select path="${field.propertyName}" id="${field.propertyId}" multiple="true" cssErrorClass="${cssErrorClassInput}" size="5">
            <c:forEach var="entry" items="${field.options}">
                <form:option  value="${entry.value}">${entry.key} - ${entry.value}</form:option>
            </c:forEach>
        </form:select>                    
        <form:errors path="${field.propertyName}" cssClass="${cssErrorClassLabelAndError}" />                    
    </c:when>
    <c:otherwise>
        UNIMPLEMENTED FIELD TYPE ${field.typeName} for ${field.propertyName} for ${field}
    </c:otherwise>
</c:choose>
<c:if test="${not empty field.attributes.help and field.categoryName ne 'autocompleter'}">
    <tags:hoverHelp path="${field.propertyName}" code="${field.attributes.help}" />
</c:if>