<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.bitlab.finalProject.db.News" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="vendor/head.jsp"%>
    <title><%=newsName%></title>
</head>
<body>
<%@include file="vendor/shapka.jsp"%>
<div class="container mt-3">
    <div class="row mt-3">
        <div class="col-12">
            <%
                ArrayList<News> news= (ArrayList<News>) request.getAttribute("news");
                if(news!=null){
                    for(News n : news){
            %>
            <div class="p-5 mb-3" style="background-color: #c7c6c6">
                <a href="news-details?id=<%=n.getId()%>">
                    <h3><%=n.getTitle()%></h3>
                </a>
                <h5>Category: <strong><%=n.getCategory().getName()%></strong></h5>
                <p><%=n.getContent()%></p>
                <p>Posted by <strong><%=n.getUser().getFullName()%></strong>
                    at <strong><%=n.getPostDate()%></strong></p>
            </div>
            <div class="p-5 mb-3" style="background-color: #c7c6c6">
                <a href="#">Show all comments></a>
            </div>
            <%
                    }
                }
            %>
        </div>
    </div>
</div>
<%@include file="vendor/podval.jsp"%>
</body>
</html>
