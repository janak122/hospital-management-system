
<div class="container">
		<ul class="nav">
			<li class="nav-item"><a
				href="${pageContext.request.contextPath}/receptionist/list/patient/1"
				class="btn btn-info">Back</a></li>
			<li class="nav-item"><a
				href="${pageContext.request.contextPath}/receptionist/registerpatient/${entity.user.userId}"
				class="btn btn-warning">Update</a></li>
			<li class="nav-item"><a
				href="${pageContext.request.contextPath}/receptionist/delete/patient/${entity.user.userId}"
				class="btn btn-danger">Delete</a></li>
			<li class="nav-item"><button class="btn btn-success"
					onclick="makeComplaint()">Add ChiefComplaint</button></li>
			<li class="nav-item"><button class="btn btn-dark"
					onclick="allocateRoom()">My rooms</button></li>
		</ul>
	</div>