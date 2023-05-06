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
            <div class="card mt-3">
                <a href="news-details?id=<%=n.getId()%>">
                    <h5 class="card-header"><%=n.getTitle()%></h5>
                </a>
                <div class="card-body">
                    <h6>Category: <%=n.getCategory().getName()%></h6>
                    <p class="card-text"><%=n.getContent()%></p>
                </div>
                <div class="card-footer text-body-secondary">
                    Posted by <%=n.getUser().getFullName()%> at <%=n.getPostDate()%>
                </div>
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
