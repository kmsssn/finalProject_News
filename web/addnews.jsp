<%@ page import="kz.bitlab.finalProject.db.Categories" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.bitlab.finalProject.db.DBConnection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add News</title>
    <%@include file="vendor/head.jsp"%>
</head>
<body>
    <%@include file="vendor/shapka.jsp"%>
    <div class="container mt-5">
        <div class="row">
            <div class="col-6 mx-auto">
                <%
                    String success=request.getParameter("success");
                    if (success!=null){
                %>
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    News added successfully!
                    <button type="button" class="btn btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
                <%
                    }
                %>
                <%
                    String error=request.getParameter("error");
                    if (error!=null){
                %>
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    Something went wrong!
                    <button type="button" class="btn btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
                <%
                    }
                %>
                <form action="/addnews" method="post">
                    <div class="row">
                        <div class="col-12">
                            <label>TITLE:</label>
                        </div>
                    </div>
                    <div class="row mt-2">
                        <div class="col-12">
                            <input type="text" class="form-control" name="title">
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-12">
                            <label>CONTENT:</label>
                        </div>
                    </div>
                    <div class="row mt-2">
                        <div class="col-12">
                            <textarea class="form-control" name="content" required rows="10"></textarea>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-12">
                            <label>CATEGORY:</label>
                            <select name="categoryId">
                                <option value="">SELECT:</option>
                                <%
                                    ArrayList<Categories> categories= DBConnection.getCategories();
                                    for (Categories category : categories){
                                %>
                                <option value="<%=category.getId()%>"><%=category.getName()%></option>
                                <%
                                    }
                                %>
                            </select>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-12">
                            <button type="submit" class="btn btn-success">ADD NEWS</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <%@include file="vendor/podval.jsp"%>
</body>
</html>
