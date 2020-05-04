
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
<style type="text/css">

</style>
</head>
<body>
	<jsp:include page="../../../links.jsp"></jsp:include>
	<jsp:include page="adminHeader.jsp"></jsp:include>

	<jsp:include page="SearchBox.jsp">
		<jsp:param value="Patient's name / pid / emailId / gender "
			name="placeholder" />
		<jsp:param value="${EntitiesConstants.PATIENT}" name="entity" />
	</jsp:include>
	<div class="container">
		<c:choose>
			<c:when test="${empty entitylist}">
				<h1 style="position:absolute;left:50%;top:50%;
				margin-top:-100px;margin-left:-200px;
				text-align: center;font-family: Pacifico,Calibri;
				">Sorry... No Result Found !!!</h1>
			</c:when>
			<c:otherwise>
				<table class="table table-hover  table-striped" id="myTable">
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
									href='${pageContext.request.contextPath}/admin/info/patient/${patient.userId}'>
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
