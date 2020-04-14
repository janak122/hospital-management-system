<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../../../_adminHeader.jsp"/>

<!DOCTYPE html>
<html>
    <head>
        <title>Staff Table</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bgcolor.css">
    </head>
    <body>
        <div class='container'>
            <ul class='nav'>
                <li class="nav-link alert-success"><a href='${pageContext.request.contextPath}/admin/staff/addupdatestaff/new'>Register New Staff</a></li>
            </ul>
        </div>
        <hr>
        <div class="form-group"><p class="text-center"><h3 style="text-align: center">List of Staff Members</h3></p></div>
        <hr>
        <div class="container">
            <table class="table table-responsive-lg">
                <thead class="thead-light">
                    <tr>
                        <th>SID</th>
                        <th>Job</th>
                        <th>Staff-Members</th>
                        <th>Phone-Number</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="staff" items="${entitylist}">
                        <c:if test="${staff.job!='DOCTOR' and staff.job!='doctor' and staff.job!='admin'}">
                        <tr>
                            <td><a href="${pageContext.request.contextPath}/admin/staff/viewstaffinfo/${staff.userId}">
                                <c:out value="${staff.userId}" /></a></td>
                            <td><c:out value="${staff.job}" /></td>
                            <td><c:out value="${staff.name}" /></td>
                            <td><c:out value="${staff.phoneno}" /></td>
                        </tr>
                        </c:if>
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