<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="designerTags" tagdir="/WEB-INF/tags/designer"%>
<%@attribute name="pathPrefix" type="java.lang.String" required="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>



<div id="tables" class="block" style="margin: 0px;">
	<table>
		<tbody>
			<tr>
				<td colspan="100%"><fmt:message key="label_event_specify_runonstatus_mode" /></td>
			</tr>
			<tr>
				<th><fmt:message key="label_not_scheduled" /></th>
				<th><fmt:message key="label_scheduled" /></th>
				<th><fmt:message key="label_data_entry_started" /></th>
				<th><fmt:message key="label_completed" /></th>
				<th><fmt:message key="label_skipped" /></th>
				<th><fmt:message key="label_stopped" /></th>
			</tr>
			<tr>
				<td><form:checkbox path="${pathPrefix}.runOnStatus.notScheduled" /></td>
				<td><form:checkbox path="${pathPrefix}.runOnStatus.scheduled" /></td>
				<td><form:checkbox path="${pathPrefix}.runOnStatus.dataEntryStarted" /></td>
				<td><form:checkbox path="${pathPrefix}.runOnStatus.completed" /></td>
				<td><form:checkbox path="${pathPrefix}.runOnStatus.skipped" /></td>
				<td><form:checkbox path="${pathPrefix}.runOnStatus.stopped" /></td>
			</tr>
		</tbody>
	</table>
</div>