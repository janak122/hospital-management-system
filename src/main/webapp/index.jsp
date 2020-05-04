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
	var $myGroup = $('#myGroup');
	$myGroup.on('show', '.collapse', function() {
		$myGroup.find('.collapse.in').collapse('hide');
	});
</script>
<style type="text/css">
</style>
</head>
<body>

	<jsp:include page="links.jsp"></jsp:include>
	<div id="myGroup">
		<button class="btn dropdown" data-toggle="collapse"
			data-target="#keys">
			<i class="icon-chevron-right"></i> Keys <span
				class="badge badge-info pull-right">X</span>
		</button>
		<button class="btn dropdown" data-toggle="collapse"
			data-target="#attrs">
			<i class="icon-chevron-right"></i> Attributes
		</button>
		<button class="btn dropdown" data-toggle="collapse"
			data-target="#edit">
			<i class="icon-chevron-right"></i> Edit Details
		</button>

		<div class="accordion-group">
			<div class="collapse indent" id="keys" data-parent="#myGroup">
				keys</div>

			<div class="collapse indent" id="attrs" data-parent="#myGroup">
				attrs</div>

			<div class="collapse" id="edit" data-parent="#myGroup">edit</div>
		</div>
	</div>
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
