<%@page isELIgnored="false" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bgcolor.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap-grid.min.css">
    </head>
    <body>
    <jsp:include page="receptionistHeader.jsp"></jsp:include>
        <p class="text-center">Room no : ${room.roomno}</p><hr>
        <strong>Price per Bed : ${room.pricePerBed}</strong>
        <div class="container">  
            <table class="table table-striped">
                <tr>
                    <th><b>Bed No.</b></th>
                    <th><b>PID</b></th>
                    <th><b>allocate date</b></th>
                </tr>
                <c:forEach var="entry" items="${room.entries}" varStatus="status">
                    <tr>
                        <td>${status.index+1}</td>
                        <td>${entry.patientId}</td>
                        <td>${entry.arrivaldate}</td> 
                    </tr>
                </c:forEach>
            </table>
        </div>       
    </body>
</html>
