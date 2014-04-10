<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@attribute name="field" type="org.akaza.openclinica.designer.web.fields.InputField" %>
<%@attribute name="ruleResult" type="java.lang.String" required="true" %>
<%@attribute name="actionResult" type="java.lang.String" required="true" %>

<c:choose>
    <c:when test="${ruleResult == actionResult}">
        <img src="includes/img/run.png" alt="Run">
    </c:when>
    <c:when test="${empty ruleResult}">
    </c:when>
    <c:otherwise>
        <img src="includes/img/stop.png" alt="Will not run">
    </c:otherwise>
</c:choose>