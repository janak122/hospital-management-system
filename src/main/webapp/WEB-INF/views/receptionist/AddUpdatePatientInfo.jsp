
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Patient's Details</title>

<script>
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#blah')
                .attr('src', e.target.result);
        };
        reader.readAsDataURL(input.files[0]);
    }
}
</script>
</head>
<body>
	<jsp:include page="../../../links.jsp"></jsp:include>
	<jsp:include page="receptionistHeader.jsp"></jsp:include>
	<form class="form"
		action="${pageContext.request.contextPath}/receptionist/registerpatient"
		method="post" enctype="multipart/form-data">

		<div class="container row">
			<!--div4-->
			<div class="input-group">

				<div class="col-sm-3">
					<!--left col-->
					
					<img
						src="${pageContext.request.contextPath}/images/${entity.imgurl}"
						class="img-thumbnail img-responsive" id="blah">
					<div class="ml-2 col-sm-6">
						<input type="file" name="file" class="file" id="img"
							accept="image/x-png,image/jpg,image/jpeg" onchange="readURL(this);"
							title="To Update Profile Picture">
					</div>
				</div>
				<div class="col-sm-9">
					<div class="tab-content">
						<div class="tab-pane active" id="home">
							<div class="row">
								<div class="col-12" style="padding: 0">
									<label for="Id" style="margin: 4px 0px 0px 0px"><strong>Id</strong></label>
									<input type="text" class="form-control" name="userId" id="Id"
										readonly value="${entity.userId}">
								</div>
							</div>
							<div class="row">
								<div class="col-12 " style="padding: 0">
									<label for="name" style="margin: 4px 0px 0px 0px"><strong>Name</strong></label>
									<input type="text" class="form-control" name="name" id="name"
										required value="${entity.name}" title="Enter your first name">
								</div>
							</div>

							<div class="row">
								<div class="col-6 " style="padding: 0">
									<label for="gender" style="margin: 4px 0px 0px 0px"><strong>Gender</strong></label>
									<select class="form-control" name="gender" required
										value="${entity.gender}">
										<option>male</option>
										<option>female</option>
									</select>
								</div>
								<div class="col-6 " style="padding: 0">
									<label for="birthdate" style="margin: 4px 0px 0px 0px"><strong>Birthdate</strong></label>
									<input required type="date" class="form-control" name="birthdate"
										value="${entity.birthdate}" id="birthdate"
										title="enter your birthdate">
								</div>
							</div>

							<div class="row"></div>

							<div class="row">
								<div class="col-12" style="padding: 0">
									<label for="email" style="margin: 4px 0px 0px 0px"><strong>Email</strong></label>
									<input required type="email" class="form-control" name="emailaddress"
										id="email" value="${entity.emailaddress}"
										title="enter your email.">
								</div>
							</div>

							<div class="row">
								<div class="col-12 " style="padding: 0">
									<label for="phoneno" style="margin: 4px 0px 0px 0px"><strong>Phone
											Number</strong></label> <input required type="text" class="form-control" name="phoneno"
										id="phoneno" value="${entity.phoneno}"
										title="enter your mobile number if any.">
								</div>
							</div>

							<div class="row">
								<div class="col-12 " style="padding: 0">
									<label for="text" style="margin: 4px 0px 0px 0px"><strong>Address</strong></label>
									<input required type="text" class="form-control" name="address"
										id="address" value="${entity.address}" title="enter address">
								</div>
							</div>
							<div class="form-group">
								<div class="col-xs-12 ">
									<br>
									<button class="btn btn-lg btn-success" type="submit"
										name="save">Save</button>
									<button class="btn btn-lg" type="reset">Reset</button>
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
		</div>
	</form>
</body>
</html>