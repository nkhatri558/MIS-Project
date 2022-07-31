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
            <jsp:include page="navbar.jsp"/>
            <jsp:include page="top_navbar.jsp"/>
            <%
                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                response.setHeader("pragma", "no-cache");
                response.setHeader("Expires", "0");
                // If session exists
                if (session.getAttribute("user") != null) {
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
                            <div class="card card-info">
                                <div class="card-header">
                                    <h3 class="card-title">Add Thesis</h3>

                                    <div class="card-tools">
                                        <button type="button" class="btn btn-tool" data-card-widget="collapse" data-toggle="tooltip" title="Collapse">
                                            <i class="fas fa-minus"></i></button>
                                    </div>
                                </div>
                                <form id="thesisForm" enctype="multipart/form-data">
                                    <input type="hidden" name="action" value="addThesis"/>
                                    <div class="card-body">
                                        <div class="form-group">
                                            <input type="hidden" name="selectedBatch" id="selectedBatchOption"/>
                                            <input type="hidden" name="selectedId" id="selectedId"/>

                                            <label for="inputBatch">Batch</label>

                                            <select name="batch" class="form-control" id="batchId">

                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputAnnouncementDate">Announcement Date</label>
                                            <input name="announcementDate" type="date" id="inputAnnouncementDate" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label for="inputDueDate">Due Date</label>
                                            <input name="dueDate" type="date" id="inputDueDate" class="form-control">
                                        </div>

                                        <div class="form-group">
                                            <input id="submitBtn" type="submit" value="New Thesis Announcement" class="btn btn-success float-right" >
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
                                                <th>Batch</th>
                                                <th>Announcement Date</th>
                                                <th>Due Date</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody id="tab">

                                        </tbody>
                                        <tfoot>
                                            <tr>
                                                <th>Batch</th>
                                                <th>Announcement Date</th>
                                                <th>Due Date</th>
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
        <script src="js/thesis.js"></script>
    </body>
</html>
