<%-- 
    Document   : index
    Created on : July 1, 2020, 9:26:51 AM
    Author     : khatr
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Manage Information System</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Font Awesome -->
        <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
        <!-- overlayScrollbars -->
        <link rel="stylesheet" href="dist/css/adminlte.min.css">
        <!-- Google Font: Source Sans Pro -->
        <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
    </head>
    <body class="hold-transition sidebar-mini">

        <!-- Site wrapper -->
        <div class="wrapper">
            <jsp:include page="navbar.jsp"/>
            <jsp:include page="top_navbar.jsp"/>
            <%
                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                response.setHeader("pragma", "no-cache");
                response.setHeader("Expires", "0");
                // If Session Exists
                if (session.getAttribute("user") != null) {
            %>


            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <div class="container-fluid">
                        <h1>Dashboard</h1>
                        <div class="row mb-2">
                            <div class="col-sm-6">
                                
                                <div class="col-md-6">
                                    <!-- LINE CHART -->
                                    <div class="card d-inline-block card-info" style="height: 350px; width: 650px; ">
                                        <div class="card-header">
                                            <h3 class="card-title">Registration Chart</h3>

                                            <div class="card-tools">
                                                <button type="button" class="btn btn-tool" data-card-widget="collapse"><i class="fas fa-minus"></i>
                                                </button>
                                                <button type="button" class="btn btn-tool" data-card-widget="remove"><i class="fas fa-times"></i></button>
                                            </div>
                                        </div>
                                        <div class="card-body">
                                            <div class="chart">
                                                <canvas id="lineChart" style="min-height: 250px; height: 250px; max-height: 250px; max-width: 100%;"></canvas>
                                            </div>
                                        </div>
                                        <!-- /.card-body -->
                                    </div>
                                    <!-- /.card -->
                                    
                                    <!-- BAR CHART -->
                                    <div class="card card-info" style="height: 350px; width: 650px;">
                                        <div class="card-header">
                                            <h3 class="card-title">Fees Chart</h3>

                                            <div class="card-tools">
                                                <button type="button" class="btn btn-tool" data-card-widget="collapse"><i class="fas fa-minus"></i>
                                                </button>
                                                <button type="button" class="btn btn-tool" data-card-widget="remove"><i class="fas fa-times"></i></button>
                                            </div>
                                        </div>
                                        <div class="card-body">
                                            <div class="chart">
                                                
                                                <canvas id="barChart" style="min-height: 250px; height: 250px; max-height: 250px; max-width:100%;"></canvas>
                                            </div>
                                        </div>
                                        <!-- /.card-body -->
                                    </div>
                                    <!-- /.card -->
                                    

                                </div>

                            </div>
                            <div class="col-sm-6">
                                <!-- DONUT CHART -->
                                    <div class="card card-info" style="height: 350px; width: 650px; ">
                                        <div class="card-header">
                                            <h3 class="card-title">Seminar Chart</h3>

                                            <div class="card-tools">
                                                <button type="button" class="btn btn-tool" data-card-widget="collapse"><i class="fas fa-minus"></i>
                                                </button>
                                                <button type="button" class="btn btn-tool" data-card-widget="remove"><i class="fas fa-times"></i></button>
                                            </div>
                                        </div>
                                        <div class="card-body">
                                            <canvas id="donutChart" style="min-height: 250px; height: 250px; max-height: 250px; max-width: 100%;"></canvas>
                                        </div>
                                        <!-- /.card-body -->
                                    </div>
                                <!-- PIE CHART -->
                                    <div class="card card-info" style="height: 350px; width: 650px;">
                                      <div class="card-header">
                                        <h3 class="card-title">Student Chart</h3>

                                        <div class="card-tools">
                                          <button type="button" class="btn btn-tool" data-card-widget="collapse"><i class="fas fa-minus"></i>
                                          </button>
                                          <button type="button" class="btn btn-tool" data-card-widget="remove"><i class="fas fa-times"></i></button>
                                        </div>
                                      </div>
                                      <div class="card-body">
                                        <canvas id="pieChart" style="min-height: 250px; height: 250px; max-height: 250px; max-width: 100%;"></canvas>
                                      </div>
                                      <!-- /.card-body -->
                                    </div>
                                    <!-- /.card -->
                                    <!-- /.card -->
                            </div>
                        </div><!-- /.container-fluid -->
                </section> 
                <!-- Main content -->

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
        <script src="js/dashboard.js"></script>
        <!-- Bootstrap 4 -->
        <script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- jQuery -->
        <script src="plugins/jquery/jquery.min.js"></script>
        <!-- Bootstrap 4 -->
        <script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- ChartJS -->
        <script src="plugins/chart.js/Chart.min.js"></script>
        <script src="plugins/chart.js/Chart.js"></script>
        <!-- jQuery -->

</html>

<!-- page script -->
</body>
</html>
