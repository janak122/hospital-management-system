
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
		<jsp:param value="name / job / sid" name="placeholder" />
		<jsp:param value="${EntitiesConstants.STAFF}" name="entity" />
	</jsp:include>
	<div class="container">
		<table class="table table-hover" id="myTable">
			<thead class="thead-light">
				<tr>
					<th>SID</th>
					<th>Job</th>
					<th>Staff-Members</th>
					<th>Phone-Number</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="staff" items="${entitylist}">
					<tr>
						<td><c:out value="${staff.userId}" /></td>
						<td><c:out value="${staff.job}" /></td>
						<td><c:out value="${staff.name}" /></td>
						<td><c:out value="${staff.phoneno}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<jsp:include page="Pagination.jsp">
		<jsp:param value="${EntitiesConstants.STAFF}" name="dest" />
	</jsp:include>
</body>
</html>
