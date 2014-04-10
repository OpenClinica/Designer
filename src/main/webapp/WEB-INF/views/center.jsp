<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="ui-layout-center" style="display: none;">
    <!--<h3 class="ui-widget-header">Center Pane</h3>-->
    <!--<p class="ui-layout-content ui-widget-content">-->
        <div  class="ui-layout-content ui-widget-content" id="theCenter">
            <!--  <span id="tabs2"></span> USE THIS IF NO TABS INVOLVED-->
            <div id="tabs2">
                <ul>
                    <li><a href="<c:url value='/ruleBuilder/ruleBuilderFormA' />">Designer</a></li>
                    <li><a href="<c:url value='/ruleBuilder/xmlForm' />">XML</a></li>
                    <li><a href="<c:url value='/ruleBuilder/testForm' />">Test</a></li>
                </ul>
            </div> 
            <div id="divId" title="Choose a Rule to edit">
                <iframe id="modalIframeId" width="100%" height="100%" marginWidth="0" marginHeight="0" frameBorder="0" scrolling="auto" title="Dialog Title">Your browser does not suppr</iframe>
            </div> 
            <div id="sessionTimeoutDialog" title="Session Expiration Alert!"><div id="counter"></div><div id="expirationMessage"></div></div>
            <%-- DIV used as container for OID already exists in system --%>
            <div id="duplicateRuleDialog" title="<fmt:message key="duplicate_oid_message_dialog_header"/>"><fmt:message key="duplicate_oid_message"/></div>
            <%-- DIV used as container for Reset confirmation --%>
            <div id="verifyResetDialog" title="<fmt:message key="verify_reset_dialog_header"/>"><fmt:message key="verify_reset_dialog_message"/></div>
            <%-- DIV used as container for Response Options Values, Item metadata --%>
            <div id="dialogResponseOptionsValues" title="Response Options/ Response Values">
                <p id="dialogResponseOptionsValuesHtml">...</p>
            </div>  
        </div>
    <!--</p>-->
</div> 