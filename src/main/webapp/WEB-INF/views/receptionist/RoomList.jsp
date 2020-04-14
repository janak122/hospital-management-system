<%-- 
    Document   : RoomList
    Created on : 24 Jan, 2020, 10:15:11 PM
    Author     : ACER
--%>
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
		<jsp:param value="Roomno / typeofroom" name="placeholder" />
		<jsp:param value="${EntitiesConstants.ROOM}" name="entity" />
	</jsp:include>
	<div class="container">
		<table class="table table-hover" id="myTable">
			<thead class="thead-light">
				<tr>
					<th>RoomNo.</th>
					<th>Type of Room</th>
					<th>Availability</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="entry" items="${entitylist}">
					<tr>
						<td><a
							href='${pageContext.request.contextPath}/receptionist/info/room/${entry.roomno}'>
								${entry.roomno}</a></td>
						<td><c:out value="${entry.typeofroom}" /></td>
						<td><c:out value="${entry.available}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<jsp:include page="Pagination.jsp">
		<jsp:param value="${EntitiesConstants.ROOM}" name="dest" />
	</jsp:include>
</body>
</html>
