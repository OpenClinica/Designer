<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="org.akaza.openclinica.i18n.licensing" var="licensing"/>

<div class="ui-layout-south ui-widget-content" style="display: none;">
    <div style="margin: 0 10px; float: left; width:100px"><a href="https://www.openclinica.com/designer-terms" target="new">Terms of Service</a></div>
    <div style="float: left; margin-left: 85px;"><span class="text">OpenClinica Rules Designer <fmt:message key="Version_release" bundle="${licensing}"/></span></div>
    <div style="margin: 0 10px; float: right;"><span class="text">Version 1.1</span></div>
    <div style="clear: both;"></div>
</div>