
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>

</head>
<body>
	<jsp:include page="../../../links.jsp"></jsp:include>
	<jsp:include page="receptionistHeader.jsp"></jsp:include>

	
	<div class="container">
		<ul class="nav">
			<li class="nav-link alert-success"><a
				href='${pageContext.request.contextPath}/receptionist/registerpatient/new'>RegisterNewPatient</a></li>
		</ul>
	</div>
	<jsp:include page="SearchBox.jsp">
		<jsp:param value="Patient's name / pid / emailId / gender "
			name="placeholder" />
		<jsp:param value="${EntitiesConstants.PATIENT}" name="entity" />
	</jsp:include>
	<div class="container">
		<c:choose>
			<c:when test="${empty entitylist}">
				<c:out value="no result found !"></c:out>
			</c:when>
			<c:otherwise>
				<table class="table table-hover" id="myTable">
					<thead class="thead-light">
						<tr>
							<th>PID</th>
							<th>Gender</th>
							<th>Patient Name</th>
							<th>Phone Number</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="patient" items="${entitylist}">
							<tr>
								<td><a
									href='${pageContext.request.contextPath}/receptionist/info/patient/${patient.userId}'>
										<c:out value="${patient.userId}" />
								</a></td>
								<td><c:out value="${patient.gender}" /></td>
								<td><c:out value="${patient.name}" /></td>
								<td><c:out value="${patient.phoneno}" /></td>
							</tr>

						</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
	<jsp:include page="Pagination.jsp">
		<jsp:param value="${EntitiesConstants.PATIENT}" name="dest" />
	</jsp:include>
</body>
</html>
