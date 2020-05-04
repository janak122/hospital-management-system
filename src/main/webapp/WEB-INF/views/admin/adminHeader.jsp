<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap-grid.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bgcolor.css">

<c:set var="path" value="${initParam['path']}" />

<c:set var="destinations"
	value="${EntitiesConstants.PATIENT}/1;;${EntitiesConstants.DOCTOR}/1;;${EntitiesConstants.STAFF}/1;;profile/${sessionScope.userid}" />
<c:set var="links" value="Patients;;Doctors;;Staff;;My Profile" />

<c:set var="destinations" value="${fn:split(destinations,';;')}" />
<c:set var="links" value="${fn:split(links,';;')}" />
<nav class="navbar navbar-expand-md navbar-light bg-light">
	<div class="container-fluid">
		<a class="navbar-brand" href="#"><img
			src="${pageContext.request.contextPath}/resources/images/logo1.jpg"
			height="70" width="300"></a>
			<!-- Collapse button -->
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<c:forEach var="dest" items="${destinations}" varStatus="status">
					<li class="nav-item"><a
						href="${pageContext.request.contextPath}/admin/list/${dest}"
						class="nav-link">${links[status.index]}</a></li>
				</c:forEach>
				<li class="nav-item"><a
					href="${pageContext.request.contextPath}/logout" class="nav-link">Log
						Out</a></li>
			</ul>
		</div>
	</div>
</nav>
<script>
	function myFunction() {
		// Declare variables
		var input, filter, table, tr, td, i, txtValue;
		input = document.getElementById("myInput");
		filter = input.value.toUpperCase();
		table = document.getElementById("myTable");
		tr = table.getElementsByTagName("tr");

		for (var i = 0; i < tr.length; i++) {
			var value = "none";
			for (var j = 0; j < table.rows[i].cells.length; j++) {
				txtValue = table.rows[i].cells[j].innerHTML;
				if (txtValue.toUpperCase().indexOf(filter) > -1) {
					value = "";
				}
			}
			tr[i].style.display = value;
		}
	}
</script>