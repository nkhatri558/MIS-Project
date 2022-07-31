<%-- 
    Document   : student.jsp
    Created on : Jul 13, 2020, 9:59:37 AM
    Author     : 92310
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       

<!-- Latest compiled JavaScript -->

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

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <div class="container-fluid">
                        <div class="row mb-2">
                            <div class="col-sm-6">
                                <h1>Manage Seminars</h1>
                            </div>
                            <div class="col-sm-6">
                                <ol class="breadcrumb float-sm-right">
                                    <li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
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

                            <!-- /.card -->
                            <div class="card card-info">
                                <div class="card-header">
                                    <h3 class="card-title">Students Records</h3>
                                </div>
                                <!-- /.card-header -->
                                <div class="card-body">
                                    <table id="example1" class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                                <th>Serial no </th>
                                                <th>Seminar title</th>
                                                <th>Student Roll Number </th>
                                                <th>Applied date </th>
                                                <th>Date of conduct </th>
                                                <th>Remarks </th>
                                                <th>Comments </th>
                                                <th>Status</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody id="tab">

                                        </tbody>
                                        <tfoot>
                                            <tr>
                                                <th>Serial no </th>
                                                <th>Seminar title</th>
                                                <th>Student Roll Number</th>
                                                <th>Applied date </th>
                                                <th>Date of conduct </th>
                                                <th>Remarks </th>
                                                <th>Comments </th>
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


            <!-- Control Sidebar -->
            <aside class="control-sidebar control-sidebar-dark">
                <!-- Control sidebar content goes here -->
            </aside>
            <!-- /.control-sidebar -->   
        </div>
        <!-- The Modal -->
        <div class="modal" id="myModal">
            <div class="modal-dialog">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">Update Seminar Details</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        <form id="seminarGroupId" method="POST">
                            <div class="card-body">
                                <div class="form-group row">

                                    <input name="seminarId" type="hidden" id="inputSeminarId" class="form-control">
                                    <input name="thesisId" type="hidden" id="inputThesisId" class="form-control">
                                    <label for="inputSeminarTitle">Seminar Title</label>
                                    <input name="seminarTitle" type="text" id="inputSeminarTitle" class="form-control">

                                    <label for="inputThesisTitle">Thesis Title</label>
                                    <input name="thesisTitle" type="text" id="inputThesisTitle" class="form-control">

                                    <label for="inputAppliedDate">Applied Date</label>
                                    <input name="appliedDate" type="date" id="inputAppliedDate" class="form-control">
                                    
                                    <label for="inputDateOfConduct">Date of Conduct</label>
                                    <input name="dateOfConduct" type="date" id="inputDateOfConduct" class="form-control">
                                    
                                    <label for="inputRemarks">Remarks</label>
                                    <input name="remarks" type="text" id="inputRemarks" class="form-control">
                                    
                                    <label for="inputComments">Comments</label>
                                        <input name="comments" type="text" id="inputComments" class="form-control">
                                    
                                    <label for="inputStatus">Status</label>
                                    <input name="status" type="text" id="inputStatus" class="form-control">

                                </div>



                            </div>
                            <!-- /.card-body -->
                        </form>
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" id="updateModal" >Update</button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                    </div>

                </div>
            </div>
        </div>
        <!-- ./wrapper -->
         
      <jsp:include page="bottom_links.jsp"/>
        <!-- page script -->

        <script src="js/seminar_details.js"></script>
        
    </body>
</html>