<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <%@include file="vendor/head.jsp"%>
</head>
<body>
    <%@include file="vendor/shapka.jsp"%>
    <style>
        .password-input{
            position: relative;
        }
        .password-input input[type="password"]{
            padding-right: 30px;
        }
        .password-input .toggle-password{
            position: absolute;
            top: 50%;
            right:5px;
            transform: translateY(-50%);
            cursor: pointer;
        }
    </style>
    <div class="container">
        <div class="row mt-3">
            <div class="col-6 mx-auto">
                <%
                    String emailError=request.getParameter("emailerror");
                    if(emailError!=null){
                %>
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    Incorrect email, try again!
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
                <%
                    }
                %>
                <%
                    String passwordError=request.getParameter("passworderror");
                    if(passwordError!=null){
                %>
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    Incorrect password, try again!
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
                <%
                    }
                %>
                <form action="login" method="post">
                    <div class="row">
                        <div class="col-12">
                            <label>EMAIL:</label>
                        </div>
                    </div>
                    <div class="row mt-2">
                        <div class="col-12">
                            <input type="email" class="form-control" name="email">
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-12">
                            <label>PASSWORD:</label>
                        </div>
                    </div>
                    <div class="row mt-2">
                        <div class="col-12">
                            <div class="password-input">
                                <input type="password" class="form-control" id="password" name="password">
                                <img class="toggle-password" src="/imgs/free-icon-eye-535193.png" alt="show" onclick="passwordVisibility()"
                                     style="width: 5%; opacity: 0.35;">
                            </div>
                        </div>
                    </div>
                    <div class="mt-3">
                        <div class="col-12">
                            <button type="submit" class="btn btn-success">SIGN IN</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <%@include file="vendor/podval.jsp"%>
    <script>
        function passwordVisibility(){
            var passwordInput=document.getElementById("password");
            var but=document.querySelector('.toggle-password');

            if(passwordInput.type=="password"){
                passwordInput.type="text";
                but.src="/imgs/free-icon-eye-7615155.png";
            }else{
                passwordInput.type="password";
                but.src="/imgs/free-icon-eye-535193.png";
            }
        }
    </script>
</body>
</html>
