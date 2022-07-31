

var thesisId;
var stdId;
function saveThesis() {
    var thesisTitle = $("#inputThesisTitle").val();
    var studentRollNumber = $("#inputStudentRollNumber").val();
    var thesisExamDate = $("#inputThesisExamDate").val();
    var finalResults = $("#inputFinalResults").val();
    var remarks = $("#inputRemarks").val();
    $.ajax({
        type: "POST",
        url: "ThesisController",
        data: {
            action: "add",
            thesisTitle: thesisTitle,
            studentRollNumber: studentRollNumber,
            thesisExamDate: thesisExamDate,
            finalResults: finalResults,
            remarks: remarks
        },
        success: function (data, textStatus, jqXHR) {
            showData();
            $("html").animate({scrollTop: $(document).height()}, 1000);
        },
        error: function (data, textStatus, jqXHR) {
            alert("Error");
        }
    });
}
function showData() {
    $("#submitBtn").val("Add New Thesis");

    $.ajax({
        url: "ThesisDetailsController",
        method: "POST",
        async: false,
        data: {
            action: "view"
        },
        success: function (data) {

            var data = JSON.parse(data);

            var html = "";
            for (var i = 0; i < data.length; i++) {
                if (data[i].status === "pending") {
                    html += "<tr><td >" + data[i].thesisTitle + "</td>";
                    html += "<td >" + data[i].student.rollNumber + "</td>";
                    html += "<td >" + data[i].thesisExamDate + "</td>";
                    html += "<td >" + data[i].thesis.dueDate + "</td>";
                    html += "<td >" + data[i].finalResults + "</td>";
                    html += "<td >" + data[i].remarks + "</td>";
                    html += "<td ><select class='form-control' id='batchId'>";
                    html += "<option>" + data[i].status + "</option>";
                    html+="<option>approved</option>";
                    html+="<option>rejected</option>";
                    html+=  "</select></td>";
                    html += "<td class='text-center py-0 align-middle'><div class='btn-group btn-group-sm'>";
                    html += "<a type='button' class='btn btn-info' onclick='getData(" + data[i].thesisDetailsId + ");'><i class='fas fa-pencil-alt'></i></a>";
                    html += "<a type='button' class='btn btn-danger' onclick='deleteData(" + data[i].thesisDetailsId + ");'><i class='fas fa-trash'></i></a>";
                    html += "</div></td>";
                    html += "</tr>";
                }
                else if(data[i].status === "approved"){
                    html += "<tr><td >" + data[i].thesisTitle + "</td>";
                    html += "<td >" + data[i].student.rollNumber + "</td>";
                    html += "<td >" + data[i].thesisExamDate + "</td>";
                    html += "<td >" + data[i].thesis.dueDate + "</td>";
                    html += "<td >" + data[i].finalResults + "</td>";
                    html += "<td >" + data[i].remarks + "</td>";
                    html += "<td ><select class='form-control' id='batchId'>";
                    html += "<option>" + data[i].status + "</option>";
                    html+="<option>pending</option>";
                    html+="<option>rejected</option>";
                    html+=  "</select></td>";
                    html += "<td class='text-center py-0 align-middle'><div class='btn-group btn-group-sm'>";
                    html += "<a type='button' class='btn btn-info' onclick='getData(" + data[i].thesisDetailsId + ");'><i class='fas fa-pencil-alt'></i></a>";
                    html += "<a type='button' class='btn btn-danger' onclick='deleteData(" + data[i].thesisDetailsId + ");'><i class='fas fa-trash'></i></a>";
                    html += "</div></td>";
                    html += "</tr>";
                }
                else if(data[i].status === "rejected"){
                    html += "<tr><td >" + data[i].thesisTitle + "</td>";
                    html += "<td >" + data[i].student.rollNumber + "</td>";
                    html += "<td >" + data[i].thesisExamDate + "</td>";
                    html += "<td >" + data[i].thesis.dueDate + "</td>";
                    html += "<td >" + data[i].finalResults + "</td>";
                    html += "<td >" + data[i].remarks + "</td>";
                    html += "<td ><select class='form-control' id='batchId'>";
                    html += "<option>" + data[i].status + "</option>";
                    html+="<option>pending</option>";
                    html+="<option>approved</option>";
                    html+=  "</select></td>";
                    html += "<td class='text-center py-0 align-middle'><div class='btn-group btn-group-sm'>";
                    html += "<a type='button' class='btn btn-info' onclick='getData(" + data[i].thesisDetailsId + ");'><i class='fas fa-pencil-alt'></i></a>";
                    html += "<a type='button' class='btn btn-danger' onclick='deleteData(" + data[i].thesisDetailsId + ");'><i class='fas fa-trash'></i></a>";
                    html += "</div></td>";
                    html += "</tr>";
                }
                
            }
            $("#tab").html(html);
        }
    });
}
function getData(id) {
    thesisId = id;
    $.ajax({
        url: "ThesisDetailsController",
        method: "POST",
        async: false,
        data: {
            action: "getThesisRecord",
            thesisId: thesisId
        },
        success: function (data) {
            var data = JSON.parse(data);
            stdId=data.student.studentId;
            $("#inputThesisTitle").val(data.thesisTitle);
            $("#inputStudentRollNumber").val(data.student.rollNumber);
            $("#inputThesisAppliedDate").val(data.thesisExamDate);
            $("#inputFinalResults").val(data.finalResults);
            $("#inputRemarks").val(data.remarks);
            
            $("#submitBtn").val("Update Thesis");
            $("html").animate({scrollTop: 0}, "slow");
        },
        error: function (data, textStatus, jqXHR) {
            alert("Error");
        }
    });
}
function updateData() {
   
    var status=$("#batchId option:selected").val();
    var thesisTitle = $("#inputThesisTitle").val();  
    var thesisExamDate = $("#inputThesisAppliedDate").val();
    var finalResults = $("#inputFinalResults").val();
    var remarks = $("#inputRemarks").val();
    var id = thesisId;
    $.ajax({
        type: "POST",
        url: "ThesisDetailsController",
        data: {
            action: "update",
            thesisTitle: thesisTitle,
            studentId: stdId,
            thesisExamDate: thesisExamDate,
            finalResults: finalResults,
            remarks: remarks,
            thesisId: id,
            status:status
        },
        success: function (data, textStatus, jqXHR) {
            showData();
            $("html").animate({scrollTop: $(document).height()}, 1000);
        },
        error: function (data, textStatus, jqXHR) {
            alert("Error");
        }
    });
}
function deleteData(id) {
    thesisId = id;
    
    $.ajax({
        type: "POST",
        url: "ThesisDetailsController",
        data: {
            action: "delete",
            thesisId: thesisId
        },
        success: function (data, textStatus, jqXHR) {
            showData();
            $("html").animate({scrollTop: $(document).height()}, 1000);
            location.reload();
        },
        error: function (data, textStatus, jqXHR) {
            alert("Error");
        }
    });
}
$(document).ready(function () {
    showData();
    $(function () {
        $("#example1").DataTable({
            "responsive": true,
            "autoWidth": false
        });
    });
    $("#thesisForm").validate({
        rules: {
            thesisTitle: {
                required: true
            },
            studentId: {
                required: true
            }
        },
        messages: {
            thesisTitle: {
                required: "Please Enter Thesis Title"
            },
            studentId: {
                required: "Please Enter Student ID"
            }

        },
        errorElement: 'span',
        errorPlacement: function (error, element) {
            error.addClass('invalid-feedback');
            element.closest('.form-group').append(error);
        },
        highlight: function (element, errorClass, validClass) {
            $(element).addClass('is-invalid');
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).removeClass('is-invalid');
        }
    });
});
$("#submitBtn").on("click", function () {
    if ($("#thesisForm").valid()) {
        if ($("#submitBtn").val() === "Add New Thesis") {
            saveThesis();
        } else if ($("#submitBtn").val() === "Update Thesis") {
            updateData();
            showData();
        }
    }
});
if (window.history.replaceState) {
    window.history.replaceState(null, null, window.location.href);
}

