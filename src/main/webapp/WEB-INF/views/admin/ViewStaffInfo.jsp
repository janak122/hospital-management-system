<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Doctor's Information</title>
</head>
<body>
	<jsp:include page="../../../links.jsp"></jsp:include>
	<jsp:include page="adminHeader.jsp"></jsp:include>
	<div class="container">
		<ul class="nav">
			<li class="nav-item"><a
				href="${pageContext.request.contextPath}/admin/register/staff/${entity.user.userId}"
				class="btn btn-warning">Update</a></li>
			<li class="nav-item"><a
				href="${pageContext.request.contextPath}/admin/delete/staff/${entity.user.userId}"
				class="btn btn-danger">Delete</a></li>
		</ul>
		<div class="form-group">
			<p class="text-center">
			<h3 style="text-align: center">Staff's Information</h3>
			</p>
		</div>
	</div>
	<div class="row">
		<!--div4-->
		<div class="col-sm-3">
			<!--left col-->
			<img
				src="${pageContext.request.contextPath}/images/${entity.user.imgurl}"
				class="img-thumbnail img-responsive" id="blah">
			<!-- img src="${pageContext.request.contextPath}/resources/images/logo1.jpg" class="img-responsive img-thumbnail" 
                     width="400" height="400"-->
		</div>
		<div class="col-sm-8">
			<div class="tab-content">
				<div class="tab-pane active" id="home">
					<div class="row">
						<div class="col-4">
							<label><strong>Doctor Id : </strong></label><b>
								${entity.user.userId}</b> <input type="hidden" name="userId"
								value="${entity.user.userId}">
						</div>
						<div class="col-4" style="text-align: right">
							<label><strong>Age : </strong></label> ${entity.age}
						</div>
					</div>
					<div class="row">
						<div class="col-8">
							<label for="name" style="margin: 4px 0px 0px 0px"><strong>Name
									: </strong></label> <input type="text" class="form-control" name="name"
								id="name" value="${entity.user.name}"
								title="Enter your first name" readonly="">
						</div>
					</div>

					<div class="row">
						<div class="col-4">
							<label><strong>Gender : </strong></label> <input type="text"
								name="gender" class="form-control" readonly=""
								value="${entity.user.gender}">
						</div>
						<div class="col-4">
							<label for="birthdate"><strong>Birthdate :</strong></label> <input
								type="date" class="form-control" name="birthdate"
								value="${entity.user.birthdate}" id="birthdate" readonly=""
								title="enter your birthdate">
						</div>
					</div>

					<div class="row">
						<div class="col-4">
							<label><b>Arrival Time : </b></label> <input type="text"
								class="form-control" name="arrivaltime" id="arrivaltime"
								readonly=""
								value="<fmt:formatDate type = 'time' timeStyle='short' value = '${entity.arrivaltime}' />">
						</div>
						<div class="col-4">
							<label><b>Depart Time : </b></label> <input type="text"
								class="form-control" name="departtime" id="departtime"
								readonly=""
								value="<fmt:formatDate type = 'time' timeStyle='short' value = '${entity.departtime}' />">
						</div>
					</div>

					<div class="row">
						<div class="col-4">
							<label><b>Date of Join : </b></label> <input type="date"
								class="form-control" name="dateofjoin"
								value="${entity.dateofjoin}" readonly="">
						</div>
						<div class="col-4">
							<label><b>Salary : </b></label> <input type="number"
								name="salary" class="form-control" required="" maxlength="8"
								value="${entity.salary}" readonly="">
						</div>
					</div>

					<div class="row">
						<div class="col-4">
							<label for="phoneno"><strong>Phone Number : </strong></label> <input
								type="number" class="form-control" name="phoneno" id="phoneno"
								readonly value="${entity.user.phoneno}"
								title="enter your mobile number">
						</div>
						<div class="col-4">
							<label for="email"><strong>Email :</strong></label> <input
								type="email" class="form-control" name="emailaddress" id="email"
								readonly="" value="${entity.user.emailaddress}"
								title="enter your email.">
						</div>
					</div>

					<div class="row">
						<div class="col-8">
							<label for="address"><strong>Address :</strong></label> <input
								type="text" class="form-control" name="address" readonly=""
								id="address" value="${entity.user.address}"
								title="enter address">
						</div>
					</div>

					<div class="row">
						<div class="col-8">
							<label for="job"><strong>Job :</strong></label> <input
								type="text" class="form-control" name="job" readonly="" id="job"
								value="${entity.job}">
						</div>
					</div>

				</div>
				<!--/tab-pane-->
			</div>
			<!--/tab-pane-->
		</div>
		<!--/tab-content-->
	</div>
	<!--div4-->
</body>
</html>