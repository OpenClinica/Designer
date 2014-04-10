<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div ID="OClogo"> </div>

<table border="0" cellpadding="0" cellspacing="0" class="loginBoxes">
    <tr>
        <td class="loginBox_T"></td>
    </tr>
    <tr>
        <td class="loginBox">
            <div ID="newsBox">
                <h1>Resource Not Found</h1>
                <p>
                    <spring:message code="error_resourcenotfound_problemdescription" />
                </p>
            </div>
        </td>
    </tr>
</table>