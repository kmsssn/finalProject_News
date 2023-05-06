<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.bitlab.finalProject.db.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Details</title>
    <%@include file="vendor/head.jsp"%>
</head>
<body>
    <%@include file="vendor/shapka.jsp"%>
    <div class="container">
        <div class="row mt-3">
            <div class="col-12">
                <%
                    News news= (News) request.getAttribute("news");
                    if (news!=null){
                %>
                <div class="card">
                        <h5 class="card-header"><%=news.getTitle()%></h5>
                    <div class="card-body">
                        <h6>Category: <%=news.getCategory().getName()%></h6>
                        <p class="card-text"><%=news.getContent()%></p>
                    <%
                        if (currentUser != null){
                            if (currentUser.getId() == news.getUser().getId() || currentUser.getRole()==1) {
                    %>
                    <div>
                        <button type="button" class="btn btn-primary btn-sm" data-bs-toggle="modal"
                                data-bs-target="#editNews">
                            EDIT
                        </button>
                        <button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal"
                                data-bs-target="#deleteNews">
                            DELETE
                        </button>
                        <div class="modal fade" id="editNews" data-bs-backdrop="static" data-bs-keyboard="false"
                             tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <form action="/update-news" method="post">
                                        <input type="hidden" name="id" value="<%=news.getId()%>">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="staticBackdropLabel">Edit News</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="row">
                                                <div class="col-12">
                                                    <label>
                                                        TITLE :
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="row mt-2">
                                                <div class="col-12">
                                                    <input type="text" class="form-control" name="title" required
                                                           placeholder="Insert title:" value="<%=news.getTitle()%>">
                                                </div>
                                            </div>
                                            <div class="row mt-3">
                                                <div class="col-12">
                                                    <label>
                                                        CONTENT :
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="row mt-2">
                                                <div class="col-12">
                                                <textarea class="form-control" name="content"
                                                          placeholder="Insert content:" required
                                                          rows="10"><%=news.getContent()%></textarea>
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
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel
                                            </button>
                                            <button class="btn btn-success">Update</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="modal fade" id="deleteNews" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <form action="/delete-news" method="post">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="exampleModalLabel">Delete</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            Are you sure?
                                            <input type="hidden" name="delNews_id" value="<%=news.getId()%>">
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                            <button class="btn btn-danger">Delete</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%
                            }
                        }
                    %>
                        <div class="card-footer text-body-secondary mt-2">
                            Posted by <%=news.getUser().getFullName()%> at <%=news.getPostDate()%>
                        </div>
                    </div>
                </div>
                <%
                    if (currentUser != null) {
                %>
                <div class="mt-3">
                    <form action="/addcomment" method="post">
                        <input type="hidden" name="news_id" value="<%=news.getId()%>">
                        <div class="row">
                            <div class="col-12">
                                <textarea class="form-control" name="comment" placeholder="Comment here"></textarea>
                            </div>
                        </div>
                        <div class="row mt-3">
                            <div class="col-12">
                                <button class="btn btn-success btn-sm">ADD COMMENT</button>
                            </div>
                        </div>
                    </form>
                </div>
                <%
                    }
                %>
                <div class="row">
                    <div class="col-12">
                        <%
                            ArrayList<Comments> comments = (ArrayList<Comments>) request.getAttribute("comments");
                            if (comments != null){
                                for(Comments comment : comments){
                        %>
                        <div class="card p-3 mt-3">
                                <div class="col-md-8">
                                    <div class="d-flex flex-column" id="comment-container">
                                        <div class="bg-white">
                                            <div class="flex-row d-flex">
                                                <img src="imgs/no-ava.jpg" width="50" class="rounded-circle">
                                                <div class="d-flex flex-column justify-content-start ms-3">
                                                    <span class="d-block font-weight-bold">
                                                        <%=comment.getUser().getFullName()%></span>
                                                    <span class="date text-black-50">Public
                                                        <%=comment.getPostDate()%></span>
                                                </div>
                                            </div>
                                            <div class="mt-2">
                                                <p class="comment-text"><%=comment.getComment()%></p>
                                            </div>
                            <%
                                if (currentUser!=null){
                                    if(currentUser.getRole()==1 || currentUser.getId() == news.getUser().getId()){
                            %>
                                    <div>
                            <button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#deleteComment">
                                DELETE
                            </button>
                                            <div class="modal fade" id="deleteComment" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <form action="/delete-comment" method="post">
                                                            <div class="modal-header">
                                                                <h1 class="modal-title fs-5" id="delCom">Delete</h1>
                                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">
                                                                </button>
                                                            </div>
                                                            <div class="modal-body">
                                                                Are you sure?
                                                                <input type="hidden" name="delComment_id" value="<%=comment.getId()%>">
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                                <button class="btn btn-danger">Delete</button>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                            <%
                                    }
                                }
                            %>
                                    </div>
                                </div>
                            </div>
                        </div>
                            <%
                                    }
                                }
                            %>
                    </div>
                </div>
                <%
                    }
                %>
            </div>
        </div>
    </div>
    <%@include file="vendor/podval.jsp"%>
</body>
</html>