<%-- 
    Document   : student_profile
    Created on : Jul 31, 2020, 2:38:59 PM
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
                // If Session Exists
                if (session.getAttribute("user") != null) {
            %>

            <input type="hidden" id="studentId" value="${param.id}"/>
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <div class="container-fluid">
                        <div class="row mb-2">
                            <div class="col-sm-6">
                                <h1>Manage Students</h1>
                            </div>
                            <div class="col-sm-6">
                                <ol class="breadcrumb float-sm-right">
                                    <li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
                                    <li class="breadcrumb-item active">Student Profile</li>
                                </ol>
                            </div>
                        </div>
                    </div><!-- /.container-fluid -->
                </section> 
                <!-- Main content -->
                <!-- Main content -->
                <section class="content">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-12">

                                <!-- Profile Image -->
                                <div class="card card-info card-outline">
                                    <div class="card-body box-profile">
                                        <div class="text-center">
                                            <img id="studentProfileImage" class="profile-user-img img-fluid img-circle"
                                                 src="dist/img/user4-128x128.jpg"
                                                 alt="User profile picture"/>
                                        </div>

                                    </div>
                                    <!-- /.card-body -->
                                </div>
                                <!-- /.card -->
                                <div class="card">
                                    <div class="card-header p-2">
                                        <ul class="nav nav-pills">
                                            <li class="nav-item"><a class="nav-link active" href="#information" data-toggle="tab">Information</a></li>
                                            <li class="nav-item"><a class="nav-link" href="#timeline" data-toggle="tab">Documents</a></li>
                                        </ul>
                                    </div><!-- /.card-header -->
                                    <div class="card-body">
                                        <div class="tab-content">
                                            <div class="active tab-pane" id="information">
                                                <div class="card card-info">
                                                    <div class="card-header">
                                                        <h3 class="card-title">Personal Information</h3>

                                                    </div>
                                                    <div class="card-body">
                                                        <div class="row">
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputRollNumber">Roll Number</label>
                                                                    <input type="text" class="form-control" id="inputRollNumber" placeholder="19ME-SW101">
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputFullName">Full Name</label>
                                                                    <input type="text" class="form-control" id="inputFullName" placeholder="Nand Lal">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputFatherName">Father Name</label>
                                                                    <input type="text" class="form-control" id="inputFatherName" placeholder="19ME-SW101">
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputLegalId">Legal ID</label>
                                                                    <input type="text" class="form-control" id="inputLegalId" placeholder="Nand Lal">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputLegalIdNo">Legal ID Number</label>
                                                                    <input type="text" class="form-control" id="inputLegalIdNo" placeholder="19ME-SW101">
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputNationality">Nationality</label>
                                                                    <input type="text" class="form-control" id="inputNationality" placeholder="Nand Lal">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputPlaceOfIssueOfLegalId">Place Of Issue Of Legal ID</label>
                                                                    <input type="text" class="form-control" id="inputPlaceOfIssueOfLegalId" placeholder="19ME-SW101">
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputDateOfIssueOfLegalId">Date of Issue of Legal ID</label>
                                                                    <input type="date" class="form-control" id="inputDateOfIssueOfLegalId" placeholder="Nand Lal">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputMobileNumber">Mobile Number</label>
                                                                    <input type="text" class="form-control" id="inputMobileNumber" placeholder="19ME-SW101">
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputDob">Date Of Birth</label>
                                                                    <input type="date" class="form-control" id="inputDob" placeholder="Nand Lal">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputEmail">Email</label>
                                                                    <input type="text" class="form-control" id="inputEmail" placeholder="19ME-SW101">
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputDomicile">Domicile</label>
                                                                    <input type="text" class="form-control" id="inputDomicile" placeholder="Nand Lal">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputCountryOfBirth">Country Of Birth</label>
                                                                    <input type="email" class="form-control" id="inputCountryOfBirth" placeholder="19ME-SW101">
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputBloodGroup">Blood Group</label>
                                                                    <input type="email" class="form-control" id="inputBloodGroup" placeholder="19ME-SW101">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputReligion">Religion</label>
                                                                    <input type="email" class="form-control" id="inputReligion" placeholder="Nand Lal">
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputAddress">Address</label>
                                                                    <input type="email" class="form-control" id="inputAddress" placeholder="19ME-SW101">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputCurrentAddress">Current Address</label>
                                                                    <input type="email" class="form-control" id="inputCurrentAddress" placeholder="Nand Lal">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>    
                                                </div>

                                                <div class="card card-info">
                                                    <div class="card-header">
                                                        <h3 class="card-title">Academic Information</h3>

                                                    </div>
                                                    <div class="card-body">
                                                        <div class="row">
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputSemester">Semester</label>
                                                                    <input type="email" class="form-control" id="inputSemester" placeholder="19ME-SW101">
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputDepartment">Deparment</label>
                                                                    <input type="text" class="form-control" id="inputDepartment" placeholder="Nand Lal">

                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputBatch">Batch</label>
                                                                    <input type="email" class="form-control" id="inputBatch" placeholder="19ME-SW101">
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputAdmissionDate">Admission Date</label>
                                                                    <input type="email" class="form-control" id="inputAdmissionDate" placeholder="Nand Lal">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputSupervisor">Supervisor</label>
                                                                    <input type="email" class="form-control" id="inputSupervisor" placeholder="19ME-SW101">
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputProgram">Program</label>
                                                                    <input type="email" class="form-control" id="inputProgram" placeholder="Nand Lal">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputFieldProgram">Field Program</label>
                                                                    <input type="text" class="form-control" id="inputFieldProgram" placeholder="19ME-SW101">
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputShift">Shift</label>
                                                                    <input type="text" class="form-control" id="inputShift" placeholder="Nand Lal">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputTiming">Timing</label>
                                                                    <input type="text" class="form-control" id="inputTiming" placeholder="19ME-SW101">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>

                                            </div>
                                            <div class="card-header">



                                            </div>
                                            <!-- /.tab-pane -->
                                            <div class="tab-pane" id="timeline">
                                                <div class="card card-info">
                                                    <div class="card-header">
                                                        <h3 class="card-title">Matriculation</h3>

                                                    </div>
                                                    <div class="card-body">
                                                        <div class="row">
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputMatriculationPassingYear">Passing Year</label>
                                                                    <input type="text" class="form-control" id="inputMatriculationPassingYear" placeholder="19ME-SW101">
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputMatriculationBoard">Board</label>
                                                                    <input type="text" class="form-control" id="inputMatriculationBoard" placeholder="Nand Lal">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputMatriculationMarks">Marks Obtained</label>
                                                                    <input type="text" class="form-control" id="inputMatriculationMarks" placeholder="19ME-SW101">
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputMatriculationSubject">Subject</label>
                                                                    <input type="text" class="form-control" id="inputMatriculationSubject" placeholder="Nand Lal">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="form-group">
                                                                <label for="inputMatriculationMarksheet">Marksheet</label>
                                                                <img id="inputMatriculationMarksheet" src="dist/img/user4-128x128.jpg" id="inputMarksheet" width="1000px" height="800px"/>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="form-group">
                                                                <label for="inputMatriculationCertificate">Certificate</label>
                                                                <img id="inputMatriculationCertificate" src="dist/img/user4-128x128.jpg" width="1000px" height="800px"/>
                                                            </div>
                                                        </div>
<!--                                                        <div class="row">
                                                            <div class="form-group">
                                                                <input id="submitBtn" type="submit" value="Update" class="btn btn-success float-right" >
                                                            </div>
                                                        </div>-->
                                                    </div>    
                                                </div>
                                                <div class="card card-info">
                                                    <div class="card-header">
                                                        <h3 class="card-title">Intermediate</h3>

                                                    </div>
                                                    <div class="card-body">
                                                        <div class="row">
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputIntermediatePassingYear">Passing Year</label>
                                                                    <input type="text" class="form-control" id="inputIntermediatePassingYear" placeholder="19ME-SW101">
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputIntermediateBoard">Board</label>
                                                                    <input type="text" class="form-control" id="inputIntermediateBoard" placeholder="Nand Lal">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputIntermediateMarks">Marks Obtained</label>
                                                                    <input type="text" class="form-control" id="inputIntermediateMarks" placeholder="19ME-SW101">
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputIntermediateSubject">Subject</label>
                                                                    <input id="inputIntermediateSubject" type="text" class="form-control" id="inputIntermediateSubject" placeholder="Nand Lal">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="form-group">
                                                                <label for="inputIntermediateMarksheet">Marksheet</label>
                                                                <img id="inputIntermediateMarksheet" src="dist/img/user4-128x128.jpg" width="1000px" height="800px">
                                                                
                                                                <!--<img id="inputIntermediateMarksheet" src="uploads/academicDocuments/intermediate/F16SW49_marksheetImage.png" width="1000px" height="800px">-->
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="form-group">
                                                                <label for="inputIntermediateCertificate">Certificate</label>
                                                                <img id="inputIntermediateCertificate" src="dist/img/user4-128x128.jpg" width="1000px" height="800px">
                                                            </div>
                                                        </div>
<!--                                                        <div class="row">
                                                            <div class="form-group">
                                                                <input id="submitBtn" type="submit" value="Update" class="btn btn-success float-right" >
                                                            </div>
                                                        </div>-->
                                                    </div>    
                                                </div>
                                                <div class="card card-info">
                                                    <div class="card-header">
                                                        <h3 class="card-title">Graduate</h3>

                                                    </div>
                                                    <div class="card-body">
                                                        <div class="row">
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputGraduatePassingYear">Passing Year</label>
                                                                    <input type="text" class="form-control" id="inputGraduatePassingYear" placeholder="19ME-SW101">
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputGraduateUniversity">University</label>
                                                                    <input type="text" class="form-control" id="inputGraduateUniversity" placeholder="Nand Lal">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputGraduateCgpa">CGPA</label>
                                                                    <input type="text" class="form-control" id="inputGraduateCgpa" placeholder="19ME-SW101">
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="inputGraduateDegreeProgram">Degree Program</label>
                                                                    <input type="text" class="form-control" id="inputGraduateDegreeProgram" placeholder="Nand Lal">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="form-group">
                                                                <label for="inputGraduateMarksheet">Marksheet</label>
                                                                <img id="inputGraduateMarksheet" src="dist/img/user4-128x128.jpg" width="1000px" height="800px">
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="form-group">
                                                                <label for="inputGraduateCertificate">Certificate</label>
                                                                <img id="inputGraduateCertificate" src="dist/img/user4-128x128.jpg" width="1000px" height="800px">
                                                            </div>
                                                        </div>
<!--                                                        <div class="row">
                                                            <div class="form-group">
                                                                <input id="submitBtn" type="submit" value="Update" class="btn btn-success float-right" >
                                                            </div>
                                                        </div>-->
                                                    </div>    
                                                </div>
                                            </div>
                                            <!-- /.tab-pane -->
                                        </div>
                                        <div class="form-group">
                                            <input id="submitBtn1" type="submit" value="Update Student Record" class="btn btn-success float-left">
                                        </div>
                                        <!-- /.tab-content -->
                                    </div><!-- /.card-body -->
                                </div>
                                <!-- /.nav-tabs-custom -->


                            </div>
                            <!-- /.row -->
                        </div><!-- /.container-fluid -->
                </section>
                <!-- /.content -->
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
        <script src="js/student_profile.js"></script>
    </body>
</html>
