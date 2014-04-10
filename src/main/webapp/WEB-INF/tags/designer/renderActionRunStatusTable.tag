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
				<td colspan="100%"><fmt:message key="label_specify_mode" /></td>
			</tr>
			<tr>
				<th><fmt:message key="label_admin_data_entry" /></th>
				<th><fmt:message key="label_initial_data_entry" /></th>
				<th><fmt:message key="label_double_data_entry" /></th>
				<%-- <th><fmt:message key="label_import_data_entry" /></th>  --%>
				<th><fmt:message key="label_batch" /></th>
			</tr>
			<tr>
				<td><form:checkbox path="${pathPrefix}.run.administrativeDataEntry" /></td>
				<td><form:checkbox path="${pathPrefix}.run.initialDataEntry" /></td>
				<td><form:checkbox path="${pathPrefix}.run.doubleDataEntry" /></td>
				<%-- <td><form:checkbox path="${pathPrefix}.run.importDataEntry" /></td>  --%>
				<form:hidden path="${pathPrefix}.run.importDataEntry"/>
				<td><form:checkbox path="${pathPrefix}.run.batch" /></td>
			</tr>
		</tbody>
	</table>
</div>