
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
	<jsp:include page="SearchBox.jsp">
		<jsp:param value="name / typeofdoctor / sid" name="placeholder" />
		<jsp:param value="${EntitiesConstants.DOCTOR}" name="entity" />
	</jsp:include>
	<div class='container'>
		<table class='table table-hover' id="myTable">
			<thead class='thead-light'>
				<tr>
					<th>Doctor ID</th>
					<th>Type of Doctor</th>
					<th>Doctor Name</th>
					<th>Phone Number</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="doctor" items="${entitylist}">
					<tr>
						<td><c:out value="${doctor.userId}" /></td>
						<td><c:out value="${doctor.typeofdoctor}" /></td>
						<td><c:out value="${doctor.name}" /></td>
						<td><c:out value="${doctor.phoneno}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<jsp:include page="Pagination.jsp">
		<jsp:param value="${EntitiesConstants.DOCTOR}" name="dest" />
	</jsp:include>
</body>
</html>
