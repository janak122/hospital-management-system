<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../../../_doctorHeader.jsp"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Patient Table</title>    
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bgcolor.css">
    </head>
    <body>
        <hr>
        <div class="form-group"><p class="text-center"><h3 style="text-align: center">Appointments</h3></p></div>
        <hr>
        <div class="container">
            <table class="table table-responsive-lg">
                <thead class="thead-light">
                    <tr>
                        <th>Patient ID</th>
                        <th>Gender</th>
                        <th>Patient Name</th>
                        <th>Phone Number</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="complaint" items="${complaints}">                    
                        <tr>
                            <td><a href='${pageContext.request.contextPath}/doctor/patientinfo/patient/${complaint.userId}'>
                                    <c:out value="${complaint.userId}"/></a></td>
                            <td><c:out value="${complaint.gender}"/></td>
                            <td><c:out value="${complaint.name}"/></td>
                            <td><c:out value="${complaint.phoneno}"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
            
    </body>
</html>
