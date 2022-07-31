<%-- 
    Document   : student_fees
    Created on : Aug 21, 2020, 4:52:41 PM
    Author     : khatr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fees Details</title>
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
                                <h1>Fees Details</h1>
                            </div>
                            <div class="col-sm-6">
                                <ol class="breadcrumb float-sm-right">
                                    <li class="breadcrumb-item"><a href="student_dashboard.jsp">Home</a></li>
                                    <li class="breadcrumb-item active">Fees</li>
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
                                    <h3 class="card-title mt-2">Fees Details</h3>
                                    <div class="card-tools">
                                        <button id="addFeesBtn" class="btn btn-info">Add New Fees</button>
                                    </div>

                                </div>
                                <!-- /.card-header -->
                                <div class="card-body">
                                    <table id="example1" class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                                <th>Fees Title</th>
                                                <th>Fees Amount</th>
                                                <th>Date Announced</th>
                                                <th>Due Date</th>
                                                <th>Date Paid</th>
                                                <th>Amount Paid</th>
                                                <th>Late Fees</th>
                                                <th>Challan No</th>
                                                <th>Challan Image</th>
                                                <th>Status</th>
                                            </tr>
                                        </thead>
                                        <tbody id="tab">

                                        </tbody>
                                        <tfoot>
                                            <tr>
                                                <th>Fees Title</th>
                                                <th>Fees Amount</th>
                                                <th>Date Announced</th>
                                                <th>Due Date</th>
                                                <th>Date Paid</th>
                                                <th>Amount Paid</th>
                                                <th>Late Fees</th>
                                                <th>Challan No</th>
                                                <th>Challan Image</th>
                                                <th>Status</th>
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
                        <h4 class="modal-title">Add New Fees</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        <form id="studentFeesForm" enctype="multipart/form-data">
                        <div class="card-body">
                            <div class="form-group row">
                                
                                    <input name="action" type="hidden" id="action" class="form-control" value="addStudentFees">        
                                    <input name="studentId" type="hidden" id="inputStudentId" class="form-control">
                                    <input name="studentFeesId" type="hidden" id="inputStudentFeesId" class="form-control">
                                    <input name="feesId" type="hidden" id="inputFeesId" class="form-control">
                                    <label for="inputFeesTitle">Fees Title</label>
                                    <select name="feesTitle" type="text" id="inputFeesTitle" class="form-control">

                                    </select>

                                    <label for="inputFeesAmount">Fees Amount</label>
                                    <input name="feesAmount" disabled type="text" id="inputFeesAmount" class="form-control">

                                    <label for="inputDateAnnounced">Announcement Date</label>
                                    <input name="dateAnnounced" disabled type="datetime-local" id="inputDateAnnounced" class="form-control">

                                    <label for="inputDueDate">Due Date</label>
                                    <input name="dueDate" disabled type="datetime-local" id="inputDueDate" class="form-control">

                                    <label for="inputDatePaid">Date Paid</label>
                                    <input name="datePaid" type="date" id="inputDatePaid" class="form-control">

                                    <label for="inputAmountPaid">Amount Paid</label>
                                    <input name="amountPaid" type="number" id="inputAmountPaid" class="form-control">

                                    <label for="inputLateFees">Late Fees</label>
                                    <input name="lateFees" type="number" id="inputLateFees" class="form-control">

                                    <label for="inputChallanNo">Challan Number</label>
                                    <input name="challanNo" type="number" id="inputChallanNo" class="form-control">

                                    <label for="inputChallanImage">Challan Image</label>
                                    <input name="challanImage" type="file" class="form-control" id="inputChallanImage">
                                
                            </div>



                        </div>
                        <!-- /.card-body -->
                        </form>
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <input type="submit" value="Add" class="btn btn-danger" id="addBtn">
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
        <script src="js/student_fees.js"></script>
    </body>
</html>
