/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function populateFeesList() {
    var studentId = $("#studentSessionId").val();
    $.ajax({
        url: "FeesController",
        method: "POST",
        async: false,
        data: {
            action: "getFeesListByStudentId",
            studentId: studentId
        },
        success: function (data) {
            var data = JSON.parse(data);
            var html = "<option value='' selected disabled>Select Fees Type</option>";
            for (var i = 0; i < data.length; i++) {
                html += "<option value='" + data[i].feesId + "'>" + data[i].feesTitle + "</option>";
            }
            $("#inputFeesTitle").html(html);
        }
    });
}

function addStudentFeesData() {
    var form = $("#studentFeesForm")[0];
    var data = new FormData(form);
    $.ajax({
        url: "FeesDetailsController",
        method: "POST",
        data: data,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        success: function (data) {
            alert("Record Added!");
            $("#myModal").modal("hide");
        }
    });
}

function fillFeesData(feesId) {
    $.ajax({
        url: "FeesController",
        method: "POST",
        async: false,
        data: {
            action: "getFeesRecord",
            feesId: feesId
        },
        success: function (data) {
            var data = JSON.parse(data);
            $("#inputFeesAmount").val(data.amount);
            $("#inputDateAnnounced").val(data.announcementDate);
            $("#inputDueDate").val(data.dueDate);
            $("#inputFeesId").val(data.feesId);
            $("#inputStudentId").val($("#studentSessionId").val());
        }
    });
}
function showStudentFeeRecords() {
    var studentId = $("#studentSessionId").val();
    $.ajax({
        url: "FeesController",
        method: "POST",
        async: false,
        data: {
            action: "showStudentFeeRecords",
            studentId: studentId
        },
        success: function (successData) {
            console.log(successData);
            var d = JSON.parse(successData);
//            if (successData.length > 2) {
//                if (successData[successData.length - 1] === ']' && successData[successData.length - 2] === '[') {
//                    d = JSON.parse(successData.substring(0, successData.length - 2));
//                } else if (successData[0] === '[' && successData[1] === ']') {
//                    d = JSON.parse(successData.substring(2, successData.length));
//                }
//            } else {
//                d = JSON.parse(successData);
//            }
            console.log(d);
            //var d = JSON.parse(successData);
            var html = "";
            for (var i = 0; i < d.length; i++) {
                if (d[i] !== null) {
                    html += "<tr><td >" + d[i].fees.feesTitle + "</td>";
                    html += "<td >" + d[i].fees.amount + "</td>";
                    html += "<td >" + d[i].fees.announcementDate + "</td>";
                    html += "<td >" + d[i].fees.dueDate + "</td>";
                    html += "<td >" + d[i].datePaid + "</td>";
                    html += "<td >" + d[i].amountPaid + "</td>";
                    html += "<td >" + d[i].lateFees + "</td>";
                    html += "<td >" + d[i].challanNumber + "</td>";
                    html += "<input type='hidden' id='imagePath' value='" + d[i].challanImage + "'/>";
                    html += "<td ><a href='" + d[i].challanImage + "' target='_blank' onclick=''> <img id='rowImage' src=" + d[i].challanImage + " style='width: 100px; height: 30px;'/></a></td>";
                    if (d[i].status === 'submitted' || d[i].status === 'accepted') {
                        html += "<td>" + d[i].status + "<td>";
                    } else {
                        html += "<td><a type='button' class='btn btn-info' onclick='getData(" + d[i].studentFeesId + ");'><i class='fas fa-pencil-alt'></i></a><td>";
                    }

                    html += "</tr>";
                }
            }
            $("#tab").html(html);
        }
    });
}

function getData(studentFeesId) {
    $.ajax({
        url: "FeesDetailsController",
        method: "POST",
        async: false,
        data: {
            action: "getStudentFeesRecord",
            studentFeesId: studentFeesId
        },
        success: function (data) {
            var data = JSON.parse(data);
            $("#myModal").modal("show");
            $("#inputStudentId").val(data.student.studentId);
            $("#inputFeesId").val(data.fees.feesId);
            var html = "<option>" + data.fees.feesTitle + "</option>";
            $("#inputFeesTitle").html(html);
            $("#inputFeesAmount").val(data.fees.amount);
            $("#inputDateAnnounced").val(data.fees.announcementDate);
            $("#inputDueDate").val(data.fees.dueDate);
            $("#inputDatePaid").val(data.datePaid);
            $("#inputAmountPaid").val(data.amountPaid);
            $("#inputLateFees").val(data.lateFees);
            $("#inputChallanNo").val(data.challanNumber);
            $("#addBtn").val("Update");
            $("#action").val("updateStudentFees");
            $("#inputStudentFeesId").val(studentFeesId);
        },
        error: function (data, textStatus, jqXHR) {
            alert("Error");
        }
    });
}

function updateStudentFeesRecord() {
    var form = $("#studentFeesForm")[0];
    var data = new FormData(form);
    $.ajax({
        url: "FeesDetailsController",
        method: "POST",
        data: data,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        success: function (data) {
            $("#myModal").modal("hide");
            location.reload();
        }
    });
}

$(document).ready(function () {
    showStudentFeeRecords();
    $(function () {
        $("#example1").DataTable({
            "responsive": true,
            "autoWidth": false
        });
    });
    $("#studentFeesForm").validate({
        rules: {
            feesTitle: {
                required: true
            },
            datePaid: {
                required: true
            },
            amountPaid: {
                required: true
            },
            lateFees: {
                required: true
            },
            challanNo: {
                required: true
            },
            challanImage: {
                required: true
            }
        },
        messages: {
            feesTitle: {
                required: "Select fees title"
            },
            datePaid: {
                required: "Select date paid"
            },
            amountPaid: {
                required: "Enter amount Paid"
            },
            lateFees: {
                required: "Enter late fees paid"
            },
            challanNo: {
                required: "Enter Challan Number"
            },
            challanImage: {
                required: "Select Challan Image"
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
    $("#inputFeesTitle").change(function () {
        fillFeesData($("#inputFeesTitle").val());
    });
    $("#addFeesBtn").click(function () {
        $("#addBtn").val("Add");
        populateFeesList();
        $("#myModal").modal("show");
    });
    $("#addBtn").click(function () {
        if ($("#studentFeesForm").valid()) {
            if ($("#addBtn").val() === 'Add') {
                addStudentFeesData();
            } else if ($("#addBtn").val() === 'Update') {
                updateStudentFeesRecord();
            }
        }
        showStudentFeeRecords();
    });
    $('#myModal').on('hidden.bs.modal', function (e) {
        $(this)
                .find("input,textarea,select")
                .val('')
                .end();
    });
});
if (window.history.replaceState) {
    window.history.replaceState(null, null, window.location.href);
}
