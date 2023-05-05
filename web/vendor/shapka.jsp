<%@ page import="kz.bitlab.finalProject.db.Users" %>
<%
    Users currentUser=(Users) session.getAttribute("CURRENT_USER");
%>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="/"><%=newsName%></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/">Home</a>
                </li>
                <%
                    if(currentUser!=null){
                %>
                    <%
                        if (currentUser.getRole()==1){
                    %>
                        <li class="nav-item">
                            <a class="nav-link" href="/addnews">Add News</a>
                        </li>
                    <%
                        }
                    %>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <%=currentUser.getFullName()%>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="/profile">My Profile</a></li>
                            <li><a class="dropdown-item" href="/settings">Settings</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="/logout">Logout</a></li>
                        </ul>
                    </li>
                    <%
                        }else{
                    %>
                        <li class="nav-item">
                            <a class="nav-link" href="/login">Login</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/register">Register</a>
                        </li>
                <%
                    }
                %>
            </ul>
        </div>
    </div>
</nav>
<div style="background-color: rgba(105,105,105,0.51); height: 1.5px; width: 90%; margin-left: 5%; margin-top: 0.5%"></div>
