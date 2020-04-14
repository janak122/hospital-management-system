<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <c:set var="path" value="${initParam['path']}"/>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bgcolor.css">    
    </head>
    <body>
        <c:set var="destinations" value="patientlist/1;;doctorlist;;stafflist;;profile/${sessionScope.userid}"/>
        <c:set var="links" value="Patients;;Doctors;;Staff;;My Profile"/>
        
        <c:set var="destinations" value="${fn:split(destinations,';;')}"/>
        <c:set var="links" value="${fn:split(links,';;')}"/>
        <nav class="navbar navbar-expand-md navbar-light bg-light">
            <div class="container-fluid">
                <a class="navbar-brand" href="#"><img src="${pageContext.request.contextPath}/resources/images/logo1.jpg" height="70" width="300"></a>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <c:forEach var="dest" items="${destinations}" varStatus="status">
                            <li class="nav-item"><a href="${pageContext.request.contextPath}/admin/${dest}" class="nav-link">${links[status.index]}</a></li>
                        </c:forEach>
                        <li class="nav-item"><a href="${pageContext.request.contextPath}/logout" class="nav-link">Log Out</a></li>
                    </ul>
                </div>
            </div>  
        </nav>
    </body>
</html>