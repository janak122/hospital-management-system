<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>[[${msg}]]</h1>
	<form method="POST" th:action="@{/playground}" th:object="${order}">
		<h1>Order your taco creations!</h1>
		<%-- div th:if="${#fields.hasErrors()}">
			<span class="validationError"> Please correct the problems
				below and resubmit. </span>
		</div--%>
		<h3>Deliver my taco masterpieces to...</h3>
		<div>
			<label for="name">Name: </label> <input type="text"
				th:field="*{name}" />
		</div>
		<div>
			<label for="street">Street address: </label> <input type="text"
				th:field="*{street}" /> <br />
		</div>
		<div>
			<label for="city">City: </label> <input type="text"
				th:field="*{city}" />
		</div>
		<div>
			<label for="state">State: </label> <input type="text"
				th:field="*{state}" />
		</div>
		<div>
			<label for="zip">Zip code: </label> <input type="text"
				th:field="*{zip}" />
		</div>
		<div>
			<h3>Here's how I'll pay...</h3>
			<label for="ccNumber">Credit Card #: </label> <input type="text"
				th:field="*{ccNumber}" />
		</div>
		<div>
			<label for="ccExpiration">Expiration: </label> <input type="text"
				th:field="*{ccExpiration}" />
		</div>
		<div>
			<label for="ccCVV">CVV: </label> <input type="text"
				th:field="*{ccCVV}" />
		</div>
		<input type="submit" value="Submit order" />
	</form>
</body>
</html>