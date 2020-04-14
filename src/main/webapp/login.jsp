<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="_header.jsp" />
<html>
<head>
<title>Login to hms</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="x-ua-compatible" content="ie=edge">
</head>
<body>
	<jsp:include page="links.jsp"></jsp:include>
	<!-- div class="container">
		<div class="card"-->
			<form action="loginProcess" method="post" class="text-center border border-light p-5">
				<p class="h4 mb-4">Login</p>
				<div class="md-form form-lg">
					<input type="text" name="id" class="form-control form-control-lg">
					<label>UserId</label>
				</div>
				<div class="md-form form-lg">
					<input type="password" name="password"
						class="form-control form-control-lg"> <label>password</label>
				</div>
				<div class="form-group">
					<input type="submit" value="submit" class="btn btn-success">
					<input type="reset" value="Reset" name="reset"
						class="btn btn-warning">
				</div>
			</form>
		<!-- /div>
	</div-->
</body>
</html>
