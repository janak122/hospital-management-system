<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../../../_adminHeader.jsp"/>

<!DOCTYPE html>
<html>
    <head>
        <title>Doctor Table</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bgcolor.css">
    </head>
    <body>
        <div class="container" style="padding: 0px 0px 5px 15px">
            <ul class="nav">
                <li class="nav-link alert-success"><a href='${pageContext.request.contextPath}/admin/doctor/addupdatedoctor/new'>Register New Doctor</a></li>
            </ul>
        </div>
        <hr>
        <div class="form-group"><p class="text-center"><h3 style="text-align: center">List of Doctors</h3></p></div>
        <hr>
        <div class='container'>
            <table class='table table-responsive-lg'>
                <thead class='thead-light'>
                    <tr>
                        <th>Doctor ID</th>
                        <th>Type of Doctor</th>
                        <th>Doctor Name</th>
                        <th>Phone Number</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="doctor" items="${entitylist}">
                    <tr>
                        <td><a href="${pageContext.request.contextPath}/admin/doctor/viewdoctorinfo/${doctor.userId}">
                            <c:out value="${doctor.userId}" /></a></td>
                    <td><c:out value="${doctor.typeofdoctor}" /></td>
                    <td><c:out value="${doctor.name}" /></td>
                    <td><c:out value="${doctor.phoneno}" /></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>  
        <!--        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <c:if test="${pages.currentPage!=1}">
                    <li class="page-item">
                        <a class="page-link" href="${pageContext.request.contextPath}/general/list/patient/${pages.currentPage-1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:forEach var="pageno" items="${pages.pagelist}">
                    <c:choose>
                        <c:when test="${pages.currentPage eq pageno}">
                            <li class="page-item active">
                                <a class="page-link" href="${pageContext.request.contextPath}/general/list/patient/${pageno}">${pageno}</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link" href="${pageContext.request.contextPath}/general/list/patient/${pageno}">${pageno}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${pages.currentPage < pages.totalpages}">
                    <li class="page-item">
                        <a class="page-link" href="${pageContext.request.contextPath}/general/list/patient/${pages.currentPage+1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </nav>-->
    </body>
</html>