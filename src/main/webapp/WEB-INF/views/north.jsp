<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="ui-layout-north ui-widget-content" style="display: none; overflow: visible; z-index: 3;">
    <!-- 
    <div style="float: right; margin-right: 160px;">
        <h1>Rule Designer</h1>
    </div>
    <div id="switcher"></div>
    <img src="includes/OpenClinica_Enterprise_xxsmall.gif"></img>
    -->
    <div id="header">
    <div id="branding">
        <p id="name"><a id="home" href="#"><fmt:message key="label_header_rule_designer"/></a></p>
    </div>
    <!-- 
    <div id="actions">
        <ul class="actionCont dropdown right">
            <li class="actionItem"><span>${sessionScope['uiODMContainer'].studyNameAndOid}&nbsp;</span>
                <a href="#" title="" id="usermenu" class="aiButton">&nbsp;</a>
                <div class="dropdownCont" style="visibility: hidden; opacity: 0;">
                    <div class="dcWrapper">
                        <ul>
                            <li><a href="" onclick="return showDialog('${sessionScope['scopedTarget.userPreferences'].editRulesOnOcInstance}')"><fmt:message key="label_header_edit_rules"/></a></li>
                            <li><a href="<c:url value='/access/exit' />"><fmt:message key="label_header_exit"/></a></li>
                        </ul>
                    </div>
                </div>
            </li>
        </ul>
    </div>
    -->
    <div id="actions">
        <div id="menuButton" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="top: 10px;" role="button" aria-disabled="false">
            <span class="ui-button-text">
                <a href="" onclick='$("#verifyResetDialog").dialog("open"); return false;'><fmt:message key="label_header_new_rule"/></a> |
                <a href="" onclick="return showDialog('${sessionScope['scopedTarget.userPreferences'].editRulesOnOcInstance}')"><fmt:message key="label_header_edit_rules"/></a> | 
                <a href="<c:url value='/access/exit' />"><fmt:message key="label_header_exit"/></a>
            </span>
        </div>
    </div>  
    </div>
</div>
