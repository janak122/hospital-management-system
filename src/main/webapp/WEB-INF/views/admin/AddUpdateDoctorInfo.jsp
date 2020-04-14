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
        <title>${title}</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bgcolor.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-grid.min.css">
        <script src="${pageContext.request.contextPath}/resources/javascript/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/javascript/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/javascript/main.js"></script>
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
            function validateInputs() {

                var passwd = document.getElementById('passwd').value;
                var repasswd = document.getElementById('repasswd').value;
                if (repasswd.localeCompare(passwd) !== 0)
                {
                    document.getElementById('pass').style.color = 'red';
                    document.getElementById('pass').innerHTML = "*Not Matching Password and Reenter Password";
                    return false;
                }
                return true;
            }
        </script>
    </head>
    <body>
        <div class="container">
            <ul class="nav">
                <li class="nav-item"><a href="${pageContext.request.contextPath}${backpath}" class="btn btn-info">Back</a></li>
            </ul>
            <hr>
            <div class="form-group"><p class="text-center"><h3 style="text-align: center">${headername}</h3></p></div>
            <hr>
        </div>
        <form action="${pageContext.request.contextPath}/admin/updatedoctor" onsubmit="return validateInputs()" method="POST"
               class="form" >
            <div class="row"><!--div4-->
                <div class="col-sm-3"><!--left col-->
                    <img src="${pageContext.request.contextPath}/resources/images/logo1.jpg" class="img-responsive img-thumbnail" 
                         width="400" height="400"> 
                    <div class="ml-2 col-sm-6">
                        <input type="file" name="img" class="file" id="img" accept="image/*" onchange="myFunction()" title="To Update Profile Picture">
                    </div>
                </div>
                <div class="col-sm-8">  
                    <div class="tab-content">
                        <div class="tab-pane active" id="home">         
                            <div class="row">
                                <div class="col-8">
                                    <label><strong>Doctor Id : </strong></label><b> ${userForm.user.userId}</b>
                                    <input type="hidden" name="userId" value="${userForm.user.userId}">
                                    <input type="hidden" name="job" value="DOCTOR">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-8">
                                    <label for="name" style="margin:4px 0px 0px 0px"><strong>Name : </strong></label>
                                    <input type="text" class="form-control" name="name" id="name" 
                                           value="${userForm.user.name}" title="Enter your first name">
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-4">
                                    <c:set var="male" value="checked"/>
                                    <c:set var="female" value=""/>
                                    <c:if test="${userForm.user.gender=='female'}">
                                        <c:set var="female" value="checked"/>
                                    </c:if>
                                    <label><strong>Gender : </strong></label>
                                    <div class="custom-control custom-radio">
                                        <div class="custom-control custom-radio custom-control-inline">
                                            <input type="radio" class="custom-control-input" id="male" name="gender" ${male} value="male">
                                            <label class="custom-control-label" for="male">Male</label>
                                        </div>
                                        <div class="custom-control custom-radio custom-control-inline">
                                            <input type="radio" class="custom-control-input" id="female" name="gender" ${female} value="female">
                                            <label class="custom-control-label" for="female">Female</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-4">
                                    <label for="birthdate"><strong>Birthdate :</strong></label>
                                    <input type="date" class="form-control" name="birthdate"
                                           value="${userForm.user.birthdate}" id="birthdate">
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-4">
                                    <label><b>Type of Doctor :</b></label>
                                    <select name="typeofdoctor" required="" class="form-control">
                                        <option value="${userForm.typeofdoctor}">${userForm.typeofdoctor}</option>
                                        <option value="cardiologist">Cardiologist</option>
                                        <option value="orthopedic">Orthopedic</option>
                                        <option value="dentist">Dentist</option>
                                        <option value="neurologist">Neurologist</option>
                                    </select>
                                </div>
                                <div class="col-4">
                                    <label><b>Degrees : </b></label>
                                    <select name="degrees" required="" class="form-control">
                                        <option value="${userForm.degrees}">${userForm.degrees}</option>
                                        <option value="MBBS">MBBS</option>
                                        <option value="MD">MD</option>
                                        <option value="MS">MS</option>
                                        <option value="MDS">MDS</option>
                                        <option value="PHD">PHD</option>
                                    </select>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-4">        
                                    <label><b>Arrival Time : </b></label>
                                    <select name="arrivaltime" required class="form-control">
                                        <option value="08:00 AM">08:00 AM</option>
                                        <option value="08:30 AM">08:30 AM</option>
                                        <option value="09:00 AM">09:00 AM</option>
                                        <option value="09:30 AM">09:30 AM</option>
                                        <option value="10:00 AM">10:00 AM</option>
                                        <option value="10:30 AM">10:30 AM</option>
                                        <option value="04:00 PM">04:00 PM</option>
                                        <option value="06:00 PM">06:00 PM</option>
                                        <option value="10:00 PM">10:00 PM</option>
                                    </select>
                                </div>
                                <div class="col-4">
                                    <label><b>Depart Time : </b></label>
                                    <select name="departtime" required class="form-control">
                                        <option value="04:00 PM">04:00 PM</option>
                                        <option value="06:00 PM">06:00 PM</option>
                                        <option value="07:00 PM">07:00 PM</option>
                                        <option value="07:45 PM">07:45 PM</option>
                                        <option value="08:30 PM">08:00 PM</option>
                                        <option value="09:00 PM">09:00 PM</option>
                                        <option value="09:30 PM">09:30 PM</option>
                                        <option value="10:00 PM">10:00 PM</option>
                                        <option value="10:30 PM">10:30 PM</option>
                                        <option value="12:00 PM">12:00 PM</option>
                                        <option value="06:00 AM">06:00 AM</option>
                                    </select>
                                </div>         
                            </div>

                            <div class="row">
                                <div class="col-4">
                                    <label><b>Date of Join : </b></label>
                                    <input type="date" class="form-control" name="dateofjoin" value="${userForm.dateofjoin}">
                                </div>
                                <div class="col-4">
                                    <label><b>Salary : </b></label>
                                    <input type="number" name="salary" class="form-control" required=""  maxlength="8"
                                           value="${userForm.salary}">
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
                            <div id="fields" class="row">
                                <div class="col-4">
                                    <label for="password"><strong>Password :</strong></label>
                                    <input type="password" class="form-control" required="" name="password" id="passwd" value="${login.password}" placeholder="Password" title="enter your password.">
                                </div>
                                <div class="col-4">
                                    <label for="password2"><strong>Reenter Password :</strong></label>
                                    <input type="password" class="form-control" name="repassword" id="repasswd" value="${login.password}" placeholder="Reenter Password" title="confirm your password.">
                                </div>
                            </div>       
                            <div class="row">
                                <div class="col">
                                    <p id='pass'></p>
                                </div>
                            </div>
                                
                            <div class="row" style="text-align: center">
                                <div class="col-8">
                                    <br>
                                    <button class="btn btn-lg btn-success" type="submit" onclick="validateInputs()" name="save">Save</button>
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