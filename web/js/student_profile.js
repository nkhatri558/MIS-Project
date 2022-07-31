/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var studentId;
function updateData() {
    var id = studentId;
    var fullName = $("#inputFullName").val();
    var rollNumber = $("#inputRollNumber").val();
    var fathersName = $("#inputFatherName").val();
    var legalId = $("#inputLegalId").val();
    var legalIdNo = $("#inputLegalIdNo").val();
    var nationality = $("#inputNationality").val();
    var placeOfIssueOfLegalId = $("#inputPlaceOfIssueOfLegalId").val();
    var dateOfIssueOfLegalId = $("#inputDateOfIssueOfLegalId").val();
    var mobile = $("#inputMobileNumber").val();
    var dob = $("#inputDob").val();
    var email = $("#inputEmail").val();
    var domicile = $("#inputDomicile").val();
    var countryOfBirth = $("#inputCountryOfBirth").val();
    var bloodGroup = $("#inputBloodGroup").val();
    var religion = $("#inputReligion").val();
    var address = $("#inputAddress").val();
    var currentAddress = $("#inputCurrentAddress").val();
    var semester = $("#inputSemester").val();
    var batch = $("#inputBatch").val();
    var admissionDate = $("#inputAdmissionDate").val();
    // $("#inputSupervisor").val(data[21].supervisor);
    var program = $("#inputProgram").val();
    var fieldProgram = $("#inputFieldProgram").val();
    var shift = $("#inputShift").val();
    var timing = $("#inputTiming").val();
    //var departmentId = $("#inputDepartmentId").val();
    var departmentName = $("#inputDepartment").val();
    $.ajax({
        type: "POST",
        url: "StudentController",
        data: {
            action: "update",
            studentId: id,
            fullName: fullName,
            rollNumber: rollNumber,
            fathersName: fathersName,
            legalId: legalId,
            legalIdNo: legalIdNo,
            nationality: nationality,
            placeOfIssueOfLegalId: placeOfIssueOfLegalId,
            dateOfIssueOfLegalId: dateOfIssueOfLegalId,
            mobile: mobile,
            dob: dob,
            email: email,
            domicile: domicile,
            countryOfBirth: countryOfBirth,
            bloodGroup: bloodGroup,
            religion: religion,
            address: address,
            currentAddress: currentAddress,
            semester: semester,
            batch: batch,
            admissionDate: admissionDate,
            program: program,
            fieldProgram: fieldProgram,
            shift: shift,
            timing: timing,
            departmentId: departmentId,
            departmentName: departmentName
        }
        ,
        success: function (data, textStatus, jqXHR) {
            const Toast = Swal.mixin({
                        toast: true,
                        position: 'top-end',
                        showConfirmButton: false,
                        timer: 3000
                    });
                    Toast.fire({
                        icon: 'success',
                        title: 'Data Updated Successfully!'
                    });
                    location.reload();
        }
        ,
        error: function (data, textStatus, jqXHR) {
            alert("Error");
        }
    }
    );
}

function getMatriculationInfo() {
    var sId = $("#studentId").val();
    $.ajax({
        url: "StudentController",
        method: "POST",
        async: false,
        data: {
            action: "getMatriculationInfo",
            id: sId
        },
        success: function (data) {
            var data = JSON.parse(data);
            $("#inputMatriculationPassingYear").val(data.passingYear);
            $("#inputMatriculationMarks").val(data.marks);
            $("#inputMatriculationBoard").val(data.board);
            $("#inputMatriculationSubject").val(data.subject);
            $("#inputMatriculationMarksheet").attr("src", data.marksheetImage);
            $("#inputMatriculationCertificate").attr("src", data.certificateImage);


            $("html").animate({scrollTop: 0}, "slow");
        },
        error: function (data, textStatus, jqXHR) {
            alert("Error");
        }
    });
}
function getIntermediateInfo() {
    var sId = $("#studentId").val();
    $.ajax({
        url: "StudentController",
        method: "POST",
        async: false,
        data: {
            action: "getIntermediateInfo",
            id: sId
        },
        success: function (data) {
            var data = JSON.parse(data);
            $("#inputIntermediatePassingYear").val(data.passingYear);
            $("#inputIntermediateMarks").val(data.marks);
            $("#inputIntermediateBoard").val(data.board);
            $("#inputIntermediateSubject").val(data.subject);
            $("#inputIntermediateMarksheet").attr("src", data.marksheetImage);
            $("#inputIntermediateCertificate").attr("src", data.certificateImage);


            $("html").animate({scrollTop: 0}, "slow");
        },
        error: function (data, textStatus, jqXHR) {
            alert("Error");
        }
    });
}
function getGraduateInfo() {
    var sId = $("#studentId").val();
    $.ajax({
        url: "StudentController",
        method: "POST",
        async: false,
        data: {
            action: "getGraduateInfo",
            id: sId
        },
        success: function (data) {
            var data = JSON.parse(data);
            $("#inputGraduatePassingYear").val(data.passingYear);
            $("#inputGraduateCgpa").val(data.cgpa);
            $("#inputGraduateUniversity").val(data.university);
            $("#inputGraduateDegreeProgram").val(data.degreeProgram);
            $("#inputGraduateMarksheet").attr("src", data.marksheetImage);
            $("#inputGraduateCertificate").attr("src", data.certificateImage);


            $("html").animate({scrollTop: 0}, "slow");
        },
        error: function (data, textStatus, jqXHR) {
            alert("Error");
        }
    });
}
function getData() {
    var sId = $("#studentId").val();
    $.ajax({
        url: "StudentController",
        method: "POST",
        async: false,
        data: {
            action: "getStudentProfileRecord",
            id: sId
        },
        success: function (data) {
            var data = JSON.parse(data);
            $("#inputDepartmentId").val(data.department.departmentName);
            $("#inputFullName").val(data.fullName);
            $("#inputRollNumber").val(data.rollNumber);
            $("#inputFatherName").val(data.fathersName);
            $("#inputLegalId").val(data.legalId);
            $("#inputLegalIdNo").val(data.legalIdNo);
            $("#inputNationality").val(data.nationality);
            $("#inputPlaceOfIssueOfLegalId").val(data.placeOfIssueOfLegalId);
            $("#inputDateOfIssueOfLegalId").val(data.dateOfIssueOfLegalId);
            $("#inputMobileNumber").val(data.mobile);
            $("#inputDob").val(data.dob);
            $("#inputEmail").val(data.email);
            $("#inputDomicile").val(data.domicile);
            $("#inputCountryOfBirth").val(data.countryOfBirth);
            $("#inputBloodGroup").val(data.bloodGroup);
            $("#inputReligion").val(data.religion);
            $("#inputAddress").val(data.address);
            $("#inputCurrentAddress").val(data.currentAddress);
            $("#inputSemester").val(data.semester);
            $("#inputDepartment").val(data.department.departmentName);
            $("#inputBatch").val(data.batch);
            $("#inputAdmissionDate").val(data.admissionDate);
            $("#inputSupervisor").val(data.supervisor);
            $("#inputProgram").val(data.program);
            $("#inputFieldProgram").val(data.fieldProgram);
            $("#inputShift").val(data.shift);
            $("#inputTiming").val(data.timing);
            $("#studentProfileImage").attr("src", data.profileImage);

            $("html").animate({scrollTop: 0}, "slow");
        },
        error: function (data, textStatus, jqXHR) {
            alert("Error");
        }
    });
}
function getId(id) {
    studentId = id;
}
$(document).ready(function () {
    getData();
    getId(studentId);
    getMatriculationInfo();
    getIntermediateInfo();
    getGraduateInfo();
    $('#submitBtn1').click(function () {
        updateData();
    });
});

