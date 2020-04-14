<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../../../_doctorHeader.jsp"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Profile</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bgcolor.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-grid.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/javascript/main.js">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/javascript/jquery.js">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/javascript/bootstrap.min.js">

        <script>
            function myFunction() {

                var file = document.getElementById('img').files[0];
                var reader = new FileReader();
                reader.onload = function (e) {
                    $('.img-responsive').attr('src', e.target.result);
                    var image = document.createElement("img");
                    image.src = e.target.result;
                };
                reader.readAsDataURL(file);
            }
        </script>
    </head>
    <body>
        <div class="container">
            <ul class="nav">
                <li class="nav-item"><a href="${pageContext.request.contextPath}/doctor/patientlist/1" class="btn btn-info">Back</a></li>
            </ul>
            <hr>
            <div class="form-group"><p class="text-center"><h3 style="text-align: center">My Profile</h3></p></div>
            <hr>
        </div>
        <form class="form" action="${pageContext.request.contextPath}/doctor/updateprofile" method="post" >
            <div class="row"><!--div4-->
                <div class="col-sm-3"><!--left col-->
                    <img src="${pageContext.request.contextPath}/resources/images/logo1.jpg" class="img-responsive img-thumbnail" > 
                </div>
                <div class="col-sm-8">  
                    <div class="tab-content">
                        <div class="tab-pane active" id="home">         
                            <div class="row">
                                <div class="col-8">
                                    <label><strong>Doctor Id : </strong></label><b> ${userForm.user.userId}</b>
                                    <input type="hidden" name="userId" value="${userForm.user.userId}">
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
                                    <label><b>Type of Doctor :</b></label>
                                    <input type="text" class="form-control" name="typeofdoctor" id="type" 
                                           value="${userForm.typeofdoctor}" readonly="">
                                </div>
                                <div class="col-4">
                                    <label><b>Degrees : </b></label>
                                    <input type="text" class="form-control" name="degrees" id="name" 
                                           value="${userForm.degrees}" readonly="">
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-4">        
                                    <label><b>Arrival Time : </b></label>
                                    <!--                                    <select name="arrivaltime" required class="form-control">
                                                                            <option value="08:00 AM">08:00 AM</option>
                                                                            <option value="08:30 AM">08:30 AM</option>
                                                                            <option value="09:00 AM" selected>09:00 AM</option>
                                                                            <option value="09:30 AM">09:30 AM</option>
                                                                            <option value="10:00 AM">10:00 AM</option>
                                                                            <option value="10:30 AM">10:30 AM</option>
                                                                            <option value="04:00 PM">04:00 PM</option>
                                                                            <option value="06:00 PM">06:00 PM</option>
                                                                            <option value="10:00 PM">10:00 PM</option>
                                                                        </select>-->
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
                                    <input type="number" class="form-control" name="phoneno" id="phoneno" required="" value="${userForm.user.phoneno}" title="enter your mobile number">
                                </div>
                                <div class="col-4">
                                    <label for="email"><strong>Email :</strong></label>
                                    <input type="email" class="form-control" name="emailaddress" id="email"  value="${userForm.user.emailaddress}" title="enter your email.">
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-8">
                                    <label for="address"><strong>Address :</strong></label>
                                    <input type="text" class="form-control" name="address" required id="address" value="${userForm.user.address}" title="enter address">
                                </div>
                            </div>
                            <div class="row" style="text-align: center">
                                <div class="col-8">
                                    <br>
                                    <button class="btn btn-lg btn-success" type="submit" name="save">Save</button>
                                    <button class="btn btn-lg btn-info" type="reset">Reset</button>
                                </div>
                            </div>

                        </div><!--/tab-pane-->                          
                    </div><!--/tab-pane-->
                </div><!--/tab-content-->
            </div><!--div4-->
        </form>
    </body>
</html>