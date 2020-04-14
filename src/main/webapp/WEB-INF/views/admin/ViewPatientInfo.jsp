
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../../../_adminHeader.jsp"/>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Patient Information</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap-grid.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bgcolor.css">
    </head>
    <body>
        <div class="container">
            <ul class="nav">
                <li class="nav-item"><a href="${pageContext.request.contextPath}/admin/patientlist/1" class="btn btn-info">Back</a></li>
            </ul>
        </div>  
        <div class="container">
            <hr>
            <div class="form-group"><p class="text-center"><h3 style="text-align: center">Patient's Information</h3></p></div>
            <hr>
            <dl class="row">
                <dt class="col-sm-2">Name : </dt><dd class="col">${entity.user.name}</dd>
                <dt class="col-sm-2 ">Age : </dt><dd class="col">${entity.age}</dd>
            </dl>
            <dl class="row">
                <dt class="col-sm-2">Gender : </dt><dd class="col">${entity.user.gender}</dd>
                <dt class="col-sm-2">Room Number : </dt><dd class="col">#here make list#</dd>
            </dl>
            <dl class="row">
                <dt class="col-sm-2">Phone Number :</dt> <dd class="col">${entity.user.phoneno}</dd>
                <dt class="col-sm-2">Address : </dt><dd class="col">${entity.user.address}</dd>
            </dl>
        </div>
        <div class="container">
            <table class="table table-responsive-md table-info table-hover" id="table">
                <tr class="bg-info">
                    <th><b>Date</b></th>
                    <th style="margin-right: 0px;margin-left: 0px"><b>Chief-Complaint</b></th>
                    <th style="margin-right: 0px;margin-left: 0px"><b>Status</b></th>
                    <th style="margin-right: 0px;margin-left: 0px"><b>Doctor's ID</b></th>
                    <th style="margin-right: 0px;margin-left: 0px"><b>Doctor's Prescription</b></th>                    
                </tr>
                <c:forEach var="chiefcomplaint"  varStatus="status" items="${entity.complaint}">
                    <tr><td><fmt:formatDate value="${chiefcomplaint.creationdate}" type="date"/></td>
                        <td style="margin-right: 0px;margin-left: 0px">${chiefcomplaint.chief_complaint}</td>
                        <td style="margin-right: 0px;margin-left: 0px">${chiefcomplaint.status}</td>
                        <td style="margin-right: 0px;margin-left: 0px">${chiefcomplaint.doctorid}</td>
                        <td style="margin-right: 0px;margin-left: 0px">${chiefcomplaint.prescription}</td>
                    </tr> 
                </c:forEach>
            </table>
        </div>
    </body>
</html>