<%-- 
    Document   : student_fees
    Created on : Aug 6, 2020, 11:01:55 PM
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
            <jsp:include page="navbar.jsp"/>
            <jsp:include page="top_navbar.jsp"/>
            <%
                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                response.setHeader("pragma", "no-cache");
                response.setHeader("Expires", "0");
                //If Session Exists
                if (session.getAttribute("user") != null) {
            %>
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <div class="container-fluid">
                        <div class="row mb-2">
                            <div class="col-sm-6">
                                <h1>Manage Fees Details</h1>
                            </div>
                            <div class="col-sm-6">
                                <ol class="breadcrumb float-sm-right">
                                    <li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
                                    <li class="breadcrumb-item active">Fees Details</li>
                                </ol>
                            </div>
                        </div>
                    </div><!-- /.container-fluid -->
                </section> 
                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-md-12">
<!--                            <div class="card card-info">
                                <div class="card-header">
                                    <h3 class="card-title">Add Fees Details</h3>

                                    <div class="card-tools">
                                        <button type="button" class="btn btn-tool" data-card-widget="collapse" data-toggle="tooltip" title="Collapse">
                                            <i class="fas fa-minus"></i></button>
                                    </div>
                                </div>
                                <form id="studentFeesForm" method="POST">
                                    <div class="card-body">
                                        <div class="form-group">
                                            <label for="inputStudentRollNumber">Student Roll Number</label>
                                            <input name="studentId" type="text" id="inputStudentRollNumber" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label for="inputFees">Fees Title</label>
                                            <input name="feesTitle" type="number" id="inputFeesTitle" class="form-control">
                                            <select name="feesId" class="form-control custom-select" id="inputFees">

                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputDatePaid">Date Paid</label>
                                            <input name="datePaid" type="date" id="inputDatePaid" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label for="inputAmountPaid">Amount Paid</label>
                                            <input name="amountPaid" type="number" id="inputAmountPaid" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label for="inputLateFee">Late Fee</label>
                                            <input name="lateFee" type="number" id="inputLateFee" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label for="inputChallanNumber">Challan Number</label>
                                            <input name="challanNumber" type="number" id="inputChallanNumber" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <input id="submitBtn" type="submit" value="Add New Fee Detail" class="btn btn-success float-right" >
                                        </div>


                                    </div>
                                     /.card-body 
                                </form>

                            </div>-->
                            <!-- /.card -->
                            <div class="card">
                                <div class="card-header">
                                    <h3 class="card-title">Fees Details</h3>
                                </div>
                                <!-- /.card-header -->
                                <div class="card-body">
                                    <table id="example1" class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                                <th>Student Roll Number</th>
                                                <th>Fees Title</th>
                                                <th>Date Paidt</th>
                                                <th>Amount Paid</th>
                                                <th>Late Fee</th>
                                                <th>Challan Number</th>
                                                <th>Challan Image</th>
                                                <th>Status</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody id="tab">

                                        </tbody>
                                        <tfoot>
                                            <tr>
                                                <th>Student Roll Number</th>
                                                <th>Fees Title</th>
                                                <th>Date Paidt</th>
                                                <th>Amount Paid</th>
                                                <th>Late Fee</th>
                                                <th>Challan Number</th>
                                                <th>Challan Image</th>
                                                <th>Status</th>
                                                <th>Action</th>
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
            <div class="modal" id="challanImageModal">
            <div class="modal-dialog">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        <div class="card-body">
                                <img id="viewChallanImage" width="450px;" height="1000px;">
                            
                        </div>
                        <!-- /.card-body --> 
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


            <!-- Control Sidebar -->
            <aside class="control-sidebar control-sidebar-dark">
                <!-- Control sidebar content goes here -->
            </aside>
            <!-- /.control-sidebar -->   
        </div>
        <!-- ./wrapper -->

        <jsp:include page="bottom_links.jsp"/>
        <!-- page script -->
        <script src="js/feesDetails.js"></script>
    </body>
</html>
