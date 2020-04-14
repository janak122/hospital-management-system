<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="../../../_doctorHeader.jsp"/>

<!DOCTYPE html>
<html>
    <head>
        <title>Patient's Case Report</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap-grid.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bgcolor.css">
        <script>
            function writePrescription(index) {
                document.getElementById("card" + index).style.display = "";
            }
            function cancelComplaint(index) {
                document.getElementById("card" + index).style.display = "none";
            }
        </script>
    </head>
    <body>
        <div class="container">
            <ul class="nav">
                <li class="nav-item"><a href="${pageContext.request.contextPath}/doctor/patientlist/1" class="btn btn-info">Back</a></li>
            </ul>
        </div>
        <hr>
        <div class="form-group"><p class="text-center"><h3 style="text-align: center">Patient's Information</h3></p></div>
        <hr>
        <dl class="row">
            <dt class="col-sm-1"></dt><dd class="col"></dd>
            <dt class="col-sm-2">Name : </dt><dd class="col">${entity.user.name}</dd>
            <dt class="col-sm-2 ">Age : </dt><dd class="col">${entity.age}</dd>
        </dl>
        <dl class="row">
            <dt class="col-sm-1"></dt><dd class="col"></dd>
            <dt class="col-sm-2">Gender : </dt><dd class="col">${entity.user.gender}</dd>
            <dt class="col-sm-2">Room Number : </dt><dd class="col">#Here give RoomNo. if admitted</dd>
        </dl>
        <dl class="row">
            <dt class="col-sm-1"></dt><dd class="col"></dd>
            <dt class="col-sm-2">Phone Number :</dt> <dd class="col">${entity.user.phoneno}</dd>
            <dt class="col-sm-2">Address : </dt><dd class="col">${entity.user.address}</dd>
        </dl>
        <div class="container">
            <table class="table table-responsive-md table-info table-hover" id="table">
                <tr  class="bg-info">
                    <th><b>Date</b></th>
                    <th style="margin-right: 0px;margin-left: 0px"><b>Chief-Complaint</b></th>
                    <th style="margin-right: 0px;margin-left: 0px"><b>Doctor's ID</b></th>
                    <th style="margin-right: 0px;margin-left: 0px"><b>Status</b></th>
                    <th style="margin-right: 0px;margin-left: 0px"><b>Doctor's Prescription</b></th>                    
                    <th></th>
                </tr>
                <c:forEach var="chiefcomplaint"  varStatus="status" items="${entity.complaint}">
                    
                    <form action="${pageContext.request.contextPath}/doctor/writePrescription" method="POST">    

                        <input type="hidden" name="pid" value="${entity.user.userId}">
                        <tr>
                            <td><fmt:formatDate value="${chiefcomplaint.creationdate}" type="date"/></td>
                            <td style="margin-right: 0px;margin-left: 0px">${chiefcomplaint.chief_complaint}</td>
                            <td style="margin-right: 0px;margin-left: 0px">${chiefcomplaint.doctorid}</td>
                            <td style="margin-right: 0px;margin-left: 0px">${chiefcomplaint.status}</td>
                            <td style="margin-right: 0px;margin-left: 0px">${chiefcomplaint.prescription}</td>
                            <td style="margin-right: 0px;margin-left: 0px">
                                <c:if test="${chiefcomplaint.status=='PENDING' and chiefcomplaint.doctorid==sessionScope.userid}">
                                    <input type="hidden" value="${chiefcomplaint.caseid}" name="caseid">
                                    <button id="write" type="button" class="btn btn-success" onclick="writePrescription(${status.index})">Write Prescription</button>
                                </c:if>    
                            </td>
                        </tr>
                        <tr>
                            <td colspan="6" id="card${status.index}" style="display: none">
                                <div>
                                    <div class='row'>
                                        <div class='col'>
                                            <label><b>Write Prescription : </b></label>
                                            <input type="text" class="form-control" name="prescription" value="${chiefcomplaint.prescription}" required>
                                        </div>
                                    </div>
                                    <div class="row" style="padding: 15px">
                                        <div class="col">
                                            <input type="submit"  name="confirm" value="Submit" class="btn btn-success">
                                            <button id="cancel" type="button" name="cancel" class="btn btn-warning" onclick="cancelComplaint('${status.index}')">Cancel</button>    
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </form>

                </c:forEach>
            </table>
        </div>
    </body>
</html>