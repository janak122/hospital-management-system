
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Patient Information</title>

<script>
	function clear() {
		document.getElementById("table").style.display = "none";
		document.getElementById("complaint").style.display = "none";
		document.getElementById("rooms").style.display = "none";
	}
	function makeComplaint() {
		clear();
		document.getElementById("complaint").style.display = "";
	}
	function makeDefault() {
		clear();
		document.getElementById("table").style.display = "";
	}
	function allocateRoom() {
		clear();
		document.getElementById("rooms").style.display = "";
	}
	function doMagic() {
		var value = document.getElementById("typeofroom").value;
		document.getElementById("vip").style.display = "none";
		document.getElementById("general").style.display = "none";
		document.getElementById("regular").style.display = "none";
		if ("vip" === value) {
			document.getElementById("vip").style.display = "";
		} else if ("general" === value) {
			document.getElementById("general").style.display = "";
		} else {
			document.getElementById("regular").style.display = "";
		}
	}
</script>
</head>
<body>
	<jsp:include page="../../../links.jsp"></jsp:include>
	<jsp:include page="receptionistHeader.jsp"></jsp:include>
	<jsp:include page="__ViewPatientInfo/links.jsp"></jsp:include>
	<jsp:include page="__ViewPatientInfo/patientInfo.jsp"></jsp:include>

	<div class="container">
		<jsp:include page="__ViewPatientInfo/chiefcomplaintslist.jsp"></jsp:include>
		<jsp:include page="__ViewPatientInfo/addChiefComplaint.jsp"></jsp:include>
		<jsp:include page="__ViewPatientInfo/allocateRoom.jsp"></jsp:include>		
	</div>
</body>
</html>