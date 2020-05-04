
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<form class="form-inline my-2" action="<c:url value="/admin/list/${entity}/query"/>">
		
		<input class="form-control " alt="submit" type="search" name="q"
			onkeyup="myFunction()" id="myInput" placeholder="${param.placeholder}" aria-label="Search" style="width: 500px">
		<input type="image" height="35" width="35" src="${pageContext.request.contextPath}/resources/images/search.jpg" alt="submit">
		
	</form>
</div>
