<%-- 
    Document   : student_registration
    Created on : Aug 24, 2020, 10:07:12 PM
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
    <body class="hold-transition layout-top-nav">
        <!-- Site wrapper -->
        <div class="wrapper">
            <%--<jsp:include page="student_navbar.jsp"/>--%>
            <nav class="main-header navbar navbar-expand navbar-white navbar-light">
                <div class="container">
                    <a class="navbar-brand">
                        <img src="dist/img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3"
                             style="opacity: .8">
                        <span class="brand-text font-weight-light">MIS MUET</span>
                    </a>
                    <!-- Right navbar links -->
                    <ul class="navbar-nav ml-auto">
                        <li>
                            <form action="HomeController" method="post">
                                <input type="submit" class="btn btn-primary" value="Logout"/>
                            </form>

                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-widget="control-sidebar" data-slide="true" href="#" role="button">
                                <i class="fas fa-th-large"></i>
                            </a>
                        </li>
                    </ul>
            </nav>
            <!-- /.navbar -->
            <%
                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                response.setHeader("pragma", "no-cache");
                response.setHeader("Expires", "0");
                if (session.getAttribute("student") != null) {
            %>

            <input type="hidden" id="studentId" value="${param.id}"/>
            <!-- Content Wrapper. Contains page content -->
            
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <div class="container-fluid">
                        <div class="row mb-2">
                            <div class="col-sm-6">
                                <h1>Student Registration</h1>
                            </div>
                            <div class="col-sm-6">
                                <ol class="breadcrumb float-sm-right">
                                    <li class="breadcrumb-item active">Registration</li>
                                </ol>
                            </div>
                        </div>
                    </div><!-- /.container-fluid -->
                </section> 
                <!-- Main content -->
                <!-- Main content -->
                <div class="content">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-12">
                                <!-- /.card -->
                                <div class="card card-info">
                                    <div class="card-header p-2">
                                        <ul class="nav nav-pills">
                                            <li class="nav-item"><a id="tab1" class="nav-link active" href="#information" data-toggle="tab">Information</a></li>
                                            <li class="nav-item" ><a id="tab2" disabled="disabled" class="nav-link" href="#timeline" data-toggle="tab">Documents</a></li>
                                        </ul>
                                    </div><!-- /.card-header -->
                                    <div class="card-body">
                                        <div class="tab-content">
                                            <div class="active tab-pane" id="information">
                                                <form id="personalInformation" enctype="multipart/form-data">
                                                    <input type="hidden" name="studentId" value="${sessionScope.student.studentId}"/>
                                                    <input type="hidden" name="action" value="registerStudent"/>
                                                    <div class="card card-primary">
                                                        <div class="card-header">
                                                            <h3 class="card-title">Personal Information</h3>
                                                        </div>

                                                        <div class="card-body">
                                                            <div class="row">
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputRollNumber">Roll Number</label>
                                                                        <input name="rollNumber" type="text" class="form-control" id="inputRollNumber" placeholder="19ME-SW101" value="${sessionScope.student.rollNumber}" disabled="disabled">
                                                                    </div>
                                                                </div>
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputFullName">Full Name</label>
                                                                        <input name="fullName" type="text" class="form-control" id="inputFullName" placeholder="Nand Lal" value="${sessionScope.student.fullName}" disabled="disabled">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputFatherName">Father Name</label>
                                                                        <input name="fatherName" type="text" class="form-control" id="inputFatherName" placeholder="19ME-SW101">
                                                                    </div>
                                                                </div>
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputLegalId">Legal ID</label>
                                                                        <input name="legalId" type="text" class="form-control" id="inputLegalId" placeholder="Nand Lal">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputLegalIdNo">Legal ID Number</label>
                                                                        <input name="legalIdNo" type="text" class="form-control" id="inputLegalIdNo" placeholder="19ME-SW101">
                                                                    </div>
                                                                </div>
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputNationality">Nationality</label>
                                                                        <input name="nationality" type="text" class="form-control" id="inputNationality" placeholder="Nand Lal">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputPlaceOfIssueOfLegalId">Place Of Issue Of Legal ID</label>
                                                                        <input name="placeOfIssueOfLegalId" type="text" class="form-control" id="inputPlaceOfIssueOfLegalId" placeholder="19ME-SW101">
                                                                    </div>
                                                                </div>
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputDateOfIssueOfLegalId">Date of Issue of Legal ID</label>
                                                                        <input name="dateOfIssueOfLegalId" type="date" class="form-control" id="inputDateOfIssueOfLegalId" placeholder="Nand Lal">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputMobileNumber">Mobile Number</label>
                                                                        <input name="mobileNumber" type="text" class="form-control" id="inputMobileNumber" placeholder="19ME-SW101">
                                                                    </div>
                                                                </div>
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputDob">Date Of Birth</label>
                                                                        <input name="dob" type="date" class="form-control" id="inputDob" placeholder="Nand Lal">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputEmail">Email</label>
                                                                        <input name="email" type="email" class="form-control" id="inputEmail" placeholder="19ME-SW101" value="${sessionScope.student.email}" disabled="disabled">
                                                                    </div>
                                                                </div>
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputDomicile">Domicile</label>
                                                                        <input name="domicile" type="text" class="form-control" id="inputDomicile" placeholder="Nand Lal">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputCountryOfBirth">Country Of Birth</label>
                                                                        <input name="countryOfBirth" type="text" class="form-control" id="inputCountryOfBirth" placeholder="19ME-SW101">
                                                                    </div>
                                                                </div>
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputBloodGroup">Blood Group</label>
                                                                        <input name="bloodGroup" type="text" class="form-control" id="inputBloodGroup" placeholder="19ME-SW101">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputReligion">Religion</label>
                                                                        <input name="religion" type="text" class="form-control" id="inputReligion" placeholder="Nand Lal">
                                                                    </div>
                                                                </div>
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputAddress">Address</label>
                                                                        <input name="address" type="text" class="form-control" id="inputAddress" placeholder="19ME-SW101">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputCurrentAddress">Current Address</label>
                                                                        <input name="currentAddress" type="text" class="form-control" id="inputCurrentAddress" placeholder="Nand Lal">
                                                                    </div>
                                                                </div>
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputProfileImage">Profile Image</label>
                                                                        <input name="profileImage" type="file" class="form-control" id="inputProfileImage">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div> 

                                                    </div>

                                                    <div class="card card-primary">
                                                        <div class="card-header">
                                                            <h3 class="card-title">Academic Information</h3>

                                                        </div>
                                                        <div class="card-body">
                                                            <div class="row">
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputSemester">Semester</label>
                                                                        <input name="semesterStudying" type="text" class="form-control" id="inputSemester">
                                                                    </div>
                                                                </div>
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputDepartmentId">Deparment</label>
                                                                        <select name="departmentName" class="form-control custom-select" id="inputDepartmentId">

                                                                        </select>

                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputBatch">Batch</label>
                                                                        <input name="batch" type="text" class="form-control" id="inputBatch" placeholder="19ME-SW101">
                                                                    </div>
                                                                </div>
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputAdmissionDate">Admission Date</label>
                                                                        <input name="admissionDate" type="date" class="form-control" id="inputAdmissionDate" placeholder="Nand Lal">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputSupervisor">Supervisor</label>
                                                                        <input name="supervisor" type="text" class="form-control" id="inputSupervisor" placeholder="19ME-SW101">
                                                                    </div>
                                                                </div>
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputProgram">Program</label>
                                                                        <input name="program" type="text" class="form-control" id="inputProgram" placeholder="Nand Lal">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputFieldProgram">Field Program</label>
                                                                        <input name="fieldProgram" type="text" class="form-control" id="inputFieldProgram" placeholder="19ME-SW101">
                                                                    </div>
                                                                </div>
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputShift">Shift</label>
                                                                        <input name="shift" type="text" class="form-control" id="inputShift" placeholder="Nand Lal">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputTiming">Timing</label>
                                                                        <input name="timing" type="text" class="form-control" id="inputTiming" placeholder="19ME-SW101">
                                                                    </div>
                                                                </div>

                                                            </div>
                                                        </div>

                                                    </div>
                                                </form>
                                            </div>
                                            <div class="card-header"></div>
                                            <!-- /.tab-pane -->
                                            <div class="tab-pane" id="timeline">
                                                <div class="card card-primary">
                                                    <div class="card-header">
                                                        <h3 class="card-title">Matriculation</h3>

                                                    </div>
                                                    <div class="card-body">
                                                        <form id="academicMatriculation" enctype="multipart/form-data">
                                                            <input type="hidden" name="studentId" value="${sessionScope.student.studentId}"/>
                                                            <input type="hidden" name="action" value="addMatriculationInfo"/>
                                                            <div class="row">
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputMatriculationPassingYear">Passing Year</label>
                                                                        <input name="matriculationPassingYear" type="year" class="form-control" id="inputMatriculationPassingYear" placeholder="19ME-SW101">
                                                                    </div>
                                                                </div>
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputMatriculationBoard">Board</label>
                                                                        <input name="matriculationBoard" type="text" class="form-control" id="inputMatriculationBoard" placeholder="Nand Lal">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputMatriculationMarks">Percentage</label>
                                                                        <input name="matriculationObtainedPercentage" type="number" class="form-control" id="inputMatriculationMarks" placeholder="19ME-SW101">
                                                                    </div>
                                                                </div>
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputMatriculationSubject">Subject</label>
                                                                        <input name="matriculationSubject" type="text" class="form-control" id="inputMatriculationSubject" placeholder="Nand Lal">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="form-group">
                                                                    <label for="inputMatriculationMarksheet">Marksheet</label>
                                                                    <!--<img src="../../dist/img/user4-128x128.jpg" width="1000px" height="800px">-->
                                                                    <input name="matriculationMarksheet" type="file" id="inputMatriculationMarksheet">
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="form-group">
                                                                    <label for="inputMatriculationCertificate">Certificate</label>
                                                                    <!--<img src="../../dist/img/user4-128x128.jpg" width="1000px" height="800px">-->
                                                                    <input name="matriculationCertficate" type="file" id="inputMatriculationCertificate">
                                                                </div>
                                                            </div>
                                                        </form>
                                                        <div class="row">
                                                            <div class="form-group">
                                                                <!--<input id="submitBtn" type="submit" value="Update" class="btn btn-success float-right" >-->
                                                            </div>
                                                        </div>
                                                    </div>    
                                                </div>
                                                <div class="card card-primary">
                                                    <div class="card-header">
                                                        <h3 class="card-title">Intermediate</h3>

                                                    </div>
                                                    <div class="card-body">
                                                        <form id="academicIntermediate" enctype="multipart/form-data">
                                                            <input type="hidden" name="studentId" value="${sessionScope.student.studentId}"/>
                                                            <input type="hidden" name="action" value="addIntermediateInfo"/>
                                                            <div class="row">
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputIntermediatePassingYear">Passing Year</label>
                                                                        <input name="intermediatePassingYear" type="number" class="form-control" id="inputIntermediatePassingYear" placeholder="19ME-SW101">
                                                                    </div>
                                                                </div>
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputIntermediateBoard">Board</label>
                                                                        <input name="intermediateBoard" type="text" class="form-control" id="inputIntermediateBoard" placeholder="Nand Lal">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputIntermediateMarks">Marks Obtained</label>
                                                                        <input name="IntermediateObtainedMarks" type="number" class="form-control" id="inputIntermediateMarks" placeholder="19ME-SW101">
                                                                    </div>
                                                                </div>
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputIntermediateSubject">Subject</label>
                                                                        <input name="intermediateSubject" type="text" class="form-control" id="inputIntermediateSubject" placeholder="Nand Lal">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="form-group">
                                                                    <label for="inputIntermediateMarksheet">Marksheet</label>
                                                                    <!--<img src="../../dist/img/user4-128x128.jpg" width="1000px" height="800px">-->
                                                                    <input name="intermediateMarksheet" type="file" id="inputIntermediateMarksheet">
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="form-group">
                                                                    <label for="inputIntermediateCertificate">Certificate</label>
                                                                    <!--<img src="../../dist/img/user4-128x128.jpg" width="1000px" height="800px">-->
                                                                    <input name="intermediateCertificate" type="file" id="inputIntermediateCertificate">
                                                                </div>
                                                            </div>
                                                        </form>
                                                        <div class="row">
                                                            <div class="form-group">
                                                                <!--<input id="submitBtn" type="submit" value="Update" class="btn btn-success float-right" >-->
                                                            </div>
                                                        </div>
                                                    </div>    
                                                </div>
                                                <div class="card card-primary">
                                                    <div class="card-header">
                                                        <h3 class="card-title">Graduate</h3>

                                                    </div>
                                                    <div class="card-body">
                                                        <form id="academicGraduate" enctype="multipart/form-data">
                                                            <input type="hidden" name="studentId" value="${sessionScope.student.studentId}"/>
                                                            <input type="hidden" name="action" value="addGraduateInfo"/>
                                                            <div class="row">
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputGraduatePassingYear">Passing Year</label>
                                                                        <input name="graduatePassingYear" type="number" class="form-control" id="inputGraduatePassingYear" placeholder="19ME-SW101">
                                                                    </div>
                                                                </div>
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputUniversity">University</label>
                                                                        <input name="graduateUniversity" type="text" class="form-control" id="inputUniversity" placeholder="Nand Lal">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputCgpa">CGPA</label>
                                                                        <input name="graduateCgpa" type="number" class="form-control" id="inputGraduateMarks" placeholder="19ME-SW101">
                                                                    </div>
                                                                </div>
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label for="inputGraduateDegreeProgram">Degree Program</label>
                                                                        <input name="graduateDegreeProgram" type="text" class="form-control" id="inputGraduateDegreeProgram" placeholder="Nand Lal">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="form-group">
                                                                    <label for="inputGraduateMarksheet">Marksheet</label>
                                                                    <!--<img src="../../dist/img/user4-128x128.jpg" width="1000px" height="800px">-->
                                                                    <input name="graduateMarksheet" type="file" id="inputGraduateMarksheet">
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="form-group">
                                                                    <label for="inputGraduateCertificate">Certificate</label>
                                                                    <!--<img src="../../dist/img/user4-128x128.jpg" width="1000px" height="800px">-->
                                                                    <input name="graduateCertificate" type="file" id="inputGraduateCertificate">
                                                                </div>
                                                            </div>
                                                        </form>
                                                        <div class="row">
                                                            <div class="form-group">
                                                                <!--<input id="submitBtn" type="submit" value="Update" class="btn btn-success float-right" >-->
                                                            </div>
                                                        </div>
                                                    </div>    
                                                </div>
                                            </div>
                                            <!-- /.tab-pane -->
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-6">
                                                <div class="form-group">
                                                    <input id="submitBtn" disabled="disabled" type="submit" value="Submit" class="btn btn-success float-left">
                                                </div>
                                            </div>
                                            <div class="col-sm-6">
                                                <div class="form-group">
                                                    <Button id="nextBtn" class="btn btn-success float-right">Next</button>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- /.tab-content -->
                                    </div><!-- /.card-body -->
                                </div>
                                <!-- /.nav-tabs-custom -->


                            </div>
                            <!-- /.row -->
                        </div><!-- /.container-fluid -->
                </div>
                <!-- /.content -->
            </div>
                                                            </div>
            <!-- /.content-wrapper -->
            <%
                } else {
                    // If Session Expires

                    response.sendRedirect("login.jsp");
                }
            %>
            <div class="modal fade" id="modal-success">
                <div class="modal-dialog">
                    <div class="modal-content bg-success">
                        <div class="modal-header">
                            <h4 class="modal-title">Success</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span></button>
                        </div>
                        <div class="modal-body">
                            <p>Registration Successful!&hellip;</p>
                        </div>
                        <div class="modal-footer justify-content-between">
                            <button id="continueBtn" type="button" class="btn btn-outline-light" data-dismiss="modal">Continue</button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            <!-- /.modal -->
            <!-- Control Sidebar -->
            <aside class="control-sidebar control-sidebar-dark">
                <!-- Control sidebar content goes here -->
            </aside>
            <!-- /.control-sidebar -->   
        </div>
        <!-- ./wrapper -->

        <jsp:include page="bottom_links.jsp"/>
        <!-- page script -->
        <script src="js/student_registration.js"></script>
    </body>
</html>

