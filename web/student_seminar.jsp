<%-- 
    Document   : student_seminar
    Created on : Aug 21, 2020, 10:13:15 PM
    Author     : khatr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Seminar Details</title>
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
                                <h1>Seminar Details</h1>
                            </div>
                            <div class="col-sm-6">
                                <ol class="breadcrumb float-sm-right">
                                    <li class="breadcrumb-item"><a href="student_dashboard.jsp">Home</a></li>
                                    <li class="breadcrumb-item active">Seminar</li>
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
                                    <h3 class="card-title mt-2">Seminar Details</h3>
                                    <div class="card-tools">
                                        <button id="applySeminarBtn" class="btn btn-info">Apply For Seminar</button>
                                    </div>
                                    
                                </div>
                                <!-- /.card-header -->
                                <div class="card-body">
                                    <table id="example1" class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                                <th>Seminar Title</th>
                                                <!--<th>Thesis Title</th>-->
                                                <th>Date Applied</th>
                                                <th>Date Conducted</th>
                                                <th>Remarks</th>
                                                <th>Comments</th>
                                            </tr>
                                        </thead>
                                        <tbody id="tab">

                                        </tbody>
                                        <tfoot>
                                            <tr>
                                               <th>Seminar Title</th>
                                                <!--<th>Thesis Title</th>-->
                                                <th>Date Applied</th>
                                                <th>Date Conducted</th>
                                                <th>Remarks</th>
                                                <th>Comments</th>
                                            </tr>
                                        </tfoot>
                                    </table>
                                </div>
                                <!-- /.card-body -->
                            </div>
                            <!-- /.card -->
                        </div>

                    </div>

                </section>
            </div>
            <!-- /.content-wrapper -->
            
            <!-- Control Sidebar -->
            <aside class="control-sidebar control-sidebar-dark">
                <!-- Control sidebar content goes here -->
            </aside>
            <!-- /.control-sidebar -->   
        </div>
        <!-- ./wrapper -->
        <!-- The Modal -->
        <div class="modal" id="myModal">
            <div class="modal-dialog">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">Apply For Seminar</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        <form id="seminarForm">
                        <div class="card-body">
                            <div class="form-group row">
                                
                                    <input name="action" type="hidden" id="action" class="form-control" value="applyForSeminar">        
                                    <input name="studentId" type="hidden" id="inputStudentId" class="form-control">
                                    <label for="inputSeminar">Seminar Title</label>
                                    <select name="seminarTitle" type="text" id="inputSeminar" class="form-control">

                                    </select>
                            </div>



                        </div>
                        <!-- /.card-body -->
                        </form>
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <input type="submit" value="Apply" class="btn btn-danger" id="applyBtn">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                    </div>

                </div>
            </div>
        </div>
        <%
                } else {
                    // If Session Expires

                    response.sendRedirect("login.jsp");
                }
        %>
        <jsp:include page="bottom_links.jsp"/>
        <!-- page script -->
        <script src="js/student_seminar.js"></script>
    </body>
</html>
