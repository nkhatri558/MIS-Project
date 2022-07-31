<%-- 
    Document   : login
    Created on : Aug 8, 2020, 4:07:21 PM
    Author     : khatr
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>MIS Login</title>
        <link href="https://fonts.googleapis.com/css?family=Karla:400,700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.materialdesignicons.com/4.8.95/css/materialdesignicons.min.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/login.css">
        <!--SweetAlert2--> 
        <link rel="stylesheet" href="plugins/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css">
        <!--Toastr--> 
        <link rel="stylesheet" href="plugins/toastr/toastr.min.css">
        <!--jQuery--> 
        <script src="plugins/jquery/jquery.min.js"></script>
        <!--SweetAlert2--> 
        <script src="plugins/sweetalert2/sweetalert2.min.js"></script>
        <!--Toastr--> 
        <script src="plugins/toastr/toastr.min.js"></script>
    </head>
    <body>
        <c:if test="${sessionScope.failed == 1}">
            <script>
                $(document).ready(function () {
                    const Toast = Swal.mixin({
                        toast: true,
                        position: 'top-end',
                        showConfirmButton: false,
                        timer: 3000
                    });
                    Toast.fire({
                        icon: 'error',
                        title: 'Invalid Email or Password'
                    });
                });

            </script>
        </c:if>
        <main class="d-flex align-items-center min-vh-100 py-3 py-md-0 "style="background: linear-gradient(to right, #1d976c, #99d9e8);">
            <div class="container">
                <div class="card login-card">
                    <div class="row no-gutters">
                        <div class="col-md-7">
                            <img src="assets/images/bg.jpg" alt="login" class="login-card-img">
                        </div>
                        <div class="col-md-5">
                            <div class="card-body">
                                <div class="brand-wrapper">
                                    <img src="assets/images/muet.png" alt="logo" class="logo">
                                </div>
                                <p class="login-card-description">Sign into your account</p>
                                <form action="UserController" method="post">
                                    <div class="form-group">
                                        <label for="inputEmail" class="sr-only">Email</label>
                                        <input type="email" name="email" id="inputEmail" class="form-control" placeholder="Email address">
                                    </div>
                                    <div class="form-group mb-4">
                                        <label for="inputPassword" class="sr-only">Password</label>
                                        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="***********">
                                    </div>
                                    <input name="action" type="hidden" value="login"></input>
                                    <input name="login" id="login" class="btn btn-block login-btn mb-4" type="submit" value="Login">
                                </form>
                                <a href="#!" class="forgot-password-link">Forgot password?</a>
                                <p class="login-card-footer-text">Don't have an account? <a href="#!" class="text-reset">Register here</a></p>
                                <nav class="login-card-footer-nav">
                                    <a href="#!">Terms of use.</a>
                                    <a href="#!">Privacy policy</a>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
        <jsp:include page="bottom_links.jsp"/>
        <script src="js/user.js"></script>
    </body>
</html>

