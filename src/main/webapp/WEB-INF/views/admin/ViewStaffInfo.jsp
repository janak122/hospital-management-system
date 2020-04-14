<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../../../_adminHeader.jsp"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Doctor's Information</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bgcolor.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-grid.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/javascript/main.js">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/javascript/jquery.js">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/javascript/bootstrap.min.js">
    </head>
    <body>
        <div class="container">
            <ul class="nav">
                <li class="nav-item"><a href="${pageContext.request.contextPath}/admin/stafflist" class="btn btn-info">Back</a></li>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/admin/staff/addupdatestaff/${userForm.user.userId}" class="btn btn-warning">Update</a></li>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/admin/delete/staff/${userForm.user.userId}" class="btn btn-danger">Delete</a></li>
            </ul>
            <hr>
            <div class="form-group"><p class="text-center"><h3 style="text-align: center">Doctor's Information</h3></p></div>
            <hr>
        </div>
        <div class="row"><!--div4-->
            <div class="col-sm-3"><!--left col-->
                <img src="${pageContext.request.contextPath}/resources/images/logo1.jpg" class="img-responsive img-thumbnail" 
                     width="400" height="400"> 
            </div>
            <div class="col-sm-8">  
                <div class="tab-content">
                    <div class="tab-pane active" id="home">         
                        <div class="row">
                            <div class="col-4">
                                <label><strong>Doctor Id : </strong></label><b> ${userForm.user.userId}</b>
                                <input type="hidden" name="userId" value="${userForm.user.userId}">
                            </div>
                            <div class="col-4" style="text-align: right">
                                <label><strong>Age : </strong></label> ${userForm.age}
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-8">
                                <label for="name" style="margin:4px 0px 0px 0px"><strong>Name : </strong></label>
                                <input type="text" class="form-control" name="name" id="name" 
                                       value="${userForm.user.name}" title="Enter your first name" readonly="">
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-4">
                                <label><strong>Gender : </strong></label>
                                <input type="text" name="gender" class="form-control" readonly="" value="${userForm.user.gender}">
                            </div>
                            <div class="col-4">
                                <label for="birthdate"><strong>Birthdate :</strong></label>
                                <input type="date" class="form-control" name="birthdate"
                                       value="${userForm.user.birthdate}" id="birthdate" readonly="" title="enter your birthdate">
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-4">        
                                <label><b>Arrival Time : </b></label>
                                <input type="text" class="form-control" name="arrivaltime" id="arrivaltime" readonly=""
                                       value="<fmt:formatDate type = 'time' timeStyle='short' value = '${userForm.arrivaltime}' />">
                            </div>
                            <div class="col-4">
                                <label><b>Depart Time : </b></label>
                                <input type="text" class="form-control" name="departtime" id="departtime" readonly=""
                                       value="<fmt:formatDate type = 'time' timeStyle='short' value = '${userForm.departtime}' />">
                            </div>         
                        </div>

                        <div class="row">
                            <div class="col-4">
                                <label><b>Date of Join : </b></label>
                                <input type="date" class="form-control" name="dateofjoin" value="${userForm.dateofjoin}" readonly="">
                            </div>
                            <div class="col-4">
                                <label><b>Salary : </b></label>
                                <input type="number" name="salary" class="form-control" required=""  maxlength="8"
                                       value="${userForm.salary}" readonly="">
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-4">
                                <label for="phoneno"><strong>Phone Number : </strong></label>
                                <input type="number" class="form-control" name="phoneno" id="phoneno" readonly value="${userForm.user.phoneno}" title="enter your mobile number">
                            </div>
                            <div class="col-4">
                                <label for="email"><strong>Email :</strong></label>
                                <input type="email" class="form-control" name="emailaddress" id="email" readonly=""  value="${userForm.user.emailaddress}" title="enter your email.">
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-8">
                                <label for="address"><strong>Address :</strong></label>
                                <input type="text" class="form-control" name="address" readonly="" id="address" value="${userForm.user.address}" title="enter address">
                            </div>
                        </div>
                            
                        <div class="row">
                            <div class="col-8">
                                <label for="job"><strong>Job :</strong></label>
                                <input type="text" class="form-control" name="job" readonly="" id="job" value="${userForm.job}">
                            </div>
                        </div>

                    </div><!--/tab-pane-->                          
                </div><!--/tab-pane-->
            </div><!--/tab-content-->
        </div><!--div4-->
    </body>
</html>