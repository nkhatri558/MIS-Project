<%-- 
    Document   : student_thesis
    Created on : Oct 23, 2020, 6:20:01 PM
    Author     : 92310
--%>

<%-- 
    Document   : thesis_group
    Created on : Aug 17, 2020, 8:47:09 PM
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
           
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <div class="container-fluid">
                        <div class="row mb-2">
                            <div class="col-sm-6">
                                <h1>Manage Thesis</h1>
                            </div>
                            <div class="col-sm-6">
                                <ol class="breadcrumb float-sm-right">
                                    <li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
                                    <li class="breadcrumb-item active">Thesis</li>
                                </ol>
                            </div>
                        </div>
                    </div><!-- /.container-fluid -->
                </section> 
                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card card-primary">
                                <div class="card-header">
                                    <h3 class="card-title">Apply for Thesis</h3>

                                    <div class="card-tools">
                                        <button type="button" class="btn btn-tool" data-card-widget="collapse" data-toggle="tooltip" title="Collapse">
                                            <i class="fas fa-minus"></i></button>
                                    </div>
                                </div>
                                <form id="thesisForm" method="POST" enctype="multipart/form-data">
                                    <input type="hidden" name="action" value="add"/>
                                     <input type="hidden" name="studentId" id="studentSessionId" value="${sessionScope.student.studentId}"/>
                                 <div class="card-body">
                                        <div class="form-group">
                                            <label for="inputThesisExamDate">Thesis Title</label>
                                            <input name="thesisTitle" type="text" id="inputThesisTitle" class="form-control">

                                        </div>
                                        <div class="form-group">
                                            
                                            <label for="inputBatch">Batch</label>
                                            <input name="batchName" type="hidden" id="inputBatchName" class="form-control" >
                                            <select  name="batch" class="form-control" id="batchId">
                                                
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputThesisExamDate">Applied Date</label>
                                            <input name="examDate" type="date" id="inputAppliedDate" class="form-control">
                                        </div>

                                        <div class="input-group mb-3 px-2 py-2 rounded-pill bg-white shadow-sm">                                        
                                            <input name="uploadImage" id="inputUploadImage" type="file"  class="form-control border-0">
                                            <div class="input-group-append">
                                                <label for="upload" class="btn btn-light m-0 rounded-pill px-4"> <i class="fa fa-cloud-upload mr-2 text-muted"></i><small class="text-uppercase font-weight-bold text-muted">Choose file</small></label>
                                            </div>
                                        </div>    
                                        <div class="form-group">
                                        <input id="submitBtn" type="submit" value="Apply for Thesis" class="btn btn-success float-right" >
                                    </div>
                                 </div>
                            <!-- /.card-body -->
                            </form>
                        </div>
                        <!-- /.card -->
                        <div class="card">
                            <div class="card-header">
                                <h3 class="card-title">Thesis</h3>
                            </div>
                            <!-- /.card-header -->
                            <div class="card-body">
                                <table id="example1" class="table table-bordered table-striped">
                                    <thead>
                                        <tr>
                                            <th>Roll No</th>
                                            <th>Applied Date</th>
                                            <th>Final Results</th>
                                            <th>Remarks</th>
                                            <th>Status</th>
                                            
                                        </tr>
                                    </thead>
                                    <tbody id="tab">

                                    </tbody>
                                    <tfoot>
                                        <tr>

                                            <th>Roll No</th>
                                            <th>Applied Date</th>
                                            <th>Final Results</th>
                                            <th>Remarks</th>
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
<script src="js/student_thesis.js"></script>
</body>
</html>
