
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table class="table table-responsive-md table-info table-hover"
	id="table">
	<tr>
		<th><b>Date</b></th>
		<th style="margin-right: 0px; margin-left: 0px"><b>Chief-Complaint</b></th>
		<th style="margin-right: 0px; margin-left: 0px"><b>Status</b></th>
		<th style="margin-right: 0px; margin-left: 0px"><b>Doctor's
				ID</b></th>
		<th style="margin-right: 0px; margin-left: 0px"><b>Doctor's
				Prescription</b></th>
		<th></th>
	</tr>
	<c:forEach var="chiefcomplaint" varStatus="status"
		items="${entity.complaint}">
		<tr>
			<td><fmt:formatDate value="${chiefcomplaint.creationdate}"
					type="date" /></td>
			<td style="margin-right: 0px; margin-left: 0px">${chiefcomplaint.chief_complaint}</td>
			<td style="margin-right: 0px; margin-left: 0px">${chiefcomplaint.status}</td>
			<td style="margin-right: 0px; margin-left: 0px">${chiefcomplaint.doctorid}</td>
			<td style="margin-right: 0px; margin-left: 0px">${chiefcomplaint.prescription}</td>
			<td><a
				href="${pageContext.request.contextPath}/receptionist/delete/patientcomplaint/${chiefcomplaint.caseid}?entitykey=${entity.user.userId}"
				class="btn btn-danger">delete</a></td>
		</tr>
	</c:forEach>
</table>