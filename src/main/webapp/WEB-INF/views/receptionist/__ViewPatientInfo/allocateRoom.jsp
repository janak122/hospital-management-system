
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="card" style="display: none" id="rooms">
	<ul class="list-group">
		<li class="list-group-item" style="padding: 6px 16px 6px 20px">
			<button class="btn btn-warning float-right" id="deactive-allocate"
				onclick="makeDefault()">cancle</button>
		</li>
	</ul>
	<c:forEach var="entry" items="${entity.entries}">
		<form
			action="${pageContext.request.contextPath}/receptionist/deallocateRoom">
			<input type="hidden" name="pid" value="${entity.user.userId}">
			<input type="hidden" name="entryid" value="${entry.entryid}">
			<ul class="list-group">
				<li class="list-group-item" style="padding: 6px 16px 6px 20px">
					<strong>${entry.roomno}</strong> : <input type="submit"
					class="btn btn-danger float-right" value="deallocate">
				</li>
			</ul>
		</form>
	</c:forEach>



	<ul class="list-group">
		<li class="list-group-item" style="padding: 6px 16px 6px 20px"><strong>New
				Room ? </strong> <select name="typeofroom" id="typeofroom" required
			onchange="doMagic()">
				<option>vip</option>
				<option>general</option>
				<option>regular</option>
		</select></li>
	</ul>

	<form
		action="${pageContext.request.contextPath}/receptionist/allocateRoom">
		<input type="hidden" name="pid" value="${entity.user.userId}">
		<div id="vip">
			<ul class="list-group">
				<li><strong>RoomNO : </strong><select name="roomno" required>
						<c:forEach var="rmno" items="${entity.vipRooms}">
							<option>${rmno}</option>
						</c:forEach>
				</select> <input type="submit" class="btn btn-success float-right"
					value="submit"></li>
			</ul>
		</div>
	</form>


	<form
		action="${pageContext.request.contextPath}/receptionist/allocateRoom">
		<div id="general" style="display: none">
			<input type="hidden" name="pid" value="${entity.user.userId}">
			<ul class="list-group">
				<li><strong>RoomNO : </strong><select name="roomno" required>
						<c:forEach var="rmno" items="${entity.generalRooms}">
							<option>${rmno}</option>
						</c:forEach>
				</select> <input type="submit" class="btn btn-success float-right"
					value="submit"></li>
			</ul>
		</div>
	</form>


	<form
		action="${pageContext.request.contextPath}/receptionist/allocateRoom">
		<div id="regular" style="display: none">
			<input type="hidden" name="pid" value="${entity.user.userId}">
			<ul class="list-group">
				<li><strong>RoomNO : </strong><select name="roomno" required>
						<c:forEach var="rmno" items="${entity.regularRooms}">
							<option>${rmno}</option>
						</c:forEach>
				</select> <input type="submit" class="btn btn-success float-right"
					value="submit"></li>
			</ul>
		</div>
	</form>

</div>