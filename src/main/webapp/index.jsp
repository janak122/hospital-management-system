<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Material Design for Bootstrap</title>
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
</head>
<body>

	<jsp:include page="links.jsp"></jsp:include>
	<c:out value="${pageContext.servletContext.contextPath}" />
	<!-- jQuery -->
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<!-- Bootstrap tooltips -->
	<script type="text/javascript" src="js/popper.min.js"></script>
	<!-- Bootstrap core JavaScript -->
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<!-- MDB core JavaScript -->
	<script type="text/javascript" src="js/mdb.min.js"></script>
	<!-- Your custom scripts (optional) -->
	<script type="text/javascript"></script>

</body>
</html>
