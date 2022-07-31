/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$("#nextBtn").on("click", function() {
    $("#tab2").prop("disabled", false);
    $("#tab2").click();
    $("#tab1").prop("disabled", true);
    $("#submitBtn").removeAttr("disabled");
    $("#nextBtn").prop("disabled", true);
    $("#submitBtn").attr("type", "submit");
});
$("#tab1").on("click", function() {
    $("#submitBtn").prop("disabled", true);
    $("#nextBtn").prop("disabled", false);
});
$("#submitBtn").on("click", function(){
    updatePersonalInformation();
    updateMatriculationInformation();
    updateIntermediateInformation();
    updateGraduateInformation();
    $("#modal-success").modal('show');
});
$("#continueBtn").on("click", function() {
    window.location.href = "student_dashboard.jsp";
});
function populateDepartments() {
    $.ajax({
        url: "DepartmentController",
        method: "POST",
        async: false,
        data: {
            action: "view"
        },
        success: function (data) {
            var data = JSON.parse(data);
            var html = "<option value='' selected disabled>Select Department</option>";
            for (var i = 0; i < data.length; i++) {
                html += "<option value='" + data[i].departmentName + "'>" + data[i].departmentName + "</option>";
            }
            $("#inputDepartmentId").html(html);
        }
    });
    
}
function updatePersonalInformation() {
    var form = $("#personalInformation")[0];
    var data = new FormData(form);
    $.ajax({
        type: "POST",
        url: "StudentController",
        data: data,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        success: function (data, textStatus, jqXHR) {
        }
        ,
        error: function (data, textStatus, jqXHR) {
            alert("Error");
        }
    }
    );
}
function updateMatriculationInformation() {
    var form = $("#academicMatriculation")[0];
    var data = new FormData(form);
    $.ajax({
        type: "POST",
        url: "StudentController",
        data: data,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        success: function (data, textStatus, jqXHR) {
        }
        ,
        error: function (data, textStatus, jqXHR) {
            alert("Error");
        }
    });
}
function updateIntermediateInformation() {
    var form = $("#academicIntermediate")[0];
    var data = new FormData(form);
    $.ajax({
        type: "POST",
        url: "StudentController",
        data: data,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        success: function (data, textStatus, jqXHR) {
        }
        ,
        error: function (data, textStatus, jqXHR) {
            alert("Error");
        }
    });
}
function updateGraduateInformation() {
    var form = $("#academicGraduate")[0];
    var data = new FormData(form);
    $.ajax({
        type: "POST",
        url: "StudentController",
        data: data,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        success: function (data) {
        },
        error: function (data, textStatus, jqXHR) {
            alert("Error");
        }
    });
}
$(document).ready(function() {
    populateDepartments();
   $("#tab2").prop("disabled", true);
});
