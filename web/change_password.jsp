<%-- 
    Document   : change_password
    Created on : Aug 28, 2020, 8:01:32 PM
    Author     : khatr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Management Information System</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <jsp:include page="head_links.jsp"/>
        <style>
            .fa-pencil-alt {
                color: white;
            }
            .fa-trash {
                color: white;
            }
        </style>
    </head>
    <body class="hold-transition sidebar-mini">
        <!-- Site wrapper -->
        <div class="wrapper">
            <jsp:include page="student_navbar.jsp"/>
            <jsp:include page="student_topnavbar.jsp"/>
            <%
                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                response.setHeader("pragma", "no-cache");
                response.setHeader("Expires", "0");
                if (session.getAttribute("student") != null) {
            %>
            <input type="hidden" id="studentSessionId" value="${sessionScope.student.studentId}"/>
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <div class="container-fluid">
                        <div class="row mb-2">
                            <div class="col-sm-6">
                                <h1>Update Password</h1>
                            </div>
                            <div class="col-sm-6">
                                <ol class="breadcrumb float-sm-right">
                                    <li class="breadcrumb-item"><a href="student_dashboard.jsp">Home</a></li>
                                    <li class="breadcrumb-item active">Change Password</li>
                                </ol>
                            </div>
                        </div>
                    </div><!-- /.container-fluid -->
                </section> 
                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card card-info">
                                <div class="card-header">
                                    <h3 class="card-title">Change Password</h3>

                                    <div class="card-tools">
                                        <button type="button" class="btn btn-tool" data-card-widget="collapse" data-toggle="tooltip" title="Collapse">
                                            <i class="fas fa-minus"></i></button>
                                    </div>
                                </div>
                                <form id="changePasswordForm" method="POST">
                                    <input type="hidden" value="${sessionScope.student.studentId}" id="studentId">
                                    <input type="hidden" value="studentPasswordChange" id="form">
                                    <div class="card-body">
                                        <div class="form-group">
                                            <label for="inputCurrentPassword">Current Password*</label>
                                            <input name="changePassword" type="password" id="inputCurrentPassword" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label for="inputNewPassword">New Password*</label>
                                            <input name="newPassword" type="password" id="inputNewPassword" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label for="inputConfirmPassword">Confirm New Password*</label>
                                            <input name="confirmPassword" type="password" id="inputConfirmPassword" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <input id="changeBtn" type="submit" value="Update Password" class="btn btn-success float-right" >
                                        </div>


                                    </div>
                                    <!-- /.card-body -->
                                </form>

                            </div>
                            <!-- /.card -->
                        </div>

                    </div>

                </section>
            </div>
            <!-- /.content-wrapper -->
            <%
                } else {
                    // If Session Expires

                    response.sendRedirect("login.jsp");
                }
            %>



            <!-- Control Sidebar -->
            <aside class="control-sidebar control-sidebar-dark">
                <!-- Control sidebar content goes here -->
            </aside>
            <!-- /.control-sidebar -->   
        </div>
        <!-- ./wrapper -->

        <jsp:include page="bottom_links.jsp"/>
        <!-- page script -->
        <script src="js/user.js"></script>
    </body>
</html>