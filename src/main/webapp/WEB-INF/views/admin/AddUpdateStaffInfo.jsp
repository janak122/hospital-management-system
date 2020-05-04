<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
<script>
	function addFields() {
		var field = document.getElementById('fields');
		var job = document.getElementById('job').value;
		var value = 'none';
		if ("receptionist".localeCompare(job) == 0
				|| "admin".localeCompare(job) == 0) {
			value = "";
		}
		fields.style.display = value;
	}
	function validateInputs() {
		var job = document.getElementById('job').value;
		if ("receptionist".localeCompare(job) == 0
				|| "admin".localeCompare(job) == 0) {
			var passwd = document.getElementById('passwd').value;
			var repasswd = document.getElementById('repasswd').value;
			if (repasswd.localeCompare(passwd) != 0) {
				document.getElementById('pass').style.color = 'red';
				document.getElementById('pass').innerHTML = "*Not Matching Password and Reenter Password";
				return false;
			}
		}
		return true;
	}
</script>
</head>
<body>
	<script>
		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					$('#blah').attr('src', e.target.result);
				};
				reader.readAsDataURL(input.files[0]);
			}
		}
	</script>
	<jsp:include page="../../../links.jsp"></jsp:include>
	<jsp:include page="adminHeader.jsp"></jsp:include>
	<div class="form-group">
		<p class="text-center">
		<h3 style="text-align: center">Staff's Information</h3>
		</p>
	</div>

	<form action="${pageContext.request.contextPath}/admin/process/staff"
		onsubmit="return validateInputs()" method="POST" class="form"
		enctype="multipart/form-data">
		<div class="row">
			<!--div4-->
			<div class="col-sm-3">
				<!--left col-->
				<img
					src="${pageContext.request.contextPath}/images/${userForm.user.imgurl}"
					class="img-thumbnail img-responsive" id="blah">
				<div class="ml-2 col-sm-6">
					<input type="file" name="file" class="file" id="img"
						accept="image/x-png,image/jpg,image/jpeg"
						onchange="readURL(this);" title="To Update Profile Picture">
				</div>
			</div>
			<div class="col-sm-8">
				<div class="tab-content">
					<div class="tab-pane active" id="home">
						<div class="row">
							<div class="col-8">
								<label><strong>Doctor Id : </strong></label><b>
									${userForm.user.userId}</b> <input type="hidden" name="userId"
									value="${userForm.user.userId}">
							</div>
						</div>
						<div class="row">
							<div class="col-8">
								<label for="name" style="margin: 4px 0px 0px 0px"><strong>Name
										: </strong></label> <input type="text" class="form-control" name="name"
									id="name" value="${userForm.user.name}"
									title="Enter your first name">
							</div>
						</div>

						<div class="row">
							<div class="col-4">
								<c:set var="male" value="checked" />
								<c:set var="female" value="" />
								<c:if test="${userForm.user.gender=='female'}">
									<c:set var="female" value="checked" />
								</c:if>
								<label><strong>Gender : </strong></label>
								<div class="custom-control custom-radio">
									<div class="custom-control custom-radio custom-control-inline">
										<input type="radio" class="custom-control-input" id="male"
											name="gender" ${male} value="male"> <label
											class="custom-control-label" for="male">Male</label>
									</div>
									<div class="custom-control custom-radio custom-control-inline">
										<input type="radio" class="custom-control-input" id="female"
											name="gender" ${female} value="female"> <label
											class="custom-control-label" for="female">Female</label>
									</div>
								</div>
							</div>
							<div class="col-4">
								<label for="birthdate"><strong>Birthdate :</strong></label> <input
									type="date" class="form-control" name="birthdate"
									value="${userForm.user.birthdate}" id="birthdate">
							</div>
						</div>

						<div class="row">
							<div class="col-4">
								<label><b>Arrival Time : </b></label> <select name="arrivaltime"
									required class="form-control">
									<option value="08:00 AM">08:00 AM</option>
									<option value="08:30 AM">08:30 AM</option>
									<option value="09:00 AM">09:00 AM</option>
									<option value="09:30 AM">09:30 AM</option>
									<option value="10:00 AM">10:00 AM</option>
									<option value="10:30 AM">10:30 AM</option>
									<option value="04:00 PM">04:00 PM</option>
									<option value="06:00 PM">06:00 PM</option>
									<option value="10:00 PM">10:00 PM</option>
								</select>
							</div>
							<div class="col-4">
								<label><b>Depart Time : </b></label> <select name="departtime"
									required class="form-control">
									<option value="04:00 PM">04:00 PM</option>
									<option value="06:00 PM">06:00 PM</option>
									<option value="07:00 PM">07:00 PM</option>
									<option value="07:45 PM">07:45 PM</option>
									<option value="08:30 PM">08:00 PM</option>
									<option value="09:00 PM">09:00 PM</option>
									<option value="09:30 PM">09:30 PM</option>
									<option value="10:00 PM">10:00 PM</option>
									<option value="10:30 PM">10:30 PM</option>
									<option value="12:00 PM">12:00 PM</option>
									<option value="06:00 AM">06:00 AM</option>
								</select>
							</div>
						</div>

						<div class="row">
							<div class="col-4">
								<label><b>Date of Join : </b></label> <input type="date"
									class="form-control" name="dateofjoin"
									value="${userForm.dateofjoin}">
							</div>
							<div class="col-4">
								<label><b>Salary : </b></label> <input type="number"
									name="salary" class="form-control" required="" maxlength="8"
									value="${userForm.salary}">
							</div>
						</div>

						<div class="row">
							<div class="col-4">
								<label for="phoneno"><strong>Phone Number : </strong></label> <input
									type="number" class="form-control" name="phoneno" id="phoneno"
									required="" value="${userForm.user.phoneno}"
									title="enter your mobile number">
							</div>
							<div class="col-4">
								<label for="email"><strong>Email :</strong></label> <input
									type="email" class="form-control" name="emailaddress"
									id="email" value="${userForm.user.emailaddress}"
									title="enter your email.">
							</div>
						</div>

						<div class="row">
							<div class="col-8">
								<label for="address"><strong>Address :</strong></label> <input
									type="text" class="form-control" name="address" required
									id="address" value="${userForm.user.address}"
									title="enter address">
							</div>
						</div>

						<div class="row">
							<div class="col-8">
								<label for="job"><b>Type of Job : </b></label> <select id="job"
									name="job" required class="form-control" onchange="addFields()">
									<option value="${userForm.job}" selected>${userForm.job}</option>
									<option value="admin">Admin</option>
									<option value="receptionist">Receptionist</option>
									<option value="nurse">Nurse</option>
									<option value="security guard">Security Guard</option>
									<option value="peon">Peon</option>
								</select>
							</div>
						</div>
						<div id="fields" class="row" style="display: none">
							<div class="col-4">
								<label for="password"><strong>Password :</strong></label> <input
									type="password" class="form-control" name="password"
									id="passwd" value="${login.password}" placeholder="Password"
									title="enter your password.">
							</div>
							<div class="col-4">
								<label for="password2"><strong>Reenter Password
										:</strong></label> <input type="password" class="form-control"
									name="repassword" id="repasswd" value="${login.password}"
									placeholder="Reenter Password" title="confirm your password.">
							</div>
						</div>
						<div class="row">
							<div class="col">
								<p id='pass'></p>
							</div>
						</div>
						<div class="row" style="text-align: center">
							<div class="col-8">
								<br>
								<button class="btn btn-lg btn-success" type="submit"
									onclick="validateInputs()" name="save">Save</button>
								<button class="btn btn-lg btn-info" type="reset">Reset</button>
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
	</form>
</body>
</html>