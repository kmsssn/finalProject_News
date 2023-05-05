
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <%@include file="vendor/head.jsp"%>
</head>
<body>
    <%@include file="vendor/shapka.jsp"%>
    <div class="container">
        <div class="row mt-3">
            <div class="col-12">
                <h3 class="text-center">PROFILE PAGE OF
                    <%=currentUser.getFullName()%>
                </h3>
            </div>
        </div>
    </div>
    <%@include file="vendor/podval.jsp"%>
</body>
</html>
