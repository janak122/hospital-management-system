<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="path" value="${initParam['path']}"/>
<!DOCTYPE html>
<html>
    <c:set var="path" value="${initParam['path']}"/>
    <head>
        <title>Navigation</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="#"/>     
        <link rel="stylesheet" href="#"/>      
    </head>
    <body>
        
        <nav class="navbar navbar-expand-md navbar-light bg-light">
            <div class="container-fluid">
                <a class="navbar-brand" href="#"><img src="${pageContext.request.contextPath}/resources/images/logo1.jpg" height="70" width="300"></a>
                
            </div>  
        </nav>
    </body>
</html>
