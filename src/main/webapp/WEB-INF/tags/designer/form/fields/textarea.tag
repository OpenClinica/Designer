<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="designerTags" tagdir="/WEB-INF/tags/designer" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@attribute name="labelCSSErrorClass" type="java.lang.String" %>
<%@attribute name="imputCSSErrorClass" type="java.lang.String" %>
<%@attribute name="labelNameKey" type="java.lang.String" %>
<%@attribute name="title" type="java.lang.String" %>
<%@attribute name="rows" type="java.lang.String" %>
<%@attribute name="cols" type="java.lang.String" %>
<%@attribute name="path" type="java.lang.String" required="true" %>

<form:label for="${path}" path="${path}" cssErrorClass="${labelCSSErrorClass}"><fmt:message key="${labelNameKey}"/>: </form:label>
<form:textarea path="${path}" cssErrorClass="${imputCSSErrorClass}" rows="${rows}" cols="${cols}"/>
<form:errors path="${path}" cssClass="${labelCSSErrorClass}" />