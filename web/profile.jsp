
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <%@include file="vendor/head.jsp"%>
    <link rel="stylesheet" type="text/css" href="styles/profile.css">
</head>
<body>
    <%@include file="vendor/shapka.jsp"%>
    <div class="container mt-5">
        <div class="row d-flex justify-content-center">
            <div class="col-md-7">
                <div class="card p-3 py-4">
                    <div class="text-center">
                        <img src="/imgs/no-ava.jpg" width="100" class="rounded-circle">
                    </div>
                    <div class="text-center mt-3">
                        <span class="bg-secondary p-1 px-4 rounded text-while"><%=currentUser.getEmail()%></span>
                        <h5 class="mt-2 mb=0"><%=currentUser.getFullName()%></h5>
                        <%
                            if (currentUser.getRole()==1){
                        %>
                            <span>News Creator</span>
                        <%
                            }else{
                        %>
                            <span>Reader</span>
                        <%
                            }
                        %>
                        <div class="buttons">
                            <button class="btn btn-outline-primary px-4" onclick="location.href='home'">To news</button>
                            <form action="/logout" method="get">
                                <button class="btn px-4 ms-3" type="submit">Log out</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="vendor/podval.jsp"%>
</body>
</html>
