
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
	<jsp:include page="adminHeader.jsp"></jsp:include>
	<div class="container" style="padding: 0px 0px 5px 15px">
		<ul class="nav">
			<li class="nav-link alert-success"><a
				href='<c:url value="/admin/register/doctor/new"/>'>Register
					New Doctor</a></li>
		</ul>
	</div>
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
					<td><a
						href="${pageContext.request.contextPath}/admin/info/doctor/${doctor.userId}">
							<c:out value="${doctor.userId}" />
					</a></td>
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
