
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="collapse" id="newComplaint" data-parent="#parent">
	<div class="card" id="complaint">
		<form
			action="${pageContext.request.contextPath}/receptionist/chiefcomplaint"
			method="post">
			<div class="card-header" style="padding: 6px 16px 6px 20px">
				<h4>
					Appointment With <b>Dr. </b> <small><select name="did"
						required>
							<c:forEach items="${doctors}" var="did">
								<option>${did}</option>
							</c:forEach>
					</select></small>
				</h4>
			</div>
			<input type="hidden" name="pid" value="${entity.user.userId}">
			<ul class="list-group">
				<li class="list-group-item" style="padding: 6px 16px 6px 20px">
					<strong>Chief Complaint</strong> : <input type="text"
					name="complaint" class="form-inline form-control"
					style="max-width: 300px" placeholder="chief complaint" required>
				</li>
				<li class="list-group-item" style="padding: 6px 16px 6px 20px">
					<strong>Description</strong> : <input type="text"
					name="description" class="form-control" placeholder="Description">
				</li>
				<!--li class="list-group-item" style="padding:6px 16px 6px 20px">
                            <strong>Date</strong> :  <input type="date" style="max-width: 300px" class="form-control" name="appointmentdate" required>
                        </li-->
				<li class="list-group-item" style="padding: 6px 16px 6px 20px">
					<input type="submit" value="submit" class="btn btn-success">
				</li>
			</ul>
		</form>
	</div>
</div>